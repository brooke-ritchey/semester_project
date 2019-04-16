create table COMPONENT
(
    ID SERIAL NOT NULL
        primary key,
    NAME VARCHAR(24) not null,
    DESCRIPTION VARCHAR(255) not null,
    WHOLESALE_PRICE DECIMAL(13,4)
);
