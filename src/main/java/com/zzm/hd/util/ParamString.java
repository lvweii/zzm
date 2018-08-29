package com.zzm.hd.util;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * 参数字符串
 * 提取、设置参数字符串变量
 * 
 * @author terry
 *
 */
public class ParamString {

	/**
	 * 从输入串中提取参数值串
	 * 输入串格式： para1 = value1; para2 = value2;
	 * @param input 输入串
	 * @param paraname 参数名称
	 * @return 参数串
	 */
	public static String GetParaValue(String input, String paraname)
	{
		return GetParaValue(input, paraname, ";");
	}
	
	/**
	 * 从输入串中提取参数值串
	 * 输入串格式： para1 = value1; para2 = value2;
	 * @param input 输入串
	 * @param paraname 参数名称
	 * @return 参数串
	 */
	public static String GetParaValue(String input, String paraname, String splitChar)
	{
		if(CommonTool.isNullOrEmpty(paraname))
			return null;
		String strRet = "";
		if ( input != null )
		{
			// String format = "\\b\\s*"+paraname+"\\s*=\\s*([^=;]+);";
			String format = "\\b\\s*"+paraname+"\\s*=\\s*([^"+splitChar+"]+)"+splitChar;
			
			Pattern pattern = Pattern.compile(format);
			Matcher matcher = pattern.matcher(input);
			
			if(matcher.find())
			{
				strRet = matcher.group(1);
			}
		 }

		return strRet;
	}

	/**
	 * 将输入串中指定名称的参数替换成新值,如果没有该项参数则自动在后面添加。
	 * 输入串格式： para1 = value1; para2 = value2;
	 * @param input 输入参数串
	 * @param pn 参数名
	 * @param valstr 参数值
	 * @return 替换好的参数串
	 */
	public static String SetParaValue( String input, String pn, String valstr )
	{
		return SetParaValue( input, pn, valstr, ";");
	}
	
	/**
	 * 将输入串中指定名称的参数替换成新值,如果没有该项参数则自动在后面添加。
	 * 输入串格式： para1 = value1; para2 = value2;
	 * @param input 输入参数串
	 * @param pn 参数名
	 * @param valstr 参数值
	 * @return 替换好的参数串
	 */
	public static String SetParaValue( String input, String pn, String valstr, String splitChar )
	{
		if(CommonTool.isNullOrEmpty( splitChar) )
			return input;
//		String strRet = "";
		
		if(input == null){
			input = "";
		}
		
		if( pn != null && valstr != null )
		{
			// String format = "\\b\\s*"+pn+"\\s*=\\s*([^=;]+);";
			String format = "\\b\\s*"+pn+"\\s*=[^=" + splitChar + "]*" + splitChar;

			Pattern pattern = Pattern.compile(format);
			Matcher matcher = pattern.matcher(input);
			
			if (matcher.find())
			{
				input = matcher.replaceAll("");
				input = input + pn.trim() + "=" + valstr + splitChar;
				// 也可用此标准方法
				//input = matcher.replaceAll(Matcher.quoteReplacement( pn.trim() + "=" + valstr + splitChar));
			}
			else
			{
				if(input.trim().length() > 0 && !input.endsWith(splitChar))
					input = input+splitChar;
				input = input + pn.trim() + "=" + valstr + splitChar;
			}
		}

		return input;
	}

	/**
	 * 将变量字符串从输入串中移除
	 * 
	 * @param input 输入串
	 * @param pn 变量字符串
	 * 
	 * @return 移除后的输入串
	 */
	public static String RemoveParaValue(String input, String pn)
	{
		String strRet = "";
		
		if (input != null && pn != null)
		{
			// 生成正则表达式
			String format = "\\b\\s*" + pn + "\\s*=\\s*[^=;]+\\s*;";

			Pattern pattern = Pattern.compile(format);
			Matcher matcher = pattern.matcher(input);
			if (matcher.find())
			{
				strRet = matcher.replaceAll("");
			}
		}
		
		return strRet;
	}
	/**
	 * 获得动态参数的第1个值
	 * 例：水位=12124545,站A;
	 * 返回 12124545
	 * @param input
	 * @param pn
	 * @return
	 */
	public static String getDynParamFirstValue(String input, String pn)
	{		
		String value = GetParaValue(input, pn);
		if(!CommonTool.isNullOrEmpty( value ))
		{
			String[] items = value.split(",");
			if(items!= null&& items.length>0)
				return items[0];
		}
		return null;
	}
	/**
	 * 获得动态参数的第2个值
	 * 例：水位=12124545,站A;
	 * 返回 站A
	 * @param input
	 * @param pn
	 * @return
	 */
	public static String getDynParamSecondValue(String input, String pn)
	{
		String value = GetParaValue(input, pn);
		if(!CommonTool.isNullOrEmpty( value ))
		{
			String[] items = value.split(",");
			if(items!= null&& items.length>1)
				return items[1];
		}
		return null;
	}
	
