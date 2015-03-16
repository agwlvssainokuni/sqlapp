-- Project Name : SpringApp
-- Date/Time    : 2015/02/24 23:17:39
-- Author       : agwlvssainokuni
-- RDBMS Type   : IBM DB2
-- Application  : A5:SQL Mk-2

-- 桁マスタ
create table digit (
  d INTEGER not null
  , updated_at TIMESTAMP default CURRENT_TIMESTAMP not null
  , created_at TIMESTAMP default CURRENT_TIMESTAMP not null
  , lock_version INTEGER default 1 not null
  , deleted_flg INTEGER default 0 not null
  , constraint digit_PKC primary key (d)
) ;

-- 休日マスタ
create table dayoff_master (
  name VARCHAR(10) default 'standard' not null
  , dt DATE not null
  , type VARCHAR(2) default '00' not null
  , updated_at TIMESTAMP default CURRENT_TIMESTAMP not null
  , created_at TIMESTAMP default CURRENT_TIMESTAMP not null
  , lock_version INTEGER default 1 not null
  , deleted_flg INTEGER default 0 not null
  , constraint dayoff_master_PKC primary key (name,dt)
) ;

-- 業務日時マスタ
create table bizdatetime_master (
  id BIGINT not null AUTO_INCREMENT
  , bizdate DATE default CURRENT_DATE not null
  , offset_day INTEGER default 0 not null
  , offset_hour INTEGER default 0 not null
  , offset_minute INTEGER default 0 not null
  , offset_second INTEGER default 0 not null
  , updated_at TIMESTAMP default CURRENT_TIMESTAMP not null
  , created_at TIMESTAMP default CURRENT_TIMESTAMP not null
  , lock_version INTEGER default 1 not null
  , deleted_flg INTEGER default 0 not null
  , constraint bizdatetime_master_PKC primary key (id)
) ;

comment on table digit is '桁マスタ';
comment on column digit.d is '桁';
comment on column digit.updated_at is '更新日時';
comment on column digit.created_at is '作成日時';
comment on column digit.lock_version is 'ロックバージョン';
comment on column digit.deleted_flg is '削除フラグ';

comment on table dayoff_master is '休日マスタ';
comment on column dayoff_master.name is '識別名';
comment on column dayoff_master.dt is '年月日';
comment on column dayoff_master.type is '休日区分';
comment on column dayoff_master.updated_at is '更新日時';
comment on column dayoff_master.created_at is '作成日時';
comment on column dayoff_master.lock_version is 'ロックバージョン';
comment on column dayoff_master.deleted_flg is '削除フラグ';

comment on table bizdatetime_master is '業務日時マスタ';
comment on column bizdatetime_master.id is 'ID';
comment on column bizdatetime_master.bizdate is '業務日付';
comment on column bizdatetime_master.offset_day is 'オフセット(日)';
comment on column bizdatetime_master.offset_hour is 'オフセット(時)';
comment on column bizdatetime_master.offset_minute is 'オフセット(分)';
comment on column bizdatetime_master.offset_second is 'オフセット(秒)';
comment on column bizdatetime_master.updated_at is '更新日時';
comment on column bizdatetime_master.created_at is '作成日時';
comment on column bizdatetime_master.lock_version is 'ロックバージョン';
comment on column bizdatetime_master.deleted_flg is '削除フラグ';
