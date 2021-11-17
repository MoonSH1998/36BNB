CREATE DATABASE IF NOT EXISTS mysns
DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;

USE mysns;

/*user���̺�*/
CREATE TABLE IF NOT EXISTS user(
	id varchar(128) PRIMARY KEY ,
	jsonstr VARCHAR(1024)
    
);
/*feed���̺�*/
CREATE TABLE IF NOT EXISTS feed (
	no INT UNSIGNED PRIMARY KEY,
	id VARCHAR(128),
	uni VARCHAR(128),
	jsonstr VARCHAR(8192)
	);
/*feed heart,repoert ����Ǵ� ���̺�*/
create table if not exists feedoption(
 	list INT UNSIGNED PRIMARY KEY,
 	jsonstr VARCHAR(1024)
 );
 
/*uni �߰� ��û ���̺� cnt�� ��û ����*/
 create table if not exists add_uni(
 	uni varchar(128) PRIMARY KEY,
 	cnt int(128)
 );
 
 /*ȸ������ ������ uni���*/
 create table if not exists uni_list(
 	uniList varchar(128) PRIMARY KEY
 );