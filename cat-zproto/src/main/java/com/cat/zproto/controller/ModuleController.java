package com.cat.zproto.controller;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.cat.zproto.assist.generator.IDefineCodeGenerator;
import com.cat.zproto.assist.generator.IDefineProtoGenerator;
import com.cat.zproto.constant.CommonConstant;
import com.cat.zproto.core.result.SystemCodeEnum;
import com.cat.zproto.core.result.SystemResult;
import com.cat.zproto.domain.module.ModuleEntity;
import com.cat.zproto.domain.proto.ProtocolObject;
import com.cat.zproto.domain.proto.ProtocolStructure;
import com.cat.zproto.domain.system.SettingConfig;
import com.cat.zproto.domain.system.SettingVersion;
import com.cat.zproto.domain.table.po.AssistProperties;
import com.cat.zproto.domain.table.po.Entity;
import com.cat.zproto.domain.table.po.Properties;
import com.cat.zproto.domain.table.po.TableEntity;
import com.cat.zproto.domain.template.TemplateStruct;
import com.cat.zproto.dto.DataBeanDto;
import com.cat.zproto.dto.TableFreemarkerDto;
import com.cat.zproto.dto.TemplateDto;
import com.cat.zproto.enums.ProtoTypeEnum;
import com.cat.zproto.enums.TemplateEnum;
import com.cat.zproto.service.CommandService;
import com.cat.zproto.service.DbService;
import com.cat.zproto.service.ModuleService;
import com.cat.zproto.service.ProtoService;
import com.cat.zproto.service.SvnService;
import com.cat.zproto.service.TemplateService;
import com.cat.zproto.util.StringUtil;
import com.cat.zproto.util.ZipUtil;
import com.google.common.collect.BiMap;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import freemarker.cache.StringTemplateLoader;
import freemarker.template.Template;

/**
 * ?????????????????????????????? 1. ???????????? 2. ???????????? 3. ???????????? 4. ???????????? 5. ????????????
 * 
 * 6. ???????????? 7. ???????????? 8. ???????????? 9. ????????????
 * 
 * 10.???????????? 11.????????????
 * 
 * @author Jeremy
 */
@Controller
@RequestMapping("/module")
public class ModuleController {

	private static Logger logger = LoggerFactory.getLogger(ModuleController.class);

	@Autowired
	private ModuleService moduleService;

	@Autowired
	private ProtoService protoService;

	@Autowired
	private SettingConfig setting;

	@Autowired
	private TemplateService templateService;

	@Autowired
	private CommandService commandService;

	@Autowired
	private DbService dbService;

	@Autowired
	private IDefineProtoGenerator defineProtoGenerator;
	
	@Autowired
	private IDefineCodeGenerator defineCodeGenerator;

	@Autowired
	private SvnService svnService;

