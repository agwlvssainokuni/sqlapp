UPDATE async_procs
SET
	status = 'SUCCESS',
	finished_at = CURRENT_TIMESTAMP,
	result = :result,
	updated_at = CURRENT_TIMESTAMP
WHERE
	id = :id
	AND
	deleted_flg = 0
