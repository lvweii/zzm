package com.zzm.hd.util;

import com.zzm.hd.common.SysConstants;
import com.zzm.hd.common.iPoint;
import com.zzm.hd.dto.Chardata;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Array;
import java.math.RoundingMode;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Date;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import org.apache.log4j.Logger;

public class CommonTool
{
	private static Logger log = Logger.getLogger(CommonTool.class);

	/**
	 * 生产32位的UUID
	 * 
	 * @return
	 */
	public static String createUUID()
	{
		return UUID.randomUUID().toString().replace("-", "");
	}

	public static InetAddress getIP(String ip)
	{
		try
		{
			return InetAddress.getByName(ip.trim());
		}
		catch (Exception e1)
		{
			return null;
		}
	}

	public static InetSocketAddress getSocketAddress(String ip, int port)
	{
		try
		{
			return new InetSocketAddress(InetAddress.getByName(ip.trim()), port);
		}
		catch (Exception e1)
		{
			return null;
		}
	}

	/**
	 * MD5加密
	 * 
	 * @param source
	 * @return
	 * @throws NoSuchAlgorithmException
	 * @throws UnsupportedEncodingException
	 */
	public static String md5HexString(String source) throws NoSuchAlgorithmException, UnsupportedEncodingException
	{
		MessageDigest digest = MessageDigest.getInstance("MD5");
		byte[] digestBytes = digest.digest(source.getBytes("utf-8"));
		StringBuilder builder = new StringBuilder();
		for (byte b : digestBytes)
		{
			String s = String.format("%02X", b);
			builder.append(s);
		}
		return builder.toString();
	}

	/**
	 * 将对象转换成整数
	 * 
	 * @param str
	 *            转换的对??
	 * @param errorV
	 *            出错后的数据
	 * @return
	 */
	public static int ConvertToInt(Object str, int errorV)
	{
		int v = errorV;
		try
		{
			if (str != null)
				v = Integer.parseInt(str.toString());
		}
		catch (Exception er)
		{
			v = errorV;
		}

		return v;
	}
	/**
	 * 将对象转换成Byte
	 * 
	 * @param str
	 *            转换的对??
	 * @param errorV
	 *            出错后的数据
	 * @return
	 */
	public static Byte ConvertToByte(Object str)
	{
		Byte v = null;
		try
		{
			if (str != null)
				v = Byte.parseByte( str.toString());
		}
		catch (Exception er)
		{
		}

		return v;
	}
	/**
	 * 将对象转换成整数
	 * 
	 * @param str
	 *            转换的对??
	 * @param errorV
	 *            出错后的数据
	 * @return
	 */
	public static Integer ConvertToInt(Object str)
	{
		Integer v = null;
		try
		{
			if (str != null)
				v = Integer.parseInt(str.toString());
		}
		catch (Exception er)
		{
		}

		return v;
	}

	/**
	 * 将对象转换成浮点??
	 * 
	 * @param str
	 *            转换的对??
	 * @param errorV
	 *            出错后的数据
	 * @return
	 */
	public static Double ConvertToDouble(Object str)
	{
		Double v = null;
		try
		{
			if (str != null)
				v = Double.parseDouble(str.toString());
		}
		catch (Exception er)
		{
		}

		return v;
	}

	/**
	 * 将Double型的列表转换为double型的数组
	 * 
	 * @param vList
	 * @return
	 */
	public static double[] convertTodouble(List< Double > vList)
	{
		if (vList == null)
			return null;

		double[] ds = new double[vList.size()];
		for (int i = 0; i < vList.size(); i++)
		{
			ds[i] = vList.get(i);
		}
		return ds;
	}

	/**
	 * 将对象转换成浮点??
	 * 
	 * @param str
	 *            转换的对??
	 * @param errorV
	 *            出错后的数据
	 * @return
	 */
	public static double ConvertToDouble(Object str, double errorV)
	{
		double v = errorV;
		try
		{
			if (str != null)
				v = Double.parseDouble(str.toString());
		}
		catch (Exception er)
		{
			v = errorV;
		}

		return v;
	}

	/**
	 * 将对象转换成长整??
	 * 
	 * @param str
	 *            转换的对??
	 * @param errorV
	 *            出错后的数据
	 * @return
	 */
	public static long ConvertToLong(Object str, long errorV)
	{
		long v = errorV;
		try
		{
			if (str != null)
				v = Long.parseLong(str.toString());
		}
		catch (Exception er)
		{
			v = errorV;
		}

		return v;
	}

	/**
	 * 将对象转换成长整??
	 * 
	 * @param str
	 *            转换的对??
	 * @param errorV
	 *            出错后的数据
	 * @return
	 */
	public static Long ConvertToLong(Object str, Long errorV)
	{
		Long v = errorV;
		try
		{
			if (str != null)
				v = Long.parseLong(str.toString());
		}
		catch (Exception er)
		{
			v = errorV;
		}

		return v;
	}