	/**
	 * home????????????
	 * @return
	 * @return ModelAndView
	 * @date 2021???6???12?????????9:50:40
	 */
	@RequestMapping("/index")
	public ModelAndView index() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("index");
		return mv;
	}
	
	/**
	 * ?????????
	 */
	@RequestMapping("/viewModule")
	public ModelAndView viewModule(String version) {
		ModelAndView mv = new ModelAndView();
		if (!setting.getVersionInfo().containsKey(version)) {
			mv.setViewName("error");
		}else {
			mv.setViewName("view_module");
			mv.addObject("version", version);
			mv.addObject("dbType", setting.getDbInfo().getDbType());
		}
		return mv;
	}

	/**
	 * ??????????????????
	 */
	@RequestMapping("/addModuleView")
	public ModelAndView addModuleView(String version) {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("add_module");
		mv.addObject("version", version);
		return mv;
	}

	/**
	 * ????????????
	 */
	@RequestMapping("/editModuleView")
	public ModelAndView editModuleView(String version, int id) {
		ModuleEntity moduleEntitie = moduleService.getModuleEntity(version, id);
		ModelAndView mv = new ModelAndView();
		mv.setViewName("edit_module");
		mv.addObject("version", version);
		mv.addObject("data", moduleEntitie);
		return mv;
	}
	
	/**
	 * ???????????????
	 */
	@RequestMapping("/editBeanInfoView")
	public Object editBeanInfoView(String version, int id) {
		ModelAndView mv = new ModelAndView();
		ModuleEntity moduleEntity = moduleService.getModuleEntity(version, id);
		
		if (moduleEntity == null) {
			mv.setViewName("error");
			return mv;
		}
		
		mv.setViewName("edit_beanInfo");
		mv.addObject("version", version);
		mv.addObject("id", id);
		mv.addObject("data", moduleEntity);
		return mv;
	}
	
	/**
	 * ??????bean????????????
	 */
	@RequestMapping("/addBeanPropertiesView")
	public Object addBeanPropertiesView(String version) {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("add_bean_properties");
		mv.addObject("version", version);
		return mv;
	}
	
	/**
	 * ?????????????????????
	 */
	@ResponseBody
	@RequestMapping("/dataBeanList")
	public Object dataBeanList(String version, int id) {
		ModuleEntity moduleEntity = moduleService.getModuleEntity(version, id);
 		if (moduleEntity == null) {
			return SystemResult.build(SystemCodeEnum.ERROR_CANNOT_DOUND_MODULE);
		}
		TableEntity tableEntity = dbService.getTableEntity(moduleEntity.getName());
		if (tableEntity == null) {
			return SystemResult.build(SystemCodeEnum.ERROR_CANNOT_DOUND_MODULE);
		}
		ArrayList<Properties> ret = new ArrayList<>();
		ret.addAll(tableEntity.getProperties());
		//logger.info("ret:{}", ret);
		return SystemResult.build(SystemCodeEnum.SUCCESS, ret);
	}
	
	/**
	 * ?????????????????????
	 */
	@ResponseBody
	@RequestMapping("/dataAssistBeanList")
	public Object dataAssistBeanList(String version, int id) {
		ModuleEntity moduleEntity = moduleService.getModuleEntity(version, id);
		if (moduleEntity == null) {
			return SystemResult.build(SystemCodeEnum.ERROR_CANNOT_DOUND_MODULE);
		}
		TableEntity tableEntity = dbService.getTableEntity(moduleEntity.getName());
		if (tableEntity == null) {
			return SystemResult.build(SystemCodeEnum.ERROR_CANNOT_DOUND_MODULE);
		}
		ArrayList<AssistProperties> ret = new ArrayList<>();
		for (Entity<AssistProperties> assistEntity : tableEntity.getAssistEntityMap().values()) {
			ret.addAll(assistEntity.getProperties());
		}
		//logger.info("dataAssistBeanList, ret:{}", ret);
		return SystemResult.build(SystemCodeEnum.SUCCESS, ret);
	}
	
	/**
	 * ???????????????????????????
	 * @return
	 * @return ModelAndView
	 * @date 2021???6???12?????????9:50:40
	 */
	@ResponseBody
	@RequestMapping(value = "/saveDataBean")
	public Object saveDataBean(@RequestBody DataBeanDto dto) {
		//???????????????????????????
		ModuleEntity moduleEntity = moduleService.getModuleEntity(dto.getVersion(), dto.getModuleId());
		if (moduleEntity == null) {
			return SystemResult.build(SystemCodeEnum.ERROR_CANNOT_DOUND_MODULE);
		}
		TableEntity tableEntity = dbService.getTableEntity(moduleEntity.getName());
		//????????????
		tableEntity.setProperties(dto.getPropertiesDtos());

		//??????????????????
		Map<String, Entity<AssistProperties>> temp = new HashMap<>();
		for (AssistProperties assist:dto.getAssistPropertiesDtos()) {
			Entity entity = temp.getOrDefault(assist.getEntityName(), new Entity<AssistProperties>());
			entity.addProperties(assist);
			temp.put(assist.getEntityName(), entity);
		}
		tableEntity.setAssistEntityMap(temp);
		dbService.saveTableEntity(moduleEntity.getName());
		return SystemResult.build(SystemCodeEnum.SUCCESS);
	}
	
	/**
	 * ???????????????????????????
	 * @return
	 * @return ModelAndView
	 * @date 2021???6???12?????????9:50:40
	 */
	@ResponseBody
	@RequestMapping(value = "/editBeanPropertiesSave")
	public Object editBeanPropertiesSave(@RequestBody DataBeanDto dto) {
		//???????????????????????????
		ModuleEntity moduleEntity = moduleService.getModuleEntity(dto.getVersion(), dto.getModuleId());
		if (moduleEntity == null) {
			return SystemResult.build(SystemCodeEnum.ERROR_CANNOT_DOUND_MODULE);
		}
//		TableEntity tableEntity = dbService.getTableEntity(moduleEntity.getName());
//		Properties property = new Properties();
//		property.setIndexId(tableEntity.getNextIndexId());
//		property.setDesc(dto.getDesc());
//		property.setField(dto.getField());
//		property.setType(StringEscapeUtils.escapeHtml4(dto.getType()));
//		property.setKeyword(dto.getKeyword());
//		property.setInit(StringEscapeUtils.escapeHtml4(dto.getInit()));
//		tableEntity.addEntityBeans(property);
//		
//		dbService.saveTableEntity(moduleEntity.getName());
		return SystemResult.build(SystemCodeEnum.SUCCESS);
	}
	
	/**
	 * ??????????????????????????????????????????
	 * @return
	 * @return ModelAndView
	 * @date 2021???6???12?????????9:50:40
	 */
	@ResponseBody
	@RequestMapping(value = "/editBeanAssistPropertiesSave")
	public Object editBeanAssistPropertiesSave(@RequestBody DataBeanDto dto) {
		//???????????????????????????
		ModuleEntity moduleEntity = moduleService.getModuleEntity(dto.getVersion(), dto.getModuleId());
		if (moduleEntity == null) {
			return SystemResult.build(SystemCodeEnum.ERROR_CANNOT_DOUND_MODULE);
		}
		// ????????????????????????
		TableEntity tableEntity = dbService.getTableEntity(moduleEntity.getName());
		// ????????????
		logger.info("=====>{}", dto);
//		dto.getPropertiesDtos();
//		tableEntity.setProperties(dto.getPropertiesDtos());
		
//		Entity<AssistProperties> assistEntity = tableEntity.getOrCreateAssistEntity(dto.getAssistEntityName());
//		assistEntity.
		
//		Properties property = new Properties();
//		property.setIndexId(tableEntity.getNextIndexId());
//		property.setDesc(dto.getDesc());
//		property.setField(dto.getField());
//		property.setType(StringEscapeUtils.escapeHtml4(dto.getType()));
//		property.setKeyword(dto.getKeyword());
//		property.setInit(StringEscapeUtils.escapeHtml4(dto.getInit()));
//		tableEntity.addEntityBeans(property);
//		
//		dbService.saveTableEntity(moduleEntity.getName());
		return SystemResult.build(SystemCodeEnum.SUCCESS);
	}

	/**
	 * ?????????????????? 1.req?????? 2.ack?????? 3.dto????????????
	 * 
	 * @param version ??????
	 * @param id      ??????id
	 */
	@RequestMapping("/protoEditView")
	public Object protoEditView(String version, int id) {
		ModelAndView mv = new ModelAndView();
		mv.addObject("version", version);
		mv.addObject("id", id);
		mv.addObject("sortBy", setting.getProto().getProtoIdSortBy());
		ModuleEntity entity = moduleService.getModuleEntity(version, id);
		if (entity == null) {
			mv.setViewName("error");
			return mv;
		}
		mv.setViewName("edit_protocol");
		ProtocolObject protoObject = protoService.getProtoObject(version, entity.getName());
		if (protoObject == null) {
			return mv;
		}
		/*
		 *???????????? 
		 *1. ????????????
		 *2.?????????????????????
		 */
		List<String> allTypes = new ArrayList<>(ProtoTypeEnum.enumMap.values());
//		allTypes.addAll(protoService.getAllPbProtoName(version, setting.getProto().getPbPrefix()));
		/*
		 * ??????????????????????????????, ??????????????????,???????????????????????????????????? ??????????????????????????????????????? 20210603
		 * ??????????????????, ????????????????????? 20210611
		 */
		BiMap<String, Integer> protoIdMap = protoService.getProtoIdMap(version);
		TreeMap<Integer, ProtocolStructure> structureMap = new TreeMap<>();
		List<ProtocolStructure> structures = new ArrayList<ProtocolStructure>();
		for (ProtocolStructure struct : protoObject.getStructures().values()) {
			String name = struct.getName();
			int protoId = protoIdMap.getOrDefault(name, 0);
			if (protoId == 0) {//??????????????????
				structures.add(struct);
			} else {
				structureMap.put(protoId, struct);
			}
		}
		structures.addAll(structureMap.values());
		mv.addObject("data", structures);
		mv.addObject("allTypes", allTypes);
		return mv;
	}

	/**
	 * ?????????????????? 1.req?????? 2.ack?????? 3.dto????????????
	 * 
	 * @param version ??????
	 * @param id      ??????id
	 */
	@RequestMapping("/protoListView")
	public Object protoListView(String version, int id) {
		ModelAndView mv = new ModelAndView();
		ModuleEntity entity = moduleService.getModuleEntity(version, id);
		if (entity == null) {
			return SystemResult.build(SystemCodeEnum.ERROR_PARAM);
		}
		ProtocolObject protoObject = protoService.getProtoObject(version, entity.getName());
		if (protoObject == null) {
			mv.setViewName("no_proto");
			return mv;
		}
		BiMap<String, Integer> protoNameIdMap = protoService.getProtoIdMap(version);
		Collection<ProtocolStructure> structures = protoObject.getStructures().values();

		// ??????treeMap????????????????????????????????????
		TreeMap<Integer, ProtocolStructure> requests = new TreeMap<>();
		TreeMap<Integer, ProtocolStructure> responses = new TreeMap<>();
		List<ProtocolStructure> pbs = new ArrayList<>();

		String reqPrefix = setting.getProto().getReqPrefix();
		String respPrefix = setting.getProto().getRespPrefix();
		String pbPrefix = setting.getProto().getPbPrefix();

		for (ProtocolStructure struct : structures) {
			String name = struct.getName();
			int protoId = protoNameIdMap.getOrDefault(name, 0);
			if (protoId == 0) {
				if (name.startsWith(pbPrefix)) {
					pbs.add(struct);
				}
			} else if (name.startsWith(reqPrefix)) {
				requests.put(protoId, struct);
			} else if (name.startsWith(respPrefix)) {
				responses.put(protoId, struct);
			}
		}
		mv.setViewName("view_protpcol");
		mv.addObject("version", version);
		mv.addObject("requests", requests.values());
		mv.addObject("responses", responses.values());
		mv.addObject("pbs", pbs);
		mv.addObject("protoNameIdMap", protoNameIdMap);
		return mv;
	}

	/**
	 * ????????????, ?????????????????????, ???????????????, ?????????proto??????, ?????????proto??????
	 * 
	 * @param version      ??????
	 * @param id           ??????id
	 */
	@ResponseBody
	@RequestMapping("/protoCommit")
	public Object protoCommit(String version, int id, @RequestBody List<ProtocolStructure> protoStructure) {
		if (protoStructure == null) {
			return SystemResult.build(SystemCodeEnum.ERROR_PARAM);
		}
		if (protoStructure.isEmpty()) {
			return SystemResult.build(SystemCodeEnum.ERROR_NO_CHANGE);
		}
		ModuleEntity entity = moduleService.getModuleEntity(version, id);
		if (entity == null) {
			return SystemResult.build(SystemCodeEnum.UNKNOW);
		}
		protoService.updateProtoObject(version, entity.getName(), protoStructure);
		protoService.updateProtoIds(version, moduleService.getAllModuleEntity(version));
		return SystemResult.build(SystemCodeEnum.SUCCESS);
	}

	/**
	 * ??????????????????<br>
	 * ???*.proto?????????,???????????????protoObject, ?????????????????????.
	 * 
	 * @return void
	 * @date 2021???6???5?????????10:49:16
	 */
	@RequestMapping("/protoReverseLoad")
	public Object protoReverseLoad(String version) {
		ModelAndView mv = new ModelAndView();
		boolean result = protoService.reverseLoad(version);
		result = result & moduleService.reverseLoad(version, protoService.getAllProtoObject(version),
				protoService.getProtoIdMap(version));
		if (result) {
			mv.setViewName("index");
			mv.addObject("version", version);
		} else {
			mv.setViewName("error");
		}
		return mv;
	}

	/**
	 * ??????????????????<br>
	 * CALL "bin/protoc.exe" --java_out=%java_out% --proto_path=%proto_path% %%i
	 * <br>
	 * CALL "bin/protoc.exe" --csharp_out=%csharp_out% --proto_path=%proto_path% %%i
	 * <br>
	 * {protoc} -I={protoPath} --java_out={javaPath} {moduleName}.proto <br>
	 * %s -I=%s --java_out=%s %s.proto
	 * 
	 * @param version  ??????
	 * @param id       ??????id
	 * @param langType ????????????,all????????????
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/createMessage")
	public Object createMessage(String version, int id, String langType) {
		ModuleEntity entity = moduleService.getModuleEntity(version, id);
		if (entity == null) {
			return SystemResult.build(SystemCodeEnum.ERROR_PARAM);
		}
		ProtocolObject protoObject = protoService.getProtoObject(version, entity.getName());
		if (protoObject == null) {
			return SystemResult.build(SystemCodeEnum.ERROR_PARAM);
		}
		// ????????????,????????????????????????
		String ret = "";
		SystemResult result = null;
		if (langType.equals("all")) {
//			Map<String, String> pathMap = setting.getProto().getGeneratorPath();
//			for (String languegeType : pathMap.keySet()) {
//				result = createMessage(version, protoObject, languegeType);
//				ret = ret.concat(String.format(CommonConstant.GENERATE_RESULT, languegeType, result.tips())).concat("<br>");
//			}
			return SystemResult.build(SystemCodeEnum.ERROR_NOT_SUPPORT);
		} else {
			result = createMessage(version, protoObject, langType);
			ret = String.format(CommonConstant.GENERATE_RESULT, langType, result.tips());
		}
		result.setTips(ret);
		return result;
	}

	private SystemResult createMessage(String version, ProtocolObject protoObject, String langType) {
		String protoFormat = CommonConstant.PROTOC_EXECUTE_FORMAT;
		
		String protoExePath = setting.getProto().getProtoExePath();
		String os = System.getProperty("os.name");
		if (os.toLowerCase().startsWith("win")) {
			protoExePath = protoExePath.concat("/").concat("protoc.exe");
		}else {
			protoExePath = protoExePath.concat(File.separator).concat("protoc");
		}
		File exePath = new File(protoExePath);
		protoExePath = exePath.getPath();
		
		String protoPath = setting.getProto().getProtoPath();
		String languageType = langType;
		SettingVersion versionInfo = setting.getVersionInfo().get(version);
		String genPath = versionInfo.getGenDir().concat(File.separator).concat(langType);
		String outClassName = protoObject.getOutClass();
		
		String command = String.format(protoFormat, protoExePath, protoPath, languageType, genPath, outClassName);
		SystemResult result = commandService.execCommand(command);
		logger.info("command:{}, result:{}", command, result);

		Collection<TemplateStruct> struts = templateService.getAllStruct(TemplateEnum.PROTO.getType());
		for (TemplateStruct templateStruct : struts) {
			defineProtoGenerator.generate(version, templateStruct, protoObject);
		}
		return result;
	}
	
	@ResponseBody
	@RequestMapping("/downloadCode")
	public String downloadCode(String version, int id, HttpServletResponse response) {
		ModuleEntity entity = moduleService.getModuleEntity(version, id);
		if (entity == null) {
			return "????????????";
		}
		String fileName = entity.getName().toLowerCase().concat(".zip");
		String path = CommonConstant.DOWNLOAD_PACKAGE.concat(File.separator).concat(fileName);
		
		File file = new File(path);
		if (!file.isFile()) {
			return "????????????, ?????????????????????";
		}
		try (InputStream inStream = new FileInputStream(file.getAbsolutePath());
				BufferedInputStream bis = new BufferedInputStream(inStream);) {
			OutputStream os = response.getOutputStream();
			// ?????????????????????
//            response.reset();
			response.setContentType("application/force-download");// ???????????????????????????
			response.addHeader("Content-Disposition", "attachment;fileName=" + fileName);// ???????????????
			byte[] b = new byte[1024];
			int len;
			while ((len = inStream.read(b)) > 0) {
				os.write(b, 0, len);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return "????????????";
		}
		return "????????????";
	}
	
	/**
	 * ??????????????????
	 * @return
	 * @return ModelAndView
	 * @date 2021???6???12?????????9:50:40
	 */
	@ResponseBody
	@RequestMapping("/runTemplate")
	public Object runTemplate(@RequestBody String str) {
		freemarker.template.Configuration configuration = new freemarker.template.Configuration(freemarker.template.Configuration.VERSION_2_3_29);
		StringTemplateLoader templateLoader = new StringTemplateLoader();
		configuration.setTemplateLoader(templateLoader);
		configuration.setDefaultEncoding("UTF-8");
		Iterator<Entry<String, SettingVersion>> iter = setting.getVersionInfo().entrySet().iterator();
		SettingVersion first = iter.hasNext() ? iter.next().getValue() : null;
		//?????????????????????, ??????dto
		TableFreemarkerDto dto = this.createFreemarkerDto(first.getVersion(), 101);
		// template??????????????????, ?????????????????????????????????key
		templateLoader.putTemplate("template", str); 
		configuration.setClassicCompatible(true);
		String ret = "";
		try (StringWriter stringWriter = new StringWriter();){
			Template template = configuration.getTemplate("template", "utf-8");
			template.process(dto, stringWriter);
			ret = stringWriter.toString();
		} catch (Exception e) {
			logger.error("runTemplate error", e);
			return SystemResult.build(SystemCodeEnum.ERROR_GENERATE_FREEMAKER, e.getMessage());
		}		
		return SystemResult.build(SystemCodeEnum.SUCCESS, ret);
	}
	
	/**
	 * ????????????????????????
	 * @return
	 * @return ModelAndView
	 * @date 2021???6???12?????????9:50:40
	 */
	@ResponseBody
	@RequestMapping("/showTemplateDetail")
	public Object showTemplateDetail(@RequestBody TemplateDto templateDto) {
		//???????????????????????????
		try {
			int nodeId = templateDto.getCurNode();
			String name = templateDto.getFileName();
			TemplateStruct struct = templateService.getStruct(nodeId, name);
			if (struct == null) {
				return SystemResult.build(SystemCodeEnum.ERROR_NOT_FOUND_FREEMAKER);
			}
			return SystemResult.build(SystemCodeEnum.SUCCESS, struct.getContent());
		} catch (Exception e) {
			logger.error("showTemplate error, {}",e);
			return SystemResult.build(SystemCodeEnum.SUCCESS, e.getMessage());
		}
	}
	
	/**
	 * ??????????????????,?????????????????????,??????????????????
	 * @return
	 * @return ModelAndView
	 * @date 2021???6???12?????????9:50:40
	 */
	@ResponseBody
	@RequestMapping(value = "/updateTemplateDetail", method = {RequestMethod.POST})
	public Object updateTemplateDetail(@RequestBody TemplateDto templateDto) {
		//???????????????????????????
		int id = templateDto.getCurNode();
		String fileName = templateDto.getFileName();
		String content = templateDto.getContent();
		try {
			SystemCodeEnum code = templateService.updateStruct(id, fileName, content);
			if (code != SystemCodeEnum.SUCCESS) {
				return SystemResult.build(code);
			}
			return SystemResult.build(SystemCodeEnum.SUCCESS, content);
		} catch (Exception e) {
			logger.error("showTemplate error, {}",e);
			return SystemResult.build(SystemCodeEnum.UNKNOW, e.getMessage());
		}
	}
	
	/**
	 * ???????????????,???????????????????????????
	 * @return
	 * @return ModelAndView
	 * @date 2021???6???12?????????9:50:40
	 */
	@ResponseBody
	@RequestMapping(value = "/saveTemplateDetail", method = {RequestMethod.POST})
	public Object saveTemplateDetail(@RequestBody TemplateDto templateDto) {
		//???????????????????????????
		String fileName = templateDto.getFileName();
		String content = templateDto.getContent();
		try {
			//?????????????????????,???????????????
			if (!StringUtil.isEnglish(fileName)) {
				return SystemResult.build(SystemCodeEnum.ERROR_SUBFIX_NOT_RIGHT);
			}
			SystemCodeEnum code = templateService.saveStruct(templateDto.getCurNode(), fileName, content);
			if (code != SystemCodeEnum.SUCCESS) {
				return SystemResult.build(code);
			}
			return SystemResult.build(SystemCodeEnum.SUCCESS, content);
		} catch (Exception e) {
			logger.error("showTemplate error, {}",e);
			return SystemResult.build(SystemCodeEnum.SUCCESS, e.getMessage());
		}
	}
	
	/**
	 * ??????????????????,?????????????????????,??????????????????
	 * @return
	 * @return ModelAndView
	 * @date 2021???6???12?????????9:50:40
	 */
	@ResponseBody
	@RequestMapping(value = "/deleteTemplateFile")
	public Object deleteTemplateFile(@RequestBody TemplateDto templateDto) {
		//???????????????????????????
		int id = templateDto.getCurNode();
		String fileName = templateDto.getFileName();
		try {
			SystemCodeEnum code = templateService.deleteStruct(id, fileName);
			if (code != SystemCodeEnum.SUCCESS) {
				return SystemResult.build(code);
			}
			return SystemResult.build(SystemCodeEnum.SUCCESS);
		} catch (Exception e) {
			logger.error("showTemplate error, {}",e);
			return SystemResult.build(SystemCodeEnum.UNKNOW, e.getMessage());
		}
	}
	
	/**
	 * ????????????
	 * @return
	 * @return ModelAndView
	 * @date 2021???6???12?????????9:50:40
	 */
	@ResponseBody
	@RequestMapping(value = "/renameTemplateFile")
	public Object renameTemplateFile(@RequestBody TemplateDto dto) {
		//???????????????????????????
		try {
			String fileName = dto.getFileName();
			if (fileName.endsWith(CommonConstant.TEMPLATE_SUBFIX)) {
				fileName = fileName.replaceAll(CommonConstant.TEMPLATE_SUBFIX, "");
			}
			if (!StringUtil.isEnglish(fileName)) {
				return SystemResult.build(SystemCodeEnum.ERROR_SUBFIX_NOT_RIGHT);
			}
			SystemCodeEnum code = templateService.renameStruct(dto.getCurNode(), dto.getFileName());
			if (code != SystemCodeEnum.SUCCESS) {
				return SystemResult.build(code);
			}
			return SystemResult.build(SystemCodeEnum.SUCCESS);
		} catch (Exception e) {
			logger.error("showTemplate error, {}",e);
			return SystemResult.build(SystemCodeEnum.UNKNOW, e.getMessage());
		}
	}
	
	/**
	 * ????????????
	 * @return
	 * @return ModelAndView
	 * @date 2021???6???12?????????9:50:40
	 */
	@ResponseBody
	@RequestMapping(value = "/newTemplateFile")
	public Object newTemplateFile(@RequestBody TemplateDto dto) {
		//???????????????????????????
		try {
			int curNodeId  = dto.getCurNode();
			String name = "undefined";
			return templateService.newStruct(curNodeId, name);
		} catch (Exception e) {
			logger.error("showTemplate error, {}",e);
			return SystemResult.build(SystemCodeEnum.UNKNOW, e.getMessage());
		}
	}
	
	/**
	 * ????????????<br>
	 * ???????????????module??????, ?????????????????????table, ????????????table??????????????????.<br>
	 * ????????????????????????????????????, ??????????????????. ????????????????????????????????????<br>
	 * 
	 * @note ????????????--moduleName??????????????????????????? <br>
	 *       ??????????????????, ?????????????????????????????????, ??????????????????????????????????????????.
	 * @return void
	 * @date 2021???6???6?????????3:38:25
	 */
	@ResponseBody
	@RequestMapping("/createCode")
	public Object createCode(String version, int id) {
		ModuleEntity entity = moduleService.getModuleEntity(version, id);
		if (entity == null) {
			return SystemResult.build(SystemCodeEnum.ERROR_CANNOT_FOUND_PROTOOBJECT);
		}
//		ProtocolObject protoObject = protoService.getProtoObject(version, entity.getName());
//		if (protoObject == null) {
//			return SystemResult.build(SystemCodeEnum.ERROR_CANNOT_DOUND_MODULE);
//		}
//		TableEntity tableEntity = dbService.getTableEntity(entity.getName());
//		if (tableEntity == null) {
//			return SystemResult.build(SystemCodeEnum.ERROR_NOT_FOUND_TABLE);
//		}
		
		TableFreemarkerDto dto = this.createFreemarkerDto(version, id);
//		TableFreemarkerDto dto = new TableFreemarkerDto(tableEntity, protoObject, entity);
//		//?????????????????????
//		List<ProtocolStructure> protoPBStructList = Lists.newArrayList();
//		List<ProtocolStructure> protoReqStructList = Lists.newArrayList();
//		Map<String, ProtocolStructure> protoAckStructMap = Maps.newHashMap();
//		Map<String, ProtocolStructure> structureMap = protoObject.getStructures();
//
//		String reqPrefix = setting.getProto().getReqPrefix();
//		String respPrefix = setting.getProto().getRespPrefix();
//		String pbPrefix = setting.getProto().getPbPrefix();
//		
//		for (String key : structureMap.keySet()) {
//			if (key.startsWith(respPrefix)) {
//				String newKey = key.replace(respPrefix, reqPrefix);
//				protoAckStructMap.put(newKey, structureMap.get(key));
//			} else if (key.startsWith(reqPrefix)) {
//				protoReqStructList.add(structureMap.get(key));
//			} else if (key.startsWith(pbPrefix)) {
//				protoPBStructList.add(structureMap.get(key));
//			}
//		}
//		dto.getProtoAckStructMap().putAll(protoAckStructMap);
//		dto.getProtoReqStructList().addAll(protoReqStructList);
//		dto.getProtoPBStructList().addAll(protoPBStructList);
		Collection<TemplateStruct> struts = templateService.getAllStruct(TemplateEnum.CODE.getType());
		for (TemplateStruct struct : struts) {
			defineCodeGenerator.generate(version, struct, dto);
		}
		// ????????????
		zipCode(version, entity);
		return SystemResult.build(SystemCodeEnum.SUCCESS);
	}

	@ResponseBody
	@RequestMapping("/svn")
	public Object svn(String version, int id, String opt) {
		if (opt.equals("checkOut")) {
			svnService.checkOut();
		}
		if (opt.equals("update")) {
			svnService.update(CommonConstant.GENERATOR_PATH);
		}
		if (opt.equals("commit")) {
			svnService.doCleanup(CommonConstant.GENERATOR_PATH);
			svnService.doCommit(CommonConstant.GENERATOR_PATH);
		}
		return SystemResult.build(SystemCodeEnum.SUCCESS);
	}

	/**
	 * ????????????
	 * 
	 * @param version
	 * @param entity
	 */
	private void zipCode(String version, ModuleEntity entity) {
		// ????????????
		String entityName = entity.getName().toLowerCase();
		SettingVersion versionInfo = setting.getVersionInfo().get(version);
		String srcPath = versionInfo.getCodePath().concat(File.separator).concat(entityName);
		String zipPath = CommonConstant.DOWNLOAD_PACKAGE;
		try {
			ZipUtil.zip(srcPath, zipPath, entityName);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * ????????????
	 */
	@ResponseBody
	@RequestMapping("/addModule")
	public Object addModule(String version, @RequestBody ModuleEntity entity) {
		if (entity == null || entity.getId() == 0 || entity.getName() == "") {
			return SystemResult.build(SystemCodeEnum.ERROR_PARAM).result();
		}
		if (moduleService.getModuleEntity(version, entity.getId()) != null) {
			return SystemResult.build(SystemCodeEnum.ERROR_ADD_REPEAT).result();
		}
		moduleService.replacModuleEntity(version, entity);
		return SystemResult.build(SystemCodeEnum.SUCCESS);
	}

	/**
	 * ????????????
	 */
	@ResponseBody
	@RequestMapping("/editModule")
	public Object editModule(String version, @RequestBody ModuleEntity entity) {
		if (entity == null || entity.getId() == 0 || entity.getName() == "") {
			return SystemResult.build(SystemCodeEnum.ERROR_PARAM).result();
		}
		moduleService.replacModuleEntity(version, entity);
		return SystemResult.build(SystemCodeEnum.SUCCESS);
	}

	/**
	 * ????????????
	 */
	@ResponseBody
	@RequestMapping("/deleteModule")
	public Object deleteModule(String version, @RequestParam(value = "ids[]") int[] ids) {
		if (ids.length > 2) {
			return SystemResult.build(SystemCodeEnum.ERROR_DELETE_LIMIT_TWO);
		}
		for (Integer id : ids) {
			moduleService.removeModuleEntity(version, id);
		}
		return SystemResult.build(SystemCodeEnum.SUCCESS);
	}

	/**
	 * ????????????
	 * 
	 * @param version
	 */
	@ResponseBody
	@RequestMapping("/moduleList")
	public Object moduleList(String version) {
		Collection<ModuleEntity> moduleEntities = moduleService.getAllModuleEntity(version);
		return SystemResult.build(SystemCodeEnum.SUCCESS, moduleEntities);
	}
	
	/**
	 * ???module, proto, ?????????????????????, ?????????dto, ????????????freemaker??????
	 * @param version
	 * @param moduleId
	 * @return
	 */
	private TableFreemarkerDto createFreemarkerDto(String version, int moduleId){
		ModuleEntity entity = moduleService.getModuleEntity(version, moduleId);
		if (entity == null) {
			return null;
		}
		ProtocolObject protoObject = protoService.getProtoObject(version, entity.getName());
		if (protoObject == null) {
			return null;
		}
		TableEntity tableEntity = dbService.getTableEntity(entity.getName());
		if (tableEntity == null) {
			return null;
		}
		TableFreemarkerDto dto = new TableFreemarkerDto(tableEntity, protoObject, entity);
//		//?????????????????????
		List<ProtocolStructure> protoPBStructList = Lists.newArrayList();
		List<ProtocolStructure> protoReqStructList = Lists.newArrayList();
		Map<String, ProtocolStructure> protoAckStructMap = Maps.newHashMap();
		Map<String, ProtocolStructure> structureMap = protoObject.getStructures();

		String reqPrefix = setting.getProto().getReqPrefix();
		String respPrefix = setting.getProto().getRespPrefix();
		String pbPrefix = setting.getProto().getPbPrefix();
		
		for (String key : structureMap.keySet()) {
			if (key.startsWith(respPrefix)) {
				String newKey = key.replace(respPrefix, reqPrefix);
				protoAckStructMap.put(newKey, structureMap.get(key));
			} else if (key.startsWith(reqPrefix)) {
				protoReqStructList.add(structureMap.get(key));
			} else if (key.startsWith(pbPrefix)) {
				protoPBStructList.add(structureMap.get(key));
			}
		}
		dto.getProtoAckStructMap().putAll(protoAckStructMap);
		dto.getProtoReqStructList().addAll(protoReqStructList);
		dto.getProtoPBStructList().addAll(protoPBStructList);
		return dto;
	}

	/**
	 * ??????proto????????????
	 */
	@ResponseBody
	@RequestMapping("/selectProtoObjList")
	public List<String> selectProtoList(String version, int moduleId, String input){
		ModuleEntity entity = moduleService.getModuleEntity(version, moduleId);
		if (entity == null) {
			return null;
		}
		List<String> allTypes = new ArrayList<>();
		if (StringUtils.isBlank(input)) {
			//????????????????????????, ?????????????????????
			allTypes.addAll(ProtoTypeEnum.enumMap.values());
		}else {
			for(String s : ProtoTypeEnum.enumMap.values()) {
				if (s.startsWith(input)) {
					allTypes.add(s);
				}
			}
			//?????????????????????????????????
			allTypes.addAll(protoService.getAllPbProtoName(version, input));
		}
		return allTypes;
	}

}
