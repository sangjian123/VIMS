package com.vms.constant;

/**
 * 常量类
 *
 * @author xugangfeng
 * @version [版本号, 2016-11-11]
 * @see [相关类/方法]
 * @since [V100R001]
 */
public interface GeneralConstant
{
    
    /**
     * 文件类型.xls
     */
    String FILE_TYPE_XLS = ".xls";
    
    /**
     * 文件类型.pdf
     */
    String FILE_TYPE_PDF = ".pdf";
    
    String YMDHH24MISS = "YYYYMMDD HH24MISS";
    
    /**
     * 8位时间格式 日期部分 MMddyyyy
     */
    String DATETIME_EN_8 = "MMddyyyy";
    
    /**
     * 8位时间格式 日期部分 MMddyyyy
     */
    String DATETIME_EN_10 = "MM-dd-yyyy";
    
    /**
     * 8位时间格式 日期部分 MMddyyyy
     */
    String DATETIME_EN_14 = "MM-dd-yyyy HH:mm:ss";
    
    /**
     * 18位时间格式 yyyyMMddHHmmss.SSS
     */
    String DATETIME_18 = "yyyyMMddHHmmss.SSS";
    
    /**
     * 18位时间格式 YYYYMMDDHHmmssnnnn 应用端时间戳使用
     */
    String DATETIME_18_A = "yyyyMMddHHmmssSSSS";
    
    /**
     * 17位时间格式 yyyyMMddHHmmssSSS
     */
    String DATETIME_17 = "yyyyMMddHHmmssSSS";
    
    /**
     * 14位时间格式 yyyyMMddHHmmss
     */
    String DATETIME_14 = "yyyyMMddHHmmss";
    
    /**
     * 14位时间格式 yyyy-MM-dd HH:mm:ss
     */
    String DATETIME_14_COMMON = "yyyy-MM-dd HH:mm:ss";
    
    /**
     * 15位时间格式 yyyy-MM-dd'T'HH:mm:ss
     */
    String DATETIME_15 = "yyyy-MM-dd'T'HH:mm:ss";
    
    /**
     * 16位时间格式 yyyyMMdd'T'HHmmss'Z'
     */
    String DATETIME_16 = "yyyyMMdd'T'HHmmss'Z'";
    
    /**
     * 12位时间格式 yyyy-MM-dd HH:mm
     */
    String DATETIME_12_EN = "MM-dd-yyyy HH:mm";
    
    /**
     * 10位时间格式 日期部分 yyyy-MM-dd
     */
    String DATETIME_10 = "yyyy-MM-dd";
    
    /**
     * 8位时间格式 日期部分 yyyyMMdd
     */
    String DATETIME_8 = "yyyyMMdd";
    
    /**
     * 6位时间格式 日期部分 yyyymm
     */
    String DATE_6 = "yyyyMM";
    
    /**
     * 6位时间格式 时间部分 HHmmss
     */
    String DATETIME_6 = "HHmmss";
    
    /**
     * 0000
     */
    String TIMESTAMP_PATTON_SUFFIX_18 = "0000";
    
    String HTTP = "http://";
    
    /**
     * 冒号
     */
    String COLON = ":";
    
    /**
     * 分隔符
     */
    String COMMA = ",";
    
    /**
     * 24(小时)
     */
    float TWENTY_FOUR = 24f;
    
    /**
     * 60(分钟或秒)
     */
    float SIXTY = 60f;
    
    /**
     * 1000(毫秒)
     */
    float THOUSAND = 1000f;
    
    /**
     * 系统默认编码格式
     */
    String CHARACTER_CODING = "UTF-8";
    
    /**
     * 编码GBK
     */
    String CHARACTER_GBK = "GBK";
    
    /**
     * xml标记
     */
    String XML_TG = "$xml";
    
    /**
     * xml标记
     */
    String XMLDATA_TG = "xmldata";
    
    /**
     * text/html
     */
    String TXT_TEST = "text/html";
    
    /**
     * 路径分割符
     */
    String SEPARATOR = "/";
    
    /**
     * 获取$ 消息模板用到 by wangling
     */
    String CONFIG = "$";
    
    /**
     * 配置文件路径
     */
    String WEB_INFO = "WEB-INF";
    
    /**
     * 当前页码
     */
    int INIT_CURRENT_PAGE = 1;
    
    /**
     * FTP协议路径前缀
     */
    String PROTOCOL_FTP = "ftp://";
    
    /**
     * Socket接收数据缓冲区
     */
    int SOCKET_RECEIVE_BUFFER_SIZE = 1048576;
    
