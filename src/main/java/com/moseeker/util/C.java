package com.moseeker.util;

/**
 * @category 常量类
 */
public final class C {
	
	public static final Integer DEFAULT_PAGE_SIZE = 10;
	public static final Integer DEFAULT_PAGE_INDEX = 1;
	
	public static final String EMPTY = "";

	// activemq配置
	public static final String ACTIVEMQ_QUEUE_DEFAULT = "default";// 默认队列
	public static final String ACTIVEMQ_QUEUE_BASIS_EMAIL = "basis_email";// 发邮件
	public static final String ACTIVEMQ_QUEUE_BASIS_SMS = "basis_sms";// 发短信
	public static final String ACTIVEMQ_QUEUE_LOGGER_INTERFACE = "logger_interface";// 接口日志
	public static final String ACTIVEMQ_QUEUE_LOGGER_QUARTZ = "logger_quartz";// 定时任务日志
	public static final String ACTIVEMQ_QUEUE_LOGGER_VISIT = "logger_visit";// 访问记录
	public static final String ACTIVEMQ_QUEUE_SYSTEM_USER_HISTORY = "system_user_history";// 平台用户操作历史
	public static final String ACTIVEMQ_QUEUE_BASIS_ATTACHMENT_DOWNLOAD = "basis_attachment_download";// 附件下载次数
	public static final String ACTIVEMQ_QUEUE_BASIS_ATTACHMENT_UPLOAD_QINIU = "basis_attachment_upload_qiniu";// 七牛附件上传
	public static final String ACTIVEMQ_QUEUE_BASIS_WECHART_MESSAGE = "basis_wechart_message";// 微信公众号消息推送

	public static final Double EARTH_RADIUS = 6378.137;

	public static final Integer REDIS_OPERATION_LOCK = 500;
	public static final Integer REDIS_API_LOCK = 50;

	// 项目配置
	public static final String COMPANY = "";
	public static final String SYSTEM = "";
	public static final String VERSION = "1.0.0";
	public static final String DEVELOPER = "caizhh";
	public static final Boolean SHOW_SQL = Boolean.TRUE;

	// DateUtil
	public static final String FORMAT_YYYYMMDD = "yyyy-MM-dd";
	public static final String FORMAT_HHMISS = "HH:mm:ss";
	public static final String FORMAT_YYMMDD = "yyMMdd";
	public static final String FORMAT_YYYYMM = "yyyyMM";
	public static final String FORMAT_YYYYMMDD_HHMISS = "yyyy-MM-dd HH:mm:ss";
	public static final String FORMAT_YYYYMMDD_HHMISS_SSS = "yyyy-MM-dd HH:mm:ss:SSS";
	public static final String FORMAT_YYYYMMDD_CN = "yyyy年MM月dd日";
	public static final String FORMAT_HHMISS_CN = "HH时mm分ss秒";
	public static final String FORMAT_YYYYMMDD_HHMISS_CN = "yyyy年MM月dd日 HH时mm分ss秒";
	public static final String FORMAT_YYYYMMDD_HHMISS_SSS_CN = "yyyy年MM月dd日 HH时mm分ss秒 SSS毫秒";

	// session有效期（秒）
	public static final Integer SESSION = 3600;

	public static final String INFINITE_DATE = "2099-12-31";

	public static final String MONITOR_CONFIG_USERNAME = "root";// 用户名
	public static final String MONITOR_CONFIG_PASSWORD = "";// 密码
	public static final String MONITOR_CONFIG_FILEPATH = "/home/config/ssh";// 私钥文件
	public static final String MONITOR_CONFIG_HOST = "192.168.5.213";// 服务器地址
	public static final Integer MONITOR_CONFIG_PORT = 22;// 端口号
	public static final Integer MONITOR_CONFIG_TIMEOUT = 60000000;// 处理超时

	// 常量
	public static final String YES = "yes";
	public static final String NO = "no";

	// 排序
	public static final String FIELD_ID = "id";
	public static final String FIELD_REMARK = "remark";
	public static final String FIELD_DELETED = "deleted";
	public static final String FIELD_CREATEBY = "createBy";
	public static final String FIELD_CREATETIME = "createTime";
	public static final String FIELD_UPDATEBY = "updateBy";
	public static final String FIELD_UPDATETIME = "updateTime";
	public static final String FIELD_VERSION = "version";
	
	public static final String ORDER_ASC = "asc";
	public static final String ORDER_DESC = "desc";

	// Po定义数据库字段的长度
	public static final int COLUMN_LENGTH_LONG = 11;
	public static final int COLUMN_LENGTH_INTEGER = 11;
	public static final int COLUMN_LENGTH_STRING = 200;
	public static final int COLUMN_LENGTH_ENUM = 50;
	public static final int COLUMN_LENGTH_BIGDECIMAL = 20;

	public static final int PRECISION_PRICE = 2;// 单价的小数位数
	public static final int PRECISION_AMOUNT = 2;// 金额的小数位数
	public static final int PRECISION_QUANTITY = 2;// 数量的小数位数
	public static final int PRECISION_RATE = 4;// 比率（折扣率、税率等）的小数位数
	public static final int PRECISION_CURRENCY = 0;// 虚拟货币（家币、积分等）的小数位数
	public static final int PRECISION_ITUDE = 10;// 经纬度的小数位数

}
