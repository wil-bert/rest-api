SET MODE PostgreSQL;

CREATE TABLE IF NOT EXISTS users (
id int PRIMARY KEY auto_increment,
name VARCHAR,
position VARCHAR,
role VARCHAR,
department VARCHAR
);

CREATE TABLE IF NOT EXISTS department (
id int PRIMARY KEY auto_increment,
departmentname VARCHAR,
description VARCHAR,
numberofemployees INTEGER,
departmentid INTEGER
);

CREATE TABLE IF NOT EXISTS news (
id int PRIMARY KEY auto_increment,
generalnews VARCHAR,
departmentnews VARCHAR
);