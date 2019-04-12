package com.moseeker.constant;

/**
 * 
 * Common项目常用配置信息
 * <p>
 *
 * @date Mar 26, 2016
 * @company moseeker
 * @author wjf
 * @email wjf2255@gmail.com
 */
public final class Constant {

    public static final int BONUS = 2;  //默认红包金额
    public static final String POINTS_CONF_EMPLOYEE_VERIFIED = "完成员工认证";
    public static final String POINTS_CONF_REFINE_CANDIDATE= "完善被推荐人信息";
    public static final String EMPLOYEE_PARSE_PROFILE_DOCUMENT = "员工简历简历数据";
    public static final String USER_PARSE_PROFILE_DOCUMENT = "用户简历数据";

    private Constant() throws AssertionError {
        throw new AssertionError();
    };

    /** ALPHADOG系统appid 常量定义 && 共同常量定义 START **/
    public static final int APPID_ALPHADOG = 0;     // 基础服务本身
    public static final int APPID_ALPHACLOUD =301;    // 重构的基础服务本身（alphacloud）
    public static final int APPID_C = 1;            // pc端
    public static final int APPID_QX = 2;           // weixin端(聚合号)
    public static final int APPID_PLATFORM = 3;     // weixin端（企业号）
    public static final int APPID_HR = 4;           // hr
    public static final int APPID_NEOWEIXINREFER_QX = 5; // 新weixin端(聚合号)
    public static final int APPID_NEOWEIXINREFER_PLATFORM = 6; // 新weixin端(聚合号)
    public static final int APPID_NEOWEIXINREFER_HELPER = 7; // 新weixin端（招聘助手号）
    public static final int APPID_MINIAPP = 8;      // minapp（小程序）
    public static final int APPID_ATS = 20;         // ats
    public static final int APPID_SYSPLAT = 21;     // sysplat
    public static final int APPID_CRONJOB = 22;     // cronjob
    public static final int APPID_SEARCHENGINE = 23;// 搜索引擎
    public static final int APPID_CHAOS = 24;       // chaos
    public static final int APPID_EMAIL = 30;       // PYTHON Email 服务
    public static final int APPID_PARSER = 31;      // PYTHON Parser服务
    public static final int APPID_DOWNLOAD = 32;    // PYTHON download服务
    public static final int APPID_SCRAPER = 51;     // PYTHON scraper
    public static final int APPID_OMS = 60;         // 内部crm, OMS
    public static final int APPID_ANALYTICS = 101;  // 统计
    public static final int APPID_MOBOT = 102;      // mobot
    public static final int APPID_DAS = 201;        // das（以后会迁到基础服务，届时会被删除）
    public static final int APPID_PARTNER = 202;    // partner（支付宝校园招聘对接）
    public static final int APPID_IMPORT_SCRIPT = 203; // 脚本批量导入第三方简历
    public static final int APPID_PINDUODUO = 204;  // 聘多多

    public static final String DASVALIDATE_SENSITIVEWORDS_ILLEGAL = "敏感词校验失败";

    public static final int logConfigType = 3;
    public static final int cacheConfigType = 1;
    public static final int sessionConfigType = 2;
    public static final byte elkConfigType = 5;

    public static final int ENABLE = 1;
    public static final int DISABLE = 0;

    public static final int ENABLE_OLD = 0;
    public static final int DISABLE_OLD = 1;

    // status ok状态
    public static final int OK = 0;

    public static final int REDIS_CONNECT_ERROR_APPID = 0;
    public static final String REDIS_CONNECT_ERROR_EVENTKEY = "REDIS_CONNECT_ERROR";
    public static final String REDIS_CACHE_CONFIG_NOTEXIST_ERROR_EVENTKEY = "REDIS_CACHE_CONFIG_NOTEXIST_ERRO";
    // 消息模板通知 KEY_IDENTIFIER
    public static final String REDIS_KEY_IDENTIFIER_MQ_MESSAGE_NOTICE_TEMPLATE = "MQ_MESSAGE_NOTICE_TEMPLATE";

