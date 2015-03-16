-- Project Name : SpringApp
-- Date/Time    : 2015/02/01 7:36:51
-- Author       : agwlvssainokuni
-- RDBMS Type   : IBM DB2
-- Application  : A5:SQL Mk-2

-- 発番マスタ
create table numbering_master (
  id BIGINT not null AUTO_INCREMENT
  , name VARCHAR(30) not null
  , template VARCHAR(30) default 0000000000 not null
  , min_value BIGINT default 0 not null
  , max_value BIGINT default 9999999999 not null
  , current_value BIGINT default 0 not null
  , updated_at TIMESTAMP default CURRENT_TIMESTAMP not null
  , created_at TIMESTAMP default CURRENT_TIMESTAMP not null
  , lock_version INTEGER default 1 not null
  , deleted_flg INTEGER default 0 not null
  , constraint numbering_master_PKC primary key (id)
) ;

create index numbering_master_IX1
  on numbering_master(name);

comment on table numbering_master is '発番マスタ';
comment on column numbering_master.id is 'ID';
comment on column numbering_master.name is '発番名称';
comment on column numbering_master.template is 'テンプレート';
comment on column numbering_master.min_value is '最小値';
comment on column numbering_master.max_value is '最大値';
comment on column numbering_master.current_value is '現在値';
comment on column numbering_master.updated_at is '更新日時';
comment on column numbering_master.created_at is '作成日時';
comment on column numbering_master.lock_version is 'ロックバージョン';
comment on column numbering_master.deleted_flg is '削除フラグ';
