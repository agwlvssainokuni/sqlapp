-- NAME: createSignupRequest
INSERT INTO signup_request (
	mail_addr,
	token,
	applied_at
)
VALUES (
	:mailAddr,
	:token,
	:appliedAt
)
;

-- NAME: validateMailAddr
SELECT
	CASE
		WHEN COUNT(*) = 0 THEN 1
		WHEN COUNT(*) >= :numOfReq THEN 0
		WHEN MAX(A.applied_at) >= :intervalFrom THEN 0
		ELSE 1
	END
FROM
	signup_request AS A
WHERE
	A.mail_addr = :mailAddr
	AND
	A.applied_at >= :rangeFrom
	AND
	A.deleted_flg = 0
;

-- NAME: validateToken
SELECT
	COUNT(*)
FROM
	signup_request AS A
WHERE
	A.mail_addr = :mailAddr
	AND
	A.token = :token
	AND
	A.applied_at >= :validFrom
	AND
	A.deleted_flg = 0
	AND
	NOT EXISTS (
		SELECT * FROM signup_request AS B
		WHERE
			B.mail_addr = A.mail_addr
			AND
			B.applied_at > A.applied_at
			AND
			B.deleted_flg = 0
	)
;
