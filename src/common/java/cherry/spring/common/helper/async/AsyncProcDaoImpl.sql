-- NAME: createAsyncProc
INSERT INTO async_proc (
	name,
	launcher_id,
	status,
	registered_at
)
VALUES (
	:name,
	:launcherId,
	'PREPARING',
	CURRENT_TIMESTAMP
)
;

-- NAME: invokeAsyncProc
UPDATE async_proc
SET
	status = 'INVOKED',
	invoked_at = CURRENT_TIMESTAMP,
	updated_at = CURRENT_TIMESTAMP,
	lock_version = lock_version + 1
WHERE
	id = :id
	AND
	deleted_flg = 0
;

-- NAME: startAsyncProc
UPDATE async_proc
SET
	status = 'PROCESSING',
	started_at = CURRENT_TIMESTAMP,
	updated_at = CURRENT_TIMESTAMP,
	lock_version = lock_version + 1
WHERE
	id = :id
	AND
	deleted_flg = 0
;

-- NAME: successAsyncProc
UPDATE async_proc
SET
	status = 'SUCCESS',
	finished_at = CURRENT_TIMESTAMP,
	result = :result,
	updated_at = CURRENT_TIMESTAMP,
	lock_version = lock_version + 1
WHERE
	id = :id
	AND
	deleted_flg = 0
;

-- NAME: errorAsyncProc
UPDATE async_proc
SET
	status = 'ERROR',
	finished_at = CURRENT_TIMESTAMP,
	result = :result,
	updated_at = CURRENT_TIMESTAMP,
	lock_version = lock_version + 1
WHERE
	id = :id
	AND
	deleted_flg = 0
;