    /**
     * 英文的;
     */
    String SEMICOLON = ";";
    
    /**
     * 制表符
     */
    String TAB_KEY = "\t";
    
    /**
     * 换行符
     */
    String ENTER_KEY = "\n";
    
    /**
     * 数据资源名称
     */
    String DB_RESOURCE = "db";
    
    /**
     * 数据类型属性键
     */
    String DB_DATABASE_TYPE = "db.database.type";
    
    /**
     * 数据库类型oracle
     */
    String DATABASE_TYPE_ORACLE = "oracle";
    
    /**
     * 无符号Short最大
     */
    int MAX_UNSIGN_SHORT = 0xffff;
    
    /**
     * 无符号Byte最大
     */
    int MAX_UNSIGN_BYTE = 0xff;
    
    /**
     * id
     */
    String ID = "id";
    
    /**
     * SQL语句排序 asc
     */
    String SQL_ASC = "asc";
    
    /**
     * SQL语句排序 desc
     */
    String SQL_DESC = "desc";
    
    /**
     * 右括号
     */
    String RIGHT_BRACKET = "}";
    
    /**
     * 左括号
     */
    String LEFT_BRACKET = "{";
    
    /**
     * 左中括号
     */
    String LEFT_MIDDEL_BRACKET = "[";
    
    /**
     * 右中括号
     */
    String RIGTH_MIDDLE_BRACKET = "]";
    
    /**
     * 符号@
     */
    String RIGTH_AT = "@";
    
    /**
     * 毫秒级时间格式字符串
     */
    int DATETIME_IN_MILLIS = 1000;
    
    /**
     * 一分钟等于60秒
     */
    int SECOND_TIME = 60;
    
    /**
     * 1秒等于60毫秒
     */
    int MILI_SECOND_TIME = 60;
    
    /**
     * 30分钟的毫秒数
     */
    int HALFHOUR = 1800000;
    
    /**
     * 15分钟的毫秒数
     */
    int QUARTER = 900000;
    
    /**
     * Token有效期
     */
    int TOKEN_EXPIRE_INTERVAL = 60;
    
    /**
     * 毫秒级时间格式字符串
     */
    long DATETIME_IN_MILLIS_L = 2L;
    
    /**
     * 字段所占字节数1 字节
     */
    int BYTE_LENGTH_ONE = 1;
    
    /**
     * 字段所占字节数 2 字节
     */
    int BYTE_LENGTH_TWO = 2;
    
    /**
     * 十六进制格式字符串中每个字节占用的长度
     */
    int HEX_STRING_LENGTH_PER_BYTE = 2;
    
    /**
     * 十六进制格式字符串中每个SHORT类型数据占用的长度
     */
    int HEX_STRING_LENGTH_PER_SHORT = 4;
    
    /**
     * 十六进制格式字符串中每个INT类型数据占用的长度
     */
    int HEX_STRING_LENGTH_PER_INT = 8;
    
    /**
     * 十六进制格式字符串中每个LONG类型数据占用的长度
     */
    int HEX_STRING_LENGTH_PER_LONG = 16;
    
    /**
     * 移动报文头长度
     */
    int CM_MESSAGE_HEAD_LENGTH = 28;
    
    /**
     * 电信报文头长度
     */
    int CT_MESSAGE_HEAD_LENGTH = 20;
    
    /**
     * 16进制基数
     */
    int RADIX_HEX = 16;
    
    /**
     * 10进制基数
     */
    int RADIX_DEC = 10;
    
    /**
     * 8进制基数
     */
    int RADIX_OCTAL = 8;
    
    /**
     * 全部记录
     */
    int ALL_ROWS = -1;
    
    /**
     * 通讯协议类型 - TCP
     */
    int PROTOCOL_TYPE_TCP = 1;
    
    /**
     * 通讯协议类型 - UDP
     */
    int PROTOCOL_TYPE_UDP = 2;
    
    /**
     * 通讯协议类型 - SMS
     */
    int PROTOCOL_TYPE_SMS = 0;
    
    /**
     * 注册
     */
    byte OPER_TYPE_REGISTER = 0;
    
    /**
     * 解除注册
     */
    byte OPER_TYPE_UNREGISTER = 1;
    
    /**
     * 无准备就绪的侦听键集
     */
    int NO_KEYS_READY = 0;
    
    /**
     * 电信版本
     */
    int VERSION_DIANXIN = 1;
    
    /**
     * 移动版本
     */
    int VERSION_YIDONG = 0;
    
    /**
     * 响应报文命令字 & 实际命令字 = 0x8000 则为响应
     */
    int RES_COMMANDID_BEGIN = 0x8000;
    
