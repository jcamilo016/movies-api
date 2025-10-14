-- Drop tables if they exist (for clean recreation)
DROP TABLE IF EXISTS movie_backdrops
CASCADE;
DROP TABLE IF EXISTS movie_genres
CASCADE;
DROP TABLE IF EXISTS reviews
CASCADE;
DROP TABLE IF EXISTS movies
CASCADE;

-- Create movies table
CREATE TABLE movies
(
    id BIGINT
    AUTO_INCREMENT PRIMARY KEY,
    imdb_id VARCHAR
    (20) UNIQUE NOT NULL,
    title VARCHAR
    (255) NOT NULL,
    release_date VARCHAR
    (20),
    trailer_link VARCHAR
    (500),
    poster VARCHAR
    (500)
);

    -- Create reviews table
    CREATE TABLE reviews
    (
        id BIGINT
        AUTO_INCREMENT PRIMARY KEY,
    body TEXT,
    movie_id BIGINT,
    FOREIGN KEY
        (movie_id) REFERENCES movies
        (id) ON
        DELETE CASCADE
);

        -- Create movie_genres table (for ElementCollection)
        CREATE TABLE movie_genres
        (
            movie_id BIGINT NOT NULL,
            genre VARCHAR(100) NOT NULL,
            FOREIGN KEY (movie_id) REFERENCES movies(id) ON DELETE CASCADE
        );

        -- Create movie_backdrops table (for ElementCollection)
        CREATE TABLE movie_backdrops
        (
            movie_id BIGINT NOT NULL,
            backdrop VARCHAR(500) NOT NULL,
            FOREIGN KEY (movie_id) REFERENCES movies(id) ON DELETE CASCADE
        );

        -- Create indexes for better performance
        CREATE INDEX idx_movies_imdb_id ON movies(imdb_id);
        CREATE INDEX idx_reviews_movie_id ON reviews(movie_id);
        CREATE INDEX idx_movie_genres_movie_id ON movie_genres(movie_id);
        CREATE INDEX idx_movie_backdrops_movie_id ON movie_backdrops(movie_id);