create table users
(
    id bigserial primary key,
    username text not null,
    email text not null,
    password text not null,
    role text not null
)
