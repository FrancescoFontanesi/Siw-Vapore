package it.uniroma3.siw.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import it.uniroma3.siw.model.Developer;
import it.uniroma3.siw.repository.CredentialsRepository;
import it.uniroma3.siw.service.CredentialsService;
import it.uniroma3.siw.service.DeveloperService;
import it.uniroma3.siw.service.GameService;



@Controller
public class AdminController {

	@Autowired
	private CredentialsRepository credentialsRepository;
	
	@Autowired
	private CredentialsService credentialsService;
	
	@Autowired
	private DeveloperService developerService;
	
	@Autowired
	private GameService gameService;
	

	
	@GetMapping("/editDeveloper/{id}")
	public String editDeveloper(@PathVariable("id") Long id,Model model) {
		model.addAttribute("developer", credentialsRepository.findById(id).get().getUser());
		model.addAttribute("id",id);
		return "editDeveloper";
	}
	
	
	@PostMapping("/editDeveloper/{id}")
	public String editDev( @ModelAttribute("developer") Developer dev, @PathVariable("id") Long id ) {
		developerService.updateDeveloper((Developer)credentialsRepository.findById(id).get().getUser(), dev);
		return "redirect:/myPage";
	}
	



	@GetMapping("myPage/delete/{id}") 
	public String deleteUser(@PathVariable("id") Long id){
		credentialsService.deleteCredentials(id); 
		return "redirect:/myPage"; 
	}
	
	@GetMapping("/game/{id}/delete")
    public String getGame(@PathVariable Long id) {
        gameService.deleteGame(id);
        return "redirect:/";
    }

}



