create table users
(
    id bigserial primary key,
    name text,
    surname text,
    username text not null,
    password text not null,
    role text not null
)