	/**
	 * 将对象转换成布尔??
	 * 
	 * @param str
	 *            转换的对??
	 * @param errorV
	 *            出错后的数据
	 * @return
	 */
	public static boolean ConvertToBoolean(Object str, boolean errorV)
	{
		boolean v = errorV;
		try
		{
			if (str != null && !CommonTool.isNullOrEmpty(str.toString()))
				v = Boolean.parseBoolean(str.toString());
		}
		catch (Exception er)
		{
			v = errorV;
		}

		return v;
	}

	/**
	 * 将对象转换成Timestamp类型
	 * 
	 * @param val
	 *            需要转换的值
	 * @param errorV
	 *            出错后的默认数据
	 * @return
	 */
	public static Timestamp ConvertToTimestamp(Object val, Timestamp errorV)
	{
		Timestamp ts = null;
		try
		{
			if (val != null)
			{
				if (val instanceof String)
				{
					ts = Timestamp.valueOf((String)val);
				}
				else if (val instanceof Date)
				{
					ts = new Timestamp(((Date)val).getTime());
				}
				else if (val instanceof Timestamp)
				{
					ts = (Timestamp)val;
				}
				else if (val instanceof Calendar)
				{
					ts = new Timestamp(((Calendar)val).getTime().getTime());
				}
			}
		}
		catch (Exception er)
		{
			ts = errorV;
		}

		return ts;
	}

	/**
	 * 将对象转换IP地址
	 * 
	 * @param str
	 *            转换的IP地址字符串
	 * @param errorV
	 *            出错后的数据
	 * @return
	 */
	public static InetSocketAddress ConverttoIPAddress(String addrStr)
	{
		InetSocketAddress address = null;

		if (addrStr != null && !addrStr.isEmpty())
		{
			try
			{
				String[] destAddrs = addrStr.split(":");
				address = new InetSocketAddress(InetAddress.getByName(destAddrs[0]), Integer.parseInt(destAddrs[1]));
			}
			catch (NumberFormatException e)
			{
				System.out.println("IP地址数字转换错误");
				e.printStackTrace();
			}
			catch (UnknownHostException e)
			{
				System.out.println("无效IP地址");
				e.printStackTrace();
			}
		}

		return address;
	}

	/**
	 * 判断字符串是否为空或空字符串
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isNullOrEmpty(String str)
	{
		if (str == null)
			return true;
		if (str.trim().length() == 0)
			return true;
		return false;
	}

	/**
	 * 转换为布尔数??
	 * 
	 * @param val
	 * @return
	 */
	public static boolean toboolean(String val)
	{
		boolean bRet = false;

		if (val != null && !val.isEmpty())
		{
			try
			{
				bRet = Boolean.parseBoolean(val);
			}
			catch (Exception e)
			{
				System.out.println(e.getMessage());

				bRet = false;
			}
		}

		return bRet;
	}

	/**
	 * 转换为整型数??
	 * 
	 * @param val
	 * @return
	 */
	public static int toint(String val)
	{
		int intRet = Integer.MIN_VALUE;

		if (val != null && !val.isEmpty())
		{
			try
			{
				intRet = Integer.parseInt(val);
			}
			catch (Exception e)
			{
				System.out.println(e.getMessage());

				intRet = Integer.MIN_VALUE;
			}
		}

		return intRet;
	}

	/**
	 * 转换为单精度型数??
	 * 
	 * @param val
	 * @return
	 */
	public static float tofloat(String val)
	{
		float fRet = Float.MIN_EXPONENT;

		if (val != null && !val.isEmpty())
		{
			try
			{
				fRet = Float.parseFloat(val);
			}
			catch (Exception e)
			{
				System.out.println(e.getMessage());

				fRet = Float.MIN_EXPONENT;
			}
		}

		return fRet;
	}

	/**
	 * 转换为双精度型数??
	 * 
	 * @param val
	 * @return
	 */
	public static double todouble(String val)
	{
		double dRet = Double.MIN_EXPONENT;

		if (val != null && !val.isEmpty())
		{
			try
			{
				dRet = Double.parseDouble(val);
			}
			catch (Exception e)
			{
				System.out.println(e.getMessage());

				dRet = Double.MIN_EXPONENT;
			}
		}

		return dRet;
	}

	/**
	 * 合并数组
	 * 
	 * @param <T>
	 * @param objs
	 * @return
	 */
	public static < T > T[] mergerArray(T[]... objArrs)
	{
		if (objArrs == null)
			return null;

		T[] tmpArr = null;
		ArrayList< T > items = new ArrayList< T >();
		for (T[] objs : objArrs)
		{
			if (objs != null && objs.length > 0)
			{
				if (tmpArr == null)
					tmpArr = (T[])Array.newInstance(objs[0].getClass(), 0);
				for (int i = 0; i < objs.length; i++)
				{
					if (!items.contains(objs[i]))
						items.add(objs[i]);
				}
			}
		}
		return items.size() > 0 ? (T[])items.toArray(tmpArr) : null;
	}

