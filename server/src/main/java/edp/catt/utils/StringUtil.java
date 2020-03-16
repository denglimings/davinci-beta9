package edp.catt.utils;

import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.net.*;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Map.Entry;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtil {
	
	private final static String CODE_STR = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/";
    private final static int ORGINAL_LEN = 8;
    private final static int NEW_LEN = 6;

	public static boolean checkStr(String str) {
		boolean bool = true;
		if(str == null || "".equals(str.trim()))
			bool = false;
		return bool;
	}

	public static boolean checkObj(Object obj) {
		boolean bool = true;
		//if (obj == null || "".equals(obj.toString().trim())) //任意对象转字符串，效率低，obj内容越多越会影响性能
		if (obj == null || ( (obj instanceof String ||  obj instanceof StringBuffer)  &&  "".equals(obj.toString().trim())) )
			bool = false;
		return bool;
	}

	public static String toString(Object obj) {
		return obj != null ? obj.toString().trim() : "";
	}

	public static Integer toInteger(Object obj) {
		return obj != null ? Integer.parseInt(obj.toString()) : 0;
	}

	public static int toInt(String str) {
		return "".equals(str) ? -1 : Integer.parseInt(str);
	}



	/**
	 * 是否合法IP
	 *
	 * @param ip
	 * @return
	 */
	public static boolean checkIP( String ip ) {
		if ( StringUtil.checkStr( ip ) ) {
			String reg = "^(\\d{1,2}|1\\d\\d|2[0-4]\\d|25[0-5])\\.(\\d{1,2}|1\\d\\d|2[0-4]\\d|25[0-5])\\.(\\d{1,2}|1\\d\\d|2[0-4]\\d|25[0-5])\\.(\\d{1,2}|1\\d\\d|2[0-4]\\d|25[0-5])$";
			return Pattern.compile( reg ).matcher( ip ).matches();
		}
		return false;
	}

	public static String getISOToGBK(String str){
		String strName = "";
		try{
			if(str!=null){
				strName = new String(str.getBytes("ISO8859_1"),"GBK");
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return strName;
	}

	public static String getISOToUTF8(String str){
		String strName = "";
		try{
			if(str!=null){
				strName = new String(str.getBytes("ISO8859_1"),"UTF8");
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return strName;
	}

	public static String getNowDate(){
		Date nowDate = new Date();
		Calendar now = Calendar.getInstance();
		now.setTime(nowDate);
	    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
	    String str = formatter.format(now.getTime());
	    //str=getNextDate(str,-1);
		return str;
	}

	/**
	 * //当前时间减去 30 天
	 */
	public static String getNowTimeLittleDate2(){
		return getNowTimeLittleDate(-30);
	}



	public static String getNowTime(){
		Date nowDate = new Date();
		Calendar now = Calendar.getInstance();
		now.setTime(nowDate);
	    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	    String str = formatter.format(now.getTime());
		return str;
	}

	public static long getTimeInMillis(String sDate, String eDate){
		Timestamp sd = Timestamp.valueOf(sDate);
		Timestamp ed = Timestamp.valueOf(eDate);
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(sd);
		long timethis = calendar.getTimeInMillis();

		Calendar calendar2 = Calendar.getInstance();
		calendar2.setTime(ed);
		long timeend = calendar2.getTimeInMillis();
		long thedaymillis = timeend-timethis;
		return thedaymillis;
	}

	public static String formatDateTime(String dTime) {
		String dateTime = "";
		if(dTime != null && !"".equals(dTime) && !dTime.startsWith("1900-01-01")) {
			Timestamp t = Timestamp.valueOf(dTime);
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");
			dateTime = formatter.format(t);
		}
		return dateTime;
	}

	public static String formatTime(String dTime) {
		String dateTime = "";
		if(dTime != null && !"".equals(dTime)) {
			Timestamp t = Timestamp.valueOf(dTime);
			SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss");
			dateTime = formatter.format(t);
		}
		return dateTime;
	}

	//当前日期是第几周
	public static String getWeekOfYear(){
		Date date = new Date();
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		String week = calendar.get(Calendar.WEEK_OF_YEAR)+"";
		return week;
	}

	//当前时间减去一年
	public static String getNowTimeLittle(){

		Calendar c = Calendar.getInstance();

		c.add(Calendar.YEAR, -1);

		String time = c.get(Calendar.YEAR) + "-" + (c.get(Calendar.MONTH) + 1)
				+ "-" + c.get(Calendar.DATE) + " " + c.get(Calendar.HOUR_OF_DAY)
				+ ":" + c.get(Calendar.MINUTE) + ":" + c.get(Calendar.SECOND);
        String returnstr = "";

        try{

      	   	Date d = StringUtil.parses(time,"yyyy-MM-dd HH:mm:ss");
      	   	SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

      	   	returnstr = formatter.format(d);
        }catch(Exception e){
        }
	    return returnstr;
	}

	//当前时间减去一天
	public static String getNowTimeLittleDate(int days){

		Calendar c = Calendar.getInstance();

		c.add(Calendar.DATE, days);

		String time = c.get(Calendar.YEAR) + "-" + (c.get(Calendar.MONTH) + 1)
				+ "-" + c.get(Calendar.DATE) + " " + c.get(Calendar.HOUR_OF_DAY)
				+ ":" + c.get(Calendar.MINUTE) + ":" + c.get(Calendar.SECOND);

		String returnstr = "";

		try{

	      	Date d = StringUtil.parses(time,"yyyy-MM-dd HH:mm:ss");
	      	SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd 00:00:00");

	      	returnstr = formatter.format(d);
        }catch(Exception e){

        }
		return returnstr;
	}

	//根据参数获取随机值的整位数
	public static String getRandom(int num) {
		return (Math.random()+"").substring(2, num + 2);
	}

	public static String getTimeInMillis(Date sDate, Date eDate){
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(sDate);
		long timethis = calendar.getTimeInMillis();

		Calendar calendar2 = Calendar.getInstance();
		calendar2.setTime(eDate);
		long timeend = calendar2.getTimeInMillis();
		long thedaymillis = timeend-timethis;
		return thedaymillis < 1000 ? thedaymillis + "毫秒!" : (thedaymillis/1000) + "秒钟!";
	}

	public static String showTrace() {
    	StackTraceElement[] ste = new Throwable().getStackTrace();
    	StringBuffer CallStack = new StringBuffer();

    	for (int i = 1; i < ste.length; i++) {
    		CallStack.append(ste[i].toString()+ "\n");
    		if (i > 4 ) break;
		}
    	return CallStack.toString();
    }

	public static String checkTableDefKey(String[] key, String[] value, String name) {
		String str = "";
		for(int i=0; i<key.length; i++) {
			if(name.equals(key[i])) {
				str = value[i];
				break;
			}
		}
		return str;
	}

	//判断字符串是否中文
	public static boolean isChinese(String str) {
		String regEx = "[\\u4e00-\\u9fa5]";
		Pattern p = Pattern.compile(regEx);
		Matcher m = p.matcher(str);
		return m.find();
	}

	public static String getStrToGbk(String str){
		String strName = "";
		try{
			if(str!=null){
				strName = new String(str.getBytes("UTF-8"),"GBK");
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return strName;
	}

	public static String getNextDate(String ts, int i){
		Calendar now = Calendar.getInstance();
		Timestamp t = Timestamp.valueOf(ts + " 00:00:00.000");
		now.setTime(t);
	    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
	    now.add(Calendar.DAY_OF_MONTH, +(i));
		String dt = formatter.format(now.getTime());
		return dt;
	}

	public static String getNextTime(String ts, int i){
		Calendar now = Calendar.getInstance();
		Timestamp t = Timestamp.valueOf(ts);
		now.setTime(t);
	    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	    now.add(Calendar.MINUTE, +(i));
		String dt = formatter.format(now.getTime());
		return dt;
	}

	public static String getNextMonth(String ts, int i){
		Calendar now = Calendar.getInstance();
		Timestamp t = Timestamp.valueOf(ts);
		now.setTime(t);
	    SimpleDateFormat formatter = new SimpleDateFormat("yyyyMM-dd HH:mm:ss");
	    now.add(Calendar.MONTH, +(i));
		String dt = formatter.format(now.getTime());
		return dt;
	}

	//取Unix时间戳
	public static long getUnixTime(String dateTime) {
		Date date1 = null;
		Date date2 = null;
		try {
			date1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(dateTime);
		    date2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("1970-01-01 08:00:00");
		} catch (ParseException e) {
			e.printStackTrace();
		}
        long l = (date1.getTime() - date2.getTime())/1000;
        return l;
	}

	public static String toFirstUpperCase(String str) {
		if(str == null || "".equals(str.trim()))
			return "";
		String firstChar = str.substring(0, 1).toUpperCase();
		String lastStr = str.substring(1);
		return firstChar + lastStr;
	}

	//判断一个字符串是不是数字
	public static boolean isNum(String str){
		boolean flg;
		try{Double.parseDouble(str);
			flg=true;
		}catch(Exception ex){
			flg=false;
		}
		return flg;
	}

	//去除字符串数组中的重复值
	//add by tanzhouwen
	public static String [] filterRepeat(String[]  stringArray)
	{
		ArrayList   arrayList   =   new   ArrayList();
		for (String str : stringArray) {
			if(!arrayList.contains(str))
			{
				arrayList.add(str);
			}
		}
		return  (String[]) arrayList.toArray(new String[]{});
	}

	/**
	 * 得到ID的in字句
	 * 如e.id in (1,3,4)
	 * @param ids 如"1,2,3,"等
	 * @param alias 如"e.id"等
	 * @return
	 */
	public static  String getIn300Ids(String ids,String alias) {
	    String tempS[] = ids.split(",");
	    int len = tempS.length;
	    int which = 0;
	    boolean isAnd = alias.indexOf("not")>0;
	    StringBuffer idsStr = new StringBuffer();
        idsStr.append("(");
	    if (len>300) {
	        if (len % 300 > 0) {
	            which = len / 300 + 1;
	        } else {
	            which = len / 300;
	        }
	        for (int i = 0; i < which; i++) {
	            idsStr.append(alias + " in (");
                for (int j = 300*i; j < 300*i+300; j++) {
                    if (j<len) {
                        idsStr.append(tempS[j]+",");
                    } else {
                        break;
                    }
                }
	            idsStr = idsStr.replace(idsStr.lastIndexOf(","),idsStr.length(),"");
	            if (i<which-1) {
	            	if(isAnd){
	            		idsStr.append(") and ");
	            	}else{
	            		idsStr.append(") or ");
	            	}
	            } else {
		            idsStr.append(")");
	            }
            }
	    } else {
	        idsStr.append(alias + " in (");
	        if (ids.lastIndexOf(",")==ids.length()-1) {
	            idsStr.append(ids.substring(0,ids.length()-1));
	        } else {
	            idsStr.append(ids);
	        }
	        idsStr.append(" )");
	    }
        idsStr.append(")");
	    return idsStr.toString();
	}


	/**
	 * 格式字符串
	 * 格式：a,b,v====>'a','b','v'
	 * @param str
	 * @return
	 */
	public static String getFormatString(String str){
//		String strArr[]=str.split(",");
//		String retStr="";
//		for (int i = 0; i < strArr.length; i++) {
//			if(i>0){
//				retStr=retStr+",";
//			}
//			retStr=retStr+"'"+strArr[i]+"'";
//		}
		//上面的代码循环次数越多，性能越差，所以改为下面的代码实现
		return "'" + str.replaceAll(",", "','") + "'";
	}

	public static String strRound(double value, int decimalPlaces) {
		// 声明一个返
		String rval;

		// 如果小数位是0
		if (decimalPlaces == 0) {
			rval = String.valueOf(Math.round(value));
			return rval;
		}

		// 先将参数值转为String型,并找到小数点所在位置
		DecimalFormat dformat = new DecimalFormat("0.0000000");
		String str=dformat.format(value);
		int point = str.indexOf(".");
		// 分别得到小数点之前的字符与小数点之后的字符
		String beforePoint = str.substring(0, point);
		String afterPoint = str.substring(point + 1);

		// 如果小数位正好是要求的小数位数
		if (afterPoint.length() == decimalPlaces) {
			rval = String.valueOf(value);
		} else if (afterPoint.length() < decimalPlaces) {
			// 如果小数位数少于要求的小数位数,则在后面补零
			StringBuffer sb = new StringBuffer(afterPoint);
			for (int i = 0; i < decimalPlaces - afterPoint.length(); i++) {
				sb.append("0");
			}
			// 连接
			sb.insert(0, ".").insert(0, beforePoint);
			rval = sb.toString();
		} else {
			// 如果小数位数多于要求的小数位数,则要四舍五入

			// 不管怎样,先舍
			StringBuffer sb = null;
			sb = new StringBuffer(beforePoint);
			sb.append(".").append(afterPoint.substring(0, decimalPlaces));
			String val = sb.toString();
			// 得到要舍掉的那位数
			int temp = Integer.valueOf(afterPoint.charAt(decimalPlaces) + "");
			// 如果要舍掉位置的数<4
			if (temp < 4) {
				rval = val;
			} else {
				// 如果要舍掉的位置>4

				// 构造出要加的o.XX1,如期而至2.588,保留2位小数,就要在2.5的基础上加0.01
				sb = new StringBuffer("0.1");
				for (int i = 1; i < decimalPlaces; i++) {
					sb.insert(2, "0");
				}

				// 在已经舍掉的情况下加上该补足的0.XX1
				double dbl = Double.valueOf(val)
						+ Double.valueOf(sb.toString());
				val = String.valueOf(dbl);
				// 此时加完后可能变成1,如0.99,保留1位小数,四舍五入就变成1了,所以要再判断是否小数位够位数
				// 如果没有小数位,则补足小数位
				if (val.indexOf(".") == -1) {
					val += ".";
					for (int i = 0; i < decimalPlaces; i++) {
						val += "0";
					}

					rval = val;
				} else {
					// 如果有小数位,不管怎样都补足小数位
					val = val.substring(val.indexOf(".") + 1);
					sb = new StringBuffer(dbl + "");
					for (int i = 0; i < decimalPlaces - val.length(); i++) {
						sb.append("0");
					}
					rval = sb.toString();
				}
			}
		}

		//因为double型不精确,所以最后再截一次
		point = rval.indexOf(".");
		return rval.substring(0, point + decimalPlaces + 1);

	}

	/**
     * 删除input字符串中的html格式
     * @param input
     * @return
     */
    public static String splitAndFilterString(String input) {
        if (input == null || input.trim().equals("")) {
            return "";
        }
        //去掉所有html元素,
        String str = input.replaceAll("\\&[a-zA-Z]{1,10};", "").replaceAll("<[^>]*>", "");
        //String str = input.replaceAll("<[a-zA-Z]+[1-9]?[^><]*>", "").replaceAll("</[a-zA-Z]+[1-9]?>", "");
        str = str.replaceAll("[(/>)<]", "");
        return str;
    }
    /*
     * 移自通用导入
     */
    public static boolean isNull( String str ){
    	if( str == null || str.trim().length() == 0 ) {
			return true ;
		}
    	return false ;
    }
    /*
     * 移自通用导入
     */
	public static boolean isNotNull( String str ){
    	return  !isNull( str );
    }

	/*
	 * 获取本机IP
	 */
	public static String getLocalHostAddress(){
		String ip = "Unknown";
		try {
			InetAddress inet = InetAddress.getLocalHost();
			ip = inet.getHostAddress();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
	    return ip;
    }

	/*
	 * 根据表名与ID拼为memcached里的key
	 */
	public static String getMcCacheKey(String tableName, String id) {
		return tableName + "-" + id;
	}

	

	/*
	 * 获取bean里定义的id
	 */
	public static String getBeanId(Object o) {
		Method[] ms = o.getClass().getMethods();
		for(Method m : ms) {
			Annotation[] as = m.getDeclaredAnnotations();
			for(Annotation oAnnotation : as) {
				if(oAnnotation.annotationType().getSimpleName().equals("Id")) {
					try {
						return toString(m.invoke(o, null));
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		}
		return null;
	}

	/**
	 * 把传进来的参数中两个单引号变成一个单引号
	 * @param pojo
	 */
	@SuppressWarnings("unchecked")
	public static void dropQuotes(Object pojo){
		Class cls = pojo.getClass();
		Field[] fields = cls.getDeclaredFields();
		try{
			for(Field field : fields){
				Class type = field.getType();
				if(type.getName().equals("java.lang.String")){
					String fieldName = field.getName();
					String firstLetter = fieldName.substring(0,1).toUpperCase();
					String getMethodName = "get" + firstLetter + fieldName.substring(1);
					Method m = cls.getMethod(getMethodName,new Class[]{});
					String obj = (String)m.invoke(pojo, new Object[]{});
					if(obj != null && obj.indexOf("'") != -1){
						String str = obj.replace("''","'");
						String setMethodName = "set" + firstLetter + fieldName.substring(1);
						Method setMethod = cls.getMethod(setMethodName, new Class[]{type});
						setMethod.invoke(pojo, new Object[]{str});
					}
				}
			}
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}

	/**
	 * 将list<map>里的某个属性值拼起来
	 */
	public static String appendBySplit(List l, String name, String split) {
		return appendBySplit(l, name, split, false);
	}

	/**
	 * 将list<map>里的某个属性值拼起来
	 *
	 * @param l
	 *            数据集合
	 * @param name
	 *            Map 的 key
	 * @param split
	 *            分隔符
	 * @param ignoreRepeat
	 *            是否剔除重复值
	 * @return 拼接字符串
	 * @author tangxiaolong
	 * @version 2013-12-20
	 */
	public static String appendBySplit(List l, String name, String split, boolean ignoreRepeat) {
		return appendBySplit(l, new String[] { name }, split, false, ignoreRepeat);
	}

	/**
	 * 将list<map>里的某个属性值拼起来
	 *
	 * @param list
	 *            数据集合
	 * @param names
	 *            属性关键字集合
	 * @param split
	 *            分隔符
	 * @param isStr
	 *            是否字符串
	 * @param ignoreRepeat
	 *            是否去重
	 * @return 拼接后的字符串
	 */
	public static String appendBySplit(List list, String[] names, String split, boolean isStr, boolean ignoreRepeat) {
		if (list == null || list.size() < 1) {
			return "";
		}
		StringBuilder bd = new StringBuilder();
		List<String> list2 = new ArrayList<String>();
		int size = list.size();
		Map<String, Object> row = null;
		for (int i = 0; i < size; i++) {
			row = (Map<String, Object>) list.get(i);
			for (String name : names) {
				if (StringUtil.checkObj(row.get(name))) {
					list2.add(StringUtil.toString(row.get(name)));
				}
			}
		}
		Set<String> set = null;
		if (ignoreRepeat) {// 去重
			set = new HashSet<String>(list2);
		}
		boolean isFirst = true;
		for (String s : ignoreRepeat ? set : list2) {
			if (!isFirst) {
				bd.append(split);
			} else {
				isFirst = false;
			}
			if (isStr) {
				bd.append("'").append(s).append("'");
			} else {
				bd.append(s);
			}
		}
		return bd.toString();
	}


	

	/**
	 * 获得数据库字段类型
	 * @param typeName ""
	 * @param colScale ""
	 * @return ""
	 */
    public static int getColType(String typeName, String colScale) {
    	int type = 0;
    	if("varchar".equalsIgnoreCase(typeName) || "varchar2".equalsIgnoreCase(typeName) || "text".equalsIgnoreCase(typeName)) {
    		type = 1;
    	}else if("char".equalsIgnoreCase(typeName)) {
    		type = 2;
    	}else if("datetime".equalsIgnoreCase(typeName)) {
    		type = 6;
    	}else if("date".equalsIgnoreCase(typeName)) {
    		type = 7;
    	}else if("time".equalsIgnoreCase(typeName)) {
    		type = 8;
    	}

    	if(Integer.parseInt(colScale) > 0) {
    		type = 5;
    	}else {
    		if("numeric".equalsIgnoreCase(typeName) || "long".equalsIgnoreCase(typeName)) {
        		type = 3;
        	}else if("smallint".equalsIgnoreCase(typeName) || "int".equalsIgnoreCase(typeName) || "integer".equalsIgnoreCase(typeName)) {
        		type = 4;
        	}
    	}
    	return type;
    }

    /**
     * 获取数据库数据类型
     * @param type ""
     * @param len ""
     * @param flt ""
     * @return ""
     */
    public static String getDBColType(int type, int len, int flt) {
    	String typeName = "";
    	if(type == 1) {
    		typeName = "varchar(" + len + ")";
    	}else if(type == 2) {
    		typeName = "char(" + len + ")";
    	}else if(type == 3) {
    		typeName = "numeric(" + len + ")";
    	}else if(type == 4) {
    		typeName = "int";
    	}else if(type == 5) {
    		typeName = "numeric(" + len + "," + flt + ")";
    	}else if(type == 6) {
    		typeName = "datetime";
    	}else if(type == 7) {
    		typeName = "date";
    	}else if(type == 8) {
    		typeName = "time";
    	}
    	return typeName;
    }

    /**
     * 获取java数据类型
     * @param type ""
     * @return ""
     */
    public static String getStrColType(int type) {
    	String typeName = "";
    	if(type == 1) {
    		typeName = "String";
    	}else if(type == 2) {
    		typeName = "Char";
    	}else if(type == 3) {
    		typeName = "Long";
    	}else if(type == 4) {
    		typeName = "Integer";
    	}else if(type == 5) {
    		typeName = "Double";
    	}else if(type == 6) {
    		typeName = "Date";
    	}else if(type == 7) {
    		typeName = "Date";
    	}else if(type == 8) {
    		typeName = "Date";
    	}
    	return typeName;
    }

    /**
     * 获取htc列表排序字段数据类型
     * @param type ""
     * @return ""
     */
    public static String getGridColType(int type) {
    	String typeName = "";
    	if(type == 1) {
    		typeName = "String";
    	}else if(type == 2) {
    		typeName = "String";
    	}else if(type == 3) {
    		typeName = "Number";
    	}else if(type == 4) {
    		typeName = "Number";
    	}else if(type == 5) {
    		typeName = "Number";
    	}else if(type == 6) {
    		typeName = "Date";
    	}else if(type == 7) {
    		typeName = "Date";
    	}else if(type == 8) {
    		typeName = "Date";
    	}
    	return typeName;
    }

    /**
	 * 获取字符串的中文个数
	 * @author tanjianwen
	 * @return
	 */
	public static int getChineseCount(String str) {
		return str.getBytes().length - str.length();
	}



	/**
	 * 把str转换成Base64编码
	 * @param str
	 * @return
	 */
	public static String encodeBase64(String str){
		String binStr = getBinStr(str);

		int oldByteLen = binStr.length() / ORGINAL_LEN;     //原始字节数，数字字母占1个，中文占2个
		int newByteLen = oldByteLen * 4 / 3;    //新的字节数

		String appendStr = "";                  //最后面该不该加“=”

		switch(oldByteLen % 3){
			case 0 : break;
			case 1 : newByteLen = newByteLen + 1; appendStr = "=="; break;
			case 2 : newByteLen = newByteLen + 1; appendStr = "="; break;
			default:
		}

		StringBuilder base64Str = new StringBuilder("");
		for(int k = 0; k < newByteLen; k++){
			String bin = "";
			// 二进制字符串按照6个一组分隔
			if((k+1) * NEW_LEN > binStr.length()){
				bin = binStr.substring(k * NEW_LEN, binStr.length());
			}else{
				bin = binStr.substring(k * NEW_LEN, (k+1) * NEW_LEN);
			}

			int intval = Integer.parseInt(bin, 2);  //二进制转成十进制
			if(bin.length() < NEW_LEN){
				/**
				 * 不足6位时右侧补0
				 * 0101  -- > 010100 (补2个0)
				 * 10    -- > 100000 (补4个0)
				 */
				intval <<= NEW_LEN - bin.length();
			}
			//从码表里面查找当前字符，返回字符在码表中的位置
			base64Str = base64Str.append(CODE_STR.charAt(intval));
		}
		base64Str = base64Str.append(appendStr);
		return base64Str.toString();
	}

	/**
	 * 把字符串转行成二进制，再把二进制拼接起来
	 * 返回拼接后的二进制字符串
	 * @param str
	 * @return
	 */
	public static String getBinStr(String str){
		// 采用默认语言环境的 character set。
		byte[] b = str.getBytes();
		StringBuilder sb = new StringBuilder("");
		for(byte c : b){
			String tmpStr = Integer.toBinaryString(c);
			if(tmpStr.length() > ORGINAL_LEN){
				//截取， 如： "1111111111111111111111111010101" to "11010101"
				sb.append(tmpStr.substring(tmpStr.length() - ORGINAL_LEN, tmpStr.length()));
			}else if(tmpStr.length() < ORGINAL_LEN){
				//前补0， 如： "110011" to "00110011"
				int i = 0;
				while(tmpStr.length() + i < ORGINAL_LEN){
					i++;
					sb.append("0");
				}
				sb.append(tmpStr);
			}else{
				sb.append(tmpStr);
			}
		}
		return sb.toString();
	}

	/**
	 * Base64编码还原String的实现
	 * @author liujieming
	 * @date 2010-9-29-上午11:15:24
	 * @param encodeStr
	 * @return
	 * @throws Exception
	 */
	public static String decodeBase64(String encodeStr) throws Exception{
		StringBuilder sb = new StringBuilder("");
		for (int i = 0; i < encodeStr.length(); i++){

			char c = encodeStr.charAt(i);       //把"1tC5sg=="字符串一个个分拆
			int k = CODE_STR.indexOf(c);        //分拆后的字符在CODE_STR中的位置,从0开始,如果是'=',返回-1
			if(k != -1){                        //如果该字符不是'='
				String tmpStr = Integer.toBinaryString(k);
				int n = 0;
				while(tmpStr.length() + n < NEW_LEN){
					n++;
					sb.append("0");
				}
				sb.append(tmpStr);
			}
		}

		/**
		 * 8个字节分拆一次，得到总的字符数
		 * 余数是加密的时候补的，舍去
		 */
		int newByteLen = sb.length() / ORGINAL_LEN;

		/**
		 * 二进制转成字节数组
		 */
		byte[] b = new byte[newByteLen];
		for(int j = 0; j < newByteLen; j++){
			b[j] = (byte)Integer.parseInt(sb.substring(j * ORGINAL_LEN, (j+1) * ORGINAL_LEN),2);
		}

		/**
		 * 字节数组还原成String
		 */
		return new String(b, "gb2312");
	}

	

	

	/**
	 * 去除日期字符串中毫秒部分 2012-08-31 11:06:42.425
	 * @param list
	 */
	@SuppressWarnings("unchecked")
	public static void clearMilliSecond(List list){
		if(list == null || list.size()<1){
			return;
		}
		for(Object obj:list){
			Map map=(Map)obj;
			clearMilliSecond(map);
		}
	}

	/**
	 * 去除日期字符串中毫秒部分 2012-08-31 11:06:42.425
	 * @param map 需要处理的map
	 * @author tangxiaolong
	 * @version 2014-6-20
	 */
	@SuppressWarnings("unchecked")
	public static void clearMilliSecond(Map map){
		if(map == null || map.size()<1){
			return;
		}
		//正则匹配带毫秒格式日期
		String dateReg="\\d{4}(-\\d{1,2}){2} \\d{1,2}(:\\d{1,2}){2}\\.\\d{1,3}";
		for(Object tmpObj:map.entrySet()){
			Entry entry=(Entry)tmpObj;
			if(!checkObj(entry.getValue())){
				continue;
			}
			String value=entry.getValue().toString();

			//截取毫秒前面的部分
			if(value.matches(dateReg)){
				value=value.substring(0,value.indexOf("."));
				entry.setValue(value);
			}
		}
	}


	/**
	 * 获得请求客户端的IP
	 * @param request ""
	 * @return ""
	 */
	public static String getIpAddr(HttpServletRequest request) {
		String ip = request.getHeader("x-forwarded-for");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		return ip;
	}



	/**
     * Windows下获取本机IP地址
     * @return IP地址
     */
    private static String getIPOnWindows() throws Exception{
        String ip = "";
        BufferedReader bufferedReader = null;
        Process process = null;
        try {
            process = Runtime.getRuntime().exec("ipconfig");
            bufferedReader = new BufferedReader(new InputStreamReader(process
                    .getInputStream()));
            String line = null;
            int index = -1;
            boolean isLocal = false;
            while ((line = bufferedReader.readLine()) != null) {
            	//找到本地连接信息
            	if(line.toLowerCase().indexOf("ethernet adapter 本地连接:") >= 0 ||
                		line.indexOf("以太网适配器 本地连接:") >=0 ||
                		line.indexOf("以太网适配器 以太网:") >=0){
                    isLocal = true;
                }

            	//找到IP信息
            	if(line.toLowerCase().indexOf("ipv4") >= 0 ||
            			line.toLowerCase().indexOf("ip address") >= 0){
            		if(isLocal){
            			index = line.indexOf(":");
            			ip = line.substring(index + 1).trim();
                        break;
                    }
            	}
            }
            //没有取到本地连接中的IP信息，则通过Java API来获取
            if(!StringUtil.checkStr(ip)){
            	InetAddress inet = InetAddress.getLocalHost();
                ip = inet.getHostAddress();
            }
        } catch (IOException e) {
        	throw e;
        } finally {
            try {
                if (bufferedReader != null) {
                    bufferedReader.close();
                }
            } catch (IOException e1) {
            }
            bufferedReader = null;
            process = null;
        }

        return ip;
    }


	/**
	 * 效验字符串是否符合正则表达式格式
	 * @param value
	 * @param regexp
	 * @return
	 */
	public static boolean checkRegexp(String value, String regexp) {
		return Pattern.compile(regexp).matcher(value).matches();
	}

	/**
	 * 替换特殊字符
	 * @param str 目标字符串
	 * @param repStrArr 特殊字符转换关系["&:&amp;", "<:&lt;", ">:&gt;"]
	 * @param reverse 是否反向转换
	 * @return 结果字符串
	 * @author tangxiaolong
	 * @version 20130702
	 */
	public static String replaceSpecialChar(String str, String[] repStrArr, boolean reverse){
		if(repStrArr == null || repStrArr.length == 0
				|| !StringUtil.checkStr(str)){
			return str;
		}

		//特殊字符转换
		String[] tmpArr = null;

		for(String repStr:repStrArr){
			if(!StringUtil.checkStr(repStr)){
				continue;
			}
			tmpArr = repStr.split(":");
			if(tmpArr.length != 2
					|| !StringUtil.checkStr(tmpArr[0])
					|| !StringUtil.checkStr(tmpArr[1])){
				continue;
			}
			if(reverse){
				str = str.replace(tmpArr[1], tmpArr[0]);
			}
			else{
				str = str.replace(tmpArr[0], tmpArr[1]);
			}
		}
		return str;
	}

	

	

	
	/**
	 * 获取map中的属性
	 * @param map ""
	 * @param valueName ""
	 * @return ""
	 */
	public static String getMapSingleValue(Map map,String valueName){
		String value = "";
		if(StringUtil.checkObj(map.get(valueName))){
			value = map.get(valueName).toString().trim();
		}
		return value;
	}

	/**
	 * 判断一个字符串是不是整数
	 * @param str 字符串
	 * @return 是数值返回true,否则false
	 */
	public static boolean isInteger(String str){
		str = str.trim();
		String integer = "[\\-\\+]?[0-9]+$";
		boolean flg = Pattern.compile(integer).matcher(str).matches();
		return flg;
	}

	/**
	 * 拆解电路路由时隙串(目前只适用于拆解155M的多时隙串，如AU4=3-8,12-16)
	 * @param slotStr
	 * @return
	 */
	public static String getSlotString(String slotStr){
		String delimiter = ","; 	//默认时隙分隔符
		if(slotStr.indexOf(delimiter)<0 && slotStr.indexOf("-")<0){
			return "'"+slotStr+"'";
		}
		String returnStr = "";
		StringBuilder returnSlotBuilder = new StringBuilder();	//返回的时隙字符串
		String prevSlotString = "";		//时隙字符串前缀
		int startIndex = 0;
		int endIndex = 0;
		if(slotStr.indexOf("AU4=")==0){
			try{
				prevSlotString = slotStr.substring(0,slotStr.indexOf("="));//获取时隙串前缀
				slotStr = slotStr.substring(slotStr.indexOf("=")+1,slotStr.length());
				String [] slotArray = slotStr.split(delimiter);

				for(int i = 0;i < slotArray.length; i++){
					String tempString = slotArray[i];
					String [] tempArr = tempString.split("-");
					if(tempArr.length > 1){
						startIndex = new Integer(tempArr[0]);
						endIndex = new Integer(tempArr[1]);
						//如果时隙串中未按从小到大顺序排列则进行转换(如时隙串为：AU4=8-3,16-14)
						if(startIndex > endIndex){
							int tempIndex = startIndex;
							startIndex = endIndex;
							endIndex = tempIndex;
						}
						for(int j = startIndex; j <= endIndex; j++){
							returnSlotBuilder.append("'").append(prevSlotString).append("=").append(j).append("'").append(delimiter);
						}
					}
					else{
						returnSlotBuilder.append("'").append(prevSlotString).append("=").append(tempArr[0]).append("'").append(delimiter);
					}
				}
				returnStr = returnSlotBuilder.toString();
				if(returnStr.length()>0){
					returnStr = returnStr.substring(0,returnStr.length()-1);	//截取最后一个逗号
				}
			}catch (Exception e) {
				return returnStr;
			}
		}

		return returnStr;
	}

    /**
     * 获取参数值
     *
     * @param queryString 所有参数 a=xx&b=yy
     * @param paramName   参数名
     * @return 不存在返回空串""
     * @author zhangchao
     */
    public static String getParameter(String queryString, String paramName) {
        String param = "";
        if (queryString != null && paramName != null && queryString.indexOf(paramName) >= 0) {
            String[] parameters = queryString.split("&");
            for (int i = 0; i < parameters.length; i++) {
                int pos = parameters[i].indexOf("=");
                if (pos < 0) continue;
                String name = parameters[i].substring(0, pos);
                String value = parameters[i].substring(pos + 1);
                if (name.equals(paramName)) {
                    param = value;
                    break;
                }
            }
        }
        return param;
    }



	/**
	 * 取告警查询分区条件开始结束日期for oracle
	 *
	 * @param startDate
	 *            开始日期(YYYY-MM-DD)
	 * @param endDate
	 *            结束日期(YYYY-MM-DD)
	 * @return
	 */
	public static String[] getOracleAlarmQueryPartitionStr(String startDate, String endDate) {
		String[] strs = new String[2];
		strs[0] = startDate;
		strs[1] = endDate;
		return strs;
	}

	/**
	 * 转数据类型，转完后的值，可用于SQL ？号入库
	 * @param fieldType
	 *  1||NUMBER:浮点数(Double||BigDecimal);
	 *  2||DIGIT: 整数(Integer||Long||BigDecimal);
	 *  4||DATE: 日期,不含时分秒(Timestamp);
	 *  7||DATETIME：日期，包含时分秒(Timestamp);
	 *  5||STRING：文本串(String);
	 * @param value 被转换的值
	 * @return 转换好的值
	 * */
	public static Object parseObj(String fieldType, String value){
		return parseObj(fieldType,value,null,null);
	}

    /**
	 * 按给定格式解析字符串为时间对象
	 * @param strDate
	 * @param pattern
	 * @return Date
	 * @throws ParseException
	 */
	public static Date parses(String   strDate,   String   pattern)   throws   ParseException   {
		  return new SimpleDateFormat(pattern).parse(strDate);
	}

    /**
	 * 转数据类型，转完后的值，可用于SQL ？号入库
	 * @param fieldType
	 *  1||NUMBER:浮点数(Double||BigDecimal);
	 *  2||DIGIT: 整数(Integer||Long||BigDecimal);
	 *  4||DATE: 默认返回年月日,配合format,flg参数使用,如果format,flg都为空，则返回一个不含时分秒的日期(Timestamp);
	 *  7||DATETIME：日期，包含时分秒(Timestamp);
	 *  8||TIMESTAMP：自定义年月日时分秒，由format参数决定(Timestamp);
	 *  3||5||6||STRING：文本串(String);
	 * @param value 被转换的值
	 * @param format 日期转换格式
	 * @param flg 是否在value后追加" 23:59:59"，配合fieldType=4使用
	 *        如果:flg!=false，会在value值后加 23:59:59，然后按format格式进行转换，format为空，则默认yyyy-MM-dd HH:mm:ss
	 *            使用场景：时间区间查询，结束时间若没有时分秒，则以一天最后一秒为准
	 *        否则:直接按format格式进行转换,format为空，则默认yyyy-MM-dd
	 * @return 转换好的值
	 * */
	public static Object parseObj(String fieldType, String value, String format, Boolean flg){
		Object obj = null;
		try{
			int ftype = getFiledType(fieldType);

			if (StringUtil.checkStr(value)){
				switch (ftype) {
				case 2:
					obj = (value.length()>=18)? new BigDecimal(value) : toDigit(value);
					break;// 整数
				case 1:
					obj = (value.length()>10)? new BigDecimal(value) : Double.valueOf(value);
					break;// 小数
				case 4:
					obj = getSqlDate(value, format, flg);
					break;// 日期
				case 7:
					obj = getSqlDateTime(value, format);
					break;// 日期时间
				case 8:
					obj = getSqlDateTime(value, format);
					break;// 时间，以后扩展用到
				default:
					obj = value;
				}
			}
		}catch(Exception ex){
			obj = value;
		}
		return obj;
	}

	/**
	 * 数据类型枚举文本转枚举值
	 * @param fieldType 数据类型
	 * @return 类型值
	 */
	private static int getFiledType(String fieldType){
		String type;
		if(isInteger(fieldType)){
			type = fieldType;
		}else if("NUMBER".equals(fieldType)){
			type = "1";
		}else if("DIGIT".equals(fieldType)){
			type = "2";
		}else if("DATE".equals(fieldType)){
			type = "4";
		}else if("DATETIME".equals(fieldType)){
			type = "7";
		}else if("TIMESTAMP".equals(fieldType)){
			type = "8";
		}else{
			type = "5";
		}
		return  toInt(type);
	}

	/**
	 * 日期类型
	 * @param value 被转换的值
	 * @param format 日期转换格式
	 * @param flg 是否在value后追加" 23:59:59"
	 * @return 日期时间对象
	 * @throws Exception
	 */
	private static Timestamp getSqlDate(String value, String format, Boolean flg) throws Exception {
		String str;
		if(flg != null && flg == false){
		  str = StringUtil.checkStr(format) ? format : "yyyy-MM-dd";
		}else {
		  value += " 23:59:59";
		  str = StringUtil.checkStr(format) ? format : "yyyy-MM-dd HH:mm:ss";
		}
		return getSqlDateTime(value, str);
	}

	private static Timestamp getSqlDateTime(String value) throws Exception {
		return getSqlDateTime(value, null);
	}

	/**
	 * 按格式转成日期对象
	 * @param value 被转换的值
	 * @param format 日期转换格式
	 * @return 日期时间对象
	 * @throws Exception
	 */
	private static Timestamp getSqlDateTime(String value,String format) throws Exception {
		String str = StringUtil.checkStr(format) ? format : "yyyy-MM-dd HH:mm:ss";
		Timestamp timestamp = null;
		try{
			str = StringUtil.checkStr(format) ? format : "yyyy-MM-dd HH:mm:ss";
			timestamp = new Timestamp(StringUtil.parses(value,str).getTime());
		}catch(Exception e){
			str = "yyyy-MM-dd";
			timestamp = new Timestamp(StringUtil.parses(value,str).getTime());
		}
		return timestamp;
	}

	/**
	 * 转整型,大于10位转成Long,否则Integer
	 * @param value 被转换的值
	 * @return 整数
	 */
	private static Object toDigit(String value){
		return (value.length()>=10) ? Long.valueOf(value) : StringUtil.toInt(value);
	}

	/**
	 * 获取ts加i分钟的时间
	 *
	 * @param ts 字符串时间
	 * @param i 分钟偏移量
	 * @param inFormatStr 输入时间格式
	 * @param outFormatStr 输出时间格式
	 * @return
	 */
	public static String getNextMinutes(String ts, int i, String inFormatStr, String outFormatStr) {
		SimpleDateFormat formatter = new SimpleDateFormat(inFormatStr);
		Date date;
		try {
			date = formatter.parse(ts);
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
		SimpleDateFormat formatter2 = new SimpleDateFormat(outFormatStr);
		Calendar now = Calendar.getInstance();
		now.setTime(date);
		now.add(Calendar.MINUTE, i);
		String dt = formatter2.format(now.getTime());
		return dt;
	}

	/**
	 * decode方法
	 * @param chars 要被decode的数据
	 * */
	public static String urlDecode(String chars){
		try{
			if(chars==null){chars="";}
			chars = chars.replace("％", "%"); //SSH3有XSS拦截器，可能转成全角后，导到转不回来
			chars = URLDecoder.decode(URLDecoder.decode(chars,"utf-8"),"utf-8");  //decode 2次，因为SSH3有特殊的处理，decode更多次，不会对结果有影响
		}catch(Exception ex){
			return "";
		}
		return chars;
	}

	/**
	 * Encode方法
	 * @param chars 要被Encode的数据
	 * */
	public static String urlEncode(String chars){
		try{
			if(chars==null){chars="";}
			chars = URLEncoder.encode(chars, "utf-8");  //decode 2次，因为SSH3有特殊的处理，decode更多次，不会对结果有影响
		}catch(Exception ex){
			return "";
		}
		return chars;
	}

    /**
     * 添加分号
     * @param str
     * @return
     */
    public static String addSemicolon(String str){
        String[] strs = str.split(",");
        StringBuilder sb = new StringBuilder();
        for(int i=0;i<strs.length;i++){
            sb.append("'").append(strs[i]).append("',");
        }
        sb.deleteCharAt(sb.lastIndexOf(","));
        return sb.toString();
    }

    /**
     * 按字节截取字符串
     * @param str 字符串
     * @param len 字节长度
     * @return 按len截取后的字符串， 如最后一个是中文则 取前一个结束。
     */
    public static String substringByByte(String str, int len) {
        String result = null;
        if (str != null) {
            byte[] a = str.getBytes();
            if (a.length <= len) {
                result = str;
            } else if (len > 0) {
                result = new String(a, 0, len);
                int length = result.length();
                if (str.charAt(length - 1) != result.charAt(length - 1)) {
                    if (length < 2) {
                        result = null;
                    } else {
                        result = result.substring(0, length - 1);
                    }
                }
            }
        }
        return result;
    }



    /**
	 * 将list转换为map形式
	 *
	 * @param list
	 *            list数据
	 * @param key
	 *            关键字
	 * @return Map<key, Map<Object, Object>>形式的数据
	 */
	public static Map transMapByKey(List list, String key) {
		Map map = new HashMap();
		int size = list.size();
		for (int i = 0; i < size; i++) {
			map.put(((Map) (list.get(i))).get(key), list.get(i));
		}
		return map;
	}

	/**
	 * 将list<map>里的某些属性值每num个合并成一个字符串，返回这些字符串集合
	 *
	 * @param list
	 *            数据集合
	 * @param names
	 *            属性关键字集合
	 * @param split
	 *            分隔符
	 * @param isStr
	 *            是否字符串
	 * @param ignoreRepeat
	 *            是否去重
	 * @param num
	 *            合并数
	 * @return 拼接后的字符串
	 */
	public static List<String> appendBySplit(List list, String[] names, String split, boolean isStr, boolean ignoreRepeat, int num) {
		List<String> rtList = new ArrayList<String>();
		if (list == null || list.size() < 1) {
			return rtList;
		}

		List<String> list2 = new ArrayList<String>();
		int size = list.size();
		Map<String, Object> row = null;
		for (int i = 0; i < size; i++) {
			row = (Map<String, Object>) list.get(i);
			for (String name : names) {
				if (StringUtil.checkObj(row.get(name))) {
					list2.add(StringUtil.toString(row.get(name)));
				}
			}
		}
		if (ignoreRepeat) {// 去重
			Set<String> set = new HashSet<String>(list2);
			list2.clear();
			list2.addAll(set);
		}
		boolean isFirst = true;
		StringBuilder bd = new StringBuilder();
		size = list2.size();
		for (int i = 0; i < size; i++) {
			if (isFirst) {
				isFirst = false;
			} else {
				bd.append(split);
			}
			if (isStr) {
				bd.append("'").append(list2.get(i)).append("'");
			} else {
				bd.append(list2.get(i));
			}
			if ((i % num == num - 1) || (i == size - 1)) {
				rtList.add(bd.toString());
				bd.setLength(0);
				isFirst = true;
			}
		}
		return rtList;
	}


	/**
	 * 将"1,2,3...N"的字符串转换成["1,2,3","4,5,6","7,8,9",...]
	 *
	 * @param str
	 *            原始字符串
	 * @param num
	 *            指定拼接成一个字符串的节点数
	 * @param isDistinct
	 *            是否去重
	 * @return 字符串集合
	 */
	public static List<String> appendEachNum(String str, int num, boolean isDistinct) {
		return appendEachNum(str.split(","), num, isDistinct);
	}

	/**
	 * 将[1,2,3...N]的数组转换成["1,2,3","4,5,6","7,8,9",...]
	 *
	 * @param arr
	 *            原始数组
	 * @param num
	 *            指定拼接成一个字符串的节点数
	 * @param isDistinct
	 *            是否去重
	 * @return 字符串集合
	 */
	public static List<String> appendEachNum(String[] arr, int num, boolean isDistinct) {
		List<String> list = new ArrayList<String>();
		for (String s : arr) {
			list.add(s);
		}
		return appendEachNum(list, num, isDistinct);
	}

	/**
	 * 将[1,2,3...N]的集合转换成["1,2,3","4,5,6","7,8,9",...]
	 *
	 * @param set
	 *            原始集合
	 * @param num
	 *            指定拼接成一个字符串的节点数
	 * @return 字符串集合
	 */
	public static List<String> appendEachNum(Set<String> set, int num) {
		return appendEachNum(new ArrayList<String>(set), num, false);
	}

	/**
	 * 将[1,2,3...N]的集合转换成["1,2,3","4,5,6","7,8,9",...]
	 *
	 * @param list
	 *            原始集合
	 * @param num
	 *            指定拼接成一个字符串的节点数
	 * @param isDistinct
	 *            是否去重
	 * @return 字符串集合
	 */
	public static List<String> appendEachNum(List<String> list, int num, boolean isDistinct) {
		if (isDistinct) {// 去重
			return appendEachNum(new HashSet<String>(list), num);
		} else {
			List<String> rtList = new ArrayList<String>();
			boolean isFirst = true;
			StringBuilder bd = new StringBuilder();
			int size = list.size();
			for (int i = 0; i < size; i++) {
				if (isFirst) {
					isFirst = false;
				} else {
					bd.append(",");
				}
				bd.append(list.get(i));
				if ((i % num == num - 1) || (i == size - 1)) {
					rtList.add(bd.toString());
					bd.setLength(0);
					isFirst = true;
				}
			}
			return rtList;
		}
	}

	/**
	 * 拼接List里面某属性值
	 * @param list
	 * @param key
	 * @return  key1,key2,key3,....
	 */
	public static String getAttributesStr(List<Map> list,String key){
		String str="";
		if(list.size()>0){
			for(int i=0;i<list.size();i++){
				Object value = list.get(i).get(key);
				if(checkObj(value)){
					if(str==""){
						str = value.toString();
					}else{
						str=str + "," + value.toString();
					}
				}

			}
		}
		return str;
	}

	/**
	 * 拼接List里面某属性值
	 * @param list
	 * @param key
	 * @return  'key1','key2','key3',....
	 */
	public static String getAttributesString(List<Map> list,String key){
		String str="";
		if(list.size()>0){
			for(int i=0;i<list.size();i++){
				Object value = list.get(i).get(key);
				if(checkObj(value)){
					if(str==""){
						str = "'"+value.toString()+"'";
					}else{
						str=str + "," + "'" + value.toString() + "'";
					}
				}

			}
		}
		return str;
	}

	public static List<Map<String, List<String>>> pareseGroupList(List<String> list,int groupNum){
		if(groupNum==0)throw new RuntimeException("分组数必须大于0，建议取值600至1200");
		List<Map<String, List<String>>> cuidGroup = new ArrayList<Map<String, List<String>>>();
		for(int i = 0; i < list.size(); i++){
			String cuid = list.get(i);
			if(i == 0 || (i+1) % groupNum == 0) {
				List<String> idList = new ArrayList<String>();
				Map<String, List<String>> map = new HashMap<String, List<String>>();
				map.put("list", idList);
				cuidGroup.add(map);
			}
			Map<String, List<String>> map = cuidGroup.get(i/groupNum);
			List<String> idList = map.get("list");
			idList.add(cuid);
		}
		return cuidGroup;
	}

	/**
	 * 去重：去除List里面重复的map
	 * @param list
	 * @return
	 */
	public static List removDuplicates(List<Map> list) {
		//找到哪些位置需要移除
		List<String> has = new ArrayList<String>();
		List<Integer> needRemove = new ArrayList<Integer>();
		for (int i = 0; i < list.size(); i++) {
			Map item = list.get(i);
			Set itemSet = item.entrySet();
			List<Entry<String, String>> itemSetList = new ArrayList(itemSet);
			Collections.sort(itemSetList,new Comparator<Entry<String, String>>() {

				@Override
				public int compare(Entry<String, String> o1, Entry<String, String> o2) {
					String key = o1.getKey();
					String key2 = o2.getKey();
					return key.compareTo(key2);
				}

			});
			String join = StringUtils.arrayToCommaDelimitedString(itemSetList.toArray());
//			String join = StringUtils.join(itemSetList.toArray());
			if (has.contains(join)) {
				needRemove.add(i);
			}else{
				has.add(join);
			}
		}
		//移除指定位置
		List<Map> listMap = new ArrayList<Map>();
		for (Integer index : needRemove) {
			listMap.add(list.get(index.intValue()));
		}

		for (Map map3 : listMap) {
			list.remove(map3);
		}

		return list;
	}

	/**
	 *
	 * @param ids
	 * @return
	 */
	public static String getIdsStr(String ids) {
		String idsStr = "";
		if (ids != null) {
			String idsArry[] = ids.split(",");
			for (int i = 0; i < idsArry.length; i++) {
				idsStr += "'" + idsArry[i] + "'" + ",";
			}
			idsStr = idsStr.substring(0, idsStr.length() - 1);
		}
		return idsStr;
	}



	/**
	 * 将String[]拼起来
	 */
	public static String appendBySplit(String[] arr) {
		StringBuilder bd = new StringBuilder();
		for (int i = 0; i < arr.length; i++) {
			bd.append(i == 0 ? "" : ",");
			bd.append(arr[i]);
		}
		return bd.toString();
	}

	/**
	 * 将Set<String>拼接成字符串
	 *
	 * @param set
	 *            Set对象
	 * @param split
	 *            分隔符
	 * @return 字符串
	 */
	public static String appendBySplit(Set<String> set, String split) {
		StringBuilder bd = new StringBuilder();
		boolean isFirst = true;
		for (String s : set) {
			if (isFirst) {
				isFirst = false;
			} else {
				bd.append(split);
			}

			bd.append(s);

		}
		return bd.toString();
	}
	
	
	/**
	 * 去掉字符串换行符、回车符
	 * @param str
	 * @return
	 */
	public static String replaceBlank(String str) {
		String dest = "";
		if (str!=null) {
			Pattern p = Pattern.compile("\t|\r|\n");
			Matcher m = p.matcher(str);
			dest = m.replaceAll("");
		}
		return dest;
	}
	
	/**
	 * 从url截取服务地址
	 * 
	 * @param url
	 * @return
	 */
	public static String getIP(String url) {
		// 使用正则表达式过滤，
		String re = "((http|ftp|https)://)(([a-zA-Z0-9._-]+)|([0-9]{1,3}.[0-9]{1,3}.[0-9]{1,3}.[0-9]{1,3}))(([a-zA-Z]{2,6})|(:[0-9]{1,5})?)";
		String str = "";
		// 编译正则表达式
		Pattern pattern = Pattern.compile(re);
		// 忽略大小写的写法
		// Pattern pat = Pattern.compile(regEx, Pattern.CASE_INSENSITIVE);
		Matcher matcher = pattern.matcher(url);
		// 若url==http://127.0.0.1:9040或www.baidu.com的，正则表达式表示匹配
		if (matcher.matches()) {
			str = url;
		} else {
			String[] split2 = url.split(re);
			if (split2.length > 1) {
				String substring = url.substring(0, url.length() - split2[1].length());
				str = substring;
			} else {
				str = split2[0];
			}
		}
		return str;
	}
	
	/**
	 * 文件byte字节转化为KB、MB、GB
	 * @param size
	 * @author wuzhi
	 * @return
	 */
	public static String getNetFileSizeDescription(long size) {
		StringBuilder bytes = new StringBuilder();
		DecimalFormat format = new DecimalFormat("###.0");
		double G = 2 << 29;//1024*1024*1024
		double M = 2 << 19;//1024*1024
		double K = 2 << 9;//1024
		if (size >= G) {
            bytes.append(format.format(size / G)).append("G");
        } else if (size >= M) {
            bytes.append(format.format(size / M)).append("M");
        } else if (size >= K) {
            bytes.append(format.format(size / K)).append("K");
        } else {
            if (size <= 0) {
                bytes.append("0B");
            } else {
                bytes.append((int) size).append("B");
            }
        }
        return bytes.toString();
	}

}
