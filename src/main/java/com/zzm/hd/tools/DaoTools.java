package com.zzm.hd.tools;

import com.zzm.hd.dto.Chardata;
import com.zzm.hd.param.DeleteParam;
import com.zzm.hd.param.ExCond;
import com.zzm.hd.param.FetchParam;
import com.zzm.hd.param.Param;
import com.zzm.hd.param.TimeCond;
import com.zzm.hd.param.WebGenSqlCond;
import com.zzm.hd.util.CommonTool;
import com.zzm.hd.util.ParamString;
import java.io.Serializable;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.apache.log4j.Logger;

public class DaoTools implements Serializable {

	/**
	 * 将calendar时间毫秒圆整成0
	 * 
	 * @param ca
	 * @return
	 */
	public static Calendar setMillsec0(Calendar ca) {
		ca.set(Calendar.MILLISECOND, 0);
		return ca;
	}

	/**
	 * 分析时间变量形成属性名、比较条件、值的对象的列表
	 * 
	 * @param Inmap
	 *            传入的条件参数
	 * @return 属性名、比较条件、值的对象的列表
	 */
	public static List<Object> parseTimetoObject(HashMap<String, Object> Inmap,
			String idname) {
		ArrayList<Object> timeobj = new ArrayList<Object>();
		Timestamp btime, etime;
		btime = etime = null;
		if ((Calendar) Inmap.get(Param.TimeType.EQ_BTIME) != null) {
			Calendar ca = (Calendar) Inmap.get(Param.TimeType.EQ_BTIME);
			ca = setMillsec0(ca);
			Inmap.put(Param.TimeType.EQ_BTIME, ca);
			btime = new Timestamp(
					((Calendar) Inmap.get(Param.TimeType.EQ_BTIME)).getTime()
							.getTime());

			if (btime != null) {
				timeobj.add(idname);
				timeobj.add("=");
				timeobj.add(btime);

			}
		}
		if (btime == null
				&& ((Calendar) Inmap.get(Param.TimeType.GE_BTIME) != null)) {
			Calendar ca = (Calendar) Inmap.get(Param.TimeType.GE_BTIME);
			ca = setMillsec0(ca);
			Inmap.put(Param.TimeType.GE_BTIME, ca);
			btime = new Timestamp(
					((Calendar) Inmap.get(Param.TimeType.GE_BTIME)).getTime()
							.getTime());
			if (btime != null) {
				timeobj.add(idname);
				timeobj.add(">=");
				timeobj.add(btime);
			}
		}
		if (btime == null
				&& ((Calendar) Inmap.get(Param.TimeType.GT_BTIME) != null)) {
			Calendar ca = (Calendar) Inmap.get(Param.TimeType.GT_BTIME);
			ca = setMillsec0(ca);
			Inmap.put(Param.TimeType.GT_BTIME, ca);
			btime = new Timestamp(
					((Calendar) Inmap.get(Param.TimeType.GT_BTIME)).getTime()
							.getTime());
			if (btime != null) {
				timeobj.add(idname);
				timeobj.add(">");
				timeobj.add(btime);
			}
		}
		if (btime == null
				&& ((Calendar) Inmap.get(Param.TimeType.LE_BTIME) != null)) {
			Calendar ca = (Calendar) Inmap.get(Param.TimeType.LE_BTIME);
			ca = setMillsec0(ca);
			Inmap.put(Param.TimeType.LE_BTIME, ca);
			btime = new Timestamp(
					((Calendar) Inmap.get(Param.TimeType.LE_BTIME)).getTime()
							.getTime());
			if (btime != null) {
				timeobj.add(idname);
				timeobj.add("<=");
				timeobj.add(btime);
			}
		}
		if (btime == null
				&& ((Calendar) Inmap.get(Param.TimeType.LT_BTIME) != null)) {
			Calendar ca = (Calendar) Inmap.get(Param.TimeType.LT_BTIME);
			ca = setMillsec0(ca);
			Inmap.put(Param.TimeType.LT_BTIME, ca);
			btime = new Timestamp(
					((Calendar) Inmap.get(Param.TimeType.LT_BTIME)).getTime()
							.getTime());
			if (btime != null) {
				timeobj.add(idname);
				timeobj.add("<");
				timeobj.add(btime);
			}
		}
		if ((Calendar) Inmap.get(Param.TimeType.EQ_ETIME) != null) {
			Calendar ca = (Calendar) Inmap.get(Param.TimeType.EQ_ETIME);
			ca = setMillsec0(ca);
			Inmap.put(Param.TimeType.EQ_ETIME, ca);
			etime = new Timestamp(
					((Calendar) Inmap.get(Param.TimeType.EQ_ETIME)).getTime()
							.getTime());

			if (etime != null) {
				timeobj.add(idname);
				timeobj.add("=");
				timeobj.add(etime);
			}
		}
		if (etime == null
				&& ((Calendar) Inmap.get(Param.TimeType.GE_ETIME) != null)) {
			Calendar ca = (Calendar) Inmap.get(Param.TimeType.GE_ETIME);
			ca = setMillsec0(ca);
			Inmap.put(Param.TimeType.GE_ETIME, ca);
			etime = new Timestamp(
					((Calendar) Inmap.get(Param.TimeType.GE_ETIME)).getTime()
							.getTime());
			if (etime != null) {
				timeobj.add(idname);
				timeobj.add(">=");
				timeobj.add(etime);
			}
		}
		if (etime == null
				&& ((Calendar) Inmap.get(Param.TimeType.GT_ETIME) != null)) {
			Calendar ca = (Calendar) Inmap.get(Param.TimeType.GT_ETIME);
			ca = setMillsec0(ca);
			Inmap.put(Param.TimeType.GT_ETIME, ca);
			etime = new Timestamp(
					((Calendar) Inmap.get(Param.TimeType.GT_ETIME)).getTime()
							.getTime());
			if (etime != null) {
				timeobj.add(idname);
				timeobj.add(">");
				timeobj.add(etime);
			}
		}
		if (etime == null
				&& ((Calendar) Inmap.get(Param.TimeType.LE_ETIME) != null)) {
			Calendar ca = (Calendar) Inmap.get(Param.TimeType.LE_ETIME);
			ca = setMillsec0(ca);
			Inmap.put(Param.TimeType.LE_ETIME, ca);
			etime = new Timestamp(
					((Calendar) Inmap.get(Param.TimeType.LE_ETIME)).getTime()
							.getTime());
			if (etime != null) {
				timeobj.add(idname);
				timeobj.add("<=");
				timeobj.add(etime);
			}
		}
		if (etime == null
				&& ((Calendar) Inmap.get(Param.TimeType.LT_ETIME) != null)) {
			Calendar ca = (Calendar) Inmap.get(Param.TimeType.LT_ETIME);
			ca = setMillsec0(ca);
			Inmap.put(Param.TimeType.LT_ETIME, ca);
			etime = new Timestamp(
					((Calendar) Inmap.get(Param.TimeType.LT_ETIME)).getTime()
							.getTime());
			if (etime != null) {
				timeobj.add(idname);
				timeobj.add("<");
				timeobj.add(etime);
			}
		}
		if (btime == null && etime == null)
			return null;
		// 如果结束时间未设置，则缺省给当前系统时间，
		if (etime == null) {
			Calendar ca = Calendar.getInstance();
			ca = setMillsec0(ca);
			Inmap.put(Param.TimeType.EQ_BTIME, ca);
			etime = new Timestamp(Calendar.getInstance().getTime().getTime());
			timeobj.add(idname);
			timeobj.add("<=");
			timeobj.add(etime);
		}
		return timeobj;

	}

	/**
	 * @param idname
	 *            需要替换的字段名
	 * @param value
	 *            需要替换的字段值
	 * @return
	 */
	public static List<Object> parseValuetoObject(String idname, Object value) {
		ArrayList<Object> idobj = new ArrayList<Object>();
		idobj.add(idname);
		idobj.add("=");
		idobj.add(value);
		return idobj;
	}

	/**
	 * 分析站号参数形成属性名、比较条件、值的对象的列表
	 * 
	 * @param Inmap
	 *            传入的条件参数
	 * @return 属性名、比较条件、值的对象的列表
	 */
	public static List<Object> parseIdtoObject(HashMap<String, Object> Inmap,
			String idname) {
		ArrayList<Object> idobj = new ArrayList<Object>();

		Object[] Idarray;
		Idarray = (Object[]) Inmap.get(Param.IdType.LONG_IDARRAY);

		Object[] strIdarray;
		strIdarray = (Object[]) Inmap.get(Param.IdType.STR_IDARRAY);

		if (Idarray != null) {
			/*
			 * if(Inmap.get(Param.AppType.App_Type_Key).equals(Param.AppType.
			 * APP_Type_WDS
			 * )&&!Inmap.get(Param.RunDataType.Rundata_Type_Key).equals
			 * (Param.RunDataType.RUN_RTREAL)) idobj.add("id.senid"); else
			 * if(Inmap
			 * .get(Param.AppType.App_Type_Key).equals(Param.AppType.APP_Type_NC
			 * )) idobj.add("id.dbk"); else
			 * if(Inmap.get(Param.AppType.App_Type_Key
			 * ).equals(Param.AppType.APP_Type_WDS
			 * )&&Inmap.get(Param.RunDataType.
			 * Rundata_Type_Key).equals(Param.RunDataType.RUN_RTREAL))
			 * idobj.add("id"); else if
			 * (Inmap.get(Param.AppType.App_Type_Key).equals
			 * (Param.AppType.APP_Type_MODEL) ) idobj.add("id");
			 */
			idobj.add(idname);
			idobj.add("in");

			Long[] lidarray = new Long[Idarray.length];

			for (int i = 0; i < Idarray.length; i++) {
				if (Idarray[i] instanceof String) {
					lidarray[i] = Long.parseLong((String) Idarray[i]);
				} else {
					lidarray[i] = (Long) Idarray[i];
				}
			}
			idobj.add(lidarray);
		}

		if (strIdarray != null) {
			/*
			 * if(Inmap.get(Param.AppType.App_Type_Key).equals(Param.AppType.
			 * APP_Type_WDS)) idobj.add("id.senid"); else
			 * if(Inmap.get(Param.AppType
			 * .App_Type_Key).equals(Param.AppType.APP_Type_NC))
			 * idobj.add("id.dbk");
			 */
			idobj.add(idname);
			idobj.add("in");
			String[] lidarraystr = new String[strIdarray.length];
			for (int i = 0; i < strIdarray.length; i++) {
				lidarraystr[i] = (String) strIdarray[i];
			}

			idobj.add(lidarraystr);
		}

		return idobj;
	}

	/**
	 * 分析类型参数形成属性名、比较条件、类型的对象的列表
	 * 
	 * @param Inmap
	 *            传入的条件参数 Param.ValueType.Value_Type_Key
	 * @return 属性名、比较条件、类型的对象的列表
	 */
	public static List<Object> parseTypetoObject(HashMap<String, Object> Inmap,
			String idname) {
		ArrayList<Object> idobj = new ArrayList<Object>();

		Object[] Typearray;
		Typearray = (Object[]) Inmap.get(Param.CalcType.Value_Type_Key);

		if (Typearray != null) {

			idobj.add(idname);
			idobj.add("in");
			Integer[] lidarray = new Integer[Typearray.length];
			for (int i = 0; i < Typearray.length; i++) {
				lidarray[i] = ((Long) Typearray[i]).intValue();
			}
			idobj.add(lidarray);
		}
		return idobj;
	}

	/**
	 * 序列数值判断连续性并返回累积值
	 * 
	 * @param DataList
	 *            需要处理的数据序列
	 * @param nCycle
	 *            循环空间
	 * @param fSequence
	 *            连续性检验
	 * @param fSlope
	 *            分辨率
	 * @return List<Double> values第一个是计算时段内最后一个有效数据的时间毫秒数 第二个是雨量累积值 如果
	 *         values.get(0)==0 说明没算出雨量
	 * 
	 */
	public static List<Double> getAccValue(List<Chardata> DataList,
			float nCycle, float fSequence, float fSlope) {

		List<Double> revalues = new ArrayList<Double>();

		Long times = 0L;
		double fRslt = 0;
		int nRec = DataList.size();
		if (nRec < 2) {
			// times最后一个有效数据的毫秒数
			if (nRec > 0) {
				times = DataList.get(DataList.size() - 1).getTime()
						.getTimeInMillis();
				revalues.add(times.doubleValue());
			} else {
				revalues.add(0.0);
			}

			revalues.add(fRslt);
			return revalues;

		}

		int pRec3 = nRec - 1;
		int pRec1, pRec2;
		while (pRec3 >= 1) {

			double fD, fV;
			pRec1 = pRec3 - 2;
			/*
			 * if(pRec1<0) { pRec1 = 0; }
			 */
			pRec2 = pRec3 - 1;
			fD = DataList.get(pRec3).getV() - DataList.get(pRec2).getV();
			if (fD >= 0)
				fV = fD;
			else
				fV = fD + nCycle * fSlope;

			if (Math.abs(fV) <= fSequence) // 正常连续
			{
				fRslt = fRslt + fV;
				if (times == 0) {
					times = DataList.get(pRec3).getTime().getTimeInMillis();
				}
				pRec3 = pRec3 - 1;
			} else // 非正常连续
			{
				if (pRec1 < 0) // 就剩下最前的两条数据了
					break;
				fD = DataList.get(pRec2).getV() - DataList.get(pRec1).getV(); // 如果跳变计算前面两条数据的差值
				if (fD >= 0)
					fV = fD;
				else
					fV = fD + nCycle * fSlope; // 翻圈
				// 第三个被剔除
				if (Math.abs(fV) <= fSequence) // 第一、第二个数据正常连续：说明 pRec3 对应的是跳变数据
				{
					fRslt = fRslt + fV;
					if (times == 0) {
						times = DataList.get(pRec2).getTime().getTimeInMillis();
					}
					pRec3 = pRec3 - 2;

				} else {
					fD = DataList.get(pRec3).getV()
							- DataList.get(pRec1).getV();
					// 说明第二个被剔除了
					if (fD >= 0)
						fV = fD;
					else
						fV = fD + nCycle * fSlope;
					if (Math.abs(fV) <= fSequence) // 第一个和第三个正常连续，说明第二个为跳变数据
					{
						fRslt = fRslt + fV;
						if (times == 0) {
							times = DataList.get(pRec3).getTime()
									.getTimeInMillis();
						}
						pRec3 = pRec3 - 2;
					} else
						pRec3 = pRec3 - 1;
				}
			}
		}
		// times最后一个有效数据的毫秒数
		revalues.add(times.doubleValue());
		revalues.add(fRslt);
		return revalues;
	}

	/**
	 * 检查Inmap参数不能为空
	 * 
	 * @param log
	 *            日志类
	 * @param Inmap
	 */
	public static void checkInmapNotNull(Logger log,
			HashMap<String, Object> Inmap) {

		if (Inmap == null) {
			log.error("Inmap 为空, 函数无法执行");
			throw new RuntimeException("Inmap 为空, 函数无法执行");
		}
	}

	/**
	 * 判断当前是否时WDS的应用
	 * 
	 * @author caijie
	 * @return
	 * @param log
	 *            日志类
	 * @param Inmap
	 *            (Param.AppType.App_Type_Key : Param.AppType.APP_Type_WDS)
	 * @see {@link#checkParaExist(Logger log, HashMap<String, Object> Inmap,
	 *      String paName)} 检查特定参数是否存在
	 * @see {@link#checkTimeParaExist(Logger log, HashMap<String, Object>
	 *      inmap)} 检查时间参数是否存在
	 * @see {@link#checkWdsApp(Logger log, HashMap<String, Object> Inmap)}
	 *      检查水情应用参数是否存在
	 * @see {@link#checkNcApp(Logger log, HashMap<String, Object> Inmap)}
	 *      检查监控应用参数是否存在
	 */
	public static void checkWdsApp(Logger log, HashMap<String, Object> inmap) {
		// TODO Auto-generated method stub
		checkInmapNotNull(log, inmap);
		String appname = (String) inmap.get(Param.AppType.App_Type_Key);
		if (!appname.equals(Param.AppType.APP_Type_WDS)) {
			log.error("参数传入的调用的在应用无法被处理，参数传入的应用类型为：" + appname);
			throw new RuntimeException("参数传入的调用的在应用无法被处理，参数传入的应用类型为：" + appname);
		}
	}
	
	/**
	 * 判断当前是否是环保的应用
	 * @param log
	 *            日志类
	 * @param Inmap
	 *            (Param.AppType.App_Type_Key : Param.AppType.APP_Type_HB)
	 */
	public static void checkHbApp(Logger log, HashMap<String, Object> inmap) {
		checkInmapNotNull(log, inmap);
		String appname = (String) inmap.get(Param.AppType.App_Type_Key);
		if (!appname.equals(Param.AppType.APP_Type_HB)) {
			log.error("参数传入的调用的在应用无法被处理，参数传入的应用类型为：" + appname);
			throw new RuntimeException("参数传入的调用的在应用无法被处理，参数传入的应用类型为：" + appname);
		}
	}

	/**
	 * 判断当前是否时NC的应用
	 * 
	 * @author caijie
	 * @return
	 * @param log
	 *            日志类
	 * @param Inmap
	 *            (Param.AppType.App_Type_Key : Param.AppType.APP_Type_WDS)
	 * @see {@link#checkParaExist(Logger log, HashMap<String, Object> Inmap,
	 *      String paName)} 检查特定参数是否存在
	 * @see {@link#checkTimeParaExist(Logger log, HashMap<String, Object>
	 *      inmap)} 检查时间参数是否存在
	 * @see {@link#checkWdsApp(Logger log, HashMap<String, Object> Inmap)}
	 *      检查水情应用参数是否存在
	 * @see {@link#checkNcApp(Logger log, HashMap<String, Object> Inmap)}
	 *      检查监控应用参数是否存在
	 */
	public static void checkNcApp(Logger log, HashMap<String, Object> inmap) {
		// TODO Auto-generated method stub
		checkInmapNotNull(log, inmap);
		String appname = (String) inmap.get(Param.AppType.App_Type_Key);
		if (!appname.equals(Param.AppType.APP_Type_NC)) {
			log.error("参数传入的调用的在应用无法被处理，参数传入的应用类型为：" + appname);
			throw new RuntimeException("参数传入的调用的在应用无法被处理，参数传入的应用类型为：" + appname);
		}
	}

