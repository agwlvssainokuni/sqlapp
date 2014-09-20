-- Project Name : SqlApp
-- Date/Time    : 2014/06/28 6:37:37
-- Author       : agwlvssainokuni
-- RDBMS Type   : IBM DB2
-- Application  : A5:SQL Mk-2

-- SQLツール・CSV取込み
CREATE TABLE sqltool_load( 
	id INTEGER NOT NULL auto_increment, 
	database_name VARCHAR (50) DEFAULT 'default' NOT NULL, 
	query VARCHAR (5000) NOT NULL, 
	updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL, 
	created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL, 
	lock_version INTEGER DEFAULT 1 NOT NULL, 
	deleted_flg INTEGER DEFAULT 0 NOT NULL, 
	CONSTRAINT sqltool_load_pkc PRIMARY KEY (id)
); 

-- SQLツール・ステートメント指定
CREATE TABLE sqltool_statement( 
	id INTEGER NOT NULL auto_increment, 
	database_name VARCHAR (50) DEFAULT 'default' NOT NULL, 
	query VARCHAR (5000) NOT NULL, 
	param_map VARCHAR (5000), 
	updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL, 
	created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL, 
	lock_version INTEGER DEFAULT 1 NOT NULL, 
	deleted_flg INTEGER DEFAULT 0 NOT NULL, 
	CONSTRAINT sqltool_statement_pkc PRIMARY KEY (id)
); 

-- SQLツール・句指定
CREATE TABLE sqltool_clause( 
	id INTEGER NOT NULL auto_increment, 
	database_name VARCHAR (50) DEFAULT 'default' NOT NULL, 
	select_clause VARCHAR (500) NOT NULL, 
	from_clause VARCHAR (500) NOT NULL, 
	where_clause VARCHAR (500), 
	group_by_clause VARCHAR (500), 
	having_clause VARCHAR (500), 
	order_by_clause VARCHAR (500), 
	param_map VARCHAR (5000), 
	updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL, 
	created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL, 
	lock_version INTEGER DEFAULT 1 NOT NULL, 
	deleted_flg INTEGER DEFAULT 0 NOT NULL, 
	CONSTRAINT sqltool_clause_pkc PRIMARY KEY (id)
); 

-- SQLツール・メタデータ
CREATE TABLE sqltool_metadata( 
	id INTEGER NOT NULL auto_increment, 
	sql_type VARCHAR (32) NOT NULL, 
	name VARCHAR (50) DEFAULT CURRENT_TIMESTAMP NOT NULL, 
	description VARCHAR (500) DEFAULT CURRENT_TIMESTAMP NOT NULL, 
	owned_by VARCHAR (32) NOT NULL, 
	published_flg INTEGER DEFAULT 0 NOT NULL, 
	registered_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL, 
	updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL, 
	created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL, 
	lock_version INTEGER DEFAULT 1 NOT NULL, 
	deleted_flg INTEGER DEFAULT 0 NOT NULL, 
	CONSTRAINT sqltool_metadata_pkc PRIMARY KEY (id)
); 

COMMENT 
	ON TABLE sqltool_load IS 'SQLツール・CSV取込み'; 

COMMENT 
	ON COLUMN sqltool_load.id IS 'ID'; 

COMMENT 
	ON COLUMN sqltool_load.database_name IS 'DB名'; 

COMMENT 
	ON COLUMN sqltool_load.query IS 'クエリ'; 

COMMENT 
	ON COLUMN sqltool_load.updated_at IS '更新日時'; 

COMMENT 
	ON COLUMN sqltool_load.created_at IS '作成日時'; 

COMMENT 
	ON COLUMN sqltool_load.lock_version IS 'ロックバージョン'; 

COMMENT 
	ON COLUMN sqltool_load.deleted_flg IS '削除フラグ'; 

COMMENT 
	ON TABLE sqltool_statement IS 'SQLツール・ステートメント指定'; 

COMMENT 
	ON COLUMN sqltool_statement.id IS 'ID'; 

COMMENT 
	ON COLUMN sqltool_statement.database_name IS 'DB名'; 

COMMENT 
	ON COLUMN sqltool_statement.query IS 'クエリ'; 

COMMENT 
	ON COLUMN sqltool_statement.param_map IS 'パラメタMAP(JSON)'; 

COMMENT 
	ON COLUMN sqltool_statement.updated_at IS '更新日時'; 

COMMENT 
	ON COLUMN sqltool_statement.created_at IS '作成日時'; 

COMMENT 
	ON COLUMN sqltool_statement.lock_version IS 'ロックバージョン'; 

COMMENT 
	ON COLUMN sqltool_statement.deleted_flg IS '削除フラグ'; 

COMMENT 
	ON TABLE sqltool_clause IS 'SQLツール・句指定'; 

COMMENT 
	ON COLUMN sqltool_clause.id IS 'ID'; 

COMMENT 
	ON COLUMN sqltool_clause.database_name IS 'DB名'; 

COMMENT 
	ON COLUMN sqltool_clause.select_clause IS 'SELECT句'; 

COMMENT 
	ON COLUMN sqltool_clause.from_clause IS 'FROM句'; 

COMMENT 
	ON COLUMN sqltool_clause.where_clause IS 'WHERE句'; 

COMMENT 
	ON COLUMN sqltool_clause.group_by_clause IS 'GROUP BY句'; 

COMMENT 
	ON COLUMN sqltool_clause.having_clause IS 'HAVING句'; 

COMMENT 
	ON COLUMN sqltool_clause.order_by_clause IS 'ORDER BY句'; 

COMMENT 
	ON COLUMN sqltool_clause.param_map IS 'パラメタMAP(JSON)'; 

COMMENT 
	ON COLUMN sqltool_clause.updated_at IS '更新日時'; 

COMMENT 
	ON COLUMN sqltool_clause.created_at IS '作成日時'; 

COMMENT 
	ON COLUMN sqltool_clause.lock_version IS 'ロックバージョン'; 

COMMENT 
	ON COLUMN sqltool_clause.deleted_flg IS '削除フラグ'; 

COMMENT 
	ON TABLE sqltool_metadata IS 'SQLツール・メタデータ'; 

COMMENT 
	ON COLUMN sqltool_metadata.id IS 'ID'; 

COMMENT 
	ON COLUMN sqltool_metadata.sql_type IS 'SQL種別'; 

COMMENT 
	ON COLUMN sqltool_metadata.name IS '表示名'; 

COMMENT 
	ON COLUMN sqltool_metadata.description IS '説明文'; 

COMMENT 
	ON COLUMN sqltool_metadata.owned_by IS '所有者'; 

COMMENT 
	ON COLUMN sqltool_metadata.published_flg IS '公開フラグ'; 

COMMENT 
	ON COLUMN sqltool_metadata.registered_at IS '登録日時'; 

COMMENT 
	ON COLUMN sqltool_metadata.updated_at IS '更新日時'; 

COMMENT 
	ON COLUMN sqltool_metadata.created_at IS '作成日時'; 

COMMENT 
	ON COLUMN sqltool_metadata.lock_version IS 'ロックバージョン'; 

COMMENT 
	ON COLUMN sqltool_metadata.deleted_flg IS '削除フラグ'; 


