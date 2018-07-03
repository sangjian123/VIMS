drop table t_userinfo;
--用户
create table t_userinfo( id number(20) not null,
                         loginname varchar2(200) not null,
                         nickname varchar2(200) not null,
                         password varchar2(200) not null,
                         phone varchar2(20) ,
                         createtime date not null, 
                         updatetime date ,
                         status     number(1),
                         belongdept number(20)
                        );
                        
create sequence s_userinfo_id
minvalue 1
maxvalue 9999999999999999999
start with 1
increment by 1
cache 20
order;

insert into t_userinfo(id,loginname, nickname , password, phone,createtime , updatetime,status,belongdept )values(1,'root','root','f01ee7e230e47fa04ec8a41e06987076','',sysdate,null,1,'');

--角色
create table T_ROLE
(
  id          NUMBER(19) not null,
  name        VARCHAR2(64) not null,
  description VARCHAR2(255),
  status      NUMBER(2) not null
);
--用户角色关系
create table T_USER_ROLE
(
  id      NUMBER(19) not null,
  user_id VARCHAR2(16),
  role_id NUMBER(19)
);

-- 菜单
create table T_RESOURCE
(
  id           NUMBER(19) not null,
  name         VARCHAR2(64) not null,
  url          VARCHAR2(4000),
  description  VARCHAR2(255),
  icon         VARCHAR2(32),
  pid          NUMBER(19),
  seq          NUMBER(2) not null,
  status       NUMBER(2) not null,
  resourcetype NUMBER(2) not null,
  createdate   DATE,
  name_cn      VARCHAR2(64),
  menu_code    VARCHAR2(64)
);

create table T_ROLE_RESOURCE
(
  id          NUMBER(19) not null,
  role_id     NUMBER(19) not null,
  resource_id NUMBER(19) not null
);

-- Create table
create table T_ORGANIZATION
(
  id                   NUMBER(19) not null,
  name                 VARCHAR2(64) not null,
  address              VARCHAR2(100),
  createdate           DATE,
  description VARCHAR2(255)
);

                        
create table t_dirverinfo( id number(10) not null,
                           username varchar2(200)    not null,
                           telphone varchar2(20)     not null,
                           createtime date           not null, 
                           updatetime date           not null,
                           email      varchar2(30)   not null,
                           status     number(1) default 0      not null ,
                           licencepic varchar2(100)  not null,
                           belongdept number(10)
                          ); 
                          
create sequence s_dirverinfo_id
minvalue 1
maxvalue 9999999999999999999
start with 1
increment by 1
cache 20
order;                       
                        
                        
create table t_equipment( id number(10) not null,
                          equipnumber  varchar2(20) not null,
                          belongdept   number(2)    not null,
                          cardnumber   varchar2(20) not null,
                          brand        varchar2(20) not null,
                          flowcardnumber   varchar2(20)  not null,
                          displayicon      varchar2(100) not null,
                          driverid         number(2)     not null,
                          driverpic        varchar2(100) not null,
                          createtime       date not null, 
                          updatetime       date not null
                         );
create sequence s_equipment_id
minvalue 1
maxvalue 9999999999999999999
start with 1
increment by 1
cache 20
order; 

create table t_dept
(
  id          number(19) not null,
  name        varchar2(64) not null,
  description varchar2(255)
);

create table T_EQUIPPOSINFO
(
  equipnumber    VARCHAR2(20) not null,
  longitude      NUMBER(20,6),
  dimension      NUMBER(20,6),
  ctime          DATE,
  prelongitude   NUMBER(20,6),
  predimension   NUMBER(20,6),
  ptime          DATE,
  mileage        NUMBER(20,5),
  speed          NUMBER(10),
  flag           VARCHAR2(5),
  longitude_flag VARCHAR2(5),
  dimension_flag VARCHAR2(5)
);   
                        
create table T_EQUIPHISPOSINFO
(
  equipnumber  VARCHAR2(20) not null,
  longitude    NUMBER(20,6) not null,
  dimension    NUMBER(20,6) not null,
  ctime        DATE not null,
  prelongitude NUMBER(20,6),
  predimension NUMBER(20,6),
  ptime        DATE,
  mileage      NUMBER(20,5),
  speed        NUMBER(10)
);  