	/**
	 * 判断当前是否时DSM的应用
	 * 
	 * @author yangning
	 * @return
	 * @param log
	 *            日志类
	 * @param Inmap
	 *            (Param.AppType.App_Type_Key : Param.AppType.APP_Type_WDS)
	 * @see {@link#checkParaExist(Logger log, HashMap<String, Object> Inmap,
	 *      String paName)} 检查特定参数是否存在
	 * @see {@link#checkTimeParaExist(Logger log, HashMap<String, Object>
	 *      inmap)} 检查时间参数是否存在
	 * @see {@link#checkWdsApp(Logger log, HashMap<String, Object> Inmap)}
	 *      检查水情应用参数是否存在
	 * @see {@link#checkNcApp(Logger log, HashMap<String, Object> Inmap)}
	 *      检查监控应用参数是否存在
	 */
	public static void checkDsmApp(Logger log, HashMap<String, Object> inmap) {
		// TODO Auto-generated method stub
		checkInmapNotNull(log, inmap);
		String appname = (String) inmap.get(Param.AppType.App_Type_Key);
		if (!appname.equals(Param.AppType.APP_Type_PM)) {
			log.error("参数传入的调用的在应用无法被处理，参数传入的应用类型为：" + appname);
			throw new RuntimeException("参数传入的调用的在应用无法被处理，参数传入的应用类型为：" + appname);
		}
	}

	/**
	 * 判断测点是否为监控开关量
	 * 
	 * @author caijie
	 * @return 是否为开关量
	 * @param id
	 *            测点号
	 * 
	 */
	public static boolean checkNcPointDIN(Long id) {
		if (id == null)
			return false;
		Integer type = (int) (id % 100);
		if (type.equals(73))
			return true;
		if (type.equals(74))
			return true;
		return false;
	}

	/**
	 * 判断测点是否为监控数据
	 * 
	 * @author caijie
	 * @return 是否为监控数据
	 * @param id
	 *            测点号
	 * 
	 */
	public static boolean checkNcPoint(Long id) {
		if (id == null)
			return false;
		Integer type = (int) (id / 10000000000000L);
		if (type.intValue() == 71)
			return true;
		return false;
	}

	/**
	 * 判断当前参数HashMap中是否存在相应的参数
	 * 
	 * @author caijie
	 * @return
	 * @param log
	 *            日志类
	 * @param inmap
	 *            (Param.TimeType.EQ_BTIME...)
	 * @param paName
	 *            需要监测的参数字符串(如Param.TimeType.EQ_BTIME)
	 * @see {@link#checkParaExist(Logger,HashMap, String )} 检查特定参数是否存在
	 * @see {@link#checkTimeParaExist(Logger, HashMap)} 检查时间参数是否存在
	 * @see {@link#checkWdsApp(Logger, HashMap)} 检查水情应用参数是否存在
	 * @see {@link#checkNcApp(Logger, HashMap)} 检查监控应用参数是否存在
	 */
	public static void checkParaExist(Logger log,
			HashMap<String, Object> inmap, String paName) {
		checkInmapNotNull(log, inmap);
		String logstr = new String();
		if (paName.equals(Param.AppType.App_Type_Key)) {
			Object obj = inmap.get(Param.AppType.App_Type_Key);
			if (obj == null) {
				logstr = "参数传入的调用在应用无法被处理，应用类型不存在";
				log.error(logstr);
				throw new RuntimeException(logstr);
			}
		} else if (paName.equals(Param.TimeType.EQ_BTIME)) {
			Object obj = inmap.get(Param.TimeType.EQ_BTIME);
			if (obj == null || !(obj instanceof Calendar)) {
				logstr = "参数传入的调用在应用无法被处理，开始时间等于条件参数不存在";
				log.error(logstr);
				throw new RuntimeException(logstr);
			}
		} else if (paName.equals(Param.TimeType.EQ_ETIME)) {
			Object obj = inmap.get(Param.TimeType.EQ_ETIME);
			if (obj == null || !(obj instanceof Calendar)) {
				logstr = "参数传入的调用在应用无法被处理，结束时间等于条件参数不存在";
				log.error(logstr);
				throw new RuntimeException(logstr);
			}
		} else if (paName.equals(Param.TimeType.GE_BTIME)) {
			Object obj = inmap.get(Param.TimeType.GE_BTIME);
			if (obj == null || !(obj instanceof Calendar)) {
				logstr = "参数传入的调用在应用无法被处理，开始时间大于等于条件参数不存在";
				log.error(logstr);
				throw new RuntimeException(logstr);
			}
		} else if (paName.equals(Param.TimeType.GE_ETIME)) {
			Object obj = inmap.get(Param.TimeType.GE_ETIME);
			if (obj == null || !(obj instanceof Calendar)) {
				logstr = "参数传入的调用在应用无法被处理，结束时间大于等于条件参数不存在";
				log.error(logstr);
				throw new RuntimeException(logstr);
			}
		} else if (paName.equals(Param.TimeType.GT_BTIME)) {
			Object obj = inmap.get(Param.TimeType.GT_BTIME);
			if (obj == null || !(obj instanceof Calendar)) {
				logstr = "参数传入的调用在应用无法被处理，开始时间大于条件参数不存在";
				log.error(logstr);
				throw new RuntimeException(logstr);
			}
		} else if (paName.equals(Param.TimeType.GT_ETIME)) {
			Object obj = inmap.get(Param.TimeType.GT_ETIME);
			if (obj == null || !(obj instanceof Calendar)) {
				logstr = "参数传入的调用在应用无法被处理，结束时间大于条件参数不存在";
				log.error(logstr);
				throw new RuntimeException(logstr);
			}
		}

		else if (paName.equals(Param.TimeType.LE_BTIME)) {
			Object obj = inmap.get(Param.TimeType.LE_BTIME);
			if (obj == null || !(obj instanceof Calendar)) {
				logstr = "参数传入的调用在应用无法被处理，开始时间小于等于条件参数不存在";
				log.error(logstr);
				throw new RuntimeException(logstr);
			}
		}

		else if (paName.equals(Param.TimeType.LE_ETIME)) {
			Object obj = inmap.get(Param.TimeType.LE_ETIME);
			if (obj == null || !(obj instanceof Calendar)) {
				logstr = "参数传入的调用在应用无法被处理，结束时间小于等于条件参数不存在";
				log.error(logstr);
				throw new RuntimeException(logstr);
			}
		}

		else if (paName.equals(Param.TimeType.LT_BTIME)) {
			Object obj = inmap.get(Param.TimeType.LT_BTIME);
			if (obj == null || !(obj instanceof Calendar)) {
				logstr = "参数传入的调用在应用无法被处理，开始时间小于条件参数不存在";
				log.error(logstr);
				throw new RuntimeException(logstr);
			}
		}

		else if (paName.equals(Param.TimeType.LT_ETIME)) {
			Object obj = inmap.get(Param.TimeType.LT_ETIME);
			if (obj == null || !(obj instanceof Calendar)) {
				logstr = "参数传入的调用在应用无法被处理，结束时间小于条件参数不存在";
				log.error(logstr);
				throw new RuntimeException(logstr);
			}
		}
		/****************************************/
		else if (paName.equals(Param.IdType.LONG_IDARRAY)) {
			Object obj = inmap.get(Param.IdType.LONG_IDARRAY);
			if (obj == null) {
				logstr = "参数传入的调用在应用无法被处理，站号数组参数不存在";
				log.error(logstr);
				throw new RuntimeException(logstr);
			}
		} else if (paName.equals(Param.IdType.STR_IDARRAY)) {
			Object obj = inmap.get(Param.IdType.STR_IDARRAY);
			if (obj == null) {
				logstr = "参数传入的调用在应用无法被处理，字符数组参数不存在";
				log.error(logstr);
				throw new RuntimeException(logstr);
			}
		}
		/****************************************/
		else if (paName.equals(Param.ExParamType.EX_SLIPWIN)) {
			Object obj = inmap.get(Param.ExParamType.EX_SLIPWIN);
			if (obj == null) {
				logstr = "参数传入的调用在应用无法被处理，滑动窗口参数不存在";
				log.error(logstr);
				throw new RuntimeException(logstr);
			}
		} else if (paName.equals(Param.ExParamType.EX_SLIPSTEP)) {
			Object obj = inmap.get(Param.ExParamType.EX_SLIPSTEP);
			if (obj == null) {
				logstr = "参数传入的调用在应用无法被处理，滑动步长参数不存在";
				log.error(logstr);
				throw new RuntimeException(logstr);
			}
		} else if (paName.equals(Param.ExParamType.EX_SQL)) {
			Object obj = inmap.get(Param.ExParamType.EX_SQL);
			if (obj == null) {
				logstr = "参数传入的调用在应用无法被处理，SQL语句不存在无法执行自定义SQL函数";
				log.error(logstr);
				throw new RuntimeException(logstr);
			}
		} else if (paName.equals(Param.ExParamType.EX_INTERVAL_INT)) {
			Object obj = inmap.get(Param.ExParamType.EX_INTERVAL_INT);
			if (obj == null) {
				logstr = "参数传入的调用在应用无法被处理，间隔参数不存在";
				log.error(logstr);
				throw new RuntimeException(logstr);
			}
		} else if (paName.equals(Param.ExParamType.EX_INT)) {
			Object obj = inmap.get(Param.ExParamType.EX_INT);
			if (obj == null) {
				logstr = "参数传入的调用在应用无法被处理，EX_INT参数不存在";
				log.error(logstr);
				throw new RuntimeException(logstr);
			}
		} else if (paName.equals(Param.RunDataType.Rundata_Type_Key)) {
			Object obj = inmap.get(Param.RunDataType.Rundata_Type_Key);
			if (obj == null) {
				logstr = "参数传入的调用在应用无法被处理，没有确定需要取数据数据表类型";
				log.error(logstr);
				throw new RuntimeException(logstr);
			}
		} else if (paName.equals(Param.ValType.Special_Type_Key)) {
			Object obj = inmap.get(Param.ValType.Special_Type_Key);
			if (obj == null) {
				logstr = "参数传入的调用在应用无法被处理，没有确定需要取值的类型";
				log.error(logstr);
				throw new RuntimeException(logstr);
			}
		} else if (paName.equals(Param.CalcType.Value_Type_Key)) {
			Object obj = inmap.get(Param.CalcType.Value_Type_Key);
			if (obj == null) {
				logstr = "参数传入的调用在应用无法被处理，没有确定需要取值的类型";
				log.error(logstr);
				throw new RuntimeException(logstr);
			}
		} else if (paName.equals(Param.AlarmTabType.Alarm_Tab_Type)) {
			Object obj = inmap.get(Param.AlarmTabType.Alarm_Tab_Type);
			if (obj == null) {
				logstr = "参数传入的调用在应用无法被处理，没有确定需要报警表类型";
				log.error(logstr);
				throw new RuntimeException(logstr);
			}
		} else if (paName.equals(Param.StaticValue.StaticValue_key)) {
			Object obj = inmap.get(Param.StaticValue.StaticValue_key);
			if (obj == null) {
				logstr = "参数传入的调用在应用无法被处理，没有确定需要获取的静态属性值";
				log.error(logstr);
				throw new RuntimeException(logstr);
			}
		} else if (paName.equals(Param.SlipType.Slip_Type_Key)) {
			Object obj = inmap.get(Param.SlipType.Slip_Type_Key);
			if (obj == null) {
				logstr = "参数传入的调用在应用无法被处理，没有确定滑动步长的类型";
				log.error(logstr);
				throw new RuntimeException(logstr);
			}
		}
	}

	/**
	 * 判断当前参数HashMap中是否存在时间参数
	 * 
	 * @param log
	 *            日志类
	 * @param inmap
	 *            (Param.TimeType.EQ_BTIME...)
	 * @author caijie
	 * @return
	 * @see {@link#checkParaExist(Logger log, HashMap<String, Object> Inmap,
	 *      String paName)} 检查特定参数是否存在
	 * @see {@link#checkTimeParaExist(Logger log, HashMap<String, Object>
	 *      inmap)} 检查时间参数是否存在
	 * @see {@link#checkWdsApp(Logger log, HashMap<String, Object> Inmap)}
	 *      检查水情应用参数是否存在
	 * @see {@link#checkNcApp(Logger log, HashMap<String, Object> Inmap)}
	 *      检查监控应用参数是否存在
	 */
	public static void checkTimeParaExist(Logger log,
			HashMap<String, Object> inmap) {
		checkInmapNotNull(log, inmap);
		String logstr = new String();

		Object obj = inmap.get(Param.TimeType.EQ_BTIME);
		if (obj != null && (obj instanceof Calendar))
			return;

		obj = inmap.get(Param.TimeType.EQ_ETIME);
		if (obj != null && (obj instanceof Calendar))
			return;

		obj = inmap.get(Param.TimeType.GE_BTIME);
		if (obj != null && (obj instanceof Calendar))
			return;

		obj = inmap.get(Param.TimeType.GE_ETIME);
		if (obj != null && (obj instanceof Calendar))
			return;

		obj = inmap.get(Param.TimeType.GT_BTIME);
		if (obj != null && (obj instanceof Calendar))
			return;

		obj = inmap.get(Param.TimeType.GT_ETIME);
		if (obj != null && (obj instanceof Calendar))
			return;

		obj = inmap.get(Param.TimeType.LE_BTIME);
		if (obj != null && (obj instanceof Calendar))
			return;

		obj = inmap.get(Param.TimeType.LE_ETIME);
		if (obj != null && (obj instanceof Calendar))
			return;

		obj = inmap.get(Param.TimeType.LT_BTIME);
		if (obj != null && (obj instanceof Calendar))
			return;

		obj = inmap.get(Param.TimeType.LT_ETIME);
		if (obj != null && (obj instanceof Calendar))
			return;

		logstr = "参数传入的调用在应用无法被处理，时间参数不存在";
		log.error(logstr);
		throw new RuntimeException(logstr);
	}

	/**
	 * 去除String右侧空格
	 * 
	 * @param sdata
	 *            原有字符串
	 * @return 去除了右侧空格的String
	 */
	public static String StRightTrim(String sdata) {
		while (sdata.lastIndexOf(" ") == sdata.length() - 1) {
			sdata = sdata.substring(0, sdata.length() - 1);
		}
		return sdata;
	}

	/**
	 * 日期转换 Object 任意类型  To Calendar
	 * @param obj Object
	 * @returnCalendar日期
	 */
	public static Calendar tgObjectToCalender(Object obj) {
		if(obj instanceof Long){
			return tgLongToCalender((Long)obj);
		}else if(obj instanceof Date){
			return tgDateToCalendar((Date)obj);
		}else if(obj instanceof Timestamp){
			return tgTimestampToCalendar((Timestamp)obj);
		}
		
		return null;
	}
	
