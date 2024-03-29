CREATE TABLE users(
    id bigint PRIMARY KEY AUTO_INCREMENT,
    name varchar(50) NOT NULL,
    password varchar(255) NOT NULL,
    email varchar(50) NOT NULL UNIQUE,
    active boolean NOT NULL,
    role enum('ADMIN', 'DEFAULT') NOT NULL,
    register_at date NOT NULL
);