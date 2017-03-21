package com.proyecto.serflix.service;

import com.proyecto.serflix.domain.Request;
import com.proyecto.serflix.repository.RequestRepository;
import com.proyecto.serflix.service.MapsAPI.MapsDTOService;
import com.proyecto.serflix.web.rest.dto.RequestDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.inject.Inject;

@Service
public class RequestService {
    @Inject
    private RequestRepository requestRepository;

    @Autowired
    private MapsDTOService mapsDTOService;

    public Request buildRequest(RequestDTO requestFromAndroid){
        /*Type type = requestFromAndroid.getType();
        String name = type+" recommendation from "+requestFromAndroid.getCreationDate();
        Company company = requestFromAndroid.getCompany();
        ZonedDateTime viewDate =
            ZonedDateTime.ofInstant(requestFromAndroid.getViewDate().toInstant(), ZoneId.systemDefault());
        ZonedDateTime creationDate =
            ZonedDateTime.ofInstant(requestFromAndroid.getCreationDate().toInstant(), ZoneId.systemDefault());
        Location location = mapsDTOService;
        User userRequester = requestFromAndroid.getUserRequester();
        Set<Forecast> forecasts = //Get forecast de forecastService;
        Set<Preferences> //Get forecast de forecastService;
        Set<User> userGuests = ;
        Request request = new Request(type, name, viewDate, creationDate, company, userRequester, location,
            userGuests, forecasts, preferences);*/

        return new Request();
    }
}
