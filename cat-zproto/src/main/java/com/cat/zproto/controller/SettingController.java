package com.cat.zproto.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.cat.zproto.core.result.SystemCodeEnum;
import com.cat.zproto.core.result.SystemResult;
import com.cat.zproto.domain.system.SettingConfig;
import com.cat.zproto.domain.system.SettingMysql;
import com.cat.zproto.domain.system.SettingProto;
import com.cat.zproto.domain.system.SettingSvn;
import com.cat.zproto.domain.system.SettingVersion;
import com.cat.zproto.domain.template.TemplateStruct;
import com.cat.zproto.dto.TemplateTreeDto;
import com.cat.zproto.enums.TemplateEnum;
import com.cat.zproto.service.ModuleService;
import com.cat.zproto.service.ProtoService;
import com.cat.zproto.service.TemplateService;
import com.cat.zproto.util.Pair;

/**
 * 版本设置， 设置信息保存值setting.json文件 1. 系统设置 2.
 * 
 * @auth Jeremy
 * @date 2021年6月12日下午9:43:14
 */
@Controller
@RequestMapping("/setting")
public class SettingController {

	private static Logger logger = LoggerFactory.getLogger(SettingController.class);

	@Autowired
	private SettingConfig setting;
	
	@Autowired
	private ModuleService moduleService;
	
	@Autowired
	private ProtoService protoService;
	
	@Autowired
	private TemplateService templateService;
	
	/**
	 *设置信息
	 * @return ModelAndView
	 * @date 2021年6月12日下午9:50:40
	 */
	@RequestMapping("/radio")
    public ModelAndView settingView(){
    	ModelAndView mv = new ModelAndView();
    	mv.setViewName("radio");
    	mv.addObject("data", setting);
        return mv;
    } 

	/**
	 * 设置修改页
	 * @return ModelAndView
	 * @date 2021年6月12日下午9:50:40
	 */
	@ResponseBody
	@RequestMapping("/versionList")
	public Object versionList() {
		Collection<SettingVersion> versions = setting.getVersionInfo().values();
		return SystemResult.build(SystemCodeEnum.SUCCESS, versions);
	}

