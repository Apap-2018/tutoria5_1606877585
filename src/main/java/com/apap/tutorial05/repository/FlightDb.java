package com.apap.tutorial05.repository;

import com.apap.tutorial05.model.FlightModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FlightDb extends JpaRepository<FlightModel,Long> {


}
