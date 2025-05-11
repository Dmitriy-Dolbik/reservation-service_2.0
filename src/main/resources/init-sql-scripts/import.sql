create schema my_schema;
create sequence reservations_seq;
create table my_schema.Reservations (
    id bigint primary key,
    endDay date,
    startDay date,
    carId bigint,
    userId varchar(255)
);
