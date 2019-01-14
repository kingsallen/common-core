package com.moseeker.util.validation.rules;


import com.moseeker.exception.BaseException;
import com.moseeker.util.StringUtils;
import com.moseeker.util.validation.ValidateRule;

/**
 * 
 * @description 字符型必填项判断
 * @author wjf
 * @date Jul 9, 2015
 * @company 大岂千寻
 * @email wjf2255@gmail.com
 */
public class RequiredStringValidateRule extends ValidateRule {
	/**
	 * 
	 * @param paramName
	 *            被校验的参数的名称
	 * @param beanToValidated
	 *            被校验的参数
	 */
	public RequiredStringValidateRule(String paramName, Object beanToValidated)
			throws BaseException {
		super(paramName, beanToValidated);
		this.errorMessage = "是必填项！";
	}

	/**
	 * 
	 * @param paramName
	 *            被校验的参数的名称
	 * @param beanToValidated
	 *            被校验的参数
	 * @param errorMessage
	 *            错误信息
	 */
	public RequiredStringValidateRule(String paramName, Object beanToValidated,
			String errorMessage) throws BaseException {
		super(paramName, beanToValidated, errorMessage);
	}

	/**
	 * 
	 * @param paramName
	 *            被校验的参数的名称
	 * @param message
	 *            校验消息（默认是参数名称+错误消息）
	 * @param beanToValidated
	 *            被校验的参数
	 */
	public RequiredStringValidateRule(String paramName, String message,
			Object beanToValidated) throws BaseException {
		super(paramName, message, beanToValidated);
		this.errorMessage = "是必填项！";
	}

	@Override
	public String validate() {
		if (this.beanToValidated instanceof String
				&& !StringUtils.isNullOrEmpty((String) this.beanToValidated)) {
			this.legal = true;
			this.message = "";
		} else {
			this.legal = false;
			if (StringUtils.isNullOrEmpty(this.message)
					&& !StringUtils.isNullOrEmpty(this.errorMessage)) {
				this.message = this.paramName + " " + errorMessage;
			}
		}
		return this.message;
	}
}
