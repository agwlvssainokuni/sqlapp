-- Project Name : SqlApp
-- Date/Time    : 2014/05/30 21:50:00
-- Author       : agwlvssainokuni
-- RDBMS Type   : IBM DB2
-- Application  : A5:SQL Mk-2

-- SQL文・SELECT
CREATE TABLE sql_select( 
	id INTEGER DEFAULT auto_increment NOT NULL, 
	select_clause VARCHAR (500) NOT NULL, 
	from_clause VARCHAR (500) NOT NULL, 
	where_clause VARCHAR (500), 
	group_by_clause VARCHAR (500), 
	having_clause VARCHAR (500), 
	order_by_clause VARCHAR (500), 
	updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL, 
	created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL, 
	deleted_flg INTEGER DEFAULT 0 NOT NULL, 
	CONSTRAINT sql_select_pkc PRIMARY KEY (id)
); 

-- SQLメタデータ
CREATE TABLE sql_metadata( 
	id INTEGER DEFAULT auto_increment NOT NULL, 
	sql_type VARCHAR (32) NOT NULL, 
	name VARCHAR (50) NOT NULL, 
	description VARCHAR (500) NOT NULL, 
	updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL, 
	created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL, 
	deleted_flg INTEGER DEFAULT 0 NOT NULL, 
	CONSTRAINT sql_metadata_pkc PRIMARY KEY (id)
); 

COMMENT 
	ON TABLE sql_select IS 'SQL文・SELECT'; 

COMMENT 
	ON COLUMN sql_select.id IS 'ID'; 

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
	ON COLUMN sql_metadata.updated_at IS '更新日時'; 

COMMENT 
	ON COLUMN sql_metadata.created_at IS '作成日時'; 

COMMENT 
	ON COLUMN sql_metadata.deleted_flg IS '削除フラグ'; 


