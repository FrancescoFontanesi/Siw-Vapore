package it.uniroma3.siw.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import it.uniroma3.siw.repository.GameRepository;

@Controller
public class HomeController {
	
	@Autowired
	public GameRepository gameRepository;

    @GetMapping(value = "/")
    public String index(Model model) {
    	model.addAttribute("games", gameRepository.findAll());
        return "index.html"; 
    }
}
