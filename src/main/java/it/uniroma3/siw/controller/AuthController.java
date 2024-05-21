package it.uniroma3.siw.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import it.uniroma3.siw.model.Credentials;
import it.uniroma3.siw.model.Customer;
import it.uniroma3.siw.model.Developer;
import it.uniroma3.siw.service.CredentialsService;
import it.uniroma3.siw.service.UserService;
import jakarta.validation.Valid;

@Controller 
public class AuthController {
	
	private final String UPLOAD_DIR = "./static/images";
	@Autowired
	private CredentialsService credentialsService;
	
	@Autowired
	private UserService userService;
	

	@GetMapping(value = "/register") 
	public String showRegisterForm (Model model) {
		model.addAttribute("credentials", new Credentials());
		return "formRegisterUser";
	}
	@PostMapping(value = "/register")
	public String showSpecificRoleRegister(Model model, @ModelAttribute("credentials") Credentials credentials) {
		switch(credentials.getRole()) {
		case "DEVELOPER":
			model.addAttribute("developer", new Developer());
			model.addAttribute("credentials", credentials);
			return "register/developer";
		case "CUSTOMER":
			model.addAttribute("customer", new Customer());
			model.addAttribute("credentials", credentials);
			return "register/customer";
		default:
			model.addAttribute("Error", "This role is not allowed ");
			return "register";
		}
	}
	
	@GetMapping("/register/developer")
	public String shwoRegisterDeveloper(Model model, @ModelAttribute("developer") Developer developer, @ModelAttribute("credentials") Credentials credentials,@RequestParam("file") MultipartFile file) {
		model.addAttribute("credentials", credentials);

        // check if file is empty
        if (file.isEmpty()) {
            model.addAttribute("message", "Please select a file to upload.");
            return "redirect:/";
        }

        // normalize the file path
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());

        // save the file on the local file system
        try {
            Path path = Paths.get(UPLOAD_DIR + fileName);
            Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            e.printStackTrace();
        }
        developer.setLogo("UPLOAD_DIR"+"fileName");
		model.addAttribute("developer", developer);
		return "formNewCustomer";
	}
	
	@PostMapping("register/developer")
	public String registerDeveloper (@Valid @ModelAttribute("developer") Developer dev,
            BindingResult userBindingResult, @Valid
            @ModelAttribute("credentials") Credentials credentials,
            BindingResult credentialsBindingResult,
            Model model) {
		
		if(!userBindingResult.hasErrors() && !credentialsBindingResult.hasErrors()) {
			
			
            userService.saveUser(dev);
            credentials.setUser(dev);
            credentialsService.saveCredentials(credentials);
            model.addAttribute("dev", dev);
            return "registrationSuccessful";
        }
        return "/register/developer";
		
	}
	
	@GetMapping("/register/customer")
	public String shwoRegisterCustomer(Model model, @ModelAttribute("customer") Customer customer, @ModelAttribute("credentials") Credentials credentials,@RequestParam("file") MultipartFile file) {
		model.addAttribute("credentials", credentials);
		  // check if file is empty
        if (file.isEmpty()) {
            model.addAttribute("message", "Please select a file to upload.");
            return "redirect:/";
        }

        // normalize the file path
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());

        // save the file on the local file system
        try {
            Path path = Paths.get(UPLOAD_DIR + fileName);
            Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            e.printStackTrace();
        }
        customer.setProfilePic("UPLOAD_DIR"+"fileName");
		model.addAttribute("customer", customer);
		return "formNewDeveloper";
	}
	
	@PostMapping("register/customer")
	public String registerCustomer (@Valid @ModelAttribute("customer") Customer customer,
            BindingResult userBindingResult, @Valid
            @ModelAttribute("credentials") Credentials credentials,
            BindingResult credentialsBindingResult,
            Model model) {
		
		if(!userBindingResult.hasErrors() && !credentialsBindingResult.hasErrors()) {
            userService.saveUser(customer);
            credentials.setUser(customer);
            credentialsService.saveCredentials(credentials);
            model.addAttribute("customer", customer);
            return "registrationSuccessful";
        }
        return "register/customer";
		
	}
	
	

}
