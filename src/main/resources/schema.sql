-- 20220704 Note: file created in Lesson 35. By default Hibernate looks for a file called schema.sql to initialize the
--                database
drop table if exists book;
drop table if exists hibernate_sequence;

create table book (
                  id bigint not null,
                  isbn varchar(255),
                  publisher varchar(255),
                  title varchar(255),
                  primary key (id)
) engine=InnoDB;

create table hibernate_sequence (
                  next_val bigint
) engine=InnoDB;

insert into hibernate_sequence values ( 1 );