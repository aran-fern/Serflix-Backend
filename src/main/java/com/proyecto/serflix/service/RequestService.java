package com.proyecto.serflix.service;

import com.proyecto.serflix.domain.Request;
import com.proyecto.serflix.repository.RequestRepository;
import org.springframework.stereotype.Service;

import javax.inject.Inject;

@Service
public class RequestService {
    @Inject
    private RequestRepository requestRepository;

    public Request buildRequest(){
        Request request = new Request();
        return request;
    }
}
