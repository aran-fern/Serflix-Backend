package com.proyecto.serflix.service.WeatherDatabase;

import com.proyecto.serflix.service.dto.WeatherDatabase.Currently;
import org.springframework.stereotype.Repository;
import retrofit2.Call;
import retrofit2.GsonConverterFactory;
import retrofit2.Retrofit;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

@Repository
public interface WeatherDTORepository {
    @GET("forecast/{lat},{lon}?exclude=minutely,flags")
    Call<Currently> getIcon(@Path("lat") double lat, @Path("lon") double lon, @Query("api_key") String apiKey);



    //URL ENTERA : https://api.darksky.net/forecast/b663aac760fab18d52b433a1d2c84a5e/37.8267,-122.4233?exclude=minutely,flags
    public static String url = "https://api.darksky.net/";
    public static final Retrofit retrofit = new Retrofit.Builder()
        .baseUrl(url)
        .addConverterFactory(GsonConverterFactory.create())
        .build();
}
