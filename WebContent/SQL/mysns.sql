CREATE DATABASE IF NOT EXISTS mysns
DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;

USE mysns;

CREATE TABLE IF NOT EXISTS user(
	id varchar(128) PRIMARY KEY ,
	name varchar(128),
	stu_num varchar(128) , 
    birth varchar(32) ,
    phone_num varchar(32),
    ps VARCHAR(32)
      
);

CREATE TABLE IF NOT EXISTS feed(
    no INT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
    id VARCHAR(128), -- "email"
    content VARCHAR(4096),
    images VARCHAR(1024), -- "url"
    ts TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

		CREATE TABLE IF NOT EXISTS feed(
		no INT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
		id VARCHAR(128), -- "email"
		content VARCHAR(4096),
		ts TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);