create table T_DEVICELIST
(
  id          NUMBER(10) not null,
  name        VARCHAR2(200) not null,
  pid         NUMBER(10),
  isleaf      NUMBER(1),
  description VARCHAR2(200)
);


insert into t_devicelist(id,name,pid,isleaf,description) values(0,'root',-1,0,'root node');
insert into t_devicelist(id,name,pid,isleaf,description) values(1,'鼓楼区',0,0,'root node');
insert into t_devicelist(id,name,pid,isleaf,description) values(2,'电力大厦1',1,1,'root node');
insert into t_devicelist(id,name,pid,isleaf,description) values(3,'电力大厦2',1,1,'root node');


create table T_DEVICEKEY
(
  id          NUMBER(10) not null,
  url         VARCHAR2(200) not null,
  username    VARCHAR2(200) not null,
  password    VARCHAR2(200) not null,
  devname     VARCHAR2(200),
  devpassword VARCHAR2(200),
  channelid   VARCHAR2(200) 
);	

insert into t_devicekey(id,url,username,password,devname,devpassword,channelid) values(2,'http://ezcloud.uniview.com/','caixiaohua123','Device00001','caixiaohua123',1);
insert into t_devicekey(id,url,username,password,devname,devpassword,channelid) values(3,'http://ezcloud.uniview.com/','caixiaohua123','Device00001','caixiaohua123',1);

commit;	

							 
--工作流相关

create table ACT_EVT_LOG
(
  LOG_NR_       NUMBER(19) not null,
  TYPE_         NVARCHAR2(64),
  PROC_DEF_ID_  NVARCHAR2(64),
  PROC_INST_ID_ NVARCHAR2(64),
  EXECUTION_ID_ NVARCHAR2(64),
  TASK_ID_      NVARCHAR2(64),
  TIME_STAMP_   TIMESTAMP(6) not null,
  USER_ID_      NVARCHAR2(255),
  DATA_         BLOB,
  LOCK_OWNER_   NVARCHAR2(255),
  LOCK_TIME_    TIMESTAMP(6),
  IS_PROCESSED_ NUMBER(3) default 0
);

create table ACT_RE_DEPLOYMENT
(
  ID_          NVARCHAR2(64) primary key   not null,
  NAME_        NVARCHAR2(255),
  CATEGORY_    NVARCHAR2(255),
  TENANT_ID_   NVARCHAR2(255) default '',
  DEPLOY_TIME_ TIMESTAMP(6)
);

create table ACT_GE_BYTEARRAY
(
  ID_            NVARCHAR2(64) primary key   not null,
  REV_           NUMBER(38),
  NAME_          NVARCHAR2(255),
  DEPLOYMENT_ID_ NVARCHAR2(64),
  BYTES_         BLOB,
  GENERATED_     NUMBER(1),
  check (GENERATED_ IN (1,0)) 
);
create index ACT_IDX_BYTEAR_DEPL on ACT_GE_BYTEARRAY (DEPLOYMENT_ID_) ;

create table ACT_GE_PROPERTY
(
  NAME_  NVARCHAR2(64) not null,
  VALUE_ NVARCHAR2(300),
  REV_   NUMBER
);

insert into ACT_GE_PROPERTY(NAME_,VALUE_,REV_) values('schema.version','5.18.0.1',1);

commit;

create table ACT_HI_ACTINST
(
  ID_                NVARCHAR2(64) not null,
  PROC_DEF_ID_       NVARCHAR2(64) not null,
  PROC_INST_ID_      NVARCHAR2(64) not null,
  EXECUTION_ID_      NVARCHAR2(64) not null,
  ACT_ID_            NVARCHAR2(255) not null,
  TASK_ID_           NVARCHAR2(64),
  CALL_PROC_INST_ID_ NVARCHAR2(64),
  ACT_NAME_          NVARCHAR2(255),
  ACT_TYPE_          NVARCHAR2(255) not null,
  ASSIGNEE_          NVARCHAR2(255),
  START_TIME_        TIMESTAMP(6) not null,
  END_TIME_          TIMESTAMP(6),
  DURATION_          NUMBER(19),
  TENANT_ID_         NVARCHAR2(255) default ''
);

