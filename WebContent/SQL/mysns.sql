CREATE DATABASE IF NOT EXISTS mysns
DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;

USE mysns;

CREATE TABLE IF NOT EXISTS user(
	id varchar(128) PRIMARY KEY ,
	jsonstr VARCHAR(1024)
    
);

CREATE TABLE IF NOT EXISTS feed (
	no INT UNSIGNED PRIMARY KEY,
	id VARCHAR(128),
	jsonstr VARCHAR(8192)
	);

create table if not exists feedoption(
 	list INT UNSIGNED PRIMARY KEY,
 	jsonstr VARCHAR(1024)
 );
 

 
 create table if not exists add_uni(
 	list INT UNSIGNED PRIMARY KEY,
 	jsonstr VARCHAR(1024)
 );