	/**
	 * 设置
	 * @return ModelAndView
	 * @date 2021年6月12日下午9:50:40
	 */
	@RequestMapping("/setting")
	public ModelAndView setting() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("setting");
		return mv;
	}

	/**
	 * 编辑模板页面
	 * @return ModelAndView
	 * @date 2021年6月12日下午9:50:40
	 */
	@RequestMapping("/editTemplate")
	public ModelAndView editTemplate() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("edit_template");
		return mv;
	}
	
	/**
	 * 模块列表
	 */
	@ResponseBody
	@RequestMapping("/templateFileList")
	public Object templateFileList() {
		List<TemplateTreeDto> dtos = new ArrayList<>();
		for (TemplateEnum tenum : TemplateEnum.values()) {
			TemplateTreeDto dto = tenum.newTreeDto();
			Collection<TemplateStruct> structs = templateService.getAllStruct(tenum.getType());
			for (TemplateStruct struct : structs) {
				dto.addChildren(struct.toTemplateDto());
			}
			dtos.add(dto);
		}
		return SystemResult.build(SystemCodeEnum.SUCCESS, dtos);
	}
	/**
	 * 设置修改页
	 * @return ModelAndView
	 * @date 2021年6月12日下午9:50:40
	 */
	@RequestMapping("/settingUpdateView")
	public ModelAndView settingUpdateView() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("edit_setting");
		mv.addObject("data", setting);
		return mv;
	}

	/**
	 * 添加版本界面
	 * @return ModelAndView
	 * @date 2021年6月12日下午9:51:13
	 */
	@RequestMapping("/addVersionView")
	public ModelAndView addVersionView() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("add_version");
		return mv;
	}
	
	/**
	 * 请求添加模板的页面
	 */
	@RequestMapping("/addTemplateView")
	public ModelAndView addTemplateView() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("add_template");
		return mv;
	}
	
	/**
	 * 添加版本, 从最新版本中复制所有信息内容, 作为分支信息
	 * 1. 新建目录
	 * 2.执行check out导出分支信息
	 * 3.从主干拷贝文件到新目录
	 * 4.提交目录到svn
	 * @return ModelAndView
	 * @date 2021年6月12日下午9:51:13
	 */
	@ResponseBody
	@RequestMapping("/addVersionSave")
	public Object addVersionSave(String version) {
		if (StringUtils.isBlank(version)) {
			return SystemResult.build(SystemCodeEnum.ERROR_PARAM);
		}
		else if (setting.getVersionInfo().containsKey(version)) {
			return SystemResult.build(SystemCodeEnum.ERROR_VERSION_EXIST);
		}
		//保存配置
		SettingVersion settingVersion = SettingVersion.create(version, Pair.of("None", "None"));
		//从主干目录拷贝文件到新目录
		Iterator<Entry<String, SettingVersion>> iter = setting.getVersionInfo().entrySet().iterator();
		SettingVersion first = iter.hasNext() ? iter.next().getValue() : null;
		if (first != null) {
			//有主干,拷贝主干的配置到新目录
			File srcDir = new File(first.getConfigDataDirPath());
			File destDir = new File(settingVersion.getConfigDataDirPath());
			try {
				destDir.mkdirs();
				FileUtils.copyDirectory(srcDir, destDir);
				//加载新分支配置
				moduleService.loadModuleProperties(settingVersion);
				protoService.loadProtoProperties(settingVersion);
			} catch (IOException e) {
				e.printStackTrace();
				logger.error("addVersionSave error, 复制主干文件出错", e);
				return SystemResult.build(SystemCodeEnum.ERROR_COPIED_TRUNK_DIR);
			}
		}else {
			//无主干,do nothing.
		}
		setting.addVersionInfo(settingVersion);
		setting.save();
		return SystemResult.build(SystemCodeEnum.SUCCESS);
	}
	
	/**
	 * 添加版本, 从最新版本中复制所有信息内容, 作为分支信息
	 * 1. 新建目录
	 * 2.执行check out导出分支信息
	 * 3.从主干拷贝文件到新目录
	 * 4.提交目录到svn
	 * @return ModelAndView
	 * @date 2021年6月12日下午9:51:13
	 */
	@ResponseBody
	@RequestMapping("/deleteVersion")
	public Object deleteVersion(@RequestParam(value = "versions[]") List<String> versions) {
		if (versions == null) {
			return SystemResult.build(SystemCodeEnum.ERROR_PARAM);
		}
		final int size = versions.size();
		if (size == 0) {
			return SystemResult.build(SystemCodeEnum.ERROR_PARAM);
		}
		else if (size >= 2) {
			return SystemResult.build(SystemCodeEnum.ERROR_DELETE_LIMIT_ONE);
		}
		//	删除目录, 实际上只能删除一个
		for (String version : versions) {
			SettingVersion settingInfo = setting.getVersionInfo().remove(version);
			if (settingInfo != null) {
				try {
					FileUtils.deleteDirectory(new File(settingInfo.getConfigDataDirPath()));
				} catch (IOException e) {
					logger.error("deleteVersion error, 删除版本错误", e);
					return SystemResult.build(SystemCodeEnum.ERROR_DELETE_BRANCH);
				}
			}
		}
		//	保存配置信息
		setting.save();
		return SystemResult.build(SystemCodeEnum.SUCCESS);
	}

	/**
	 * 添加保存数据库配置信息
	 * 
	 * @return
	 * @date 2021年6月12日下午9:51:13
	 */
	@ResponseBody
	@RequestMapping("/addSettingDb")
	public Object addSettingDb(@RequestBody SettingMysql info) {
		if (info == null) {
			return SystemResult.build(SystemCodeEnum.ERROR_PARAM);
		}
//		SettingMysql dbInfo = setting.getDbInfo();
//		dbInfo.setUrl(info.getUrl());
//		dbInfo.setUsername(info.getUsername());
//		dbInfo.setPassword(info.getPassword());
		setting.setDbInfo(info);
		return setting.save();
	}

	/**
	 * 添加保存proto配置信息
	 * 
	 * @return
	 * @date 2021年6月12日下午9:51:13
	 */
	@ResponseBody
	@RequestMapping("/addSettingProto")
	public Object addSettingProto(@RequestBody SettingProto info) {
		if (info == null) {
			return SystemResult.build(SystemCodeEnum.ERROR_PARAM);
		}
//		SettingProto proto = setting.getProto();
//		proto.setProtoExePath(info.getProtoExePath());
//		proto.setProtoPath(info.getProtoPath());
//		proto.setJavaPackagePath(info.getJavaPackagePath());
//		proto.setProtoIdSortBy(info.getProtoIdSortBy());
//		proto.setReqPrefix(info.getReqPrefix());
//		proto.setRespPrefix(info.getRespPrefix());
//		proto.setPbPrefix(info.getPbPrefix());
//		proto.setPtoroCoefficient(info.getPtoroCoefficient());
		setting.setProto(info);
		return setting.save();
	}

	/**
	 * 添加保存proto配置信息
	 * 
	 * @return
	 * @date 2021年6月12日下午9:51:13
	 */
	@ResponseBody
	@RequestMapping("/addSettingSvn")
	public Object addSettingSvn(@RequestBody SettingSvn info) {
		if (info == null) {
			return SystemResult.build(SystemCodeEnum.ERROR_PARAM);
		}
//		SettingSvn svn = setting.getSvn();
//		svn.setAccount(info.getAccount());
//		svn.setPassword(info.getPassword());
//		svn.setSourceCheckOutUrl(info.getSourceCheckOutUrl());
		setting.setSvn(info);
		return setting.save();
	}

}
