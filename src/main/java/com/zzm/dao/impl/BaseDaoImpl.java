package com.zzm.dao.impl;

import com.zzm.dao.HibernateDAO;
import com.zzm.dao.IBaseDao;
import com.zzm.hd.dto.Page;
import com.zzm.hd.param.ExCond;
import com.zzm.hd.param.Param;
import com.zzm.hd.tools.DaoTools;
import com.zzm.hd.util.CommonTool;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.sql.DatabaseMetaData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.criterion.Criterion;
import org.hibernate.transform.ResultTransformer;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.orm.hibernate5.HibernateCallback;

/**
 * 数据库底层封装，封装hibernate与jdbc操作
 * 
 * @author yang ning
 * 
 */
public class BaseDaoImpl implements IBaseDao {
	protected HibernateDAO hibernateDAO;
	protected JdbcTemplate jdbcTemplateDAO;
	protected Logger log = Logger.getLogger(this.getClass());

	public HibernateDAO getHibernateDAO() {
		return hibernateDAO;
	}

	public void setHibernateDAO(HibernateDAO hibernateDAO) {
		this.hibernateDAO = hibernateDAO;
	}

	public JdbcTemplate getJdbcTemplateDAO() {
		return jdbcTemplateDAO;
	}

	public void setJdbcTemplateDAO(JdbcTemplate jdbcTemplateDAO) {
		this.jdbcTemplateDAO = jdbcTemplateDAO;
	}

	private static Properties properties = null;

