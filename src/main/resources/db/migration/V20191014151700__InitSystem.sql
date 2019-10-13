drop table if exists user;
create table user(
id  bigint unsigned not null auto_increment comment '主键',
account	varchar(50) 	comment	 '帐号',
name	varchar(50) 	comment	 '名称',
status	 tinyint unsigned 	comment	 '状态',
pwd	varbinary(100)	comment	 '密码',
id_card	varchar(50)	comment	 '身份证号码',
phone	varchar(50)	comment	 '手机号',
email	varchar(50)	comment	 '电子邮箱',
update_time	timestamp not null default current_timestamp on update current_timestamp	comment	 '修改时间',
create_time	timestamp not null default current_timestamp comment	 '创建时间',
update_id bigint unsigned comment '修改人',
create_id bigint unsigned not null comment	 '创建人',
note varchar(200) comment	'备注',
version	int unsigned not null default 0	comment	 '版本',
del_flag tinyint unsigned not null default 0 comment	 '0：正常 1：已删除',
primary key (id)
)comment '用户表';

drop table if exists role;
create table role(
id  bigint unsigned not null auto_increment comment '主键',
name	varchar(50) comment	 '角色名称',
status	 tinyint unsigned comment	 '角色状态',
update_time	timestamp not null default current_timestamp on update current_timestamp	comment	 '修改时间',
create_time	timestamp not null default current_timestamp comment	 '创建时间',
update_id bigint unsigned comment '修改人',
create_id bigint unsigned not null comment	 '创建人',
note varchar(200) comment	'备注',
version	int unsigned not null default 0	comment	 '版本',
del_flag tinyint unsigned not null default 0 comment	 '0：正常 1：已删除',
primary key (id)
)comment '角色表';

drop table if exists authority;
create table authority(
id  bigint unsigned not null auto_increment comment '主键',
parent_id	 bigint unsigned	comment	 '父权限id',
name	varchar(30)	comment	 '权限名称',
code	varchar(100)	comment	 '编码',
right_type	enum('MENU','FUNCTION','DATA')	comment	 '权限类别',
right_content	varchar(100)	comment	 '权限内容',
update_time	timestamp not null default current_timestamp on update current_timestamp	comment	 '修改时间',
create_time	timestamp not null default current_timestamp comment	 '创建时间',
update_id bigint unsigned comment '修改人',
create_id bigint unsigned not null comment	 '创建人',
note varchar(200) comment	'备注',
version	int unsigned not null default 0	comment	 '版本',
del_flag tinyint unsigned not null default 0 comment	 '0：正常 1：已删除',
primary key (id)
)comment '权限表';

drop table if exists user_authority_ref;
create table user_authority_ref(
id  bigint unsigned not null auto_increment comment '主键',
authority_id	 bigint unsigned comment	 '权限id',
user_id	 bigint unsigned comment	 '用户id',
status  tinyint unsigned comment	 '状态',
update_time	timestamp not null default current_timestamp on update current_timestamp	comment	 '修改时间',
create_time	timestamp not null default current_timestamp comment	 '创建时间',
update_id bigint unsigned comment '修改人',
create_id bigint unsigned not null comment	 '创建人',
note varchar(200) comment	'备注',
version	int unsigned not null default 0	comment	 '版本',
del_flag tinyint unsigned not null default 0 comment	 '0：正常 1：已删除',
primary key (id)
)comment '用户权限关联表';

drop table if exists user_role_ref;
create table user_role_ref(
id  bigint unsigned not null auto_increment comment '主键',
role_id	 bigint unsigned comment	 '角色id',
user_id	 bigint unsigned comment	 '用户id',
update_time	timestamp not null default current_timestamp on update current_timestamp	comment	 '修改时间',
create_time	timestamp not null default current_timestamp comment	 '创建时间',
update_id bigint unsigned comment '修改人',
create_id bigint unsigned not null comment	 '创建人',
note varchar(200) comment	'备注',
version	int unsigned not null default 0	comment	 '版本',
del_flag tinyint unsigned not null default 0 comment	 '0：正常 1：已删除',
primary key (id)
)comment '用户角色关联表';

