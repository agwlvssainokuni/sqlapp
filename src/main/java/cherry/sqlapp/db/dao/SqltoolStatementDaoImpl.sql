-- NAME: findById
SELECT
	id,
	database_name,
	query,
	param_map,
	updated_at,
	created_at,
	lock_version,
	deleted_flg
FROM
	sqltool_statement
WHERE
	id = :id
	AND
	deleted_flg = 0
;

-- NAME: create
INSERT INTO sqltool_statement (
	id,
	database_name,
	query,
	param_map
)
VALUES (
	:id,
	:databaseName,
	:query,
	:paramMap
)
;

-- NAME: update
UPDATE sqltool_statement
SET
	database_name = :databaseName,
	query = :query,
	param_map = :paramMap,
	updated_at = CURRENT_TIMESTAMP,
	lock_version = lock_version + 1
WHERE
	id = :id
	AND
	lock_version = :lockVersion
	AND
	deleted_flg = 0
;
