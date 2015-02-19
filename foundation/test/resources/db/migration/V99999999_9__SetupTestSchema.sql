CREATE TABLE etl_executor_test (
	id		INTEGER		NOT NULL	AUTO_INCREMENT,
	login	VARCHAR(32)	NOT NULL,
	passwd	VARCHAR(64)	NOT NULL,
	name	VARCHAR(255),
	del_flg	INTEGER		DEFAULT 0,
	CONSTRAINT etl_executor_test_pkcs PRIMARY KEY (id)
);

CREATE TABLE etl_extr_ldr_test (
	id		INTEGER		NOT NULL	AUTO_INCREMENT,
	name	VARCHAR(32)	NOT NULL,
	address	VARCHAR(64)	NOT NULL,
	CONSTRAINT etl_extr_ldr_test_pkcs PRIMARY KEY (id)
);

CREATE TABLE conversion_test (
	id		INTEGER		NOT NULL	AUTO_INCREMENT,
	joda_date		DATE,
	joda_time		TIME,
	joda_datetime	TIMESTAMP,
	sec_str		VARCHAR(104),
	sec_int		VARCHAR(40),
	sec_long	VARCHAR(40),
	sec_bigint	VARCHAR(104),
	sec_bigdec	VARCHAR(104),
	flag_code	INTEGER,
	deleted_flg	INTEGER
);
