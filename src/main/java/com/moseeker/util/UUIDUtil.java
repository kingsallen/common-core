package com.moseeker.util;

import java.text.DecimalFormat;
import java.util.Random;
import java.util.UUID;

/**
 * 
 * @description UUID工具类
 * @author wjf
 * @date May 31, 2015
 * @company 大岂千寻
 * @email wjf2255@gmail.com
 */
public class UUIDUtil {

	private static Random random = new Random();
	private static MD5Util md5Util = new MD5Util();


	public UUIDUtil() {}
	
	/**
	 * 获取没有“-”符号的UUID信息
	 * @return
	 */
	public static String getUUID() {
		UUID uuid = UUID.randomUUID();
		String str = uuid.toString();
		String temp = str.substring(0, 8) + str.substring(9, 13) + str.substring(14, 18) + str.substring(19, 23) + str.substring(24);
		return temp;
	}
	
	/**
	 * 获取没有“-”符号的UUID数组。
	 * @param length 数组的长度
	 * @return
	 */
	public static String[] getUUID(int length) {
		if(length < 1) {
			return null;
		}
		String[] strArray = new String[length];
		for(int i=0; i< length; i++) {
			strArray[i] = getUUID();
		}
		return strArray;
	}

	/**
	 * 根据数据库ID生成28位的唯一码
	 * @param id 数据库编号
	 * @return 唯一码
	 */
	public static String get28UUID(int id) {
		StringBuffer stringBuffer = new StringBuffer();
		String time = getTime9();
		String ID = getID(id);
		String random = getRandom();
		//9时间毫秒 + 11位数据库ID + 8 位随机数
		for (int i=0; i<8; i++) {
			stringBuffer.append(random.charAt(i)).append(ID.charAt(i)).append(time.charAt(i));
			if (i == 7) {
				stringBuffer.append(time.charAt(8)).append(ID.substring(8));
			}
		}
		return stringBuffer.toString();
	}

	private static String getRandom() {
		int i = random.nextInt(10000);
		String id = generateFixLengthString(String.valueOf(i), 4);
		return md5Util.encryptPBKDF2(id).substring(0, 8);
	}

	private static String getID(int id) {
		return generateFixLengthString(String.valueOf(id), 11);
	}

	private static String generateFixLengthString(String str, int length) {
		if (str.length() > length) {
			return str.substring(str.length() - length);
		} else if (str.length() < length) {
			StringBuffer stringBuffer = new StringBuffer();
			for (int i=0; i< length - str.length(); i++) {
				stringBuffer.append("0");
			}
			return stringBuffer.append(str).toString();
		} else {
			return str;
		}
	}

	/**
	 * 获取9位的时间毫秒数。
	 * 如果超过9位，从后往前取9位
	 * 如果小于9位，前面补充0
	 * @return 时间
	 */
	private static String getTime9() {
		String time = String.valueOf(System.currentTimeMillis());
		if (time.length() > 9) {
			return time.substring(time.length() - 9);
		} else if (time.length() < 9) {
			StringBuffer stringBuffer = new StringBuffer(time.length() - 9);
			for (int i=0; i< time.length() - 9; i++) {
				stringBuffer.append("0");
			}
			return stringBuffer.append(time).toString();
		} else {
			return time;
		}
	}
	
	public static String get64UUID() {
		StringBuffer sb = new StringBuffer(64);
		String uuid = getUUID();
		MD5Util md5Util = new MD5Util();
		String md5SHA1 = md5Util.encryptSHA(uuid);
		//产生9位的随机数
		int max = 999999999;
		int min = 100000000;
		Random random = new Random();
        int activation_code = random.nextInt(max)%(max-min+1) + min;
        //当前系统时间
        String time = String.valueOf(System.currentTimeMillis());
		sb.append(uuid);
		if(time.length() > 10) {
			sb.append(time.substring(time.length()-11, time.length()-1));
		} else {
			DecimalFormat df=new DecimalFormat("0000000000");
			sb.append(df.format(time));
		}
		sb.append(activation_code);
		sb.append(md5SHA1.substring(0, 13));
		return sb.toString();
	}
}
