package com.proyecto.serflix.service.MovieDatabase;

import com.proyecto.serflix.domain.Movie;
import com.proyecto.serflix.service.dto.MovieDatabase.Genre;
import com.proyecto.serflix.service.dto.MovieDatabase.GenreList;
import com.proyecto.serflix.service.dto.MovieDatabase.KeywordList;
import com.proyecto.serflix.service.dto.MovieDatabase.MovieDTO;
import javassist.compiler.ast.Keyword;
import retrofit2.Call;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    public static List<Keyword> getMovieKeywordS(int id){
        List<Keyword> keyWordsList = null;
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
    public static List<Genre> getGenres(){
        Call<GenreList> call = apiService.getGenres(apiKey);
        GenreList genresList = null;
        try {
            genresList = call.execute().body();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return genresList.getGenres();
    }

    public static Movie getMovieFromDto(MovieDTO d){
        Long id = new Long(d.getId());
        return new Movie(d.getTitle(), id);
    }
}
