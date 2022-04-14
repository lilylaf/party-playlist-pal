SELECT *
FROM users

SELECT *
FROM artist

SELECT *
FROM song

SELECT *
FROM genre

SELECT *
FROM dj_library

SELECT *
FROM event

SELECT song_id, song_name, artist_name, featured_artist
FROM song
NATURAL JOIN dj_library
NATURAL JOIN users
WHERE dj_library.user_id = 3
ORDER BY song_id ASC;

SELECT user_id, username
FROM users
WHERE role = 'ROLE_DJ';

--query to look up song in dj library to add to playlist--
SELECT song_name, artist_name
FROM song
NATURAL JOIN dj_library
WHERE song_name ILIKE '%e%';

SELECT song_id, song_name, artist_name, featured_artist
FROM song
NATURAL JOIN event_song
WHERE event_id = 3;

--select all song names with id's and genres--
SELECT song.song_name, song.song_id, genre_name FROM  song
JOIN song_genre ON song.song_id = song_genre.song_id