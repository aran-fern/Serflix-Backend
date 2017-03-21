package com.proyecto.serflix.service.WeatherDatabase;


import com.proyecto.serflix.domain.Forecast;
import com.proyecto.serflix.domain.enumeration.Weather;
import com.proyecto.serflix.service.dto.WeatherDatabase.Currently;
import com.proyecto.serflix.service.dto.WeatherDatabase.WeatherData;
import org.springframework.stereotype.Service;
import retrofit2.Call;

import java.io.IOException;

@Service
public class WeatherDTOService {
    public static final String apiKey = "b663aac760fab18d52b433a1d2c84a5e";
    static WeatherDTORepository apiService = WeatherDTORepository.retrofit.create(WeatherDTORepository.class);

    //https://api.darksky.net/forecast/b663aac760fab18d52b433a1d2c84a5e/37.8267,-122.4233?exclude=minutely,flags

    //TODO
    //Crear getCurrentWeather, getHourWeather, getDayWeather (Forecast)

    public Forecast getCurrentForecast(String location) {
        Forecast forecast = new Forecast();
        WeatherData weatherData = null;
        //Double temperature, Weather weather
        Call<WeatherData> weatherDataCall = apiService.getWeatherData(apiKey, location);
        try {
            weatherData = weatherDataCall.execute().body();
            Currently currently = weatherData.getCurrently();
            forecast.setTemperature(currently.getTemperature());
            forecast.setWeather(getWeatherFromIcon(currently.getIcon()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return forecast;
    }

    public WeatherData getWeatherData(String location) {
        WeatherData weatherData = null;
        Call<WeatherData> weatherDataCall = apiService.getWeatherData(apiKey, location);
        try {
            weatherData = weatherDataCall.execute().body();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return weatherData;
    }

    public Weather getWeatherFromIcon(String icon) {
        switch (icon) {
            case "clear-day":
                return Weather.CLEAR;
            case "clear-night":
                return Weather.CLEAR;
            case "rain":
                return Weather.CLEAR;
            case "snow":
                return Weather.CLEAR;
            case "sleet":
                return Weather.CLEAR;
            case "wind":
                return Weather.CLEAR;
            case "fog":
                return Weather.CLEAR;
            case "partly-cloudy-day":
                return Weather.CLEAR;
            case "partly-cloudy-night":
                return Weather.CLEAR;
            default:
                return Weather.CLEAR;
        }
    }
}
