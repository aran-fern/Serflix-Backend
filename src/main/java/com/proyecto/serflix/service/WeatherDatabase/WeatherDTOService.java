package com.proyecto.serflix.service.WeatherDatabase;


import com.proyecto.serflix.service.dto.WeatherDatabase.LocationDTO;
import com.proyecto.serflix.service.dto.WeatherDatabase.WeatherData;
import org.springframework.stereotype.Service;
import retrofit2.Call;

import java.io.IOException;

@Service
public class WeatherDTOService {
    public static final String apiKey = "b663aac760fab18d52b433a1d2c84a5e";
    static WeatherDTORepository apiService = WeatherDTORepository.retrofit.create(WeatherDTORepository.class);

    //https://api.darksky.net/forecast/b663aac760fab18d52b433a1d2c84a5e/37.8267,-122.4233?exclude=minutely,flags

    public WeatherData getWeatherData(String location){
        WeatherData weatherData = null;
        Call<WeatherData> weatherDataCall = apiService.getIcon(apiKey, location);
        try{
            weatherData = weatherDataCall.execute().body();
        }catch (IOException e){
            e.printStackTrace();
        }
        return weatherData;
    }
}
