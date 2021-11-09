BEGIN;
drop function if exists check_function;
drop table if exists t_test;
drop table if exists flyway_schema_history;

COMMIT;