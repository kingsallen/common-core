package com.moseeker.util.validation.rules;


import com.moseeker.enums.CommonExceptionEnum;
import com.moseeker.exception.BaseException;
import com.moseeker.util.StringUtils;
import com.moseeker.util.validation.ValidateRule;

/**
 * 
 * @description 字符串长度校验器。如果未指定最小值，则不校验最小值；如果不指定最大值，
 *              则不校验是否小于最大值；如果都不指定范围，只校验是否是字符串。
 * @author wjf
 * @date Jul 10, 2015
 * @company 大岂千寻
 * @email wjf2255@gmail.com
 */
public class StringSplitLengthValidateRule extends ValidateRule {

	private Integer minRange; // 下限，小于该值，提示错误
	private Integer maxRange; // 上线，大于或者等于该值，提示错误

	public StringSplitLengthValidateRule(String paramName, Object beanToValidated,
                                         String errorMessage) throws BaseException {
		super(paramName, beanToValidated, errorMessage);
	}

	public StringSplitLengthValidateRule(String paramName, Object beanToValidated)
			throws BaseException {
		super(paramName, beanToValidated);
		this.errorMessage = "必须为字符串类型数据";
	}

	public StringSplitLengthValidateRule(String paramName, String message,
                                         Object beanToValidated) throws BaseException {
		super(paramName, message, beanToValidated);
	}

	public StringSplitLengthValidateRule(String paramName, Object beanToValidated,
                                         Integer minRange, Integer maxRange, String splitString, String errorMessage)
			throws BaseException {
		super(paramName, beanToValidated, splitString, errorMessage);
		this.minRange = minRange;
		this.maxRange = maxRange;
	}

	@Override
	public String validate() {
		if (minRange != null && maxRange != null && minRange > maxRange) {
			throw new BaseException(CommonExceptionEnum.error10005);
		}
		if (this.beanToValidated == null) {
			this.legal = true;
			this.message = "";
			return this.message;
		}
		this.legal = false;
		if (this.beanToValidated instanceof String) {
		    String str = (String) this.beanToValidated;
		    String[] beanArray = str.split(splitString);
			int length = beanArray.length;
			if (minRange != null && length < minRange) {
				this.errorMessage = "必须大于或等于" + minRange + "选项";
			} else if (maxRange != null && length >= maxRange) {
				this.errorMessage = "必须小于" + maxRange + "选项";
			} else {
				this.legal = true;
			}
		}
		if (!this.legal) {
			if (StringUtils.isNullOrEmpty(this.message)
					&& !StringUtils.isNullOrEmpty(this.errorMessage)) {
				this.message = this.paramName + " " + this.errorMessage;
			}
		} else {
			this.message = "";
		}
		return this.message;
	}

	public Integer getMinRange() {
		return minRange;
	}

	public void setMinRange(Integer minRange) {
		this.minRange = minRange;
	}

	public Integer getMaxRange() {
		return maxRange;
	}

	public void setMaxRange(Integer maxRange) {
		this.maxRange = maxRange;
	}
}
