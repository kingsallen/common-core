package com.moseeker.util.validation.rules;


import com.moseeker.exception.BaseException;
import com.moseeker.util.C;
import com.moseeker.util.StringUtils;
import com.moseeker.util.validation.SensitiveWordDB;
import com.moseeker.util.validation.ValidateRule;

/**
 * 
 * @description 敏感词验证器
 * @author wjf
 * @date Jul 12, 2015
 * @company 大岂仟寻
 * @email wjf2255@gmail.com
 */
public class SensitiveWordsValidateRule extends ValidateRule {

	//Logger logger = Logger.getLogger(SensitiveWordsValidateRule.class);

	SensitiveWordDB db = SensitiveWordDB.getSingleton(); // 敏感词过滤

	public SensitiveWordsValidateRule(String paramName, Object beanToValidated,
			String errorMessage) throws BaseException {
		super(paramName, beanToValidated, errorMessage);
	}

	public SensitiveWordsValidateRule(String paramName, Object beanToValidated)
			throws BaseException {
		super(paramName, beanToValidated);
		this.errorMessage = C.DASVALIDATE_SENSITIVEWORDS_ILLEGAL;
	}

	public SensitiveWordsValidateRule(String paramName, String message,
			Object beanToValidated) throws BaseException {
		super(paramName, message, beanToValidated);
		this.errorMessage = C.DASVALIDATE_SENSITIVEWORDS_ILLEGAL;
	}

	@Override
	public String validate() {
		if (this.beanToValidated == null
				|| StringUtils.isNullOrEmpty((String) this.beanToValidated)) {
			this.legal = true;
			this.message = "";
			return this.message;
		}
		if (db.sensitiveExamin((String) this.beanToValidated)) {
			this.legal = false;
			if (StringUtils.isNullOrEmpty(this.message)
					&& !StringUtils.isNullOrEmpty(this.errorMessage)) {
				this.message = this.paramName + this.errorMessage;
			}
			//log
			/*logger.info("Include sensitive words: " + message + " occur at "
					+ new DateTime().toString("yyyy-MM-dd HH:mm:sss SSS")
					+ "sensitivewords:" + this.beanToValidated);*/
		} else {
			this.legal = true;
			this.message = "";
		}
		return this.message;
	}

}
