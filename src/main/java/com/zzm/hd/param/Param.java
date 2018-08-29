package com.zzm.hd.param;
/**
 * 各层次接口调用类参数定义
 * @author terry
 *
 */
public final class Param {
	
	/**
	 * 应用类型说明
	 * 使用方式:HashMap put(App_Type_Key,APP_Type_NC|APP_Type_WDS|APP_Type_PM其中之一)
	 * @author terry
	 *
	 */
	public static final class AppType
	{	
		/**
		 * App_Type_Key是Key.
		 */
		public static final String App_Type_Key = "App_Type";
		/**
		 * APP_Type_MODEL是针对模型数据应用
		 */
		public static final String APP_Type_MODEL= "APP_MODEL";
		/**p
		 * APP_Type_NC是针对监控应用
		 */
		public static final String APP_Type_NC= "APP_NC";
		
		/**
		 * APP_Type_WDS是针对水调应用
		 */
		public static final String APP_Type_WDS= "APP_WDS";
		/**
		 * APP_Type_PM是针对大坝应用
		 */
		public static final String APP_Type_PM= "APP_PM";
		/**
		 * APP_Type_PM是针对气象系统应用
		 */
		public static final String APP_Type_QX= "APP_QX";
		
		/**
		 * APP_Type_ST323是针对实时水雨情应用
		 */
		public static final String APP_Type_ST323= "APP_ST323";
		
		/**
		 * APP_Type_ST325是针对实时水雨情应用
		 */
		public static final String APP_Type_ST325= "APP_ST325MODEL";
		/**
		 * APP_Type_STRV是针对中小河流水文应用
		 */
		public static final String APP_Type_STRV= "APP_STRV";
		
		/**
		 * APP_Type_HB是针对环保系统应用
		 */
		public static final String APP_Type_HB= "APP_HB";
	}
	
	/**
	 * 操作类型说明
	 * 使用方式:HashMap put(OP_Type_Key,OP_SELECT|OP_INSERT|OP_DELETE|OP_UPDATE|OP_INSERTORUPDATE其中之一)
	 * @author yangning
	 *
	 */
	public static final class OpType
	{
		/**
		 * OP_Type_Key是key
		 */
		public static final String OP_Type_Key= "Op_Type";
		/**
		 * OP_SELECT是读取数据
		 */
		public static final String OP_SELECT = "OP_SELECT";
		
		/**
		 * OP_INSERT是插入数据
		 */
		public static final String OP_INSERT = "OP_INSERT";
		
		/**
		 * OP_DELETE是删除数据
		 */
		public static final String OP_DELETE = "OP_DELETE";
		
		/**
		 * OP_UPDATE是更新数据
		 */
		public static final String OP_UPDATE = "OP_UPDATE";
		
		/**
		 * OP_INSERTORUPDATE是插入或更新数据
		 */
		public static final String OP_INSERTORUPDATE = "OP_INSERTORUPDATE";
		/**
		 * OP_AUTO_INSERTORUPDATE是先插入,如果插入失败则更新数据 (推荐)
		 */
		public static final String OP_AUTO_INSERTORUPDATE = "OP_AUTO_INSERTORUPDATE";
    }


			
	/**
	 * 时间说明	
	 * 使用方式:HashMap put(EQ_BTIME|GE_BTIME|GT_BTIME|LE_BTIME|LT_BTIME|EQ_ETIME|GE_ETIME|GT_ETIME|LE_ETIME|LT_ETIME其中之一,date)
	 * @author yangning
	 *
	 */
	public static final class TimeType
	{
		/**
		 * EQ_BTIME开始时间等于条件
		 */
		public static final String EQ_BTIME= "EQ_BTIME";
		/**
		 * EQ_ETIME结束时间等于条件
		 */
		public static final String EQ_ETIME= "EQ_ETIME";
		
