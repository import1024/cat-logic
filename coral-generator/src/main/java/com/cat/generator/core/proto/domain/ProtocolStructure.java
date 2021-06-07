package com.cat.generator.core.proto.domain;

import java.util.ArrayList;
import java.util.List;

/**
 * 一个协议体结构
 */
public class ProtocolStructure {
	/**
	 * 协议名称
	 */
	private String name;
	/**
	 * 备注信息
	 */
	private String comment;
	/**
	 * 字段属性
	 */
	private List<ProtocolField> fields = new ArrayList<>();
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public List<ProtocolField> getFields() {
		return fields;
	}
	public void setFields(List<ProtocolField> fields) {
		this.fields = fields;
	}
	@Override
	public String toString() {
		return "ProtocolStructure [name=" + name + ", comment=" + comment + ", fields=" + fields + "]";
	}
}
