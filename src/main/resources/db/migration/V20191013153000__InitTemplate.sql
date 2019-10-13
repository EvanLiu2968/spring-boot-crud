drop table if exists template;
create table template
(
   id                       bigint UNSIGNED not null auto_increment,
   template_name            varchar(32) not null comment '模板名称',
   template_value           varchar(128) not null comment '模板值',
   create_id                bigint UNSIGNED comment '创建人',
   update_id                bigint UNSIGNED comment '修改人',
   create_time              timestamp not null default CURRENT_TIMESTAMP comment '创建时间',
   update_time              timestamp not null default CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP comment '修改时间',
   note                     varchar(200) comment '备注',
   version                  tinyint(1) UNSIGNED default 0 comment '版本',
   del_flag              tinyint(1) UNSIGNED default 0 comment '0：正常 1：已删除',
   primary key (id)
)COMMENT='模板表';

