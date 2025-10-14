package dev.jcacosta.movies.domains;

import lombok.*;
import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "movies")
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String imdbId;

    @Column(nullable = false)
    private String title;

    private String releaseDate;
    private String trailerLink;
    private String poster;

    @ElementCollection
    @CollectionTable(name = "movie_genres", joinColumns = @JoinColumn(name = "movie_id"))
    @Column(name = "genre")
    private List<String> genres;

    @ElementCollection
    @CollectionTable(name = "movie_backdrops", joinColumns = @JoinColumn(name = "movie_id"))
    @Column(name = "backdrop")
    private List<String> backdrops;

    @OneToMany(mappedBy = "movie", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Review> reviews;
}
