-- Insert sample movies
INSERT INTO movies
    (imdb_id, title, release_date, trailer_link, poster)
VALUES
    ('tt0111161', 'The Shawshank Redemption', '1994-09-23', 'https://www.youtube.com/watch?v=6hB3S9bIaco', 'https://image.tmdb.org/t/p/w500/q6y0Go1tsGEsmtFryDOJo3dEmqu.jpg'),
    ('tt0068646', 'The Godfather', '1972-03-24', 'https://www.youtube.com/watch?v=sY1S34973zA', 'https://image.tmdb.org/t/p/w500/3bhkrj58Vtu7enYsRolD1fZdja1.jpg'),
    ('tt0468569', 'The Dark Knight', '2008-07-18', 'https://www.youtube.com/watch?v=EXeTwQWrcwY', 'https://image.tmdb.org/t/p/w500/qJ2tW6WMUDux911r6m7haRef0WH.jpg'),
    ('tt0108052', 'Schindler''s List', '1993-12-15', 'https://www.youtube.com/watch?v=gG22XNhtnoY', 'https://image.tmdb.org/t/p/w500/sF1U4EUQS8YHUYjNl3pMGNIQyr0.jpg'),
    ('tt0110912', 'Pulp Fiction', '1994-10-14', 'https://www.youtube.com/watch?v=s7EdQ4FqbhY', 'https://image.tmdb.org/t/p/w500/d5iIlFn5s0ImszYzBPb8JPIfbXD.jpg'),
    ('tt0167260', 'The Lord of the Rings: The Return of the King', '2003-12-17', 'https://www.youtube.com/watch?v=r5X-hFf6Bwo', 'https://image.tmdb.org/t/p/w500/rCzpDGLbOoPwLjy3OAm5NUPOTrC.jpg'),
    ('tt0120737', 'The Lord of the Rings: The Fellowship of the Ring', '2001-12-19', 'https://www.youtube.com/watch?v=V75dMMIW2B4', 'https://image.tmdb.org/t/p/w500/6oom5QYQ2yQTMJIbnvbkBL9cHo6.jpg'),
    ('tt0109830', 'Forrest Gump', '1994-07-06', 'https://www.youtube.com/watch?v=bLvqoHBptjg', 'https://image.tmdb.org/t/p/w500/arw2vcBveWOVZr6pxd9XTd1TdQa.jpg');

-- Insert genres for movies
INSERT INTO movie_genres
    (movie_id, genre)
VALUES
    -- The Shawshank Redemption
    (1, 'Drama'),
    (1, 'Crime'),
    -- The Godfather
    (2, 'Drama'),
    (2, 'Crime'),
    -- The Dark Knight
    (3, 'Action'),
    (3, 'Crime'),
    (3, 'Drama'),
    -- Schindler's List
    (4, 'Biography'),
    (4, 'Drama'),
    (4, 'History'),
    -- Pulp Fiction
    (5, 'Crime'),
    (5, 'Drama'),
    -- LOTR: Return of the King
    (6, 'Action'),
    (6, 'Adventure'),
    (6, 'Drama'),
    -- LOTR: Fellowship
    (7, 'Action'),
    (7, 'Adventure'),
    (7, 'Drama'),
    -- Forrest Gump
    (8, 'Drama'),
    (8, 'Romance');

-- Insert backdrops for movies
INSERT INTO movie_backdrops
    (movie_id, backdrop)
VALUES
    (1, 'https://image.tmdb.org/t/p/original/iNh3BivHyg5sQRPP1KOkzguEX0H.jpg'),
    (1, 'https://image.tmdb.org/t/p/original/l6hQWH9eDksNJNiXWYRkWqikOdu.jpg'),
    (2, 'https://image.tmdb.org/t/p/original/6xKCYgH16UuwEGAyroLU6p8HLIn.jpg'),
    (2, 'https://image.tmdb.org/t/p/original/avedvodAZUcwqevBfm8p4G2NziQ.jpg'),
    (3, 'https://image.tmdb.org/t/p/original/hqkIcbrOHL86UncnHIsHVcVmzue.jpg'),
    (3, 'https://image.tmdb.org/t/p/original/dqK9Hag1054tghRQSqLSfrkvQnA.jpg'),
    (4, 'https://image.tmdb.org/t/p/original/vI3aUGTuRRdM7J78KIdW98LdxE5.jpg'),
    (5, 'https://image.tmdb.org/t/p/original/4cDFJr4HnXN5AdPw4AKrmLlMWdO.jpg'),
    (6, 'https://image.tmdb.org/t/p/original/2u7zbn8EudG6kLlBzUYqP8RyFU4.jpg'),
    (7, 'https://image.tmdb.org/t/p/original/x2RS3uTcsJJ9IfjNPcgDmukoEcQ.jpg'),
    (8, 'https://image.tmdb.org/t/p/original/7c9UVPPYSwSzOsL96eKcSj3WqIY.jpg');

-- Insert sample reviews
INSERT INTO reviews
    (body, movie_id)
VALUES
    ('One of the greatest films ever made. A masterpiece of storytelling and character development.', 1),
    ('Tim Robbins and Morgan Freeman deliver outstanding performances in this emotionally powerful drama.', 1),
    ('An absolute classic that redefined the crime genre. Marlon Brando''s performance is legendary.', 2),
    ('The cinematography and direction are flawless. A true work of art.', 2),
    ('Christopher Nolan created a masterpiece with incredible action sequences and deep psychological themes.', 3),
    ('Heath Ledger''s Joker is one of the most iconic villains in cinema history.', 3),
    ('A harrowing and important film that everyone should see. Spielberg''s direction is exceptional.', 4),
    ('Liam Neeson gives a powerful performance in this historical drama.', 4),
    ('Tarantino''s non-linear narrative and sharp dialogue make this film unforgettable.', 5),
    ('John Travolta and Samuel L. Jackson have incredible chemistry throughout the film.', 5),
    ('The epic conclusion to the trilogy is visually stunning and emotionally satisfying.', 6),
    ('Peter Jackson created a perfect ending to this incredible fantasy saga.', 6),
    ('The beginning of an epic journey. The world-building is absolutely incredible.', 7),
    ('Elijah Wood and Ian McKellen bring these beloved characters to life perfectly.', 7),
    ('Tom Hanks delivers one of his best performances in this heartwarming and funny film.', 8),
    ('A beautiful story about life, love, and the unexpected turns our journey can take.', 8);