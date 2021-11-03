BEGIN;

create table if not exists t_test
(
    id bigserial primary key,
    created_at timestamp not null default now(),
    updated_at timestamp not null default now(),
    deleted BOOLEAN not null default false,
    enabled BOOLEAN not null default false,

    title bigint not null default 0
);

COMMIT;