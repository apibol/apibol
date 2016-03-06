CREATE DATABASE user;
CREATE TABLE IF NOT EXISTS users (
    id varchar(250) NOT NULL,
    email varchar(250) NOT NULL,
    nickname varchar(250) NOT NULL,
    PRIMARY KEY(id)
);