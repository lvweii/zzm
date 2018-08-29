
package com.zzm.hd.param;



/**图形界面调用业务逻辑层参数类说明
 * @author terry
 */
public final class FetchParam    implements Cloneable, java.io.Serializable{
	
	private static final long serialVersionUID = 3148452478511880652L;

	public FetchParam(String apptype) {
		this.apptype = apptype;
	}
	/**传入时间条件和时间的类
	 * @author terry
	 *
	 */
	//应用类型说明  允许输入 Param.AppType.APP_Type_NC|Param.AppType.APP_Type_WDS|Param.AppType.APP_Type_PM|Param.AppType.APP_Type_MODEL|Param.AppType.APP_Type_QX其中之一)
	public String apptype;
	//计算类型说明  允许输入 Param.CalcType.CALC_RT|Param.CalcType.CALC_REAL|Param.CalcType.CALC_SUM|Param.CalcType.CALC_AVG|Param.CalcType.CALC_MAX|Param.CalcType.CALC_MIN其中之一)
	public String calctype;
	//附加Int标志说明，当函数内有需要特别要求时传入 ，具体视函数约定
	public Integer ex_int;
	//附加Int标志说明，流域图调用结束时间等于约束条件
	public Integer ex_interval_int;
	//滑动窗口类型  允许输入 Param.SlipType.Slip_Type_Hour|Param.SlipType.Slip_Type_Ts|Param.SlipType.Slip_Type_Day其中之一
	public String sliptype;
	//滑动窗口步长
	public Integer slipwin;
	//滑动寻找步长,与窗口步长配对使用
	public Integer slipstep;
	//自定义SQL传入 
	public String sqlstr;
	//数据稀疏个数
	public Integer filter;
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
	//Param.RunDataType.RUN_SERAIL 获取闸门或机组开度序列功能
	//Param.RunDataType.RUN_COMTRADE_FILE 获取故障录波列表
	//Param.RunDataType.RUN_COMTRADE_POINT 获取故障录波文件测点
	//Param.RunDataType.RUN_COMTRADE_CURVE 获取故障录波曲线
	public String rundatatype;
	//值类型说明 可以是静态属性值和动态属性值
	//允许输入 Param.ValType.Special_V|Param.ValType.Special_AVGV|Param.ValType.Special_MAXV|Param.ValType.Special_MINV|其中之一
	public String valtype;
	//获取静态属性时生效
	public String staticvalue;
	//时间条件
	public TimeCond timeCond;
	//需要传入的查数据的Long站号数组
	public Long[] idarrayLongs;
	//需要传入的查数据的String站号数组
	public String[] idarrayStrings;	
	//需要传入的查数据的文件名数组
	public String[] FileNameStrings;	
	//自定义查询条件组
	public ExCond[] exconds;	
	//自定义查询条件组的组合关系，表示相邻2个条件的组合关系，如exconds中的exconds[0]和exconds[1]采用OR，则linkstrs[0]="OR"
	public String[] linkstrs;	
	//SQL的附加替换串
	public String str0;
	public String str1;
	public String str2;
	//排序标志，不设置或0是升序，1降序
	public Integer orderflag;
	@Override
	public Object clone()
	{
		FetchParam newFetchParam = null;
		try {
			newFetchParam = (FetchParam)super.clone();
		} catch (Exception e) {
		}
		return newFetchParam;
	}
	
	

}
