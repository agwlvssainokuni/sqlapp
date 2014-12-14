-- Project Name : SpringApp
-- Date/Time    : 2014/12/14 10:04:51
-- Author       : agwlvssainokuni
-- RDBMS Type   : IBM DB2
-- Application  : A5:SQL Mk-2

-- 非同期実行状況管理・例外
create table async_process_exception (
  id BIGINT not null AUTO_INCREMENT
  , async_id BIGINT not null
  , exception TEXT not null
  , updated_at TIMESTAMP default CURRENT_TIMESTAMP not null
  , created_at TIMESTAMP default CURRENT_TIMESTAMP not null
  , lock_version INTEGER default 1 not null
  , deleted_flg INTEGER default 0 not null
  , constraint async_process_exception_PKC primary key (id)
) ;

create index async_process_exception_IX1
  on async_process_exception(async_id);

-- 非同期実行状況管理・コマンド・結果
create table async_process_command_result (
  ID BIGINT not null AUTO_INCREMENT
  , async_id BIGINT not null
  , exit_value INTEGER not null
  , stdout VARCHAR(1024) not null
  , stderr VARCHAR(1024) not null
  , updated_at TIMESTAMP default CURRENT_TIMESTAMP not null
  , created_at TIMESTAMP default CURRENT_TIMESTAMP not null
  , lock_version INTEGER default 1 not null
  , deleted_flg INTEGER default 0 not null
  , constraint async_process_command_result_PKC primary key (ID)
) ;

create index async_process_command_result_IX1
  on async_process_command_result(async_id);

-- 非同期実行状況管理・コマンド・引数
create table async_process_command_arg (
  id BIGINT not null AUTO_INCREMENT
  , async_id BIGINT not null
  , argument VARCHAR(50) not null
  , updated_at TIMESTAMP default CURRENT_TIMESTAMP not null
  , created_at TIMESTAMP default CURRENT_TIMESTAMP not null
  , lock_version INTEGER default 1 not null
  , deleted_flg INTEGER default 0 not null
  , constraint async_process_command_arg_PKC primary key (id)
) ;

create index async_process_command_arg_IX1
  on async_process_command_arg(async_id);

-- 非同期実行状況管理・コマンド
create table async_process_command (
  id BIGINT not null AUTO_INCREMENT
  , async_id BIGINT not null
  , command VARCHAR(50) not null
  , updated_at TIMESTAMP default CURRENT_TIMESTAMP not null
  , created_at TIMESTAMP default CURRENT_TIMESTAMP not null
  , lock_version INTEGER default 1 not null
  , deleted_flg INTEGER default 0 not null
  , constraint async_process_command_PKC primary key (id)
) ;

create index async_process_command_IX1
  on async_process_command(async_id);

-- 非同期実行状況管理・ファイル処理・結果詳細
create table async_process_file_result_detail (
  id BIGINT not null AUTO_INCREMENT
  , async_id BIGINT not null
  , record_number BIGINT not null
  , description VARCHAR(50)
  , updated_at TIMESTAMP default CURRENT_TIMESTAMP not null
  , created_at TIMESTAMP default CURRENT_TIMESTAMP not null
  , lock_version INTEGER default 1 not null
  , deleted_flg INTEGER default 0 not null
  , constraint async_process_file_result_detail_PKC primary key (id)
) ;

create index async_process_file_result_detail_IX1
  on async_process_file_result_detail(async_id);

-- 非同期実行状況管理・ファイル処理・結果
create table async_process_file_result (
  id BIGINT not null AUTO_INCREMENT
  , async_id BIGINT not null
  , total_count BIGINT not null
  , ok_count BIGINT not null
  , ng_count BIGINT not null
  , updated_at TIMESTAMP default CURRENT_TIMESTAMP not null
  , created_at TIMESTAMP default CURRENT_TIMESTAMP not null
  , lock_version INTEGER default 1 not null
  , deleted_flg INTEGER default 0 not null
  , constraint async_process_file_result_PKC primary key (id)
) ;

