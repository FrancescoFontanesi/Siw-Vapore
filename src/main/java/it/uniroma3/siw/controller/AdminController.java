package it.uniroma3.siw.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import it.uniroma3.siw.model.Credentials;
import it.uniroma3.siw.repository.CredentialsRepository;
import it.uniroma3.siw.service.CredentialsService;

@Controller
public class AdminController {

	@Autowired
	private CredentialsRepository credentialsRepository;
	
	@Autowired
	private CredentialsService credentialsService;
	

	@GetMapping("myPage/edit/{id}")
	public String showEditUserForm(@PathVariable("id") Long id, Model model) {
		Optional<Credentials> user = credentialsRepository.findById(id);
		model.addAttribute("user", user);
		return "editUser";
	}

	/*
	 * @PostMapping("/edit/{id}") public String editUser(@PathVariable("id") Long
	 * id, User user) { userService.updateUser(id, user); return "redirect:/admin";
	 * }
	 */

	@GetMapping("myPage/delete/{id}") 
	public String deleteUser(@PathVariable("id") Long id){
		credentialsService.deleteCredentials(id); 
		return "redirect:/myPage"; 
	}

}



