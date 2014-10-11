-- NAME: findBizdate
SELECT
	id,
	bizdate,
	offset_day,
	offset_hour,
	offset_minute,
	offset_second,
	CURRENT_TIMESTAMP AS current_date_time
FROM
	bizdatetime_master
WHERE
	deleted_flg = 0;
ORDER BY
	id
LIMIT 1 OFFSET 0
;
