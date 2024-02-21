CREATE TABLE tasks(
	id bigint PRIMARY KEY AUTO_INCREMENT,
	name varchar(50) NOT NULL,
	description varchar(255) NOT NULL,
	register_at date NOT NULL
);

CREATE TABLE task_status(
	id bigint PRIMARY KEY AUTO_INCREMENT,
	name varchar(50) NOT NULL,
	color varchar(50) NOT NULL
);

CREATE TABLE task_tags(
	id bigint PRIMARY KEY AUTO_INCREMENT,
	name varchar(50) NOT NULL,
	color varchar(50) NOT NULL
);


CREATE TABLE join_task_status(
	id bigint PRIMARY KEY AUTO_INCREMENT,
	task_id bigint NOT NULL,
	status_id bigint NOT NULL,
	FOREIGN KEY (task_id) REFERENCES tasks(id),
	FOREIGN KEY (status_id) REFERENCES task_status(id)
);

CREATE TABLE join_task_tag(
	id bigint PRIMARY KEY AUTO_INCREMENT,
	task_id bigint NOT NULL,
	tag_id bigint NOT NULL,
	FOREIGN KEY (task_id) REFERENCES tasks(id),
	FOREIGN KEY (tag_id) REFERENCES task_tags(id)
);

CREATE TABLE task_updated_log(
	id bigint PRIMARY KEY AUTO_INCREMENT,
	type_action enum('CREATE', 'COMMENT', 'UPDATE_STATUS', 'ADD_RESPONSIBLE', 'REMOVE_RESPONSIBLE') NOT NULL,
	register_at date NOT NULL,
	task bigint NOT NULL,
	by_user bigint NOT NULL,
	FOREIGN KEY (task) REFERENCES tasks(id),
	FOREIGN KEY (by_user) REFERENCES users(id)
);

CREATE TABLE task_comments(
	id bigint PRIMARY KEY AUTO_INCREMENT,
	comment varchar(255) NOT NULL,
	register_at date NOT NULL,
	task bigint NOT NULL,
	by_user bigint NOT NULL,
	FOREIGN KEY (task) REFERENCES tasks(id),
	FOREIGN KEY (by_user) REFERENCES users(id)
);

CREATE TABLE task_responsible(
	id bigint PRIMARY KEY AUTO_INCREMENT,
	task bigint NOT NULL,
	user_id bigint NOT NULL,
	FOREIGN KEY (task) REFERENCES tasks(id),
	FOREIGN KEY (user_id) REFERENCES users(id)
);

