-- liquibase formatted sql

-- changeset sombriks:2025-05-27-initial-schema.sql:1

create table asset_types
(
    id      integer   not null primary key,
    name    text      not null unique,
    created timestamp not null default current_timestamp,
    updated timestamp
);

create table login_types
(
    id      integer   not null primary key,
    name    text      not null unique,
    created timestamp not null default current_timestamp,
    updated timestamp
);

-- rollback drop table asset_types;
-- rollback drop table login_types;

-- changeset sombriks:2025-05-27-initial-schema.sql:2

insert into asset_types(id, name)
values (1, 'Consumable'),
       (2, 'Furniture'),
       (3, 'Vehicle'),
       (4, 'Building');

insert into login_types(id, name)
values (1, 'Password'),
       (2, 'Github'),
       (3, 'Token'),
       (4, 'Google');

-- rollback delete from asset_types;
-- rollback delete from login_types;

-- changeset sombriks:2025-05-27-initial-schema.sql:3

create table assets
(
    id             integer   not null primary key autoincrement,
    name           text      not null,
    asset_types_id integer   not null references asset_types (id),
    created        timestamp not null default current_timestamp,
    updated        timestamp
);

create table users
(
    id      integer   not null primary key autoincrement,
    name    text      not null,
    created timestamp not null default current_timestamp,
    updated timestamp
);

create table groups
(
    id      integer   not null primary key autoincrement,
    name    text      not null,
    created timestamp not null default current_timestamp,
    updated timestamp
);

create table departments
(
    id      integer   not null primary key autoincrement,
    name    text      not null,
    created timestamp not null default current_timestamp,
    updated timestamp
);

-- rollback drop table assets;
-- rollback drop table users;
-- rollback drop table groups;
-- rollback drop table departments;

-- changeset sombriks:2025-05-27-initial-schema.sql:4

create table logins
(
    id             integer   not null primary key autoincrement,
    login_types_id integer   not null references login_types (id),
    users_id       integer   not null references users (id) on delete cascade,
    challenge      text      not null,
    created        timestamp not null default current_timestamp,
    updated        timestamp
);

create table users_groups
(
    users_id  integer not null references users (id) on delete cascade,
    groups_id integer not null references groups (id) on delete cascade,
    primary key (users_id, groups_id)
);

create table users_departments
(
    users_id integer not null references users (id) on delete cascade,
    departments_id integer not null references departments(id) on delete cascade,
    primary key (users_id, departments_id)
);

-- rollback drop table logins;
-- rollback drop table users_groups;
-- rollback drop table users_departments;