drop table if exists role_authority_ref;
create table role_authority_ref(
id  bigint unsigned not null auto_increment comment '主键',
role_id	 bigint unsigned comment	 '角色id',
authority_id	 bigint unsigned comment	 '权限id',
status  tinyint unsigned comment	 '状态',
update_time	timestamp not null default current_timestamp on update current_timestamp	comment	 '修改时间',
create_time	timestamp not null default current_timestamp comment	 '创建时间',
update_id bigint unsigned comment '修改人',
create_id bigint unsigned not null comment	 '创建人',
note varchar(200) comment	'备注',
version	int unsigned not null default 0	comment	 '版本',
del_flag tinyint unsigned not null default 0 comment	 '0：正常 1：已删除',
primary key (id)
)comment '角色权限关联表';

drop table if exists dict_type;
create table dict_type(
id  bigint unsigned not null auto_increment comment '主键',
code varchar(100) comment	 '代码',
name varchar(100) comment	 '名称',
show_flag  tinyint unsigned not null default 1 comment	 '是否显示',
update_time	timestamp not null default current_timestamp on update current_timestamp	comment	 '修改时间',
create_time	timestamp not null default current_timestamp comment	 '创建时间',
update_id bigint unsigned comment '修改人',
create_id bigint unsigned not null comment	 '创建人',
note varchar(200) comment	'备注',
version	int unsigned not null default 0	comment	 '版本',
del_flag tinyint unsigned not null default 0 comment	 '0：正常 1：已删除',
primary key (id)
)comment '字典类型表';

drop table if exists dict;
create table dict(
id  bigint unsigned not null auto_increment comment '主键',
dict_type_id  bigint unsigned comment	 '类型id',
parent_id  bigint unsigned comment	 '父id',
code varchar(100) comment	 '代码',
name varchar(100) comment	 '名称',
sort	 int unsigned 	comment	 '排序',
show_flag  tinyint unsigned not null default 1 comment	 '是否显示',
update_time	timestamp not null default current_timestamp on update current_timestamp	comment	 '修改时间',
create_time	timestamp not null default current_timestamp comment	 '创建时间',
update_id bigint unsigned comment '修改人',
create_id bigint unsigned not null comment	 '创建人',
note varchar(200) comment	'备注',
version	int unsigned not null default 0	comment	 '版本',
del_flag tinyint unsigned not null default 0 comment	 '0：正常 1：已删除',
primary key (id)
)comment '字典明细表';

drop table if exists sys_area;
create table sys_area(
id  bigint unsigned not null auto_increment comment '主键',
parent_id  bigint unsigned comment	 '父id',
code varchar(100) comment	 '代码',
name varchar(100) comment	 '名称',
level	 tinyint unsigned 	comment	 '层级',
sort	 int unsigned 	comment	 '排序',
show_flag  tinyint unsigned comment	 '是否显示',
update_time	timestamp not null default current_timestamp on update current_timestamp	comment	 '修改时间',
create_time	timestamp not null default current_timestamp comment	 '创建时间',
update_id bigint unsigned comment '修改人',
create_id bigint unsigned not null comment	 '创建人',
note varchar(200) comment	'备注',
version	int unsigned not null default 0	comment	 '版本',
del_flag tinyint unsigned not null default 0 comment	 '0：正常 1：已删除',
primary key (id)
)comment '地区表';

drop table if exists dept;
create table dept(
id  bigint unsigned not null auto_increment comment '主键',
name	varchar(300) 	comment	 '部门名称',
short_name	varchar(100)	comment	 '部门简称',
code	varchar(50)	comment	 '部门编码',
parent_id	bigint unsigned 	comment	 '上级部门id',
level	varchar(50) 	comment	 '部门级别',
depart_type	varchar(50) 	comment	 '部门类型',
sort	int unsigned 	comment	 '排序',
postal_code	varchar(50)	comment	 '部门邮政编码',
unit_address	varchar(500)	comment	 '部门地址',
phone	varchar(50)	comment	 '部门电话号码',
fax_number	varchar(50)	comment	 '部门传真号码',
web_address	varchar(100)	comment	 '部门网址',
email	varchar(100)	comment	 '电子信箱',
contact_name	varchar(30)	comment	 '部门联系人姓名',
contact_phone	varchar(50)	comment	 '部门联系人电话',
description	VARCHAR(3000)	comment	 '简介',
approve_date	datetime	comment	 '部门成立日期',
update_time	timestamp	not null default current_timestamp on update current_timestamp	comment	 '修改时间',
create_time	timestamp	not null default current_timestamp	comment	 '创建时间',
update_id	bigint unsigned comment	 '修改人',
create_id	bigint unsigned not null	comment	 '创建人',
note	varchar(200)	comment	'备注',
version	int unsigned	not null default 0	comment	 '版本',
del_flag	tinyint unsigned not null default 0	comment	 '0：正常 1：已删除',
primary key (id)
)comment '部门'	;

