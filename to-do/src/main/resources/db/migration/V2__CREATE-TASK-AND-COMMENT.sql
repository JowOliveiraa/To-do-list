CREATE TABLE tasks(
	id bigint PRIMARY KEY AUTO_INCREMENT,
	name varchar(50) NOT NULL,
	description varchar(255) NOT NULL,
	register_at date NOT NULL
);

CREATE TABLE comments(
	id bigint PRIMARY KEY AUTO_INCREMENT,
	comment varchar(255) NOT NULL,
	register_at date NOT NULL
);


