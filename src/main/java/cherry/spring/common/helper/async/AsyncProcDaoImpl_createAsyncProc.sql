INSERT INTO async_procs (
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