	public int getMaxCount(String key) {
		long startTime = System.currentTimeMillis();

		File directory = new File("..");
		String path = directory.getAbsolutePath();
		path = path.substring(0, path.length() - 2);
		// String path=null;
		/*
		 * try { path = directory.getCanonicalPath(); } catch (IOException e2) {
		 * // TODO Auto-generated catch block e2.printStackTrace(); }
		 */
		int maxcount = 60000;
		if (properties != null) {
			String value = properties.getProperty(key);
			if (value != null) {
				maxcount = Integer.parseInt(value);
			}
			long endTime = System.currentTimeMillis();
			log.debug(" GenHDDao.getMaxCount: " + (endTime - startTime) + " (ms) ");
			return maxcount;
		} else // 重新读取配置文件，加载属性
		{
			InputStream input = null;
			Reader reader = null;
			try {
				// System.out.println("配置文件的目录是："+path+"\\config.properties");
				input = new FileInputStream(path + "\\config.properties");
				reader = new InputStreamReader(input, "UTF-8");
				if (properties == null) {
					properties = new Properties();
					properties.load(reader);
				}
			} catch (FileNotFoundException e) {
				// /* 如果配置文件不存在，则创建之 */
				// File file = new File(path+"\\config.properties");
				// try {
				// file.createNewFile();
				// } catch (IOException e1) {
				// e1.printStackTrace();
				// }
				long endTime = System.currentTimeMillis();
				log.debug(" GenHDDao.getMaxCount: " + (endTime - startTime) + " (ms) ");
				return maxcount;

			} catch (IOException e) {
				long endTime = System.currentTimeMillis();
				log.debug(" GenHDDao.getMaxCount: " + (endTime - startTime) + " (ms) ");
				return maxcount;
			}
			try {
				if (reader != null) {
					reader.close();
				}
				if (input != null) {
					input.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
			if (properties != null) {
				String value = properties.getProperty(key);
				if (value != null) {
					long endTime = System.currentTimeMillis();
					log.debug(" GenHDDao.getMaxCount: " + (endTime - startTime) + " (ms) ");
					maxcount = Integer.parseInt(value);
				}
			}

		}
		long endTime = System.currentTimeMillis();
		log.debug(" GenHDDao.getMaxCount: " + (endTime - startTime) + " (ms) ");
		return maxcount;
	}

	public Boolean isQueryCountLimit(String key) {
		long startTime = System.currentTimeMillis();
		if (properties == null) // 重新读取配置文件，加载属性
		{
			properties = new Properties();
			File directory = new File("..");
			String path = directory.getAbsolutePath();
			path = path.substring(0, path.length() - 2);
			InputStream input = ClassLoader.getSystemResourceAsStream("config.properties");
			if (input == null) {
				try {
					input = new FileInputStream(path + "\\config.properties");
				} catch (FileNotFoundException e) {
					// System.out.println("No config.properties for GenHDDao");
					// e.printStackTrace();
				}
			}
			if (input != null) {
				Reader reader = null;
				try {
					reader = new InputStreamReader(input, "UTF-8");
					properties.load(reader);
				} catch (UnsupportedEncodingException e1) {
					e1.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
				try {
					if (reader != null)
						reader.close();
					input.close();
				} catch (IOException e) {
				}
			}
		}
		if (properties != null) {
			String value = properties.getProperty(key);
			long endTime = System.currentTimeMillis();
			log.debug(" GenHDDao.isQueryCountLimit: " + (endTime - startTime) + " (ms) ");
			return ConvertToBoolean(value, true);
		} else {
			long endTime = System.currentTimeMillis();
			log.debug(" GenHDDao.isQueryCountLimit: " + (endTime - startTime) + " (ms) ");
			return true;
		}
	}

	/**
	 * 将对象转换成布尔�?
	 * 
	 * @param str
	 *            转换的对�?
	 * @param errorV
	 *            出错后的数据
	 * @return
	 */
	public static boolean ConvertToBoolean(Object str, boolean errorV) {
		boolean v = errorV;
		try {
			if (str != null && !CommonTool.isNullOrEmpty(str.toString()))
				v = Boolean.parseBoolean(str.toString());
		} catch (Exception er) {
			v = errorV;
		}

		return v;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public Object getEntity(Class classType, Serializable id) {
		return hibernateDAO.get(classType, id);
	}

	@Override
	public Serializable save(Object entity) {
		return hibernateDAO.save(entity);
	}

	@SuppressWarnings("rawtypes")
	@Override
	public List loadAll(Class entityClass) {
		return hibernateDAO.loadAll(entityClass);
	}

	@Override
	public void update(Object entity) {
		long startTime = System.currentTimeMillis();
		hibernateDAO.update(entity);
		long endTime = System.currentTimeMillis();
		log.debug(" GenHDDao.update: " + (endTime - startTime) + " (ms) ");
	}

	@Override
	public void delete(Object entity) {
		long startTime = System.currentTimeMillis();
		hibernateDAO.delete(entity);
		long endTime = System.currentTimeMillis();
		log.debug(" GenHDDao.delete: " + (endTime - startTime) + " (ms) ");
	}

	@SuppressWarnings("rawtypes")
	@Override
	public List findByProperty(Class entityClass, String[] propertyNames, Object[] parameter) {
		return hibernateDAO.findByProperty(entityClass, propertyNames, parameter);
	}

	@Override
	public List find(String hql, Object... parameters) {
		return hibernateDAO.find(hql, parameters);
	}

	@Override
	public List find(String hql, String[] paramNames, Object[] values) {
		return hibernateDAO.find(hql, paramNames, values);
	}

	@SuppressWarnings("rawtypes")
	@Override
	public List findByExample(Object entity) {
		return hibernateDAO.findByExample(entity);
	}

	@SuppressWarnings("rawtypes")
	@Override
	public int batchExcute(List entityList, String opType) {
		return hibernateDAO.batchExcute(entityList, opType);
	}

	/**
	 * 批量执行DML
	 * 
	 * @author yangning
	 * @param list是具体PO类的列表
	 *            使用范例： List <WdsHydroElements> list;
	 * @param optype
	 *            执行参数 使用范例： Param.OpType.OP_DELETE
	 * 
	 */
	@SuppressWarnings("rawtypes")
	public int batchDml(List list, String optype) {
		if (list == null || list.size() == 0)
			return 0;
		return hibernateDAO.batchExcute(list, optype);
	}

	/**
	 * 将解析过的条件列表拼装成hibernate Criterion
	 * 
	 * @author yangning
	 * @param objlist
	 *            List[]数据列表， 使用范例： List<Object>
	 *            timeList=DaoTools.parseTimetoObject(inmap,"id.time");
	 *            List<Object>
	 *            idList=DaoTools.parseIdtoObject(inmap,getSpecAppIdName());
	 *            List[] paramlist=new List[]{timeList,idList};
	 * @return hibernate Criterion
	 */
	@SuppressWarnings("rawtypes")
	public Criterion[] combCreterion(List[] objlist) {
		long startTime = System.currentTimeMillis();

		ArrayList<String> propertyNames = new ArrayList<String>();
		ArrayList<String> condition = new ArrayList<String>();
		ArrayList<Object> propertyValue = new ArrayList<Object>();

		for (List litemp : objlist) {
			for (int i = 0; i < litemp.size(); i = i + 3) {
				propertyNames.add(litemp.get(i).toString());
				condition.add(litemp.get(i + 1).toString());
				propertyValue.add(litemp.get(i + 2));

			}
		}

		String[] proStr = new String[propertyNames.size()];
		propertyNames.toArray(proStr);

		String[] condStr = new String[condition.size()];
		condition.toArray(condStr);

		Object[] proObj = new Object[propertyValue.size()];
		propertyValue.toArray(proObj);
		Criterion[] criterions = hibernateDAO.createCriterion(proStr, condStr, proObj);
		long endTime = System.currentTimeMillis();
		log.debug(" GenHDDao.combCreterion: " + (endTime - startTime) + " (ms) ");
		return criterions;
	}

	/**
	 * 获取时段整编运行历史数据函数 inmap为必须参数, 不能为null, inmap中 Param.AppType.App_Type_Key
	 * 参数不能为null, inmap中 Param.IdType.LONG_IDARRAY或者Param.IdType.STR_IDARRAY
	 * 参数不能为null, inmap中 Param.RunDataType.Rundata_Type_Key参数不能为null,
	 * 
	 * @author yangning
	 * @author caijie
	 * @param inmap
	 *            (Param.TimeType : 时间条件) 参数的使用相见Param.TimeType类
	 *            使用范例：inmap.put(Param.TimeType.EQ_BTIME, calendar);
	 *            (Param.IdType : 测点条件) 参数的使用相见Param.IdType类
	 *            使用范例：inmap.put(Param.IdType.LONG_IDARRAY,idList);
	 *            (Param.RunDataType.Rundata_Type_Key : 测点类型)
	 *            参数的使用相见Param.RunDataType.Rundata_Type_Key类
	 *            使用范例：inmap.put(Param.RunDataType.Rundata_Type_Key,Param.RunDataType.RUN_RT);
	 * @return 特征值序列 HashMap<Long, List<SpanData>> Long 对应ID, 特征值序列
	 *         HashMap<Long, List<SpanData>> SpanData senid 对应站号, 特征值序列
	 *         HashMap<Long, List<SpanData>> SpanData time 对应记录对应时间, 特征值序列
	 *         HashMap<Long, List<SpanData>> SpanData avgv 对应平均值, 特征值序列
	 *         HashMap<Long, List<SpanData>> SpanData v 对应数值, 特征值序列
	 *         HashMap<Long, List<SpanData>> SpanData minv 对应最小值, 特征值序列
	 *         HashMap<Long, List<SpanData>> SpanData maxt 对应最大值时间, 特征值序列
	 *         HashMap<Long, List<SpanData>> SpanData mint 对应最小值时间, 特征值序列
	 *         HashMap<Long, List<SpanData>> SpanData s 对应测值品质, 特征值序列
	 *         HashMap<Long, List<SpanData>> SpanData maxs 对应最大值品质, 特征值序列
	 *         HashMap<Long, List<SpanData>> SpanData mins 对应最小值品质, 特征值序列
	 *         HashMap<Long, List<SpanData>> SpanData avgs 对应平均值品质,
	 */
	/**
	 * 直接执行sql语句 inmap为必须参数, 不能为null, inmap中 Param.ExParamType.EX_SQL 参数不能为null,
	 * 
	 * @author yangning
	 * @param inmap
	 *            (Param.ExParamType.EX_SQL : sql语句)
	 *            参数的使用相见Param.ExParamType.EX_SQL类
	 *            使用范例：inmap.put(Param.ExParamType.EX_SQL, string);
	 *            (Param.TimeType.EQ_BTIME : 绑定开始时间条件)
	 *            参数的使用相见Param.TimeType.EQ_BTIME类
	 *            使用范例：inmap.put(Param.TimeType.EQ_BTIME,calendar);
	 *            (Param.TimeType.EQ_ETIME : 绑定结束时间条件)
	 *            参数的使用相见Param.TimeType.EQ_ETIME类
	 *            使用范例：inmap.put(Param.TimeType.EQ_ETIME,calendar);
	 *            (Param.ExParamType.EX_SLIPWIN : 结果个数限制)
	 *            参数的使用相见Param.ExParamType.EX_SLIPWIN类
	 *            使用范例：inmap.put(Param.ExParamType.EX_SLIPWIN,10);
	 *            (Param.IdType.STR_IDARRAY : 绑定字段名或字符串侧点名)
	 *            参数的使用相见Param.IdType.STR_IDARRAY类
	 *            使用范例：inmap.put(Param.IdType.STR_IDARRAY,COLNAME);
	 *            (Param.IdType.LONG_IDARRAY : 绑定测点号)
	 *            参数的使用相见Param.IdType.LONG_IDARRAY类
	 *            使用范例：inmap.put(Param.IdType.LONG_IDARRAY,1111111);
	 *            其中：EQ_BTIME，EQ_ETIME可以压入需要带入的时间，SQL串中的TB，TE将被当做时间保留字会被[EQ_BTIME]，[EQ_ETIME]的值替换
	 *            LONG_IDARRAY可以压入Long型站号组，[IDARRAY]将被当做保留字会被LONG_IDARRAY的值替换
	 *            STR_IDARRAY可以压入String型站号组，[STRIDARRAY]将被当做保留字会被STR_IDARRAY的值替换
	 *            EX_INT如果压入1，则STR_IDARRAY中存放的是需要替换的Column的名称 EX_SLIPWIN如果压入，则替换
	 * @return List 其中存放的是Object[]按查询字段存放
	 */
	@Override
	@SuppressWarnings("rawtypes")
	public List execSql(HashMap<String, Object> inmaplocal) {
		long startTime = System.currentTimeMillis();
		HashMap<String, Object> inmap = DaoTools.changeTimeForSql(inmaplocal);

		/*
		 * DaoTools.checkParaExist(log, inmaplocal, "EX_SQL"); //
		 * 时间做个转换，sql只接收EQ_BTIME 和EQ_ETIME String sqlstr=(String)
		 * inmap.get(Param.ExParamType.EX_SQL); //记录绑定变量位置 int pos=0;
		 * //绑定变量位置和值的数组 HashMap<Integer,Object> ar=new HashMap<Integer,
		 * Object>(); //分解SQl串，根据分解结果进行变量和值的绑定 StringBuffer sbBuffer=new
		 * StringBuffer(); String tempstr=sqlstr; if(!tempstr.contains("[")) {
		 * sbBuffer=sbBuffer.append(sqlstr); } else { //把左括号带着
		 * sbBuffer=sbBuffer.append(sqlstr.substring(0, sqlstr.indexOf("["))); }
		 * int lastoverpos=0; while (tempstr.contains("[")) { int
		 * i=tempstr.indexOf("["); int j=tempstr.indexOf("]");
		 * if(lastoverpos!=0) { sbBuffer.append(tempstr.substring(0,i)); }
		 * String context=tempstr.substring(i+1,j); if(context.equals("TB")) {
		 * context="[TB]"; //如果存在开始时间绑定 Calendar bt=(Calendar)
		 * inmap.get(Param.TimeType.EQ_BTIME); if(bt!=null)
		 * pos=DaoTools.bindTime( log, bt, context,0,ar, pos,inmap) ;
		 * sbBuffer.append((String) inmap.get(Param.ExParamType.EX_SQL));
		 * 
		 * } else if(context.equals("TE")){ context="[TE]"; //如果存在结束时间绑定
		 * Calendar et=(Calendar) inmap.get(Param.TimeType.EQ_ETIME);
		 * if(et!=null) pos=DaoTools.bindTime( log, et, context,1,ar, pos,inmap)
		 * ; sbBuffer.append((String) inmap.get(Param.ExParamType.EX_SQL));
		 * 
		 * } else if(context.equals("ROWCOUNT")){ context="[ROWCOUNT]";
		 * //如果存在取数条数限制 Integer rownum=(Integer)
		 * inmap.get(Param.ExParamType.EX_SLIPWIN); if(rownum!=null)
		 * pos=DaoTools.bindCount( log, rownum, context,ar, pos,inmap) ;
		 * sbBuffer.append((String) inmap.get(Param.ExParamType.EX_SQL));
		 * 
		 * } else if(context.equals("COLUMNNAME")){ context="[COLUMNNAME]";
		 * //如果存在列名是传入的情况 Object[] strIdarray; strIdarray=(Object[])
		 * inmap.get(Param.IdType.STR_IDARRAY); Integer clounmflag=(Integer)
		 * inmap.get(Param.ExParamType.EX_INT); if(clounmflag!=null &&
		 * strIdarray.length>0) DaoTools.bindColunm( log, strIdarray,
		 * context,inmap) ; sbBuffer.append((String)
		 * inmap.get(Param.ExParamType.EX_SQL)); } else
		 * if(context.equals("IDARRAY")){ context="[IDARRAY]"; //如果存在Long形站号绑定
		 * Object[] Idarray; Idarray=(Object[])
		 * inmap.get(Param.IdType.LONG_IDARRAY);
		 * if(Idarray!=null&&Idarray.length>0) pos=DaoTools.bindLongID(
		 * log,Idarray,context, ar, pos,inmap) ; sbBuffer.append((String)
		 * inmap.get(Param.ExParamType.EX_SQL)); } else
		 * if(context.equals("STRIDARRAY")){ context="[STRIDARRAY]";
		 * //如果存在String形站号绑定 Object[] strIdarray; strIdarray=(Object[])
		 * inmap.get(Param.IdType.STR_IDARRAY);
		 * if(strIdarray!=null&&strIdarray.length>0) pos=DaoTools.bindStrID(
		 * log,strIdarray,context, ar, pos,inmap) ; sbBuffer.append((String)
		 * inmap.get(Param.ExParamType.EX_SQL)); } else
		 * if(context.equals("S0")){ context="[S0]"; //如果存在String形站号绑定
		 * sbBuffer.append(context); } else if(context.equals("S1")){
		 * context="[S1]"; //如果存在String形站号绑定 sbBuffer.append(context); } else
		 * if(context.equals("S2")){ context="[S2]"; //如果存在String形站号绑定
		 * sbBuffer.append(context); }
		 * 
		 * tempstr=tempstr.substring(j+1); lastoverpos=j+1; } //最后一个替换要处理
		 * if(tempstr!=null&&lastoverpos!=0) { sbBuffer.append(tempstr); }
		 * String sqlstrlast=sbBuffer.toString();
		 * if(inmap.get(Param.ExParamType.EX_STRING)!=null) { String
		 * repStr=(String) inmap.get(Param.ExParamType.EX_STRING); sqlstrlast =
		 * sqlstrlast.replace("[S0]",repStr ); }
		 * if(inmap.get(Param.ExParamType.EX_STRING1)!=null) { String
		 * repStr=(String) inmap.get(Param.ExParamType.EX_STRING1); sqlstrlast =
		 * sqlstrlast.replace("[S1]",repStr ); }
		 * if(inmap.get(Param.ExParamType.EX_STRING2)!=null) { String
		 * repStr=(String) inmap.get(Param.ExParamType.EX_STRING2); sqlstrlast =
		 * sqlstrlast.replace("[S2]",repStr ); }
		 * 
		 * inmap.put(Param.ExParamType.EX_SQL,sqlstrlast);
		 * 
		 * if(inmap.get(Param.ExCondition.ExCondition_key)!=null) {
		 * pos=DaoTools.bindExCond(log, sqlstr, (ExCond[])
		 * inmap.get(Param.ExCondition.ExCondition_key), ar, pos,inmap) ; }
		 * sqlstr=(String) inmap.get(Param.ExParamType.EX_SQL); Object[] obj=new
		 * Object[ar.size()]; for(int i=0;i<pos;i++) { obj[i]=ar.get(i+1); }
		 */
		Object[] obj = bindParamForSqlOrHql(inmaplocal);
		String sqlstr = (String) inmaplocal.get(Param.ExParamType.EX_SQL);

		try {
			List rows = null;
			// 判断一下，当查询需要的三个条件都有的时候执行分页查询
			if (inmap.get(Param.ExParamType.EX_INT) != null && inmap.get(Param.ExParamType.EX_SLIPWIN) != null
					&& inmap.get(Param.ExParamType.EX_SLIPSTEP) != null) {
				Boolean isHql = (Boolean) inmap.get("isHql");
				if (isHql != null && isHql) {
					Page page = hibernateDAO.findPageByQuery((Integer) inmap.get(Param.ExParamType.EX_INT),
							sqlstr, (Integer) inmap.get(Param.ExParamType.EX_SLIPWIN),
							(Integer) inmap.get(Param.ExParamType.EX_SLIPSTEP), obj);
					rows = page.getItems();
				} else {
					try {
						Page page = hibernateDAO.findPageBySql((Integer) inmap.get(Param.ExParamType.EX_INT),
								sqlstr, (Integer) inmap.get(Param.ExParamType.EX_SLIPWIN),
								(Integer) inmap.get(Param.ExParamType.EX_SLIPSTEP), obj);
						rows = page.getItems();

					}
					// 如果sql不行执行一下hql分页
					catch (Exception e) {
						Page page = hibernateDAO.findPageByQuery((Integer) inmap.get(Param.ExParamType.EX_INT),
								sqlstr, (Integer) inmap.get(Param.ExParamType.EX_SLIPWIN),
								(Integer) inmap.get(Param.ExParamType.EX_SLIPSTEP), obj);
						rows = page.getItems();
					}
				}

			} else {
				int count = 0;
				try {

					if (inmaplocal.get(Param.TimeType.EQ_BTIME) != null
							&& inmaplocal.get(Param.TimeType.EQ_ETIME) != null) {
						SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

						Calendar cab = (Calendar) inmaplocal.get(Param.TimeType.EQ_BTIME);
						String formatStr = dateFormat.format(cab.getTime());
						System.out.println("sql开始执行的时间：" + formatStr);
						Calendar cae = (Calendar) inmaplocal.get(Param.TimeType.EQ_ETIME);
						formatStr = dateFormat.format(cae.getTime());
						System.out.println("sql结束执行的时间：" + formatStr);
						long spans = (cae.getTimeInMillis() - cab.getTimeInMillis()) / 1000;
						// gis流域图取数据不再读取总数
						cab = Calendar.getInstance();
						if (spans > 86400 && inmap.get(Param.ExParamType.EX_PROGRAM) == null
								&& isQueryCountLimit("sqlQueryCountLimit")) {
							count = hibernateDAO.getCountBySql(sqlstr, obj);
						}
						cae = Calendar.getInstance();
						spans = (cae.getTimeInMillis() - cab.getTimeInMillis()) / 1000;
						System.out.println("执行耗时:   " + spans);
					} else {
						Calendar cab = (Calendar) inmaplocal.get(Param.TimeType.EQ_BTIME);
						// gis流域图取数据不再读取总数
						if ((inmap.get(Param.ExParamType.EX_PROGRAM) == null)
								&& isQueryCountLimit("sqlQueryCountLimit")) {
							count = hibernateDAO.getCountBySql(sqlstr, obj);
						}
						// Calendar cae=Calendar.getInstance();
						// long
						// spans=(cae.getTimeInMillis()-cab.getTimeInMillis())/1000;
						// System.out.println("执行耗时: "+spans);

					}

				} catch (Exception e) {
					// e.printStackTrace();
					count = 0;
				}
				// 先替换sql变成select count(*)的样式
				/*
				 * if(sqlstr.toUpperCase().indexOf("SELECT")>=0) {
				 * 
				 * StringBuffer countsb=new StringBuffer();
				 * countsb.append("select count(*) "); StringBuffer
				 * sqlstrnew=new StringBuffer();
				 * sqlstrnew.append(sqlstr.substring(sqlstr.toUpperCase().
				 * indexOf("FROM"))); countsb.append(sqlstrnew); List countList
				 * = null;
				 * 
				 * try { //当查询条数的sql执行不成功时，不做限制
				 * countList=hibernateDAO.queryByNativeSql(countsb.toString(),
				 * obj);
				 * 
				 * 
				 * } catch (Exception e) { countList = null; }
				 * if(countList!=null) { Long count=0L; for(int
				 * kk=0;kk<countList.size();kk++) {
				 * if(countList.get(countList.size()-1) instanceof Integer ) {
				 * count +=Long.parseLong( ((Integer)
				 * countList.get(countList.size()-1)).toString()); } else {
				 * count +=Long.parseLong( ((BigDecimal)
				 * countList.get(countList.size()-1)).toString());
				 * 
				 * } } if(count>getMaxCount("maxcount")) { String
				 * logstr="获取的记录数大于系统能够提供的最大数据条数，请检查条件"; log.error(logstr);
				 * throw new RuntimeException(logstr); } } }
				 */
				if (count > getMaxCount("maxcount")) {
					String logstr = "获取的记录数大于系统能够提供的最大数据条数，请检查条件" + count + ">" + getMaxCount("maxcount");
					log.error(logstr);
					throw new RuntimeException(logstr);
				}
				// long startTime = System.currentTimeMillis();
				rows = hibernateDAO.queryByNativeSql(sqlstr, obj);
				// long endTime = System.currentTimeMillis();
				// System.out.println("--------------------------"+sqlstr);
				// System.out.println("------------------hibernateDAO.queryByNativeSql
				// sendtime :" +(endTime-startTime));
				// List tests=hibernateDAO.queryByNativeSql(" select
				// senid,COUNT(*),AVG(AVGV) from wds.hourdb where
				// senid=301083401000283 and TIME>'2013-03-10 11:00:00' and
				// TIME<'2013-03-20 11:00:00' GROUP BY senid", null);
				// System.out.println(tests.size());
			}
			// 增加分页查询功能20130427
			long endTime = System.currentTimeMillis();
			log.debug(" GenHDDao.execSql: " + (endTime - startTime) + " (ms) ");
			return rows;
		} catch (RuntimeException e) {
			// System.out.println(e);
			log.error("查询无法执行！" + e);
		}
		long endTime = System.currentTimeMillis();
		log.debug(" GenHDDao.execSql: " + (endTime - startTime) + " (ms) ");
		return null;
	}

	/**
	 * 执行DML绑定批量操作，自定义SQL
	 * 
	 * @param inmap
	 * @return
	 */
	@Override
	public int execDmlSql(HashMap<String, Object> inmap) {
		final long startTime = System.currentTimeMillis();
		DaoTools.checkParaExist(log, inmap, "EX_SQL");
		// String sqlstr=(String) inmap.get(Param.ExParamType.EX_SQL);
		final Object[] ar = bindParamForSqlOrHql(inmap);
		String sqlstr = (String) inmap.get(Param.ExParamType.EX_SQL);
		/*
		 * //记录绑定变量位置 int pos=0; //绑定变量位置和值的数组 final HashMap<Integer,Object>
		 * ar=new HashMap<Integer, Object>(); //分解SQl串，根据分解结果进行变量和值的绑定 String
		 * tempstr=sqlstr; while (tempstr.contains("[")) { sqlstr=(String)
		 * inmap.get(Param.ExParamType.EX_SQL); int i=tempstr.indexOf("["); int
		 * j=tempstr.indexOf("]"); String context=tempstr.substring(i+1,j);
		 * if(context.equals("TB")) { //如果存在开始时间绑定 Calendar bt=(Calendar)
		 * inmap.get(Param.TimeType.EQ_BTIME); if(bt!=null)
		 * pos=DaoTools.bindTime( log, bt, sqlstr,0,ar, pos,inmap) ; } else
		 * if(context.equals("TE")){ //如果存在结束时间绑定 Calendar et=(Calendar)
		 * inmap.get(Param.TimeType.EQ_ETIME); if(et!=null)
		 * pos=DaoTools.bindTime( log, et, sqlstr,1,ar, pos,inmap) ;
		 * 
		 * } else if(context.equals("ROWCOUNT")){ //如果存在取数条数限制 Integer
		 * rownum=(Integer) inmap.get(Param.ExParamType.EX_SLIPWIN);
		 * if(rownum!=null) pos=DaoTools.bindCount( log, rownum, sqlstr,ar,
		 * pos,inmap) ; } else if(context.equals("COLUMNNAME")){ //如果存在列名是传入的情况
		 * Object[] strIdarray; strIdarray=(Object[])
		 * inmap.get(Param.IdType.STR_IDARRAY); Integer clounmflag=(Integer)
		 * inmap.get(Param.ExParamType.EX_INT); if(clounmflag!=null &&
		 * strIdarray.length>0) DaoTools.bindColunm( log, strIdarray,
		 * sqlstr,inmap) ;
		 * 
		 * } else if(context.equals("IDARRAY")){ //如果存在Long形站号绑定 Object[]
		 * Idarray; Idarray=(Object[]) inmap.get(Param.IdType.LONG_IDARRAY);
		 * if(Idarray.length>0) pos=DaoTools.bindLongID( log,Idarray,sqlstr, ar,
		 * pos,inmap) ;
		 * 
		 * } else if(context.equals("STRIDARRAY")){ //如果存在String形站号绑定 Object[]
		 * strIdarray; strIdarray=(Object[])
		 * inmap.get(Param.IdType.STR_IDARRAY); if(strIdarray.length>0)
		 * pos=DaoTools.bindStrID( log,strIdarray,sqlstr, ar, pos,inmap) ; }
		 * tempstr=tempstr.substring(j+1); }
		 * 
		 * sqlstr=(String) inmap.get(Param.ExParamType.EX_SQL);
		 * if(inmap.get(Param.ExCondition.ExCondition_key)!=null) {
		 * pos=DaoTools.bindExCond(log, sqlstr, (ExCond[])
		 * inmap.get(Param.ExCondition.ExCondition_key), ar, pos,inmap); }
		 */
		if (sqlstr.toUpperCase().contains("DELETE") || sqlstr.toUpperCase().contains("UPDATE")
				|| sqlstr.toUpperCase().contains("INSERT")) {
			int[] inta = null;
			if (!sqlstr.contains("?")) {
				jdbcTemplateDAO.execute(sqlstr);
			} else {

				inta = jdbcTemplateDAO.batchUpdate(sqlstr, new BatchPreparedStatementSetter() {

					@Override
					public void setValues(PreparedStatement arg0, int arg) throws SQLException {
						System.out.println("一共要批量执行的条数：" + (arg + 1));
						for (int i = 0; i < ar.length; i++) {
							if (ar[i] instanceof Long) {
								arg0.setLong(i + 1, (Long) ar[i]);
							} else if (ar[i] instanceof String) {
								try {
									arg0.setLong(i + 1, Long.valueOf((String) ar[i]));
								} catch (Exception e) {
									arg0.setString(i + 1, (String) ar[i]);
								}
							} else if (ar[i] instanceof GregorianCalendar) {
								Calendar aa = (Calendar) ar[i];
								Timestamp tp = new Timestamp(aa.getTime().getTime());
								arg0.setTimestamp(i + 1, tp);
							} else if (ar[i] instanceof Timestamp) {
								arg0.setTimestamp(i + 1, (Timestamp) ar[i]);
							}

						}
						/*
						 * for(int i=0;i<ar.size();i++) {
						 * if(ar.containsKey(i+1)) {
						 * if(ar.get(i+1).getClass()==Long.class) {
						 * arg0.setLong(i+1, (Long) ar.get(i+1)); } else
						 * if(ar.get(i+1).getClass()==String.class) {
						 * arg0.setString(i+1, (String) ar.get(i+1)); } else
						 * if(ar.get(i+1).getClass()==GregorianCalendar.class) {
						 * Calendar aa= (Calendar) ar.get(i+1); Timestamp tp=new
						 * Timestamp(aa.getTime().getTime());
						 * arg0.setTimestamp(i+1, tp); } else
						 * if(ar.get(i+1).getClass()==Timestamp.class) {
						 * arg0.setTimestamp(i+1, (Timestamp) ar.get(i+1)); } }
						 * 
						 * }
						 */
					}

					@Override
					public int getBatchSize() {
						long endTime = System.currentTimeMillis();
						log.debug(" GenHDDao.execDmlSql: " + (endTime - startTime) + " (ms) ");
						return 1;
					}

				});
				if (inta.length > 0) {
					long endTime = System.currentTimeMillis();
					log.debug(" GenHDDao.execDmlSql: " + (endTime - startTime) + " (ms) ");
					return inta[0];
				} else {
					long endTime = System.currentTimeMillis();
					log.debug(" GenHDDao.execDmlSql: " + (endTime - startTime) + " (ms) ");
					return 0;
				}
			}
			long endTime = System.currentTimeMillis();
			log.debug(" GenHDDao.execDmlSql: " + (endTime - startTime) + " (ms) ");
			return 0;
		}
		long endTime = System.currentTimeMillis();
		log.debug(" GenHDDao.execDmlSql: " + (endTime - startTime) + " (ms) ");
		return 0;
	}

	/**
	 * 获取实体类对应的表的列名称和中文说明
	 * 
	 * @param entityClass
	 *            实体类名称
	 * @return 列名为key，列说明为value的hashmap
	 */
	@Override
	public HashMap<String, String> getColumnData(Class entityClass) {
		long startTime = System.currentTimeMillis();
		String tableName;
		String[] strs = DaoTools.getPojoTableNameAndCol(entityClass, null);
		tableName = strs[1];
		HashMap<String, String> res = new HashMap<String, String>();
		try {
			// String sql = "select * from tab";
			String sql = "select COLUMN_NAME,COMMENTS FROM USER_COL_COMMENTS WHERE TABLE_NAME=?";
			List rows = hibernateDAO.queryByNativeSql(sql, tableName);
			Iterator iterator = rows.iterator();
			while (iterator.hasNext()) {
				Object[] result = (Object[]) iterator.next();
				if (result[0] == null)
					continue;
				if (result[1] == null)
					continue;
				String colname = ((String) result[0]).toLowerCase();
				// 判断一下是不是复合主键，如果是复合主键要有id.字段名传给hql查询才能正确执行
				if (!DaoTools.getIsMethodInPOJO(entityClass, colname)) {
					colname = "id." + colname;
				}
				String remarks = (String) result[1];
				res.put(colname, remarks);
			}

		} catch (Exception e) {
			try {
				DatabaseMetaData metaData = jdbcTemplateDAO.getDataSource().getConnection().getMetaData();
				ResultSet colRet = metaData.getColumns(null, "%", tableName, "%");
				while (colRet.next()) {
					String colname = colRet.getString("COLUMN_NAME").toLowerCase();
					if (!DaoTools.getIsMethodInPOJO(entityClass, colname)) {
						colname = "id." + colname;
					}
					String colType = colRet.getString("TYPE_NAME");
					String remarks = colRet.getString("REMARKS");
					String colDef = colRet.getString("COLUMN_DEF");
					res.put(colname, remarks);
				}
			} catch (Exception e1) {
				long endTime = System.currentTimeMillis();
				log.debug(" GenHDDao.getColumnData: " + (endTime - startTime) + " (ms) ");
				return res;
			}
			long endTime = System.currentTimeMillis();
			log.debug(" GenHDDao.getColumnData: " + (endTime - startTime) + " (ms) ");
			return res;
		}
		long endTime = System.currentTimeMillis();
		log.debug(" GenHDDao.getColumnData: " + (endTime - startTime) + " (ms) ");
		return res;
	}

	/**
	 * 针对通用类型的查询组合
	 * 
	 * @param entityClass
	 *            实体类名称
	 * @param inmap
	 *            查询条件在inmap的ExCondition和ExCondLinkStr中
	 * @return
	 */
	@Override
	public List execGenCondSql(Class entityClass, HashMap<String, Object> inmap) {
		// 当通用查询条件组合和组合串不为空时才可以进行此操作
		if (inmap.get(Param.ExCondition.ExCondition_key) != null
				&& inmap.get(Param.ExCondLinkStr.ExCondlinkstr_key) != null) {
			ExCond[] exConds = (ExCond[]) inmap.get(Param.ExCondition.ExCondition_key);
			String excondlinkstr = (String) inmap.get(Param.ExCondLinkStr.ExCondlinkstr_key);
			List parameters = new ArrayList<Object>();
			String pos[] = new String[exConds.length];
			for (int i = 0; i < exConds.length; i++) {
				// String excond=exConds.get(i).getCharctype().toString()+"
				// "+exConds.get(i).getCondition()+" ?";
				// String cond="c["+i+"]";
				String cond = "c" + i;
				String excond = exConds[i].getCharctype().toString() + " " + exConds[i].getCondition();
				if (exConds[i].getValues() instanceof Object[]) {
					Object[] objs = (Object[]) exConds[i].getValues();
					excond += " ( ";
					for (int j = 0; j < objs.length; j++) {
						excond += "?,";
					}
					excond = excond.substring(0, excond.length() - 1);
					excond += " )";
				} else {
					excond = excond + " ? ";
				}
				pos[i] = cond;
				excondlinkstr = excondlinkstr.replace(cond, excond);
				if (!(exConds[i].getValues() instanceof Object[])) {
					parameters.add(exConds[i].getValues());

				} else {
					Object[] objs = (Object[]) exConds[i].getValues();
					for (int j = 0; j < objs.length; j++) {
						parameters.add(objs[j]);
					}

				}
			}
			/*
			 * String[] strs= DaoTools.getPojoTableNameAndCol(entityClass,null);
			 * String
			 * sql="select * from "+strs[0]+"."+strs[1]+" where "+excondlinkstr;
			 * return jdbcTemplateDAO.queryForList(sql, parameters);
			 */
			String hql = " from " + entityClass.getSimpleName() + " where " + excondlinkstr;
			return hibernateDAO.find(hql, parameters.toArray());

		}
		return null;
	}

	/**
	 * 根据子类和父ID，寻找需要的子对象列表
	 * 
	 * @param childClass
	 *            子类
	 * @param parentId
	 *            父ID
	 * @return 子类List
	 */
	@SuppressWarnings("rawtypes")
	@Override
	public List getChildList(Class childClass, Long parentId) {
		long startTime = System.currentTimeMillis();
		List datalist = new ArrayList();
		// 获取该表全部数据
		if (parentId == null) {
			String hql = "from " + childClass.getSimpleName() + " order by ID asc";
			datalist = hibernateDAO.getHibernateTemplate().find(hql);
		}

		else {

			Criterion[] criterions = hibernateDAO.createCriterion(new String[] { "pId" }, new String[] { "=" },
					new Object[] { parentId });
			datalist = hibernateDAO.findByCriteria(childClass, new String[] { "id" }, new boolean[] { true },
					criterions);

		}
		long endTime = System.currentTimeMillis();
		log.debug(" GenHDDao.getChildList: " + (endTime - startTime) + " (ms) ");
		return datalist;
	}

	/**
	 * 获取任意数据表的条件查询的记录条数,
	 * 
	 * @author yangning
	 * @param objClass
	 * @param ExConds
	 * @return 记录条数
	 */
	@Override
	public Long getRecordCount(String apptype, Class objClass, HashMap<String, Object> inMap) {
		long startTime = System.currentTimeMillis();
		Long count = 0L;
		HashMap<String, Object[]> retvalues = new HashMap<String, Object[]>();
		String hql = "";
		if (inMap.get(Param.ExParamType.EX_STRING) != null) {
			hql = "from " + objClass.getName();
			hql = hql + " where " + inMap.get(Param.ExParamType.EX_STRING);
			count = hibernateDAO.getCountByQuery(hql, null);
		} else {
			hql = DaoTools.genHqlbyexconds(apptype, objClass, inMap, retvalues);
			count = hibernateDAO.getCountByQuery(hql, retvalues.get("values"));
		}
		long endTime = System.currentTimeMillis();
		log.debug(" GenHDDao.getRecordCount: " + (endTime - startTime) + " (ms) ");
		return count;

	}

	/**
	 * 获得满足该HQL条件的记录count
	 * 
	 * @param hql
	 * @param paramNames
	 * @param values
	 * @param reCreateCountHql
	 *            是否重新生成 count语句
	 * @return
	 */
	@Override
	public int getRecordCount(String hql, String[] paramNames, Object[] values, boolean reCreateCountHql) {
		return hibernateDAO.getCountByQuery(hql, paramNames, values, reCreateCountHql);
	}

	/**
	 * 获取任意数据表的分页查询的数据列
	 * 
	 * @param totalCount
	 *            记录总数
	 * @param hql
	 *            执行的hql语句
	 * @param pageSize
	 *            每页记录数
	 * @param startIndex
	 *            起始记录数
	 * @param parameters
	 *            绑定变量参数值
	 * @return
	 */
	@Override
	public List getRecordBypage(int totalCount, String hql, int pageSize, int startIndex, Object... parameters) {
		return hibernateDAO.findPageByQuery(totalCount, hql, pageSize, startIndex, parameters).getItems();

	}

	/**
	 * 获取任意数据表的分页查询的数据列
	 * 
	 * @param hql
	 * @param paramNames
	 *            参数名集合
	 * @param values
	 *            参数组集合
	 * @param resultTransformer
	 *            结果转换器
	 * @param totalCount
	 * @param pageSize
	 * @param startIndex
	 * @return
	 */
	@Override
	public List getRecordBypage(String hql, String[] paramNames, Object[] values, ResultTransformer resultTransformer,
			int totalCount, int pageSize, int startIndex) {
		return hibernateDAO
				.findPageByQuery(hql, paramNames, values, resultTransformer, totalCount, pageSize, startIndex)
				.getItems();
	}

	/**
	 * 根据Long的内部ID获取对象的业务id，主要用于外系统，其ID是字符串型的,只适用于没有复合主键的模型类点号定义表
	 * 
	 * @param clsname
	 *            需要进行缓缓的业务对象定义类的名称
	 * @param inid
	 *            传入的系统内部使用的长整型id
	 * @param retChar
	 *            第三方定义的id号的字段名称
	 * @return 第三方定义的id，数据都是用该id存储的
	 */
	public String getBrlId(String clsname, Long inid, String retChar) {
		long startTime = System.currentTimeMillis();
		String hql = "select " + retChar + " from " + clsname + " where Id=?";
		List lists = hibernateDAO.find(hql, inid);
		String id = "";
		if (lists != null) {
			for (int i = 0; i < lists.size(); i++) {
				id = (String) lists.get(0);
				break;
			}
		}
		long endTime = System.currentTimeMillis();
		log.debug(" GenHDDao.getBrlId: " + (endTime - startTime) + " (ms) ");
		return id;
	}

	@Override
	@SuppressWarnings("rawtypes")
	public Object[] bindParamForSqlOrHql(HashMap<String, Object> inmaplocal) {
		long startTime = System.currentTimeMillis();
		DaoTools.checkParaExist(log, inmaplocal, "EX_SQL");
		HashMap<String, Object> inmap = DaoTools.changeTimeForSql(inmaplocal);
		// 时间做个转换，sql只接收EQ_BTIME 和EQ_ETIME
		String sqlstr = (String) inmap.get(Param.ExParamType.EX_SQL);
		// 记录绑定变量位置
		int pos = 0;
		// 绑定变量位置和值的数组
		HashMap<Integer, Object> ar = new HashMap<Integer, Object>();
		// 分解SQl串，根据分解结果进行变量和值的绑定
		StringBuffer sbBuffer = new StringBuffer();
		String tempstr = sqlstr;
		if (!tempstr.contains("[")) {
			sbBuffer = sbBuffer.append(sqlstr);
		} else {
			// 把左括号带着
			sbBuffer = sbBuffer.append(sqlstr.substring(0, sqlstr.indexOf("[")));
		}
		int lastoverpos = 0;
		while (tempstr.contains("[")) {
			int i = tempstr.indexOf("[");
			int j = tempstr.indexOf("]");
			if (lastoverpos != 0) {
				sbBuffer.append(tempstr.substring(0, i));
			}
			String context = tempstr.substring(i + 1, j);
			if (context.equals("TB")) {
				context = "[TB]";
				// 如果存在开始时间绑定
				Calendar bt = (Calendar) inmap.get(Param.TimeType.EQ_BTIME);
				if (bt != null)
					pos = DaoTools.bindTime(log, bt, context, 0, ar, pos, inmap);
				sbBuffer.append((String) inmap.get(Param.ExParamType.EX_SQL));

			} else if (context.equals("TE")) {
				context = "[TE]";
				// 如果存在结束时间绑定
				Calendar et = (Calendar) inmap.get(Param.TimeType.EQ_ETIME);
				if (et != null)
					pos = DaoTools.bindTime(log, et, context, 1, ar, pos, inmap);
				sbBuffer.append((String) inmap.get(Param.ExParamType.EX_SQL));

			} else if (context.equals("ROWCOUNT")) {
				context = "[ROWCOUNT]";
				// 如果存在取数条数限制
				Integer rownum = (Integer) inmap.get(Param.ExParamType.EX_SLIPWIN);
				if (rownum != null)
					pos = DaoTools.bindCount(log, rownum, context, ar, pos, inmap);
				sbBuffer.append((String) inmap.get(Param.ExParamType.EX_SQL));

			} else if (context.equals("COLUMNNAME")) {
				context = "[COLUMNNAME]";
				// 如果存在列名是传入的情况
				Object[] strIdarray;
				strIdarray = (Object[]) inmap.get(Param.IdType.STR_IDARRAY);
				Integer clounmflag = (Integer) inmap.get(Param.ExParamType.EX_INT);
				if (clounmflag != null && strIdarray.length > 0)
					DaoTools.bindColunm(log, strIdarray, context, inmap);
				sbBuffer.append((String) inmap.get(Param.ExParamType.EX_SQL));
			} else if (context.equals("IDARRAY")) {
				context = "[IDARRAY]";
				// 如果存在Long形站号绑定
				Object[] Idarray;
				Idarray = (Object[]) inmap.get(Param.IdType.LONG_IDARRAY);
				if (Idarray != null && Idarray.length > 0)
					pos = DaoTools.bindLongID(log, Idarray, context, ar, pos, inmap);
				sbBuffer.append((String) inmap.get(Param.ExParamType.EX_SQL));
			} else if (context.equals("STRIDARRAY")) {
				context = "[STRIDARRAY]";
				// 如果存在String形站号绑定
				Object[] strIdarray;
				strIdarray = (Object[]) inmap.get(Param.IdType.STR_IDARRAY);
				if (strIdarray != null && strIdarray.length > 0)
					pos = DaoTools.bindStrID(log, strIdarray, context, ar, pos, inmap);
				sbBuffer.append((String) inmap.get(Param.ExParamType.EX_SQL));
			} else if (context.equals("S0")) {
				context = "[S0]";
				// 如果存在String形站号绑定
				sbBuffer.append(context);
			} else if (context.equals("S1")) {
				context = "[S1]";
				// 如果存在String形站号绑定
				sbBuffer.append(context);
			} else if (context.equals("S2")) {
				context = "[S2]";
				// 如果存在String形站号绑定
				sbBuffer.append(context);
			}

			tempstr = tempstr.substring(j + 1);
			lastoverpos = j + 1;
		}
		// 最后一个替换要处理
		if (tempstr != null && lastoverpos != 0) {
			sbBuffer.append(tempstr);
		}
		String sqlstrlast = sbBuffer.toString();
		if (inmap.get(Param.ExParamType.EX_STRING) != null) {
			String repStr = (String) inmap.get(Param.ExParamType.EX_STRING);
			sqlstrlast = sqlstrlast.replace("[S0]", repStr);
		}
		if (inmap.get(Param.ExParamType.EX_STRING1) != null) {
			String repStr = (String) inmap.get(Param.ExParamType.EX_STRING1);
			sqlstrlast = sqlstrlast.replace("[S1]", repStr);
		}
		if (inmap.get(Param.ExParamType.EX_STRING2) != null) {
			String repStr = (String) inmap.get(Param.ExParamType.EX_STRING2);
			sqlstrlast = sqlstrlast.replace("[S2]", repStr);
		}

		inmap.put(Param.ExParamType.EX_SQL, sqlstrlast);

		if (inmap.get(Param.ExCondition.ExCondition_key) != null) {
			pos = DaoTools.bindExCond(log, sqlstr, (ExCond[]) inmap.get(Param.ExCondition.ExCondition_key), ar, pos,
					inmap);
		}
		sqlstr = (String) inmap.get(Param.ExParamType.EX_SQL);
		inmaplocal.put(Param.ExParamType.EX_SQL, sqlstr);
		Object[] obj = new Object[ar.size()];
		for (int i = 0; i < pos; i++) {
			obj[i] = ar.get(i + 1);
		}
		long endTime = System.currentTimeMillis();
		log.debug(" GenHDDao.bindParamForSqlOrHql: " + (endTime - startTime) + " (ms) ");
		return obj;
	}

	/**
	 * 原生sql查询(不推荐使用)
	 * 
	 * @param sql
	 * @param parameters
	 * @return
	 */
	@Override
	public List queryByNativeSql(final String sql, final Object... parameters) {
		return hibernateDAO.queryByNativeSql(sql, parameters);
	}

	/**
	 * 原生sql查询(不推荐使用)
	 * 
	 * @param sql
	 * @param parameterMap
	 * @return
	 */
	@Override
	public List queryByNativeSql(final String sql, final Map<String, Object> parameterMap) {
		return hibernateDAO.queryByNativeSql(sql, parameterMap);
	}

	/**
	 * sql exec 批处理</br>
	 * 使用样例</br>
	 * 
	 * @param classType
	 *            实体类型 必要参数
	 * @param propertyNames
	 *            需要update的字段名称 必要参数
	 * @param values
	 *            字段的新值集合 必要参数
	 * @param conditionPropertyNames
	 *            条件字段名称 可为null
	 * @param condition
	 *            条件表达式，注意是HQL表达式 可为null
	 * @param conditionValues
	 *            条件值集合 可为null
	 * @return 批处理影响的记录条目数量
	 */
	@Override
	public int batchExecuteBySQL(final String sql, final Object[]... parameters) {
		return hibernateDAO.batchExecuteBySQL(sql, parameters);
	}

	@Override
	public List execSql(String sql, Object... params) {
		// TODO Auto-generated method stub
		return hibernateDAO.exeQuery(sql, params);
	}

	@Override
	public List queryByHQL(String hql, Object[] parameters) {
		return hibernateDAO.exeQuery(hql, parameters);
	}

	@Override
	public void saveOrUpdate(Object entity) {
		// TODO Auto-generated method stub
		hibernateDAO.saveOrUpdate(entity);
	}

	@Override
	public int getCount(String hql, Object[] param) {
		Long i = hibernateDAO.getCountByQuery(hql, param);
		return i.intValue();
	}

	@Override
	public Page getByPage(int totalCount, String hql, int pageSize, int startIndex, Object[] parameters) {
		Page page = hibernateDAO.findPageByQuery(totalCount, hql, pageSize, startIndex, parameters);

		return page;
	}

	@Override
	public int getCountBySql(String sql, Object[] param) {
		String countQueryString = (new StringBuilder(" select count(*) ")).append(removeSelect(removeOrders(sql)))
				.toString();
		Object b = hibernateDAO.queryByNativeSql(countQueryString, param).get(0);
		return Integer.valueOf(b.toString());
	}

	@Override
	public Page getPageBySql(final int totalCount, final String sql, final int pageSize, final int startIndex,
			final Object[] parameters) {
		if (totalCount < 1)
			return new Page(new ArrayList(0), 0);
		else
			return (Page) hibernateDAO.getHibernateTemplate().execute(new HibernateCallback() {

				@Override
				public Object doInHibernate(Session session) throws HibernateException {
					SQLQuery query = session.createSQLQuery(sql);
					if (null != parameters) {
						for (int i = 0; i < parameters.length; i++) {
							query.setParameter(i, parameters[i]);

						}
					}
					List items = query.setFirstResult(startIndex).setMaxResults(pageSize).list();
					Page ps = new Page(items, totalCount, pageSize, startIndex);
					return ps;
				}
			});
	}

	@Override
	public int getCount(Class entityClass, Criterion criterions[]) {
		return hibernateDAO.getCountByCriterions(entityClass, criterions);
	}

	@Override
	public Page getByPage(Class entityClass, int totalCount, Criterion criterions[], int pageSize, int startIndex,
			String orderProperty[], boolean isAsc[]) {
		Page page = hibernateDAO.findPageByCriterions(entityClass, totalCount, criterions, pageSize, startIndex,
				orderProperty, isAsc);

		return page;
	}

	private String removeSelect(String sql) {
		int beginPos = sql.toLowerCase().indexOf("from");
		return sql.substring(beginPos);
	}

	private static String removeOrders(String sql) {
		Pattern p = Pattern.compile("order\\s*by[\\w|\\W|\\s|\\S]*", 2);
		Matcher m = p.matcher(sql);
		StringBuffer sb = new StringBuffer();
		for (; m.find(); m.appendReplacement(sb, ""))
			;
		m.appendTail(sb);
		return sb.toString();
	}

	@Override
	public Criterion[] exCondToCriterion(ExCond[] exConds) {
		List<Object> list = DaoTools.parseExCondtoObject(exConds);
		List[] paramlist = new List[] { list };
		return hibernateDAO.CombCreterion(paramlist);
	}

	@Override
	public int executeByHQL(final String hql, final Object[] parameters){
		return hibernateDAO.executeByHQL(hql,parameters);
	}

	@Override
	public void saveorupdate(Object entity) {
		hibernateDAO.saveOrUpdate(entity);
	}
	
	@Override
	public int executeBySQL(final String sql, final Object[] parameters){
		return hibernateDAO.executeBySQL(sql,parameters);
	}
	
	@Override
	public List createNativeQuery(final String sql, final Object... parameters) {
		return hibernateDAO.createNativeQuery(sql,parameters);
	}

}
