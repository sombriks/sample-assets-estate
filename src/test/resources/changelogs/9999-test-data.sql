-- liquibase formatted sql

-- changeset sombriks:9999-test-data.sql context: test

insert into users(name) values ('test user');

-- rollback delete from users;
