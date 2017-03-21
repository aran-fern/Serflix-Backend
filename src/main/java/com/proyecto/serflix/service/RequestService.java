package com.proyecto.serflix.service;

import com.proyecto.serflix.domain.Forecast;
import com.proyecto.serflix.domain.Location;
import com.proyecto.serflix.domain.Request;
import com.proyecto.serflix.domain.User;
import com.proyecto.serflix.domain.enumeration.Company;
import com.proyecto.serflix.domain.enumeration.Type;
import com.proyecto.serflix.repository.RequestRepository;
import com.proyecto.serflix.repository.UserRepository;
import com.proyecto.serflix.security.SecurityUtils;
import com.proyecto.serflix.service.MapsAPI.MapsDTOService;
import com.proyecto.serflix.service.WeatherDatabase.WeatherDTOService;
import com.proyecto.serflix.web.rest.dto.RequestDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.HashSet;
import java.util.Set;

@Service
public class RequestService {
    @Inject
    private RequestRepository requestRepository;

    @Inject
    private UserRepository userRepository;

    @Autowired
    private MapsDTOService mapsDTOService;

    @Autowired
    private WeatherDTOService weatherDTOService;

    public Request buildRequest(RequestDTO requestFromAndroid){
        //!!!!!!!!!!FALTA!!!!!!!!!
        //Get Location del servicio
        Location location = new Location();
        //

        Set<Forecast> forecasts = new HashSet<>();
        Type type = requestFromAndroid.getType();
        String name = type+" recommendation from "+requestFromAndroid.getCreationDate();
        Company company = requestFromAndroid.getCompany();
        ZonedDateTime viewDate =
            ZonedDateTime.ofInstant(requestFromAndroid.getViewDate().toInstant(), ZoneId.systemDefault());
        ZonedDateTime creationDate =
            ZonedDateTime.ofInstant(requestFromAndroid.getCreationDate().toInstant(), ZoneId.systemDefault());
        User userRequester = userRepository.findOneByLogin(SecurityUtils.getCurrentUserLogin()).get();
        if (viewDate.isBefore(ZonedDateTime.now().plusHours(3))){
            //Forecast currentForecast = weatherDTOService.getCurrentForecast(location);
            //forecasts.add(currentForecast);
        }

        Request request = new Request(type, name, viewDate, creationDate, company, userRequester, location, forecasts);
        return request;
    }
}
