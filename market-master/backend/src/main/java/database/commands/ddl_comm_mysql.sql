CREATE TABLE users (
	username varchar(100) NOT NULL,
	password varchar(100) NOT NULL,
	last_date DATE NOT NULL,
	role varchar(100) NOT NULL DEFAULT 'user',
	PRIMARY KEY (username)
);

CREATE TABLE search_history (
	content varchar(200) NOT NULL,
	username varchar(100) NOT NULL,
	content_date DATE NOT NULL
);