		/**
		 * GE_BTIME开始时间大于等于条件
		 */
		public static final String GE_BTIME= "GE_BTIME";
		/**
		 * GE_ETIME结束时间大于等于条件
		 */
		public static final String GE_ETIME= "GE_ETIME";
		/**
		 * GT_BTIME开始时间大于条件
		 */
		public static final String GT_BTIME= "GT_BTIME";
		/**
		 * GT_ETIME结束时间大于条件
		 */
		public static final String GT_ETIME= "GT_ETIME";
		/**
		 * LE_BTIME开始时间小于等于条件
		 */
		public static final String LE_BTIME= "LE_BTIME";
		/**
		 * LE_ETIME结束时间小于等于条件
		 */
		public static final String LE_ETIME= "LE_ETIME";
		/**
		 * LT_BTIME开始时间小于条件
		 */
		public static final String LT_BTIME= "LT_BTIME";
		/**
		 * LT_ETIME结束时间小于条件
		 */
		public static final String LT_ETIME= "LT_ETIME";
		
	}
	
	/**
	 * 站号数组说明
	 * 使用方式:HashMap put(LONG_IDARRAY|STR_IDARRAY其中之一,Object[])
	 * @author yangning
	 *
	 */
	public static final class IdType
	{	
		
		/**
		 * LONG_IDARRAY是站号数组
		 */
		public static final String LONG_IDARRAY="LONG_IDARRAY";
		/**
		 * LONG_IDARRAY是字符串数组
		 */
		public static final String STR_IDARRAY="STR_IDARRAY";

	}
	
	/**
	 * 运算类型说明 
	 * 使用方式:HashMap put(Value_Type_Key,CALC_RT|CALC_REAL|CALC_SUM|CALC_AVG|CALC_MAX|CALC_MIN其中之一)
	 * @author yangning
	 *
	 */
	public static final class CalcType
	{
		/**
		 * Value_Type_Key是key
		 */
		public static final String Value_Type_Key= "Calc_Type";
		


		/**
		 * CALC_SUM是求和值
		 */
		public static final String CALC_SUM= "CALC_SUM";
		
		/**
		 * CALC_AVG是平均值
		 */
		public static final String CALC_AVG= "CALC_AVG";
		
		/**
		 * CALC_MAX是最大值
		 */
		public static final String CALC_MAX= "CALC_MAX";
		
		/**
		 * CALC_MIN是最小值
		 */
		public static final String CALC_MIN= "CALC_MIN";
	
		/**
		 * CALC_REAL计算最新一条数据，最靠近结束时间的数据，如果没送结束时间就取最新一条数据
		 */
		public static final String CALC_REAL= "CALC_REAL";
		/**
		 * CALC_SPANREAL计算时段内最新数据，如果时段内没值不返回
		 */
		public static final String CALC_SPANREAL= "CALC_SPANREAL";


	}
	
	/**
	 * 运行数据取数类型说明
	 * 使用方式:HashMap put(Rundata_Type_Key,RUN_RTREAL|RUN_RT|RUN_TSDB|RUN_HOUR|RUN_DAY|RUN_DAY10|RUN_MONTH|RUN_YEAR|RUN_ALL|RUN_DINVALUE|RUN_TRACE其中之一)
	 * @author yangning
	 *
	 */
	/**
	 * 运行数据取数类型说明
	 * 使用方式:HashMap put(Rundata_Type_Key,RUN_RTREAL|RUN_RT|RUN_TSDB|RUN_HOUR|RUN_DAY|RUN_DAY10|RUN_MONTH|RUN_YEAR|RUN_ALL|RUN_DINVALUE|RUN_TRACE其中之一)
	 * @author yangning
	 *
	 */
	public static final class RunDataType
	
	{
		/**
		 * Rundata_Type_Key是key
		 */
		public static final String Rundata_Type_Key= "Rundata_Type";
	
		/**
		 * RUN_RTREAL是取最新一条断面数据
		 */
		public static final String RUN_RTREAL= "RUN_RTREAL";
		/**
		 * RUN_RTCOUNT是取最新雨量累计数据
		 */
		public static final String RUN_RTCOUNT= "RUN_RTCOUNT";

