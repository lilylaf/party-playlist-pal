BEGIN TRANSACTION;


DROP TABLE IF EXISTS event_song;
DROP TABLE IF EXISTS dj_library;
DROP TABLE IF EXISTS song_genre;
DROP TABLE IF EXISTS event_genre;
DROP TABLE IF EXISTS event_host;
DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS genre;
DROP TABLE IF EXISTS artist;
DROP TABLE IF EXISTS song;


DROP SEQUENCE IF EXISTS seq_user_id;


CREATE SEQUENCE seq_user_id
  INCREMENT BY 1
  NO MAXVALUE
  NO MINVALUE
  CACHE 1;


CREATE TABLE users (
	user_id int DEFAULT nextval('seq_user_id'::regclass) NOT NULL,
	username varchar(50) UNIQUE NOT NULL,
	password_hash varchar(200) NOT NULL,
	role varchar(50) NOT NULL,
	CONSTRAINT PK_user PRIMARY KEY (user_id)
);

INSERT INTO users (username,password_hash,role) VALUES ('user','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_USER');
INSERT INTO users (username,password_hash,role) VALUES ('admin','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_ADMIN');

--start of our tables
CREATE TABLE genre(
	genre_id serial NOT NULL,
	genre_name varchar(64),
	CONSTRAINT PK_genre PRIMARY KEY (genre_id)
);
CREATE TABLE artist (
	artist_id serial NOT NULL,
	artist_name varchar(64),
	CONSTRAINT PK_artist PRIMARY KEY (artist_id)
);
CREATE TABLE event (
	event_id serial NOT NULL,
	user_id int NOT NULL, 
	event_name varchar(100),
	information varchar(512),
	picture bytea,
	
	CONSTRAINT PK_event PRIMARY KEY (event_id),
	CONSTRAINT FK_event_user_id FOREIGN KEY (user_id) REFERENCES users(user_id) 
);
CREATE TABLE song (
	song_id serial NOT NULL,
	artist_id int NOT NULL,
	song_name varchar(64),
	
	CONSTRAINT PK_song PRIMARY KEY (song_id),
	CONSTRAINT FK_song_artist_id FOREIGN KEY (artist_id) REFERENCES artist(artist_id)
);
CREATE TABLE event_host (
	user_id int NOT NULL,
	event_id int NOT NULL,
	
	CONSTRAINT PK_event_host PRIMARY KEY (user_id, event_id),
	CONSTRAINT FK_event_host_user_id FOREIGN KEY (user_id) REFERENCES users(user_id),
	CONSTRAINT FK_event_host_event_id FOREIGN KEY (event_id) REFERENCES event(event_id) 
);
CREATE TABLE event_genre(
	genre_id int NOT NULL,
	event_id int NOT NULL,
	
	CONSTRAINT PK_event_genre PRIMARY KEY (genre_id, event_id),
	CONSTRAINT FK_event_genre_genre_id FOREIGN KEY (genre_id) REFERENCES genre(genre_id),
	CONSTRAINT FK_event_genre_event_id FOREIGN KEY (event_id) REFERENCES event(event_id)
);
CREATE TABLE song_genre(
	genre_id int NOT NULL,
	song_id int NOT NULL,
	
	CONSTRAINT PK_song_genre PRIMARY KEY (genre_id, song_id),
	CONSTRAINT FK_song_genre_genre_id FOREIGN KEY (genre_id) REFERENCES genre(genre_id),
	CONSTRAINT FK_song_genre_song_id FOREIGN KEY (song_id) REFERENCES song(song_id)
);
CREATE TABLE dj_library(
	user_id int NOT NULL,
	song_id int NOT NULL,
	
	CONSTRAINT PK_dj_library PRIMARY KEY (user_id, song_id),
	CONSTRAINT FK_dj_library_user_id FOREIGN KEY (user_id) REFERENCES users(user_id),
	CONSTRAINT FK_dj_library_song_id FOREIGN KEY (song_id) REFERENCES song(song_id)
);
CREATE TABLE event_song(
	event_id int NOT NULL,
	song_id int NOT NULL,
	song_order int, 
	
	CONSTRAINT PK_event_song PRIMARY KEY (event_id, song_id),
	CONSTRAINT FK_event_song_event_id FOREIGN KEY (event_id) REFERENCES event(event_id),
	CONSTRAINT FK_event_song_song_id FOREIGN KEY (song_id) REFERENCES song(song_id)
);


--end of our tables

--- USER SETUP (Do Not Modify)
DROP USER IF EXISTS final_capstone_owner;
DROP USER IF EXISTS final_capstone_appuser;

CREATE USER final_capstone_owner
WITH PASSWORD 'finalcapstone';

GRANT ALL
ON ALL TABLES IN SCHEMA public
TO final_capstone_owner;

GRANT ALL
ON ALL SEQUENCES IN SCHEMA public
TO final_capstone_owner;

CREATE USER final_capstone_appuser
WITH PASSWORD 'finalcapstone';

GRANT SELECT, INSERT, UPDATE, DELETE
ON ALL TABLES IN SCHEMA public
TO final_capstone_appuser;

GRANT USAGE, SELECT
ON ALL SEQUENCES IN SCHEMA public
TO final_capstone_appuser;


COMMIT TRANSACTION;