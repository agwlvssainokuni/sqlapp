-- NAME: findById
SELECT
	id,
	database_name,
	select_clause,
	from_clause,
	where_clause,
	group_by_clause,
	having_clause,
	order_by_clause,
	param_map,
	updated_at,
	created_at,
	lock_version,
	deleted_flg
FROM
	sqltool_clause
WHERE
	id = :id
	AND
	deleted_flg = 0
;

-- NAME: create
INSERT INTO sqltool_clause (
	id,
	database_name,
	select_clause,
	from_clause,
	where_clause,
	group_by_clause,
	having_clause,
	order_by_clause,
	param_map
)
VALUES (
	:id,
	:databaseName,
	:selectClause,
	:fromClause,
	:whereClause,
	:groupByClause,
	:havingClause,
	:orderByClause,
	:paramMap
)
;

-- NAME: update
UPDATE sqltool_clause
SET
	database_name = :databaseName,
	select_clause = :selectClause,
	from_clause = :fromClause,
	where_clause = :whereClause,
	group_by_clause = :groupByClause,
	having_clause = :havingClause,
	order_by_clause = :orderByClause,
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
