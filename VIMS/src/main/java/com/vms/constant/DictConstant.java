package com.vms.constant;

/**
 * 
 * 存放数据字典对应的code_type
 *
 * @author jihonghai
 * @version [版本号, 2016-11-16]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public interface DictConstant
{
    
    /**
     * 是否有营销系统
     */
    String IS_EPMS = "APP_IS_EPMS";
    
    String ENERGY_TYPE = "energyType";
    
    /**
     * 计划状态
     */
    String DIST_PLAN_STATUS = "distPlanStatus";
    
    /**
     *业务申请类型
     */
    String BUSI_TYPE_CODE = "busiTypeCode";
    
    /**
     * 系统用户当前状态
     */
    String USER_STATUS = "curStatusCode";
    
    /**
     * 采集设备参数，设备类别
     */
    String GATHER_TYPE_CODE = "collTmnlType";
    
    /**
     * 采集设备参数，生产厂家
     */
    String GATHER_MANUFACTURER = "collTmnlFactory";
    
    /**
     * 采集设备参数，设备型号
     */
    String GATHER_MODEL_CODE = "lcModeCode";
    
    /**
     * 采集设备参数，固件版本
     */
    String GATHER_SOFTWARE_VN = "manuSoftVerison";
    
    /**
     * 采集设备参数，模板状态
     */
    String GATHER_VALID_FLAG = "validFlag";
    
    /**
     * 采集点状态
     */
    String GATHER_STATUS_CODE = "cpStatus";
    
    /**
     * 采集模板状态
     */
    String GATHER_TMPL_STATUS = "validFlag";
    
    /**
     * 采集模板类型
     */
    String GATHER_TMPL_TYPE = "ciCodeStyle";
    
    /**
     * 采集终端类型
     */
    String GATHER_DLC_TYPE = "collTmnlType";
    
    /**
     * 采集方式
     */
    String GATHER_COLL_MODE = "ctCommType";
    
    /**
     * 采集终端通信规约
     */
    String GATHER_PROTOCOL_CODE = "commProtocol";
    
    /**
     * 采集终端资产的的运行状态
     */
    String GATHER_CUR_STATUS_CODE = "tmnlStatus";
    
    /**
     * 采集任务分组任务类型
     */
    String GATHER_GROUP_TYPE_CODE = "collItemUse";
    
    /**
     * 采集用户群组任务类型
     */
    String GATHER_USER_GROUP_TYPE_CODE = "collGroupType";
    
    /**
     * 采集用户群组数据项类别
     */
    String GATHER_USER_GROUP_ITEM_CODE = "GroupItemType";
    
    /**
     * 采集用户群组状态
     */
    String GATHER_USER_GROUP_STATUS = "rsStatusCode";
    
    /**
     * 采集任务状态
     */
    String GATHER_TASK_STATUS = "collTaskStatus";
    
    /**
     * 采集点级别
     */
    String GATHER_CP_LEVEL_CODE = "cpLvlCode";
    
    /**
     * 采集运行终端状态
     */
    String GATHER_RUN_STATUS_CODE = "tmnlRunStatus";
    
    /**
     * 采集接口日志类型
     */
    String GATHER_RUN_WS_TYPE = "interfaceType";
    
    /**
     * 采集接口日志名称
     */
    String GATHER_RUN_WS_NAME = "interfaceName";
    
    /**
     * 采集接口校验规则类别
     */
    String GATHER_RULE_TYPE_CODE = "ruleChkType";
    
    /**
     * 采集任务执行状态
     */
    String GATHER_TIME_TASK_STATUS = "taskStatus";
    
    /**
     * 发布状态
     */
    String GATHER_PUBLISH_STATUS_CODE = "publishStatus";
    
    /**
     * 数据类型
     */
    String GATHER_COLLRTNDATA_TYPE_CODE = "collRtnDataType";
    
    /**
     * 数据来源
     */
    String GATHER_COLL_DATASRC_CODE = "collDataSrc";
    
    /**
     * 事件分类
     */
    String GATHER_EVENT_TYPE_CODE = "eventType";
    
    /**
     * 操作类型
     */
    String GATHER_EVENT_DATA_CODE = "eventData";
    
    /**
     * 周期单位
     */
    String GATHER_TIME_CYC_CODE = "timeCyc";
    
    /**
     * 采集接口校验脚本类别
     */
    String GATHER_RULE_CONTENT_TYPE = "ruleContType";
    
    /**
     * 设备型号数据项类别
     */
    String GATHER_MODEL_ITEM_TYPE = "modelItemType";
    
    /**
     * 采集用户群组数据项类别
     */
    String GATHER_USER_SWITCH = "Switch";
    
    /**
     * 采集统计电表用途
     */
    String GATHER_METER_USE_TYPE = "meterUseType";
    
    /**
     * 信用等级、价值等级、风险等级 集合
     */
    String CUST_EVAL_LEVEL = "custEvalLevel";
    
    /**
     *custEvalLevel 截取值的起始点
     */
    Integer CUST_EVAL_LEVEL_START = 0;
    
    /**
     *custEvalLevel 截取值的终止点
     */
    Integer CUST_EVAL_LEVEL_END = 2;
    
    /**
     * 信用等级前缀
     */
    String CREDIT_LEVEL_PREFIX = "01";
    
    /**
     *风险等级前缀
     */
    String RISK_LEVEL_PREFIX = "02";
    
    /**
     * 价值等级前缀
     */
    String COST_LEVEL_PREFIX = "03";
    
    /**
     * 信用等级
     */
    String CREDIT_LEVEL = "creditLevel";
    
    /**
     *风险等级
     */
    String RISK_LEVEL = "riskLevel";
    
    /**
     * 价值等级
     */
    String COST_LEVEL = "costLevel";
    
    /**
     * vip等级
     */
    String VIP_LEVEL = "vipLevel";
    
    /**
     * 电能表类别
     */
    String METER_SORT = "meterSort";
    
    /**
     * 电能表类型
     */
    String METER_TYPE_CODE = "meterTypeCode";
    
    /**
     * 电能表当前状态
     */
    String METER_STATUS = "meterStatus";
    
    /**
     * 电能表型号
     */
    String METER_MODEL_NO = "meterModelNo";
    
    /**
     * 接线方式
     */
    String WIRING_MODE = "wiringMode";
    
    /**
     * 电能表电压
     */
    String METER_VOLT = "meterVolt";
    
    /**
     * 电能表标定电流
     */
    String METER_RCSORT = "meterRcSort";
    
    /**
     * 电能表准确度等级
     */
    String METER_ACCURACY = "meterAccuracy";
    
    /**
     *电能表方案变更说明 
     */
    String METER_SCHEMECD = "busiChgDesc";
    
    /**
     * 行业分类
     */
    String TRADE_CODE = "tradeCode";
    
    /**
     * 用电类别
     */
    String ELEC_TYPE_CODE = "elecTypeCode";
    
    /**
     * 用户类别
     */
    String CUST_SORT_CODE = "custSortCode";
    
    /**
     * 互感器类别
     */
    String IT_SORT = "itSort";
    
    /**
     * 互感器类型
     */
    String IT_TYPE_CODE = "itTypeCode";
    
    /**
     * 电压互感器型号
     */
    String VI_MODEL_NO = "viModelNo";
    
    /**
     * 互感器额定电压
     */
    String IT_RV = "itRv";
    
    /**
     * 互感器电流变比
     */
    String CURRENT_IT_RATIO = "currentItRatio";
    
    /**
     * 互感器电压变比
     */
    String VOLT_RATIO_CODE = "voltRatioCode";
    
    /**
     * 互感器TA/TV准确度等级
     */
    String IT_PRE_LEVEL_CODE = "itPreLevelCode";
    
    /**
     * 互感器相别
     */
    String IT_PHASE = "itPhase";
    
    /**
     * 互感器额定一次
     */
    String FST_RC_CODE = "fstRcCode";
    
    /**
     * 互感器当前状态
     */
    String IT_STATUS = "itStatus";
    
    /**
     * 互感器方案变更说明
     */
    String BUSI_CHGDESC = "busiChgDesc";
    
    /**
     * 负控设备型号
     */
    String LC_MODEL_CODE = "lcModelCode";
    
    /**
     * 负控设备类型
     */
    String LC_TYPE_CODE = "lcTypeCode";
    
    /**
     * 负控设备制造单位
     */
    String LC_DEV_FACTURER = "lcdevFacturer";
    
    /**
     * 负控设备采集方式
     */
    String CT_COMM_TYPE = "ctCommType";
    
    /**
     * 负控设备当前状态
     */
    String T_MNL_STATUS = "tmnlStatus";
    
    /**
     * 封印类别
     */
    String SEAL_TYPE_CODE = "sealTypeCode";
    
    /**
     * 封印制造单位
     */
    String SEAL_MANU_FACTURER = "sealManufacturer";
    
    /**
     * 封印型号
     */
    String SEAL_MODEL_NO = "sealModelNo";
    
    /**
     * 封印颜色
     */
    String SEAL_COLOR = "sealColor";
    
    /**
     * 封印用途
     */
    String SEAL_USAGE = "sealUsage";
    
    /**
     * 封印工种
     */
    String SEAL_FLAG_WORK_TYPE = "sealFlagWorkType";
    
    /**
     * 封印状态
     */
    String SEAL_STATUS = "sealStatus";
    
    /**
     * 抄表事件类型
     */
    String EVENT_TYPE = "mrEventType";
    
    /**
     * 抄表计划类型
     */
    String PLAN_TYPE_CODE = "mrPlanType";
    
    /**
     * 抄表方式
     */
    String MR_MODE_CODE = "mrModeCode";
    
    /**
     * 计量箱/柜状态
     */
    String OTHER_DEV_STATUS = "otherDevStatus";
    
    /**
     * 库房分类
     */
    String ASSET_WH_TYPE = "assetWhType";
    
    /**
     * 库房级别
     */
    String WH_LEVEL_CODE = "whLevelCode";
    
    /**
     * 库区功能类别
     */
    String FUNC_TYPE_CODE = "funcTypeCode";
    
    /**
     * 库区专业类别
     */
    String WH_FIELD_SORT_CODE = "whFieldSortCode";
    
    /**
     * 出入库原因
     */
    String IO_WH_REASON_CODE = "ioWhReasonCode";
    
    /**
     * 存放区类型
     */
    String STORAGE_AREA_TYPE = "storageAreaType";
    
    /**
     * 储位状态
     */
    String STORE_LOC_STATUS = "storeLocStatus";
    
    /**
     * 当前计划状态
     */
    String MROPERACT = "mrOperAct";
    
    /**
     * 抄表完成状态
     */
    String MR_STATUS_CODE = "mrStatusCode";
    
    /**
     * 电压等级
     */
    String PS_VOLT_CODE = "psVoltCode";
    
    /**
     * 公变转变标志
     */
    String PUB_PRIV_FLAG = "pubPrivFlag";
    
    /**
     * 抄表段属性
     */
    String MR_SECT_ATTR = "mrSectAttr";
    
    /**
     * 抄表周期
     */
    String PERIOD_CODE = "periodCode";
    
    /**
     * 用户状态
     */
    String STATUS_CODE = "statusCode";
    
    /**
     * 电能表制造单位
     */
    String METER_FACTURER = "meterFacturer";
    
    /**
     * 互感器制造单位
     */
    String IT_MANU_FACTURER = "itManufacturer";
    
    /**
     * 参考表标志
     */
    String YN_JUDGE_FLAG = "ynJudgeFlag";
    
    /**
     * 客户价值等级
     */
    String CUST_VALUE_LEVEL = "custValueLevel";
    
    /**
     * 客户信用等级
     */
    String CUST_CREDIT_LEVEL = "custCreditLevel";
    
    /**
     * 电能表类型
     */
    String TYPE_CODE = "meterTypeCode";
    
    /**
     * 电能表类别
     */
    String SORT_CODE = "meterSort";
    
    /**
     * 电能表电压
     */
    String VOLT_CODE = "meterVolt";
    
    /**
     * 电能表电流
     */
    String RATED_CURRENT = "meterRcSort";
    
    /**
    * 
    * 证件类型
    */
    String CERT_TYPE_CODE = "certType";
    
    /**
     * 受电设备主备性质
     */
    String MS_FLAG = "msFlag";
    
    /**
     * 受电设备运行状态
     */
    String RUN_STATUS_CODE = "runStatusCode";
    
    /**
     * 进线方式
     */
    String LINEIN_MODE = "lineinMode";
    
    /**
     * 电源类型
     */
    String PS_TYPE_CODE = "psTypeCode";
    
    /**
     * 电源相数
     */
    String PHASE_CODE = "phaseCode";
    
    /**
     * 供电电压
     */
    String PS_VOLT = "psVoltCode";
    
    /**
     * 运行方式
     */
    String RUN_MODE = "runMode";
    
    /**
     * 保护方式
     */
    String PROTECT_MODE = "psProtectMode";
    
    /**
     * 保护类型
     */
    String RELAY_PROTECT_MODE = "relayProtMode";
    
    /**
     * 受电设备类型
     */
    String EQUIP_TYPE_CODE = "equipTypeCode";
    
    /**
     * 功率因素标准
     */
    String PF_STD_CODE = "pfStdCode";
    
    /**
     * 电源性质
     */
    String PS_ATTR = "psAttr";
    
    /**
     * 变损算法标志
     */
    String TL_ALG_CODE = "tlAlgCode";
    
    /**
     * 施封位置
     */
    String SEAL_LOC_FLAG = "sealLocFlag";
    
    /**
     * 有效标志
     */
    String EFFECTIVE_FLAG = "effectiveFlag";
    
    /**
     * 完好标志
     */
    String INTACT_FLAG = "intactFlag";
    
    /**
     * 电价码
     */
    String PRC_CODE = "prcCode";
    
    /**
     * 是否执行峰谷标志
     */
    String TS_FLAG = "tsFlag";
    
    /**
    * 变更标志
    */
    String BUSI_CHG_DESC = "chgDesc";
    
    /**
     * 电能表示数类型
     */
    String READ_TYPE_CODE = "readTypeCode";
    
    /**
     * 变压器类型
     */
    String TRAN_MODEL_NO = "tranModelNo";
    
    /**
     * 负荷性质
     */
    String LODE_ATTR_CODE = "lodeAttrCode";
    
    /**
     * 计划类型
     */
    String CHK_PLAN = "chkPlan";
    
    /**
     * 检查结果
     */
    String CHK_RSLT = "chkRslt";
    
    /**
     * 计划状态
     */
    String PLAN_STATUS_CODE = "planStatusCode";
    
    /**
     * 票据类型
     */
    String NOTE_TYPE_CODE = "noteTypeCode";
    
    /**
     * 电费通知方式
     */
    String NOTIFY_MODE = "notifyMode";
    
    /**
     * 设备种类
     */
    String EQUIP_TYPE = "mdSort";
    
    /**
     * 数据字典维护类型
     */
    String MAINT_TYPE_CODE = "codeMaintType";
    
    /**
     * 合同类型
     */
    String CONTRACT_TYPE = "ContractType";
    
    /**
     * 合同状态
     */
    String CONTRACT_STATUS = "contractStatus";
    
    /**
     * 失效标志
     */
    String EXPIRE_FLAG = "expireFlag";
    
    /**
     * 电流互感器型号
     */
    String CI_MODEL_NO = "ciModelNo";
    
    /**
     * 组合互感器型号
     */
    String CO_MPITMODEL_NO = "compItModelNo";
    
    /**
     * 优惠方式
     */
    String DISC_MODE = "discMode";
    
    /**
     * 数据字典是否有效
     */
    String IS_EFFECT = "isEffect";
    
    /**
     * 用户付费模式
     */
    String CONS_PAY_MODE = "consPayMode";
    
    /**
     * 时段
     */
    String PRCT_SCODE = "prcTsCode";
    
    /**
     * 策略分类
     */
    String PRC_POLICY_SORT = "prcPolicySort";
    
    /**
     * 策略范围分类
     */
    String RANGE_TYPE_CODE = "rangeTypeCode";
    
    /**
     * 操作类型
     */
    String OPER_TYPE = "operType";
    
    /**
     * 业务类别
     */
    String APP_TYPE_CODE = "appTypeCode";
    
    /**
     * 用电类别
     */
    String CONS_SORT_CODE = "consSortCode";
    
    /**
     * 加密算法
     */
    String STS_EA = "stsEA";
    
    /**
     * 密钥类型
     */
    String STS_KEY_TYPE = "stsKeyType";
    
    /**
     * 费率指数
     */
    String STS_TARIFFINDEX = "stsTariffindex";
    
    /**
     * 防窃电检查类型
     */
    String STEAL_CHECK_TYPE_CODE = "stealCheckType";
    
    /**
     * 防窃电检查结果
     */
    String STEAL_CHECK_RESULT = "stealCheckResult";
    
    /**
     * 窃电类型
     */
    String STEAL_Mode = "stealMode";
    
    /**
     * 示数装拆说明
     */
    String IR_REMARK = "irRemark";
    
    /**
    * 银行代码
    */
    String BANK_CODE = "bankCode";
    
    String IDX = "IDX_";
    
    /**
    * 是否预付费
    */
    String PREPAY_FLAG = "consPayMode";
    
    String WARNING = "WARNING";
    
    /**
    * 税率类型
    */
    String RATE_TYPE = "rateType";
    
    /**
    * 补贴类型
    */
    String SUBSIDY_TYPE = "subsidyType";
    
    String SUBSC_IBECONT = "subscibeCont";
    
    String RVC_MODE = "rcvMode";
    
    String REMIND_TYPE = "remindType";
    
    String UMS_ERROR_CODE = "umsErroCode";//参数下发错误类型
    
    /**
     * 告警类型
     */
    String ALARM_TYPE = "alarmType";
    
    /**
     *告警级别
     */
    String ALARM_LEVEL = "alarmLevel";
    
    /**告警状态*/
    String ALARM_STATUS = "alarmStatus";
    
    /**计量点类型*/
    String MP_SORT_CODE = "mpSortCode";
    
    /**关口计量点类型*/
    String MP_TYPE_CODE = "chkunitTypeCode";
    
    String GATEWAY_TYPE = "gateWayType";
    
    /**是否AMI标识*/
    String AMI_TYPE = "amiTypeCode";
    
    /**
     * 货币单位系统参数
     */
    String MONETARY_UNIT = "BILLING_MONETARYUNIT";
    
    String PAY_MODE = "feePayMode";//缴费方式
    
    String SETTLE_MODE = "settleMode";//结算方式
    
    String POWER_STATE = "powerState";//用电状态
    
    /**
     *   库存阈值类型 
     */
    String DSI_INDEX_TYPE = "dsiIndexType";
    
    /**
     *  库存阈值类别 
     */
    String DSI_TYPE_CODE = "dsiTypeCode";
    
    String EQUIP_CATEG = "equipCateg";
    
    /**
     *  抄表机型号
     */
    String LCMODECODE = "lcModeCode";
    
    /**
     *  生产厂家
     */
    String COLLTMNLFACTORY = "collTmnlFactory";
    
    /**
     *  抄表机状态
     */
    String RUNSTATCODE = "runStatCode";
    
    String MP_STATUS = "mpStatus";
    
    /**
     * 电能表报废原因
     */
    String DISCARD_REASON = "discardReason";
    
    /**
     * 互感器报废原因
     */
    String DIT_DISCARD_REASON = "ditDisCardReason";
    
    /**
     * 采集设备报废原因
     */
    String DLC_DISCARD_REASON = "dlcDisCardReason";
    
    /**
     * 欠费状态
     */
    String SETTLE_FLAG = "settleFlag";
    
    /**
     * 正向反向
     */
    String PLUSE_ATTR = "pulseAttr";
    
    /**
     * 建档失败原因
     */
    String FILING_FAIL_CODE = "umsError";
    
    /**
     * 是 否
     */
    String LC_FLAG = "lcFlag";
    
    /**
     * 文档类型
     */
    String DOCU_TYPE = "docuType";
    
    /**
     * 生产厂商
     */
    String PRODUCT_V = "productTypeCode";
    
    /**
     * 母线型号
     */
    String GMLINE_TYPE = "GmlineTypeCode";
    
    /**
     * 联系人类型
     */
    String CONTACT_MODE = "contactMode";
    
    /**
     * 业务费类型
     */
    String BUSI_FEE_TYPE = "busiFeeType";
    
    /**
     * 错误日志类型
     */
    String LOG_DESC = "logDesc";
    
    /**
     * 差错原因
     */
    String ERROR_CAUSE = "errorCause";
    
    /**
     * 业务费收费状态
     */
    String BUSI_FEE_STATUS = "busiFeeStatus";
    
    /**
     * 申请方式
     */
    String POWER_APP_MODE = "powerAppMode";
    
    /**
     * 客服服务业务类型
     */
    String CALL_CENTER_BUSI_TYPE_CODE = "callBusiTypeCode";
    
    /**
     * 处理结果
     */
    String HANDLE_RESULT = "handleResult";
    
    /**
     * 故障类型
     */
    String FAULT_TYPE_CODE = "faultTypeCode";
    
    /**
     * 故障原因
     */
    String FAULT_REASON = "faultReason";
    
    /**
     * 投诉类型
     */
    String COMPLAIN_TYPE_CODE = "complainTypeCode";
    
    /**
     * 举报类型
     */
    String TIPOFF_TYPE_CODE = "tipOffTypeCode";
    
    /**
     * 建议类型
     */
    String SUGGEST_TYPE_CODE = "suggestTypeCode";
    
    /**
     * 表扬类型
     */
    String PRAISE_TYPE_CODE = "praiseTypeCode";
    
    /**
     * 客户价值规则
     */
    String CUST_VALUE_RULE = "custValueRule";
    
    /**
     * 工单类型
     */
    String JOBS_TYPE = "jobsType";
    
    /**
     * 客户信用规则
     */
    String CREDIT_RULES = "creditRules";
    
    /**
     *  达标标志
     */
    String STANDARD_FLAG = "standardFlag";
    
    /**
     * 信用评估因素
     */
    String CREDIT_FACTOR = "creditFactor";
    
    /**
     * 线损异常处理类型
     */
    String LL_EXCEPTION_TYPE = "llExceptionType";
    
    /**
     * 审核结果
     */
    String AUDIT_FLAG = "replyOpinion";
    
    /**
     * 月份代码
     */
    String MONTH_CODE = "monthCode";
    
    /**
     * 月、日
     */
    String CYCLE = "checkcycle";
    
    /**
     * 缴费方式，客户信用
     */
    String CREDIT_CHARGE_TYPE = "creditChargeType";
    
    String PR_CODE = "prCode";
    
    String METER_FREQ = "meterFreq";
    
    /**
     * 电能表通讯方式
     */
    String COMM_MODE = "commMode";
    
    /**
     *  电能表位数
     */
    String METER_DIGITS = "meterDigits";
    
    String HANDLE_FLAG = "handleFlag";
    
    /**
     *  电量计算方式
     */
    String CALC_MODE = "calcMode";
    
    /**
     *  计量方式
     */
    String MEAS_MODE = "measMode";
    
    /**
     * 窃电分析结果
     */
    String SUSPICION = "suspicion";
    
    /**
     * 窃电用户分类
     */
    String STEAL_ELEC_CLS_TYPE = "stealElecClsType";
    
    /**
     * 行政级别
     */
    String ADMINISTRATIVE_LEVEL = "administrative";
    
    /**
     * 退补处理分类
     */
    String COMPENSATION_TYPE = "compensationType";
    
    /**
     * 当前系统是EPMS时,ismdm=false; 是MDM时,ismdm=true;
     */
    String MDM_INDEX = "mdm_index";
    
    /**
     * 考核单元类型
     */
    String CHKUNIT_LINK_FLAG = "linkFlag";
    
    /**
     * 预警等级
     */
    String WARNING_LEVEL = "warningLevel";
    
    /**
     * 缺口等级
     */
    String GAP_GRADE = "gapGrade";
    
    /**
     * 群组类型
     */
    String GROUP_TYPE = "groupType";
    
    /**
     * 负控标识
     */
    String LOAD_STATUS = "loadStatus";
    
    /**
     * 拉合闸状态
     */
    String PULLOF_STATUS = "pullOffStatus";
    
    /**
     * 电量耗尽跳闸开关
     */
    String TRIP_WITCH = "tripWitch";
    
    /**
     * 前置接口方式
     */
    String FRONT_MACHINE_TYPE = "frontMachineType";
    
    /**
     * 前置接口协议
     */
    String FRONT_MACHINE_PROTOCOL = "intfProtocol";
    
    /**
     * 公告受众类型
     */
    String MSG_TYPE = "msgType";
    
    /**
     * 基本电价参数
     */
    String BASE_FEE_CODE = "baseFeeCode";
    
    /**
     * 方案状态
     */
    String PLAN_STATUS = "planStatus";
    
    /**
     * APP类型
     */
    String APK_TYPE = "apkType";
    
    /**
     * 基本电费计算方式
     */
    String BA_CALC_MODE = "baCalcMode";
    
    /**
     * 方案类型
     */
    String PLAN_TYPE = "planType";
    
    /**
     * Kafka 电表参数下发topic
     */
    String INTERFACE_PRARM_SEND_TOPIC = "interface_param_send_topic";
    
    /**
     * epms系統錯誤碼
     */
    String EPMS_ERROR_CODE = "epmsErroCode";
    
    /**
     * 电表参数下发结果码
     */
    String ERROR_CODE = "errorCode";
    
    /**
     * 参数下发类型
     */
    String PARAM_ISSUED = "paramIssued";
    
    /**
     * 消息队列中消息消费失败需要再处理的消息
     */
    String KAFKA_REHANDLE_KEY = "KAFKA_REHANDLE_KEY";
}
