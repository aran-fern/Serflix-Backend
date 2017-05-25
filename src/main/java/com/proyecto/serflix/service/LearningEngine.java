package com.proyecto.serflix.service;

import com.proyecto.serflix.domain.MovieRecomendation;
import com.proyecto.serflix.service.MovieDatabase.MovieDTOService;
import com.proyecto.serflix.service.dto.MovieDatabase.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.lang.Math.toIntExact;

@Service
public class LearningEngine {
    @Autowired
    private MovieDTOService movieDTOService;

    public void learnFromRecommendation(MovieRecomendation recommendation){
        //Get all relevant data from movie that we'll need
        int movieId = toIntExact(recommendation.getMovieDTO().getIdExternalApi());
        MovieDTO movie = movieDTOService.getMovie(movieId);
        String title = movie.getTitle();
        List<ProductionCompany> productionCompanies = movie.getProductionCompanies();
        List<ProductionCountry> productionCountries = movie.getProductionCountries();
        List<Genre> genres = movie.getGenres();
        List<Keyword> keywords = movieDTOService.getMovieKeywords(movieId);
        String releaseYear = movie.getReleaseDate().substring(0,4);
        Credits credits = movieDTOService.getMoviecredits(movieId);
        List<Cast> cast = credits.getCast();
        List<Crew> crew = credits.getCrew();

        //Update or insert each data gathered before and the movie as well into the user's preferences (add or take X points)
        int points = 0;
        switch (recommendation.getRecomendationResult()){
            case ACCEPTED:
                points = 5;
                break;
            case PRESELECTED:
                points = 2;
                break;
            case REJECTED:
                points = -5;
                break;
            case WATCHED_DISLIKED:
                points = -7;
                break;
            case WATCHED_LIKED:
                points = 7;
                break;
        }
    }
}
