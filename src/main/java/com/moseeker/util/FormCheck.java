package com.moseeker.util;

import com.moseeker.exception.BaseException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

public class FormCheck {

	private static final BaseException PARAM_NULL_EXPCEPTION = new BaseException("9999","参数不能为空！");
    private static String MOBILE_EXP = "^[1][3,4,5,6,7,8,9][0-9]{9}$";

    public static String getMobileExp() {
        return MOBILE_EXP;
    }

	/**
	 * @param str
	 *            被校验的字符串
	 * @return 是否校验通过，true：包含特殊字符；false：不包含特殊字符
	 * @throws PatternSyntaxException
	 *             校验规则异常，主要由于正则表达式出错
	 */
	public static boolean specialCharactor(String str)
			throws PatternSyntaxException, BaseException {
		if (str == null || str.trim().equals("")) {
			throw PARAM_NULL_EXPCEPTION;
		}
		// 只允许字母和数字
		// String regEx = "[^a-zA-Z0-9]";
		// 清除掉所有特殊字符
		String regEx = "[`~!@#$%^&*()+=|{}':;',\\[\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？]";
		Pattern p = Pattern.compile(regEx);
		Matcher m = p.matcher(str);
		return m.find();
	}

	/**
	 * 判断是否包含特殊字符
	 *
	 * @param str
	 *            被校验的字符串
	 * @return 是否校验通过，true：包含特殊字符；false：不包含特殊字符
	 * @throws PatternSyntaxException
	 *             校验规则异常，主要由于正则表达式出错
	 */
	public static boolean shortSpecialCharactor(String str)
			throws PatternSyntaxException, BaseException {
		if (str == null || str.trim().equals("")) {
			throw PARAM_NULL_EXPCEPTION;
		}
		String regEx = "[~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？]";
		Pattern p = Pattern.compile(regEx);
		Matcher m = p.matcher(str);
		return m.find();
	}

	/**
	 * 判断是否由6-20位的ascii编码组成的字符串
	 *
	 * @param str
	 *            被校验的对象
	 * @return 是否校验通过，true：由6-20位的ascii编码组成的字符串；false：由6-20位的ascii编码组成的字符串
	 * @throws PatternSyntaxException
	 *             校验规则异常，主要由于正则表达式出错
	 */
	public static boolean pwdSpecialCharactor(String str)
			throws PatternSyntaxException, BaseException {
		if (str == null || str.trim().equals("")) {
			throw PARAM_NULL_EXPCEPTION;
		}
		String regEx = "^[\\x21-\\x73]{6,20}$";
		Pattern p = Pattern.compile(regEx);
		Matcher m = p.matcher(str);
		return m.find();
	}

	/**
	 * 判断是否是邮箱
	 *
	 * @param str
	 * @return 校验规则异常，主要由于正则表达式出错
	 */
	public static boolean isEmail(String str) throws BaseException {
		if (str == null || str.trim().equals("")) {
			throw PARAM_NULL_EXPCEPTION;
		}
		String regEx = "^([\\w-])+(\\.[\\w-]+)*@([\\w-])+((\\.[\\w-]{2,})+)$";
		Pattern p = Pattern.compile(regEx);
		Matcher m = p.matcher(str);
		return m.find();
	}

	/**
	 * 判断是否是URL地址。不精确判断
	 *
	 * @param str
	 *            被校验的对象
	 * @return 是否校验通过，true：由6-20位的ascii编码组成的字符串；false：由6-20位的ascii编码组成的字符串
	 */
	@Deprecated
	public static boolean isURL(String str) throws BaseException {
		if (str == null || str.trim().equals("")) {
			throw PARAM_NULL_EXPCEPTION;
		}
		String regEx = "[a-zA-z]+://[^\\s]*";
		Pattern p = Pattern.compile(regEx);
		Matcher m = p.matcher(str);
		return m.find();
	}

	/**
	 * 判断的依据是unicode中文字符的范围
	 *
	 * @param str
	 *            被校验的对象
	 * @return
	 */
	public static boolean isChineseCharactor(String str)
			throws BaseException {
		if (str == null || str.trim().equals("")) {
			throw PARAM_NULL_EXPCEPTION;
		}
		String regEx = "^[\u4e00-\u9fa5]+$";
		Pattern p = Pattern.compile(regEx);
		Matcher m = p.matcher(str);
		return m.find();
	}

