-- liquibase formatted sql

-- changeset sombriks:9999-test-data.sql-1 context: test

insert into users(name)
values ('test user');
insert into departments(name)
values ('test department');
insert into assets(name, description, asset_types_id)
values ('Pencil', 'color pencil', 1),
       ('Paper', 'a4 white paper', 1),
       ('Glue', 'paper glue', 1);

-- rollback delete from users;
-- rollback delete from departments;
-- rollback delete from assets;

-- changeset sombriks:9999-test-data.sql-2 context: test

insert into logins(login_types_id, users_id, email, challenge)
values (1, 1, 'test@test.com', '$2a$10$.qGypUVEtCTO7KNERjqqX.BelKxSMO5dGoHVDAiHLz7/1AFOqccQW');

insert into users_groups (users_id, groups_id)
values (1,1);

insert into users_departments
values  (1,1);

insert into consumables_position (assets_id, departments_id, author, asset_status_id, change_reasons_id, unit_value, amount, started)
values(1, 1, 1, 1, 1, 1, 10, '2025-06-29 00:00:00');
insert into consumables_position (assets_id, departments_id, author, asset_status_id, change_reasons_id, unit_value, amount, started)
values(2, 1, 1, 1, 1, 1, 10, '2025-06-29 00:00:00');
insert into consumables_position (assets_id, departments_id, author, asset_status_id, change_reasons_id, unit_value, amount, started)
values(3, 1, 1, 1, 1, 1, 10, '2025-06-29 00:00:00');
insert into consumables_position (assets_id, departments_id, author, asset_status_id, change_reasons_id, unit_value, amount, started)
values(3, 1, 1, 1, 7, 1, 15, '2025-06-30 00:00:00');

-- rollback delete from consumables_position;
-- rollback delete from users_departments;
-- rollback delete from users_groups;
-- rollback delete from logins;
