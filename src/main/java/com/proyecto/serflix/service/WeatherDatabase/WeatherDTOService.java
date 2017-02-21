package com.proyecto.serflix.service.WeatherDatabase;

import com.proyecto.serflix.service.dto.WeatherDatabase.Currently;
import retrofit2.Call;

import java.io.IOException;

public class WeatherDTOService {
    public static final String apiKey = "b663aac760fab18d52b433a1d2c84a5e";
    static WeatherDTORepository apiService = WeatherDTORepository.retrofit.create(WeatherDTORepository.class);

    //https://api.darksky.net/forecast/b663aac760fab18d52b433a1d2c84a5e/37.8267,-122.4233?exclude=minutely,flags

    public static Currently getCurrentlyWeather(double lat, double lon){
        Currently currently = null;
        Call<Currently> currentlyCall = apiService.getIcon(lat, lon, apiKey);
        try{
            currently = currentlyCall.execute().body();
        }catch (IOException e){
            e.printStackTrace();
        }
        return currently;
    }
}