	/**
	 * 判断是否是电话号码
	 *
	 * @param str
	 * @return
	 */
	public static boolean isPhone(String str) throws BaseException {
		if (str == null || str.trim().equals("")) {
			throw PARAM_NULL_EXPCEPTION;
		}
		String regEx = "^\\d{3}-\\d{8}|\\d{4}-\\d{7}$";
		Pattern p = Pattern.compile(regEx);
		Matcher m = p.matcher(str);
		return m.find();
	}

	public static boolean isCharacter(String str) throws BaseException {
		if (StringUtils.isNullOrEmpty(str)) {
			throw PARAM_NULL_EXPCEPTION;
		}
		String regEx = "^[a-zA-z]*$";
		Pattern p = Pattern.compile(regEx);
		Matcher m = p.matcher(str);
		return m.find();
	}

	/**
	 * 判断是否是手机号码
	 *
	 * @param str
	 * @return
	 */
	public static boolean isMobile(String str) throws BaseException {
		if (str == null || str.trim().equals("")) {
			throw PARAM_NULL_EXPCEPTION;
		}
		Pattern p = Pattern.compile(MOBILE_EXP);
		Matcher m = p.matcher(str);
		return m.find();
	}

	public static boolean isNumber(String str) throws BaseException {
		if (str == null || str.trim().equals("")) {
			throw PARAM_NULL_EXPCEPTION;
		}
		String regEx = "^[-]{0,1}[0-9]+\\.{0,1}[0-9]{0,2}$";
		Pattern p = Pattern.compile(regEx);
		Matcher m = p.matcher(str);
		return m.find();
	}

	public static boolean isInteger(String str) throws BaseException {
		if (str == null || str.trim().equals("")) {
			throw PARAM_NULL_EXPCEPTION;
		}
		String regEx = "^([\\-]{0,1}[1-9]+[0-9]*)$|^0$";
		Pattern p = Pattern.compile(regEx);
		Matcher m = p.matcher(str);
		return m.find();
	}

	public static boolean isDate(String str) throws BaseException {
		if (str == null || str.trim().equals("")) {
			throw PARAM_NULL_EXPCEPTION;
		}
		String regEx = "^(([0-9]{3}[1-9]|[0-9]{2}[1-9][0-9]{1}|[0-9]{1}[1-9][0-9]{2}|[1-9][0-9]{3})-"
				+ "(((0[13578]|1[02])-(0[1-9]|[12][0-9]|3[01]))|((0[469]|11)-(0[1-9]|[12][0-9]|30))|"
				+ "(02-(0[1-9]|[1][0-9]|2[0-8]))))$|^((([0-9]{2})(0[48]|[2468][048]|[13579][26])|"
				+ "((0[48]|[2468][048]|[3579][26])00))-02-29)$";
		Pattern p = Pattern.compile(regEx);
		Matcher m = p.matcher(str);
		return m.find();
	}

	public static boolean isDateTime(String str) throws BaseException {
		if (str == null || str.trim().equals("")) {
			throw PARAM_NULL_EXPCEPTION;
		}
		String regEx = "(([0-9]{3}[1-9]|[0-9]{2}[1-9][0-9]{1}|[0-9]{1}[1-9][0-9]{2}|[1-9][0-9]{3})-"
				+ "(((0[13578]|1[02])-(0[1-9]|[12][0-9]|3[01]))|((0[469]|11)-(0[1-9]|[12][0-9]|30))|"
				+ "(02-(0[1-9]|[1][0-9]|2[0-8]))))|((([0-9]{2})(0[48]|[2468][048]|[13579][26])|"
				+ "((0[48]|[2468][048]|[3579][26])00))-02-29)\\s{1,3}([0-1]{1}[0-9]{1}|[2]{1}[0-3]{1}):[0-5]{1}[0-9]{1}:[0-5]{1}[0-9]{1}";
		Pattern p = Pattern.compile(regEx);
		Matcher m = p.matcher(str);
		return m.find();
	}

	//@Test
	public void pwTest() {
		String str = "123【】6!@#$%^&*()-=_+";
		System.out.println(pwdSpecialCharactor(str));
	}

	//@Test
	public void isEmailTest() {
		// String str = "wengjianfei@moseeker.com";
		// String str = "hello@moseeker";
		// String str = "hello@moseeker.";
		// String str = "@moseeker.com";
		String str = "wengjianfei@mosee.com.cn";
		System.out.println(isEmail(str));
	}

	//@Test
	public void isIntegerTest() {
		String str = "011";
		System.out.println(isInteger(str));
	}

	//@Test
	public void isDateTest() {
		String date = "2012-02-30";
		System.out.println("date." + isDate(date));
	}

	//@Test
	public void isDateTimeTest() {
		String date = "2012-02-29 23:59:59";
		System.out.println("date." + isDateTime(date));
	}
}
