package it.uniroma3.siw.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import it.uniroma3.siw.model.Customer;
import it.uniroma3.siw.model.Developer;
import it.uniroma3.siw.model.Game;
import it.uniroma3.siw.repository.CredentialsRepository;
import it.uniroma3.siw.service.CredentialsService;
import it.uniroma3.siw.service.CustomerService;
import it.uniroma3.siw.service.DeveloperService;
import it.uniroma3.siw.service.GameService;
import jakarta.validation.Valid;



@Controller
public class AdminController {

	@Autowired
	private CredentialsRepository credentialsRepository;
	
	@Autowired
	private CredentialsService credentialsService;
	
	@Autowired
	private DeveloperService developerService;
	
	@Autowired
	private CustomerService customerService;
	
	@Autowired
	private GameService gameService;
	

	
	@GetMapping("/edit/{id}")
	public String editDeveloper(@PathVariable("id") Long id,Model model) {
		model.addAttribute("user", credentialsRepository.findById(id).get().getUser());
		model.addAttribute("id",id);
		if(credentialsRepository.findById(id).get().getRole() == "Developer")
		return "editDeveloper";
		else
			return "editCustomer";
	}
	
	
	@PostMapping("/editDeveloper/{id}")
	public String editDeveloper( @ModelAttribute("user") Developer dev, @PathVariable("id") Long id ) {
		developerService.updateDeveloper(id, dev);
		return "redirect:/myPage";
	}
	
	@PostMapping("/editCustomer/{id}")
	public String editCustomer( @ModelAttribute("user") Customer c, @PathVariable("id") Long id ) {
		customerService.updateCustomer(id, c);
		return "redirect:/myPage";
	}
	
	@PostMapping("/editGame/{id}")
	public String editGame(@Valid @ModelAttribute("game") Game g,  @PathVariable("id") Long id ) {
		gameService.updateGame(id,g);
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