	/// <summary>
	/// 在规定格式的字符串中查找并返回要求的Long数组
	/// 格式如下:“12,23,45”，返回{12,23,45}Long数组
	/// </summary>
	/// <param name="param">计算参数字符串:如“12,23,45”</param>
	/// <returns>对应的单精度数组</returns>		
	public static Long[] SplitMRCalParams(String param)
	{
		if ( param == null || param.trim().length()<1)
			return null;

		//char [] c = {','};// 分隔符
		// 根据指定分隔符将计算参数字符串拆分成字符串数组
		String[] items = param.split(",");

		Long[] calParams = new Long[items.length];
		// 将字符串数组转换成单精度数组并返回
		for(int i=0;i<items.length;i++)
		{
			try
			{
				calParams[i] = Long.valueOf(items[i]);
				
			}catch (Exception e) {}
		}
		return calParams;
	}
	
	/// <summary>
		/// 在规定格式的字符串中查找并返回要求的Long数组
		/// 格式如下:“12,23,45”，返回{12,23,45}Long数组
		/// </summary>
		/// <param name="param">计算参数字符串:如“12,23,45”</param>
		/// <returns>对应的单精度数组</returns>		
		public static List<Long> SplitMRCalParamsList(String param)
		{
			if ( param == null || param.trim().length()<1)
				return null;

			//char [] c = {','};// 分隔符
			// 根据指定分隔符将计算参数字符串拆分成字符串数组
			String[] items = param.split(",");

			Long[] calParams = new Long[items.length];
			
			List<Long> ids = new ArrayList<Long>();
			// 将字符串数组转换成单精度数组并返回
			for(int i=0;i<items.length;i++)
			{
				try
				{					
					ids.add(Long.valueOf(items[i]));
					
				}catch (Exception e) {}
			}
			return ids;
		}
	
	/// <summary>
	/// Long数组 返回在规定格式的字符串
    /// 格式如下:“12,23,45”
		
	public static String LongSToString(Long[] ids)
	{
		if ( ids == null || ids.length<1)
			return null;
		
		StringBuilder sbs = new StringBuilder();
		
		for(Long id :ids)
		{
			sbs.append(id+",");
		}
		
		sbs.setLength(sbs.length()-1);

	
		return sbs.toString();
	}
	
	/// <summary>
			///  在规定格式的字符串中查找并返回要求的字符串数组
			///  格式如下:“aa,23;bb”，返回{aa,23,bb}字符串数组
			/// </summary>
			/// <param name="strFullParam">计算参数字符串:如“aa,23;bb”</param>
			/// <param name="delimiter">指定分隔符:如“，；”</param>
			/// <returns>拆分后的字符串数组</returns>
			public static String[] SplitStr(String strFullParam,String delimiter)
			{
				String[] strParams = null;
				if ( strFullParam != null )
					strParams = strFullParam.split(delimiter);
				
				if ( strParams != null )
				{
					for ( int i=0; i<strParams.length; i++ )
					{
						strParams[i] = strParams[i].trim();
					}
				}

				return strParams;
			}
			