    /**
     * 请求报文命令字 & 实际命令字等到对应请求命令字
     */
    int REQ_COMMANDID_END = 0x0FFF;
    
    /**
     * 电信 分包事务ID 分割点 终端小于0x8000
     */
    int TRANSID_SPLIT_POINT = 0x8000;
    
    /**
     * 短信PDU头长度 移动
     */
    int SMS_PDU_LENGTH = 12;
    
    /**
     * 移动消息摘要长度
     */
    int HASH_LENGTH = 20;
    
    /**
     * 移动分包报文最小长度
     */
    int SUBPKG_TAG_PREFIX_LENGTH = 11;
    
    /**
     * 移动分包报文数据最小长度
     */
    int SUBPKG_DATA_PREFIX_LENGTH = 7;
    
    /**
     * 摘要值长度 16
     */
    int HASH_VALUE_LENGTH = 0x10;
    
    /**
     * CM流水号起始位置
     */
    int CM_SID_BEGIN_POS = 4;
    
    /**
     * CT流水号起始位置
     */
    int CT_SID_BEGIN_POS = 6;
    
    /**
     * 电信crc16 TLV长度
     */
    int CT_CRC16_TLV_LENGTH = 6;
    
    /**
     * 分包消息保留字
     */
    int SUBPKG_RESERVE = 0x0100;
    
    /**
     * NUM_0X10
     */
    int NUM_0X10 = 0x10;
    
    /**
     * NUM_0X21
     */
    int NUM_0X21 = 0x21;
    
    /**
     * NUM_0XE0
     */
    int NUM_0XE0 = 0xe0;
    
    /**
     * NUM_0XE021
     */
    int NUM_0XE021 = 0xe021;
    
    /**
     * 数字0
     */
    int NUM_0 = 0;
    
    /**
     * 数字1
     */
    int NUM_1 = 1;
    
    /**
     * 数字2
     */
    int NUM_2 = 2;
    
    /**
     * 数字3
     */
    int NUM_3 = 3;
    
    /**
     * 数字4
     */
    int NUM_4 = 4;
    
    /**
     * 数字5
     */
    int NUM_5 = 5;
    
    /**
     * 数字6
     */
    int NUM_6 = 6;
    
    /**
     * 数字7
     */
    int NUM_7 = 7;
    
    /**
     * 数字8
     */
    int NUM_8 = 8;
    
    /**
     * 数字9
     */
    int NUM_9 = 9;
    
    /**
     * 数字10
     */
    int NUM_10 = 10;
    
    /**
     * 数字11
     */
    int NUM_11 = 11;
    
    /**
     * 数字12
     */
    int NUM_12 = 12;
    
    /**
     * 数字14
     */
    int NUM_14 = 14;
    
    /**
     * 数字15
     */
    int NUM_15 = 15;
    
    /**
     * 数字16
     */
    int NUM_16 = 16;
    
    /**
     * 数字22
     */
    int NUM_22 = 22;
    
    /**
     * 数字32
     */
    int NUM_32 = 32;
    
    /**
     * 数字36
     */
    int NUM_36 = 36;
    
    /**
     * 数字100
     */
    int NUM_100 = 100;
    
    /**
     * 数字255
     */
    int NUM_255 = 255;
    
    /**
     * 数字500
     */
    int NUM_500 = 500;
    
    /**
     * 数字3000
     */
    int NUM_3000 = 3000;
    
    /**
     * 数字5000
     */
    int NUM_5000 = 5000;
    
    /**
     * 数字1000000
     */
    int NUM_1000000 = 1000000;
    
    /**
     * 默认的设备超时时间
     */
    int DEFAULT_INTERVAL_TIME = 20;
    
    /**
     * 一天的毫秒数
     */
    long DAY_TIME_MILLIS = 24L * SECOND_TIME * SECOND_TIME * DATETIME_IN_MILLIS;
    
    /**
     * 空字符串
     */
    String BLANK = "";
    
    /**
     * 校验数字的规则
     */
    String REGX = "^(0|[1-9][0-9]*)$";
    
    /**
     * 数字1200
     */
    int NUM_1200 = 0;
    
    /**
     * PREFIX_HEX
     */
    String PREFIX_HEX = "0x";
    
    /**
     * checkout email
     */
    String EMAIL = "^\\w+((-\\w+)|(\\.\\w+))*\\@[A-Za-z0-9]+((\\.|-)[A-Za-z0-9]+)*\\.[A-Za-z0-9]+$";
    
    /**
     * 电话传真手机
     */
    String MOBILEL_FAX_PHONE = "^^\\+?((\\d{1,4}-\\d{4,20})|(\\d{4,20}))$";
    
