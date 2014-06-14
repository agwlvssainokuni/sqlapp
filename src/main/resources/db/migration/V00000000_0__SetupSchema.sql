-- Project Name : SqlApp
-- Date/Time    : 2014/06/15 6:13:32
-- Author       : agwlvssainokuni
-- RDBMS Type   : IBM DB2
-- Application  : A5:SQL Mk-2

-- SQL文・CSV取込み
CREATE TABLE sql_csv( 
	id INTEGER NOT NULL auto_increment, 
	database_name VARCHAR (50) DEFAULT 'default' NOT NULL, 
	query VARCHAR (5000) NOT NULL, 
	query_alt_1 VARCHAR (5000), 
	query_alt_2 VARCHAR (5000), 
	param_map VARCHAR (5000), 
	updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL, 
	created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL, 
	deleted_flg INTEGER DEFAULT 0 NOT NULL, 
	CONSTRAINT sql_csv_pkc PRIMARY KEY (id)
); 

-- SQL文・一般クエリ
CREATE TABLE sql_any( 
	id INTEGER NOT NULL auto_increment, 
	database_name VARCHAR (50) DEFAULT 'default' NOT NULL, 
	query VARCHAR (5000) NOT NULL, 
	param_map VARCHAR (5000), 
	updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL, 
	created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL, 
	deleted_flg INTEGER DEFAULT 0 NOT NULL, 
	CONSTRAINT sql_any_pkc PRIMARY KEY (id)
); 

-- SQL文・SELECT
CREATE TABLE sql_select( 
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
	deleted_flg INTEGER DEFAULT 0 NOT NULL, 
	CONSTRAINT sql_select_pkc PRIMARY KEY (id)
); 

-- SQLメタデータ
CREATE TABLE sql_metadata( 
	id INTEGER NOT NULL auto_increment, 
	sql_type VARCHAR (32) NOT NULL, 
	name VARCHAR (50) DEFAULT CURRENT_TIMESTAMP NOT NULL, 
	description VARCHAR (500) DEFAULT CURRENT_TIMESTAMP NOT NULL, 
	owned_by VARCHAR (32) NOT NULL, 
	published_flg INTEGER DEFAULT 0 NOT NULL, 
	registered_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL, 
	updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL, 
	created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL, 
	deleted_flg INTEGER DEFAULT 0 NOT NULL, 
	CONSTRAINT sql_metadata_pkc PRIMARY KEY (id)
); 

COMMENT 
	ON TABLE sql_csv IS 'SQL文・CSV取込み'; 

COMMENT 
	ON COLUMN sql_csv.id IS 'ID'; 

COMMENT 
	ON COLUMN sql_csv.database_name IS 'DB名'; 

COMMENT 
	ON COLUMN sql_csv.query IS 'クエリ'; 

COMMENT 
	ON COLUMN sql_csv.query_alt_1 IS '代替クエリ1'; 

COMMENT 
	ON COLUMN sql_csv.query_alt_2 IS '代替クエリ2'; 

COMMENT 
	ON COLUMN sql_csv.param_map IS 'パラメタMAP(JSON)'; 

COMMENT 
	ON COLUMN sql_csv.updated_at IS '更新日時'; 

COMMENT 
	ON COLUMN sql_csv.created_at IS '作成日時'; 

COMMENT 
	ON COLUMN sql_csv.deleted_flg IS '削除フラグ'; 

COMMENT 
	ON TABLE sql_any IS 'SQL文・一般クエリ'; 

COMMENT 
	ON COLUMN sql_any.id IS 'ID'; 

COMMENT 
	ON COLUMN sql_any.database_name IS 'DB名'; 

COMMENT 
	ON COLUMN sql_any.query IS 'クエリ'; 

COMMENT 
	ON COLUMN sql_any.param_map IS 'パラメタMAP(JSON)'; 

COMMENT 
	ON COLUMN sql_any.updated_at IS '更新日時'; 

COMMENT 
	ON COLUMN sql_any.created_at IS '作成日時'; 

COMMENT 
	ON COLUMN sql_any.deleted_flg IS '削除フラグ'; 

COMMENT 
	ON TABLE sql_select IS 'SQL文・SELECT'; 

COMMENT 
	ON COLUMN sql_select.id IS 'ID'; 

COMMENT 
	ON COLUMN sql_select.database_name IS 'DB名'; 

COMMENT 
	ON COLUMN sql_select.select_clause IS 'SELECT句'; 

COMMENT 
	ON COLUMN sql_select.from_clause IS 'FROM句'; 

COMMENT 
	ON COLUMN sql_select.where_clause IS 'WHERE句'; 

COMMENT 
	ON COLUMN sql_select.group_by_clause IS 'GROUP BY句'; 

COMMENT 
	ON COLUMN sql_select.having_clause IS 'HAVING句'; 

COMMENT 
	ON COLUMN sql_select.order_by_clause IS 'ORDER BY句'; 

COMMENT 
	ON COLUMN sql_select.param_map IS 'パラメタMAP(JSON)'; 

COMMENT 
	ON COLUMN sql_select.updated_at IS '更新日時'; 

COMMENT 
	ON COLUMN sql_select.created_at IS '作成日時'; 

COMMENT 
	ON COLUMN sql_select.deleted_flg IS '削除フラグ'; 

COMMENT 
	ON TABLE sql_metadata IS 'SQLメタデータ'; 

COMMENT 
	ON COLUMN sql_metadata.id IS 'ID'; 

COMMENT 
	ON COLUMN sql_metadata.sql_type IS 'SQL種別'; 

COMMENT 
	ON COLUMN sql_metadata.name IS '表示名'; 

COMMENT 
	ON COLUMN sql_metadata.description IS '説明文'; 

COMMENT 
	ON COLUMN sql_metadata.owned_by IS '所有者'; 

COMMENT 
	ON COLUMN sql_metadata.published_flg IS '公開フラグ'; 

COMMENT 
	ON COLUMN sql_metadata.registered_at IS '登録日時'; 

COMMENT 
	ON COLUMN sql_metadata.updated_at IS '更新日時'; 

COMMENT 
	ON COLUMN sql_metadata.created_at IS '作成日時'; 

COMMENT 
	ON COLUMN sql_metadata.deleted_flg IS '削除フラグ'; 


