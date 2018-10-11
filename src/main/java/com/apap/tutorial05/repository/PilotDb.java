package com.apap.tutorial05.repository;

import com.apap.tutorial05.model.PilotModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PilotDb extends JpaRepository<PilotModel,Long> {
    PilotModel findByLicenseNumber(String licenseNumber);
    PilotModel findPilotModelById(long id);
}
