package com.moseeker.util.validation.rules;


import com.moseeker.exception.BaseException;
import com.moseeker.util.StringUtils;
import com.moseeker.util.validation.ValidateRule;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 
 * @description 正则表达式校验规则
 * @author wjf
 * @date Jul 10, 2015
 * @company 大岂千寻
 * @email wjf2255@gmail.com
 */
public class RegExpressValidateRule extends ValidateRule {

	private String regExpress; // 正则表达式

	public RegExpressValidateRule(String paramName, Object beanToValidated,
			String errorMessage) throws BaseException {
		super(paramName, beanToValidated, errorMessage);
	}

	public RegExpressValidateRule(String paramName, Object beanToValidated)
			throws BaseException {
		super(paramName, beanToValidated);
		this.errorMessage = "不合符校验规则";
	}

	public RegExpressValidateRule(String paramName, String message,
			Object beanToValidated) throws BaseException {
		super(paramName, message, beanToValidated);
	}

	/**
	 * 
	 * @param beanToValidated
	 *            被校验的对象
	 * @param paramName
	 *            被校验参数的名称
	 * @param regExpress
	 *            正则表达式
	 */
	public RegExpressValidateRule(Object beanToValidated, String paramName,
			String regExpress) throws BaseException {
		super(paramName, beanToValidated);
		this.regExpress = regExpress;
	}

	@Override
	public String validate() {
		if (this.beanToValidated == null) {
			this.legal = true;
			this.message = "";
			return this.message;
		}
		this.legal = false;
		if (this.beanToValidated instanceof String) {
			Pattern p = Pattern.compile(regExpress);
			Matcher m = p.matcher((String) beanToValidated);
			if (m.find()) {
				this.legal = true;
			} else {
				this.errorMessage = "不合符规则";
			}
		} else {
			this.errorMessage = StringUtils.isNotNullOrEmpty(this.errorMessage)?this.errorMessage:"不合符规则";
		}
		if (!this.legal) {
			if (StringUtils.isNullOrEmpty(this.message)
					&& !StringUtils.isNullOrEmpty(this.errorMessage)) {
				this.message = this.paramName + this.errorMessage;
			}
		} else {
			this.message = "";
		}
		return this.message;
	}

	public String getRegExpress() {
		return regExpress;
	}

	public void setRegExpress(String regExpress) {
		this.regExpress = regExpress;
	}
}
