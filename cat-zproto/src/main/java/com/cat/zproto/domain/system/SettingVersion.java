package com.cat.zproto.domain.system;

import java.io.File;
import java.util.Date;
import org.apache.commons.lang3.time.FastDateFormat;
import com.cat.zproto.constant.CommonConstant;
import com.cat.zproto.util.Pair;

/**
 * 版本控制
 * @author Jeremy
 */
public class SettingVersion {
	
	/**
	 * 版本号,对内版本号
	 */
	private String version;
	/**
	 * 版本控制路径<br>
	 * 因为git跟svn的用法略显不同, 所以可能未来会抽象出一个版本控制类<br>
	 * 分别用于git合svn的版本控制
	 */
	private Pair<String, String> versionControllPath;
	
	/**
	 * 初始化日期
	 */
	private String initDate;
	
	
	//=====================无需存储字段======================
	/**
	 * module结构存储的路径
	 */
	private transient String modulePath;
	/**
	 * proto结构路径
	 */
	private transient String protoDataPath;
	/**
	 * proto协议号路径
	 */
	private transient String protoIdPath;
	/**
	 * 生成的代码路径
	 */
	private transient String codePath;
	/**
	 * 生成的协议路径
	 */
	private transient String protoMessagePath;
	/**
	 * 生成的目录
	 */
	private transient String genDir;
	
	/**
	 * @param versionControllPath
	 */
	public SettingVersion(String version, Pair<String, String> versionControllPath) {
		this.version = version;
		this.versionControllPath = versionControllPath;
		this.initDate = FastDateFormat.getInstance("yyyy-MM-dd HH:mm:ss").format(new Date());
		
		try {
//			File dir = ResourceUtils.getFile(CommonConstant.RESOURCE_CONFIGDATA_PATH);
//			ClassPathResource resource = new ClassPathResource(CommonConstant.SYSTEM_SETTING);
//			ClassPathResource resource = new ClassPathResource();
//			InputStream inputStream= resource.getInputStream();
//			String content = FileUtils.readFileToString(file,StandardCharsets.UTF_8);
//			String content = IOUtils.toString(inputStream, StandardCharsets.UTF_8);
			
//			String path = Thread.currentThread().getContextClassLoader().getResource("").getPath()+"/configdata";
			String path = CommonConstant.RESOURCE_CONFIGDATA_PATH;
			String versionDir = path.concat(File.separator).concat(version);
			
			File file = new File(versionDir);
			file.mkdirs();
			
			this.modulePath = versionDir.concat(CommonConstant.MODULE_FILE_NAME);
			this.protoDataPath = versionDir.concat(CommonConstant.PROTO_FILE_NAME);
			this.protoIdPath = versionDir.concat(CommonConstant.PROTO_ID_FILE_NAME);
			
			//生成路径
			this.genDir = CommonConstant.GENERATOR_PATH.concat(version);
			file = new File(versionDir);
			file.mkdirs();
			
			this.codePath = genDir.concat(CommonConstant.CODE_PACKAGE);
			this.protoMessagePath =  genDir.concat(CommonConstant.PROTO_PACKAGE);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public String getVersion() {
		return version;
	}
	public Pair<String, String> getVersionControllPath() {
		return versionControllPath;
	}
	public void setVersionControllPath(Pair<String, String> versionControllPath) {
		this.versionControllPath = versionControllPath;
	}
	public String getInitDate() {
		return initDate;
	}

	public String modulePath() {
		return modulePath;
	}

	public String protoDataPath() {
		return protoDataPath;
	}

	public String protoIdPath() {
		return protoIdPath;
	}

	public String codePath() {
		return codePath;
	}
	
	public String protoMessagePath() {
		return protoMessagePath;
	}
	/**
	 * 生成目录
	 * @return
	 */
	public String genDir() {
		return genDir;
	}
	
	
}