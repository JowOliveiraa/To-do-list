CREATE TABLE status(
    id bigint PRIMARY KEY AUTO_INCREMENT,
    name varchar(50) NOT NULL,
    color varchar(50) NOT NULL
);

INSERT INTO status(name, color)
    VALUES('default', 'default');