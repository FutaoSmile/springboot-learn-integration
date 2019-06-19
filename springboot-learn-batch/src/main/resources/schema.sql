drop table if exists batch;

create table `batch`
(
    `id`    bigint(10)   not null auto_increment primary key,
    `email` varchar(255) not null,
    `age`   int(3)       not null,
    `name`  varchar(255) not null
)