drop table if exists post;
create table post(
id  bigint unsigned not null auto_increment comment	 'id',
depart_id	bigint unsigned	comment	 '部门主表id',
name	varchar(50)	comment	 '岗位名称',
full_name	varchar(200)	comment	 '岗位全称',
type	int unsigned	comment	 '岗位类别',
level	tinyint unsigned	comment	 '岗位层级',
description	VARCHAR(3000)	comment	 '描述信息',
time_begin	datetime	comment	 '生效时间',
time_end	datetime	comment	 '失效时间',
vacancy_time	datetime	comment	 '空缺时间',
update_time	timestamp	not null default current_timestamp on update current_timestamp	comment	 '修改时间',
create_time	timestamp	not null default current_timestamp	comment	 '创建时间',
update_id	bigint unsigned comment	 '修改人',
create_id	bigint unsigned not null	comment	 '创建人',
note	varchar(200)	comment	'备注',
version	int unsigned	not null default 0	comment	 '版本',
del_flag	tinyint unsigned not null default 0	comment	 '0：正常 1：已删除',
primary key (id)
)comment '岗位信息' ;

drop table if exists dept_post_ref;
create table dept_post_ref(
id  bigint unsigned not null auto_increment comment	 'id',
depart_id	bigint unsigned	 not null comment	 '部门主表id',
post_id bigint unsigned  not null	comment	 '岗位id',
status 	tinyint unsigned not null default 1 comment	 '状态 0 无效 1 有效',
update_time	timestamp	not null default current_timestamp on update current_timestamp	comment	 '修改时间',
create_time	timestamp	not null default current_timestamp	comment	 '创建时间',
update_id	bigint unsigned comment	 '修改人',
create_id	bigint unsigned not null	comment	 '创建人',
note	varchar(200)	comment	'备注',
version	int unsigned	not null default 0	comment	 '版本',
del_flag	tinyint unsigned not null default 0	comment	 '0：正常 1：已删除',
primary key (id)
)comment '部门岗位关联表' ;

drop table if exists headcount;
create table headcount(
id  bigint unsigned not null auto_increment comment	 'id',
dept_post_ref_id	bigint unsigned	 not null comment	 '部门岗位关联表id',
count	int unsigned	comment	 '编制人数',
update_time	timestamp	not null default current_timestamp on update current_timestamp	comment	 '修改时间',
create_time	timestamp	not null default current_timestamp	comment	 '创建时间',
update_id	bigint unsigned comment	 '修改人',
create_id	bigint unsigned not null	comment	 '创建人',
note	varchar(200)	comment	'备注',
version	int unsigned	not null default 0	comment	 '版本',
del_flag	tinyint unsigned not null default 0	comment	 '0：正常 1：已删除',
primary key (id)
)comment '编制表' ;

drop table if exists person;
create table person(
id  bigint unsigned not null auto_increment comment '主键',
photo_url	varchar(100)	comment	 '照片',
name	varchar(50)	comment	 '姓名',
code	varchar(20)	comment	 '工号',
cert_type	varchar(50)	comment	 '证件类型',
cert_id	varchar(100)	comment	 '身份证号',
spell	varchar(50)	comment	 '拼音',
old_name	varchar(50)	comment	 '曾用名',
english_name	varchar(50)	comment	 '英文名',
gender	tinyint unsigned	comment	 '性 别  0 女 1 男',
birthday	date	comment	 '出生日期',
height	tinyint unsigned	comment	 '身高',
weight	decimal(3,2)	comment	 '体重',
country_code	varchar(50)	comment	 '国籍(字典表)',
native_place_code	varchar(50)	comment	 '籍贯(字典表)',
native_place_suppl	varchar(200)	comment	 '籍贯补充',
nation_code	varchar(50)	comment	 '民族（字典表）',
politics_code	varchar(50)	comment	 '政治面貌（字典表）',
marital_status	tinyint unsigned	comment	 '婚姻状况(1 未婚 2 已婚 3 再婚 4 丧偶 5 离婚)',
speciality	varchar(1000)	comment	 '特长专长',
hobby	varchar(1000)	comment	 '兴趣爱好',
join_work_time	date	comment	 '参加工作时间',
birth_place_code	varchar(50)	comment	 '出生地（字典表）',
birth_place_suppl	varchar(200)	comment	 '出生地补充',
grow_place	varchar(50)	comment	 '成长地（字典表）',
grow_place_suppl	varchar(200)	comment	 '成长地补充',
description varchar(1000)	comment	 '描述',
entry_date	date	comment	 '入职时间',
attachment_url	varchar(100)	comment	 '附件url',
update_time	timestamp	not null default current_timestamp on update current_timestamp	comment	 '修改时间',
create_time	timestamp	not null default current_timestamp	comment	 '创建时间',
update_id	bigint unsigned comment	 '修改人',
create_id	bigint unsigned not null	comment	 '创建人',
note	varchar(200)	comment	'备注',
version	int unsigned	not null default 0	comment	 '版本',
del_flag	tinyint unsigned not null default 0	comment	 '0：正常 1：已删除',
primary key (id)
)comment '人员表';