		/**
		 * RUN_RT是取实时数据
		 */
		public static final String RUN_RT= "RUN_RT";
		/**
		 * RUN_TSDB是取5分钟 数据
		 */
		public static final String RUN_TSDB= "RUN_TSDB";
		/**
		 * RUN_HOUR是取小时数据
		 */
		public static final String RUN_HOUR= "RUN_HOUR";
		/**
		 * RUN_DAY是取日数据
		 */
		public static final String RUN_DAY= "RUN_DAY";
		/**
		 * RUN_DAY10是取旬数据
		 */
		public static final String RUN_DAY10= "RUN_DAY10";
		/**
		 * RUN_MONTH是取月数据
		 */
		public static final String RUN_MONTH= "RUN_MONTH";
		/**
		 * RUN_MONTH是取季度数据
		 */
		public static final String RUN_MONTH3= "RUN_MONTH3";
		/**
		 * RUN_YEAR是取年数据
		 */
		public static final String RUN_YEAR= "RUN_YEAR";
		/**
		 * RUN_DAY3是取3日数据，水利行业统计量的一种时标
		 */
		public static final String RUN_DAY3= "RUN_DAY3";
		
		/**
		 * RUN_DAY7是取侯数据，水利行业统计量的一种时标
		 */
		public static final String RUN_DAY7= "RUN_DAY7";
		/**
		 * RUN_BX是取报讯数据
		 */
		public static final String RUN_BX= "RUN_BX";
		/**
		 * RUN_DINVALUE 是取动作记录数据
		 */
		public static final String RUN_DINVALUE= "RUN_DINVALUE";
		
		/**
		 * RUN_TRACE是取事故追忆数据
		 */
		public static final String RUN_TRACE="RUN_TRACE";
		
		/**
		 * RUN_ALL取全时段内各类数据，即不满小时从实时表取数，不满日从小时表取数，否则从日表取数据
		 */
		public static final String RUN_ALL= "RUN_ALL";
		/**
		 * PLAN_LOAD取发电计划数据
		 */
		public static final String RUN_PLANLOAD= "RUN_PLANLOAD";
		
		/**
		 * ADDB取高级应用预报数据
		 */
		public static final String RUN_ADDB= "RUN_ADDB";
		
		/**
		 * 获取机组或闸门开始到结束范围内的序列数据，开始时间若没有数据以最靠近开始时间的数据补上
		 */
		public static final String RUN_SERAIL= "RUN_SERAIL";
		/**
		 * 获取机组或闸门开始到结束范围内的动作时间和动作次数
		 */
		public static final String RUN_COUNT= "RUN_COUNT";
		/**
		 * 获取故障录波文件列表信息
		 */
		public static final String RUN_COMTRADE_FILE= "RUN_COMTRADE_FILE";
		/**
		 * 获取故障录波文件测点信息
		 */
		public static final String RUN_COMTRADE_POINT= "RUN_COMTRADE_POINT";
		/**
		 * 获取故障录波波形
		 */
		public static final String RUN_COMTRADE_CURVE= "RUN_COMTRADE_CURVE";
	}
	
	
	/**
	 * 特殊附加类型说明
	 * @author yangning
	 * 使用方式:HashMap put(EX_INT|EX_SLIPSTEP|EX_SLIPWIN|EX_SQL其中之一,object)
	 *
	 */
	public static final class ExParamType
	{	
		/**
		 * EX_INT附加Int标志
		 */
		public static final String EX_INT= "EX_INT";
		//
		/**
		 * EX_SLIPSTEP是滑动步长
		 */
		public static final String EX_SLIPSTEP= "EX_SLIPSTEP";
		//
		/**
		 * EX_SLIPWIN是滑动窗口
		 */
		public static final String EX_SLIPWIN= "EX_SLIPWIN";
		
		/**
		 * EX_SQL是自定义sql，其中[TB],[TE],[LONGIDARRAY],[STRIDARRAY]可以用时间和站号数组替换
		 */
		public static final String EX_SQL= "EX_SQL";

		/**
		 * EX_INT附加Int标志
		 */
		public static final String EX_INTERVAL_INT= "EX_INTERVAL_INT";
		
		/**
		 * EX_USERNAME写数据时的用户名
		 */
		public static final String EX_USERNAME= "EX_USERNAME";
		
		/**
		 * EX_MACHINE写数据时的机器名
		 */
		public static final String EX_MACHINE= "EX_MACHINE";
		/**
		 * EX_PROGRAM写数据时的程序名
		 */
		public static final String EX_PROGRAM= "EX_PROGRAM";
		/**
		 * EX_DOUBLE附加DOUBLE值
		 */
		public static final String EX_DOUBLE= "EX_DOUBLE";
		/**
		 * EX_STRING附加STRING值
		 */
		public static final String EX_STRING= "EX_STRING";
		/**
		 * EX_STRING附加STRING1值
		 */
		public static final String EX_STRING1= "EX_STRING1";
		/**
		 * EX_STRING附加STRING2值
		 */
		public static final String EX_STRING2= "EX_STRING2";
		
