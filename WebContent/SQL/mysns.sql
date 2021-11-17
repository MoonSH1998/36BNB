CREATE DATABASE IF NOT EXISTS mysns
DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;

USE mysns;

/*user테이블*/
CREATE TABLE IF NOT EXISTS user(
	id varchar(128) PRIMARY KEY ,
	jsonstr VARCHAR(1024)
    
);
/*feed테이블*/
CREATE TABLE IF NOT EXISTS feed (
	no INT UNSIGNED PRIMARY KEY,
	id VARCHAR(128),
	uni VARCHAR(128),
	jsonstr VARCHAR(8192)
	);
/*feed heart,repoert 저장되는 테이블*/
create table if not exists feedoption(
 	list INT UNSIGNED PRIMARY KEY,
 	jsonstr VARCHAR(1024)
 );
 
/*uni 추가 요청 테이블 cnt는 요청 개수*/
 create table if not exists add_uni(
 	uni varchar(128) PRIMARY KEY,
 	cnt int(128)
 );
 
 /*회원가입 가능한 uni목록*/
 create table if not exists uni_list(
 	uniList varchar(128) PRIMARY KEY
 );