drop table if exists person_post_ref;
create table person_post_ref(
id  bigint unsigned not null auto_increment comment '主键',
person_id	bigint unsigned	comment	 '依赖人员ID',
dept_post_ref_id	bigint unsigned	 not null comment	 '部门岗位关联表id',
update_time	timestamp	not null default current_timestamp on update current_timestamp	comment	 '修改时间',
create_time	timestamp	not null default current_timestamp	comment	 '创建时间',
update_id	bigint unsigned comment	 '修改人',
create_id	bigint unsigned not null	comment	 '创建人',
note	varchar(200)	comment	'备注',
version	int unsigned	not null default 0	comment	 '版本',
del_flag	tinyint unsigned not null default 0	comment	 '0：正常 1：已删除',
primary key (id)
)comment '人员岗位关联表';

drop table if exists person_leave;
create table person_leave(
id  bigint unsigned not null auto_increment comment '主键',
person_id	bigint unsigned	comment	 '依赖人员ID',
leave_time	date	not null 	comment	 '离职时间',
leave_reason	varchar(1000)	comment	 '离职原因',
update_time	timestamp	not null default current_timestamp on update current_timestamp	comment	 '修改时间',
create_time	timestamp	not null default current_timestamp	comment	 '创建时间',
update_id	bigint unsigned comment	 '修改人',
create_id	bigint unsigned not null	comment	 '创建人',
note	varchar(200)	comment	'备注',
version	int unsigned	not null default 0	comment	 '版本',
del_flag	tinyint unsigned not null default 0	comment	 '0：正常 1：已删除',
primary key (id)
)comment '离职表';

drop table if exists contact;
create table contact(
id  bigint unsigned not null auto_increment comment '主键',
person_id	bigint unsigned	comment	 '依赖人员ID',
home_address	varchar(600)	comment	 '家庭住址',
home_phone	varchar(20)	comment	 '家庭电话',
home_postal_code	varchar(10)	comment	 '家庭邮政编码 ',
unit_address	varchar(600)	comment	 '工作单位地址',
office_phone	varchar(20)	comment	 '办公电话',
unit_postal_code	varchar(10)	comment	 '工作单位邮政编码',
mobile_phone	varchar(20)	comment	 '移动电话',
email	varchar(100)	comment	 '电子邮箱',
wechat	varchar(100)	comment	 '微信',
QQ	varchar(20)	comment	 'QQ',
alipay	varchar(100)	comment	 '支付宝',
dingding	varchar(100)	comment	 '钉钉',
microblog	varchar(100)	comment	 '微博',
update_time	timestamp	not null default current_timestamp on update current_timestamp	comment	 '修改时间',
create_time	timestamp	not null default current_timestamp	comment	 '创建时间',
update_id	bigint unsigned comment	 '修改人',
create_id	bigint unsigned not null	comment	 '创建人',
note	varchar(200)	comment	'备注',
version	int unsigned	not null default 0	comment	 '版本',
del_flag	tinyint unsigned not null default 0	comment	 '0：正常 1：已删除',
primary key (id)
)comment '联系信息表';

