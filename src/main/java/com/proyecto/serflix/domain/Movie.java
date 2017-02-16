package com.proyecto.serflix.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A Movie.
 */
@Entity
@Table(name = "movie")
public class Movie implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "id_external_api")
    private Long idExternalApi;

    @OneToMany(mappedBy = "movieDTO")
    @JsonIgnore
    private Set<MovieRecomendation> movieRecomendations = new HashSet<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public Movie name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getIdExternalApi() {
        return idExternalApi;
    }

    public Movie idExternalApi(Long idExternalApi) {
        this.idExternalApi = idExternalApi;
        return this;
    }

    public void setIdExternalApi(Long idExternalApi) {
        this.idExternalApi = idExternalApi;
    }

    public Set<MovieRecomendation> getMovieRecomendations() {
        return movieRecomendations;
    }

    public Movie movieRecomendations(Set<MovieRecomendation> movieRecomendations) {
        this.movieRecomendations = movieRecomendations;
        return this;
    }

    public Movie addMovieRecomendation(MovieRecomendation movieRecomendation) {
        movieRecomendations.add(movieRecomendation);
        movieRecomendation.setMovieDTO(this);
        return this;
    }

    public Movie removeMovieRecomendation(MovieRecomendation movieRecomendation) {
        movieRecomendations.remove(movieRecomendation);
        movieRecomendation.setMovieDTO(null);
        return this;
    }

    public void setMovieRecomendations(Set<MovieRecomendation> movieRecomendations) {
        this.movieRecomendations = movieRecomendations;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Movie movie = (Movie) o;
        if (movie.id == null || id == null) {
            return false;
        }
        return Objects.equals(id, movie.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Movie{" +
            "id=" + id +
            ", name='" + name + "'" +
            ", idExternalApi='" + idExternalApi + "'" +
            '}';
    }
}
