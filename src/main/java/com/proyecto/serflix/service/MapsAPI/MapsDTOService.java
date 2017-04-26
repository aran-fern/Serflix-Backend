package com.proyecto.serflix.service.MapsAPI;

import com.proyecto.serflix.domain.Location;
import com.proyecto.serflix.domain.Movie;
import com.proyecto.serflix.service.dto.MapsAPI.AddressDTO;
import com.proyecto.serflix.service.dto.MovieDatabase.Genre;
import com.proyecto.serflix.service.dto.MovieDatabase.GenreList;
import com.proyecto.serflix.service.dto.MovieDatabase.KeywordList;
import com.proyecto.serflix.service.dto.MovieDatabase.MovieDTO;
import javassist.compiler.ast.Keyword;
import org.springframework.stereotype.Service;
import retrofit2.Call;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@Service
public class MapsDTOService {
    public static final String apiKey = "AIzaSyBIsWoDLus9G4yQespRGvvy8_dZeOnw71c";
    static MapsDTORepository apiService = MapsDTORepository.retrofit.create(MapsDTORepository.class);

    //No delete
    public AddressDTO getGeocode(String latlng){
        AddressDTO addressDTO = null;
        Call<AddressDTO> callGeocode = apiService.geocode(latlng, apiKey);
        try {
            addressDTO = callGeocode.execute().body();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return addressDTO;
    }

    public Location getLocation(String latlng){
        Location location = new Location();
        AddressDTO addressDTO = null;
        Call<AddressDTO> locationCall = apiService.geocode(latlng, apiKey);
        try{
            addressDTO = locationCall.execute().body();
            location.setCountry(addressDTO
                .getResults()
                .stream()
                .filter(result -> result.getTypes()
                    .contains("country")).collect(Collectors.toList())
                .get(0).getFormattedAddress());
            location.setState(addressDTO
                .getResults()
                .stream()
                .filter(result -> result.getTypes()
                    .contains("administrative_area_level_1")).collect(Collectors.toList())
                .get(0).getFormattedAddress());
            location.setCity(addressDTO
                .getResults()
                .stream()
                .filter(result -> result.getTypes()
                    .contains("administrative_area_level_2")).collect(Collectors.toList())
                .get(0).getFormattedAddress());
        }catch (IOException e) {
            e.printStackTrace();
        }
        return location;
    }
}
