-- NAME: findTemplate
SELECT
	A.id        AS id,
	A.name      AS name,
	A.sender    AS sender,
	B.locale    AS locale,
	B.subject   AS subject,
	B.body      AS body
FROM
	mail_template AS A
	JOIN mail_template_text AS B
	ON
		B.mail_template_id = A.id
		AND
		B.locale = :locale
		AND
		B.deleted_flg = 0
WHERE
	A.name = :name
	AND
	A.deleted_flg = 0
;

-- NAME: findAddresses
SELECT
	C.id        AS id,
	C.rcpt_type AS rcpt_type,
	C.mail_addr AS mail_addr
FROM
	mail_template AS A
	LEFT OUTER JOIN mail_template_address AS C
	ON
		C.mail_template_id = A.id
		AND
		C.deleted_flg = 0
WHERE
	A.name = :name
	AND
	A.deleted_flg = 0
ORDER BY
	C.id
;
