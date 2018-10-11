package com.apap.tutorial05.service;

import com.apap.tutorial05.model.PilotModel;

public interface PilotService {
    PilotModel getPilotDetailByLicenseNumber(String licenseNumber);
    void addPilot(PilotModel pilotModel);
    void delete(PilotModel pilotModel);
    PilotModel getPilotDetailByLicenseNumber(long id);

}
