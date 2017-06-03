package com.proyecto.serflix.service;

import com.proyecto.serflix.domain.Forecast;
import com.proyecto.serflix.domain.Movie;
import com.proyecto.serflix.domain.MovieRecomendation;
import com.proyecto.serflix.domain.Request;
import com.proyecto.serflix.domain.enumeration.Weather;
import com.proyecto.serflix.repository.MovieRecomendationRepository;
import com.proyecto.serflix.repository.MovieRepository;
import com.proyecto.serflix.service.MovieDatabase.DiscoverService;
import com.proyecto.serflix.service.MovieDatabase.MovieDTOService;
import com.proyecto.serflix.service.WeatherDatabase.WeatherDTOService;
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

    @Inject
    private WeatherDTOService weatherDTOService;

    @Inject
    private DiscoverService discoverService;

    private List<MovieDTO> movieList;

    public boolean generateMovieRecommendations(Request request){

        MovieDTOService movieDTOService = new MovieDTOService();
        Forecast forecast = weatherDTOService.getCurrentForecast(request.getLocation().getLatLon());

        switch (request.getCompany()){
            case ALONE:
                break;
            case PARTNER:
                if (!forecast.getWeather().equals(Weather.CLEAR)){
                    movieList = movieDTOService.getRainyFilms();
                }else{

                }
                break;
            case FAMILY:
                break;
            case FRIENDS:
                break;
            case ANOTHER_USER:
                //movieList = movieDTOService.getKidFilms();
                //"ANOTHER_USER" lo hacemos servir como si fuese con niños ya que no contemplamos otro usuario aún
                movieList = discoverService.getKidsMovies();
                break;
            default:
                movieList = movieDTOService.getMostPopular();
                break;
        }


        for(MovieDTO movieDTO : movieList){
            String description = movieDTO.getOverview();
            //CUTRE!!!! VER COMO ARREGLAR PARA QUE PERMITA SINPOSIS DE MAS DE 255 CARCTERES!!!!!
            if (description.length() > 244){
                description = description.substring(0,244);
            }
            String tags = "";
            if (movieDTO.getId() != null){
                List<Keyword> keyWordsList = movieDTOService.getMovieKeywords(movieDTO.getId());

            if (keyWordsList != null && keyWordsList.size() > 0){
                for (int i = 0; i < 5; i++) {
                    if (keyWordsList.size() > i){
                        if (i != 0){
                            tags += ", "+keyWordsList.get(i).getName();
                        }else{
                            tags += keyWordsList.get(i).getName();
                        }
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
        }
        return true;
    }
}
