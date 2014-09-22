SqlApp - SQL実行機能
====================

##	概要

*	指定したDBに対してSQLを発行します。
*	発行できるSQLは下記の3種類です。
	*	CLAUSE: SELECT文を句ごとに記述する。
		*	SELECT, FROM, WHERE, GROUP BY, HAVING, ORDER BY を別々に指定する。
		*	ページネーションあり。
	*	STATEMENT: SELECT文ををまま記述する。
		*	ページネーションなし。
	*	LOAD: INSERT文、UPDATE文、DELETE文をそのまま記述する。
		*	SQL文に埋込むパラメタをCSVファイルで受け渡す (アップロード)。
		*	CSVファイルのタイトル行をcamelCaseに変換してパラメタ化。
		*	SQL文に「:paramName」の形式で記述しておく。
*	SQLをDBに保存しておくことができます。
	*	保存したSQLは条件を指定して検索することができます。
	*	保存したSQLは保存した利用者から参照＆実行することができます。
	*	また、「公開」に設定することで、他の利用者からも参照＆実行することができます。

##	セットアップ

*	SqlApp は Java EE Webアプリケーションです。
	*	開発にあたっては WildFly 8.1.0.Final を使っています。
*	動作には DBMS とメッセージキュー (JMS) が必要です。Java EE コンテナに下記の設定をしておいてください。
	*	DBMS
		*	SQL発行先 (default):	java:/datasources/AnotherDb
		*	SQL保存先 (sqlapp):	java:/datasources/SqlApp
	*	メッセージキュー
		*	LOAD非同期実行:	java:/jms/queue/SqlApp

##	ビルド

*	Gradle を使ってビルドします。
	*	コマンド:	gradle deliveryZip
	*	ビルド物件:	build/distributions/sqlapp-1.0-delivery.zip 
*	デプロイの手順は下記の通りです。
	*	/opt/sqlapp/ 配下へ展開。
		*	app/sqlapp.war
		*	conf/
			*	env.properties.template
			*	logback.properties.template
			*	user.properties.template
	*	conf/ 配下のファイルをそれぞれ *.properties (「.template」なし) にコピーする。
	*	app/sqlapp.war を Java EE コンテナのデプロイディレクトリに配置する。
	*	Java EE コンテナを起動する。
*	下記の要領でアクセスしてください。
	*	URL:	http://{ホスト名}:{ポート番号}/sqlapp/
	*	ユーザID/パスワード:	administrator/password
		*	conf/user.properties ファイルでアカウントを管理しています。
		*	パスワードは bcrypt 形式です。

以上。
