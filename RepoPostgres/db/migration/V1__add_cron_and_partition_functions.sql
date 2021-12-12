BEGIN;
CREATE
or replace FUNCTION check_function() RETURNS integer
AS $body$
SELECT 1 $body$ LANGUAGE SQL;
COMMIT;

-- postgresql 14
BEGIN;
CREATE
EXTENSION IF NOT EXISTS plpgsql;
CREATE
EXTENSION IF NOT EXISTS pg_cron;
COMMIT;

BEGIN;
--creating partition creators
create
or replace function min_partitioner(t text, field text, d1 timestamp, d2 timestamp)
    returns void as
$body$
declare
create_query text;
    index_query
text;
begin
for create_query, index_query in
select 'create table if not exists '
           || t || '_' || to_char(d, 'YYYY_MM_HH24_MI')
           || ' PARTITION of ' || t
           || ' FOR VALUES FROM ('''
           || to_char(date_trunc('minute', d), 'YYYY-MM-DD HH24:MI')
           || ''') TO ('''
           || to_char(date_trunc('minute', d) + interval '1 minute', 'YYYY-MM-DD HH24:MI')
           || ''');',
       'create index if not exists idx_'
           || t || '_' || to_char(d, 'YYYY_MM_HH24_MI') || '_' || field
           || ' on ' || t || '_' || to_char(d, 'YYYY_MM_HH24_MI')
           || ' ( ' || field || ' desc);'
from generate_series(d1, d2, '1 minute') as d loop
            execute create_query;
execute index_query;
end loop;
end;
$body$
language plpgsql;
----------------------
create
or replace function daily_partitioner(t text, field text, d1 timestamp, d2 timestamp)
    returns void as
$body$
declare
create_query text;
    index_query
text;
begin
for create_query, index_query in
select 'create table if not exists '
           || t || '_' || to_char(d, 'YYYY_MM_DD')
           || ' PARTITION of ' || t
           || ' FOR VALUES FROM ('''
           || to_char(date_trunc('day', d), 'YYYY-MM-DD')
           || ''') TO ('''
           || to_char(date_trunc('day', d) + interval '1 day', 'YYYY-MM-DD')
           || ''');',
       'create index if not exists idx_'
           || t || '_' || to_char(d, 'YYYY_MM_DD') || '_' || field
           || ' on ' || t || '_' || to_char(d, 'YYYY_MM_DD')
           || ' ( ' || field || ' desc);'
from generate_series(d1, d2, '1 day') as d loop
            execute create_query;
execute index_query;
end loop;
end;
$body$
language plpgsql;
---------------------------
create
or replace function monthly_partitioner(t text, field text, d1 timestamp, d2 timestamp)
    returns void as
$body$
declare
create_query text;
    index_query
text;
begin
for create_query, index_query in
select 'create table if not exists '
           || t || '_' || to_char(d, 'YYYY_MM')
           || ' PARTITION of ' || t
           || ' FOR VALUES FROM ('''
           || to_char(date_trunc('month', d), 'YYYY-MM-DD')
           || ''') TO ('''
           || to_char(date_trunc('month', d) + interval '1 month', 'YYYY-MM-DD')
           || ''');',
       'create index if not exists idx_'
           || t || '_' || to_char(d, 'YYYY_MM') || '_' || field
           || ' on ' || t || '_' || to_char(d, 'YYYY_MM')
           || ' ( ' || field || ' desc);'
from generate_series(d1, d2, '1 month') as d loop
            execute create_query;
execute index_query;
end loop;
end;
$body$
language plpgsql;
---------------------------
create
or replace function yearly_partitioner(t text, field text, d1 timestamp, d2 timestamp)
    returns void as
$body$
declare
create_query text;
    index_query
text;
begin
for create_query, index_query in
select 'create table if not exists '
           || t || '_' || to_char(d, 'YYYY')
           || ' PARTITION of ' || t
           || ' FOR VALUES FROM ('''
           || to_char(date_trunc('year', d), 'YYYY-MM-DD')
           || ''') TO ('''
           || to_char(date_trunc('year', d) + interval '1 year', 'YYYY-MM-DD')
           || ''');',
       'create index if not exists idx_'
           || t || '_' || to_char(d, 'YYYY') || '_' || field
           || ' on ' || t || '_' || to_char(d, 'YYYY')
           || ' ( ' || field || ' desc);'
from generate_series(d1, d2, '1 year') as d loop
            execute create_query;
execute index_query;
end loop;
end;
$body$
language plpgsql;

COMMIT;