drop table if exists position_info;
create table position_info(
id  bigint unsigned not null auto_increment comment '主键',
person_id	bigint unsigned	comment	 '依赖人员ID',
dept_post_ref_id	bigint unsigned	 not null comment	 '部门岗位关联表id',
position_type	tinyint unsigned	comment	 '任职方式（委派、任命）',
position_status	tinyint unsigned	comment	 '任职状态（在任 离任 挂任 试用）',
begin_date	date	comment	 '开始时间',
end_date	date	comment	 '结束时间',
position_content	varchar(5000)	comment	 '工作内容',
comment	varchar(5000)	comment	 '任职评价',
file_no	varchar(100)	comment	 '任职文号',
approve_unit	bigint unsigned	comment	 '批准机关(公司、部门)',
update_time	timestamp	not null default current_timestamp on update current_timestamp	comment	 '修改时间',
create_time	timestamp	not null default current_timestamp	comment	 '创建时间',
update_id	bigint unsigned comment	 '修改人',
create_id	bigint unsigned not null	comment	 '创建人',
note	varchar(200)	comment	'备注',
version	int unsigned	not null default 0	comment	 '版本',
del_flag	tinyint unsigned not null default 0	comment	 '0：正常 1：已删除',
primary key (id)
)comment '任职表';

drop table if exists degree;
create table degree(
id  bigint unsigned not null auto_increment comment '主键',
person_id	bigint unsigned	comment	 '依赖人员ID',
school_name	varchar(300)	comment	 '学校名称',
department	varchar(300)	comment	 '院系名称',
major	varchar(300)	comment	 '专业名称',
school_year	decimal(2,2)	comment	 '学 制（年）',
begin_date	date	comment	 '入学日期',
end_date	date	comment	 '毕业日期',
academic_code	varchar(50)	comment	 '学历 (大专 本科 硕士 博士 其他)',
academic_suppl	varchar(200)	comment	 '学历补充说明',
full_time_flag	tinyint unsigned	comment	 '是否全日制（0 非全日制 1全日制）',
highest_academic_flag	tinyint unsigned	comment	 '是否学历最高 （0否 1是）',
first_degree_flag	tinyint unsigned	comment	 '是否第一学历 （0否 1是）',
update_time	timestamp	not null default current_timestamp on update current_timestamp	comment	 '修改时间',
create_time	timestamp	not null default current_timestamp	comment	 '创建时间',
update_id	bigint unsigned comment	 '修改人',
create_id	bigint unsigned not null	comment	 '创建人',
note	varchar(200)	comment	'备注',
version	int unsigned	not null default 0	comment	 '版本',
del_flag	tinyint unsigned not null default 0	comment	 '0：正常 1：已删除',
primary key (id)
)comment '学历学位表';

drop table if exists work_experience;
create table work_experience(
id  bigint unsigned not null auto_increment comment '主键',
person_id	bigint unsigned	comment	 '依赖人员ID',
begin_date	date	comment	 '开始时间',
end_date	date	comment	 '结束时间',
company_name	varchar(300)	comment	 '公司名称',
company_scale	varchar(50)	comment	 '公司规模',
company_industry	varchar(50)	comment	 '公司性质',
company_nature	varchar(50)	comment	 '公司行业',
position_name	varchar(100)	comment	 '职位名称',
position_dept	varchar(100)	comment	 '任职部门',
work_content	varchar(5000)	comment	 '工作内容',
work_type	tinyint unsigned	comment	 '工作类型 （0 非全职 1 全职）',
leave_reason	varchar(5000)	comment	 '离职原因',
certifier	varchar(50)	comment	 '证明人',
certifier_phone	varchar(20)	comment	 '证明人电话',
update_time	timestamp	not null default current_timestamp on update current_timestamp	comment	 '修改时间',
create_time	timestamp	not null default current_timestamp	comment	 '创建时间',
update_id	bigint unsigned comment	 '修改人',
create_id	bigint unsigned not null	comment	 '创建人',
note	varchar(200)	comment	'备注',
version	int unsigned	not null default 0	comment	 '版本',
del_flag	tinyint unsigned not null default 0	comment	 '0：正常 1：已删除',
primary key (id)
)comment '工作经历';

