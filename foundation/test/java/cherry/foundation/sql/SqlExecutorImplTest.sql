-- 初期データ投入
INSERT INTO etl_executor_test (login, passwd, name) VALUES ('user1', 'user1', NULL);
INSERT INTO etl_executor_test (login, passwd, name) VALUES ('user2', 'user2', NULL);
INSERT INTO etl_executor_test (login, passwd, name) VALUES ('user3', 'user3', NULL);
INSERT INTO etl_executor_test (login, passwd, name) VALUES ('user4', 'user4', NULL);
INSERT INTO etl_executor_test (login, passwd, name) VALUES ('user5', 'user5', NULL);

-- データ更新
UPDATE etl_executor_test SET name = login;
