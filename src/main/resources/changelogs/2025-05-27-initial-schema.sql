-- liquibase formatted sql

-- changeset sombriks:2025-05-27-initial-schema.sql:1

create table asset_types
(
    id      integer   not null primary key,
    name    text      not null unique,
    created timestamp not null default current_timestamp,
    updated timestamp
);

create table asset_status
(
    id      integer   not null primary key,
    name    text      not null unique,
    created timestamp not null default current_timestamp,
    updated timestamp
);

create table change_reasons
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

create table groups
(
    id      integer   not null primary key,
    name    text      not null unique,
    created timestamp not null default current_timestamp,
    updated timestamp
);

-- rollback drop table asset_types;
-- rollback drop table asset_status;
-- rollback drop table change_reasons;
-- rollback drop table login_types;
-- rollback drop table groups;

-- changeset sombriks:2025-05-27-initial-schema.sql:2

insert into asset_types(id, name)
values (1, 'Consumable'),
       (2, 'Furniture'),
       (3, 'Vehicle'),
       (4, 'Building');

insert into asset_status(id, name)
values (1, 'Available'),
       (2, 'In use'),
       (3, 'Broken'),
       (4, 'For sale'),
       (5, 'Out of stock'),
       (999, 'Other');

insert into change_reasons (id, name)
values (1, 'Inclusion'),
       (2, 'Deactivation'),
       (3, 'Reactivation'),
       (4, 'Price change'),
       (5, 'Value change'),
       (6, 'Interest change'),
       (7, 'Amount change'),
       (8, 'Department change'),
       (9, 'In use'),
       (10, 'Available'),
       (999, 'Other');

insert into login_types(id, name)
values (1, 'Password'),
       (2, 'Github'),
       (3, 'Token'),
       (4, 'Google');

insert into groups (id, name)
values (1, 'Admin'),
       (2, 'Manager'),
       (3, 'Basic');

-- rollback delete from asset_types;
-- rollback delete from asset_status;
-- rollback delete from change_reasons;
-- rollback delete from login_types;
-- rollback delete from groups;

-- changeset sombriks:2025-05-27-initial-schema.sql:3

create table assets
(
    id             integer   not null primary key autoincrement,
    name           text      not null,
    description    text,
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

create table departments
(
    id      integer   not null primary key autoincrement,
    name    text      not null,
    created timestamp not null default current_timestamp,
    updated timestamp
);

-- rollback drop table assets;
-- rollback drop table users;
-- rollback drop table departments;

-- changeset sombriks:2025-05-27-initial-schema.sql:4

create table logins
(
    id             integer   not null primary key autoincrement,
    login_types_id integer   not null references login_types (id),
    users_id       integer   not null references users (id) on delete cascade,
    email          text      not null,
    challenge      text      not null,
    created        timestamp not null default current_timestamp,
    updated        timestamp,
    unique (login_types_id, email)
);

create table users_groups
(
    users_id  integer not null references users (id) on delete cascade,
    groups_id integer not null references groups (id) on delete cascade,
    primary key (users_id, groups_id)
);

create table users_departments
(
    users_id       integer not null references users (id) on delete cascade,
    departments_id integer not null references departments (id) on delete cascade,
    primary key (users_id, departments_id)
);

-- rollback drop table logins;
-- rollback drop table users_groups;
-- rollback drop table users_departments;

-- changeset sombriks:2025-05-27-initial-schema.sql:5

create table consumables_position
(
    id                integer        not null primary key autoincrement,
    assets_id         integer        not null references assets (id),
    departments_id    integer        not null references departments (id),
    author            integer        not null references users (id),
    asset_status_id   integer        not null references asset_status (id),
    change_reasons_id integer        not null references change_reasons (id),
    comment           text           not null default '',
    unit_value        decimal(12, 4) not null,
    amount            integer        not null check (amount >= 0),
    started           timestamp      not null default current_timestamp,
    created           timestamp      not null default current_timestamp,
    updated           timestamp
);

create table furniture_position
(
    id                integer        not null primary key autoincrement,
    assets_id         integer        not null references assets (id),
    departments_id    integer        not null references departments (id),
    author            integer        not null references users (id),
    asset_status_id   integer        not null references asset_status (id),
    change_reasons_id integer        not null references change_reasons (id),
    comment           text           not null default '',
    start_value       decimal(12, 4) not null,
    current_value     decimal(12, 4) not null,
    terminal_value    decimal(12, 4) not null,
    interest          decimal(2, 4)  not null check ( interest <= 0 ),
    started           timestamp      not null default current_timestamp,
    created           timestamp      not null default current_timestamp,
    updated           timestamp
);

create table vehicles_position
(
    id                integer        not null primary key autoincrement,
    assets_id         integer        not null references assets (id),
    departments_id    integer        not null references departments (id),
    author            integer        not null references users (id),
    asset_status_id   integer        not null references asset_status (id),
    change_reasons_id integer        not null references change_reasons (id),
    comment           text           not null default '',
    start_value       decimal(12, 4) not null,
    current_value     decimal(12, 4) not null,
    terminal_value    decimal(12, 4) not null,
    interest          decimal(2, 4)  not null check ( interest <= 0 ),
    model             text           not null default '',
    started           timestamp      not null default current_timestamp,
    created           timestamp      not null default current_timestamp,
    updated           timestamp
);

create table buildings_position
(
    id                integer        not null primary key autoincrement,
    assets_id         integer        not null references assets (id),
    departments_id    integer        not null references departments (id),
    author            integer        not null references users (id),
    asset_status_id   integer        not null references asset_status (id),
    change_reasons_id integer        not null references change_reasons (id),
    comment           text           not null default '',
    start_value       decimal(12, 4) not null,
    current_value     decimal(12, 4) not null,
    terminal_value    decimal(12, 4) not null,
    interest          decimal(2, 4)  not null check ( interest <= 0 ),
    address           text           not null,
    started           timestamp      not null default current_timestamp,
    created           timestamp      not null default current_timestamp,
    updated           timestamp
);

-- rollback consumables_position;
-- rollback furniture_position;
-- rollback vehicles_position;
-- rollback buildings_position;
