package com.zzm.hd.param;

import java.util.Calendar;

public class DeleteParam  implements java.io.Serializable{
	
	//应用类型说明  允许输入 Param.AppType.APP_Type_NC|Param.AppType.APP_Type_WDS|Param.AppType.APP_Type_PM|Param.AppType.APP_Type_MODEL|Param.AppType.APP_Type_QX其中之一)
	public String apptype;
	//计算类型说明  允许输入 Param.CalcType.CALC_RT|Param.CalcType.CALC_REAL|Param.CalcType.CALC_SUM|Param.CalcType.CALC_AVG|Param.CalcType.CALC_MAX|Param.CalcType.CALC_MIN其中之一)
	public String optype;
		
	//运行数据取数类型说明 
	//允许输入以下之一：
	//Param.RunDataType.RUN_RTREAL
    //Param.RunDataType.RUN_RT  获取实时数据
	//Param.RunDataType.RUN_TSDB 获取5分钟数据
	//Param.RunDataType.RUN_HOUR 获取小时数据
	//Param.RunDataType.RUN_DAY  获取日数据
	//Param.RunDataType.RUN_DAY10 获取旬数据
	//Param.RunDataType.RUN_MONTH 获取月数据
	//Param.RunDataType.RUN_YEAR 获取年数据
    //Param.RunDataType.RUN_ALL 获取全时段累计值求和
	//Param.RunDataType.RUN_DINVALUE 获取DIN数据
	//Param.RunDataType.RUN_TRACE   获取故障追忆数据
	public String rundatatype;
	//值类型说明  允许输入 Param.ValType.CALC_RT|Param.ValType.CALC_REAL|Param.ValType.CALC_SUM|Param.ValType.CALC_AVG|Param.ValType.CALC_MAX|Param.ValType.CALC_MIN其中之一

	//用于删除一系列点号数据时生效
	public Long[] idarrayLongs;
	//用于删除一系列文件名时生效
	public String[] idarrayStrings;
	public Calendar tb;
	public Calendar te;
	//附加Int标志说明，当函数内有需要特别要求时传入 ，deletefiledb时用于传入类型号
	public Integer ex_int=-1;

}
