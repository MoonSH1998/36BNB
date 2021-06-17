CREATE DATABASE IF NOT EXISTS mysns
DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;

USE mysns;

CREATE TABLE IF NOT EXISTS user(
	id varchar(128) PRIMARY KEY ,
	-- name varchar(128),
	-- stu_num varchar(128) ,
    -- birth varchar(32) ,
    -- phone_num varchar(32),
    -- ps VARCHAR(32)
    jsonstr VARCHAR(1024)

);

CREATE TABLE IF NOT EXISTS feed (
	no INT UNSIGNED PRIMARY KEY,
	id VARCHAR(128),
	-- content VARCHAR(4096),
	-- images VARCHAR(1024),
	-- ts TIMESTAMP DEFAULT CURRENT_TIMESTAMP
	jsonstr VARCHAR(8192)
	);

create table if not exists feedoption(
 	list INT UNSIGNED PRIMARY KEY,
	id varchar(128),
 jsonstr VARCHAR(1024)
 );
 