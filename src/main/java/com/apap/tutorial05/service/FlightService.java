package com.apap.tutorial05.service;

import com.apap.tutorial05.model.FlightModel;

import java.util.List;

public interface FlightService {

    void addFlight(FlightModel flight);
    void delete(FlightModel flightModel);
    void deleteFlightById(long id);
    List<FlightModel> getFlighDetailByLicenseNumer(String flightNumber);
}
