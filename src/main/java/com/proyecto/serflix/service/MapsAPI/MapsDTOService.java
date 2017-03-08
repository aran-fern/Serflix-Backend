package com.proyecto.serflix.service.MapsAPI;

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


@Service
public class MapsDTOService {
    public static final String apiKey = "AIzaSyBIsWoDLus9G4yQespRGvvy8_dZeOnw71c";
    static MapsDTORepository apiService = MapsDTORepository.retrofit.create(MapsDTORepository.class);

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

//    public static Movie getMovieFromDto(MovieDTO d){
//        Long id = new Long(d.getId());
//        return new Movie(d.getTitle(), id);
//    }
}
