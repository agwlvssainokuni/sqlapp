-- Project Name : SpringApp
-- Date/Time    : 2014/12/14 10:34:50
-- Author       : agwlvssainokuni
-- RDBMS Type   : IBM DB2
-- Application  : A5:SQL Mk-2

-- メールテンプレート
create table mail_template (
  id BIGINT not null AUTO_INCREMENT
  , template_name VARCHAR(32) not null
  , from_addr VARCHAR(512) not null
  , subject VARCHAR(1024) not null
  , body VARCHAR(4096) not null
  , updated_at TIMESTAMP default CURRENT_TIMESTAMP not null
  , created_at TIMESTAMP default CURRENT_TIMESTAMP not null
  , lock_version INTEGER default 1 not null
  , deleted_flg INTEGER default 0 not null
  , constraint mail_template_PKC primary key (id)
) ;

create index mail_template_IX1
  on mail_template(template_name);

-- メールテンプレート宛先
create table mail_template_address (
  id BIGINT not null AUTO_INCREMENT
  , template_id BIGINT not null
  , rcpt_type VARCHAR(3) not null
  , rcpt_addr VARCHAR(512) not null
  , updated_at TIMESTAMP default CURRENT_TIMESTAMP not null
  , created_at TIMESTAMP default CURRENT_TIMESTAMP not null
  , lock_version INTEGER default 1 not null
  , deleted_flg INTEGER default 0 not null
  , constraint mail_template_address_PKC primary key (id)
) ;

create index mail_template_address_IX1
  on mail_template_address(template_id);

comment on table mail_template is 'メールテンプレート';
comment on column mail_template.id is 'ID';
comment on column mail_template.template_name is 'テンプレート名';
comment on column mail_template.from_addr is '差出人';
comment on column mail_template.subject is '件名';
comment on column mail_template.body is '本文';
comment on column mail_template.updated_at is '更新日時';
comment on column mail_template.created_at is '作成日時';
comment on column mail_template.lock_version is 'ロックバージョン';
comment on column mail_template.deleted_flg is '削除フラグ';

comment on table mail_template_address is 'メールテンプレート宛先';
comment on column mail_template_address.id is 'ID';
comment on column mail_template_address.template_id is 'メールテンプレートID';
comment on column mail_template_address.rcpt_type is '宛先区分';
comment on column mail_template_address.rcpt_addr is '宛先アドレス';
comment on column mail_template_address.updated_at is '更新日時';
comment on column mail_template_address.created_at is '作成日時';
comment on column mail_template_address.lock_version is 'ロックバージョン';
comment on column mail_template_address.deleted_flg is '削除フラグ';
