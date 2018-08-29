package com.zzm.hd.param;

import java.io.Serializable;

/**
 * @author terry
 * 附加查询条件，当基本接口不能满足应用需求时，在基本接口数据基础上附加查询使用
 * charctype 属性名称
 * condition 查询条件，支持>,<,>=,<=,=,in,like
 * values 查询条件值或值数组 ，注意值要与chartype中的值类型一致,否则会出现值不匹配异常
 */
public class ExCond implements Serializable {

public ExCond(String charctype, String condition, Object values) {
		this.charctype = charctype;
		this.condition = condition;
		this.values = values;
	}
private 	String charctype;
private 	String condition;
private 	Object values;
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
public Object getValues() {
	return values;
}
public void setValues(Object values) {
	this.values = values;
}
	
}
