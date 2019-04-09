create table PRODUCT
(
    ID SERIAL NOT NULL primary key,
    NAME varchar(24) not null,
    DESCRIPTION varchar(24)
);

insert into PRODUCT(name, DESCRIPTION) values ('ok', 'ok')