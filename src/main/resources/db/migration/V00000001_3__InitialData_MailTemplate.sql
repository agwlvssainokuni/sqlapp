INSERT INTO mail_template (
	template_name,
	from_addr,
	subject,
	body
)
VALUES
(
	'SIGNUP_ENTRY',
	'noreply@test.com',
	'サインアップエントリ',
	'${model.mailAddr}, サインアップURI: ${model.signupUri}'
),
(
	'SIGNUP_REGISTER',
	'noreply@test.com',
	'サインアップ登録',
	'${model.mailAddr}, パスワード: ${model.password}'
)
;

INSERT INTO mail_template_address (
	template_id,
	rcpt_type,
	rcpt_addr
)
VALUES
(
	(SELECT id FROM mail_template WHERE template_name = 'SIGNUP_ENTRY'),
	'CC',
	'cc@test.com'
),
(
	(SELECT id FROM mail_template WHERE template_name = 'SIGNUP_ENTRY'),
	'BCC',
	'bcc@test.com'
),
(
	(SELECT id FROM mail_template WHERE template_name = 'SIGNUP_REGISTER'),
	'CC',
	'cc@test.com'
),
(
	(SELECT id FROM mail_template WHERE template_name = 'SIGNUP_REGISTER'),
	'BCC',
	'bcc@test.com'
)
;
