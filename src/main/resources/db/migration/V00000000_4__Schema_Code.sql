-- Project Name : SpringApp
-- Date/Time    : 2015/01/17 19:51:39
-- Author       : agwlvssainokuni
-- RDBMS Type   : IBM DB2
-- Application  : A5:SQL Mk-2

-- 区分値マスタ
create table code_master (
  id BIGINT not null AUTO_INCREMENT
  , name VARCHAR(30) not null
  , value VARCHAR(5) not null
  , label VARCHAR(50) not null
  , sort_order INTEGER default 0 not null
  , updated_at TIMESTAMP default CURRENT_TIMESTAMP not null
  , created_at TIMESTAMP default CURRENT_TIMESTAMP not null
  , lock_version INTEGER default 1 not null
  , deleted_flg INTEGER default 0 not null
  , constraint code_master_PKC primary key (id)
) ;

create index code_master_IX1
  on code_master(name);

comment on table code_master is '区分値マスタ';
comment on column code_master.id is 'ID';
comment on column code_master.name is '区分値名称';
comment on column code_master.value is '区分値';
comment on column code_master.label is '表示名称';
comment on column code_master.sort_order is '表示順序';
comment on column code_master.updated_at is '更新日時';
comment on column code_master.created_at is '作成日時';
comment on column code_master.lock_version is 'ロックバージョン';
comment on column code_master.deleted_flg is '削除フラグ';
