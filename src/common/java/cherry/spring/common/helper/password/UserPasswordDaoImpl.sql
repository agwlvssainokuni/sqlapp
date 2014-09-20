-- NAME: updatePassword
UPDATE user
SET
	password = :password,
	updated_at = CURRENT_TIMESTAMP,
	lock_version = lock_version + 1
WHERE
	id = :id
	AND
	deleted_flg = 0
;

-- NAME: changePassword
UPDATE user
SET
	password = :password,
	updated_at = CURRENT_TIMESTAMP,
	lock_version = lock_version + 1
WHERE
	login_id = :loginId
	AND
	deleted_flg = 0
;
