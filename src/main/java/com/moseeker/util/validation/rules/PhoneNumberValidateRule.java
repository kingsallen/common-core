package com.moseeker.util.validation.rules;

import com.moseeker.exception.BaseException;
import com.moseeker.util.StringUtils;
import com.moseeker.util.validation.ValidateRule;
import lombok.extern.slf4j.Slf4j;

/**
 * 校验手机号码
 * 校验规则:11位数字
 * @author yehu
 * date 2019-09-12 15:06
 */
public class PhoneNumberValidateRule extends ValidateRule {

    /**
     * 手机号码校验规则:11位数字
     */
    private static String PHONE_REGEX="^\\d{11}$";


    public PhoneNumberValidateRule(String paramName, Object beanToValidated,
                                  String errorMessage) throws BaseException {
        super(paramName, beanToValidated, errorMessage);
    }

    @Override
    public String validate() {
        this.legal = false;
        if (this.beanToValidated != null) {
            String phone = this.beanToValidated.toString();
            if (StringUtils.isNotNullOrEmpty(phone)) {
                if (phone.matches(PHONE_REGEX)) {
                    this.legal = true;
                }
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
}
