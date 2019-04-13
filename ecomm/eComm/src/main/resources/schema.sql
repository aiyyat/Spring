drop table if exists users;
CREATE TABLE users (
    id BIGINT AUTO_INCREMENT,
    username VARCHAR(255),
    password VARCHAR(255),
    enabled BOOLEAN
);
insert into users(username,password,enabled) values('admin','admin',true);
insert into users(username,password,enabled) values('root','root',true);
drop table if exists authorities;
CREATE TABLE authorities (
    username VARCHAR(255),
    authority VARCHAR(255),
    UNIQUE (username , authority)
);
insert into authorities(username,authority) values('root','ROLE_ROOT');
insert into authorities(username,authority) values('admin','ROLE_ADMIN');
