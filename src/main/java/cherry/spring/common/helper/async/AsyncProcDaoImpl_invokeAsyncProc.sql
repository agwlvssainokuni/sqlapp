UPDATE async_procs
SET
	status = 'INVOKED',
	invoked_at = CURRENT_TIMESTAMP,
	updated_at = CURRENT_TIMESTAMP
WHERE
	id = :id
	AND
	deleted_flg = 0
