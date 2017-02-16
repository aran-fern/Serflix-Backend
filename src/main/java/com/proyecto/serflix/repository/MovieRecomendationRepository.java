package com.proyecto.serflix.repository;

import com.proyecto.serflix.domain.MovieRecomendation;

import org.springframework.data.jpa.repository.*;

import java.util.List;

/**
 * Spring Data JPA repository for the MovieRecomendation entity.
 */
@SuppressWarnings("unused")
public interface MovieRecomendationRepository extends JpaRepository<MovieRecomendation,Long> {

}
