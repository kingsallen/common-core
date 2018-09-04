package com.moseeker.exception;

import com.moseeker.enums.BaseEnum;
import com.moseeker.enums.CommonExceptionEnum;

public class BaseException extends RuntimeException {

	private static final long serialVersionUID = -6998687397315108989L;
	private String errorCode;
	private String errorMessage;

	public BaseException() {
		super();
	}

	public BaseException(String errorCode) {
		super();
		this.errorCode = errorCode;
	}

	public BaseException(String errorCode, String errorMessage) {
		super(errorMessage);
		this.errorCode = errorCode;
		this.errorMessage = errorMessage;
	}
	
	public BaseException(CommonExceptionEnum commonExceptionEnum) {
		super(commonExceptionEnum.getValue());
		this.errorCode = commonExceptionEnum.getKey();
		this.errorMessage = commonExceptionEnum.getValue();
	}

    public BaseException(BaseEnum<?> commonExceptionEnum) {
        super(commonExceptionEnum.getValue());
        this.errorCode = commonExceptionEnum.getKey();
        this.errorMessage = commonExceptionEnum.getValue();
    }

	public BaseException(String errorCode, String errorMessage, String... args) {
		for (int i = 0; i < args.length; i++) {
			String searchStr = "{" + String.valueOf(i + 1) + "}";
			String regex = "\\{" + String.valueOf(i + 1) + "\\}";
			if (errorMessage.contains(searchStr)) {
				errorMessage = errorMessage.replaceAll(regex, args[i]);
			}
		}
		this.errorCode = errorCode;
		this.errorMessage = errorMessage;
	}
	
	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

}