		/**
		 * 排序标志，0是升序，1降序
		 */
		public static final String EX_ORDERFLAG= "EX_ORDERFLAG";
		/**
		 * 排序字段, 是一个string数组, 例：new String[] { "id.senid" , "id.time"}
		 */
		public static final String EX_ORDERCOLUMN= "EX_ORDERCOLUMN";
		
		//数据稀疏标识
		public static final String EX_FILTER= "EX_FILTER";

	}
	
	/**
	 * 模型测点类型说明
	 * @author caijie
	 */
	public  static final class ModelPointType{	
		/**
		 * NC_DIN 监控DIN类型测点
		 */
		public static final int NC_DIN = 73;
		/**
		 * NC_AIN 监控AIN类型测点
		 */
		public static final int NC_AIN= 71;
		/**
		 * NC_TIN 监控TIN类型测点
		 */
		public static final int NC_TIN = 72;
		/**
		 * NC_SOE 监控SOE类型测点
		 */
		public static final int NC_SOE = 74;
		/**
		 * NC_PIN 监控PIN类型测点
		 */
		public static final int NC_PIN = 75;
		/**
		 * NC_DOUT 监控DOUT类型测点
		 */
		public static final int NC_DOUT = 76;
		/**
		 * NC_CTRL 监控CTRL类型测点
		 */
		public static final int NC_CTRL = 77;
		/**
		 * NC_AOUT 监控AOUT类型测点
		 */
		public static final int NC_AOUT = 78;
		/**
		 * NC_PID 监控PID类型测点
		 */
		public static final int NC_PID = 79;

		/**
		 * WDS_POINT 水情类型测点
		 */
		public static final int WDS_POINT = 31;
		/**
		 * DS 大坝类型测点
		 */
		public static final int DS_POINT = 51;
		/**
		 * DS Ln类型
		 */
		public static final int WDS_LN = 30;
		/**
		 * 监控Ln类型
		 */
		public static final int NC_LN = 70;
		/**
		 * 水情Ln类型
		 */
		public static final int DS_LN = 50;
		/**
		 * 综合联动类型
		 */
		public static final int ZH_POINT = 90;
	};
	/**
	 * 值类型说明 
	 * 使用方式:HashMap put(Value_Type_Key,CALC_RT|CALC_REAL|CALC_SUM|CALC_AVG|CALC_MAX|CALC_MIN其中之一)
	 * @author yangning
	 *
	 */
	public static final class ValType
	{
		/**
		 * Specal_Type_Key是key
		 */
		public static final String Special_Type_Key= "Special_Type";
	
		/**
		 *Special_V是 实测值
		 */
		public static final String Special_V= "V";
		
		/**
		 *Special_AVGV是 平均值
		 */
		public static final String Special_AVGV= "AVGV";
		
		/**
		 *Special_MAXV是最大值
		 */
		public static final String Special_MAXV= "MAXV";
		
		/**
		 *Special_MINV是 最小值
		 */
		public static final String Special_MINV= "MINV";
		/**
		 *Special_ALL是取时段数据的全部特征值
		 */
		public static final String Special_ALL= "VALUE_ALL";
		
		/**
		 *Special_RT是 实时值
		 */
		public static final String Special_RT= "CALC_RT";
		
		/**
		 *Special_REAL是 最新测值
		 */
		public static final String Special_REAL= "CALC_REAL";
		/**
		 *Special_COMPARE是获取最新两个值的比较结果,NAN 无效比较 ，1升序，0平，-1降序
		 */
		public static final String Special_COMPARE= "CALC_COMPARE";


	}
	