    /**
     * IP
     */
    String IS_IP = "^(25[0-5]|2[0-4][0-9]|[0-1]{1}[0-9]{2}|[1-9]{1}[0-9]{1}|[1-9])"
        + "\\.(25[0-5]|2[0-4][0-9]|[0-1]{1}[0-9]{2}|[1-9]{1}[0-9]{1}|[1-9]|0)"
        + "\\.(25[0-5]|2[0-4][0-9]|[0-1]{1}[0-9]{2}|[1-9]{1}[0-9]{1}|[1-9]|0)"
        + "\\.(25[0-5]|2[0-4][0-9]|[0-1]{1}[0-9]{2}|[1-9]{1}[0-9]{1}|[0-9])$";
    
    /**
     * url
     */
    String URL = "^http:\\/\\/[a-zA-Z0-9]+\\.[a-zA-Z0-9]+[\\/=?%\\-&_~@`[\\]\\:+!]*([^<>])*$";
    
    /**
     * 密码
     */
    String PASSWORD = "^(?=[A-Za-z0-9]*(?:[A-Za-z][0-9]|[0-9][A-Za-z]))[A-Za-z0-9]{8,16}$|"
        + "^[A-Za-z][0-9A-Za-z]{6,14}[0-9]$|^[0-9][0-9A-Za-z]{6,14}[A-Za-z]$";
    
    /**
     * 四位的数字
     */
    String NUMBER4 = "^[a-zA-Z0-9]{4}$";
    
    /**
     * 16位的的数字或数字
     */
    String NUMBER_16 = "^[A-F0-9]{16}$";
    
    /**
     * PORT
     */
    String PORT = "^[0-9]{0,5}$";
    
    /**
     * 平台名称80位
     */
    String PLATNAME_80 = "^[0-9a-zA-Z\\u4e00-\\u9fa5]{0,80}$";
    
    /**
     * 审核能力订购有效期天 校验
     */
    String DAY_REGEX = "^([1-9]|[1-9][0-9]|[1-9][0-9][0-9]|[1][0-7][0-9][0-9]|[1][8][0-1][0-9]|[1][8][2][0-5])$";
    
    /**
     * 审核能力订购有效期周 校验
     */
    String WEK_REGEX = "^([1-9]|[1-9][0-9]|[1][0][0-9])$";
    
    /**
     * 审核能力订购有效期月 校验
     */
    String MONTH_REGEX = "^([1-9]|[1-5][0-9])$";
    
    /**
     * 审核能力订购有效期年 校验
     */
    String YEAR_REGEX = "^[1-5]{1}$";
    
    /**
     * 端口小于65535
     */
    int PORT_MAX_VALUE_65535 = 65535;
    
    /**
     * 长度小于256
     */
    int MAX_LENGTH_255 = 256;
    
    /**
     * 长度小于256
     */
    int MAX_LENGTH_256 = 256;
    
    /**
     * 长度小于20
     */
    int MAX_LENGTH_20 = 20;
    
    /**
     * 长度小于50
     */
    int MAX_LENGTH_50 = 50;
    
    /**
     * 长度小于80
     */
    int MAX_LENGTH_80 = 80;
    
    /**
     * 长度小于64
     */
    int MAX_LENGTH_64 = 64;
    
    /**
     * 长度小于4
     */
    int MAX_LENGTH_4 = 4;
    
    /**
     * 长度小于5
     */
    int MAX_LENGTH_5 = 5;
    
    /**
     * 长度小于15
     */
    int MAX_LENGTH_15 = 15;
    
    /**
     * 长度小于16
     */
    int MAX_LENGTH_16 = 16;
    
    /**
     * 长度小于30
     */
    int MAX_LENGTH_30 = 30;
    
    /**
     * 长度小于32
     */
    int MAX_LENGTH_32 = 32;
    
    /**
     * 长度小于512
     */
    int MAX_LENGTH_512 = 512;
    
    /**
     * 长度小于128
     */
    int MAX_LENGTH_128 = 128;
    
    /**
     * 桌面消息
     */
    int TYPE_DESK = 0X20;
    
    /**
     * 长度小于10
     */
    String NUMBER_MAX_LENGTH_10 = "^[0-9]{0,10}$";
    
    /**
     * 每次等待时间
     */
    long WAIT_TIME = 100L;
    
    /**
     * 最大等待次数
     */
    int WAIT_TIMES = 150;
    
    /**
     * 数字7
     */
    int HEX_FFFF = 0xFFFF;
    