			/*
			
			/// <summary>
			///  在规定格式的字符串中查找并返回要求的字符串数组
			///  格式如下:“aa,23;bb”，返回{aa,23,bb}字符串数组
			/// </summary>
			/// <param name="strFullParam">计算参数字符串:如“aa,23;bb”</param>
			/// <param name="delimiter">指定分隔符:如“，；”</param>
			/// <returns>拆分后的字符串数组</returns>
			public static String[] SplitStr(String strFullParam,String[] delimiter)
			{			
				if ( strFullParam == null ||delimiter == null )
					return null;
					
				List<String> all = new ArrayList<String>();
				
				
				for ( int j =0;j<delimiter.length;j++ )
				{
					String[] strParams = strFullParam.split(delimiter[j]);

					if ( strParams != null )
					{
						for ( int i=0; i<strParams.length; i++ )
						{
							
							strParams[i] = strParams[i].trim();
						}
					}
				
				}
				
				String[] strParams2 = new String[all.size()] ;
				for( int i =0;i<all.size();i++)
				{
					strParams2[i] = all.get(i);
				}

				return strParams2;
			}*/
	
	
	/// <summary>
    /// 将参数串转换成字节数组, 参数是逗号分割, 大于0,小于int范围
    /// 如: 1,4,8...转换成
    /// [0]:1 [1]:4 [2]:8 ...
    /// </summary>
    /// <param name="param">参数串</param>
    /// <returns>返回int数组</returns>
    public static int[] ParamStringToIntArr(String param)
    {
    	if (param == null)
            return null;

        //将参数分割, 参数格式: 1,2,3 逗号分割
        String[] ps = ParamString.SplitStr(param, "," );
        if (ps == null || ps.length == 0)
            return null;

        int[] bs = new int[ps.length];
        for (int i = 0; i < ps.length; ++i)
        {
            try
            {
                bs[i] = Integer.parseInt(ps[i]);
            }
            catch (Exception e) 
            {
                bs[i] = Integer.MIN_VALUE;
            }
        }
        return bs;
    }
    
			/// <summary>
			/// 合并条件字符串
			/// </summary>
			/// <param name="originalStr">原字符串</param>
			/// <param name="newStr">新的字符串</param>
			/// <returns>合并后的字符串</returns>
			public static String GetUniteString( String originalStr,String newStr  )
			{			
				// 如果新的为空返回老的字符串
				if ( newStr == null || newStr == "" )
					return originalStr;	

				// 如果老的为空返回新的字符串
				if ( originalStr == null || originalStr == "")
					return newStr;		
				
				  //将参数分割, 参数格式: 1,2,3 逗号分割
		        String[] originals = ParamString.SplitStr(originalStr, "," );
		       
		        String[] newStrs = ParamString.SplitStr(newStr, "," );

				if ( newStrs == null || newStrs.length <1 )
					return originalStr;

				List uniteList = new ArrayList();

				if ( originals != null )
				{
					for ( String origin :originals )
					{
						// 最小值是无效值
						if ( origin != null )
							// 加载老的设置
							uniteList.add(origin);
					}
				}
				// 比较看新的成员老的成员是否已存在，不存在添加
				for ( int i=0;i< newStrs.length ;i++ )
				{
					// 最小值是无效值
					if ( newStrs[i]!=null && !uniteList.contains( newStrs[i].trim() ))
						uniteList.add( newStrs[i].trim() );
				}

				// 排序
				//uniteList;

				StringBuilder sbuild = new StringBuilder();
				
				// 生成字符串如"1,2,3,4"
				for ( int i =0 ;i < uniteList.size() ; i++ )
				{
					sbuild.append( uniteList.get(i).toString() );	 

					if ( i < uniteList.size()-1 )				
						sbuild.append(",");			
								
				}

				return sbuild.toString();
			}
	
			/**
			 * 转换为双精度型数
			 * 
			 * @param val
			 * @return
			 */
			public static double todouble(String val)
			{
				double dRet = 0d;

				if (val != null && !val.isEmpty())
				{
					try
					{
						dRet = Double.parseDouble(val);
					}
					catch (Exception e)
					{
						//System.out.println(e.getMessage());

						dRet = 0d;
					}
				}

				return dRet;
			}
			
			/**
			 * 转换为整型数??
			 * 
			 * @param val
			 * @return
			 */
			public static int toint(String val)
			{
				int intRet = 0;

				if (val != null && !val.isEmpty())
				{
					try
					{
						intRet = Integer.parseInt(val);
					}
					catch (Exception e)
					{
						//System.out.println(e.getMessage());

						intRet = 0;
					}
				}

				return intRet;
			}
	
}