create table ACT_HI_ATTACHMENT
(
  ID_           NVARCHAR2(64) not null,
  REV_          NUMBER,
  USER_ID_      NVARCHAR2(255),
  NAME_         NVARCHAR2(255),
  DESCRIPTION_  NVARCHAR2(2000),
  TYPE_         NVARCHAR2(255),
  TASK_ID_      NVARCHAR2(64),
  PROC_INST_ID_ NVARCHAR2(64),
  URL_          NVARCHAR2(2000),
  CONTENT_ID_   NVARCHAR2(64),
  TIME_         TIMESTAMP(6)
);

create table ACT_HI_COMMENT
(
  ID_           NVARCHAR2(64) not null,
  TYPE_         NVARCHAR2(255),
  TIME_         TIMESTAMP(6) not null,
  USER_ID_      NVARCHAR2(255),
  TASK_ID_      NVARCHAR2(64),
  PROC_INST_ID_ NVARCHAR2(64),
  ACTION_       NVARCHAR2(255),
  MESSAGE_      NVARCHAR2(2000),
  FULL_MSG_     BLOB
);

create table ACT_HI_DETAIL
(
  ID_           NVARCHAR2(64) not null,
  TYPE_         NVARCHAR2(255) not null,
  PROC_INST_ID_ NVARCHAR2(64),
  EXECUTION_ID_ NVARCHAR2(64),
  TASK_ID_      NVARCHAR2(64),
  ACT_INST_ID_  NVARCHAR2(64),
  NAME_         NVARCHAR2(255) not null,
  VAR_TYPE_     NVARCHAR2(64),
  REV_          NUMBER,
  TIME_         TIMESTAMP(6) not null,
  BYTEARRAY_ID_ NVARCHAR2(64),
  DOUBLE_       NUMBER,
  LONG_         NUMBER(19),
  TEXT_         NVARCHAR2(2000),
  TEXT2_        NVARCHAR2(2000)
);

create table ACT_HI_IDENTITYLINK
(
  ID_           NVARCHAR2(64) not null,
  GROUP_ID_     NVARCHAR2(255),
  TYPE_         NVARCHAR2(255),
  USER_ID_      NVARCHAR2(255),
  TASK_ID_      NVARCHAR2(64),
  PROC_INST_ID_ NVARCHAR2(64)
);

create table ACT_HI_PROCINST
(
  ID_                        NVARCHAR2(64) not null,
  PROC_INST_ID_              NVARCHAR2(64) not null,
  BUSINESS_KEY_              NVARCHAR2(255),
  PROC_DEF_ID_               NVARCHAR2(64) not null,
  START_TIME_                TIMESTAMP(6) not null,
  END_TIME_                  TIMESTAMP(6),
  DURATION_                  NUMBER(19),
  START_USER_ID_             NVARCHAR2(255),
  START_ACT_ID_              NVARCHAR2(255),
  END_ACT_ID_                NVARCHAR2(255),
  SUPER_PROCESS_INSTANCE_ID_ NVARCHAR2(64),
  DELETE_REASON_             NVARCHAR2(2000),
  TENANT_ID_                 NVARCHAR2(255) default '',
  NAME_                      NVARCHAR2(255)
);


create table ACT_HI_TASKINST
(
  ID_             NVARCHAR2(64) not null,
  PROC_DEF_ID_    NVARCHAR2(64),
  TASK_DEF_KEY_   NVARCHAR2(255),
  PROC_INST_ID_   NVARCHAR2(64),
  EXECUTION_ID_   NVARCHAR2(64),
  PARENT_TASK_ID_ NVARCHAR2(64),
  NAME_           NVARCHAR2(255),
  DESCRIPTION_    NVARCHAR2(2000),
  OWNER_          NVARCHAR2(255),
  ASSIGNEE_       NVARCHAR2(255),
  START_TIME_     TIMESTAMP(6) not null,
  CLAIM_TIME_     TIMESTAMP(6),
  END_TIME_       TIMESTAMP(6),
  DURATION_       NUMBER(19),
  DELETE_REASON_  NVARCHAR2(2000),
  PRIORITY_       NUMBER,
  DUE_DATE_       TIMESTAMP(6),
  FORM_KEY_       NVARCHAR2(255),
  CATEGORY_       NVARCHAR2(255),
  TENANT_ID_      NVARCHAR2(255) default ''
);

