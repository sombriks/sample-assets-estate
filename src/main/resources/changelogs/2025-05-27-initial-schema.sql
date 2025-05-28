-- liquibase formatted sql

-- changeset sombriks:2025-05-27-initial-schema.sql

create table assets(
    id integer not null primary key  autoincrement,
    name text not null,
    created timestamp not null default current_timestamp,
    updated timestamp
);

-- rollback drop table assets
