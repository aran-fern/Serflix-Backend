package com.proyecto.serflix.service.MovieDatabase;

import com.proyecto.serflix.service.dto.MovieDatabase.GenreList;
import com.proyecto.serflix.service.dto.MovieDatabase.KeywordList;
import com.proyecto.serflix.service.dto.MovieDatabase.MovieDTO;
import com.proyecto.serflix.service.dto.MovieDatabase.MovieDTOList;
import org.springframework.stereotype.Repository;
import retrofit2.Call;
import retrofit2.GsonConverterFactory;
import retrofit2.Retrofit;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

import java.util.List;
import java.util.Map;

@Repository
public interface MovieDTORepository {
    @GET("movie/top_rated")
    Call<MovieDTOList> getTopRatedMovies(@Query("api_key") String apiKey);

    @GET("movie/{movie_id}/keywords")
    Call<KeywordList> getMovieKeywords(@Path("movie_id") int id, @Query("api_key") String apiKey);

    @GET("movie/{movie_id}")
    Call<MovieDTO> getMovie(@Path("movie_id") int id, @Query("api_key") String apiKey);

    @GET("movie/popular")
    Call<MovieDTOList> getMostPopular(@Query("api_key") String apiKey);

    @GET("movie/{movie_id}")
    Call<MovieDTO> getMovieInLang(@Path("movie_id") int id, @QueryMap Map<String, String> options);

    @GET("genre/movie/list")
    Call<GenreList> getGenres(@Query("api_key") String apikey);

    public static String url = "https://api.themoviedb.org/3/";
    public static final Retrofit retrofit = new Retrofit.Builder()
        .baseUrl(url)
        .addConverterFactory(GsonConverterFactory.create())
        .build();
}