drop table if exists project_experience;
create table project_experience(
id  bigint unsigned not null auto_increment comment '主键',
person_id	bigint unsigned	comment	 '依赖人员ID',
work_experience_id	bigint unsigned	comment	 '工作经历ID',
begin_date	date	comment	 '开始时间',
end_date	date	comment	 '结束时间',
project_name	varchar(300)	comment	 '项目名称',
project_desc	varchar(5000)	comment	 '项目描述',
duty_desc	varchar(5000)	comment	 '责任描述',
project_achievement	varchar(5000)	comment	 '项目成就',
project_url	varchar(100)	comment	 '项目链接',
update_time	timestamp	not null default current_timestamp on update current_timestamp	comment	 '修改时间',
create_time	timestamp	not null default current_timestamp	comment	 '创建时间',
update_id	bigint unsigned comment	 '修改人',
create_id	bigint unsigned not null	comment	 '创建人',
note	varchar(200)	comment	'备注',
version	int unsigned	not null default 0	comment	 '版本',
del_flag	tinyint unsigned not null default 0	comment	 '0：正常 1：已删除',
primary key (id)
)comment '项目经历';

drop table if exists training;
create table training(
id  bigint unsigned not null auto_increment comment '主键',
person_id	bigint unsigned	comment	 '依赖人员ID',
begin_date	date	comment	 '开始时间',
end_date	date	comment	 '结束时间',
day	tinyint unsigned unsigned	comment	 '培训时长天',
hour	tinyint unsigned unsigned	comment	 '培训时长时',
training_organ	varchar(300)	comment	 '培训机构',
training_project	varchar(300)	comment	 '培训项目',
training_content	varchar(3000)	comment	 '培训内容',
certificate	varchar(300)	comment	 '所获证书',
certificate_organt	varchar(300)	comment	 '证书颁发机构',
master_level_code	varchar(50)	comment	 '掌握程度',
certifier	varchar(50)	comment	 '证明人',
certifier_phone	varchar(20)	comment	 '证明人电话',
update_time	timestamp	not null default current_timestamp on update current_timestamp	comment	 '修改时间',
create_time	timestamp	not null default current_timestamp	comment	 '创建时间',
update_id	bigint unsigned comment	 '修改人',
create_id	bigint unsigned not null	comment	 '创建人',
note	varchar(200)	comment	'备注',
version	int unsigned	not null default 0	comment	 '版本',
del_flag	tinyint unsigned not null default 0	comment	 '0：正常 1：已删除',
primary key (id)
)comment '培训经历表';

drop table if exists award_punishment;
create table award_punishment(
id  bigint unsigned not null auto_increment comment '主键',
person_id	bigint unsigned	comment	 '依赖人员ID',
ap_type	tinyint unsigned	comment	 '类别 0惩罚 1奖励',
ap_level_code	varchar(50)	comment	 '奖惩级别（公司自定义 例如集团  公司 其他）',
ap_name	varchar(300)	comment	 '奖惩名称',
ap_date	date	comment	 '奖惩时间',
ap_desc	varchar(5000)	comment	 '奖惩说明',
effect_period	date	comment	 '影响期',
cancle_date	date	comment	 '撤销日期',
file_no	varchar(100)	comment	 '文件号',
approve_unit_id	bigint unsigned	comment	 '批准机关(公司)',
update_time	timestamp	not null default current_timestamp on update current_timestamp	comment	 '修改时间',
create_time	timestamp	not null default current_timestamp	comment	 '创建时间',
update_id	bigint unsigned comment	 '修改人',
create_id	bigint unsigned not null	comment	 '创建人',
note	varchar(200)	comment	'备注',
version	int unsigned	not null default 0	comment	 '版本',
del_flag	tinyint unsigned not null default 0	comment	 '0：正常 1：已删除',
primary key (id)
)comment '奖惩信息表';

drop table if exists key_experience;
create table key_experience(
id  bigint unsigned not null auto_increment comment '主键',
person_id	bigint unsigned	comment	 '依赖人员ID',
work_experience_id	bigint unsigned	comment	 '工作经历ID',
begin_date	date	comment	 '开始时间',
end_date	date	comment	 '结束时间',
content	varchar(3000)	comment	 '事件',
comment	varchar(3000)	comment	 '结果评价',
update_time	timestamp	not null default current_timestamp on update current_timestamp	comment	 '修改时间',
create_time	timestamp	not null default current_timestamp	comment	 '创建时间',
update_id	bigint unsigned comment	 '修改人',
create_id	bigint unsigned not null	comment	 '创建人',
note	varchar(200)	comment	'备注',
version	int unsigned	not null default 0	comment	 '版本',
del_flag	tinyint unsigned not null default 0	comment	 '0：正常 1：已删除',
primary key (id)
)comment '关键历练';


