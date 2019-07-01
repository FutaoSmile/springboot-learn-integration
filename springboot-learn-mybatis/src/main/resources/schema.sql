create database `springboot_learn_persistent_mybatis`
    default character set utf8mb4;

use `springboot_learn_persistent_mybatis`;

create table `mybatis_book`
(
    `id`     int(11) auto_increment primary key,
    `name`   varchar(255),
    `author` varchar(255)
) engine InnoDB
  character set utf8mb4;