	/**
	 * @author yangning
	 * 滑动类型 在滑动计算时生效
	 * 使用方式:HashMap put(Slip_Type_Key,Slip_Type_Hour|Slip_Type_Ts|Slip_Type_Day其中之一)
	 */
	public static final class SlipType
	{	
		/**
		 * Slip_Type_Key是Key.
		 */
		public static final String Slip_Type_Key = "Slip_Type";
		/**
		 * Slip_Type_Hour是按小时类型滑动
		 */
		public static final String Slip_Type_Hour= "SLIP_TIME_HOUR";
		/**
		 * Slip_Type_Ts是按5分钟类型滑动
		 */
		public static final String Slip_Type_Ts= "SLIP_TIME_TS";
		/**
		 * Slip_Type_Day是按日类型滑动
		 */
		public static final String Slip_Type_Day= "SLIP_TIME_Day";
		/**
		 * Slip_Type_MONTH是按月类型滑动
		 */
		public static final String Slip_Type_MONTH= "SLIP_TIME_MONTH";
	}
	/**
	 * @author caijie
	 * 报警记录类型
	 * 使用方式:HashMap put(Alarm_Tab_Type,...)
	 */
	public static final class AlarmTabType
	{	
		/**
		 * Alarm_Tab_Type是Key.
		 */
		public static final String Alarm_Tab_Type = "Alarm_Tab_Type";
		/**
		 * Alarm_Tab_SOE_LOG事故一览表
		 */
		public static final String Alarm_Tab_SOE_LOG= "Alarm_Tab_SOE_LOG";
		/**
		 * Alarm_Tab_FAIL_LOG故障一览表
		 */
		public static final String Alarm_Tab_FAIL_LOG= "Alarm_Tab_FAIL_LOG";
		/**
		 * Alarm_Tab_DIN_LOG状变一览表
		 */
		public static final String Alarm_Tab_DIN_LOG= "Alarm_Tab_DIN_LOG";
		/**
		 * Alarm_Tab_AUX_LOG辅设启停表
		 */
		public static final String Alarm_Tab_AUX_LOG= "Alarm_Tab_AUX_LOG";
		/**
		 * Alarm_Tab_AIN_LOG越复限一览表
		 */
		public static final String Alarm_Tab_AIN_LOG= "Alarm_Tab_AIN_LOG";
		/**
		 * Alarm_Tab_OPER_LOG操作一览表
		 */
		public static final String Alarm_Tab_OPER_LOG= "Alarm_Tab_OPER_LOG";
		/**
		 * Alarm_Tab_DIAG_LOG自诊断一览表
		 */
		public static final String Alarm_Tab_DIAG_LOG= "Alarm_Tab_DIAG_LOG";
		/**
		 * Alarm_Tab_AGC_LOG AGC操作表
		 */
		public static final String Alarm_Tab_AGC_LOG= "Alarm_Tab_AGC_LOG";
		/**
		 * Alarm_Tab_SEQ_LOG 流程信息表
		 */
		public static final String Alarm_Tab_SEQ_LOG= "Alarm_Tab_SEQ_LOG";
		/**
		 * Alarm_Tab_RELAY_LOG 保护动作信息表
		 */
		public static final String Alarm_Tab_RELAY_LOG= "Alarm_Tab_RELAY_LOG";
		/**
		 * 模拟量相关测点
		 */
		public static final String Alarm_Tab_ALLAIN_LOG= "Alarm_Tab_ALLAIN_LOG";
		/**
		 * 动作相关测点
		 */
		public static final String Alarm_Tab_ALLDIN_LOG= "Alarm_Tab_ALLDIN_LOG";
	
	}
	/**
	 * @author yangning
	 *附加查询条件串
	 *使用方式:HashMap put(ExCondition,...)value 是List<ExCond>
	 */
	public static final class ExCondition
	{	
		/**
		 * ExCondition_key是Key.
		 */
		public static final String ExCondition_key = "ExCondition_key";
	}
	
	
	 /**
	 * @author yangning
	 *附加查询条件串之间的组合关系
	 *自定义查询条件组的组合关系，表示相邻2个条件的组合关系，如exconds中的exconds[0]和exconds[1]采用OR，则linkstrs[0]="OR"
	 *使用方式:HashMap put(ExCondLinkStr,...)value 是String[]
	 */
	public static final class ExCondLinkStr
	{	
		/**
		 * ExCondition_key是Key.
		 */
		public static final String ExCondlinkstr_key = "ExCondlinkstr_key";
	}
	
	/**获取静态特性参数时生效
	 * @author yangning
	 *
	 */
	public static final class StaticValue
	{	
		/**
		 * StaticValue_key是Key.
		 */
		public static final String StaticValue_key= "StaticValue_key";
	}
	
	//获取静态属性时生效
	
}
