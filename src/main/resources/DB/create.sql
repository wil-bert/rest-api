SET MODE PostgreSQL;

CREATE DATABASE news;
\c news;
CREATE TABLE users (
id int PRIMARY KEY auto_increment,
name VARCHAR,
position VARCHAR,
role VARCHAR,
department VARCHAR
);

CREATE TABLE department (
id int PRIMARY KEY auto_increment,
departmentname VARCHAR,
description VARCHAR,
numberofemployees INTEGER,
departmentid INTEGER
);

CREATE TABLE  news (
id int PRIMARY KEY auto_increment,
type VARCHAR,
title VARCHAR,
departmentid int
);

CREATE TABLE department_users(
id SERIAL PRIMARY KEY,
userid INTEGER,
departmentid INTEGER);

CREATE DATABASE news_test WITH TEMPLATE news;


