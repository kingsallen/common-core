package com.moseeker.util.validation;


import com.moseeker.enums.CommonExceptionEnum;
import com.moseeker.exception.BaseException;

/**
 * 
 * @description 校验规则
 * @author wjf
 * @date Jul 8, 2015
 * @company 大岂千寻
 * @email wjf2255@gmail.com
 */
public abstract class ValidateRule {

	protected String paramName; // 参数名称
	protected String message = ""; // 验证消息
	protected Object beanToValidated; // 被校验的对象
	protected Boolean legal; // 校验是否通过
    protected String splitString ;//分割符
	protected String errorMessage; // 错误提示消息

	public ValidateRule() {
	}

	/**
	 * 
	 * @param paramName
	 *            被校验的参数的名称
	 * @param beanToValidated
	 *            被校验的参数
	 */
	public ValidateRule(String paramName, Object beanToValidated)
			throws BaseException {
		if (paramName == null) {
			throw new BaseException(CommonExceptionEnum.error10005);
		}
		this.paramName = paramName;
		this.beanToValidated = beanToValidated;
	}

    /**
     *
     * @param paramName
     *            被校验的参数的名称
     * @param beanToValidated
     *            被校验的参数
     */
    public ValidateRule(String paramName, Object beanToValidated, String splitString, String errorMessage)
            throws BaseException {
        if (paramName == null) {
            throw new BaseException();
        }
        this.paramName = paramName;
        this.beanToValidated = beanToValidated;
        this.splitString = splitString;
        this.errorMessage = errorMessage;
    }

	/**
	 * 
	 * @param paramName
	 *            被校验的参数
	 * @param message
	 *            校验信息(默认是参数名称+错误消息)
	 * @param beanToValidated
	 *            被校验的参数
	 */
	public ValidateRule(String paramName, String message, Object beanToValidated)
			throws BaseException {
		if (paramName == null) {
			throw new BaseException();
		}
		this.paramName = paramName;
		this.message = message;
		this.beanToValidated = beanToValidated;
	}

	/**
	 * 
	 * @param paramName
	 *            被校验的参数的名称
	 * @param beanToValidated
	 *            被校验的参数
	 * @param errorMessage
	 *            错误消息
	 */
	public ValidateRule(String paramName, Object beanToValidated,
			String errorMessage) throws BaseException {
		if (paramName == null) {
			throw new BaseException();
		}
		this.paramName = paramName;
		this.errorMessage = errorMessage;
		this.beanToValidated = beanToValidated;
	}

	/**
	 * 校验规则
	 * 
	 * @return 校验信息
	 */
	public abstract String validate();

	/**
	 * 校验是否合法
	 * 
	 * @return true 合法，false 不合法
	 */
	public boolean isLegal() {
		if (legal == null) {
			throw new BaseException(CommonExceptionEnum.error10005);
		}
		return legal;
	}

	/**
	 * 获取验证消息
	 * 
	 * @return 验证消息
	 */
	public String getMessage() {
		if (legal == null) {
            throw new BaseException(CommonExceptionEnum.error10005);
		}
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public String getParamName() {
		return paramName;
	}

	public void setParamName(String paramName) {
		this.paramName = paramName;
	}

    public String getSplitString() {
        return splitString;
    }

    public void setSplitString(String splitString) {
        this.splitString = splitString;
    }
}