create index async_process_file_result_IX1
  on async_process_file_result(async_id);

-- 非同期実行状況管理・ファイル処理・引数
create table async_process_file_arg (
  id BIGINT not null AUTO_INCREMENT
  , async_id BIGINT not null
  , argument VARCHAR(50) not null
  , updated_at TIMESTAMP default CURRENT_TIMESTAMP not null
  , created_at TIMESTAMP default CURRENT_TIMESTAMP not null
  , lock_version INTEGER default 1 not null
  , deleted_flg INTEGER default 0 not null
  , constraint async_process_file_arg_PKC primary key (id)
) ;

create index async_process_file_arg_IX1
  on async_process_file_arg(async_id);

-- 非同期実行状況管理・ファイル処理
create table async_process_file (
  id BIGINT not null AUTO_INCREMENT
  , async_id BIGINT not null
  , handler_name VARCHAR(50) not null
  , param_name VARCHAR(50) not null
  , original_filename VARCHAR(50) not null
  , content_type VARCHAR(50) not null
  , file_size BIGINT not null
  , updated_at TIMESTAMP default CURRENT_TIMESTAMP not null
  , created_at TIMESTAMP default CURRENT_TIMESTAMP not null
  , lock_version INTEGER default 1 not null
  , deleted_flg INTEGER default 0 not null
  , constraint async_process_file_PKC primary key (id)
) ;

create index async_process_file_IX1
  on async_process_file(async_id);

-- 非同期実行状況管理
create table async_process (
  id BIGINT not null AUTO_INCREMENT
  , launched_by VARCHAR(32) not null
  , description VARCHAR(64) not null
  , async_type VARCHAR(3) not null
  , async_status VARCHAR(3) not null
  , registered_at TIMESTAMP not null
  , launched_at TIMESTAMP
  , started_at TIMESTAMP
  , finished_at TIMESTAMP
  , updated_at TIMESTAMP default CURRENT_TIMESTAMP not null
  , created_at TIMESTAMP default CURRENT_TIMESTAMP not null
  , lock_version INTEGER default 1 not null
  , deleted_flg INTEGER default 0 not null
  , constraint async_process_PKC primary key (id)
) ;

create index async_process_IX1
  on async_process(launched_by);

comment on table async_process_exception is '非同期実行状況管理・例外';
comment on column async_process_exception.id is 'ID';
comment on column async_process_exception.async_id is '非同期実行状況管理ID';
comment on column async_process_exception.exception is '例外情報';
comment on column async_process_exception.updated_at is '更新日時';
comment on column async_process_exception.created_at is '作成日時';
comment on column async_process_exception.lock_version is 'ロックバージョン';
comment on column async_process_exception.deleted_flg is '削除フラグ';

comment on table async_process_command_result is '非同期実行状況管理・コマンド・結果';
comment on column async_process_command_result.ID is 'ID';
comment on column async_process_command_result.async_id is '非同期実行状況管理ID';
comment on column async_process_command_result.exit_value is '終了コード';
comment on column async_process_command_result.stdout is '標準出力';
comment on column async_process_command_result.stderr is '標準エラー出力';
comment on column async_process_command_result.updated_at is '更新日時';
comment on column async_process_command_result.created_at is '作成日時';
comment on column async_process_command_result.lock_version is 'ロックバージョン';
comment on column async_process_command_result.deleted_flg is '削除フラグ';

comment on table async_process_command_arg is '非同期実行状況管理・コマンド・引数';
comment on column async_process_command_arg.id is 'ID';
comment on column async_process_command_arg.async_id is '非同期実行状況管理ID';
comment on column async_process_command_arg.argument is '引数';
comment on column async_process_command_arg.updated_at is '更新日時';
comment on column async_process_command_arg.created_at is '作成日時';
comment on column async_process_command_arg.lock_version is 'ロックバージョン';
comment on column async_process_command_arg.deleted_flg is '削除フラグ';