    public static final String TIPS_SUCCESS = "success";

    public static final String NONE_JSON = "{}";
    public static final String TIPS_ERROR = "error";
    //异常队列的key值
    public static final String EXCEPTION_LIST_KEY="EXCEPTION_LIST_WARN";
    //统计异常的数值用于报错
    public static final String EXCEPTION_WARN_NUM="EXCEPTION_WARN_NUM";

    /** 系统appid 常量定义 && 共同常量定义 END  **/

    /** HR系统 区域 START **/

    // 招聘进度全状态(对应config_sys_points_conf_tpl中ID)
    public static final int RECRUIT_STATUS_APPLY            = 1 ;      // 被推荐人投递简历
    public static final int RECRUIT_STATUS_INTERVIEW        = 2 ;      // HR已经安排面试
    public static final int RECRUIT_STATUS_HIRED            = 3 ;      // 入职
    public static final int RECRUIT_STATUS_REJECT           = 4 ;      // 拒绝
    public static final int RECRUIT_STATUS_INTERVIEWPENDING = 5 ;      // MMGR面试后表示先等待
    public static final int RECRUIT_STATUS_CVCHECKED        = 6 ;      // 简历被HR查看/简历被下载
    public static final int RECRUIT_STATUS_RECOMCLICK       = 7 ;      // 转发职位被点击
    public static final int RECRUIT_STATUS_CVFORWARDED      = 8 ;      // HR将简历转给MGR评审
    public static final int RECRUIT_STATUS_CVPENDING        = 9 ;      // MMGR评审后表示先等待
    public static final int RECRUIT_STATUS_CVPASSED         = 10;      // 简历评审合格
    public static final int RECRUIT_STATUS_OFFERACCEPTED    = 11;      // 接受录取通知
    public static final int RECRUIT_STATUS_OFFERED          = 12;      // 面试通过
    public static final int RECRUIT_STATUS_FULL_RECOM_INFO  = 13;      // 完善被推荐人信息
    public static final int RECRUIT_STATUS_EMPLOYEE_REGISTER  = 14;    // 完成员工员工认证
    public static final int RECRUIT_STATUS_UPLOAD_PROFILE   = 15;    // 工上传人才简历
    
    // profile来源
    public static final int PROFILE_SOURCE_UNKNOW           				= 0 ;      // 未知,
    public static final int PROFILE_SOURCE_WEIXIN_COMPANY_NOMAL            	= 1 ;      // 微信企业端(正常),
    public static final int PROFILE_SOURCE_WEIXIN_COMPANY_DILIVER        	= 2 ;      // 微信企业端(我要投递)
    public static final int PROFILE_SOURCE_WEIXIN_COMPANY_INTERESTED        = 3 ;      // 微信企业端(我感兴趣), 
    public static final int PROFILE_SOURCE_WEIXIN_TEGETHER_NOMAL           	= 4 ;      // 微信聚合端(正常),
    public static final int PROFILE_SOURCE_WEIXIN_TEGETHER_DILIVER			= 5 ;      // 微信聚合端(我要投递),
    public static final int PROFILE_SOURCE_WEIXIN_TEGETHER_INTERESTED       = 6 ;      // 微信聚合端(我感兴趣), 
    public static final int PROFILE_SOURCE_WEIXIN_COMPANY_EMAIL      		= 100 ;    // 微信企业端Email申请
    public static final int PROFILE_SOURCE_WEIXIN_TEGETHER_EMAIL     		= 101;     // 微信聚合端Email申请
    public static final int PROFILE_SOURCE_WEIXIN_COMPANY_IMPORT        	= 150;     // 微信企业端导入
    public static final int PROFILE_SOURCE_WEIXIN_TEGETHER_IMPORT         	= 151;     // 微信聚合端导入
    public static final int PROFILE_SOURCE_PC_IMPORT    					= 152;     // PC导入
    public static final int PROFILE_SOURCE_MOBILE_BROWSER 					= 153;     // 移动浏览器
    public static final int PROFILE_SOURCE_PC_CREATE          				= 200;     // PC(正常添加)
    public static final int PROFILE_SOURCE_PC_DILIVER  						= 201;     // PC(我要投递)
    public static final int PROFILE_SOURCE_PC_INTERESTED  					= 202;     // PC(我感兴趣)

