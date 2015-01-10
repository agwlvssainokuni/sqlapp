-- Project Name : SpringApp
-- Date/Time    : 2014/12/27 7:16:05
-- Author       : agwlvssainokuni
-- RDBMS Type   : IBM DB2
-- Application  : A5:SQL Mk-2

-- メール送信キュー
create table mail_queue (
  id BIGINT not null AUTO_INCREMENT
  , mail_id BIGINT not null
  , scheduled_at TIMESTAMP not null
  , updated_at TIMESTAMP default CURRENT_TIMESTAMP not null
  , created_at TIMESTAMP default CURRENT_TIMESTAMP not null
  , lock_version INTEGER default 1 not null
  , deleted_flg INTEGER default 0 not null
  , constraint mail_queue_PKC primary key (id)
) ;

create index mail_queue_IX1
  on mail_queue(mail_id);

-- メール宛先
create table mail_rcpt (
  id BIGINT not null AUTO_INCREMENT
  , mail_id BIGINT not null
  , rcpt_type VARCHAR(3) not null
  , rcpt_addr VARCHAR(300) not null
  , updated_at TIMESTAMP default CURRENT_TIMESTAMP not null
  , created_at TIMESTAMP default CURRENT_TIMESTAMP not null
  , lock_version INTEGER default 1 not null
  , deleted_flg INTEGER default 0 not null
  , constraint mail_rcpt_PKC primary key (id)
) ;

-- メール送信記録
create table mail_log (
  id BIGINT not null AUTO_INCREMENT
  , launched_by VARCHAR(100) not null
  , launched_at TIMESTAMP not null
  , mail_status INTEGER not null
  , message_name VARCHAR(30) not null
  , scheduled_at TIMESTAMP not null
  , sent_at TIMESTAMP
  , from_addr VARCHAR(300) not null
  , subject VARCHAR(1000) not null
  , body VARCHAR(5000) not null
  , updated_at TIMESTAMP default CURRENT_TIMESTAMP not null
  , created_at TIMESTAMP default CURRENT_TIMESTAMP not null
  , lock_version INTEGER default 1 not null
  , deleted_flg INTEGER default 0 not null
  , constraint mail_log_PKC primary key (id)
) ;

create index mail_log_IX1
  on mail_log(launched_by);

create index mail_log_IX2
  on mail_log(scheduled_at);

comment on table mail_queue is 'メール送信キュー';
comment on column mail_queue.id is 'ID';
comment on column mail_queue.mail_id is 'メール送信記録ID';
comment on column mail_queue.scheduled_at is '送信予定日時';
comment on column mail_queue.updated_at is '更新日時';
comment on column mail_queue.created_at is '作成日時';
comment on column mail_queue.lock_version is 'ロックバージョン';
comment on column mail_queue.deleted_flg is '削除フラグ';

comment on table mail_rcpt is 'メール宛先';
comment on column mail_rcpt.id is 'ID';
comment on column mail_rcpt.mail_id is 'メール送信記録ID';
comment on column mail_rcpt.rcpt_type is '宛先区分';
comment on column mail_rcpt.rcpt_addr is '宛先アドレス';
comment on column mail_rcpt.updated_at is '更新日時';
comment on column mail_rcpt.created_at is '作成日時';
comment on column mail_rcpt.lock_version is 'ロックバージョン';
comment on column mail_rcpt.deleted_flg is '削除フラグ';

comment on table mail_log is 'メール送信記録';
comment on column mail_log.id is 'ID';
comment on column mail_log.launched_by is '投入者ID';
comment on column mail_log.launched_at is '投入日時';
comment on column mail_log.mail_status is 'メール送信状況';
comment on column mail_log.message_name is 'メールメッセージ名';
comment on column mail_log.scheduled_at is '送信予定日時';
comment on column mail_log.sent_at is '送信実績日時';
comment on column mail_log.from_addr is '差出人アドレス';
comment on column mail_log.subject is '件名';
comment on column mail_log.body is '本文';
comment on column mail_log.updated_at is '更新日時';
comment on column mail_log.created_at is '作成日時';
comment on column mail_log.lock_version is 'ロックバージョン';
comment on column mail_log.deleted_flg is '削除フラグ';
