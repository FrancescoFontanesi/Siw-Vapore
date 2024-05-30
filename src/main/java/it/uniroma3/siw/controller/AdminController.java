package it.uniroma3.siw.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import it.uniroma3.siw.repository.CustomerRepository;
import it.uniroma3.siw.repository.DeveloperRepository;

@Controller
public class AdminController {
	
	
	@Autowired
	CustomerRepository customerRepository;
	
	
	
	@Autowired
	DeveloperRepository developerRepository;
	
	@GetMapping("/admin")
	public String getAdminIndex(Model model) {
		model.addAttribute("customers",customerRepository.findAll());
		model.addAttribute("developers", developerRepository.findAll());
		return "admin.html";
	}

}