    // HR用户注册来源 1:雇主 2:官网 3:微信扫描 4:我也要招人(聚合号) 5:我也要招人(企业号)
    public static final String[] HR_ACCOUNT_SIGNUP_SOURCE_ARRAY = new String[]{"HR", "WWW", "WXSCAN", "QX", "WX"};
    public static final int HR_ACCOUNT_SIGNUP_SOURCE_HR     = 1;      // 雇主
    public static final int HR_ACCOUNT_SIGNUP_SOURCE_WWW    = 2;      // 官网
    public static final int HR_ACCOUNT_SIGNUP_SOURCE_WXSCAN = 3;      // 微信扫描
    public static final int HR_ACCOUNT_SIGNUP_SOURCE_QX     = 4;      // 我也要招人(聚合号)
    public static final int HR_ACCOUNT_SIGNUP_SOURCE_WX     = 5;      // 我也要招人(企业号)


    /** HR系统 区域 END **/


    /** PROFILE 区域 START **/
    public static final int PROFILE_SOURCE_MOSEEKER_MOBILE = 1; //moseeker 手机
	public static final int PROFILE_SOURCE_PROFILE = 2; //PC PROFILE
	public static final int PROFILE_SOURCE_EMAIL = 3; //EMAIL
	public static final int PROFILE_SOURCE_IMPORT = 4; //导入
	
	 /** PROFILE 区域 END **/
	
	/** 公司类型 **/
	public static final int COMPANY_TYPE_FREE = 1; //免费用户
	public static final int COMPANY_TYPE_OTHER = 2; //其他
	public static final int COMPANY_TYPE_ENTERPRISE = 0; //企业用户
	/** 公司类型 **/
	
	/** 公司来源 **/
	//public static final int COMPANY_SOURCE_PROFILE = 9; //profile添加
	public static final int COMPANY_SOURCE_HR = 0; //HR系统
	public static final int COMPANY_SOURCE_DOWNLOAD = 1; //官网下载行业报告
	public static final int COMPANY_SOURCE_PC_EDITING = 7; //PC端添加
	public static final int COMPANY_SOURCE_WX_EDITING = 8; //微信端添加
	public static final int COMPANY_SOURCE_PC_IMPORT = 9; //PC端导入
	public static final int COMPANY_SOURCE_WX_IMPORT = 10; //微信端导入

    /** PROFILE 区域 END **/
	
	/** PROFILE 区域 START **/
    public static final int ACCOUNT_TYPE_SUPERACCOUNT 	= 0; 	//超级帐号
	public static final int ACCOUNT_TYPE_SUBORDINATE 	= 1; 	//子账号
	public static final int ACCOUNT_TYPE_NORMAL 		= 2; 	//普通帐号
	
	
	public static final String THRIFT_CONNECTION_LOST = "thrift 失去连接";
	
	public static final String EXCEPTION_USERRECORD_LOST = "计算用户帐号完整度时，用户帐号信息不能为空";
	public static final String EXCEPTION_PROFILEBASIC_LOST = "计算用户基本信息完整度时，用户基本信息不能为空";
	public static final String EXCEPTION_PROFILEWORKEXP_LOST = "计算工作经历完整度时，工作经历不能为空";
	public static final String EXCEPTION_PROFILEEDUCATION_LOST = "计算教育经历完整度时，教育经历不能为空";
	public static final String EXCEPTION_PROFILEPROJECTEXP_LOST = "计算项目经历完整度时，项目经历不能为空";
	public static final String EXCEPTION_PROFILELANGUAGE_LOST = "计算外语完整度时，外语能力不能为空";
	public static final String EXCEPTION_PROFILESKILL_LOST = "计算技能完整度时，技能不能为空";
	public static final String EXCEPTION_PROFILECREDENTIALS_LOST = "计算证书完整度时，证书不能为空";
	public static final String EXCEPTION_PROFILEAWARDS_LOST = "计算获得奖项完整度时，获得奖项不能为空";
	public static final String EXCEPTION_PROFILEWORKS_LOST = "计算作品完整度时，作品不能为空";
	public static final String EXCEPTION_PROFILINTENTION_LOST = "计算期望职位完整度时，期望职位不能为空";
	
