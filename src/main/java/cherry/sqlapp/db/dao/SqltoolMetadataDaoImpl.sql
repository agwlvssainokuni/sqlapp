-- NAME: findById
SELECT
	id,
	sql_type,
	name,
	description,
	owned_by,
	updated_at,
	created_at,
	lock_version,
	deleted_flg
FROM
	sqltool_metadata
WHERE
	id = :id
	AND
	deleted_flg = 0
	AND
	(
		published_flg <> 0
		OR
		owned_by = :loginId
	)
;

-- NAME: create
INSERT INTO sqltool_metadata (
	sql_type,
	name,
	description,
	owned_by
)
VALUES (
	:sqlType,
	DEFAULT,
	:description,
	:ownedBy
)
;

-- NAME: update
UPDATE sqltool_metadata
SET
	name = :name,
	description = :description,
	published_flg = :publishedFlg,
	updated_at = CURRENT_TIMESTAMP,
	lock_version = lock_version + 1
WHERE
	id = :id
	AND
	lock_version = :lockVersion
	AND
	deleted_flg = 0
;
