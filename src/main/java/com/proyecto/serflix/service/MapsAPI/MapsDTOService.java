package com.proyecto.serflix.service.MapsAPI;

import com.proyecto.serflix.service.dto.MapsAPI.AddressDTO;
import org.springframework.stereotype.Service;
import retrofit2.Call;

import java.io.IOException;


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

    //Metodo devolver Location

//    public static Movie getMovieFromDto(MovieDTO d){
//        Long id = new Long(d.getId());
//        return new Movie(d.getTitle(), id);
//    }
}