create table ACT_HI_VARINST
(
  ID_                NVARCHAR2(64) not null,
  PROC_INST_ID_      NVARCHAR2(64),
  EXECUTION_ID_      NVARCHAR2(64),
  TASK_ID_           NVARCHAR2(64),
  NAME_              NVARCHAR2(255),
  VAR_TYPE_          NVARCHAR2(100),
  REV_               NUMBER,
  BYTEARRAY_ID_      NVARCHAR2(64),
  DOUBLE_            NUMBER,
  LONG_              NUMBER(19),
  TEXT_              NVARCHAR2(2000),
  TEXT2_             NVARCHAR2(2000),
  CREATE_TIME_       TIMESTAMP(6),
  LAST_UPDATED_TIME_ TIMESTAMP(6)
);

create table ACT_ID_GROUP
(
  ID_   NVARCHAR2(64) primary key  not null,
  REV_  NUMBER(38),
  NAME_ NVARCHAR2(255),
  TYPE_ NVARCHAR2(255)
);

create table ACT_ID_INFO
(
  ID_        NVARCHAR2(64) primary key  not null,
  REV_       NUMBER,
  USER_ID_   NVARCHAR2(64),
  TYPE_      NVARCHAR2(64),
  KEY_       NVARCHAR2(255),
  VALUE_     NVARCHAR2(255),
  PASSWORD_  BLOB,
  PARENT_ID_ NVARCHAR2(255)
);

create table ACT_ID_MEMBERSHIP
(
  USER_ID_  NVARCHAR2(64) not null,
  GROUP_ID_ NVARCHAR2(64) not null
);

create table ACT_ID_USER
(
  ID_         NVARCHAR2(64) not null,
  REV_        NUMBER,
  FIRST_      NVARCHAR2(255),
  LAST_       NVARCHAR2(255),
  EMAIL_      NVARCHAR2(255),
  PWD_        NVARCHAR2(255),
  PICTURE_ID_ NVARCHAR2(64)
);

create table ACT_PROCDEF_INFO
(
  ID_           NVARCHAR2(64) not null,
  PROC_DEF_ID_  NVARCHAR2(64) not null,
  REV_          NUMBER,
  INFO_JSON_ID_ NVARCHAR2(64)
);

create table ACT_RE_MODEL
(
  ID_                           NVARCHAR2(64) not null,
  REV_                          NUMBER,
  NAME_                         NVARCHAR2(255),
  KEY_                          NVARCHAR2(255),
  CATEGORY_                     NVARCHAR2(255),
  CREATE_TIME_                  TIMESTAMP(6),
  LAST_UPDATE_TIME_             TIMESTAMP(6),
  VERSION_                      NUMBER,
  META_INFO_                    NVARCHAR2(2000),
  DEPLOYMENT_ID_                NVARCHAR2(64),
  EDITOR_SOURCE_VALUE_ID_       NVARCHAR2(64),
  EDITOR_SOURCE_EXTRA_VALUE_ID_ NVARCHAR2(64),
  TENANT_ID_                    NVARCHAR2(255) default ''
);

create table ACT_RE_PROCDEF
(
  ID_                     NVARCHAR2(64) primary key  not null,
  REV_                    NUMBER(38),
  CATEGORY_               NVARCHAR2(255),
  NAME_                   NVARCHAR2(255),
  KEY_                    NVARCHAR2(255) not null,
  VERSION_                NUMBER(38) not null,
  DEPLOYMENT_ID_          NVARCHAR2(64),
  RESOURCE_NAME_          NVARCHAR2(2000),
  DGRM_RESOURCE_NAME_     VARCHAR2(4000),
  DESCRIPTION_            NVARCHAR2(2000),
  HAS_START_FORM_KEY_     NUMBER(1),
  HAS_GRAPHICAL_NOTATION_ NUMBER(1),
  SUSPENSION_STATE_       NUMBER(38),
  TENANT_ID_              NVARCHAR2(255) default ''
);

