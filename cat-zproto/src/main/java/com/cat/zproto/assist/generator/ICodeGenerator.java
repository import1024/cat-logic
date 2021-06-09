package com.cat.zproto.assist.generator;

import com.cat.zproto.dto.TableFreemarkerDto;

public interface ICodeGenerator extends IGenerator{
	
	/**
	 * 获取proto名字,freemarker根据此名字获得模板
	 * @return  
	 * @return String  
	 * @date 2021年6月6日下午9:09:41
	 */
	String getProtoName(TableFreemarkerDto dto);
	
	/**
	 * 生成
	 * @param dto  
	 * @return void  
	 * @date 2021年6月6日下午9:18:14
	 */
	void generate(TableFreemarkerDto dto);
}