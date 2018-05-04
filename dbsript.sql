create table t_userinfo( id number(10) not null,
                         username varchar2(200) not null,
                         password varchar2(200) not null,
                         telphone varchar2(20)  not null,
                         createtime date not null, 
                         updatetime date not null,
                         status     number(1),
                         belongdept number(10)
                        );
                        
create sequence s_userinfo_id
minvalue 1
maxvalue 9999999999999999999
start with 1
increment by 1
cache 20
order;                       
                        
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