	/**
	 * 读取指定的配置文??
	 * 
	 * @return
	 */
	public static String readTextFile(String fileName)
	{
		String retText = "";

		try
		{
			InputStream inputStream = CommonTool.class.getResourceAsStream(fileName);

			if (inputStream == null)
			{
				File file = new File(fileName);

				if (file.exists())
				{
					inputStream = new FileInputStream(fileName);
				}
			}

			if (inputStream != null)
			{
				BufferedReader bufferedreader = new BufferedReader(new InputStreamReader(inputStream));
				String s2;
				while ((s2 = bufferedreader.readLine()) != null)
				{
					retText = retText + s2 + "\n";
				}

			}
		}
		catch (Exception e)
		{
			System.out.println(e.getMessage());
			// log.error(configFileName + "加载或解析出??" + e.getMessage());
		}

		return retText;
	}

	/**
	 * 返回文件扩展??
	 * 
	 * @param fileUri
	 * 
	 * @return文件扩展??
	 */
	public static String getFileType(String fileUri)
	{
		File file = new File(fileUri);
		String fileName = file.getName();
		String fileType = fileName.substring(fileName.lastIndexOf(".") + 1, fileName.length());

		return fileType;
	}

	/**
	 * 字符串型数??数组转换为long型字符串数组
	 * 
	 * @param para
	 * @return
	 */
	public static Long[] StringToLongArray(String para)
	{
		if (CommonTool.isNullOrEmpty(para))
			return null;
		Long[] datas = null;
		try
		{
			String[] dataStr = para.split(",");

			if (dataStr != null && dataStr.length > 0)
			{
				datas = new Long[dataStr.length];
				for (int i = 0; i < dataStr.length; i++)
				{
					String ds = dataStr[i];
					datas[i] = Long.parseLong(ds);
				}
			}
		}
		catch (Exception e)
		{
			System.out.println(e.getMessage());
		}

		return datas;
	}

	/**
	 * 字符串型数??数组转换为intS型字符串数组
	 * 
	 * @param para
	 * @return
	 */
	public static int[] StringToIntArray(String para)
	{
		int[] datas = null;
		try
		{
			String[] dataStr = para.split(",");

			if (dataStr != null && dataStr.length > 0)
			{
				datas = new int[dataStr.length];
				for (int i = 0; i < dataStr.length; i++)
				{
					String ds = dataStr[i];
					datas[i] = Integer.parseInt(ds);
				}
			}
		}
		catch (Exception e)
		{
			System.out.println(e.getMessage());
		}

		return datas;
	}

	/**
	 * 字符串型数??数组转换为long型字符串数组
	 * 
	 * @param para
	 * @return
	 */
	public static Double[] StringToDoubleArray(String para)
	{
		Double[] datas = null;
		try
		{
			String[] dataStr = para.split(",");

			if (dataStr != null && dataStr.length > 0)
			{
				datas = new Double[dataStr.length];
				for (int i = 0; i < dataStr.length; i++)
				{
					String ds = dataStr[i];
					datas[i] = Double.parseDouble(ds);
				}
			}
		}
		catch (Exception e)
		{
			System.out.println(e.getMessage());
		}

		return datas;
	}

	/**
	 * 工作密钥key的最少长??至少??位字符（????字节??
	 */
	private static int keyLength = 8;

	/**
	 * 给密码加密；key至少????8??不足自动补零
	 * 
	 * @param password
	 * @param key
	 * @return
	 */
	public static String setPasswordCryp(String password, String key)
	{
		if (password == null || key == null)
		{
			return null;
		}
		String newKey = (key.length() < keyLength) ? key + "00000000".substring(key.length(), keyLength) : key;

		Calendar today = Calendar.getInstance();
		String newPwd = String.valueOf(today.get(Calendar.YEAR)) + "|" + String.valueOf(today.get(Calendar.MONTH) + 1) + "|" + String.valueOf(today.get(Calendar.DATE)) + "|" + password;
		try
		{
			return CrypTool.encode(newPwd, newKey);
		}
		catch (Exception e)
		{
			// e.printStackTrace();
			return null;
		}
	}

