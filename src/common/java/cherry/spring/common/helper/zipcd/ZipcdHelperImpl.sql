-- NAME: queryByZipcd
SELECT
	A.city_cd,
	A.zipcd,
	A.pref,
	A.city,
	A.addr,
	A.pref_kana,
	A.city_kana,
	A.addr_kana
FROM
	zipcd_master AS A
WHERE
	A.zipcd = :zipcd
	AND
	A.deleted_flg = 0
ORDER BY
	A.id
;