    /**
     * 数字7
     */
    int HEX_1021 = 0x1021;
    
    /**
     * api调用返回结果码00
     */
    String RESULE_SUCC_00 = "00";
    
    /**
     * api调用返回结果码00
     */
    String RESULE_SUCC_41 = "41";
    
    /**
     * api调用返回结果码00
     */
    String RESULE_SUCC_42 = "42";
    
    /**
     * api调用返回结果码01
     */
    String RESULE_FAILE_01 = "01";
    
    /**
     * api调用返回结果码02
     */
    String RESULE_FAILE_02 = "02";
    
    /**
     * api调用返回结果码03
     */
    String RESULE_FAILE_03 = "03";
    
    /**
     * api调用返回结果码04
     */
    String RESULE_FAILE_04 = "04";
    
    /**
     * api调用返回结果码05
     */
    String RESULE_FAILE_05 = "05";
    
    /**
     * api调用返回结果码06
     */
    String RESULE_FAILE_06 = "06";
    
    /**
     * api调用返回结果码07
     */
    String RESULE_FAILE_07 = "07";
    
    /**
     * api调用返回结果码08
     */
    String RESULE_FAILE_08 = "08";
    
    /**
     * api调用返回结果码21
     */
    String RESULE_FAILE_21 = "21";
    
    /**
     * api调用返回结果码22
     */
    String RESULE_FAILE_22 = "22";
    
    /**
     * api调用返回结果码23
     */
    String RESULE_FAILE_23 = "23";
    
    /**
     * api调用返回结果码99
     */
    String RESULE_FAILE_99 = "99";
    
    /**
     * IP unknown
     */
    String IP_UNKNOWN = "unknown";
    
    /**
     * x-forwarded-for
     */
    String X_FORWARDED_FOR = "x-forwarded-for";
    
    /**
     * Proxy-Client-IP
     */
    String PROXY_CLIENT_IP = "Proxy-Client-IP";
    
    /**
     * WL-Proxy-Client-IP
     */
    String WL_PROXY_CLIENT_IP = "WL-Proxy-Client-IP";
    
    /**
     * HTTP_CLIENT_IP
     */
    String HTTP_CLIENT_IP = "HTTP_CLIENT_IP";
    
    /**
     * HTTP_X_FORWARDED_FOR
     */
    String HTTP_X_FORWARDED_FOR = "HTTP_X_FORWARDED_FOR";
    
    /**
     * 默认分页大小
     */
    int DEFAULT_PAGE_SIZE = 10;
    
    /**
     * 默认第一页
     */
    int DEFAULT_PAGE_NUMBER = 1;
    
    /**
     * 空格
     */
    String SPACE = " ";
    
    /**
     * 星号
     */
    String ASTERISK = "*";
    
    /**
     * 公共
     */
    String PUBLIC_KEY =
        "MFwwDQYJKoZIhvcNAQEBBQADSwAwSAJBALQ8yl+oxXGPJgHOopPWatnTwVvc2zwWiVsIBbqLhdqMVioeQu2hAFooLVNUhDJmc/DAGzG5lRWQtJYN5NLVOQECAwEAAQ==";
    
    /**
     * 双冒号
     */
    String DOUBLE_COLON = "::";
    
    /**
     * 属性名
     */
    String FIELD_NAME = "FIELD_NAME";
    
    /**
     * 消息
     */
    String MESSAGE = "MESSAGE";
    
    /**
     * 输入错误
     */
    String INPUT_ERROR = "inputError";
    
    /**
     * ip
     */
    String LOCAL_HOST = "127.0.0.1";
    
    /**
     * PROJECT_INDEX
     */
    String PROJECT_INDEX = "PROJECT_INDEX";
    
    /**
     * 一级供电单位节点
     */
    String LEVEL_PARENT_ORGNO = "1";
    
    /**
     * 二级供电单位节点
     */
    String LEVEL_CHILD_ORGNO = "2";
    
    /**
     * 电压等级节点
     */
    String LEVEL_VOLTAGE = "3";
    
    /**
     * 变电站节点
     */
    String LEVEL_SUBS = "4";
    
    /**
     * 主变节点
     */
    String LEVEL_GMTRAN = "5-4";
    
    /**
     * 母线节点
     */
    String LEVEL_MLINE = "5-3";
    
    /**
     * 联络线节点
     */
    String LEVEL_TLINE = "5-5";
    
    /**
     * 线路节点
     */
    String LEVEL_LINE = "5-2";
    
    /**
     * 台区节点
     */
    String LEVEL_TG = "6";
    
    /**
     * 变压器节点
     */
    String LEVEL_GTRANS = "7";
}