	public static final int PROFILER_COMPLETENESS_USERUSER_MAXVALUE = 8; 		//用户信息完成度最大值
	public static final int PROFILER_COMPLETENESS_BASIC_MAXVALUE = 16; 			//简历基本信息完成度最大值
	public static final int PROFILER_COMPLETENESS_WORKEXP_MAXVALUE = 45; 		//工作经历最大值
	public static final int PROFILER_COMPLETENESS_EDUCATION_MAXVALUE = 10; 		//教育经历最大值
	public static final int PROFILER_COMPLETENESS_PROJECTEXP_MAXVALUE = 10; 	//项目经历最大值
	public static final int PROFILER_COMPLETENESS_LANGUAGE_MAXVALUE = 2; 		//外语技能最大值
	public static final int PROFILER_COMPLETENESS_SKILL_MAXVALUE = 1; 			//技能最大值
	public static final int PROFILER_COMPLETENESS_CREDENTIAL_MAXVALUE = 2;		//获得的证书完成度最大值
	public static final int PROFILER_COMPLETENESS_AWARD_MAXVALUE = 1;			//活得奖项完成度最大值
	public static final int PROFILER_COMPLETENESS_WORKS_MAXVALUE = 1; 			//作品完成度的最大值
	public static final int PROFILER_COMPLETENESS_INTENTION_MAXVALUE = 4;		//求职意愿完成度最大值
	
	public static final int DICT_CONSTANT_COMPANY_SCAL = 1102; 					//公司规模常量表parent_code值
	public static final int DICT_CONSTANT_COMPANY_PROPERTY = 1103; 				//公司性质常量表parent_code值
	
	public static final int EVENT_TYPE_EMAIL_VERIFIED = 1;						//邮件认证邮件模版
	public static final int EVENT_TYPE_EMPLOYEE_AUTH = 2; 						//员工认证邮件模板
	public static final int EVENT_TYPE_RECOMMEND_VALID_EMAIL = 3;               //推荐职位时验证邮箱
	public static final int EVENT_TYPE_RECOMMEND_POSITION_EMAIL = 4;              //推荐职位邮箱
	
	public static final String MQ_MESSAGE_EMAIL_BIZ = "MQ_MESSAGE_EMAIL_BIZ";	//业务邮件消息队列的key_identifier
	public static final String EMAIL_VERIFIED_SUBJECT = "邮箱认证";				//业务邮件消息队列的key_identifier
	
	public static final String MQ_MESSAGE_EMAIL_WARNING = "MQ_MESSAGE_EMAIL_WARNING";	//报警邮件消息队列的key_identifier
	public static final String MQ_MESSAGE_EMAIL_MANDRILL = "MQ_MESSAGE_EMAIL_MANDRILL";	//mandrill 邮件消息队列的key_identifier	
	
	public static final String EMPLOYEE_AUTH_CODE = "EMPLOYEE_AUTH_CODE"; // 员工认证 （邮箱激活链接）
    public static final String EMPLOYEE_AUTH_INFO = "EMPLOYEE_AUTH_INFO"; // 员工认证（员工认证信息）
    public static final String EMPLOYEE_DEFAULT_CUSTOM_FIELD_VALUE = "[]"; // user_employee.custom_field_value默认值

