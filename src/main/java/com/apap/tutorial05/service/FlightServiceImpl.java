package com.apap.tutorial05.service;

import com.apap.tutorial05.model.FlightModel;
import com.apap.tutorial05.repository.FlightDb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@Service
@Transactional
public class FlightServiceImpl implements FlightService {
    @Autowired
    private FlightDb flightDb;

    @Override
    public void addFlight(FlightModel flight) {
        flightDb.save(flight);
    }

    @Override
    public void delete(FlightModel flightModel) {
        flightDb.delete(flightModel);
    }

    @Override
    public List<FlightModel> getFlighDetailByLicenseNumer(String flightNumber) {
        List<FlightModel> allFlight  = flightDb.findAll();
        List<FlightModel> target = new LinkedList<>();
        for (FlightModel flight : allFlight){
            if (flight.getFlightNumber().equalsIgnoreCase(flightNumber))
                target.add(flight);
        }
        return target;
    }

    @Override
    public void deleteFlightById(long id) {
        flightDb.deleteById(id);
    }
}
