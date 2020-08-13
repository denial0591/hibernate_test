create sequence task_seq;
create table task
(
    id   int primary key default nextval('task_seq'),
    name varchar(1000) not null
);
------------------------------------------------------------
create sequence resource_seq;
create table resource
(
    id    int primary key default nextval('resource_seq'),
    url   varchar(1000) not null,
    url2  varchar(1000)
);
------------------------------------------------------------
create table task_has_resource
(
    task_id     int not null references task (id),
    resource_id int not null references resource (id)
);
------------------------------------------------------------
------------------------------------------------------------