create table ACT_RU_EVENT_SUBSCR
(
  ID_            NVARCHAR2(64) not null,
  REV_           NUMBER,
  EVENT_TYPE_    NVARCHAR2(255) not null,
  EVENT_NAME_    NVARCHAR2(255),
  EXECUTION_ID_  NVARCHAR2(64),
  PROC_INST_ID_  NVARCHAR2(64),
  ACTIVITY_ID_   NVARCHAR2(64),
  CONFIGURATION_ NVARCHAR2(255),
  CREATED_       TIMESTAMP(6) not null,
  PROC_DEF_ID_   NVARCHAR2(64),
  TENANT_ID_     NVARCHAR2(255) default ''
);

create table ACT_RU_EXECUTION
(
	ID_               NVARCHAR2(64) primary key  not null,
	REV_              NUMBER(38),
	PROC_INST_ID_     NVARCHAR2(64),
	BUSINESS_KEY_     NVARCHAR2(255),
	PARENT_ID_        NVARCHAR2(64),
	PROC_DEF_ID_      NVARCHAR2(64),
	SUPER_EXEC_       NVARCHAR2(64),
	ACT_ID_           NVARCHAR2(255),
	IS_ACTIVE_        NUMBER(1),
	IS_CONCURRENT_    NUMBER(1),
	IS_SCOPE_         NUMBER(1),
	IS_EVENT_SCOPE_   NUMBER(1),
	SUSPENSION_STATE_ NUMBER(38),
	CACHED_ENT_STATE_ NUMBER(38),
	TENANT_ID_        NVARCHAR2(255) default '',
	NAME_             NVARCHAR2(255),
	LOCK_TIME_        TIMESTAMP(6),
	check (IS_ACTIVE_ IN (1,0)),
	check (IS_CONCURRENT_ IN (1,0)),
	check (IS_SCOPE_ IN (1,0)),
	check (IS_EVENT_SCOPE_ IN (1,0)),
	check (IS_ACTIVE_ IN (1,0)),
	check (IS_CONCURRENT_ IN (1,0)),
	check (IS_SCOPE_ IN (1,0)),
	check (IS_EVENT_SCOPE_ IN (1,0)),
	check (IS_ACTIVE_ IN (1,0)),
	check (IS_CONCURRENT_ IN (1,0)),
	check (IS_SCOPE_ IN (1,0)),
	check (IS_EVENT_SCOPE_ IN (1,0)),
	check (IS_ACTIVE_ IN (1,0)),
	check (IS_CONCURRENT_ IN (1,0)),
	check (IS_SCOPE_ IN (1,0)),
	check (IS_EVENT_SCOPE_ IN (1,0)),
	check (IS_ACTIVE_ IN (1,0)),
	check (IS_CONCURRENT_ IN (1,0)),
	check (IS_SCOPE_ IN (1,0)),
	check (IS_EVENT_SCOPE_ IN (1,0)),
	check (IS_ACTIVE_ IN (1,0)),
	check (IS_CONCURRENT_ IN (1,0)),
	check (IS_SCOPE_ IN (1,0)),
	check (IS_EVENT_SCOPE_ IN (1,0)),
	check (IS_ACTIVE_ IN (1,0)),
	check (IS_CONCURRENT_ IN (1,0)),
	check (IS_SCOPE_ IN (1,0)),
	check (IS_EVENT_SCOPE_ IN (1,0)),
	check (IS_ACTIVE_ IN (1,0)),
	check (IS_CONCURRENT_ IN (1,0)),
	check (IS_SCOPE_ IN (1,0)),
	check (IS_EVENT_SCOPE_ IN (1,0)),
	check (IS_ACTIVE_ IN (1,0)),
	check (IS_CONCURRENT_ IN (1,0)),
	check (IS_SCOPE_ IN (1,0)),
	check (IS_EVENT_SCOPE_ IN (1,0)),
	check (IS_ACTIVE_ IN (1,0)),
	check (IS_CONCURRENT_ IN (1,0)),
	check (IS_SCOPE_ IN (1,0)),
	check (IS_EVENT_SCOPE_ IN (1,0))
);
create index ACT_IDX_EXEC_BUSKEY on ACT_RU_EXECUTION (BUSINESS_KEY_) ;
create index ACT_IDX_EXE_PARENT on ACT_RU_EXECUTION (PARENT_ID_) ;
create index ACT_IDX_EXE_PROCDEF on ACT_RU_EXECUTION (PROC_DEF_ID_) ;
create index ACT_IDX_EXE_PROCINST on ACT_RU_EXECUTION (PROC_INST_ID_) ;
create index ACT_IDX_EXE_SUPER on ACT_RU_EXECUTION (SUPER_EXEC_) ;

