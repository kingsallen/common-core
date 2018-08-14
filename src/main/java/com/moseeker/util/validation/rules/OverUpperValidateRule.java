package com.moseeker.util.validation.rules;


import com.moseeker.util.C;
import com.moseeker.util.validation.ValidateRule;
import java.util.List;
import org.apache.commons.lang.StringUtils;

/**
 * @Author: jack
 * @Date: 2018/8/6
 */
public class OverUpperValidateRule extends ValidateRule {

    private List beanToValidated;
    private int upperLimit = C.UPPER_LIMIT;

    public OverUpperValidateRule(String paramName,
                                   List beanToValidated, String message, String errorMessage, Integer upperLimit) {
        this.paramName = paramName;
        this.beanToValidated = beanToValidated;
        this.setMessage(message);
        if (!StringUtils.isNotBlank(errorMessage)) {
            this.setErrorMessage(errorMessage);
        } else {
            this.setErrorMessage("数量过多,超过上限");
        }
        if (upperLimit != null && upperLimit > 0) {
            this.upperLimit = upperLimit;
        }
        this.legal = false;
    }

    public OverUpperValidateRule(String paramName,
                                 List beanToValidated) {
        this(paramName, beanToValidated, null, null, null);
    }

    public List getBeanToValidated() {
        return beanToValidated;
    }

    public void setBeanToValidated(List beanToValidated) {
        this.beanToValidated = beanToValidated;
    }

    @Override
    public String validate() {
        this.legal = false;
        if (beanToValidated == null || beanToValidated.size() < upperLimit) {
            this.legal = true;
        }

        if (!legal) {
            if (StringUtils.isBlank(this.message)
                    && !StringUtils.isBlank(this.errorMessage)) {
                this.message = this.paramName + " " + errorMessage;
            }
        } else {
            this.message = "";
        }
        return this.message;
    }
}
