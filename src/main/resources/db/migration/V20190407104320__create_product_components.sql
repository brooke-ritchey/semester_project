create table product_component
(
    id serial not null primary key,
    quantity int not null,
    component_id integer references component(id),
    product_id integer references product(id)

);