CREATE TABLE Productos
(
    id    serial primary key,
    name  varchar(100) not null,
    price NUMERIC(10,2)      not null,
    stock integer      not null

)