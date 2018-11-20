package com.moseeker.util;

import com.moseeker.constant.Constant;
import com.moseeker.exception.BaseException;
import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.HanyuPinyinVCharType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;

/**
 * @category 拼音工具类
 */
public class PinyinUtil {

	/**
	 * 获取汉字的拼音（大写）
	 * 
	 * @param chineseLanguage
	 * @return
	 */
	public static final String getPinyinStringUpper(String chineseLanguage) {
		return getPinyinString(chineseLanguage, HanyuPinyinCaseType.UPPERCASE);
	}

	/**
	 * 获取汉字的拼音（小写）
	 * 
	 * @param chineseLanguage
	 * @return
	 */
	public static final String getPinyinStringLower(String chineseLanguage) {
		return getPinyinString(chineseLanguage, HanyuPinyinCaseType.LOWERCASE);
	}

	private static final String getPinyinString(String chineseLanguage, HanyuPinyinCaseType caseType) {
		char[] cl_chars = chineseLanguage.trim().toCharArray();
		String hanyupinyin = "";
		HanyuPinyinOutputFormat defaultFormat = new HanyuPinyinOutputFormat();
		defaultFormat.setCaseType(caseType);
		defaultFormat.setToneType(HanyuPinyinToneType.WITHOUT_TONE);// 不带声调
		defaultFormat.setVCharType(HanyuPinyinVCharType.WITH_V);// ü转换成v
		try {
			for (int i = 0; i < cl_chars.length; i++) {
				String str = String.valueOf(cl_chars[i]);
				if (str.matches("[\u4e00-\u9fa5]+")) {
					// 如果字符是中文，则将中文转为汉语拼音，并取第一个字母
					hanyupinyin += PinyinHelper.toHanyuPinyinStringArray(cl_chars[i], defaultFormat)[0];
				} else if (str.matches("[0-9]+")) {
					// 如果字符是数字，取数字
					hanyupinyin += cl_chars[i];
				} else if (str.matches("[a-zA-Z]+")) {
					// 如果字符是字母，取字母
					hanyupinyin += cl_chars[i];
				} else {
					// 否则不转换
				}
			}
		} catch (BadHanyuPinyinOutputFormatCombination e) {
			throw new BaseException(Constant.EMPTY, "字符不能转成汉语拼音");
		}
		return hanyupinyin;
	}

	/**
	 * 获取每个汉字的拼音的第一个字符（大写）
	 * 
	 * @param chineseLanguage
	 * @return
	 */
	public static final String getFirstLettersUpper(String chineseLanguage) {
		return getFirstLetters(chineseLanguage, HanyuPinyinCaseType.UPPERCASE);
	}

	/**
	 * 获取每个汉字的拼音的第一个字符（小写）
	 * 
	 * @param chineseLanguage
	 * @return
	 */
	public static final String getFirstLettersLower(String chineseLanguage) {
		return getFirstLetters(chineseLanguage, HanyuPinyinCaseType.LOWERCASE);
	}

	private static final String getFirstLetters(String chineseLanguage, HanyuPinyinCaseType caseType) {
		char[] cl_chars = chineseLanguage.trim().toCharArray();
		String hanyupinyin = "";
		HanyuPinyinOutputFormat defaultFormat = new HanyuPinyinOutputFormat();
		defaultFormat.setCaseType(caseType);
		defaultFormat.setToneType(HanyuPinyinToneType.WITHOUT_TONE);// 不带声调
		defaultFormat.setVCharType(HanyuPinyinVCharType.WITH_V);// ü转换成v
		try {
			for (int i = 0; i < cl_chars.length; i++) {
				String str = String.valueOf(cl_chars[i]);
				if (str.matches("[\u4e00-\u9fa5]+")) {
					// 如果字符是中文，则将中文转为汉语拼音，并取第一个字母
					hanyupinyin += PinyinHelper.toHanyuPinyinStringArray(cl_chars[i], defaultFormat)[0].substring(0, 1);
				} else if (str.matches("[0-9]+")) {
					// 如果字符是数字，取数字
					hanyupinyin += cl_chars[i];
				} else if (str.matches("[a-zA-Z]+")) {
					// 如果字符是字母，取字母
					hanyupinyin += cl_chars[i];
				} else {
					// 否则不转换
					hanyupinyin += cl_chars[i];// 如果是标点符号的话，带着
				}
			}
		} catch (BadHanyuPinyinOutputFormatCombination e) {
			throw new BaseException(Constant.EMPTY, "字符不能转成汉语拼音");
		}
		return hanyupinyin;
	}

	/**
	 * 取第一个汉字的第一个字符（大写）
	 * 
	 * @param chineseLanguage
	 * @return
	 */
	public static final String getFirstLetterUpper(String chineseLanguage) {
		return getFirstLetter(chineseLanguage, HanyuPinyinCaseType.UPPERCASE);
	}

	/**
	 * 取第一个汉字的第一个字符（小写）
	 * 
	 * @param chineseLanguage
	 * @return
	 */
	public static final String getFirstLetterLower(String chineseLanguage) {
		return getFirstLetter(chineseLanguage, HanyuPinyinCaseType.LOWERCASE);
	}

	private static final String getFirstLetter(String chineseLanguage, HanyuPinyinCaseType caseType) {
		char[] cl_chars = chineseLanguage.trim().toCharArray();
		String hanyupinyin = "";
		HanyuPinyinOutputFormat defaultFormat = new HanyuPinyinOutputFormat();
		defaultFormat.setCaseType(caseType);
		defaultFormat.setToneType(HanyuPinyinToneType.WITHOUT_TONE);// 不带声调
		defaultFormat.setVCharType(HanyuPinyinVCharType.WITH_V);// ü转换成v
		try {
			String str = String.valueOf(cl_chars[0]);
			if (str.matches("[\u4e00-\u9fa5]+")) {
				// 如果字符是中文，则将中文转为汉语拼音，并取第一个字母
				hanyupinyin = PinyinHelper.toHanyuPinyinStringArray(cl_chars[0], defaultFormat)[0].substring(0, 1);
			} else if (str.matches("[0-9]+")) {
				// 如果字符是数字，取数字
				hanyupinyin += cl_chars[0];
			} else if (str.matches("[a-zA-Z]+")) {
				// 如果字符是字母，取字母
				hanyupinyin += cl_chars[0];
			} else {
				// 否则不转换
			}
		} catch (BadHanyuPinyinOutputFormatCombination e) {
			throw new BaseException(Constant.EMPTY, "字符不能转成汉语拼音");
		}
		return hanyupinyin;
	}

}