	/**
	 * 给密码解密，还原密码；key至少????8??不足自动补零
	 * 
	 * @param passwordCryp
	 *            ????还原的加密字符串
	 * @param key
	 *            工作密钥
	 * @return
	 */
	public static String getPasswordCryp(String passwordCryp, String key)
	{
		// 如果????还原的加密字符串和工作密钥中有一个为null，则返回null
		if (passwordCryp == null || key == null)
		{
			return null;
		}

		// 如果工作密钥不足????字节，则自动补零返回，否则原值返??
		String newKey = (key.length() < keyLength) ? key + "00000000".substring(key.length(), keyLength) : key;

		// 获取当天日期，并按照“年|月|日??的格式组装日??
		Calendar tdy = Calendar.getInstance();
		String today = String.valueOf(tdy.get(Calendar.YEAR)) + "|" + String.valueOf(tdy.get(Calendar.MONTH) + 1) + "|" + String.valueOf(tdy.get(Calendar.DATE));

		String pwd = null;
		try
		{
			// 调用通用解密函数解密,CrypTool是??用解密函数所在的类，返回“年|月|日|password”格式的字符??
			pwd = CrypTool.decode(passwordCryp, newKey);
			if (pwd != null)
			{
				// pos分割日期和password
				int pos = pwd.lastIndexOf("|");
				if (pos > 0)
				{
					// 获取“年|月|日??格式的日??
					String dateString = pwd.substring(0, pos);
					// 获取password
					String password = pwd.substring(pos + 1);
					// 如果解密出来的日期是当天的数值，则返回password，否则返回null
					if (dateString.equals(today))
					{
						return password;
					}
					else
					{
						return null;
					}
				}
			}
		}
		catch (Exception e)
		{
			// e.printStackTrace();
			// 解密出错返回null
			return null;
		}
		// 默认返回null
		return null;
	}

	/**
	 * 按照指定比例稀疏过程线点。如果为0则是只保留所有局部极值。
	 * 
	 * @param points
	 *            按照x值升序的点
	 * @param maxCount
	 *            稀疏后的最大记录数
	 * @return 按照x升序的稀疏后的点集
	 */
	public static Chardata[] filterPoints(Chardata[] points, int maxCount)
	{
		if (points != null)
		{
			int len = points.length;
			// 当超过最大稀疏数据个数，则稀疏
			if (len > maxCount)
			{
				double rate = maxCount / (double)len;// 计算稀疏比率
				points = CommonTool.filterPoints(points, rate);
			}
		}
		return points;
	}

	/**
	 * 按照指定比例稀疏过程线点。如果为0则是只保留所有局部极值。
	 * 
	 * @param points
	 *            按照x值升序的点
	 * @param rate
	 *            稀疏后的点数量占原始点数量的比例
	 * @return 按照x升序的稀疏后的点集
	 */
	public static Chardata[] filterPoints(Chardata[] points, double rate)
	{
		if(points == null || points.length < 1 || rate < 0)
			return null;
		else if(rate >= 1 || points.length - points.length * rate < 1)
			return points;
		List<Chardata> res = new ArrayList<Chardata>(rate > 0 ? (int)(points.length * 4 * rate / 3) + 1 : points.length);
		List<Chardata> fiters = new ArrayList<Chardata>(rate > 0 ? (int)(points.length * 4 * (1 - rate) / 3) + 1 : points.length);
		List<Long> xs = new ArrayList<Long>(rate > 0 ? (int)(points.length * 4 * rate / 3) + 1 : points.length);
		Chardata max = points[0], min = points[0];
		res.add(points[0]);
		xs.add(points[0].getTime().getTimeInMillis());
		res.add(points[1]);
		xs.add(points[1].getTime().getTimeInMillis());
		double k1 = points[1].getV().doubleValue() - points[0].getV().doubleValue(), k2;
		Double x, x1;
		for(int i = 2; i < points.length; i++)
		{
			if(points[i] == null || points[i].getTime() == null || xs.contains(Long.valueOf(points[i].getTime().getTimeInMillis())))
				continue;
			x = points[i].getV();
			x1 = points[i - 1].getV();

			if(x == null || x1 == null)
				continue;
			max = (max.getV() == null || x > max.getV()) ? points[i] : max;
			min = (min.getV() == null || x < min.getV()) ? points[i] : min;
			k2 = x.doubleValue() - x1.doubleValue();
			if(k1 * k2 > 0 || (k1 == 0 && k2 == 0))
			{
				fiters.add(res.remove(res.size() - 1));
				xs.remove(xs.size() - 1);
			}
			k1 = k2;
			res.add(points[i]);
			xs.add(points[i].getTime().getTimeInMillis());
		}
		double temprate = res.size() / (double)points.length;
		int num = (int)Math.abs(points.length * rate - res.size());
		if(rate == 0 || Math.abs(rate - temprate) <= 0.02 || num < 1)
			return res.toArray(new Chardata[0]);
		else if(temprate < rate)
		{
			float index = fiters.size() / (float)num;
			for(float i = 0; i < fiters.size(); i += index)
				res.add(insertEl(xs, fiters.get((int)i).getTime().getTimeInMillis()), fiters.get((int)i));
		}
		else if(res.size() > 1)
		{
			int index, l = res.size();
			for(int i = num - 1; i >= 0; i--)
			{
				index = l * i / num;
				while(index > 0 && index + 1 < res.size() && (res.get(index).getV().equals(max.getV()) || res.get(index).getV().equals(min.getV())))
					index--;
				if(index > 0)
					res.remove(res.get(index).getV() >= res.get(index - 1).getV() ? (res.get(index + 1).getV() >= res.get(index).getV() ? index : (res.get(index + 1).getV() >= res.get(index - 1).getV() ? index + 1 : index - 1)) : (res.get(index + 1).getV() <= res.get(index).getV() ? index : (res.get(index + 1).getV() <= res.get(index - 1).getV() ? index + 1 : index - 1)));
			}
		}
		xs.clear();
		fiters.clear();
		return res.toArray(new Chardata[0]);
	}

