INSERT INTO sqltool_metadata (
	sql_type,
	name,
	description,
	owned_by,
	published_flg
)
VALUES (
	'clause',
	'QueryAsyncStatus',
	'QUERY ASYNC STATUS',
	'administrator',
	1
);

INSERT INTO sqltool_clause (
	id,
	database_name,
	select_clause,
	from_clause,
	where_clause,
	order_by_clause
)
VALUES (
	(SELECT id FROM sqltool_metadata WHERE name = 'QueryAsyncStatus'),
	'default',
	'*',
	'async_procs',
	'deleted_flg = 0',
	'id DESC'
)
;
