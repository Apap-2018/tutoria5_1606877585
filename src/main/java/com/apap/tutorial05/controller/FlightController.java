package com.apap.tutorial05.controller;

import com.apap.tutorial05.model.FlightModel;
import com.apap.tutorial05.model.PilotModel;
import com.apap.tutorial05.service.FlightService;
import com.apap.tutorial05.service.PilotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Controller class for mapping that related to flight
 */
@Controller
public class FlightController {

    @Autowired
    private FlightService flightService;

    @Autowired
    private PilotService pilotService;

    @RequestMapping(value = "/flight/add/{licenseNumber}",method = RequestMethod.GET)
    private String add(@PathVariable(value = "licenseNumber")String licenseNumber, Model model){
        FlightModel flightModel = new FlightModel();
        PilotModel pilotModel = pilotService.getPilotDetailByLicenseNumber(licenseNumber);
        flightModel.setPilot(pilotModel);
        model.addAttribute("pilot",pilotModel);
        model.addAttribute("flight",flightModel);
        return "addFlight";
    }

    @RequestMapping(value="/flight/add/{licenseNumber}", params={"add"})
    public String addRow(@ModelAttribute PilotModel pilot, BindingResult bindingResult, Model model) {
        if (pilot.getPilotFlight()==null)
            pilot.setPilotFlight(new ArrayList<>());
        pilot.getPilotFlight().add(new FlightModel());
        model.addAttribute("pilot", pilot);
        return "addFlight";
    }
    @RequestMapping(value = "/flight/add/{licenseNumber}", params={"submit"}, method = RequestMethod.POST)
    private String addFlightSubmit(@ModelAttribute PilotModel pilot) {
        PilotModel thisPilot = pilotService.getPilotDetailByLicenseNumber(pilot.getLicenseNumber());
        for (FlightModel flight:pilot.getPilotFlight()) {
            flight.setPilot(thisPilot);
            flightService.addFlight(flight);
        }
        return "add";
    }

    @RequestMapping(value = "/flight/delete",method = RequestMethod.POST)
    private String deleteFlight(@ModelAttribute PilotModel pilot,Model model){
        for (FlightModel flightModel : pilot.getPilotFlight()){
            flightService.deleteFlightById(flightModel.getId());
        }
        return "delete";
    }
    @RequestMapping("/flight/update/{id}")
    private String update(@PathVariable(name = "id")String licenseNumber,@ModelAttribute FlightModel flightModel , Model model){
        PilotModel pilotModel = pilotService.getPilotDetailByLicenseNumber(licenseNumber);
        flightModel.setPilot(pilotModel);
        model.addAttribute("flight",flightModel);
        return "update-flight";

    }
    @RequestMapping("/flight/update")
    private String updateFlightData(@ModelAttribute FlightModel flightModel){
        flightService.addFlight(flightModel);
        return "update-success";
    }
    @RequestMapping("/flight/view")
    private String viewflight(@RequestParam(name = "flightNumber")String flightNumber,Model model){
        List<FlightModel> flights = flightService.getFlighDetailByLicenseNumer(flightNumber);
        model.addAttribute("flights",flights);
        return "view-flight";
    }




}