comment on table async_process_command is '非同期実行状況管理・コマンド';
comment on column async_process_command.id is 'ID';
comment on column async_process_command.async_id is '非同期実行状況管理ID';
comment on column async_process_command.command is 'コマンド';
comment on column async_process_command.updated_at is '更新日時';
comment on column async_process_command.created_at is '作成日時';
comment on column async_process_command.lock_version is 'ロックバージョン';
comment on column async_process_command.deleted_flg is '削除フラグ';

comment on table async_process_file_result_detail is '非同期実行状況管理・ファイル処理・結果詳細';
comment on column async_process_file_result_detail.id is 'ID';
comment on column async_process_file_result_detail.async_id is '非同期実行状況管理ID';
comment on column async_process_file_result_detail.record_number is 'NGレコード番号';
comment on column async_process_file_result_detail.description is '付加情報';
comment on column async_process_file_result_detail.updated_at is '更新日時';
comment on column async_process_file_result_detail.created_at is '作成日時';
comment on column async_process_file_result_detail.lock_version is 'ロックバージョン';
comment on column async_process_file_result_detail.deleted_flg is '削除フラグ';

comment on table async_process_file_result is '非同期実行状況管理・ファイル処理・結果';
comment on column async_process_file_result.id is 'ID';
comment on column async_process_file_result.async_id is '非同期実行状況管理ID';
comment on column async_process_file_result.total_count is '総件数';
comment on column async_process_file_result.ok_count is 'OK件数';
comment on column async_process_file_result.ng_count is 'NG件数';
comment on column async_process_file_result.updated_at is '更新日時';
comment on column async_process_file_result.created_at is '作成日時';
comment on column async_process_file_result.lock_version is 'ロックバージョン';
comment on column async_process_file_result.deleted_flg is '削除フラグ';

comment on table async_process_file_arg is '非同期実行状況管理・ファイル処理・引数';
comment on column async_process_file_arg.id is 'ID';
comment on column async_process_file_arg.async_id is '非同期実行状況管理ID';
comment on column async_process_file_arg.argument is '引数';
comment on column async_process_file_arg.updated_at is '更新日時';
comment on column async_process_file_arg.created_at is '作成日時';
comment on column async_process_file_arg.lock_version is 'ロックバージョン';
comment on column async_process_file_arg.deleted_flg is '削除フラグ';

comment on table async_process_file is '非同期実行状況管理・ファイル処理';
comment on column async_process_file.id is 'ID';
comment on column async_process_file.async_id is '非同期実行状況管理ID';
comment on column async_process_file.handler_name is 'ハンドラ名';
comment on column async_process_file.param_name is 'パラメタ名';
comment on column async_process_file.original_filename is '元ファイル名';
comment on column async_process_file.content_type is 'コンテントタイプ';
comment on column async_process_file.file_size is 'ファイルサイズ';
comment on column async_process_file.updated_at is '更新日時';
comment on column async_process_file.created_at is '作成日時';
comment on column async_process_file.lock_version is 'ロックバージョン';
comment on column async_process_file.deleted_flg is '削除フラグ';

comment on table async_process is '非同期実行状況管理';
comment on column async_process.id is 'ID';
comment on column async_process.launched_by is 'キュー投入者ID';
comment on column async_process.description is '内容表記';
comment on column async_process.async_type is '非同期実行種別';
comment on column async_process.async_status is '非同期実行状況';
comment on column async_process.registered_at is '登録日時';
comment on column async_process.launched_at is 'キュー投入日時';
comment on column async_process.started_at is '実行開始日時';
comment on column async_process.finished_at is '実行終了日時';
comment on column async_process.updated_at is '更新日時';
comment on column async_process.created_at is '作成日時';
comment on column async_process.lock_version is 'ロックバージョン';
comment on column async_process.deleted_flg is '削除フラグ';