	public static final byte LOG_SMS_SENDRECORD_SYS_ALPHADOG = 5;
	
	public static final int READ_TIME_OUT = 300*1000;
	public static final int CONNECTION_TIME_OUT = 300*1000;
	
	
	public static final String WORDPRESS_POST_CUSTOMFIELD_VERSION = "version";
	public static final String WORDPRESS_POST_CUSTOMFIELD_PLATFORM = "platform";
	
	public static final int WORDPRESS_NEWSLETTER_VALUE 		= 2; 	//博客类别编号为2定义为版本更新
	
	public static final String POSITION_SYNCHRONIZATION_FAILED = "同步失败！";
	public static final String POSITION_REFRESH_FAILED = "刷新失败！";
	
	public static final int BRPOP_BLOCKING_TIMEOUT 		= 5; 	//brpop超时时间
	public static final int DESCRIPTION_LENGTH 		= 900; 	//描述长度
	
    public static final String WORDPRESS_POST_POSTSTATUS_PUBLISH = "publish";

    public static final int SMS_UPPER_LIMIT = 10; //暂时限制2条短信
    public static final int PROFILE_IMPORT_UPPER_LIMIT = 3; //没人每天简历导入次数

    public static final String HR_HEADIMG = "weixin/images/default-HR.png";
    public static final long BIND_GET_REDIS_TIMEOUT=240000;


    public static final int TEMPLATES_APPLY_NOTICE_TPL = 3; //申请成功时的消息通知ID
    public static final int TEMPLATES_NEW_RESUME_TPL = 24; // 新简历通知的消息通知ID
    public static final int EMPLOYEE_EMAILVERIFY_NOT_VERIFY_NOTICE_TPL = 79; // 新简历通知的消息通知ID

    public static final int TEMPLATES_SWITCH_APPLY_NOTICE_TPL = 29; //申请成功时 的消息通知ID
    public static final int TEMPLATES_SWITCH_NEW_RESUME_TPL = 41; //认证员工转发之后后有人投递简历 的消息通知ID

    public static final String RESUME_INFORM_HR = "resume-detail"; //简历投递给HR发邮件没有附件模板
    public static final String ANNEX_RESUME_INFORM_HR = "annex-resume-delivery-to-hr-email";
    public static final String MISMATCH_NOTIFICATION = "mismatch-notification";//简历投递不匹配发送邮件
    public static final String DELIVERY_SUCCESS = "delivery-success";//简历投递

    public static final int DICT_CONSTANT_DEGREE_USER = 3104; 		//求职者学历常量表parent_code值
    public static final int DICT_CONSTANT_GENDER_USER = 3109;       //性别常量表parent_code值
    public static final int DICT_CONSTANT_LANGUAGE_FRUENCY =3108;   //语言-掌握程度常量表parent_code值

    public static final String CDN_URL = "https://cdn.moseeker.com/";

    //微信消息模板
    public static final int EMPLOYEE_PROFILE_COMPLETION=56;
    public static final int FANS_PROFILE_COMPLETION=58;
    public static final int FANS_RECOM_POSITION=59;
    public static final int EMPLOYEE_RECOM_POSITION=57;
    public static final int POSITION_SYNC_VERIFY_INFO=66;
    public static final int AWARD_RANKING=78;

    public static final int PAGE_SIZE=20;
    public static final int PAGE_=20;

    public static final String CHINA_ID ="43";
    public static final String CHINA_CODE ="86";


    public static final int DATABASE_PAGE_SIZE=500;

    public static final int RETRY_UPPER = 3;

    //小程序简历详情路径
    public static  final String WX_APP_PROFILE_INFO_URL = "pages/profile?user_id={}";
    //简历列表的索引名称
    public static final String ES_INDEX="users";
    public static final String ES_TYPE="users";

    //talentpool邮件的config_id
    public static final int TALENTPOOL_EMAIL_PROFILE_FILTER_NOT_PASS = 70;
    public static final int TALENTPOOL_EMAIL_PROFILE_SEND = 69;

