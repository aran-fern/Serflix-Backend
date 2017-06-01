package com.proyecto.serflix.service;

import com.proyecto.serflix.domain.Forecast;
import com.proyecto.serflix.domain.Movie;
import com.proyecto.serflix.domain.MovieRecomendation;
import com.proyecto.serflix.domain.Request;
import com.proyecto.serflix.repository.MovieRecomendationRepository;
import com.proyecto.serflix.repository.MovieRepository;
import com.proyecto.serflix.service.MovieDatabase.MovieDTOService;
import com.proyecto.serflix.service.dto.MovieDatabase.Keyword;
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

    @Inject
    private MovieDTOService movieDTOService;

    public boolean generateMovieRecommendations(Request request){
        MovieDTOService movieDTOService = new MovieDTOService();

        //CONDITION FOR HORROR FILMS
        //CONDITION FOR KIDS FILMS

        List<MovieDTO> movieList = movieDTOService.getMostPopular();

        for(MovieDTO movieDTO : movieList){
            String description = movieDTO.getOverview();
            //CUTRE!!!! VER COMO ARREGLAR PARA QUE PERMITA SINPOSIS DE MAS DE 255 CARCTERES!!!!!
            if (description.length() > 244){
                description = description.substring(0,244);
            }
            String tags = "";
            List<Keyword> keyWordsList = movieDTOService.getMovieKeywords(movieDTO.getId());
            for (int i = 0; i < 5; i++) {
                if (keyWordsList.size() > i){
                    if (i != 0){
                        tags += ", "+keyWordsList.get(i).getName();
                    }else{
                        tags += keyWordsList.get(i).getName();
                    }
                }
            }
            Movie movie;
            if (movieRepository.findByName(movieDTO.getTitle()).size() > 0){
                movie = movieRepository.findByName(movieDTO.getTitle()).get(0);

            }else{
                movie = new Movie(movieDTO.getTitle(), Long.valueOf(movieDTO.getId()), movieDTO.getPosterPath(), "Cast", tags, description, movieDTO.getReleaseDate());
                movie.setIdExternalApi(Long.valueOf(movieDTO.getId()));
                movieRepository.save(movie);
            }
            MovieRecomendation recomendation = new MovieRecomendation(null, movie, request, null);
            movieRecomendationRepository.save(recomendation);
        }
        return true;
    }
}
