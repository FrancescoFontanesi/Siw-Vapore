package it.uniroma3.siw.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

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
	
    private static String UPLOADED_FOLDER = "src/main/resources/static/images/newGame/";

	
	@GetMapping("/edit/{id}")
	public String editDeveloper(@PathVariable("id") Long id,Model model) {
		model.addAttribute("user", credentialsRepository.findById(id).get().getUser());
		model.addAttribute("id",id);
		if(credentialsRepository.findById(id).get().getRole().equals("Developer"))
		return "editDeveloper";
		else
		return "editCustomer";
	}
	
	
	@PostMapping("/editDeveloper/{id}")
	public String editDeveloper( @ModelAttribute("user") Developer dev, @PathVariable("id") Long id , @RequestParam MultipartFile file) {
		developerService.updateDeveloper(id, dev,file);
		return "redirect:/myPage";
	}
	
	@PostMapping("/editCustomer/{id}")
	public String editCustomer( @ModelAttribute("user") Customer c, @PathVariable("id") Long id , @RequestParam MultipartFile file) {
		customerService.updateCustomer(id, c,file);
		return "redirect:/myPage";
	}
	
	@PostMapping("/editGame/{id}")
	public String editGame(Model m,@Valid @ModelAttribute("game") Game g,  @PathVariable("id") Long id,
			@RequestParam("file") MultipartFile file,@RequestParam("additionalFiles") List<MultipartFile> additionalFiles) {
		 
		gameService.updateGame(id,g,file,additionalFiles);
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