    public static final int RETRY_UPPER_LIMIT = 3;
    //
    public static final Integer[] TALENTPOOL_EMAIL_SWITCH_ID = {69,70,71,72,73};

    public static final String COMPANY_LOGO_URL ="/hr/common/images/default-company-logo.jpg";

    public static final int HKAMTW = 861;
    public static final int UPPER_LIMIT = 500;  //接口处理的数据量的上限

    public static final String LEADER_BOARD_UPVOTE_COUNT = "LEADER_BOARD_UPVOTE_COUNT";

    public static final String COMPANY_LOGO_DEFAULT = "http://o6yey4xgg.bkt.clouddn.com/common/images/default-company-logo.jpg";


    public static final String PROFILE_SEARCH_KEYWORD_COLUMS="projectexps.name;projectexps.member;projectexps.responsibility;" +
            "projectexps.company_name;projectexps.role;projectexps.achievement;projectexps.description;recent_job.job_name;" +
            "recent_job.company_scale_name;recent_job.company_name;recent_job.department_name;recent_job.company_new_name;" +
            "recent_job.job;recent_job.company_property_name;recent_job.position_name;recent_job.achievement;recent_job.description;" +
            "educations.degree_name;educations.description;educations.major_name;educations.college_name;intentions.salary_code_name;" +
            "intentions.industries.industry_name;intentions.worktype_name;intentions.workstate_name;intentions.tag;intentions.positions.position_name;" +
            "intentions.cities.city_name;intentions.consider_venture_company_opportunities_name;basic.icon_class;basic.position_name;basic.constellation;" +
            "basic.company_name;basic.email;basic.current_job;basic.weixin;basic.nationality_name;basic.city_name;basic.motto;basic.industry_name;basic.qq;" +
            "basic.name;basic.self_introduction;basic.gender_name;basic.birth_date;others.other;credentials.code;other_workexps.job_name;other_workexps.company_scale_name;" +
            "other_workexps.company_name;other_workexps.department_name;other_workexps.company_new_name;other_workexps.job;other_workexps.company_property_name;" +
            "other_workexps.position_name;other_workexps.achievement;other_workexps.description;credentials.name;credentials.organization;credentials.url;" +
            "attachments.name;attachment.description;skills.name;imports.user_name;imports.data;imports.account_id;awards.name;languages.name;works.name;works.url;" +
            "works.description";




    public static final Integer DEFAULT_PAGE_SIZE = 10;
    public static final Integer DEFAULT_PAGE_INDEX = 1;

    public static final String EMPTY = "";

    public static final Double EARTH_RADIUS = 6378.137;

    public static final Integer REDIS_OPERATION_LOCK = 500;
    public static final Integer REDIS_API_LOCK = 50;

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



    public static final String SERVER_SUCCESS_STATUS ="0";
    //=======================application服务使用的常量=====================
    //投递申请限制次数
    public static final int APPLICATION_COUNT_LIMIT = 3;

    public static final int IS_NOT_OPEN_GRPD=1;
    public static final String WORD_DOC="doc";
    public static final String WORD_DOCX = "docx";
    public static final String WORD_PDF = ".pdf";

    public static final String USERNAME_IS_NULL="未填写";

    /**  员工认证红包	 */
    public static final int HB_EMPLOYEE_TYPE = 0;
    /**  推荐评价红包	 */
    public static final int HB_RECOMMEND_TYPE = 1;
    /**  转发被点击红包	 */
    public static final int HB_CLICK_TYPE = 2;
    /**  转发被申请红包	 */
    public static final int HB_APPLICATION_TYPE = 3;
    /**  推荐候选人初筛通过	 */
    public static final int HB_INITIAL_SCEEN_TYPE = 4;

    public static final String POSITION_SYNC_FAIL_ROUTINGKEY = "messagetemplate.position_sync_fail";

    public static final String KAFKA_TOPIC_POSITION_STATUS = "radar_position_status";



}