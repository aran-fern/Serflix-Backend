package com.proyecto.serflix.service;

import com.proyecto.serflix.domain.Movie;
import com.proyecto.serflix.domain.MovieRecomendation;
import com.proyecto.serflix.domain.Request;
import com.proyecto.serflix.repository.MovieRecomendationRepository;
import com.proyecto.serflix.repository.MovieRepository;
import com.proyecto.serflix.service.MovieDatabase.MovieDTOService;
import com.proyecto.serflix.service.dto.MovieDatabase.MovieDTO;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.List;

@Service
public class RecommendationEngine {
    @Inject
    private MovieRecomendationRepository movieRecomendationRepository;

    @Inject
    private MovieRepository movieRepository;

    public boolean generateMovieRecommendations(Request request){
        MovieDTOService movieDTOService = new MovieDTOService();
        List<MovieDTO> movieList = movieDTOService.getMostPopular();

        for(MovieDTO movieDTO : movieList){
            String description = movieDTO.getOverview();
            //CUTRE!!!! VER COMO ARREGLAR PARA QUE PERMITA SINPOSIS DE MAS DE 255 CARCTERES!!!!!
            if (description.length() > 244){
                description = description.substring(0,244);
            }
            Movie movie = new Movie(movieDTO.getTitle(), Long.valueOf(movieDTO.getId()), movieDTO.getPosterPath(), "Cast", "Tags", description, movieDTO.getReleaseDate());
            movieRepository.save(movie);
            MovieRecomendation recomendation = new MovieRecomendation(null, movie, request, null);
            movieRecomendationRepository.save(recomendation);
        }
        return true;
    }
}