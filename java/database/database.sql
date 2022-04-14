BEGIN TRANSACTION;


DROP TABLE IF EXISTS event_song;
DROP TABLE IF EXISTS dj_library;
DROP TABLE IF EXISTS song_genre;
DROP TABLE IF EXISTS event_genre;
DROP TABLE IF EXISTS event_host;
DROP TABLE IF EXISTS event;
DROP TABLE IF EXISTS song;
DROP TABLE IF EXISTS artist;
DROP TABLE IF EXISTS genre;
DROP TABLE IF EXISTS users;



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
	genre_name varchar(64),
	CONSTRAINT PK_genre PRIMARY KEY (genre_name)
);
CREATE TABLE artist (
	artist_name varchar(64),
	CONSTRAINT PK_artist PRIMARY KEY (artist_name)
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
	song_id serial,
	artist_name varchar(64),
	song_name varchar(64),
	featured_artist varchar(64),
	
	CONSTRAINT PK_song PRIMARY KEY (song_id),
	CONSTRAINT FK_song_artist_name FOREIGN KEY (artist_name) REFERENCES artist(artist_name)
);
CREATE TABLE event_host (
	user_id int NOT NULL,
	event_id int NOT NULL,
	
	CONSTRAINT PK_event_host PRIMARY KEY (user_id, event_id),
	CONSTRAINT FK_event_host_user_id FOREIGN KEY (user_id) REFERENCES users(user_id),
	CONSTRAINT FK_event_host_event_id FOREIGN KEY (event_id) REFERENCES event(event_id) 
);
CREATE TABLE event_genre(
	genre_name varchar(64),
	event_id int NOT NULL,
	
	CONSTRAINT PK_event_genre PRIMARY KEY (genre_name, event_id),
	CONSTRAINT FK_event_genre_genre_name FOREIGN KEY (genre_name) REFERENCES genre(genre_name),
	CONSTRAINT FK_event_genre_event_id FOREIGN KEY (event_id) REFERENCES event(event_id)
);
CREATE TABLE song_genre(
	genre_name varchar(64),
	song_id int NOT NULL,
	
	CONSTRAINT PK_song_genre PRIMARY KEY (genre_name, song_id),
	CONSTRAINT FK_song_genre_genre_name FOREIGN KEY (genre_name) REFERENCES genre(genre_name),
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





--start of mock data
INSERT INTO users(username, password_hash, role)

VALUES ('DJFlexBox', '$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_DJ');


INSERT INTO users(username, password_hash, role)
VALUES ('DJGrid', '$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_DJ');

INSERT INTO users(username, password_hash, role)
VALUES ('LilDebbie', '$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_HOST');

INSERT INTO users(username, password_hash, role)
VALUES ('JayGatsby', '$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_HOST');

INSERT INTO event(user_id, event_name, information)
VALUES ('3', 'Foam Party', 'Bubbles and tunes'), ('3', 'Lollipopalooza', 'Caaaandy'), ('3', 'Cowabunga', 'Enjoy the mooooosic');

INSERT INTO genre(genre_name)
VALUES ('party'), ('pop'), ('rap');

INSERT INTO artist (artist_name)
VALUES ('P!nk'), ('LMFAO'), ('Outkast'), ('Usher'),('Salt-N-Pepa'), ('DJ Casper'), ('Lady Gaga'), ('Village People'), ('Flo Rida'), 
('Neil Diamond'), ('Cyndi Lauper'), ('Tag Team'), ('Prince'), ('Lizzo'), ('Smash Mouth'), ('Gorillaz'), ('Missy Elliott'),
('Sir Mix-a-Lot' ), ('Spice Girls'), ('Eminem'), ('Doja Cat'), ('Dr. Dre');

INSERT INTO artist
VALUES ('Kodak Black'),('Jay-Z'),('DaBaby'),('Justin Timberlake'),('Jack Harlow'),('Swae Lee'),('Taylor Swift'),('Jason Aldean'),('Luke Bryan'),('Florida Georgia Line'),('Lee Brice'),
('Cardi B'),('A$AP Rocky'),('Snoop Dogg'),('Green Day'),('Oasis'),('Elvis Presley'),('Phil Collins'),('V.I.C.'),('Playboi Carti'),('Beyonce'),('The Chainsmokers'),('Avicii'),('Pitbull'),('Kesha'),
('Lil Jon'),('Alesso'),('David Guetta'),('Skrillex'),('blink-182'),('PSY'),('3 Doors Down'),('Puddle Of Mudd'),('Nicki Minaj'),('Swedish House Mafia'),('Metallica'),('Aerosmith'),('Sheck Wes'),
('French Montana'),('AC/DC'),('Blake Shelton'),('Drake'),('Halsey'),('The Proclaimers'),('Lynyrd Skynyrd'),('Vitamin C'),('Post Malone'),('benny blanco'),('Juice WRLD'),('fun.'),('Black Eyed Peas'),
('Train'),('GAYLE'),('Macklemore'),('Kanye West'),('Nelly'),('Offset'),('My Chemical Romance'),('Paramore'),('Sublime'),('Ed Sheeran'),('John Legend'),('Robin Thicke'),('Pharrell Williams'),('Coldplay'),
('ABBA'),('Aqua'),('Third Eye Blind'),('Chumbawamba'),('Katy Perry'),('Juicy J'),('Guns N'' Roses'),('Twisted Sister'),('Journey'),('Foreigner'),('Duran Duran'),('The Beetles'),('Billy Joel'),
('John Mellencamp'),('Eagles'),('Sia'),('Lorde');

INSERT INTO song (artist_name, song_name)
VALUES ('P!nk','Get The Party Started'), ( 'LMFAO','Party Rock Anthem'), ('Outkast','Hey Ya!'), ('Usher','Yeah!'), ('Salt-N-Pepa','Push It!'), ('DJ Casper','Cha Cha Slide'), ('Lady Gaga','Just Dance'),
('Village People','Y.M.C.A.'), ( 'Flo Rida', 'Low'), ( 'Neil Diamond', 'Sweet Caroline'), ( 'Cyndi Lauper', 'Girls Just Want To Have Fun'), ( 'Tag Team', 'Whoomp, There It Is'),
('Prince',  '1999'), ('Lizzo','Truth Hurts'), ( 'Smash Mouth', 'All Star'), ( 'Gorillaz', 'DARE'), ( 'Missy Elliott', 'Work It'), ( 'Sir Mix-a-Lot', 'Baby Got Back'),
('Spice Girls',  'Wannabe'), ( 'Eminem', 'Lose Yourself'), ( 'Doja Cat', 'Moo');

INSERT INTO song (artist_name,song_name,featured_artist)
VALUES 
('Kodak Black','ZEZE','Offset'),
('Jay-Z','Holy Grail','Justin Timberlake'),
('AC/DC','Thunderstruck',null),
('DaBaby','PRACTICE',null),
('Swae Lee','Ball Is Life','Jack Harlow'),
('Taylor Swift','We Are Never Ever Getting Back Together',null),
('3 Doors Down','Kryptonite',null),
('PSY','Gangnam',null),
('Avicii','Levels','Skrillex'),
('Blake Shelton','Came Here To Forget',null),
('blink-182','All The Small Things',null),
('Florida Georgia Line','This Is How We Roll','Luke Bryan'),
('V.I.C.','Wobble',null),
('Puddle Of Mudd','She Hates Me',null),
('Nelly','Ride Wit Me',null),
('A$AP Rocky','Babushka Boi',null),
('Green Day','When I Come Around',null),
('Oasis','Wonderwall',null),
('Sublime','Santeria',null),
('Lynyrd Skynyrd','Free Bird',null),
('Halsey','Bad At Love',null),
('The Proclaimers','I''m Gonna Be (500 Miles)',null),
('Playboi Carti','Magnolia',null),
('My Chemical Romance','Welcome To The Black Parade',null),
('Paramore','Misery Business',null),
('Swedish House Mafia','One',null),
('Phil Collins','In The Air Tonight',null),
('Aerosmith','I Don''t Want To Miss A Thing',null),
('Juice WRLD','Lucid Dreams',null),
('The Chainsmokers','Closer','Halsey'),
('Halsey','Let Me Go','Florida Georgia Line'),
('Cardi B','Money',null),
('Kanye West','Mercy',null),
('GAYLE','abcdefu',null),
('Elvis Presley','Devil In Disguise',null),
('Post Malone','Congratulations',null),
('Green Day','Good Riddance (Time Of Our Lives)',null),
('Train','Marry Me',null),
('Prince','Purple Rain',null),
('Lil Jon','Low',null),
('Black Eyed Peas','I Gotta Feeling',null),
('Metallica','Enter Sandman',null),
('Pitbull','Timber','Kesha'),
('Kesha','Blow',null),
('Taylor Swift','Love Story',null),
('Jason Aldean','Burnin'' It Down',null),
('Luke Bryan','I Don''t Want This Night To End',null),
('Lee Brice','Love Like Crazy',null),
('Ed Sheeran','Thinking Out Loud',null),
('John Legend','All Of Me',null),
('Robin Thicke','Blurred Lines','Pharrell Williams'),
('Pharrell Williams','Happy',null),
('The Chainsmokers','Something Just Like This','Coldplay'),
('Vitamin C','Graduation (Friends Forever)',null),
('ABBA','Dancing Queen',null),
('Aqua','Barbie  Girl',null),
('Macklemore','Thift Shop',null),
('Drake','In My Feelings',null),
('Cardi B','I Like It',null),
('benny blanco','Eastside','Halsey'),
('French Montana','Unforgettable','Swae Lee'),
('Dr. Dre','I Need A Doctor','Eminem'),
('Oasis','Champagne Supernova',null),
('Third Eye Blind','Semi-Charmed Life',null),
('Chumbawamba','Tubthumping',null),
('Sheck Wes','Mo Bamba',null),
('Drake','Take Care','Rihanna'),
('Justin Timberlake','Suit  & Tie','Jay-Z'),
('Justin Timberlake','CAN''T STOP THE FEELING!',null),
('Katy Perry','Wide Awake',null),
('Katy Perry','Dark Horse','Juicy J'),
('Taylor Swift','...Ready For It?',null),
('Beyonce','Flawless Remix','Nicki Minaj'),
('Twisted Sister','We''re Not Gonna Take It',null),
('Guns N'' Roses','Sweet Child O'' Mine',null),
('Journey','Don''t Stop Believing',null),
('Foreigner','Hot Blooded',null),
('Eminem','Detroit Vs. Everybody',null),
('Duran Duran','Hungry Like The Wolf',null),
('Journey','Faithfully',null),
('Lynyrd Skynyrd','Sweet Home Alabama',null),
('The Beetles','I Want To Hold Your Hand',null),
('Billy Joel','Piano Man',null),
('3 Doors Down','Here Without You',null),
('Aerosmith','Crazy',null),
('John Mellencamp','Jack And Diane',null),
('Train','Soul  Sister',null),
('Eagles','Hotel California',null),
('Kodak Black','Calling My Spirit',null),
('Flo Rida','Club Can''t Handle Me','David Guetta'),
('Sia','Cheap Thrills',null),
('David Guetta','Titanium','Sia'),
('Lorde','Team',null),
('A$AP Rocky','Excuse Me',null),
('Jason Aldean','Tattoos On This Town',null),
('blink-182','What''s My Age Again?',null),
('Beyonce','Run The World (Girls)',null);

INSERT INTO song(artist_name, song_name, featured_artist)
VALUES ( 'Dr. Dre','Forgot About Dre','Eminem');

INSERT INTO song_genre(genre_name, song_id)
VALUES ('rap', 20), ('rap', 22), ('rap', 18);

INSERT INTO song_genre(genre_name, song_id)
VALUES ('pop', 1), ('pop', 7), ('pop', 11);

INSERT INTO song_genre(genre_name, song_id)
VALUES ('party', 6), ('party', 8);

INSERT INTO event_song(event_id, song_id)
VALUES (1, '6'),(1, '8');

INSERT INTO event_song(event_id, song_id)
VALUES (2, '18'), (2, '20'), (2, '22');

INSERT INTO event_song(event_id, song_id)
VALUES (3, '1'), (3, '7'), (3, '11');

INSERT INTO dj_library(user_id, song_id)
VALUES ('3', '1'), ('3', '2'), ('3', '3');

INSERT INTO event_genre(genre_name, event_id)
VALUES ('party', 1), ('rap', 2), ('pop', 3);


--end of mock data
