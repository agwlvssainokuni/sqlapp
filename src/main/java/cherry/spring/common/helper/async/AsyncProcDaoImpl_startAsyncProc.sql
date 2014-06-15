UPDATE async_procs
SET
	status = 'PROCESSING',
	started_at = CURRENT_TIMESTAMP,
	updated_at = CURRENT_TIMESTAMP
WHERE
	id = :id
	AND
	deleted_flg = 0