create table ACT_RU_IDENTITYLINK
(
  ID_           NVARCHAR2(64) not null,
  REV_          NUMBER,
  GROUP_ID_     NVARCHAR2(255),
  TYPE_         NVARCHAR2(255),
  USER_ID_      NVARCHAR2(255),
  TASK_ID_      NVARCHAR2(64),
  PROC_INST_ID_ NVARCHAR2(64),
  PROC_DEF_ID_  NVARCHAR2(64)
);

create table ACT_RU_JOB
(
  ID_                  NVARCHAR2(64) not null,
  REV_                 NUMBER,
  TYPE_                NVARCHAR2(255) not null,
  LOCK_EXP_TIME_       TIMESTAMP(6),
  LOCK_OWNER_          NVARCHAR2(255),
  EXCLUSIVE_           NUMBER(1),
  EXECUTION_ID_        NVARCHAR2(64),
  PROCESS_INSTANCE_ID_ NVARCHAR2(64),
  PROC_DEF_ID_         NVARCHAR2(64),
  RETRIES_             NUMBER,
  EXCEPTION_STACK_ID_  NVARCHAR2(64),
  EXCEPTION_MSG_       NVARCHAR2(2000),
  DUEDATE_             TIMESTAMP(6),
  REPEAT_              NVARCHAR2(255),
  HANDLER_TYPE_        NVARCHAR2(255),
  HANDLER_CFG_         NVARCHAR2(2000),
  TENANT_ID_           NVARCHAR2(255) default ''
);

create table ACT_RU_TASK
(
  ID_               NVARCHAR2(64) not null,
  REV_              NUMBER,
  EXECUTION_ID_     NVARCHAR2(64),
  PROC_INST_ID_     NVARCHAR2(64),
  PROC_DEF_ID_      NVARCHAR2(64),
  NAME_             NVARCHAR2(255),
  PARENT_TASK_ID_   NVARCHAR2(64),
  DESCRIPTION_      NVARCHAR2(2000),
  TASK_DEF_KEY_     NVARCHAR2(255),
  OWNER_            NVARCHAR2(255),
  ASSIGNEE_         NVARCHAR2(255),
  DELEGATION_       NVARCHAR2(64),
  PRIORITY_         NUMBER,
  CREATE_TIME_      TIMESTAMP(6),
  DUE_DATE_         TIMESTAMP(6),
  CATEGORY_         NVARCHAR2(255),
  SUSPENSION_STATE_ NUMBER,
  TENANT_ID_        NVARCHAR2(255) default '',
  FORM_KEY_         NVARCHAR2(255)
);

create table ACT_RU_VARIABLE
(
  ID_           NVARCHAR2(64) not null,
  REV_          NUMBER,
  TYPE_         NVARCHAR2(255) not null,
  NAME_         NVARCHAR2(255) not null,
  EXECUTION_ID_ NVARCHAR2(64),
  PROC_INST_ID_ NVARCHAR2(64),
  TASK_ID_      NVARCHAR2(64),
  BYTEARRAY_ID_ NVARCHAR2(64),
  DOUBLE_       NUMBER,
  LONG_         NUMBER(19),
  TEXT_         NVARCHAR2(2000),
  TEXT2_        NVARCHAR2(2000)
);

