package com.zzm.hd.param;

import java.util.Calendar;

public class TimeCond implements Cloneable, java.io.Serializable {

	private static final long serialVersionUID = 5845958896339618799L;

	public TimeCond(String btmcond, Calendar bt, String etmcond, Calendar et) {
		this.btmcond = btmcond;
		this.etmcond = etmcond;
		this.bt = bt;
		this.et = et;
	}

	// 开始时间条件 允许输入:">" 或">=" 或"=" 或"<"或"<="
	public String btmcond;
	// 结束时间条件 允许输入:">" 或">=" 或"=" 或"<"或"<="
	public String etmcond;
	// 开始时间
	public Calendar bt;
	// 结束时间
	public Calendar et;

	@Override
	public Object clone() {
		return new TimeCond(this.btmcond, this.bt, this.etmcond, this.et);
	}
}
