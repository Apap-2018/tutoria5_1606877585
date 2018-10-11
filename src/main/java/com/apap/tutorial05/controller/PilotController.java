package com.apap.tutorial05.controller;

import com.apap.tutorial05.model.PilotModel;
import com.apap.tutorial05.service.PilotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/**
 * Controller class for mapping that related to Pilot
 */
@Controller
public class PilotController {
    @Autowired
    private PilotService pilotService;

    @RequestMapping("/")
    private String home(){
        return "home";
    }

    @RequestMapping(value = "/pilot/add",method = RequestMethod.GET)
    private String add(Model model){
        model.addAttribute("pilot",new PilotModel());
        return "addPilot";
    }


    @RequestMapping(value = "/pilot/add",method = RequestMethod.POST)
    private String addPilotSubmit(@ModelAttribute PilotModel pilotModel){
        pilotService.addPilot(pilotModel);
        return "add";
    }

    @RequestMapping(value="/pilot/view",method = RequestMethod.GET)
    private String view(@RequestParam("licenseNumber")String licenseNumber, Model model){
        PilotModel pilotModel = pilotService.getPilotDetailByLicenseNumber(licenseNumber);
        model.addAttribute("pilot",pilotModel);
        return "view-pilot";
    }




    @RequestMapping(value = "/pilot/delete",method = RequestMethod.POST)
    private String deletePilot(@ModelAttribute PilotModel pilot){
        pilotService.delete(pilot);
        return "delete";
    }
    @RequestMapping(value = "/pilot/update/{licenseNumber}")
    private String update(@PathVariable(value = "licenseNumber") String licenseNumber,Model model){
        PilotModel pilot = pilotService.getPilotDetailByLicenseNumber(licenseNumber);
        model.addAttribute("pilot",pilot);
        return "update-pilot";


    }

    @RequestMapping(value = "/pilot/update",method = RequestMethod.POST)
    private String updateData(@ModelAttribute PilotModel pilotModel){
        pilotService.addPilot(pilotModel);
        return "update-success";

    }


}