	/**
	 * 将一个新的double值插入升序排列的集合中
	 * 
	 * @param nums
	 *            - 待插入新值的升序集合
	 * @param i
	 *            - 新值
	 */
	public static int insertEl(List< Long > nums, Long i)
	{
		int start = 0, end = nums.size() - 1;
		double flag;
		if (nums.size() < 1 || nums.get(nums.size() - 1) < i)
		{
			nums.add(i);
			return nums.size() - 1;
		}
		else if (nums.get(0) > i)
		{
			nums.add(0, i);
			return 0;
		}
		else
			// 采用2分法查找待插入位置
			while (true)
			{
				flag = nums.get((end - start) / 2 + start);
				if (flag > i)
				{
					end -= (end - start) / 2;
				}
				else
				{
					start += (end - start) / 2;
				}
				if (end - start < 2)
				{
					flag = nums.get(end);
					if (nums.get(start) > i)
					{
						nums.add(start, i);
						return start;
					}
					else if (nums.get(end) < i)
					{
						if (end + 1 == nums.size())
						{
							nums.add(i);
							return nums.size() - 1;
						}
						else
						{
							nums.add(end + 1, i);
							return end + 1;
						}
					}
					else
					{
						nums.add(end, i);
						return end;
					}
				}
			}
	}

	/**
	 * 实现点序列按指定比例的稀疏化功能。稀疏化的原则是不考虑数据的真实合理性，保持稀疏化后的曲线与原始曲线图形性状尽量一致。
	 * 
	 * @param sps
	 *            原始点集，按照x轴升序排列，每个点的x坐标必须唯一
	 * @param rate
	 *            稀疏后剩余点集与原始点集数量的百分比.如果为0表示只保留各个波峰波谷点;否则按照点的变化率从低到高的顺序进行舍弃，
	 *            直至满足稀疏率。
	 * @return 稀疏后的点集
	 */
	public static iPoint[] filterPoints(iPoint[] sps, double rate)
	{
		if (sps == null || sps.length < 2)
			return null;
		if (rate >= 1)
			return sps;

		int count = (int)(sps.length * rate);

		return filterPoints(sps, count);
	}

	/**
	 * 实现二维点集的按指定数目的稀疏化功能。稀疏化的原则是保持稀疏化后的样本与初始样本的二维密度分布函数尽量一致。
	 * 
	 * @param sps
	 *            - 待稀疏的初始点集
	 * @param finalNum
	 *            - 稀疏后的点集的数目
	 * @return 稀疏后的点集
	 */
	public static iPoint[] filterPoints(iPoint[] sps, int finalNum)
	{
		if (sps == null || sps.length < 1)
			return null;
		if (finalNum < 0 || finalNum > sps.length)
			return sps;
		// 基于X、Y坐标缓存所有点
		HashMap< Double, HashMap< Double, iPoint >> reaults = new HashMap< Double, HashMap< Double, iPoint >>(sps.length);
		Double xTemp, yTemp;
		// 为保持样本特征，点集的四个顶点特别保留，以防止接下来被稀疏掉
		iPoint xMin = sps[0], xMax = sps[0], yMin = sps[0], yMax = sps[0];
		// 将X坐标按照升序进行缓存，以备接下来进行分划、递归
		ArrayList< Double > xOrder = new ArrayList< Double >(sps.length);
		// 开始遍历
		for (iPoint sp : sps)
		{
			xTemp = sp.getX();
			yTemp = sp.getY();

			xMin = xTemp < xMin.getX() ? sp : xMin;
			xMax = xTemp > xMax.getX() ? sp : xMax;
			yMin = yTemp < yMin.getX() ? sp : yMin;
			yMax = yTemp > yMax.getX() ? sp : yMax;

			if (!reaults.containsKey(xTemp))
			{
				reaults.put(xTemp, new HashMap< Double, iPoint >());
				insertEl(xOrder, xTemp);
			}

			if (!reaults.get(xTemp).containsKey(yTemp))
			{
				reaults.get(xTemp).put(yTemp, sp);
			}
		}
		// 将四个顶点保存在集合中
		List< iPoint > saved = new ArrayList< iPoint >(6);
		saved.add(xMin);
		saved.add(xMax);
		saved.add(yMin);
		saved.add(yMax);
		// 进行稀疏
		decreaseMatrix(xOrder, reaults, sps.length - finalNum, saved);
		ArrayList< iPoint > finals = new ArrayList< iPoint >();
		for (HashMap< Double, iPoint > r : reaults.values())
		{
			if (r.size() > 0)
				finals.addAll(r.values());
		}
		return finals.toArray(new iPoint[0]);
	}

