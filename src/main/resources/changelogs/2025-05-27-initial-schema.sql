-- liquibase formatted sql

-- changeset sombriks:2025-05-27-initial-schema.sql:1

create table asset_types
(
    id      integer   not null primary key autoincrement,
    name    text      not null unique,
    created timestamp not null default current_timestamp,
    updated timestamp
);

-- rollback drop table asset_types

-- changeset sombriks:2025-05-27-initial-schema.sql:2

create table assets
(
    id             integer   not null primary key autoincrement,
    name           text      not null,
    asset_types_id integer   not null references asset_types (id),
    created        timestamp not null default current_timestamp,
    updated        timestamp
);

-- rollback drop table assets

-- changeset sombriks:2025-05-27-initial-schema.sql:3

create table groups
(
    id             integer   not null primary key autoincrement,
    name           text      not null,
    created        timestamp not null default current_timestamp,
    updated        timestamp
);

-- rollback drop table groups
