package com.proyecto.serflix.service;

import com.proyecto.serflix.domain.MovieRecomendation;
import com.proyecto.serflix.service.MovieDatabase.MovieDTOService;
import com.proyecto.serflix.service.dto.MovieDatabase.MovieDTO;
import org.springframework.stereotype.Service;

import static java.lang.Math.toIntExact;

@Service
public class LearningEngine {

    public void learnFromRecommendation(MovieRecomendation recommendation){
        MovieDTOService movieDTOService = new MovieDTOService();
        MovieDTO movie = movieDTOService.getMovie(toIntExact(recommendation.getMovieDTO().getIdExternalApi()));
    }
}
