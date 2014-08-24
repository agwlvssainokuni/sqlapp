-- Project Name : SpringApp
-- Date/Time    : 2014/08/24 20:14:14
-- Author       : agwlvssainokuni
-- RDBMS Type   : IBM DB2
-- Application  : A5:SQL Mk-2

-- 非同期処理
CREATE TABLE async_proc(
	id INTEGER NOT NULL auto_increment,
	launcher_id VARCHAR (512) NOT NULL,
	name VARCHAR (32) NOT NULL,
	status VARCHAR (32) NOT NULL CHECK status IN (
		'PREPARING',
		'INVOKED',
		'PROCESSING',
		'SUCCESS',
		'ERROR'
	) ,
	registered_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
	invoked_at TIMESTAMP,
	started_at TIMESTAMP,
	finished_at TIMESTAMP,
	RESULT VARCHAR (4096),
	updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
	created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
	lock_version INTEGER DEFAULT 1 NOT NULL,
	deleted_flg INTEGER DEFAULT 0 NOT NULL,
	CONSTRAINT async_proc_pkc PRIMARY KEY (id)
);

CREATE INDEX async_proc_ix1
	ON async_proc(launcher_id);

COMMENT
	ON TABLE async_proc IS '非同期処理';

COMMENT
	ON COLUMN async_proc.id IS 'ID';

COMMENT
	ON COLUMN async_proc.launcher_id IS '起動者ID';

COMMENT
	ON COLUMN async_proc.name IS '処理名称';

COMMENT
	ON COLUMN async_proc.status IS '状況';

COMMENT
	ON COLUMN async_proc.registered_at IS '登録日時';

COMMENT
	ON COLUMN async_proc.invoked_at IS '投入日時';

COMMENT
	ON COLUMN async_proc.started_at IS '開始日時';

COMMENT
	ON COLUMN async_proc.finished_at IS '終了日時';

COMMENT
	ON COLUMN async_proc.RESULT IS '結果情報';

COMMENT
	ON COLUMN async_proc.updated_at IS '更新日時';

COMMENT
	ON COLUMN async_proc.created_at IS '作成日時';

COMMENT
	ON COLUMN async_proc.lock_version IS 'ロックバージョン';

COMMENT
	ON COLUMN async_proc.deleted_flg IS '削除フラグ';