drop table if exists family;
create table family(
id  bigint unsigned not null auto_increment comment '主键',
person_id	bigint unsigned	comment	 '依赖人员ID',
relation_code	varchar(50)	comment	 '关系（配偶、父亲、母亲、儿子、女儿、祖父、祖母、孙子、孙女）',
name	varchar(50)	comment	 '姓名',
cert_type	varchar(50)	comment	 '证件类型',
cert_id	varchar(100)	comment	 '证件号码',
gender	tinyint unsigned	comment	 '性别',
country_code	varchar(50)	comment	 '国籍(字典表)',
politics_code	varchar(50)	comment	 '政治面貌（字典表）',
academic_code	varchar(50)	comment	 '学历 (大专 本科 硕士 博士 其他)',
nation_code	varchar(50)	comment	 '民族（字典表）',
birthday	date	comment	 '出生日期',
unit_position	varchar(1000)	comment	 '工作单位及职务',
cellphone	varchar(20)	comment	 '联系电话',
update_time	timestamp	not null default current_timestamp on update current_timestamp	comment	 '修改时间',
create_time	timestamp	not null default current_timestamp	comment	 '创建时间',
update_id	bigint unsigned comment	 '修改人',
create_id	bigint unsigned not null	comment	 '创建人',
note	varchar(200)	comment	'备注',
version	int unsigned	not null default 0	comment	 '版本',
del_flag	tinyint unsigned not null default 0	comment	 '0：正常 1：已删除',
primary key (id)
)comment '家庭信息';

drop table if exists professional_title;
create table professional_title (
id  bigint unsigned not null auto_increment comment '主键',
person_id	bigint unsigned	comment	 '依赖人员ID',
name	varchar(300)	comment	 '证书名称',
receive_date	date	comment	 '获得日期',
certificate_no	varchar(100)	comment	 '证书编号',
certificate_organt	varchar(300)	comment	 '发证机构',
attachment_url	varchar(100)	comment	 '附件id',
update_time	timestamp	not null default current_timestamp on update current_timestamp	comment	 '修改时间',
create_time	timestamp	not null default current_timestamp	comment	 '创建时间',
update_id	bigint unsigned comment	 '修改人',
create_id	bigint unsigned not null	comment	 '创建人',
note	varchar(200)	comment	'备注',
version	int unsigned	not null default 0	comment	 '版本',
del_flag	tinyint unsigned not null default 0	comment	 '0：正常 1：已删除',
primary key (id)
)comment '资格证书';

drop table if exists language;
create table language (
id  bigint unsigned not null auto_increment comment '主键',
person_id	bigint unsigned	comment	 '依赖人员ID',
language_type	varchar(50)	comment	 '语种(字典表 可参考SAP)',
language_certificate	varchar(300)	comment	 '证书',
language_level	varchar(100)	comment	 '级别',
master_level	varchar(50)	comment	 '熟练程度',
first_language_flag	tinyint unsigned	comment	 '是否第一语种',
update_time	timestamp	not null default current_timestamp on update current_timestamp	comment	 '修改时间',
create_time	timestamp	not null default current_timestamp	comment	 '创建时间',
update_id	bigint unsigned comment	 '修改人',
create_id	bigint unsigned not null	comment	 '创建人',
note	varchar(200)	comment	'备注',
version	int unsigned	not null default 0	comment	 '版本',
del_flag	tinyint unsigned not null default 0	comment	 '0：正常 1：已删除',
primary key (id)
)comment '外语水平';

drop table if exists performance;
create table performance (
id  bigint unsigned not null auto_increment comment '主键',
person_id	bigint unsigned	comment	 '人员主键',
check_year	int 	comment	 '考核年度',
type	varchar(50)	comment	 '考核类别',
direct_manage	bigint unsigned	comment	 '直接上级',
percent	int unsigned	comment	 '权重百分比(%)',
level	varchar(50)	comment	 '绩效等级',
check_time	date	comment	 '考核时间',
attachment_url	varchar(100)	comment	 '附件',
update_time	timestamp	not null default current_timestamp on update current_timestamp	comment	 '修改时间',
create_time	timestamp	not null default current_timestamp	comment	 '创建时间',
update_id	bigint unsigned comment	 '修改人',
create_id	bigint unsigned not null	comment	 '创建人',
note	varchar(200)	comment	'备注',
version	int unsigned	not null default 0	comment	 '版本',
del_flag	tinyint unsigned not null default 0	comment	 '0：正常 1：已删除',
primary key (id)
)comment '绩效表';

