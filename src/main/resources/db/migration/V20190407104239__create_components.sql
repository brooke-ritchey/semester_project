create table COMPONENT
(
    ID SERIAL NOT NULL
        primary key,
    NAME VARCHAR(24) not null,
    DESCRIPTION VARCHAR(255) not null,
    WHOLESALE_PRICE DECIMAL(13,4)
);

insert into COMPONENT(name, description, wholesale_price) VALUES ('first', 'the first one', 10.00);
insert into COMPONENT(name, description, wholesale_price) VALUES ('second', 'the 2nd one', 15.00);
insert into COMPONENT(name, description, wholesale_price) VALUES ('third', 'just something', 20.50);
insert into COMPONENT(name, description, wholesale_price) VALUES ('fourth', 'who knows anymore', 18.79);
insert into COMPONENT(name, description, wholesale_price) VALUES ('fifth', 'just for fun', 36.46);