create table t_dirverdetailinfo
(
  userid     NUMBER(10) not null,
  time       date       not null,
  startmile  NUMBER(10,5) not null,
  endmile    NUMBER(10,5) not null,
  todaymile  NUMBER(10,5) not null
);

create table T_PROCEXCEPTIONLOG
(
  procname    VARCHAR2(100) not null,
  processip   VARCHAR2(100),
  operator    VARCHAR2(1000),
  sqlerrm     VARCHAR2(2000),
  description VARCHAR2(2000),
  logtime     DATE not null
)
partition by range (LOGTIME)
(
  partition P_LESS values less than (TO_DATE(' 2017-06-01 00:00:00', 'SYYYY-MM-DD HH24:MI:SS', 'NLS_CALENDAR=GREGORIAN')),
  partition SYS_P627 values less than (TO_DATE(' 2018-05-01 00:00:00', 'SYYYY-MM-DD HH24:MI:SS', 'NLS_CALENDAR=GREGORIAN')),
  partition SYS_P628 values less than (TO_DATE(' 2018-06-01 00:00:00', 'SYYYY-MM-DD HH24:MI:SS', 'NLS_CALENDAR=GREGORIAN')),
  partition SYS_P629 values less than (TO_DATE(' 2018-07-01 00:00:00', 'SYYYY-MM-DD HH24:MI:SS', 'NLS_CALENDAR=GREGORIAN'))
);

create or replace procedure p_batch_updatedailydata
(
    str_deviceid          varchar2,
    str_longitude         varchar2,
    str_dimension         varchar2,
    str_longitude_flag    varchar2,
    str_dimension_flag    varchar2,
    str_ctime             varchar2,
    flag                  varchar2
)
as
    d_l_longitude         number(20,5);
    d_l_dimension         number(20,5);
    p_time                date;
    i_l_sencendtime       number;
    i_l_miles             number;
begin

    begin
        select s.longitude,s.dimension,s.ctime,s.mileage
          into d_l_longitude,d_l_dimension,p_time,i_l_miles
          from t_equipposinfo s
         where s.equipnumber = str_deviceid;
    exception
        when no_data_found then
              i_l_miles := 0;
    end;

    if( p_time is not null) then
         begin
             select decode(nvl(ceil((to_date(str_ctime , 'YYYY-MM-DD HH24-MI-SS') - p_time) * 24*60*60 ),2),0,1)
               into i_l_sencendtime
               from dual;
         exception
            when others then
                  i_l_sencendtime := 1;
         end;
    end if;

    update t_equipposinfo t
       set t.longitude      = str_longitude,
           t.dimension      = str_dimension,
           t.ctime          = to_date(str_ctime,'yyyymmdd hh24:mi:ss'),
           t.prelongitude   = d_l_longitude,
           t.predimension   = d_l_dimension,
           t.ptime          = p_time,
           t.flag           = flag,
           t.longitude_flag = str_longitude_flag,
           t.dimension_flag = str_dimension_flag,
           t.mileage        = t.mileage + GetDistance(to_number(str_longitude),to_number(str_dimension),to_number(d_l_longitude),to_number(d_l_dimension)),
           t.speed          = (GetDistance(to_number(str_longitude),to_number(str_dimension),to_number(d_l_longitude),to_number(d_l_dimension))/i_l_sencendtime)*60*60
     where t.equipnumber = str_deviceid;

    if(sql%notfound) then
         insert into t_equipposinfo(equipnumber,longitude,dimension,ctime,prelongitude,predimension,ptime,
                                    mileage,speed,longitude_flag,dimension_flag)
               values( str_deviceid,str_longitude,str_dimension,to_date(str_ctime,'yyyymmdd hh24:mi:ss'),str_longitude,str_dimension,to_date(str_ctime,'yyyymmdd hh24:mi:ss'),
                                    0,0,str_longitude_flag,str_dimension_flag);

    end if;
    
    insert into t_equiphisposinfo(equipnumber,longitude,dimension,ctime,mileage,speed)
                values(str_deviceid,str_longitude,str_dimension,to_date(str_ctime,'yyyymmdd hh24:mi:ss'),
                             i_l_miles+GetDistance(to_number(str_longitude),to_number(str_dimension),to_number(d_l_longitude),to_number(d_l_dimension)),
                             (GetDistance(to_number(str_longitude),to_number(str_dimension),to_number(d_l_longitude),to_number(d_l_dimension))/i_l_sencendtime)*60*60  );

    commit;
