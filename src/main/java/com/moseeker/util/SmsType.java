package com.moseeker.util;

/**
 * Created by moseeker on 2018/8/14.
 */
public enum SmsType{
    EMPLOYEE_MERGE_ACCOUNT_SMS(0),
    RANDOM_SMS(1),
    RANDOM_PWD_SMS(2),
    POSITION_FAV_SMS(3),
    NEW_APPLICATION_TO_HR_SMS(4),
    NEW_APPLIACATION_TO_APPLIER_SMS(5),
    APPLICATION_IS_VIEW_SMS(6),
    APPLICATION_REJECT_SMS(7),
    APPLICATION_CANCEL_REJECT_SMS(8),
    APPLICATION_APPROVED_SMS(9),
    APPLICATION_INTERVIEW_SMS(10),
    APPLICATION_ENTRY_SMS(11),
    UPDATE_SYSUSER_SMS(12),
    REGISTERED_THREE_DAYS_SMS(13),
    APPLIER_REMIND_EMAIL_ATTACHMENT_SMS(14),
    APPLIER_REMIND_EMAIL_ATTACHMENT_COM_SMS(15),
    HR_INVITE_BYPASS_ACCOUNT_SMS(16),
    HR_BYPASS_ACCOUNT_SMS(17),
    HR_BYPASS_ACCOUNT_OPEN_SMS(18),
    HR_BYPASS_ACCOUNT_REJECT_SMS(19),
    APPLIER_EMAIL_APP_SUC_SMS(20),
    APPLIER_EMAIL_APP_NO_ATTACH_SMS(21),
    APPLIER_EMAIL_APP_ATTACH_ERROR_SMS(22),
    APPLIER_EMAIL_APP_ATTACH_OVERSIZE_SMS(23),
    APPLIER_EMAIL_APP_RESOLVE_FAIL_SMS(24),
    APPLIER_EMAIL_APP_ATTACH_RESOLVE_FAIL_SMS(25),
    APPLIER_APP_ATTACH_RESOLVE_SUC_SMS(26),
    APPLIER_APP_ATTACH_RESOLVE_FAIL_SMS(27),
    APPLIER_APP_ATTACH_RESOLVE_ERROR_SMS(28),
    PPLIER_APP_ATTACH_RESOLVE_OVERSIZE_SMS(29),
    APPLIER_APP_RESOLVE_FAIL_SMS(30),
    ALARM_SMS(31);

    private final int value;

    private SmsType(int value) {
        this.value = value;
    }

    /**
     * Get the integer value of this enum value, as defined in the Thrift IDL.
     */
    public int getValue() {
        return value;
    }

    /**
     * Find a the enum type by its integer value, as defined in the Thrift IDL.
     * @return null if the value is not found.
     */
    public static SmsType findByValue(int value) {
        switch (value) {
            case 0:
                return EMPLOYEE_MERGE_ACCOUNT_SMS;
            case 1:
                return RANDOM_SMS;
            case 2:
                return RANDOM_PWD_SMS;
            case 3:
                return POSITION_FAV_SMS;
            case 4:
                return NEW_APPLICATION_TO_HR_SMS;
            case 5:
                return NEW_APPLIACATION_TO_APPLIER_SMS;
            case 6:
                return APPLICATION_IS_VIEW_SMS;
            case 7:
                return APPLICATION_REJECT_SMS;
            case 8:
                return APPLICATION_CANCEL_REJECT_SMS;
            case 9:
                return APPLICATION_APPROVED_SMS;
            case 10:
                return APPLICATION_INTERVIEW_SMS;
            case 11:
                return APPLICATION_ENTRY_SMS;
            case 12:
                return UPDATE_SYSUSER_SMS;
            case 13:
                return REGISTERED_THREE_DAYS_SMS;
            case 14:
                return APPLIER_REMIND_EMAIL_ATTACHMENT_SMS;
            case 15:
                return APPLIER_REMIND_EMAIL_ATTACHMENT_COM_SMS;
            case 16:
                return HR_INVITE_BYPASS_ACCOUNT_SMS;
            case 17:
                return HR_BYPASS_ACCOUNT_SMS;
            case 18:
                return HR_BYPASS_ACCOUNT_OPEN_SMS;
            case 19:
                return HR_BYPASS_ACCOUNT_REJECT_SMS;
            case 20:
                return APPLIER_EMAIL_APP_SUC_SMS;
            case 21:
                return APPLIER_EMAIL_APP_NO_ATTACH_SMS;
            case 22:
                return APPLIER_EMAIL_APP_ATTACH_ERROR_SMS;
            case 23:
                return APPLIER_EMAIL_APP_ATTACH_OVERSIZE_SMS;
            case 24:
                return APPLIER_EMAIL_APP_RESOLVE_FAIL_SMS;
            case 25:
                return APPLIER_EMAIL_APP_ATTACH_RESOLVE_FAIL_SMS;
            case 26:
                return APPLIER_APP_ATTACH_RESOLVE_SUC_SMS;
            case 27:
                return APPLIER_APP_ATTACH_RESOLVE_FAIL_SMS;
            case 28:
                return APPLIER_APP_ATTACH_RESOLVE_ERROR_SMS;
            case 29:
                return PPLIER_APP_ATTACH_RESOLVE_OVERSIZE_SMS;
            case 30:
                return APPLIER_APP_RESOLVE_FAIL_SMS;
            case 31:
                return ALARM_SMS;
            default:
                return null;
        }
    }
}

