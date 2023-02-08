create table employee (
       id bigint not null auto_increment,
        birthday date,
        email varchar(255),
        full_name varchar(255),
        password varchar(255),
        username varchar(255),
        role_id bigint,
        primary key (id)
    )