exception
    when others then
      p_storecallinfo (dbms_utility.format_call_stack);    
end p_batch_updatedailydata;
/
CREATE OR REPLACE FUNCTION GetDistance
(   lat1 number,
    lng1 number,
    lat2 number,
    lng2 number
)
RETURN NUMBER is
  earth_padius number := 6378.137;
  radLat1      number := Radian(lat1);
  radLat2      number := Radian(lat2);
  a            number := radLat1 - radLat2;
  b            number := Radian(lng1) - Radian(lng2);
  s            number := 0;
begin
  s := 2 *
       Asin(Sqrt(power(sin(a / 2), 2) +
                 cos(radLat1) * cos(radLat2) * power(sin(b / 2), 2)));
  s := s * earth_padius;
  s := Round(s * 10000) / 10000;
  return s;

end GetDistance;
/
CREATE OR REPLACE FUNCTION Radian(d number) RETURN NUMBER
is
PI number :=3.141592625;
begin
   return  d* PI/180.0;
end Radian;
/
create or replace procedure p_storecallinfo
(
    str_in_operator       in    t_procexceptionlog.operator%type
)
is
pragma autonomous_transaction;
    str_l_endofline   constant    char(1) := chr(10);
    str_l_endoffield  constant    char(1) := chr(32);

    i_l_ptfinligne    number;
    i_l_ptdebligne    number;
    i_l_ptdebcode     number;
    i_l_index         number;
    i_l_line          number;
    i_l_lineend       number;

    str_l_stackinfo   varchar2(4000);
    str_l_result      varchar2(4000);
    str_l_linestr     varchar2(4000);
    str_l_procname    varchar2(4000);
    str_l_errorpos    varchar2(4000);

begin
    str_l_stackinfo := str_in_operator;
    i_l_index       := 2;
    i_l_ptfinligne  := length(str_l_stackinfo);
    i_l_ptdebligne  := i_l_ptfinligne;

    while i_l_ptfinligne > 0 and i_l_ptdebligne > 83 loop

        i_l_ptdebligne   := instr (str_l_stackinfo, str_l_endofline, -1, i_l_index) + 1 ;
        i_l_index        := i_l_index + 1;

        str_l_linestr    := substr(str_l_stackinfo, i_l_ptdebligne, i_l_ptfinligne - i_l_ptdebligne);
        i_l_ptdebcode    := instr (str_l_linestr, str_l_endoffield, -1, 1);
        i_l_line         := instr (str_l_linestr, str_l_endoffield, -1, 4);
        i_l_lineend      := instr (str_l_linestr, str_l_endoffield, -1, 3);

        str_l_procname   := substr(substr(str_l_linestr, i_l_ptdebcode + 1),instr(substr(str_l_linestr, i_l_ptdebcode + 1),'.')+1);
        str_l_errorpos   := trim(substr(str_l_linestr, i_l_line+1,i_l_lineend-i_l_line-1));

        if (i_l_index > 3) then
            str_l_result := str_l_result || str_l_procname ||'@'||str_l_errorpos || '|';
        end if;

        i_l_ptfinligne := i_l_ptdebligne - 1;

    end loop;

     --store call information
    insert into t_procexceptionlog( procname,
                                    processip,
                                    operator,
                                    sqlerrm,
                                    description,
                                    logtime)
                            values( upper(str_l_procname),
                                    sys_context('userenv','ip_address') ,
                                    str_l_result,
                                    dbms_utility.format_error_stack,
                                    dbms_utility.format_error_backtrace,
                                    sysdate
                                   );
    commit;
exception
    when others then
        rollback;
end p_storecallinfo;
/
