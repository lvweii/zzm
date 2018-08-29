package com.zzm.hd.dto;

import java.io.Serializable;
import java.util.Calendar;

/**
 * 单值列数据通用类
 * @author terry
 *
 */
public class Chardata  implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private Long senid ;//站号
    private Calendar time;//时间
    private Long utime= (long) 0; //微秒
    private Double v;//值
    private Long s;//数据质量
    private String valuetype;//标志，实测、平均、最大、最小、求和等
    private Calendar charatime;//特征值发生时间，只在求最值时生效
    private String rundatatype ;//运行数据取数类型说明，从实时表、小时表、日表、旬表、月表获得
    private int flag;//累积量1还是模拟量0
    private String recordDesc;//记录描述
    private String unit; //单位
    
	public Chardata(Long senid, Calendar time, Double v, Long s,
			String valuetype, String rundatatype, int flag) {
		this.senid = senid;
		this.time = time;
		this.v = v;
		this.s = s;
		this.valuetype = valuetype;
		this.rundatatype = rundatatype;
		this.flag = flag;
	}
	public Chardata() {
	}
	public Chardata(Long senid, Calendar time) {
		this.senid = senid;
		this.time = time;
	}
    public Long getSenid() {
		return senid;
	}
	
	public void setSenid(Long senid) {
		this.senid = senid;
	}
	public Calendar getTime() {
		return time;
	}
	public void setTime(Calendar time) {
		this.time = time;
	}
	public Double getV() {
		return v;
	}
	public void setV(Double v) {
		this.v = v;
	}
	public Long getS() {
		return s;
	}
	public void setS(Long s) {
		this.s = s;
	}

	
	public Calendar getCharatime() {
		return charatime;
	}

	public void setCharatime(Calendar charatime) {
		this.charatime = charatime;
	}

	public String getRundatatype() {
		return rundatatype;
	}

	public void setRundatatype(String rundatatype) {
		this.rundatatype = rundatatype;
	}

	public int getFlag() {
		return flag;
	}

	public void setFlag(int flag) {
		this.flag = flag;
	}

	public String getValuetype() {
		return valuetype;
	}

	public void setValuetype(String valuetype) {
		this.valuetype = valuetype;
	}

	public String getRecordDesc() {
		return recordDesc;
	}

	public void setRecordDesc(String recordDesc) {
		this.recordDesc = recordDesc;
	}
	
	public void setUnit(String unit){
		this.unit = unit;
	}
	
	public String getUnit(){
		return unit;
	}
	public void setUtime(Long utime) {
		this.utime = utime;
	}
	public Long getUtime() {
		return utime;
	}
	/**
	 * 获得总毫秒数*1000
	 * @return 总毫秒数
	 */
	public Long getTotalUtime()
	{
		Long tm = null;
		if(time != null )
		{
			tm = time.getTimeInMillis()*1000; 
		}
		if(utime != null)
			tm+=utime;
		
		return tm;
	}
}

