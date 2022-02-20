CREATE TYPE user_role_enum AS ENUM ('USER', 'ADMIN');

CREATE TABLE recipe_user (
	id serial PRIMARY KEY,
	user_name VARCHAR (50) unique not NULL,
	email VARCHAR (50) unique not null,
	pw_hash VARCHAR(60) not null,
	user_role user_role_enum not null
);

CREATE TABLE category (
	id serial PRIMARY KEY,
	value VARCHAR (50) unique not NULL
);