	/**
	 * 通过递归方式，实现对抽取问题的分划、迭代直至最小单元进行抽取
	 * 
	 * @param nums
	 *            - 进行抽取点的X坐标的取值范围集合
	 * @param results
	 *            - 待抽取的初始点集
	 * @param toCut
	 *            - 需要抽取掉的点的数目
	 * @param saved
	 *            - 需要避免被抽取掉的点的集合
	 */
	public static void decreaseMatrix(List< Double > nums, HashMap< Double, HashMap< Double, iPoint >> results, int toCut, List< iPoint > saved)
	{
		if (toCut < 1 || nums.size() < 1)
			return;
		// 当为最小单元或只需稀疏掉一个点时，无须在分划，进行稀疏
		if (nums.size() == 1 || toCut == 1)
		{
			while (toCut > 0)
			{
				for (Double d : nums)
				{
					HashMap< Double, iPoint > ps = results.get(d);
					Double index = null;
					for (Double temp : ps.keySet())
					{
						if (!saved.contains(ps.get(temp)))
						{
							index = temp;
							break;
						}

					}
					if (index != null)
					{
						ps.remove(index);
						toCut--;
						if (toCut < 1)
						{
							break;
						}
					}
				}
			}
		}
		else
		{
			List< Double > left = nums.subList(0, nums.size() / 2);
			List< Double > right = nums.subList(nums.size() / 2, nums.size());

			double lnum = 0, rnum = 0;
			for (Double d : left)
			{
				lnum += results.get(d).size();
			}
			for (Double d : right)
			{
				rnum += results.get(d).size();
			}
			int lcut = (int)((lnum / (lnum + rnum)) * toCut);
			// 左右部分分别进行稀疏
			decreaseMatrix(left, results, lcut, saved);
			decreaseMatrix(right, results, toCut - lcut, saved);
		}
	}

	public static int insertEl(List< Double > nums, double i)
	{
		int start = 0, end = nums.size() - 1;
		double flag;
		if (nums.size() < 1 || nums.get(nums.size() - 1) < i)
		{
			nums.add(i);
			return nums.size() - 1;
		}
		else if (nums.get(0) > i)
		{
			nums.add(0, i);
			return 0;
		}
		else
			while (true)
			{
				flag = nums.get((end - start) / 2 + start);
				if (flag > i)
				{
					end -= (end - start) / 2;
				}
				else
				{
					start += (end - start) / 2;
				}
				if (end - start < 2)
				{
					flag = nums.get(end);
					if (nums.get(start) > i)
					{
						nums.add(start, i);
						return start;
					}
					else if (nums.get(end) < i)
					{
						if (end + 1 == nums.size())
						{
							nums.add(i);
							return nums.size() - 1;
						}
						else
						{
							nums.add(end + 1, i);
							return end + 1;
						}
					}
					else
					{
						nums.add(end, i);
						return end;
					}
				}
			}
	}

