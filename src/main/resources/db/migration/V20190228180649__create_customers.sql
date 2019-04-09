CREATE TABLE CUSTOMER
(
  ID SERIAL NOT NULL
    PRIMARY KEY,
  FIRST_NAME VARCHAR(24) NOT NULL,
  LAST_NAME VARCHAR(24) NOT NULL,
  ADDRESS VARCHAR(100) NOT NULL,
  PHONE_NUMBER VARCHAR(11) NOT NULL
);

insert into customer(first_name, last_name, address, phone_number) values ('ok',  'ok', 'ok', 'ok');