	/**
	 * 日期转换 Long To Calendar
	 * 
	 * @param Long日期
	 * @return Calendar日期
	 */
	public static Calendar tgLongToCalender(Long time) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(time);
		return calendar;
	}

	/**
	 * 日期转换 Date To Calendar
	 * 
	 * @param date
	 *            date日期
	 * @return Calendar日期
	 */
	public static Calendar tgDateToCalendar(Date date) {
		// TODO Auto-generated method stub
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		return cal;
	}

	/**
	 * 日期转换 Calendar To Date
	 * 
	 * @param cal
	 *            Calendar日期
	 * @return date日期
	 */
	public static Date tgCalendarTodate(Calendar cal) {
		// TODO Auto-generated method stub
		Date date = cal.getTime();
		return date;
	}

	/**
	 * 日期转换 Timestamp To Date
	 * 
	 * @param ti
	 *            Timestamp日期
	 * @return date日期
	 */
	public static Date tgTimestampTodate(Timestamp ti) {
		// TODO Auto-generated method stub
		Date date = new Date(ti.getTime());
		return date;
	}

	/**
	 * 日期转换 Date To Timestamp
	 * 
	 * @param ti
	 *            date日期
	 * @return Timestamp日期
	 */
	public static Timestamp tgDateToTimestamp(Date date) {
		// TODO Auto-generated method stub
		Timestamp ti = new Timestamp(date.getTime());
		return ti;
	}

	/**
	 * 日期转换 Timestamp To Calendar
	 * 
	 * @param ti
	 *            Timestamp日期
	 * @return Calendar日期
	 */
	public static Calendar tgTimestampToCalendar(Timestamp ti) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(ti.getTime());
		return calendar;
	}

	/**
	 * 日期转换 Calendar To Timestamp
	 * 
	 * @param cal
	 *            Calendar日期
	 * @return Timestamp日期
	 */
	public static Timestamp tgDateToTimestamp(Calendar cal) {
		// TODO Auto-generated method stub
		Timestamp ti = new Timestamp(cal.getTime().getTime());
		return ti;
	}

	/**
	 * 日期转换 具体年月日 To Calendar
	 * 
	 * @param year
	 *            年
	 * @param mon
	 *            月
	 * @param day
	 *            日
	 * @param hour
	 *            小时
	 * @param min
	 *            分钟
	 * @param sec
	 *            秒
	 * @return Calendar日期
	 */
	public static Calendar tgIntToCalendar(int year, int mon, int day,
			int hour, int min, int sec) {
		// TODO Auto-generated method stub
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.YEAR, year);
		cal.set(Calendar.MONTH, mon - 1);
		cal.set(Calendar.DATE, day);
		cal.set(Calendar.HOUR_OF_DAY, hour);
		cal.set(Calendar.MINUTE, min);
		cal.set(Calendar.SECOND, sec);
		cal.set(Calendar.MILLISECOND, 0);
		return cal;
	}

	/**
	 * 日期转换 calendar to 字符串
	 * 
	 * @param cal
	 *            calendar日期
	 * @return yyyy-MM-dd HH:mm:ss 字符串
	 */
	public static String tgCalendarToString(Calendar cal) {
		if (cal == null)
			return null;
		// TODO Auto-generated method stub
		SimpleDateFormat bartDateFormat = new SimpleDateFormat(
				"yyyy-MM-dd HH:mm:ss");
		String res = bartDateFormat.format(cal.getTime());
		return res;
	}

	/**
	 * 根据参数修改传入的Calendar参数<br>
	 * 
	 * @author caijie
	 * @param cal
	 *            原有的Calendar参数，如果该参数为null，则返回null
	 * @param year
	 *            年参数，如果为null，则不进行处理，否则将年强制修改为year值;
	 * @param day
	 *            日参数，如果为null，则不进行处理，否则将日强制修改day值;
	 * @param hour
	 *            小时参数，如果为null，则不进行处理，否则将小时强制修改hour值;
	 * @param min
	 *            分钟参数，如果为null，则不进行处理，否则将分钟强制修改min值;
	 * @param sec
	 *            秒参数，如果为null，则不进行处理，否则将秒强制修改sec值;
	 * @return Calendar 最终结果
	 * 
	 */
	public static Calendar tgModifyCalendar(Calendar cal, Integer year,
			Integer mon, Integer day, Integer hour, Integer min, Integer sec) {
		if (cal == null)
			return null;
		Calendar caltmp = (Calendar) cal.clone();
		if (year != null)
			caltmp.set(Calendar.YEAR, year);
		if (mon != null)
			caltmp.set(Calendar.MONTH, mon - 1);
		if (day != null)
			caltmp.set(Calendar.DAY_OF_MONTH, day);
		if (hour != null)
			caltmp.set(Calendar.HOUR_OF_DAY, hour);
		if (min != null)
			caltmp.set(Calendar.MINUTE, min);
		if (sec != null)
			caltmp.set(Calendar.SECOND, sec);
		caltmp.set(Calendar.MILLISECOND, 0);
		return caltmp;
	}

	/**
	 * 根据开始结束时间以及间隔时间，获取满足条件的开始时间序列，返回结果集的最后一个元素应该是最后一个时间段的结束时间，<br>
	 * 如果tB为空或者tE为空则直接返回空数组<br>
	 * 如果结束时间晚于当前时间，则将当前时间作为时间段的结??br> 如果开始时间早于结束时间，返回空数??br>
	 * 
	 * @author caijie
	 * @param tB
	 *            开始时?
	 * @param tE
	 *            结束时间
	 * @param SlipType
	 *            偏移类型：空/Param.SlipType.Slip_Type_Hour/Param.SlipType.
	 *            Slip_Type_Day/Param.SlipType.Slip_Type_MONTH
	 * @return List<Long> 获得的时间段的开始时间，最后一个元素为结束时间
	 * @see {@link#tgGetTimeListBySlip(Calendar tB, Calendar tE, String
	 *      SlipType)}
	 * @see {@link#tgGetTimeListBySlip(Logger log, Calendar tB, Calendar tE,
	 *      String SlipType)} @
	 * 
	 */
	public static List<Long> tgGetTimeListBySlip(Calendar tB, Calendar tE,
			String SlipType) {
		return tgGetTimeListBySlip(null, tB, tE, SlipType);
	}

	/**
	 * 根据开始结束时间以及间隔时间，获取满足条件的开始时间序列，返回结果集的最后一个元素应该是最后一个时间段的结束时间，<br>
	 * 如果tB为空或者tE为空则直接返回空数组<br>
	 * 如果结束时间晚于当前时间，则将当前时间作为时间段的结??br> 如果开始时间早于结束时间，返回空数??br>
	 * 
	 * @author caijie
	 * @param log
	 *            日志
	 * @param tB
	 *            开始时?
	 * @param tE
	 *            结束时间
	 * @param SlipType
	 *            偏移类型：空/Param.SlipType.Slip_Type_Hour/Param.SlipType.
	 *            Slip_Type_Day/Param.SlipType.Slip_Type_MONTH
	 * @return List<Long> 获得的时间段的开始时间，最后一个元素为结束时间
	 * @see {@link#tgGetTimeListBySlip(Calendar tB, Calendar tE, String
	 *      SlipType)}
	 * @see {@link#tgGetTimeListBySlip(Logger log, Calendar tB, Calendar tE,
	 *      String SlipType)}
	 * 
	 */
	public static List<Long> tgGetTimeListBySlip(Logger log, Calendar tB,
			Calendar tE, String SlipType) {
		ArrayList objTimeList = new ArrayList<Long>();
		if (tB == null || tE == null) {
			if (log == null) {
				String logstr = "时间参数为空，程序无法获取间隔时间";
				log.error(logstr);
				throw new RuntimeException(logstr);
			} else {
				return objTimeList;
			}
		}
		Long lTmpLong = tB.getTimeInMillis();
		Long lTimeEnd = tE.getTimeInMillis();
		long lTimeNow = Calendar.getInstance().getTimeInMillis();

		// 如果结束时间晚于当前时间，则应补全当前时间段后结束循?
		if (lTimeEnd > lTimeNow)
			lTimeEnd = lTimeNow;
		// 如果开始时间晚于结束时间，返回空数?
		if (lTmpLong > lTimeEnd)
			return objTimeList;

		if (SlipType != null) {
			while (lTimeEnd >= lTmpLong) {
				objTimeList.add(lTmpLong);
				if (SlipType.equals(Param.SlipType.Slip_Type_Hour)) {
					lTmpLong += 3600000;
				} else if (SlipType.equals(Param.SlipType.Slip_Type_Day)) {
					lTmpLong += (3600000 * 24);
				} else if (SlipType.equals(Param.SlipType.Slip_Type_MONTH)) {
					Calendar calTmp = DaoTools.tgLongToCalender(lTmpLong);
					int year = calTmp.get(Calendar.YEAR);
					int mon = calTmp.get(Calendar.MONTH);
					if (mon == 11) {
						year += 1;
						mon = 1;
					} else {
						mon += 1;
					}
					calTmp.set(Calendar.YEAR, year);
					calTmp.set(Calendar.MONTH, mon);
					lTmpLong = calTmp.getTimeInMillis();
				} else {
					try {
						throw new RuntimeException("滑动类型错误");
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
				// 如果发现下一个需要做判断的时间点已经超过当前时间,直接退出循?
				if (lTmpLong > lTimeNow) {
					objTimeList.add(lTimeNow);
					break;
				}
			}
		} else {
			objTimeList.add(lTmpLong);
			objTimeList.add(lTimeEnd);
		}
		return objTimeList;
	}

	/**
	 * 合并两个String数组<br>
	 * 
	 * @author caijie
	 * @param a
	 *            String[]
	 * @param b
	 *            String[]
	 * @return String[]
	 * 
	 */
	public static String[] addStringArray(String[] a, String[] b) {
		String[] tmpStrings = new String[a.length + b.length];
		for (int i = 0; i < a.length; i++) {
			tmpStrings[i] = a[i];
		}
		for (int i = 0; i < b.length; i++) {
			tmpStrings[i + a.length] = b[i];
		}
		return tmpStrings;
	}

	/**
	 * 将Object数组转换为Long数组<br>
	 * 
	 * @author caijie
	 * @param a
	 *            Object[]
	 * @return Long[]
	 * 
	 */
	public static Long[] turnObjsToLongs(Object[] a) {
		Long b[] = new Long[a.length];
		for (int i = 0; i < a.length; i++) {
			b[i] = (Long) a[i];
		}
		return b;
	}

	/**
	 * 将Object数组转换为String数组<br>
	 * 
	 * @author caijie
	 * @param a
	 *            Object[]
	 * @return String[]
	 * 
	 */
	public static String[] turnObjsToStrings(Object[] a) {
		String b[] = new String[a.length];
		for (int i = 0; i < a.length; i++) {
			b[i] = (String) a[i];
		}
		return b;
	}

	/**
	 * 获取Param.RunDataType.Rundata_Type_Key对应的数据库表字符串<br>
	 * <li>Param.RunDataType.RUN_HOUR 返回"HOURDB" <li>Param.RunDataType.RUN_DAY
	 * 返回"DAYDB" <li>Param.RunDataType.RUN_DAY10 返回"DAY10DB" <li>
	 * Param.RunDataType.RUN_MONTH 返回"MONTHDB" <li>Param.RunDataType.RUN_YEAR
	 * 返回"YEARDB" <li>其他 返回"HOURDB"
	 * 
	 * @author caijie
	 * @param inmap
	 *            条件
	 * @return String
	 * @see {@link #paraTurnCalcTypeToString(HashMap)}
	 *      获取Param.CalcType.Value_Type_Key对应的统计操作字符串字符串
	 * @see {@link #paraTurnValTypeToString(HashMap)}
	 *      获取Param.ValType.Special_Type_Key对应的数据库字段字符串
	 * @see {@link #paraTurnRunDataTypeToString(HashMap)}
	 *      获取Param.RunDataType.Rundata_Type_Key对应的数据库表字符串
	 * 
	 */
	public static String paraTurnRunDataTypeToString(
			HashMap<String, Object> inmap) {
		String runDataTypeString = (String) inmap
				.get(Param.RunDataType.Rundata_Type_Key);
		if (runDataTypeString.equals(Param.RunDataType.RUN_HOUR)) {
			return "HOURDB";
		} else if (runDataTypeString.equals(Param.RunDataType.RUN_DAY)) {
			return "DAYDB";
		} else if (runDataTypeString.equals(Param.RunDataType.RUN_DAY10)) {
			return "DAY10DB";
		} else if (runDataTypeString.equals(Param.RunDataType.RUN_MONTH)) {
			return "MONTHDB";
		} else if (runDataTypeString.equals(Param.RunDataType.RUN_MONTH3)) {
			return "MONTH3DB";
		} else if (runDataTypeString.equals(Param.RunDataType.RUN_YEAR)) {
			return "YEARDB";
		}
		return "HOURDB";
	}

	/**
	 * 获取Param.ValType.Special_Type_Key对应的数据库字段字符串<br>
	 * <li>Param.ValType.Special_AVGV 返回"AVGV","AVGS","TIME" <li>
	 * Param.ValType.Special_MAXV 返回"MAXV","MAXS","MAXT" <li>
	 * Param.ValType.Special_MINV 返回"MINV","MINS","MINT" <li>
	 * Param.ValType.Special_V 返回"V","S","TIME" <li>其他 返回"V","S","TIME"
	 * 
	 * @author caijie
	 * @param inmap
	 *            条件
	 * @return String[] 返回对应字符串
	 * @see {@link #paraTurnCalcTypeToString(HashMap)}
	 *      获取Param.CalcType.Value_Type_Key对应的统计操作字符串字符串
	 * @see {@link #paraTurnValTypeToString(HashMap)}
	 *      获取Param.ValType.Special_Type_Key对应的数据库字段字符串
	 * @see {@link #paraTurnRunDataTypeToString(HashMap)}
	 *      获取Param.RunDataType.Rundata_Type_Key对应的数据库表字符串
	 * 
	 */
	public static String[] paraTurnValTypeToString(HashMap<String, Object> inmap) {
		String valTypeString = (String) inmap
				.get(Param.ValType.Special_Type_Key);
		if (valTypeString.equals(Param.ValType.Special_AVGV)) {
			return new String[] { "AVGV", "AVGS", "TIME" };
		} else if (valTypeString.equals(Param.ValType.Special_MAXV)) {
			return new String[] { "MAXV", "MAXS", "MAXT" };
		} else if (valTypeString.equals(Param.ValType.Special_MINV)) {
			return new String[] { "MINV", "MINS", "MINT" };
		} else if (valTypeString.equals(Param.ValType.Special_V)) {
			return new String[] { "V", "S", "TIME" };
		}
		return new String[] { "V", "S", "TIME" };
	}

	/**
	 * 获取Param.CalcType.Value_Type_Key对应的统计操作字符串字符串<br>
	 * <li>Param.CalcType.CALC_SUM 对应"SUM" <li>Param.CalcType.CALC_AVG 对应"AVG"
	 * <li>Param.CalcType.CALC_MAX 对应"MAX" <li>Param.CalcType.CALC_MIN 对应"MIN"
	 * <li>其他 对应 "SUM"
	 * 
	 * @author caijie
	 * @param inmap
	 *            条件
	 * @return String 返回对应字符串
	 * @see {@link #paraGetTimeTypeCalendar(HashMap)}
	 *      获取Param.TimeType对应的开始和结束时间参数
	 * @see {@link #paraSetTimeTypeCalendar(HashMap, Calendar[],String[])}
	 *      不修改时间参数对应的Param.TimeType，将特定的时间参数修改入哈希条件
	 * @see {@link #paraGetTimeTypeOpString(HashMap)}
	 *      获取Param.TimeType对应的开始和结束时间参数操作字符串
	 * @see {@link #paraClearTimeTypeCalendar(HashMap)}清空已有Param.TimeType参数
	 * 
	 */
	public static String paraTurnCalcTypeToString(HashMap<String, Object> inmap) {
		String opTypeString = (String) inmap.get(Param.CalcType.Value_Type_Key);
		if (opTypeString.equals(Param.CalcType.CALC_SUM)) {
			return "SUM";
		} else if (opTypeString.equals(Param.CalcType.CALC_AVG)) {
			return "AVG";
		} else if (opTypeString.equals(Param.CalcType.CALC_MAX)) {
			return "MAX";
		} else if (opTypeString.equals(Param.CalcType.CALC_MIN)) {
			return "MIN";
		}
		return "SUM";
	}

	/**
	 * 获取Param.TimeType对应的开始和结束时间参数<br>
	 * <li>Param.TimeType.EQ_BTIME 返回结果于Calendar[0]中 <li>Param.TimeType.GE_BTIME
	 * 返回结果于Calendar[0]中 <li>Param.TimeType.GT_BTIME 返回结果于Calendar[0]中 <li>
	 * Param.TimeType.LE_BTIME 返回结果于Calendar[0]中 <li>Param.TimeType.LT_BTIME
	 * 返回结果于Calendar[0]中 <li>其他 返回null于Calendar[0]中 <li>Param.TimeType.EQ_ETIME
	 * 返回结果于Calendar[1]中 <li>Param.TimeType.LE_ETIME 返回结果于Calendar[1]中 <li>
	 * Param.TimeType.LT_ETIME 返回结果于Calendar[1]中 <li>Param.TimeType.GE_ETIME
	 * 返回结果于Calendar[1]中 <li>Param.TimeType.GT_ETIME 返回结果于Calendar[1]中 <li>其他
	 * 返回null于Calendar[1]中
	 * 
	 * @author caijie
	 * @param inmap
	 *            条件
	 * @return Calendar[] 返回时间参数的具体数值
	 * @see {@link #paraGetTimeTypeCalendar(HashMap)}
	 *      获取Param.TimeType对应的开始和结束时间参数
	 * @see {@link #paraSetTimeTypeCalendar(HashMap, Calendar[],String[])}
	 *      不修改时间参数对应的Param.TimeType，将特定的时间参数修改入哈希条件
	 * @see {@link #paraGetTimeTypeOpString(HashMap)}
	 *      获取Param.TimeType对应的开始和结束时间参数操作字符串
	 * @see {@link #paraClearTimeTypeCalendar(HashMap)}清空已有Param.TimeType参数
	 * 
	 */
	public static Calendar[] paraGetTimeTypeCalendar(
			HashMap<String, Object> inmap) {
		Calendar[] cals = new Calendar[2];
		String timeTypeString = (String) inmap
				.get(Param.ValType.Special_Type_Key);
		Calendar tbeq = (Calendar) inmap.get(Param.TimeType.EQ_BTIME);
		Calendar tbge = (Calendar) inmap.get(Param.TimeType.GE_BTIME);
		Calendar tbgt = (Calendar) inmap.get(Param.TimeType.GT_BTIME);
		Calendar tble = (Calendar) inmap.get(Param.TimeType.LE_BTIME);
		Calendar tblt = (Calendar) inmap.get(Param.TimeType.LT_BTIME);
		if (tbeq != null)
			cals[0] = tbeq;
		else if (tbge != null)
			cals[0] = tbge;
		else if (tbgt != null)
			cals[0] = tbgt;
		else if (tble != null)
			cals[0] = tble;
		else if (tblt != null)
			cals[0] = tblt;
		else
			cals[0] = null;

		Calendar teeq = (Calendar) inmap.get(Param.TimeType.EQ_ETIME);
		Calendar tele = (Calendar) inmap.get(Param.TimeType.LE_ETIME);
		Calendar telt = (Calendar) inmap.get(Param.TimeType.LT_ETIME);
		Calendar tege = (Calendar) inmap.get(Param.TimeType.GE_ETIME);
		Calendar tegt = (Calendar) inmap.get(Param.TimeType.GT_ETIME);

		if (teeq != null)
			cals[1] = tbeq;
		else if (tele != null)
			cals[1] = tele;
		else if (telt != null)
			cals[1] = telt;
		else if (tege != null)
			cals[1] = tege;
		else if (tegt != null)
			cals[1] = tegt;
		else
			cals[1] = null;
		return cals;
	}

/**
	 * 获取Param.TimeType对应的开始和结束时间参数操作字符串<br>
	 * <li>Param.TimeType.EQ_BTIME 返回结果于Calendar[0]中，结果为"="
	 * <li>Param.TimeType.GE_BTIME 返回结果于Calendar[0]中，结果为">="
	 * <li>Param.TimeType.GT_BTIME 返回结果于Calendar[0]中，结果为">"
	 * <li>Param.TimeType.LE_BTIME 返回结果于Calendar[0]中，结果为"<="
	 * <li>Param.TimeType.LT_BTIME 返回结果于Calendar[0]中，结果为"<"
	 * <li>其他 返回null于Calendar[0]中
	 * <li>Param.TimeType.EQ_ETIME 返回结果于Calendar[1]中，结果为"="
	 * <li>Param.TimeType.LE_ETIME 返回结果于Calendar[1]中，结果为"<="
	 * <li>Param.TimeType.LT_ETIME 返回结果于Calendar[1]中，结果为"<"
	 * <li>Param.TimeType.GE_ETIME 返回结果于Calendar[1]中，结果为">="
	 * <li>Param.TimeType.GT_ETIME 返回结果于Calendar[1]中，结果为">"
	 * <li>其他 返回null于Calendar[1]中
	 * 
	 * @author caijie
	 * @param inmap
	 *            条件
	 * @return String[] 返回具体操作字符串
	 * @see {@link #paraGetTimeTypeCalendar(HashMap)}
	 *      获取Param.TimeType对应的开始和结束时间参数
	 * @see {@link #paraSetTimeTypeCalendar(HashMap, Calendar[],String[])}
	 *      不修改时间参数对应的Param.TimeType，将特定的时间参数修改入哈希条件
	 * @see {@link #paraGetTimeTypeOpString(HashMap)}
	 *      获取Param.TimeType对应的开始和结束时间参数操作字符串
	 * @see {@link #paraClearTimeTypeCalendar(HashMap)}清空已有Param.TimeType参数
	 * 
	 */
	public static String[] paraGetTimeTypeOpString(HashMap<String, Object> inmap) {
		String[] opstr = new String[2];
		String timeTypeString = (String) inmap
				.get(Param.ValType.Special_Type_Key);
		Calendar tbeq = (Calendar) inmap.get(Param.TimeType.EQ_BTIME);
		Calendar tbge = (Calendar) inmap.get(Param.TimeType.GE_BTIME);
		Calendar tbgt = (Calendar) inmap.get(Param.TimeType.GT_BTIME);
		Calendar tble = (Calendar) inmap.get(Param.TimeType.LE_BTIME);
		Calendar tblt = (Calendar) inmap.get(Param.TimeType.LT_BTIME);
		if (tbeq != null)
			opstr[0] = "=";
		else if (tbge != null)
			opstr[0] = ">=";
		else if (tbgt != null)
			opstr[0] = ">";
		else if (tble != null)
			opstr[0] = "<=";
		else if (tblt != null)
			opstr[0] = "<";
		else
			opstr[0] = null;

		Calendar teeq = (Calendar) inmap.get(Param.TimeType.EQ_ETIME);
		Calendar tele = (Calendar) inmap.get(Param.TimeType.LE_ETIME);
		Calendar telt = (Calendar) inmap.get(Param.TimeType.LT_ETIME);
		Calendar tege = (Calendar) inmap.get(Param.TimeType.GE_ETIME);
		Calendar tegt = (Calendar) inmap.get(Param.TimeType.GT_ETIME);

		if (teeq != null)
			opstr[1] = "=";
		else if (tele != null)
			opstr[1] = "<=";
		else if (telt != null)
			opstr[1] = "<";
		else if (tege != null)
			opstr[1] = ">=";
		else if (tegt != null)
			opstr[1] = ">";
		else
			opstr[1] = null;
		return opstr;
	}

	/**
	 * 清空已有Param.TimeType参数，将特定的时间参数修改入哈希条件<br>
	 * <li>如果存在Param.TimeType.EQ_BTIME 且Calendar[0]不为空，且String[0]有效，则参数写入该条件 <li>
	 * 如果存在Param.TimeType.GE_BTIME 且Calendar[0]不为空，且String[0]有效，则参数写入该条件 <li>
	 * 如果存在Param.TimeType.GT_BTIME 且Calendar[0]不为空，且String[0]有效，则参数写入该条件 <li>
	 * 如果存在Param.TimeType.LE_BTIME 且Calendar[0]不为空，且String[0]有效，则参数写入该条件 <li>
	 * 如果存在Param.TimeType.LT_BTIME 且Calendar[0]不为空，且String[0]有效，则参数写入该条件
	 * 
	 * <li>如果存在Param.TimeType.EQ_ETIME 且Calendar[1]不为空，且String[1]有效，则参数写入该条件 <li>
	 * 如果存在Param.TimeType.LE_ETIME 且Calendar[1]不为空，且String[1]有效，则参数写入该条件 <li>
	 * 如果存在Param.TimeType.LT_ETIME 且Calendar[1]不为空，且String[1]有效，则参数写入该条件 <li>
	 * 如果存在Param.TimeType.GE_ETIME 且Calendar[1]不为空，且String[1]有效，则参数写入该条件 <li>
	 * 如果存在Param.TimeType.GT_ETIME 且Calendar[1]不为空，且String[1]有效，则参数写入该条件
	 * 
	 * <li>如果Calendar不为空，或者数组个数不是2个，则不修改参数条件
	 * 
	 * @author caijie
	 * @param inmap
	 *            条件
	 * @param cals
	 *            时间条件 <li>cals[0]如果不为空，对应开始时间参数 <li>cals[1]如果不为空，对应结束时间参数
	 * @param opStr
	 *            操作参数字符串 <li>opStr[0]如果不为空，对应开始时间的操作参数(">" "<" "=" ">=" "<=")
	 *            <li>opStr[1]如果不为空，对应结束时间的操作参数(">" "<" "=" ">=" "<=")
	 * @see {@link #paraGetTimeTypeCalendar(HashMap)}
	 *      获取Param.TimeType对应的开始和结束时间参数
	 * @see {@link #paraSetTimeTypeCalendar(HashMap, Calendar[],String[])}
	 *      不修改时间参数对应的Param.TimeType，将特定的时间参数修改入哈希条件
	 * @see {@link #paraGetTimeTypeOpString(HashMap)}
	 *      获取Param.TimeType对应的开始和结束时间参数操作字符串
	 * @see {@link #paraClearTimeTypeCalendar(HashMap)}清空已有Param.TimeType参数
	 * 
	 */
	public static void paraSetTimeTypeCalendar(HashMap<String, Object> inmap,
			Calendar[] cals, String[] opStr) {
		if (cals == null || opStr == null)
			return;
		if (cals.length != 2 || opStr.length != 2)
			return;
		String timeTypeString = (String) inmap
				.get(Param.ValType.Special_Type_Key);
		paraClearTimeTypeCalendar(inmap);

		if (cals[0] != null && opStr[0] != null) {
			if (opStr[0].equals("="))
				inmap.put(Param.TimeType.EQ_BTIME, cals[0]);
			else if (opStr[0].equals(">="))
				inmap.put(Param.TimeType.GE_BTIME, cals[0]);
			else if (opStr[0].equals(">"))
				inmap.put(Param.TimeType.GT_BTIME, cals[0]);
			else if (opStr[0].equals("<="))
				inmap.put(Param.TimeType.LE_BTIME, cals[0]);
			else if (opStr[0].equals("<"))
				inmap.put(Param.TimeType.LT_BTIME, cals[0]);
		}

		if (cals[1] != null && opStr[1] != null) {
			if (opStr[1].equals("="))
				inmap.put(Param.TimeType.EQ_ETIME, cals[1]);
			else if (opStr[1].equals("<="))
				inmap.put(Param.TimeType.LE_ETIME, cals[1]);
			else if (opStr[1].equals("<"))
				inmap.put(Param.TimeType.LT_ETIME, cals[1]);
			else if (opStr[1].equals(">="))
				inmap.put(Param.TimeType.GE_ETIME, cals[1]);
			else if (opStr[1].equals(">"))
				inmap.put(Param.TimeType.GT_ETIME, cals[1]);
		}
	}

	/**
	 * 清空已有Param.TimeType参数<br>
	 * <li>清空Param.TimeType.EQ_BTIME <li>清空Param.TimeType.GE_BTIME <li>
	 * 清空Param.TimeType.GT_BTIME <li>清空Param.TimeType.LE_BTIME <li>
	 * 清空Param.TimeType.LT_BTIME
	 * 
	 * <li>清空Param.TimeType.EQ_ETIME <li>清空Param.TimeType.LE_ETIME <li>
	 * 清空Param.TimeType.LT_ETIME <li>清空Param.TimeType.GE_ETIME <li>
	 * 清空Param.TimeType.GT_ETIME
	 * 
	 * @author caijie
	 * @param inmap
	 *            条件
	 * @see {@link #paraGetTimeTypeCalendar(HashMap)}
	 *      获取Param.TimeType对应的开始和结束时间参数
	 * @see {@link #paraSetTimeTypeCalendar(HashMap, Calendar[],String[])}
	 *      不修改时间参数对应的Param.TimeType，将特定的时间参数修改入哈希条件
	 * @see {@link #paraGetTimeTypeOpString(HashMap)}
	 *      获取Param.TimeType对应的开始和结束时间参数操作字符串
	 * @see {@link #paraClearTimeTypeCalendar(HashMap)}清空已有Param.TimeType参数
	 * 
	 */
	public static void paraClearTimeTypeCalendar(HashMap<String, Object> inmap) {
		String timeTypeString = (String) inmap
				.get(Param.ValType.Special_Type_Key);

		Calendar tbeq = (Calendar) inmap.get(Param.TimeType.EQ_BTIME);
		Calendar tbge = (Calendar) inmap.get(Param.TimeType.GE_BTIME);
		Calendar tbgt = (Calendar) inmap.get(Param.TimeType.GT_BTIME);
		Calendar tble = (Calendar) inmap.get(Param.TimeType.LE_BTIME);
		Calendar tblt = (Calendar) inmap.get(Param.TimeType.LT_BTIME);
		if (tbeq != null)
			inmap.remove(Param.TimeType.EQ_BTIME);
		if (tbge != null)
			inmap.remove(Param.TimeType.GE_BTIME);
		if (tbgt != null)
			inmap.remove(Param.TimeType.GT_BTIME);
		if (tble != null)
			inmap.remove(Param.TimeType.LE_BTIME);
		if (tblt != null)
			inmap.remove(Param.TimeType.LT_BTIME);
		Calendar teeq = (Calendar) inmap.get(Param.TimeType.EQ_ETIME);
		Calendar tele = (Calendar) inmap.get(Param.TimeType.LE_ETIME);
		Calendar telt = (Calendar) inmap.get(Param.TimeType.LT_ETIME);
		Calendar tege = (Calendar) inmap.get(Param.TimeType.GE_ETIME);
		Calendar tegt = (Calendar) inmap.get(Param.TimeType.GT_ETIME);

		if (teeq != null)
			inmap.remove(Param.TimeType.EQ_ETIME);
		if (tele != null)
			inmap.remove(Param.TimeType.LE_ETIME);
		if (telt != null)
			inmap.remove(Param.TimeType.LT_ETIME);
		if (tege != null)
			inmap.remove(Param.TimeType.GE_ETIME);
		if (tegt != null)
			inmap.remove(Param.TimeType.GT_ETIME);
	}

	/**
	 * 从时间参数中获取满足条件的最后一个时间点<br>
	 * 
	 * @author caijie
	 * @param inmap
	 *            条件Param.RunDataType.Rundata_Type_Key
	 * @param inmap
	 *            时间条件
	 * @see {@link #paraGetTimeTypeCalendar(HashMap)}
	 *      获取Param.TimeType对应的开始和结束时间参数
	 * @see {@link #paraSetTimeTypeCalendar(HashMap, Calendar[],String[])}
	 *      不修改时间参数对应的Param.TimeType，将特定的时间参数修改入哈希条件
	 * @see {@link #paraGetTimeTypeOpString(HashMap)}
	 *      获取Param.TimeType对应的开始和结束时间参数操作字符串
	 * @see {@link #paraClearTimeTypeCalendar(HashMap)}清空已有Param.TimeType参数
	 * 
	 */
	public static Calendar paraGetLastTimeFromCond(HashMap<String, Object> inmap) {
		String timeTypeString = (String) inmap
				.get(Param.RunDataType.Rundata_Type_Key);
		if (timeTypeString == null)
			return null;
		if (!timeTypeString.equals(Param.RunDataType.RUN_DAY)
				&& !timeTypeString.equals(Param.RunDataType.RUN_HOUR)
				&& !timeTypeString.equals(Param.RunDataType.RUN_MONTH)
				&& !timeTypeString.equals(Param.RunDataType.RUN_MONTH3)
				&& !timeTypeString.equals(Param.RunDataType.RUN_YEAR))
			return null;

		Calendar cals[] = DaoTools.paraGetTimeTypeCalendar(inmap);
		String timeOpStr[] = DaoTools.paraGetTimeTypeOpString(inmap);
		Calendar tB = cals[0];
		Calendar tE = cals[1];

		long tn = 0;
		long tb = 0;
		// 如果是找一个特定时间点
		if (tB != null && timeOpStr[0].equals("=")) {
			tb = tB.getTimeInMillis();
			if (timeTypeString.equals(Param.RunDataType.RUN_HOUR)) {
				if (tb % 3600000 == 0)
					return tB;
				else
					return null;
			} else if (timeTypeString.equals(Param.RunDataType.RUN_DAY)) {
				if (tB.get(Calendar.SECOND) != 0)
					return null;
				if (tB.get(Calendar.MINUTE) != 0)
					return null;
				if (tB.get(Calendar.HOUR_OF_DAY) != 0)
					return null;
				return tB;
			} else if (timeTypeString.equals(Param.RunDataType.RUN_MONTH)) {
				if (tB.get(Calendar.SECOND) != 0)
					return null;
				if (tB.get(Calendar.MINUTE) != 0)
					return null;
				if (tB.get(Calendar.HOUR_OF_DAY) != 0)
					return null;
				if (tB.get(Calendar.DAY_OF_MONTH) != 1)
					return null;
				return tB;
			} else if (timeTypeString.equals(Param.RunDataType.RUN_MONTH3)) {
				if (tB.get(Calendar.SECOND) != 0)
					return null;
				if (tB.get(Calendar.MINUTE) != 0)
					return null;
				if (tB.get(Calendar.HOUR_OF_DAY) != 0)
					return null;
				if (tB.get(Calendar.DAY_OF_MONTH) != 1)
					return null;
				return tB;
			} else if (timeTypeString.equals(Param.RunDataType.RUN_YEAR)) {
				if (tB.get(Calendar.SECOND) != 0)
					return null;
				if (tB.get(Calendar.MINUTE) != 0)
					return null;
				if (tB.get(Calendar.HOUR_OF_DAY) != 0)
					return null;
				if (tB.get(Calendar.DAY_OF_MONTH) != 1)
					return null;
				if (tB.get(Calendar.MONTH) != 0)
					return null;
				return tB;
			}
		}
		// 如果存在一个时间范围
		if (tE == null) {
			tn = System.currentTimeMillis();
		} else {
			tn = tE.getTimeInMillis();
			if (!timeOpStr[1].equals("<=")) {
				tn = tn - 1;
			}
		}
		if (timeTypeString.equals(Param.RunDataType.RUN_HOUR)) {
			tn = tn - tn % 3600000;
		} else if (timeTypeString.equals(Param.RunDataType.RUN_DAY)) {
			Calendar tmp = DaoTools.tgLongToCalender(tn);
			tmp = DaoTools.tgModifyCalendar(tmp, null, null, null, 0, 0, 0);
			tn = tmp.getTimeInMillis();
		} else if (timeTypeString.equals(Param.RunDataType.RUN_MONTH)) {
			Calendar tmp = DaoTools.tgLongToCalender(tn);
			tmp = DaoTools.tgModifyCalendar(tmp, null, null, 1, 0, 0, 0);
			tn = tmp.getTimeInMillis();
		} else if (timeTypeString.equals(Param.RunDataType.RUN_MONTH3)) {
			Calendar tmp = DaoTools.tgLongToCalender(tn);
			tmp = DaoTools.tgModifyCalendar(tmp, null, null, 1, 0, 0, 0);
			tn = tmp.getTimeInMillis();
		} else if (timeTypeString.equals(Param.RunDataType.RUN_YEAR)) {
			Calendar tmp = DaoTools.tgLongToCalender(tn);
			tmp = DaoTools.tgModifyCalendar(tmp, null, 1, 1, 0, 0, 0);
			tn = tmp.getTimeInMillis();
		}
		if (tB == null) {
			return DaoTools.tgLongToCalender(tn);
		} else {
			tb = tB.getTimeInMillis();
			if (tn > tb) {
				return DaoTools.tgLongToCalender(tn);
			} else if (tn < tb) {
				return null;
			} else {
				if (!timeOpStr[0].equals(">=")) {
					return null;
				} else {
					return DaoTools.tgLongToCalender(tn);
				}
			}
		}
	}

	/**
	 * Hashmap的复制<br>
	 * 
	 * @author caijie
	 * @param inmap
	 *            HashMap<String, Object>条件
	 * @return HashMap<String, Object>
	 * 
	 */
	public static HashMap<String, Object> paraCopyHashmap(
			HashMap<String, Object> inmap) {
		HashMap<String, Object> newInmap = new HashMap<String, Object>();
		Iterator it = inmap.entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry entry = (Map.Entry) it.next();
			String key = (String) entry.getKey();
			if (entry.getValue() instanceof Calendar) {
				Calendar calendar = Calendar.getInstance();
				calendar.setTimeInMillis(((Calendar) entry.getValue())
						.getTimeInMillis());
				newInmap.put(key, calendar);
			} else {
				Object value = entry.getValue();
				newInmap.put(key, value);
			}

		}
		return newInmap;
	}

	/**
	 * 清除Id数组的数组<br>
	 * 
	 * @author caijie
	 * @param Idarray
	 *            Object[] Idarray
	 */
	public static void clearIdarray(Object[] Idarray) {
		for (int i = 0; i < Idarray.length; i++)
			Idarray[i] = null;
	}

	/**
	 * 计算两个时间之间相差的小时
	 * 
	 * @param d1
	 *            Calendar日期
	 * @param d2
	 *            Calendar日期
	 * @return int 相差的小时值
	 */
	public static int diffInHour(Calendar d1, Calendar d2) {
		return (int) ((d1.getTimeInMillis() - d2.getTimeInMillis()) / (1000 * 60 * 60));
	}

	/**
	 * 计算两个时间之间相差的天数
	 * 
	 * @param d1
	 *            Calendar日期
	 * @param d2
	 *            Calendar日期
	 * @return int 相差的天
	 */
	public static int diffInDay(Calendar d1, Calendar d2) {
		return (int) ((d1.getTimeInMillis() - d2.getTimeInMillis()) / (1000 * 60 * 60 * 24));
	}

	/**
	 * 计算两个时间之间相差的旬数
	 * 
	 * @param d1
	 *            Calendar日期
	 * @param d2
	 *            Calendar日期
	 * @return int 相差的旬数
	 */
	public static int diffIn10Day(Calendar d1, Calendar d2) {
		return (int) ((d1.getTimeInMillis() - d2.getTimeInMillis()) / (1000 * 60 * 60 * 24 * 10));
	}

	/**
	 * 计算两个时间之间相差的月
	 * 
	 * @param d1
	 *            Calendar日期
	 * @param d2
	 *            Calendar日期
	 * @return int 相差的月
	 */
	public static int diffInMonth(Calendar d1, Calendar d2) {
		int year1 = d1.get(Calendar.YEAR);
		int mon1 = d1.get(Calendar.MONTH);
		int year2 = d2.get(Calendar.YEAR);
		int mon2 = d2.get(Calendar.MONTH);

		int diff = (year1 - year2) * 12 + mon1 - mon2;
		return diff;
	}

	/**
	 * 计算两个时间之间相差几个5分钟
	 * 
	 * @param d1
	 *            Calendar日期
	 * @param d2
	 *            Calendar日期
	 * @return int 相差几个5分钟
	 */
	public static int diffIn5Min(Calendar d1, Calendar d2) {
		return (int) ((d1.getTimeInMillis() - d2.getTimeInMillis()) / (1000 * 60 * 5));
	}

	/**
	 * 将IDARRAY中的站号生成绑定变量占位符和相应的值
	 * 
	 * @param log
	 *            日志类变量名称
	 * @param IdArray
	 *            Long类型
	 * @param sqlstr
	 *            输入串，被替换成带绑定变量的sql
	 * @param type
	 *            0 Long类型站号绑定，1 String类型站号绑定
	 * @param ar
	 *            绑定的位置和值
	 * @param pos
	 *            该组绑定的开始位置
	 * @param Inmap用新的带绑定变量的串替代传入的sql串
	 * @return 绑定的占位符最后位置
	 */
	public static int bindLongID(Logger log, Object[] IdArray, String sqlstr,
			HashMap<Integer, Object> ar, int pos, HashMap<String, Object> Inmap) {
		StringBuffer idString = new StringBuffer();
		if (IdArray != null) {
			if (IdArray.length > 1000) {
				log.error("站号数组过长");
				throw new RuntimeException("自定义站号数组中站号传入过多，大于1000，请检查后分批传入");

			}
			// 增加idARRAY多次出现的判断
			int k = 0;
			for (k = 0; k < IdArray.length - 1; k++) {
				idString.append("?,");
				Long aaaLong = (Long) IdArray[k];
				ar.put(pos + k + 1, aaaLong);

			}
			idString.append("?");
			Long aaaLong = (Long) IdArray[k];
			ar.put(pos + k + 1, aaaLong);
			pos = pos + k + 1;

		}

		String str = sqlstr.replace("[IDARRAY]", idString);
		Inmap.put(Param.ExParamType.EX_SQL, str);

		return pos;
	}

	/**
	 * 将IDARRAY中的站号生成绑定变量占位符和相应的值
	 * 
	 * @param log
	 *            日志类变量名称
	 * @param IdArray
	 *            String类型站号数组
	 * @param sqlstr
	 *            输入串，被替换成带绑定变量的sql
	 * @param type
	 *            0 Long类型站号绑定，1 String类型站号绑定
	 * @param ar
	 *            绑定的位置和值
	 * @param pos
	 *            该组绑定的开始位置
	 * @return 绑定的占位符最后位置
	 */
	public static int bindStrID(Logger log, Object[] IdArray, String sqlstr,
			HashMap<Integer, Object> ar, int pos, HashMap<String, Object> Inmap) {
		StringBuffer idString = new StringBuffer();
		if (IdArray != null) {
			if (IdArray.length > 1000) {
				log.error("站号数组过长");
				throw new RuntimeException("自定义站号数组中站号传入过多，大于1000，请检查后分批传入");

			}
			int k = 0;
			for (k = 0; k < IdArray.length - 1; k++) {
				idString.append("?,");
				String aaa = IdArray[k].toString();
				ar.put(k + 1 + pos, aaa);
			}
			idString.append("?");
			String aaa = IdArray[k].toString();
			ar.put(k + 1 + pos, aaa);
			pos = pos + k + 1;
		}

		String str = sqlstr.replace("[STRIDARRAY]", idString);
		Inmap.put(Param.ExParamType.EX_SQL, str);
		return pos;
	}

	/**
	 * 将calendar中的时间生成绑定变量占位符和相应的值
	 * 
	 * @param log
	 *            日志类变量名称
	 * @param calendar
	 *            日期的值
	 * @param sqlstr
	 *            输入串，被替换成带绑定变量的sql
	 * @param type
	 *            0 TB类型替换，TE类型替换
	 * @param ar
	 *            绑定的位置和值
	 * @return 绑定的占位符最后位置
	 */
	public static int bindTime(Logger log, Calendar calendar, String sqlstr,
			int type, HashMap<Integer, Object> ar, int pos,
			HashMap<String, Object> Inmap) {

		ar.put(pos + 1, new Timestamp(calendar.getTime().getTime()));
		String str = "";
		if (type == 0) {
			// str= sqlstr.replace("[TB]", "?");
			int id = sqlstr.indexOf("[TB]");
			str = sqlstr.substring(0, id) + "?"
					+ sqlstr.substring(id + 4, sqlstr.length());
		} else if (type == 1) {
			// str=sqlstr.replace("[TE]", "?");
			int id = sqlstr.indexOf("[TE]");
			str = sqlstr.substring(0, id) + "?"
					+ sqlstr.substring(id + 4, sqlstr.length());
		}
		Inmap.put(Param.ExParamType.EX_SQL, str);
		return pos + 1;
	}

	/**
	 * 将附加条件替换到参数串中去,一个属性只允许压入一个值，不支持批量，如果是同一个列的多个条件，需要把列名区分开，否则没法判断
	 * 
	 * @param log
	 *            日志类变量名称
	 * @param sqlstr
	 *            输入的操作串
	 * @param exConds是输入的附加条件
	 * @param ar
	 *            绑定的位置和值
	 * @param
	 * @return 绑定的占位符最后位置
	 * 
	 */
	public static int bindExCond(Logger log, String sqlstr, ExCond[] exConds,
			HashMap<Integer, Object> ar, int pos, HashMap<String, Object> Inmap) {
		for (int i = 0; i < exConds.length; i++) {
			ExCond exCond = exConds[i];
			String strrep = "[" + exCond.getCharctype() + "]";
			sqlstr = sqlstr.replace(strrep, "?");
			ar.put(pos + 1, exCond.getValues());
			pos++;
		}
		Inmap.put(Param.ExParamType.EX_SQL, sqlstr);
		return pos;
	}

	/**
	 * 可变列名SQl语句替换，针对水利部数据库，列名为站名的情况
	 * 
	 * @param log
	 *            日志类变量名称
	 * @param StrIdArray
	 *            String类型站号数组
	 * @param sqlstr
	 *            原始的输入sql串
	 * @return 结果类名替换后的sql串
	 */
	public static String bindColunm(Logger log, Object[] StrIdArray,
			String sqlstr, HashMap<String, Object> Inmap) {
		StringBuffer idString = new StringBuffer();
		if (StrIdArray != null) {
			if (StrIdArray.length > 1000) {
				log.error("列名字段过多，请检查！");
				throw new RuntimeException("传入的列名字段过多，大于1000，请检查！");

			}
			int k = 0;
			for (k = 0; k < StrIdArray.length - 1; k++) {
				idString.append(StrIdArray[k].toString()).append(",");
			}
			idString.append(StrIdArray[k].toString());

		}
		String str = sqlstr.replace("[COLUMNNAME]", idString);
		Inmap.put(Param.ExParamType.EX_SQL, str);
		return sqlstr;

	}

	/**
	 * 绑定rownum或top参数
	 * 
	 * @param log
	 *            日志类变量名称
	 * @param rownum
	 *            压在参数Hashmap的EX_SLIPWIN中
	 * @param sqlstr
	 *            原始的输入sql串
	 * @param ar
	 *            绑定的位置和值
	 * @param pos
	 *            绑定的占位符开始位置
	 * @return 绑定的占位符最后位置
	 */
	public static int bindCount(Logger log, Integer rownum, String sqlstr,
			HashMap<Integer, Object> ar, int pos, HashMap<String, Object> Inmap) {

		ar.put(pos + 1, rownum);
		String str = sqlstr.replace("[ROWCOUNT]", "?");
		Inmap.put(Param.ExParamType.EX_SQL, str);
		return pos + 1;

	}

	/**
	 * 自动根据传入的参数生成calendar，注意月份从0开始，0表示1月
	 * 
	 * @param year
	 * @param month
	 * @param day
	 * @param hour
	 * @param min
	 * @param sec
	 * @return 返回calendar类型
	 */
	public static Calendar setcCalendar(int year, int month, int day, int hour,
			int min, int sec) {
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.YEAR, year);
		calendar.set(Calendar.MONTH, month);
		calendar.set(Calendar.DATE, day);
		calendar.set(Calendar.HOUR_OF_DAY, hour);
		calendar.set(Calendar.MINUTE, min);
		calendar.set(Calendar.SECOND, sec);
		return calendar;
	}

	/**
	 * 将开始时间和结束时间按整天、整小时分割为日时间段、小时时间段和实时数据段。可设小时偏移量 结果放入 HashMap<String, List
	 * <Calendar>> 结构之内
	 * 
	 * @param start_calendar
	 *            开始时间
	 * @param end_calendar
	 *            结束时间
	 * @param nHourOffSet
	 *            整天时间的小时偏移量
	 * @return HashMap<String, List<Calendar>> 其中 Param.RunDataType.RUN_DAY
	 *         包含0-1对 开始结束时间 Param.RunDataType.RUN_HOUR 包含0-2对 开始结束时间
	 *         Param.RunDataType.RUN_RT 包含1-2对 开始结束时间
	 */
	public static HashMap<String, List<Calendar>> splitBeginEndTime(
			Calendar start_calendar, Calendar end_calendar, int nHourOffSet) {
		HashMap<String, List<Calendar>> splitHashMapTime = new HashMap<String, List<Calendar>>();

		List<Calendar> splitRealTimeList = new ArrayList<Calendar>();
		List<Calendar> splitHourHashList = new ArrayList<Calendar>();
		List<Calendar> splitDayHashList = new ArrayList<Calendar>();
		// Set start_calendar And end_calendar Offset
		start_calendar.add(Calendar.HOUR_OF_DAY, (nHourOffSet * -1));
		end_calendar.add(Calendar.HOUR_OF_DAY, (nHourOffSet * -1));
		if (start_calendar.after(end_calendar)) {
			Logger log = Logger.getLogger(DaoTools.class);
			log.error("开始时间大于结束时间！");
			// Do nothing
		} else {
			int nstartHour = start_calendar.get(Calendar.HOUR_OF_DAY);
			int nstartMini = start_calendar.get(Calendar.MINUTE);
			int nstartSecond = start_calendar.get(Calendar.SECOND);

			int nendHour = end_calendar.get(Calendar.HOUR_OF_DAY);
			int nendMini = end_calendar.get(Calendar.MINUTE);
			int nendSecond = end_calendar.get(Calendar.SECOND);
			// Initial dayStartTime And dayEndTime
			Calendar dayStartTime = Calendar.getInstance();
			dayStartTime.setTime(start_calendar.getTime());
			Calendar dayEndTime = Calendar.getInstance();
			dayEndTime.setTime(end_calendar.getTime());
			// Initial hourStartTime And hourEndTime
			Calendar hourStartTime = Calendar.getInstance();
			hourStartTime.setTime(start_calendar.getTime());
			Calendar hourEndTime = Calendar.getInstance();
			hourEndTime.setTime(end_calendar.getTime());

			if ((nstartHour != 0) || (nstartMini != 0) || (nstartSecond != 0)) {
				dayStartTime.add(Calendar.DATE, 1);
				dayStartTime.set(Calendar.HOUR_OF_DAY, 0);
				dayStartTime.set(Calendar.MINUTE, 0);
				dayStartTime.set(Calendar.SECOND, 0);
			}
			if ((nendHour != 0) || (nendMini != 0) || (nendSecond != 0)) {
				dayEndTime.set(Calendar.HOUR_OF_DAY, 0);
				dayEndTime.set(Calendar.MINUTE, 0);
				dayEndTime.set(Calendar.SECOND, 0);
			}

			if (dayStartTime.before(dayEndTime)) // Hava Day
			{
				// Add dayStartTime and dayEndTime
				Calendar newDayStartTime = (Calendar) dayStartTime.clone();
				Calendar newDayEndTime = (Calendar) dayEndTime.clone();
				splitDayHashList.add(newDayStartTime);
				splitDayHashList.add(newDayEndTime);
				// Deal with Start Time Split
				if ((nstartMini != 0) || (nstartSecond != 0)) {
					hourStartTime.add(Calendar.HOUR_OF_DAY, 1);
					hourStartTime.set(Calendar.MINUTE, 0);
					hourStartTime.set(Calendar.SECOND, 0);
				}
				if (hourStartTime.before(dayStartTime)) // Hava Hour
				{
					// Add hourStartTime and dayStartTime
					Calendar newHourStartTime = (Calendar) hourStartTime
							.clone();
					Calendar newdayStartTime = (Calendar) dayStartTime.clone();
					splitHourHashList.add(newHourStartTime);
					splitHourHashList.add(newdayStartTime);

					if (start_calendar.before(hourStartTime)) // Add
																// start_calendar
																// and
																// hourStartTime
					{
						Calendar newStart_calendar = (Calendar) start_calendar
								.clone();
						Calendar newhourStartTime = (Calendar) hourStartTime
								.clone();
						splitRealTimeList.add(newStart_calendar);
						splitRealTimeList.add(newhourStartTime);
					}

				} else // Not have hour
				{
					if (start_calendar.before(dayStartTime))
					// Add start_calendar and dayStartTime
					{
						Calendar newstart_calendar = (Calendar) start_calendar
								.clone();
						Calendar newshourStartTime = (Calendar) dayStartTime
								.clone();
						splitRealTimeList.add(newstart_calendar);
						splitRealTimeList.add(newshourStartTime);
					}
				}

				// Deal with End Time Split
				if ((nendMini != 0) || (nendSecond != 0)) {
					hourEndTime.set(Calendar.MINUTE, 0);
					hourEndTime.set(Calendar.SECOND, 0);
				}
				if (dayEndTime.before(hourEndTime)) // Hava Hour
				{
					// Add dayEndTime and hourEndTime
					Calendar newdayEndTime = (Calendar) dayEndTime.clone();
					Calendar newHourEndTime = (Calendar) hourEndTime.clone();
					splitHourHashList.add(newdayEndTime);
					splitHourHashList.add(newHourEndTime);

					if (hourEndTime.before(end_calendar)) // Add hourEndTime and
															// end_calendar
					{
						Calendar newhourEndTime = (Calendar) hourEndTime
								.clone();
						Calendar newEnd_calendar = (Calendar) end_calendar
								.clone();
						splitRealTimeList.add(newhourEndTime);
						splitRealTimeList.add(newEnd_calendar);
					}

				} else // Not have hour
				{
					if (dayEndTime.before(end_calendar))
					// Add dayEndTime and end_calendar
					{
						Calendar newdayEndTime = (Calendar) dayEndTime.clone();
						Calendar newend_calendar = (Calendar) end_calendar
								.clone();
						splitRealTimeList.add(newdayEndTime);
						splitRealTimeList.add(newend_calendar);
					}
				}

			} else // Not Hava Day
			{
				if ((nstartMini != 0) || (nstartSecond != 0)) {
					hourStartTime.add(Calendar.HOUR_OF_DAY, 1);
					hourStartTime.set(Calendar.MINUTE, 0);
					hourStartTime.set(Calendar.SECOND, 0);
				}
				if ((nendMini != 0) || (nendSecond != 0)) {
					hourEndTime.set(Calendar.MINUTE, 0);
					hourEndTime.set(Calendar.SECOND, 0);
				}

				if (hourStartTime.before(hourEndTime)) // Hava Hour
				{
					// Add hourStartTime and hourEndTime
					Calendar newHourStartTime = (Calendar) hourStartTime
							.clone();
					Calendar newHourEndTime = (Calendar) hourEndTime.clone();
					splitHourHashList.add(newHourStartTime);
					splitHourHashList.add(newHourEndTime);

					if (start_calendar.before(hourStartTime)) // Add
																// start_calendar
																// and
																// hourStartTime
					{
						Calendar newStart_calendar = (Calendar) start_calendar
								.clone();
						Calendar newhourStartTime = (Calendar) hourStartTime
								.clone();
						splitRealTimeList.add(newStart_calendar);
						splitRealTimeList.add(newhourStartTime);
					}

					if (hourEndTime.before(end_calendar)) // Add hourEndTime and
															// end_calendar
					{
						Calendar newshourEndTime = (Calendar) hourEndTime
								.clone();
						Calendar newEnd_calendar = (Calendar) end_calendar
								.clone();
						splitRealTimeList.add(newshourEndTime);
						splitRealTimeList.add(newEnd_calendar);
					}
				} else // Not have hour
				{
					if (start_calendar.before(end_calendar)) // Add
																// start_calendar
																// and
																// end_calendar
					{
						Calendar newStart_calendar = (Calendar) start_calendar
								.clone();
						Calendar newEnd_calendar = (Calendar) end_calendar
								.clone();
						splitRealTimeList.add(newStart_calendar);
						splitRealTimeList.add(newEnd_calendar);
					}
				}
			}
		}
		// Change Offset Hour to Normal
		int nCalendNum = splitDayHashList.size();
		for (int nInd = 0; nInd < nCalendNum; nInd++) {
			Calendar curCalendar = splitDayHashList.get(nInd);
			curCalendar.add(Calendar.HOUR_OF_DAY, nHourOffSet);
		}
		int nHourCalendNum = splitHourHashList.size();
		for (int nHourInd = 0; nHourInd < nHourCalendNum; nHourInd++) {
			Calendar curHourCalendar = splitHourHashList.get(nHourInd);
			curHourCalendar.add(Calendar.HOUR_OF_DAY, nHourOffSet);
		}
		int nRealCalendNum = splitRealTimeList.size();
		for (int nRealInd = 0; nRealInd < nRealCalendNum; nRealInd++) {
			Calendar curRealCalendar = splitRealTimeList.get(nRealInd);
			curRealCalendar.add(Calendar.HOUR_OF_DAY, nHourOffSet);
		}

		splitHashMapTime.put(Param.RunDataType.RUN_DAY, splitDayHashList);
		splitHashMapTime.put(Param.RunDataType.RUN_HOUR, splitHourHashList);
		splitHashMapTime.put(Param.RunDataType.RUN_RT, splitRealTimeList);
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		for (Calendar ca : splitDayHashList) {
			System.out.println("日表被拆分的时间：+++++"
					+ dateFormat.format(ca.getTime().getTime()));
		}

		for (Calendar ca : splitHourHashList) {
			System.out.println("小时表被拆分的时间：+++++"
					+ dateFormat.format(ca.getTime().getTime()));
		}
		for (Calendar ca : splitRealTimeList) {
			System.out.println("实时表被拆分的时间：+++++"
					+ dateFormat.format(ca.getTime().getTime()));
		}

		return splitHashMapTime;
	}

	/**
	 * 检查类的变量类型和需要绑定的参数类型是否一致
	 * 
	 * @param inclass
	 *            输入类的类名
	 * @param filedname
	 *            类的变量名称
	 * @param value
	 *            给变量准备绑定的值
	 * @return 绑定能否成功或者失败
	 */
	public static boolean checkparamtype(Class inclass, String filedname,
			Object value) {

		Field f;
		try {
			// Field f1[]=inclass.getDeclaredFields();
			// 获取全部成员变量，含私有和公有的
			f = inclass.getDeclaredField(filedname);
			// System.out.println(value.getClass().getName());
			// System.out.println(f.getType().getName());
			// 值对象类型和域对象类型相同
			if (value.getClass().getName().equals(f.getType().getName())) {
				return true;
			}
			// f.setAccessible(true);

		} catch (SecurityException e) {

			return false;
		} catch (NoSuchFieldException e) {

			return false;
		}

		return false;

	}

	/**
	 * 根据传入参数拼装SQL的条件和最终的绑定串,绑定读取的是>=tb 并且<=te的数据
	 * 
	 * @param strbase
	 *            基础sql串
	 * @param pa
	 *            条件参数
	 * @param ar
	 *            记录绑定位置和值
	 * 
	 */

	public static String BindStr(String str, HashMap<String, Object> pa,
			HashMap<Integer, Object> ar, Logger log) {
		int k = 0;
		String wherestrString = "where ";
		// 拼装站号绑定位置
		String idString = "";
		if (pa.get(Param.IdType.LONG_IDARRAY) != null) {
			Object[] idary = (Object[]) pa.get(Param.IdType.LONG_IDARRAY);
			if (idary.length > 1000) {
				log.error("站号数组过长");
				return str;

			}
			if (idary.length > 0)// 如果站号数组大于0说明是删除某点号某时段数据
			{

				for (k = 0; k < idary.length - 1; k++) {
					idString = idString + "?,";
					ar.put(k + 1, idary[k]);
				}
				idString = idString + "?";
				ar.put(k + 1, idary[k]);

			}
		}

		Calendar tbeq = (Calendar) pa.get(Param.TimeType.EQ_BTIME);
		Calendar tbge = (Calendar) pa.get(Param.TimeType.GE_BTIME);
		Calendar tbgt = (Calendar) pa.get(Param.TimeType.GT_BTIME);
		Calendar tble = (Calendar) pa.get(Param.TimeType.LE_BTIME);
		Calendar tblt = (Calendar) pa.get(Param.TimeType.LT_BTIME);
		Calendar tele = (Calendar) pa.get(Param.TimeType.LE_ETIME);
		Calendar telt = (Calendar) pa.get(Param.TimeType.LT_ETIME);
		// 没有开始时间条件，认为没有时间条件，直接站号绑定返回
		if (tbeq == null && tbge == null && tbgt == null && tble == null
				&& tblt == null) {
			wherestrString = wherestrString + " senid in(" + idString + ")";
			str = str + wherestrString;
			return str;
		}

		// 拼装时间绑定位置
		if (idString != "") {
			wherestrString = wherestrString + " senid in(" + idString
					+ ") and ";
		}

		k = k + 1;
		// 当开始时间有等于，其他条件不再判断
		if (tbeq != null) {
			wherestrString = wherestrString + "  time=? ";
			ar.put(k + 1, tbeq);
			k = k + 1;
			str = str + wherestrString;
			return str;
		} else {
			if (tbge != null) {
				wherestrString = wherestrString + "  time>=? ";
				ar.put(k + 1, tbge);
				k = k + 1;
			} else {
				if (tbgt != null) {
					wherestrString = wherestrString + "  time>? ";
					ar.put(k + 1, tbgt);
					k = k + 1;
				} else {
					if (tble != null) {
						wherestrString = wherestrString + "  time<=? ";
						ar.put(k + 1, tble);
						k = k + 1;
						str = str + wherestrString;
						return str;
					} else {
						if (tblt != null) {
							wherestrString = wherestrString + "  time<? ";
							ar.put(k + 1, tblt);
							k = k + 1;
							str = str + wherestrString;
							return str;
						}
					}
				}
			}
		}

		// 此时开始时间必定是大于或大于等于条件，结束时间只对小于或小于等于生效，其他不再判断
		// 没有结束时间
		if (tele == null && telt == null) {
			str = str + wherestrString;
			return str;
		} else {
			if (tele != null) {
				wherestrString = wherestrString + " and time<=? ";
				ar.put(k + 1, tele);
				k = k + 1;
				str = str + wherestrString;
				return str;
			} else {
				wherestrString = wherestrString + " and time<? ";
				ar.put(k + 1, telt);
				k = k + 1;
				str = str + wherestrString;
				return str;
			}
		}

	}

	/**
	 * 将参数从FetchParam转成HashMap
	 * 
	 * @param inparam
	 * @return
	 */
	public static HashMap<String, Object> TransFetchParamToHashMap(
			FetchParam inparam) {
		HashMap<String, Object> retHashMap = new HashMap<String, Object>();
		if (inparam.apptype != null) {
			retHashMap.put(Param.AppType.App_Type_Key, inparam.apptype);
		}
		if (inparam.calctype != null) {
			retHashMap.put(Param.CalcType.Value_Type_Key, inparam.calctype);
		}
		if (inparam.valtype != null) {
			retHashMap.put(Param.ValType.Special_Type_Key, inparam.valtype);
		}
		if (inparam.ex_interval_int != null) {
			retHashMap.put(Param.ExParamType.EX_INTERVAL_INT,
					inparam.ex_interval_int);
		}
		if (inparam.ex_int != null) {
			retHashMap.put(Param.ExParamType.EX_INT,
					new Integer(inparam.ex_int));
		}
		if (inparam.idarrayLongs != null) {
			retHashMap.put(Param.IdType.LONG_IDARRAY, inparam.idarrayLongs);
		}
		if (inparam.idarrayStrings != null) {
			retHashMap.put(Param.IdType.STR_IDARRAY, inparam.idarrayStrings);

		}
		if (inparam.rundatatype != null) {
			retHashMap.put(Param.RunDataType.Rundata_Type_Key,
					inparam.rundatatype);
		}
		if (inparam.slipstep != null) {
			retHashMap.put(Param.ExParamType.EX_SLIPSTEP, new Integer(
					inparam.slipstep));
		}
		if (inparam.slipwin != null) {
			retHashMap.put(Param.ExParamType.EX_SLIPWIN, inparam.slipwin);
		}

		if (inparam.sliptype != null) {
			retHashMap.put(Param.SlipType.Slip_Type_Key, inparam.sliptype);
		}
		if (inparam.sqlstr != null) {
			retHashMap.put(Param.ExParamType.EX_SQL, inparam.sqlstr);
		}
		if (inparam.staticvalue != null) {
			retHashMap.put(Param.StaticValue.StaticValue_key,
					inparam.staticvalue);
		}
		if (inparam.exconds != null) {
			retHashMap.put(Param.ExCondition.ExCondition_key, inparam.exconds);
		}
		if (inparam.linkstrs != null) {
			retHashMap.put(Param.ExCondLinkStr.ExCondlinkstr_key,
					inparam.linkstrs);
		}
		if (inparam.FileNameStrings != null) {
			retHashMap.put(Param.ExCondLinkStr.ExCondlinkstr_key,
					inparam.FileNameStrings);
		}
		if (inparam.str0 != null) {
			retHashMap.put(Param.ExParamType.EX_STRING, inparam.str0);
		}
		if (inparam.str1 != null) {
			retHashMap.put(Param.ExParamType.EX_STRING1, inparam.str1);
		}
		if (inparam.str2 != null) {
			retHashMap.put(Param.ExParamType.EX_STRING2, inparam.str2);
		}
		if (inparam.orderflag != null) {
			retHashMap.put(Param.ExParamType.EX_ORDERFLAG, inparam.orderflag);

		}
		if (inparam.filter != null) {
			retHashMap.put(Param.ExParamType.EX_FILTER, inparam.filter);

		}

		if (inparam.timeCond != null) {
			TimeCond timecond = inparam.timeCond;
			if (timecond.btmcond != null && timecond.btmcond.equals(">")
					&& timecond.bt != null) {
				Calendar calendar = Calendar.getInstance();
				calendar.setTimeInMillis(timecond.bt.getTimeInMillis());
				retHashMap.put(Param.TimeType.GT_BTIME, calendar);
			}
			if (timecond.btmcond != null && timecond.btmcond.equals(">=")
					&& timecond.bt != null) {
				Calendar calendar = Calendar.getInstance();
				calendar.setTimeInMillis(timecond.bt.getTimeInMillis());
				retHashMap.put(Param.TimeType.GE_BTIME, calendar);
			}
			if (timecond.btmcond != null && timecond.btmcond.equals("=")
					&& timecond.bt != null) {
				Calendar calendar = Calendar.getInstance();
				calendar.setTimeInMillis(timecond.bt.getTimeInMillis());
				retHashMap.put(Param.TimeType.EQ_BTIME, calendar);
			}
			if (timecond.btmcond != null && timecond.btmcond.equals("<")
					&& timecond.bt != null) {
				Calendar calendar = Calendar.getInstance();
				calendar.setTimeInMillis(timecond.bt.getTimeInMillis());

				retHashMap.put(Param.TimeType.LT_BTIME, calendar);
			}
			if (timecond.btmcond != null && timecond.btmcond.equals("<=")
					&& timecond.bt != null) {
				Calendar calendar = Calendar.getInstance();
				calendar.setTimeInMillis(timecond.bt.getTimeInMillis());
				retHashMap.put(Param.TimeType.LE_BTIME, calendar);
			}

			if (timecond.etmcond != null && timecond.etmcond.equals(">")
					&& timecond.et != null) {
				Calendar calendar = Calendar.getInstance();
				calendar.setTimeInMillis(timecond.et.getTimeInMillis());

				retHashMap.put(Param.TimeType.GT_ETIME, calendar);
			}
			if (timecond.etmcond != null && timecond.etmcond.equals(">=")
					&& timecond.et != null) {
				Calendar calendar = Calendar.getInstance();
				calendar.setTimeInMillis(timecond.et.getTimeInMillis());

				retHashMap.put(Param.TimeType.GE_ETIME, calendar);
			}
			if (timecond.etmcond != null && timecond.etmcond.equals("=")
					&& timecond.et != null) {
				Calendar calendar = Calendar.getInstance();
				calendar.setTimeInMillis(timecond.et.getTimeInMillis());

				retHashMap.put(Param.TimeType.EQ_ETIME, calendar);
			}
			if (timecond.etmcond != null && timecond.etmcond.equals("<")
					&& timecond.et != null) {
				Calendar calendar = Calendar.getInstance();
				calendar.setTimeInMillis( timecond.et.getTimeInMillis());

				retHashMap.put(Param.TimeType.LT_ETIME, calendar);
			}
			if (timecond.etmcond != null && timecond.etmcond.equals("<=")
					&& timecond.et != null) {
				Calendar calendar = Calendar.getInstance();
				calendar.setTimeInMillis( timecond.et.getTimeInMillis());

				retHashMap.put(Param.TimeType.LE_ETIME, calendar);
			}

		}
		return retHashMap;
	}

	public static List<FetchParam> SplitFetchParam(FetchParam inparam) {
		List<FetchParam> feList = new ArrayList<FetchParam>();
		if (inparam.idarrayLongs.length > 500) {
			int k = (int) Math.floor((inparam.idarrayLongs.length / 500));
			Long[] oldiLongs = inparam.idarrayLongs;
			// 先拆整500的id号
			for (int i = 0; i < k; i++) {
				FetchParam fParamnew = (FetchParam) inparam.clone();
				Long[] idi = new Long[500];
				// List<Long> idi=new ArrayList<Long>();
				int idx = 0;
				for (int m = i * 500; m < (i + 1) * 500; m++) {
					idi[idx] = oldiLongs[m];
					idx++;
				}
				fParamnew.idarrayLongs = idi;
				feList.add(fParamnew);
			}
			// 剩下最后的id压入最后
			if (k * 500 < inparam.idarrayLongs.length) {
				FetchParam fParamnew = (FetchParam) inparam.clone();
				Long[] idi = new Long[inparam.idarrayLongs.length - k * 500];
				int idx = 0;
				for (int m = k * 500; m < inparam.idarrayLongs.length; m++) {
					idi[idx] = oldiLongs[m];
					idx++;
				}
				fParamnew.idarrayLongs = idi;
				feList.add(fParamnew);
			}
		} else {
			feList.add(inparam);
		}
		return feList;
	}

	/**
	 * 将参数从FetchParam转成HashMap
	 * 
	 * @param inparam
	 * @return
	 */
	public static HashMap<String, Object> TransDelParamToHashMap(
			DeleteParam inparam) {
		HashMap<String, Object> retHashMap = new HashMap<String, Object>();
		if (inparam.apptype != null) {
			retHashMap.put(Param.AppType.App_Type_Key, inparam.apptype);
		}
		if (inparam.optype != null) {
			retHashMap.put(Param.OpType.OP_Type_Key, inparam.optype);
		}

		if (inparam.ex_int != null) {
			retHashMap.put(Param.ExParamType.EX_INT,
					new Integer(inparam.ex_int));
		}
		if (inparam.idarrayLongs != null) {
			retHashMap.put(Param.IdType.LONG_IDARRAY, inparam.idarrayLongs);
		}
		if (inparam.idarrayStrings != null) {
			retHashMap.put(Param.IdType.STR_IDARRAY, inparam.idarrayStrings);

		}
		if (inparam.rundatatype != null) {
			retHashMap.put(Param.RunDataType.Rundata_Type_Key,
					inparam.rundatatype);
		}
		if (inparam.tb != null) {
			retHashMap.put(Param.TimeType.GE_BTIME, inparam.tb);
		}

		if (inparam.te != null) {
			retHashMap.put(Param.TimeType.LE_ETIME, inparam.te);
		}

		return retHashMap;
	}

	/**
	 * @author yangning
	 * @param inParams
	 *            将界面代入的入参转换为Hashmap使用
	 * @return
	 */
	public static HashMap<String, Object>[] TransFetchParamArrayToHashMapArray(
			FetchParam[] inParams) {
		if (inParams == null) {
			throw new RuntimeException("需要提取数据的入参没有设置，无法进行数据访问！");
		}
		HashMap<String, Object>[] request = new HashMap[inParams.length];
		for (int i = 0; i < inParams.length; i++) {
			FetchParam inmap = inParams[i];
			request[i] = TransFetchParamToHashMap(inmap);

		}

		return request;
	}

	/**
	 * @author caijie
	 * @param inParams
	 *            将界面代入的入参按应用分开到hashmap中
	 * @return
	 */
	public static HashMap<String, List<FetchParam>> TransFetchParamArrayToHashMapByApp(
			FetchParam[] inParams) {
		if (inParams == null) {
			throw new RuntimeException("需要提取数据的入参没有设置，无法进行数据访问！");
		}
		HashMap<String, List<FetchParam>> request = new HashMap<String, List<FetchParam>>();
		for (int i = 0; i < inParams.length; i++) {
			List<FetchParam> param = request.get(inParams[i].apptype);
			if (param == null)
				param = new ArrayList<FetchParam>();
			param.add(inParams[i]);
			request.put(inParams[i].apptype, param);
		}

		return request;
	}

	/**
	 * 将输入串以某标志符为间隔分割成一个数组
	 * 
	 * @param str
	 *            需要分割的字符串
	 * @param splitFalg
	 *            分割符号
	 * @return 拆分后的字符串数组
	 */
	public static String[] splitStr(String str, String splitFalg) {
		List<String> strList = new ArrayList<String>();
		return str.split(splitFalg);
	}

	/**
	 * 将附加条件串形成可以拼装成criterions的List<object>
	 * 
	 * @param exconds
	 *            附加条件串数组
	 * @return
	 */
	public static List<Object> parseExCondtoObject(ExCond[] exconds) {
		if (exconds == null)
			return null;
		ArrayList<Object> idobj = new ArrayList<Object>();
		for (ExCond exCond : exconds) {
			idobj.add(exCond.getCharctype());
			idobj.add(exCond.getCondition());
			idobj.add(exCond.getValues());

		}
		return idobj;
	}

	/**
	 * 日期转换 字符串 To Calendar
	 * 
	 * @param timestr
	 *            需要转换的字符串
	 * @param daysplitstr
	 *            日的分割串
	 * @param timesplitstr
	 *            小时以后时间的分割串
	 * @return
	 */
	public static Calendar tgStringToCalender(String timestr,
			String daysplitstr, String timesplitstr) {
		Calendar calendar = Calendar.getInstance();
		int year, month, day, hour, min, sec;
		year = month = day = hour = min = sec = 0;
		if (timestr.length() > 0) {
			year = Integer.parseInt(timestr.substring(0,
					timestr.indexOf(daysplitstr)));
			timestr = timestr.substring(timestr.indexOf(daysplitstr) + 1);
		}
		if (timestr.length() > 0) {
			month = Integer.parseInt(timestr.substring(0,
					timestr.indexOf(daysplitstr)));
			timestr = timestr.substring(timestr.indexOf(daysplitstr) + 1);
		}
		if (timestr.length() > 0) {
			if (timestr.indexOf(daysplitstr) < 0) {
				day = Integer.parseInt(timestr.substring(0,
						timestr.indexOf(" ")));
				timestr = timestr.substring(timestr.indexOf(" ") + 1);
			} else {
				day = Integer.parseInt(timestr.substring(0,
						timestr.indexOf(daysplitstr)));
				timestr = timestr.substring(timestr.indexOf(daysplitstr) + 1);
			}
		}
		// 转换时、分、秒
		if (timestr.length() > 0) {
			hour = Integer.parseInt(timestr.substring(0,
					timestr.indexOf(timesplitstr)));
			timestr = timestr.substring(timestr.indexOf(timesplitstr) + 1);
		}
		if (timestr.length() > 0) {
			min = Integer.parseInt(timestr.substring(0,
					timestr.indexOf(timesplitstr)));
			timestr = timestr.substring(timestr.indexOf(timesplitstr) + 1);
		}
		if (timestr.length() > 0) {
			if (timestr.indexOf(timesplitstr) < 0) {
				sec = Integer.parseInt(timestr);
			} else {
				sec = Integer.parseInt(timestr.substring(0,
						timestr.indexOf(timesplitstr)));
				timestr = timestr.substring(timestr.indexOf(timesplitstr) + 1);
			}
		}

		calendar.set(year, month - 1, day, hour, min, sec);

		return calendar;
	}

	/**
	 * 是否允许合并特征值查询(有的情况不允许合并)
	 * 
	 * @param inParam
	 *            特征值
	 * @return
	 */
	private static boolean allowConbineFetchParam(FetchParam inParam) {
		if (inParam == null)
			return false;
		String calctype = inParam.calctype;
		if (calctype != null) {
			Long[] ids = inParam.idarrayLongs;
			if (ids != null && ids.length > 0) {
				if (calctype.equals("PM_LastAbsMaxV")
						|| calctype.equals("PM_LastAbsMaxV_SPName")
						|| calctype.equals("PM_LastMaxV")
						|| calctype.equals("PM_LastMaxV_SPName")) 
				{
					return false;
				}
			}
		}
		return true;
	}

	/**
	 * 将查询的入参进行优化，合并相同条件的入参形成新的入参数组
	 * 
	 * @param inParams
	 * @return
	 */
	public static FetchParam[] conbineFetchParams(FetchParam[] inParams) {
		// 先保存一下入参数组
		List<FetchParam> tempparams = new ArrayList<FetchParam>();

		// 就一个参数没什么可以优化之处
		if (inParams.length <= 1) {
			return inParams;
		}
		List<FetchParam> oldparams = new ArrayList<FetchParam>();
		for (int i = 0; i < inParams.length; i++) {
			if (allowConbineFetchParam(inParams[i]))
				tempparams.add((FetchParam) inParams[i].clone());
			else
				tempparams.add(inParams[i]); // 后期需要equals比较，所以要保持原有对象
			oldparams.add(inParams[i]);
		}
		// 存放优化过的入参数据组，组要针对ID优化
		List<FetchParam> listFetchParams = new ArrayList<FetchParam>();
		// 做参数检查与合并
		for (int i = 0; i < oldparams.size(); i++) {
			FetchParam pold = oldparams.get(i);
			// 存放id号数组
			List<Long> idlist = new ArrayList<Long>();

			for (int k = 0; k < pold.idarrayLongs.length; k++) {
				idlist.add(pold.idarrayLongs[k]);
			}

			for (int j = i + 1; j < oldparams.size(); j++) {
				FetchParam pnew = oldparams.get(j);
				// 寻找与其除了站号不等以外的其他参数条件相同的参数数组进行合并
				// 判断前一个参数和后一个参数是否完全相等的标志
				int app, run, calc, val, step, win, type, time, sql;
				app = run = calc = val = step = win = type = time = sql = 0;
				if ((pnew.apptype != null && pold.apptype != null && pnew.apptype
						.equals(pold.apptype))
						|| (pnew.apptype == null && pold.apptype == null)) {
					app = 1;
				}
				if ((pnew.rundatatype != null && pold.rundatatype != null && pnew.rundatatype
						.equals(pold.rundatatype))
						|| (pnew.rundatatype == null && pold.rundatatype == null)) {
					run = 1;
				}
				if ((pnew.calctype != null && pold.calctype != null && pnew.calctype
						.equals(pold.calctype))
						|| (pnew.calctype == null && pold.calctype == null)) {
					calc = 1;
				}
				if ((pnew.valtype != null && pold.valtype != null && pnew.valtype
						.equals(pold.valtype))
						|| (pnew.valtype == null && pold.valtype == null)) {
					val = 1;
				}
				if ((pnew.slipstep != null && pold.slipstep != null && pnew.slipstep
						.equals(pold.slipstep))
						|| (pnew.slipstep == null && pold.slipstep == null)) {
					step = 1;
				}
				if ((pnew.slipwin != null && pold.slipwin != null && pnew.slipwin
						.equals(pold.slipwin))
						|| (pnew.slipwin == null && pold.slipwin == null)) {
					win = 1;
				}
				if ((pnew.sliptype != null && pold.sliptype != null && pnew.sliptype
						.equals(pold.sliptype))
						|| (pnew.sliptype == null && pold.sliptype == null)) {
					type = 1;
				}
				if ((pnew.timeCond != null && pold.timeCond != null && pnew.timeCond
						.equals(pold.timeCond))
						|| (pnew.timeCond == null && pold.timeCond == null)) {
					time = 1;
				}
				if (pnew.sqlstr == null && pold.sqlstr == null) {
					sql = 1;
				}

				if (app == 1 && run == 1 && calc == 1 && val == 1 && step == 1
						&& win == 1 && type == 1 && time == 1 && sql == 1
						&& allowConbineFetchParam(pnew)) {
					for (int m = 0; m < pnew.idarrayLongs.length; m++) {
						idlist.add(pnew.idarrayLongs[m]);
					}
					oldparams.remove(j);
					j--;
				}
			}
			Object[] idObjects = idlist.toArray();
			Long[] longidLongs = new Long[idObjects.length];
			for (int kk = 0; kk < idObjects.length; kk++) {
				longidLongs[kk] = (Long) idObjects[kk];
			}
			pold.idarrayLongs = longidLongs;
			listFetchParams.add(pold);
		}
		FetchParam[] reFetchParams = new FetchParam[listFetchParams.size()];
		for (int i = 0; i < listFetchParams.size(); i++) {
			reFetchParams[i] = listFetchParams.get(i);
		}

		for (int i = 0; i < tempparams.size(); i++) {
			inParams[i] = tempparams.get(i);
		}
		return reFetchParams;
	}

	/**
	 * 返回结果集按合并前的入参进行拆分的过程
	 * 
	 * @param inParams
	 *            拆分前入参
	 * @param privFetchParams
	 *            合并后的入参
	 * @param priMaps
	 *            合并后的执行结果
	 * @return 拆分后的结果
	 */
	public static HashMap<Long, Chardata>[] splitHashMaps(
			FetchParam[] inParams, FetchParam[] privFetchParams,
			HashMap<Long, Chardata>[] priMaps) {
		HashMap<Long, Chardata>[] retMaps = new HashMap[inParams.length];
		// 如果就一个参数或者优化过的参数和原始参数个数一样，说明返回结果没什么可以拆分的
		if (inParams.length <= 1 || inParams.length == privFetchParams.length) {
			return priMaps;
		}
		/*
		 * //oldparams和newparams用于操作合并拆分，移除等 List<FetchParam> oldparams=new
		 * ArrayList<FetchParam>(); for(int i=0;i<inParams.length;i++) {
		 * oldparams.add(inParams[i]); } List<FetchParam> newparams=new
		 * ArrayList<FetchParam>(); for(int i=0;i<privFetchParams.length;i++) {
		 * newparams.add(privFetchParams[i]); }
		 */for (int i = 0; i < priMaps.length; i++) {
			// 先把结果集进行拆分处理
			HashMap<Long, Chardata> priMap = priMaps[i];
			if (priMaps[i] == null) {
				continue;
			}
			// 根据结果集取配套的入参
			if (privFetchParams.length > i) {
				FetchParam pnew = privFetchParams[i];
				// 如果是sql串查询根本不会合并，所以直接提取结果集
				if (pnew.sqlstr != null) {
					// 找到匹配的入参及提取数据
					for (int k = 0; k < inParams.length; k++) {
						FetchParam pold = inParams[k];
						int ids, app, run, calc, val, step, win, type, time;
						app = run = calc = val = step = win = type = time = ids = 0;
						if (pnew.idarrayLongs != null
								&& pold.idarrayLongs != null
								&& pnew.idarrayLongs.length == pold.idarrayLongs.length) {
							for (int m = 0; m < pold.idarrayLongs.length; m++) {
								if (!pold.idarrayLongs[m]
										.equals(pnew.idarrayLongs[m])) {
									break;
								}
								if (m == pold.idarrayLongs.length - 1) {
									ids = 1;
								}
							}
						}
						if ((pnew.apptype != null && pold.apptype != null && pnew.apptype
								.equals(pold.apptype))
								|| (pnew.apptype == null && pold.apptype == null)) {
							app = 1;
						}
						if ((pnew.rundatatype != null
								&& pold.rundatatype != null && pnew.rundatatype
									.equals(pold.rundatatype))
								|| (pnew.rundatatype == null && pold.rundatatype == null)) {
							run = 1;
						}
						if ((pnew.calctype != null && pold.calctype != null && pnew.calctype
								.equals(pold.calctype))
								|| (pnew.calctype == null && pold.calctype == null)) {
							calc = 1;
						}
						if ((pnew.valtype != null && pold.valtype != null && pnew.valtype
								.equals(pold.valtype))
								|| (pnew.valtype == null && pold.valtype == null)) {
							val = 1;
						}
						if ((pnew.slipstep != null && pold.slipstep != null && pnew.slipstep
								.equals(pold.slipstep))
								|| (pnew.slipstep == null && pold.slipstep == null)) {
							step = 1;
						}
						if ((pnew.slipwin != null && pold.slipwin != null && pnew.slipwin
								.equals(pold.slipwin))
								|| (pnew.slipwin == null && pold.slipwin == null)) {
							win = 1;
						}
						if ((pnew.sliptype != null && pold.sliptype != null && pnew.sliptype
								.equals(pold.sliptype))
								|| (pnew.sliptype == null && pold.sliptype == null)) {
							type = 1;
						}
						if ((pnew.timeCond != null && pold.timeCond != null && pnew.timeCond
								.equals(pold.timeCond))
								|| (pnew.timeCond == null && pold.timeCond == null)) {
							time = 1;
						}
						if (pold.sqlstr != null && pnew.sqlstr != null
								&& pold.sqlstr.equals(pnew.sqlstr) && app == 1
								&& run == 1 && calc == 1 && val == 1
								&& step == 1 && win == 1 && type == 1
								&& time == 1 && ids == 1) {
							HashMap<Long, Chardata> piHashMap = new HashMap<Long, Chardata>();
							for (int m = 0; m < pold.idarrayLongs.length; m++) {
								piHashMap.put(pold.idarrayLongs[m],
										priMap.get(pold.idarrayLongs[m]));
							}
							retMaps[k] = piHashMap;
						}
					}

				}
				// 非sql匹配
				else {
					for (int k = 0; k < inParams.length; k++) {
						FetchParam pold = inParams[k];
						int app, run, calc, val, step, win, type, time;
						app = run = calc = val = step = win = type = time = 0;
						if ((pnew.apptype != null && pold.apptype != null && pnew.apptype
								.equals(pold.apptype))
								|| (pnew.apptype == null && pold.apptype == null)) {
							app = 1;
						}
						if ((pnew.rundatatype != null
								&& pold.rundatatype != null && pnew.rundatatype
									.equals(pold.rundatatype))
								|| (pnew.rundatatype == null && pold.rundatatype == null)) {
							run = 1;
						}
						if ((pnew.calctype != null && pold.calctype != null && pnew.calctype
								.equals(pold.calctype))
								|| (pnew.calctype == null && pold.calctype == null)) {
							calc = 1;
						}
						if ((pnew.valtype != null && pold.valtype != null && pnew.valtype
								.equals(pold.valtype))
								|| (pnew.valtype == null && pold.valtype == null)) {
							val = 1;
						}
						if ((pnew.slipstep != null && pold.slipstep != null && pnew.slipstep
								.equals(pold.slipstep))
								|| (pnew.slipstep == null && pold.slipstep == null)) {
							step = 1;
						}
						if ((pnew.slipwin != null && pold.slipwin != null && pnew.slipwin
								.equals(pold.slipwin))
								|| (pnew.slipwin == null && pold.slipwin == null)) {
							win = 1;
						}
						if ((pnew.sliptype != null && pold.sliptype != null && pnew.sliptype
								.equals(pold.sliptype))
								|| (pnew.sliptype == null && pold.sliptype == null)) {
							type = 1;
						}
						if ((pnew.timeCond != null && pold.timeCond != null && pnew.timeCond
								.equals(pold.timeCond))
								|| (pnew.timeCond == null && pold.timeCond == null)) {
							time = 1;
						}
						if (app == 1 && run == 1 && calc == 1 && val == 1
								&& step == 1 && win == 1 && type == 1
								&& time == 1 && allowConbineFetchParam(pnew)) {
							HashMap<Long, Chardata> piHashMap = new HashMap<Long, Chardata>();
							for (int m = 0; m < pold.idarrayLongs.length; m++) {
								piHashMap.put(pold.idarrayLongs[m],
										priMap.get(pold.idarrayLongs[m]));
							}
							retMaps[k] = piHashMap;
						} else if (!allowConbineFetchParam(pnew)
								&& pnew.equals(pold)) {
							retMaps[k] = priMap;
						}
					}
				}
			}

		}
		return retMaps;
	}

	/**
	 * 获取小时整点
	 * 
	 * @param d1
	 * @return
	 */
	public static Calendar tgCalGetHour(Calendar d) {
		long tl = d.getTimeInMillis();
		return tgLongToCalender(tl - tl % 3600000);
	}

	/**
	 * 检查结束时间条件错误：如果时间超过当前时间，则将其圆整到当前时间
	 * 
	 * @param d1
	 * @return 时间结果
	 */
	public static Calendar tgCalCheckTimeNow(Calendar d) {
		long t2 = Calendar.getInstance().getTimeInMillis();
		long t1 = d.getTimeInMillis();
		if (t1 > t2)
			return Calendar.getInstance();
		return d;
	}

	/**
	 * 毫秒清零
	 * 
	 * @param d1
	 * @return 时间结果
	 */
	public static Calendar tgCalCleanMill(Calendar d) {
		long t1 = d.getTimeInMillis();
		t1 = t1 - t1 % 1000;
		return tgLongToCalender(t1);
	}

	/**
	 * 判断时间条件错误：时间超过当前时间
	 * 
	 * @param d1
	 * @return true表示存在问题
	 */
	public static boolean tgCalCondError(Calendar d) {
		long t2 = Calendar.getInstance().getTimeInMillis();
		long t1 = d.getTimeInMillis();
		return t1 > t2;
	}

	/**
	 * HashMap转换为HashTable
	 * 
	 * @author caijie
	 * @param inmap
	 * @return hashtable
	 */
	public static Hashtable paraHashMapToTable(HashMap inmap) {
		Hashtable res = new Hashtable();
		Iterator it = inmap.entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry entry = (Map.Entry) it.next();
			String key = (String) entry.getKey();
			Object value = entry.getValue();
			res.put(key, value);
		}
		return res;
	}

	/**
	 * HashMap<Long, List<Chardata>>中list的末尾增加一个记录
	 * 
	 * @author caijie
	 * @param inmap
	 * @param cha
	 *            记录元素
	 * @return HashMap 最终结果
	 */
	public static HashMap<Long, List<Chardata>> paraHashMapAddElement(
			HashMap<Long, List<Chardata>> inmap, Chardata cha) {
		List<Chardata> chas = inmap.get(cha.getSenid());
		if (chas == null)
			chas = new ArrayList<Chardata>();
		chas.add(cha);
		inmap.put(cha.getSenid(), chas);
		return inmap;
	}

	/**
	 * HashMap<Long, List<Chardata>>中list的第一个位置增加一个记录
	 * 
	 * @author caijie
	 * @param inmap
	 * @param cha
	 *            记录元素
	 * @return HashMap 最终结果
	 */
	public static HashMap<Long, List<Chardata>> paraHashMapAddElementBegin(
			HashMap<Long, List<Chardata>> inmap, Chardata cha) {
		List<Chardata> chas = inmap.get(cha.getSenid());
		if (chas == null)
			chas = new ArrayList<Chardata>();
		chas.add(0, cha);
		inmap.put(cha.getSenid(), chas);
		return inmap;
	}

	/**
	 * 判断是否为全生命周期追溯数据,需要写入用户名、机器名、程序名
	 * 
	 * @author yangning
	 * @return 是否为监控数据
	 * @param id
	 *            测点号
	 * 
	 */
	public static boolean checkPoint(HashMap<String, Object> inmap) {
		if (inmap.get(Param.ExParamType.EX_USERNAME) != null) {
			return true;
		}
		if (inmap.get(Param.ExParamType.EX_MACHINE) != null) {
			return true;
		}
		if (inmap.get(Param.ExParamType.EX_PROGRAM) != null) {
			return true;
		}
		return false;
	}

	public static void checkSt323App(Logger log, HashMap<String, Object> inmap) {
		checkInmapNotNull(log, inmap);
		String appname = (String) inmap.get(Param.AppType.App_Type_Key);
		if (!appname.equals(Param.AppType.APP_Type_ST323)) {
			log.error("参数传入的调用的在应用无法被处理，参数传入的应用类型为：" + appname);
			throw new RuntimeException("参数传入的调用的在应用无法被处理，参数传入的应用类型为：" + appname);
		}

	}

	public static void checkSt325App(Logger log, HashMap<String, Object> inmap) {
		checkInmapNotNull(log, inmap);
		String appname = (String) inmap.get(Param.AppType.App_Type_Key);
		if (!appname.equals(Param.AppType.APP_Type_ST325)) {
			log.error("参数传入的调用的在应用无法被处理，参数传入的应用类型为：" + appname);
			throw new RuntimeException("参数传入的调用的在应用无法被处理，参数传入的应用类型为：" + appname);
		}

	}

	/**
	 * 执行sql的时间参数只接受相等条件的，此处将不等条件的时间转换到相应位置
	 * 
	 * @param inmapLocal
	 */
	public static HashMap<String, Object> changeTimeForSql(
			HashMap<String, Object> inmapLocal) {
		HashMap<String, Object> inMap = paraCopyHashmap(inmapLocal);
		if (inmapLocal.get(Param.TimeType.EQ_BTIME) == null) {
			Calendar cabt = null;
			cabt = (Calendar) inmapLocal.get(Param.TimeType.GE_BTIME);
			if (cabt == null) {
				cabt = (Calendar) inmapLocal.get(Param.TimeType.GT_BTIME);
				if (cabt == null) {
					cabt = (Calendar) inmapLocal.get(Param.TimeType.LT_BTIME);
					if (cabt == null) {
						cabt = (Calendar) inmapLocal
								.get(Param.TimeType.LE_BTIME);
					}
				}
			}
			if (cabt != null) {
				inMap.put(Param.TimeType.EQ_BTIME, cabt);
			}

		}

		if (inmapLocal.get(Param.TimeType.EQ_ETIME) == null) {
			Calendar cabt = null;
			cabt = (Calendar) inmapLocal.get(Param.TimeType.GE_ETIME);
			if (cabt == null) {
				cabt = (Calendar) inmapLocal.get(Param.TimeType.GT_ETIME);
				if (cabt == null) {
					cabt = (Calendar) inmapLocal.get(Param.TimeType.LT_ETIME);
					if (cabt == null) {
						cabt = (Calendar) inmapLocal
								.get(Param.TimeType.LE_ETIME);
					}
				}
			}
			if (cabt != null) {
				inMap.put(Param.TimeType.EQ_ETIME, cabt);
			}

		}

		return inMap;
	}

	/**
	 * 获取POJO类对应的表名字段名
	 * 
	 * @param po类对象
	 * @param charaStr
	 *            属性名
	 * @return 类对应的表名
	 */
	public static String[] getPojoTableNameAndUserAndCol(Class po,
			String charaStr) {
		String[] tnameanduser = new String[3];
		Annotation[] annotation = po.getAnnotations();
		for (int i = 0; i < annotation.length; i++) {
			Annotation an = annotation[i];
			String annameString = an.toString() + ",";
			if (annameString.indexOf("Table") >= 0) {
				tnameanduser[0] = ParamString.GetParaValue(annameString,
						"schema", ",");
				tnameanduser[1] = ParamString.GetParaValue(annameString,
						"name", ",");
				if (tnameanduser[1].indexOf(")") >= 0) {
					tnameanduser[1] = tnameanduser[1].replace(")", "");
				}
				break;
			}
		}
		charaStr = charaStr.replaceFirst(charaStr.substring(0, 1), charaStr
				.substring(0, 1).toUpperCase());
		String str = "get" + charaStr;

		if (str.contains(".")) {
			String tempstr = str.substring(str.indexOf(".") + 1);
			try {
				Method m = po.getMethod(str.substring(0, str.indexOf(".")));
				Annotation[] annotations = m.getAnnotations();
				if (annotation == null) {
					tnameanduser[2] = charaStr;
				} else {
					for (Annotation ann : annotations) {
						String strrrr = ann.toString();
						int pos = strrrr.indexOf(tempstr);
						if (pos > 0) {
							pos = pos + tempstr.length() + 28;
							strrrr = strrrr.substring(pos) + ",";
							tnameanduser[2] = ParamString.GetParaValue(strrrr,
									"name", ",");
							break;
						}

					}
				}

			} catch (SecurityException e) {
				e.printStackTrace();
			} catch (NoSuchMethodException e) {
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			}

			// str=str.substring(0,str.indexOf("."))+"()."+tempstr;
		} else {

			try {
				Method m = po.getMethod(str);
				String name = m.getName();
				System.out.println(name);

				Annotation[] annotations = m.getAnnotations();
				if (annotation == null) {
					tnameanduser[2] = charaStr;
				} else {
					for (Annotation ann : annotations) {
						tnameanduser[2] = ParamString.GetParaValue(
								ann.toString(), "name", ",");
						break;
					}
				}
			} catch (SecurityException e) {

				e.printStackTrace();
			} catch (NoSuchMethodException e) {

				e.printStackTrace();
			}
		}
		return tnameanduser;

	}

	/**
	 * 获取POJO类对应的表名和字段名称
	 * 
	 * @param po类对象
	 * @param charaStr
	 *            属性名
	 * @return 类对应的表名
	 */
	public static String[] getPojoTableNameAndCol(Class po, String charaStr) {
		String[] tnameanduser = new String[3];
		Annotation[] annotation = po.getAnnotations();
		for (int i = 0; i < annotation.length; i++) {
			Annotation an = annotation[i];
			String annameString = an.toString() + ",";
			if (annameString.indexOf("Table") >= 0) {
				tnameanduser[0] = ParamString.GetParaValue(annameString,
						"schema", ",");
				tnameanduser[1] = ParamString.GetParaValue(annameString,
						"name", ",");
				if (tnameanduser[1].indexOf(")") >= 0) {
					tnameanduser[1] = tnameanduser[1].replace(")", "");
				}
				break;
			}
		}
		if (charaStr != null) {
			charaStr = charaStr.replaceFirst(charaStr.substring(0, 1), charaStr
					.substring(0, 1).toUpperCase());
			tnameanduser[2] = charaStr;
		}
		// 只需要读到表明
		else {
			tnameanduser[2] = null;
		}
		return tnameanduser;

	}

	/**
	 * 获取是否为POJO类
	 * 
	 * @param po类对象
	 * @param charaStr
	 *            属性名
	 * @return true 是POJO类
	 */
	public static boolean getISPojoTableOnClass(Class po) {

		if (po == null)
			return false;

		// String[] tnameanduser = new String[3];
		Annotation[] annotation = po.getAnnotations();

		if (annotation == null)
			return false;

		for (int i = 0; i < annotation.length; i++) {
			Annotation an = annotation[i];
			String annameString = an.toString() + ",";

			if (annameString.indexOf("Table") >= 0) {
				return true;
			}
		}

		return false;

	}

	/**
	 * 获取对象是否为POJO类
	 * 
	 * @param obj类对象
	 * @return true 是POJO类
	 */
	public static boolean getISPojoTableOnObject(Object obj) {

		if (obj == null)
			return false;

		Class cls = obj.getClass();

		if (cls == null)
			return false;

		return getISPojoTableOnClass(cls);
	}

	/**
	 * 判断某个实体类中是否有某属性的get方法
	 * 
	 * @param entityclass
	 *            实体类名
	 * @param chara
	 *            属性名
	 * @return
	 */
	public static boolean getIsMethodInPOJO(Class entityclass, String chara) {
		String getname;
		getname = "get";
		getname = getname + Character.toUpperCase(chara.charAt(0));
		getname = getname + chara.substring(1);
		Class cls = entityclass;

		try {
			cls.getMethod(getname, null);
		} catch (SecurityException e) {

			return false;
		} catch (NoSuchMethodException e) {

			return false;
		}

		return true;
	}

	/**
	 * 获取某个类某属性的对象类型，用于界面确定输入某个方法的值对象类型
	 * 
	 * @param entityclass
	 *            实体类
	 * @param chara
	 *            属性名称
	 * @return 该属性的类型
	 */
	public static Class getPOJOColumnType(Class entityclass, String chara) {
		Class cls = entityclass;
		String getname;

		try {

			// 如果是复合主键，要单独处理
			if (chara.contains("id.")) {
				chara = chara.replace("id.", "");
				getname = "get";
				getname = getname + Character.toUpperCase(chara.charAt(0));
				getname = getname + chara.substring(1);
				// getname="getId."+getname;

				Method method = cls.getMethod("getId", null);

				if (method != null) {
					// 得到复合主键的对象类型
					Class cs = method.getReturnType();
					Method method2 = cs.getMethod(getname, null);

					if (method2 != null)
						return method2.getReturnType();
				}
			} else {
				getname = "get";
				getname = getname + Character.toUpperCase(chara.charAt(0));
				getname = getname + chara.substring(1);

				Method method = cls.getMethod(getname, null);

				if (method != null)
					return method.getReturnType();

			}

		} catch (SecurityException e) {
			// e.printStackTrace();
		} catch (NoSuchMethodException e) {
			// e.printStackTrace();
		}
		return null;
	}

	/**
	 * 将web界面传入的附加条件入参转换成dao的hashmap
	 * 
	 * @author yangning
	 * @param cls
	 *            调用类型的类对象
	 * @param inmap
	 *            生成的供dao调用的hashmap
	 * @param inParams
	 *            将界面代入的入参
	 * @return 转换成功与否，1成功，0 失败；失败时返回整个数据库表内容
	 */
	public static int TransWebParamToExcond(Class cls,
			HashMap<String, Object> inmaps, WebGenSqlCond[] inParams,
			String userstr) {
		// 如果是自定义输入条件，则直接用这个条件串
		if (userstr != null) {
			inmaps.put(Param.ExParamType.EX_STRING, userstr);
			return 2;
		}
		// 当界面的附加条件为空时，进行全表查询
		if (inParams == null) {
			return 0;
		}
		ExCond[] request = new ExCond[inParams.length];
		// 连接字符串
		String strs = "";
		for (int i = 0; i < inParams.length; i++) {
			WebGenSqlCond webcond = inParams[i];
			if (webcond.getCombineStr() != null) {
				strs += "c" + i + " " + webcond.getCombineStr() + " ";
			} else {
				strs += "c" + i;
			}
			// request[i]=TransFetchParamToHashMap(inmap);
			request[i] = new ExCond(webcond.getCharctype(),
					webcond.getCondition(), null);
			// request[i].setCharctype(webcond.getCharctype());
			// request[i].setCondition(webcond.getCondition());
			Field charField = null;

			if (webcond.getCharctype().contains("id.")) {
				try {
					charField = cls.getDeclaredField("id");
					java.lang.reflect.Type genType = charField.getGenericType();
					Class pkcls = Class.forName(genType.toString().replaceAll(
							"class ", ""));
					String chartypeStr = webcond.getCharctype().replaceAll(
							"id.", "");
					charField = pkcls.getDeclaredField(chartypeStr);

				} catch (SecurityException e) {
					e.printStackTrace();
				} catch (NoSuchFieldException e) {
					e.printStackTrace();
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				}

			} else {
				try {
					charField = cls.getDeclaredField(webcond.getCharctype());
				} catch (SecurityException e) {
					e.printStackTrace();
				} catch (NoSuchFieldException e) {
					e.printStackTrace();
				}

			}

			if (charField == null) {
				continue;
			}
			java.lang.reflect.Type genType = charField.getGenericType();
			String aaaString = genType.toString();
			request[i].setValues(parseWebInObj(webcond.getCombineStr(),
					aaaString, webcond.getValues()));
			/*
			 * if (aaaString.contains("Integer")) {
			 * request[i].setValues(Integer.parseInt(webcond.getValues())); }
			 * else if (aaaString.contains("Long")) {
			 * request[i].setValues(Long.parseLong(webcond.getValues())); } else
			 * if (aaaString.contains("Float")) {
			 * request[i].setValues(Float.parseFloat(webcond.getValues())); }
			 * else if (aaaString.contains("Double")) {
			 * request[i].setValues(Double.parseDouble(webcond.getValues())); }
			 * else if (aaaString.contains("Timestamp")) {
			 * request[i].setValues(tgDateToTimestamp
			 * (tgStringToCalender(webcond.getValues(), "-",":"))); //String
			 * tttString=request[i].getValues().getClass().getName();
			 * //System.out.println("yntestttttttttttttttttttt"+tttString); }
			 * else if (aaaString.contains("Date")) {
			 * request[i].setValues(tgStringToCalender(webcond.getValues(),
			 * "-",":")); } else { request[i].setValues(webcond.getValues()); }
			 */
		}
		inmaps.put(Param.ExCondition.ExCondition_key, request);
		inmaps.put(Param.ExCondLinkStr.ExCondlinkstr_key, strs);
		return 1;
	}

	/**
	 * 形成绑定变量的字符串和参数数组
	 * 
	 * @param exconds
	 *            附加条件数组
	 * @param linkstr
	 *            连接字符串条件
	 * @return 带绑定变量的连接字符串
	 */
	@SuppressWarnings("rawtypes")
	public static String excondstr(ExCond[] exconds, String linkstr,
			List<Object> vallist) {
		StringBuffer sb = new StringBuffer();
		StringBuffer sa = new StringBuffer();
		int i = 0;
		// 只有一个条件的时候
		if (linkstr == null && exconds.length == 1) {
			linkstr = "c0";
		}
		for (ExCond excond : exconds) {
			sb.delete(0, sb.length());
			sa.delete(0, sa.length());
			sb.append(excond.getCharctype());
			sb.append(" ");
			sb.append(excond.getCondition());
			if (excond.getCondition().equals("in")) {
				Object objs = excond.getValues();
				sa.append("c");
				sa.append(i);
				if (objs instanceof Object[]) {
					sb.append("(");
					Object[] objss = (Object[]) objs;
					for (int j = 0; j < objss.length; j++) {
						if (j != objss.length - 1) {
							sb.append("?,");
						} else {
							sb.append("?");
						}
						vallist.add(objss[j]);
						// i++;
					}
					sb.append(")");
				} else {
					sb.append("(?)");
					vallist.add(excond.getValues());
					//sa.append("c");
					//sa.append(i);
				}

			} else {
				sb.append("?");
				vallist.add(excond.getValues());
				sa.append("c");
				sa.append(i);
			}

			// 连接串可能没有，当只有一个条件的时候
			if (linkstr != null) {
				linkstr = linkstr.replaceAll(sa.toString(), sb.toString());
			}
			i++;
		}

		return linkstr;
	}

	/**
	 * 通过附加条件生成相应的hql
	 * 
	 * @param apptype
	 *            应用类型
	 * @param objClass
	 *            需要进行通用查询的类
	 * @param inMap
	 *            主要调用其中的附加条件
	 * @param retvalues
	 *            key为 “values”的绑定变量值数组
	 * @return 自动形成的hql
	 */
	public static String genHqlbyexconds(String apptype, Class objClass,
			HashMap<String, Object> inMap, HashMap<String, Object[]> retvalues) {

		List<Object> vallist = new ArrayList<Object>();
		if (apptype == null) {
			throw new RuntimeException("没有传入应用类型，无法进行记录数查询计算！");
		}
		String hql = null;
		ExCond[] ExConds = null;
		String excondString = null;
		if (inMap.get(Param.ExCondition.ExCondition_key) != null) {
			ExConds = (ExCond[]) inMap.get(Param.ExCondition.ExCondition_key);
			// 入参如果是带in的函数，长度会增加
			// vallist=new Object[ExConds.length];
			excondString = DaoTools.excondstr(
					(ExCond[]) inMap.get(Param.ExCondition.ExCondition_key),
					(String) inMap.get(Param.ExCondLinkStr.ExCondlinkstr_key),
					vallist);
			excondString = "where " + excondString;

		}
		Long count = 0L;
		if (objClass != null) {
			hql = "from " + objClass.getName();
			if (excondString != null) {
				hql = hql + " " + excondString;
			}
			// count=hibernateDAO.getCountByQuery(hql,vallist);
		}
		retvalues.put("values", vallist.toArray());

		return hql;

	}

	/**
	 * 通过附加条件生成相应的hql
	 * 
	 * @param objClass
	 *            需要进行通用查询的类
	 * @param exConds
	 *            附加条件
	 * @param linkstr
	 *            条件组合方式
	 * @param orderStr
	 *            排序
	 * @param returnValList
	 *            返回变量值数组
	 * @return
	 */
	public static String genHqlbyexconds(Class objClass, ExCond[] exConds,
			String linkstr, String orderStr, List<Object> returnValList) {
		String hql = null;
		String excondString = null;
		if (exConds != null && exConds.length > 0) {
			excondString = DaoTools.excondstr(exConds, linkstr, returnValList);
			excondString = " where " + excondString;
		}
		if (objClass != null) {
			hql = "from " + objClass.getName();
			if (excondString != null) {
				hql = hql + " " + excondString;
			}
			if (!CommonTool.isNullOrEmpty(orderStr))
				hql = hql + " order by " + orderStr;
		}
		return hql;
	}

	/**
	 * 将web传入的串如果是in条件拆分成值数组
	 * 
	 * @param conbinestr
	 *            操作串
	 * @param type
	 *            转型类型
	 * @param valuestr
	 *            值数组
	 * @return
	 */
	public static Object parseWebInObj(String conbinestr, String aaaString,
			String valuestr) {
		// 只有单值的时候
		if (valuestr.indexOf(",") < 0) {
			if (aaaString.contains("Integer")) {
				return Integer.parseInt(valuestr);
			} else if (aaaString.contains("Long")) {
				return Long.parseLong(valuestr);
			} else if (aaaString.contains("Float")) {
				return Float.parseFloat(valuestr);
			} else if (aaaString.contains("Double")) {
				return Double.parseDouble(valuestr);
			} else if (aaaString.contains("Timestamp")) {
				if (!valuestr.contains(":")) {
					valuestr += " 00:00:00";
				}
				return (DaoTools.tgDateToTimestamp(DaoTools.tgStringToCalender(
						valuestr, "-", ":")));
			} else if (aaaString.contains("BigDecimal")) {
				return BigDecimal.valueOf(Double.parseDouble(valuestr));
			} else if (aaaString.contains("Date")) {
				if (!valuestr.contains(":")) {
					valuestr += " 00:00:00";
				}
				return DaoTools.tgStringToCalender(valuestr, "-", ":")
						.getTime();
			} else if (aaaString.contains("Byte")) {

				if (valuestr.matches("\\d*")) {
					// 数字

					return Byte.parseByte(valuestr);
				} else if (valuestr.toLowerCase().equals("true")) {
					return Byte.parseByte("1");

				} else {
					return Byte.parseByte("0");
				}

			} else if (aaaString.contains("Boolean")) {
				if (valuestr.matches("\\d*")) {
					// 数字
					if (Integer.parseInt(valuestr) > 0) {
						return true;
					} else {
						return false;
					}
				}
				return Boolean.parseBoolean(valuestr);
			} else {
				return valuestr;
			}
		} else {
			List<Object> objs = new ArrayList<Object>();
			if (aaaString.contains("Integer")) {
				while (valuestr.indexOf(",") > 0) {
					Integer integer = Integer.parseInt(valuestr.substring(0,
							valuestr.indexOf(",")));
					objs.add(integer);
					valuestr = valuestr.substring(valuestr.indexOf(",") + 1);
				}
				objs.add(Integer.parseInt(valuestr));
			} else if (aaaString.contains("Long")) {
				while (valuestr.indexOf(",") > 0) {
					Long integer = Long.parseLong(valuestr.substring(0,
							valuestr.indexOf(",")));
					objs.add(integer);
					valuestr = valuestr.substring(valuestr.indexOf(",") + 1);
				}
				objs.add(Long.parseLong(valuestr));
			} else if (aaaString.contains("Float")) {
				while (valuestr.indexOf(",") > 0) {
					Float integer = Float.parseFloat(valuestr.substring(0,
							valuestr.indexOf(",")));
					objs.add(integer);
					valuestr = valuestr.substring(valuestr.indexOf(",") + 1);
				}
				objs.add(Float.parseFloat(valuestr));
			} else if (aaaString.contains("Double")) {
				while (valuestr.indexOf(",") > 0) {
					Double integer = Double.parseDouble(valuestr.substring(0,
							valuestr.indexOf(",")));
					objs.add(integer);
					valuestr = valuestr.substring(valuestr.indexOf(",") + 1);
				}
				objs.add(Double.parseDouble(valuestr));
			} else if (aaaString.contains("Timestamp")) {
				while (valuestr.indexOf(",") > 0) {
					Timestamp ts = DaoTools.tgDateToTimestamp(DaoTools
							.tgStringToCalender(valuestr.substring(0,
									valuestr.indexOf(",")), "-", ":"));
					objs.add(ts);
					valuestr = valuestr.substring(valuestr.indexOf(",") + 1);
				}
				objs.add(DaoTools.tgDateToTimestamp(DaoTools
						.tgStringToCalender(valuestr, "-", ":")));
			} else if (aaaString.contains("Date")) {
				while (valuestr.indexOf(",") > 0) {
					Date dt = DaoTools.tgCalendarTodate(DaoTools
							.tgStringToCalender(valuestr.substring(0,
									valuestr.indexOf(",")), "-", ":"));
					objs.add(dt);
					valuestr = valuestr.substring(valuestr.indexOf(",") + 1);
				}
				objs.add(DaoTools.tgCalendarTodate(DaoTools.tgStringToCalender(
						valuestr, "-", ":")));
			} else if (aaaString.contains("String")) {
				while (valuestr.indexOf(",") > 0) {
					String integer = valuestr.substring(0,
							valuestr.indexOf(","));
					objs.add(integer);
					valuestr = valuestr.substring(valuestr.indexOf(",") + 1);
				}
				objs.add(valuestr);
			} else if (aaaString.contains("Byte")) {

				return Byte.parseByte(valuestr);
			} else if (aaaString.contains("Boolean")) {
				while (valuestr.indexOf(",") > 0) {
					Boolean integer = Boolean.parseBoolean(valuestr.substring(
							0, valuestr.indexOf(",")));
					objs.add(integer);
					valuestr = valuestr.substring(valuestr.indexOf(",") + 1);
				}
				objs.add(Boolean.parseBoolean(valuestr));
			}
			return objs.toArray();

		}

	}

	public static Long getLongValueFromDB(Object object) {
		if (object == null)
			return null;
		if (object instanceof BigInteger)
			return ((BigInteger) object).longValue();
		else if (object instanceof BigDecimal)
			return ((BigDecimal) object).longValue();
		else if (object instanceof Byte)
			return new Long((Byte) object);
		else if (object instanceof Integer)
			return new Long((Integer) object);
		else if (object instanceof Double)
			return ((Double) object).longValue();
		else
			return Long.valueOf(object.toString());
	}
	
	public static Double getDoubleValueFromDB(Object object) {
		if (object == null)
			return null;
		if (object instanceof BigInteger)
			return ((BigInteger) object).doubleValue();
		else if (object instanceof BigDecimal)
			return ((BigDecimal) object).doubleValue();
		else if (object instanceof Byte)
			return new Double((Byte) object);
		else if (object instanceof Integer)
			return new Double((Integer) object);
		else if (object instanceof Double)
			return ((Double) object);
		else
			return null;
	}
}
