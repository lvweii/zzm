package com.zzm.hd.param;

import java.io.Serializable;

/**
 * @author terry
 * 界面用附加查询条件，当基本接口不能满足应用需求时，在基本接口数据基础上附加查询使用
 * charctype 属性名称
 * condition 查询条件，支持>,<,>=,<=,=,in,like
 * values 查询条件值
 * combineStr 连接条件，and or
 */
public class WebGenSqlCond implements Serializable {
	public WebGenSqlCond(String charctype, String condition, String values) {
		super();
		this.charctype = charctype;
		this.condition = condition;
		this.values = values;
	}
	//连接条件可以为空
	public WebGenSqlCond(String charctype, String condition, String values,
			String combineStr) {
		super();
		this.charctype = charctype;
		this.condition = condition;
		this.values = values;
		this.combineStr = combineStr;
	}
	private 	String charctype;
	private 	String condition;
	private 	String values;
	private 	String combineStr;
	public String getCharctype() {
		return charctype;
	}
	public void setCharctype(String charctype) {
		this.charctype = charctype;
	}
	public String getCondition() {
		return condition;
	}
	public void setCondition(String condition) {
		this.condition = condition;
	}
	public String getValues() {
		return values;
	}
	public void setValues(String values) {
		this.values = values;
	}
	public String getCombineStr() {
		return combineStr;
	}
	public void setCombineStr(String combineStr) {
		this.combineStr = combineStr;
	}
}
