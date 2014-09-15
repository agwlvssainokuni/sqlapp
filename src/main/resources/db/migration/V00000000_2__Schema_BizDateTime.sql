-- Project Name : SpringApp
-- Date/Time    : 2014/09/15 10:36:38
-- Author       : agwlvssainokuni
-- RDBMS Type   : IBM DB2
-- Application  : A5:SQL Mk-2

-- 業務日時マスタ
CREATE TABLE bizdatetime_master(
	id INTEGER NOT NULL auto_increment,
	bizdate DATE DEFAULT CURRENT_DATE NOT NULL,
	offset_day INTEGER DEFAULT 0 NOT NULL,
	offset_hour INTEGER DEFAULT 0 NOT NULL,
	offset_minute INTEGER DEFAULT 0 NOT NULL,
	offset_second INTEGER DEFAULT 0 NOT NULL,
	updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
	created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
	lock_version INTEGER DEFAULT 1 NOT NULL,
	deleted_flg INTEGER DEFAULT 0 NOT NULL,
	CONSTRAINT bizdatetime_master_pkc PRIMARY KEY (id)
);

COMMENT
	ON TABLE bizdatetime_master IS '業務日時マスタ';

COMMENT
	ON COLUMN bizdatetime_master.id IS 'ID';

COMMENT
	ON COLUMN bizdatetime_master.bizdate IS '業務日付';

COMMENT
	ON COLUMN bizdatetime_master.offset_day IS 'オフセット(日)';

COMMENT
	ON COLUMN bizdatetime_master.offset_hour IS 'オフセット(時)';

COMMENT
	ON COLUMN bizdatetime_master.offset_minute IS 'オフセット(分)';

COMMENT
	ON COLUMN bizdatetime_master.offset_second IS 'オフセット(秒)';

COMMENT
	ON COLUMN bizdatetime_master.updated_at IS '更新日時';

COMMENT
	ON COLUMN bizdatetime_master.created_at IS '作成日時';

COMMENT
	ON COLUMN bizdatetime_master.lock_version IS 'ロックバージョン';

COMMENT
	ON COLUMN bizdatetime_master.deleted_flg IS '削除フラグ';


