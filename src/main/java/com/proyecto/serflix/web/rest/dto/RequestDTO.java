package com.proyecto.serflix.web.rest.dto;

import com.proyecto.serflix.domain.enumeration.Company;
import com.proyecto.serflix.domain.enumeration.Type;
import com.proyecto.serflix.service.dto.WeatherDatabase.LocationDTO;

import java.util.Date;

public class RequestDTO {
    private Type type;
    private Date viewDate;
    private Date creationDate;
    private Company company;

    private UserTokenDTO userRequester;

    private LocationDTO location;

    public RequestDTO(Type type, Date viewDate, Date creationDate, Company company, UserTokenDTO userRequester, LocationDTO location) {
        this.type = type;
        this.viewDate = viewDate;
        this.creationDate = creationDate;
        this.company = company;
        this.userRequester = userRequester;
        this.location = location;
    }

    public RequestDTO() {
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public Date getViewDate() {
        return viewDate;
    }

    public void setViewDate(Date viewDate) {
        this.viewDate = viewDate;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public UserTokenDTO getUserRequester() {
        return userRequester;
    }

    public void setUserRequester(UserTokenDTO userRequester) {
        this.userRequester = userRequester;
    }

    public LocationDTO getLocation() {
        return location;
    }

    public void setLocation(LocationDTO location) {
        this.location = location;
    }

    @Override
    public String toString() {
        return "RequestDTO{" +
            "type=" + type +
            ", viewDate=" + viewDate +
            ", creationDate=" + creationDate +
            ", company=" + company +
            ", userRequester=" + userRequester +
            ", location=" + location +
            '}';
    }
}
