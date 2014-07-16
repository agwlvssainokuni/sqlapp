-- NAME: findById
SELECT
	id,
	database_name,
	query,
	updated_at,
	created_at,
	lock_version,
	deleted_flg
FROM
	sqltool_load
WHERE
	id = :id
	AND
	deleted_flg = 0
;

-- NAME: create
INSERT INTO sqltool_load (
	id,
	database_name,
	query
)
VALUES (
	:id,
	:databaseName,
	:query
)
;

-- NAME: update
UPDATE sqltool_load
SET
	database_name = :databaseName,
	query = :query,
	updated_at = CURRENT_TIMESTAMP,
	lock_version = lock_version + 1
WHERE
	id = :id
	AND
	lock_version = :lockVersion
	AND
	deleted_flg = 0
;