	public static void main(String[] args)
	{
		String str = setPasswordCryp("admin", "123");// key至少????8??
		System.out.println(str);
		String str1 = getPasswordCryp("424BA17FBFE449B336E92750DCCB6B91", "123");
		System.out.println(str1);
		System.out.println("hello");
		try
		{
			System.out.println(md5HexString("1979313"));
		}
		catch (NoSuchAlgorithmException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (UnsupportedEncodingException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 获得数据格式化对象
	 * 
	 * @param formatString
	 * @return
	 */
	public static DecimalFormat getDecimalFormat(String formatString)
	{
		DecimalFormat decimalFormat = new DecimalFormat(formatString);
		decimalFormat.setRoundingMode(RoundingMode.HALF_UP);
		return decimalFormat;
	}

	/**
	 * 获取本机所有的网卡接口、及其对应的IP地址组
	 * 
	 * @return 返回所有的网卡，及每个网卡上绑定的ip地址组
	 */
	public static Map< NetworkInterface, List< InetAddress >> getAllIP()
	{
		Enumeration< NetworkInterface > ifs;
		try
		{
			ifs = NetworkInterface.getNetworkInterfaces();
			List< InetAddress > addrsAddr;
			Map< NetworkInterface, List< InetAddress >> res = new HashMap< NetworkInterface, List< InetAddress >>();
			while (ifs.hasMoreElements())
			{
				addrsAddr = new ArrayList< InetAddress >();
				NetworkInterface xface = ifs.nextElement();
				Enumeration< InetAddress > addrs = xface.getInetAddresses();
				while (addrs.hasMoreElements())
				{
					addrsAddr.add(addrs.nextElement());
				}
				res.put(xface, addrsAddr);
			}
			if (res.size() > 0)
				return res;
		}
		catch (SocketException e)
		{
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 通过序列化/返序列化方式深度克隆对象
	 * 
	 * @param cloneObj
	 *            要克隆的对象
	 * @return 深度克隆后的对象
	 */
	public static Object deepClone(Object cloneObj)
	{
		if (cloneObj == null)
			return null;
		ByteArrayOutputStream byteOut = null;
		ObjectOutputStream objOut = null;
		ByteArrayInputStream byteIn = null;
		ObjectInputStream objIn = null;
		try
		{
			byteOut = new ByteArrayOutputStream();
			objOut = new ObjectOutputStream(byteOut);

			objOut.writeObject(cloneObj);

			byteIn = new ByteArrayInputStream(byteOut.toByteArray());
			objIn = new ObjectInputStream(byteIn);

			return objIn.readObject();

		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		catch (ClassNotFoundException e)
		{
			e.printStackTrace();
		}
		finally
		{
			try
			{
				byteIn = null;
				byteOut = null;
				if (objOut != null)
					objOut.close();
				if (objIn != null)
					objIn.close();
			}
			catch (Exception e2)
			{
				e2.printStackTrace();
			}
		}
		return null;
	}

	/**
	 * 不管是否是调试状态，一定要输出的信息。通常是错误信息！
	 * @param info
	 */
	public static void writeErrorInfo(String info)
	{
		writeErrorInfo(info, log);
	}

	/**
	 * 不管是否是调试状态，一定要输出的信息。通常是错误信息！
	 * @param info
	 * @param filter
	 */
	public static void writeErrorInfo(String info, String filter)
	{
		writeErrorInfo(info, log, filter);
	}

	/**
	 * 不管是否是调试状态，一定要输出的信息。通常是错误信息！
	 * @param info
	 * @param log
	 */
	public static void writeErrorInfo(String info, Logger log)
	{
		writeErrorInfo(info, log, "");
	}

	/**
	 * 不管是否是调试状态，一定要输出的信息。通常是错误信息！
	 * @param info   输出内容
	 * @param log    logger对象
	 * @param filter 对输出内容进行过滤，一般以功能为单位进行过滤，比如“communication”、“alarm”等。
	 */
	public static void writeErrorInfo(String info, Logger log, String filter)
	{
		if(info == null)
			return;
		
		if (SysConstants.SysRunMode.equals("logger"))
		{
			if(log != null)
			{
				log.error(String.format("------ [%s]，【%s】", CommonTool.ConvertToString(Calendar.getInstance()), info));
			}
		}
		else
		{
			System.out.println(String.format("------ [%s]，【%s】", CommonTool.ConvertToString(Calendar.getInstance()), info));
		}
	}

	/**
	 * 以调试方式工作，根据AppConfig.xml中的配置选择是否输出调试语句，方便开发人员快速定位！
	 * 
	 * @param info
	 */
	public static void writeDebugInfo(String info)
	{
		writeDebugInfo(info, log);
	}

	/**
	 * 以调试方式工作，根据AppConfig.xml中的配置选择是否输出调试语句，方便开发人员快速定位！
	 * 
	 * @param info
	 * @param filter
	 */
	public static void writeDebugInfo(String info, String filter)
	{
		writeDebugInfo(info, log, filter);
	}

	/**
	 * 以调试方式工作，根据AppConfig.xml中的配置选择是否输出调试语句，方便开发人员快速定位！
	 * 
	 * @param info
	 * @param log
	 */
	public static void writeDebugInfo(String info, Logger log)
	{
		writeDebugInfo(info, log, "");
	}

	/**
	 * 以调试方式工作，根据AppConfig.xml中的配置选择是否输出调试语句，方便开发人员快速定位！
	 * 
	 * @param info   输出内容
	 * @param log    logger对象
	 * @param filter 对输出内容进行过滤，一般以功能为单位进行过滤，比如“communication”、“alarm”等。
	 */
	public static void writeDebugInfo(String info, Logger log, String filter)
	{
		if(info == null)
			return;
		
		if (SysConstants.SysRunMode.equals("logger"))
		{
			if(log != null)
			{
				log.debug(String.format("------ [%s]，【%s】", CommonTool.ConvertToString(Calendar.getInstance()), info));
			}
		}
		else if (SysConstants.SysRunMode.equals("debug"))
		{
			System.out.println(String.format("------ [%s]，【%s】", CommonTool.ConvertToString(Calendar.getInstance()), info));
		}
	}

	/**
	 * 日期转换字符串
	 * 
	 * @param cal
	 *            calendar日期
	 * @param format
	 *            格式
	 * @return 返回format中要求的格式日期字符串
	 */
	public static String ConvertToString(Calendar cal, String format)
	{
		if (cal == null)
			return null;

		SimpleDateFormat bartDateFormat = new SimpleDateFormat(format);
		String res = bartDateFormat.format(cal.getTime());

		return res;
	}

	/**
	 * 日期转换字符串
	 * 
	 * @param cal
	 *            calendar日期
	 * @return yyyy-MM-dd HH:mm:ss 字符串
	 */
	public static String ConvertToString(Calendar cal)
	{
		if (cal == null)
			return null;

		SimpleDateFormat bartDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String res = bartDateFormat.format(cal.getTime());

		return res;
	}
	
	/**
	 * 基于最小二乘法进行多项式曲线拟合
	 * @param vals 样本点集合
	 * @param n 拟合的最高次数
	 * @return 依次从常数项、一次项、二次项至次高次、最高次的所有多项式系数集合
	 */
	public static double[] regressionByLeastSquare(iPoint[] vals, int n)
	{
		if(vals == null || vals.length < n)
			return null;
		double[][] matrix = new double[n + 1][];
		double[] xitem = new double[2 * n + 1];
		double[] yitem = new double[n + 1];
		for(int j = 0; j < vals.length; j++)
		{
			for(int i = 0; i <= 2 * n; i++)
			{
				xitem[i] += Math.pow(vals[j].getX(), i);
			}
			for(int k = 0; k < n + 1; k++)
			{
				yitem[k] += Math.pow(vals[j].getX(), k) * vals[j].getY();
			}
		}
		for(int i = 0; i <= n; i++)
		{
			matrix[i] = new double[n + 2];
			for(int j = 0; j <= n; j++)
			{
				matrix[i][j] = xitem[i + j];
			}
			matrix[i][n + 1] = yitem[i];
		}
		return getResult(n + 1, matrix);
	}

	/**
	 * 求解多元一次方程组的根，方程组以N*N+1矩阵给出，代表了方程组的系数和右值
	 * 
	 * @param n
	 *            解向
	 * @param matrix
	 *            方程组矩阵
	 * @return 解向量
	 */
	public static double[] getResult(int n, double[][] matrix)
	{
		matrix = simple(n, matrix);
		double[] result = new double[n];
		result[result.length - 1] = matrix[n - 1][n];
		for(int i = n - 2; i >= 0; i--)
		{
			double temp = matrix[i][n];
			for(int j = n - 1; j > i; j--)
			{
				if(matrix[i][j] != 0)
				{
					temp -= result[j] * matrix[i][j];
				}
			}
			temp /= matrix[i][i];
			result[i] = temp;
		}
		return result;
	}

	private static double[][] simple(int n, double[][] matrix)
	{
		for(int k = 0; k < n; k++)
		{
			if(matrix[k][k] == 0)
			{
				changeRow(n, k, matrix);
			}
			for(int i = k; i < n; i++)
			{
				double temp = matrix[i][k];
				for(int j = 0; j < n + 1; j++)
				{
					if(temp == 0)
						continue;
					if(i == k && temp != 1)
					{
						matrix[i][j] /= temp;
					}
					else
						matrix[i][j] -= temp * matrix[k][j];
				}
			}
		}
		return matrix;
	}

	private static double[][] changeRow(int n, int k, double[][] matrix)
	{
		double[] temp = new double[n + 1];
		for(int i = k; i < n; i++)
		{
			if(i + 1 == n && matrix[k][k] == 0)
			{
				return null;
			}
			for(int j = 0; j < n + 1; j++)
			{
				temp[j] = matrix[k][j];
				matrix[k][j] = matrix[i + 1][j];
				matrix[i + 1][j] = temp[j];
			}
			if(matrix[k][k] != 0)
				return matrix;
		}
		return null;
	}
	
	/**
	 * 时间加上指定的天数
	 * @param currtamp 期望
	 * @param day 标准差
	 * @return
	 */
	public static Timestamp addDay(Timestamp currtamp,int day)
	{
		  if (currtamp == null)
			  return null;
		  
		  Calendar cc = Calendar.getInstance();
		  cc.setTime(currtamp);
		  cc.add(Calendar.DAY_OF_YEAR, day);
		
		return  new Timestamp(cc.getTimeInMillis());
	}
	
	/**
	 * 时间加上指定的天数
	 * @param currtamp 期望
	 * @param day 标准差
	 * @return
	 */
	public static Timestamp addMinute(Timestamp currtamp,int MINUTE)
	{
		  if (currtamp == null)
			  return null;
		  
		  Calendar cc = Calendar.getInstance();
		  cc.setTime(currtamp);
		  cc.add(Calendar.MINUTE, MINUTE);
		
		return  new Timestamp(cc.getTimeInMillis());
	}
	
	//9900-1-1 0:00:00 
	public static Timestamp maxTimestamp = new Timestamp(250246598400734l);
	
	//1900-1-1 0:00:00
	public static Timestamp minTimestamp = new Timestamp(-2209017599024l);
	
	
}
