package it.uniroma3.siw.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import it.uniroma3.siw.model.Credentials;
import it.uniroma3.siw.model.User;
import it.uniroma3.siw.repository.CredentialsRepository;
import it.uniroma3.siw.service.CredentialsService;
import it.uniroma3.siw.service.GameService;

@Controller
public class AdminController {

	@Autowired
	private CredentialsRepository credentialsRepository;
	
	@Autowired
	private CredentialsService credentialsService;
	
	@Autowired
	private GameService gameService;
	

	@GetMapping("edit/{id}")
	public String editCustomer(@PathVariable("id") Long id, Model model) {
		Optional<Credentials> user = credentialsRepository.findById(id);
		model.addAttribute("user", user);
		return "editCustomer.html";
	}

	
	@PostMapping("edit/{id}")
	public String editUser(@PathVariable("id") Long id, @ModelAttribute("user") User user) {
		credentialsService.updateUser(id, user);
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



