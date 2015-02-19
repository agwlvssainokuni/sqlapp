-- NAME: selectAll
SELECT
	id,
	joda_date,
	joda_time,
	joda_datetime,
	sec_str,
	sec_int,
	sec_long,
	sec_bigint,
	sec_bigdec,
	flag_code,
	deleted_flg
FROM
	conversion_test
;

-- NAME: insert
INSERT INTO conversion_test (
	joda_date,
	joda_time,
	joda_datetime,
	sec_str,
	sec_int,
	sec_long,
	sec_bigint,
	sec_bigdec,
	flag_code,
	deleted_flg
) VALUES (
	:jodaDate,
	:jodaTime,
	:jodaDatetime,
	:secStr,
	:secInt,
	:secLong,
	:secBigint,
	:secBigdec,
	:flagCode,
	:deletedFlg
)
;
