package controllers;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.covidtracker.services.CoronaVirusDataService;

@Controller

public class HomeController {
	@Autowired(required=true)
	CoronaVirusDataService coronaVirusDataService;
	
	
	@GetMapping("/")
	public String home(Model model) {
		model.addAttribute("locationStats",coronaVirusDataService.getAllStats());
		
		
		int sum =coronaVirusDataService.getAllStats().stream().mapToInt(sat->sat.getLatestTotalCases()).sum();
		int sumPre =  coronaVirusDataService.getAllStats().stream().mapToInt(sat->sat.getDifferenceFromPreDay()).sum(); 
		
		model.addAttribute("totalReportedCases", sum);
		model.addAttribute("today", sumPre);
		return "home";
	}

}
