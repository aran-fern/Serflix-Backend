package com.proyecto.serflix.service.MovieDatabase;

import com.proyecto.serflix.domain.Movie;
import com.proyecto.serflix.service.dto.MovieDatabase.*;
import org.springframework.stereotype.Service;
import retrofit2.Call;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
public class MovieDTOService {
    public static final String apiKey = "e9146e088c2bfd128d974ae6fe70bdf4";
    static MovieDTORepository apiService = MovieDTORepository.retrofit.create(MovieDTORepository.class);

    public static MovieDTO getMovie(int id){
        MovieDTO movie = null;
        Call<MovieDTO> callMovie = apiService.getMovie(id, apiKey);
        try {
            movie = callMovie.execute().body();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return movie;
    }
    /*public static List<Movie> getTopRated(){
        Call<MovieResponse> call = apiService.getTopRatedMovies(apiKey);
        List<Movie> moviesList = null;
        try {
            moviesList = call.execute().body().results;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return moviesList;
    }*/

    public static List<MovieDTO> getMostPopular(){
        List<MovieDTO> moviesDTO = null;
        Call<MovieDTOList> callMovies = apiService.getMostPopular(apiKey);
        try {
            moviesDTO = callMovies.execute().body().getMoviesDTO();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return moviesDTO;
    }


    public static List<com.proyecto.serflix.service.dto.MovieDatabase.Keyword> getMovieKeywords(int id){
        List<com.proyecto.serflix.service.dto.MovieDatabase.Keyword> keyWordsList = null;
        Call<KeywordList> callKeyWords = apiService.getMovieKeywords(id,apiKey);
        try {
            keyWordsList = callKeyWords.execute().body().getKeywords();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return keyWordsList;
    }
    public static MovieDTO getMovieInLang(int id, String lang){
        MovieDTO movie = null;
        Map<String, String> data = new HashMap<>();
        data.put("api_key", apiKey);
        data.put("language", lang);

        // simplified call to request the news with already initialized service
        Call<MovieDTO> callLang = apiService.getMovieInLang(id,data);
        try {
            movie = callLang.execute().body();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return movie;
    }

    public static Movie getMovieFromDto(MovieDTO d){
        Long id = new Long(d.getId());
        return new Movie(d.getTitle(), id);
    }

    public static Credits getMoviecredits(int id){
        Credits credits = null;
        Call<Credits> callCredits = apiService.getMovieCredits(id, apiKey);
        try {
            credits = callCredits.execute().body();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return credits;
    }
}
