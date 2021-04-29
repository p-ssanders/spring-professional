create table example (
    id int auto_increment,
    content varchar(100) not null,
    created_datetime timestamp not null default current_timestamp
);