drop table if exists tag;
create table tag (
id  bigint unsigned not null auto_increment comment '主键',
type	varchar(50)	comment	 '标签类型',
name	varchar(300)	comment	 '标签名字',
status	tinyint unsigned	comment	 '标签状态',
update_time	timestamp	not null default current_timestamp on update current_timestamp	comment	 '修改时间',
create_time	timestamp	not null default current_timestamp	comment	 '创建时间',
update_id	bigint unsigned comment	 '修改人',
create_id	bigint unsigned not null	comment	 '创建人',
note	varchar(200)	comment	'备注',
version	int unsigned	not null default 0	comment	 '版本',
del_flag	tinyint unsigned not null default 0	comment	 '0：正常 1：已删除',
primary key (id)
)comment '人员标签';

drop table if exists person_tag_ref;
create table person_tag_ref (
id  bigint unsigned not null auto_increment comment '主键',
person_id	bigint unsigned	comment	 '人员id',
tag_id	bigint unsigned	comment	 '标签id',
status 	tinyint unsigned not null default 1 comment	 '状态 0 无效 1 有效',
update_time	timestamp	not null default current_timestamp on update current_timestamp	comment	 '修改时间',
create_time	timestamp	not null default current_timestamp	comment	 '创建时间',
update_id	bigint unsigned comment	 '修改人',
create_id	bigint unsigned not null	comment	 '创建人',
note	varchar(200)	comment	'备注',
version	int unsigned	not null default 0	comment	 '版本',
del_flag	tinyint unsigned not null default 0	comment	 '0：正常 1：已删除',
primary key (id)
)comment '人员标签关联表';

drop table if exists evaluation;
create table evaluation (
id  bigint unsigned not null auto_increment comment '主键',
person_id	bigint unsigned	comment	 '人员id',
type	varchar(50)	comment	 '测评类型字典值',
result json NOT NULL	comment	 '测评结果',
description varchar(500)	comment	'描述',
update_time	timestamp	not null default current_timestamp on update current_timestamp	comment	 '修改时间',
create_time	timestamp	not null default current_timestamp	comment	 '创建时间',
update_id	bigint unsigned comment	 '修改人',
create_id	bigint unsigned not null	comment	 '创建人',
note	varchar(200)	comment	'备注',
version	int unsigned	not null default 0	comment	 '版本',
del_flag	tinyint unsigned not null default 0	comment	 '0：正常 1：已删除',
primary key (id)
)comment '测评表';

drop table if exists classify_info;
create table classify_info (
id  bigint unsigned not null auto_increment comment '主键',
parent_id  bigint unsigned comment '父id',
name	varchar(50)	comment	 '人员类别名称',
type varchar(50)	comment	 '类型',
status tinyint	comment	'状态',
update_time	timestamp	not null default current_timestamp on update current_timestamp	comment	 '修改时间',
create_time	timestamp	not null default current_timestamp	comment	 '创建时间',
update_id	bigint unsigned comment	 '修改人',
create_id	bigint unsigned not null	comment	 '创建人',
note	varchar(200)	comment	'备注',
version	int unsigned	not null default 0	comment	 '版本',
del_flag	tinyint unsigned not null default 0	comment	 '0：正常 1：已删除',
primary key (id)
)comment '人员分类表';

create table person_classify_ref (
id  bigint unsigned not null auto_increment comment '主键',
person_id	bigint unsigned	not null  comment	 '人员id',
classify_info_id bigint unsigned not null comment '父id',
status tinyint	comment	'状态',
update_time	timestamp	not null default current_timestamp on update current_timestamp	comment	 '修改时间',
create_time	timestamp	not null default current_timestamp	comment	 '创建时间',
update_id	bigint unsigned comment	 '修改人',
create_id	bigint unsigned not null	comment	 '创建人',
note	varchar(200)	comment	'备注',
version	int unsigned	not null default 0	comment	 '版本',
del_flag	tinyint unsigned not null default 0	comment	 '0：正常 1：已删除',
primary key (id)
)comment '人员分类关联表';
