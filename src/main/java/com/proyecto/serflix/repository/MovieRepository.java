package com.proyecto.serflix.repository;

import com.proyecto.serflix.domain.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Spring Data JPA repository for the Movie entity.
 */
@SuppressWarnings("unused")
public interface MovieRepository extends JpaRepository<Movie,Long> {
}
