package it.uniroma3.siw.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartFile;

import it.uniroma3.siw.model.Credentials;
import it.uniroma3.siw.model.Customer;
import it.uniroma3.siw.model.Developer;
import it.uniroma3.siw.model.User;
import it.uniroma3.siw.repository.CredentialsRepository;
import it.uniroma3.siw.service.CredentialsService;
import it.uniroma3.siw.service.UserService;
import it.uniroma3.siw.validator.CredentialsValidator;
import jakarta.validation.Valid;


@Controller
@SessionAttributes("credentials")
public class AuthController {
	
	private final String UPLOAD_DIR = "./static/images";
	@Autowired
	private CredentialsValidator credentialsValidator;
	
	@Autowired
	private CredentialsService credentialsService;

	
	@Autowired
	private CredentialsRepository credentialsRepository;

	
	
	@Autowired
	private UserService userService;
	
	
	@GetMapping("/login")
	public String getLogin(Model model) {
		return "login.html";
	
	}
	


	@GetMapping("/registration")
    public String showRegisterForm(Model model) {
        model.addAttribute("credentials", new Credentials());
        return "registration";
    }

    @PostMapping("/registration")
    public String showSpecificRoleRegister(Model model, @Valid @ModelAttribute("credentials") Credentials credentials, BindingResult result) {
       
    	this.credentialsValidator.validate(credentials, result);
    	
    	System.out.println(credentials.toString());
    	System.out.println(result.toString());
    	if (result.hasErrors()) {
            return "registration";
        }
    	
    	switch(credentials.getRole()) {
            case "Developer":
                model.addAttribute("developer", new Developer());
                model.addAttribute("credentials", credentials);
                return "formRegisterDeveloper";
            case "Customer":
                model.addAttribute("customer", new Customer());
                model.addAttribute("credentials", credentials);
                return "formRegisterCustomer";
            default:
                model.addAttribute("error", "This role is not allowed");
                return "registration";
          }
    	
    }

	
	@GetMapping("/formRegisterDeveloper")
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
		System.out.println(developer.toString());
		return "formRegisterDeveloper";
	}
	
	@PostMapping("/formRegisterDeveloper")
	public String registerDeveloper (@Valid @ModelAttribute("developer") Developer dev,
            BindingResult userBindingResult, @Valid
            @ModelAttribute("credentials") Credentials credentials,
            BindingResult credentialsBindingResult,
            Model model) {
		
		if(!userBindingResult.hasErrors() && !credentialsBindingResult.hasErrors()) {
			
			
            userService.saveDev(dev);
            credentials.setUser(dev);
            credentialsService.saveCredentials(credentials);
            model.addAttribute("dev", dev);
            return "login";
        }
        return "formRegisterDeveloper";
		
	}
	
	@GetMapping("/formRegisterCustomer")
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
            Path path = Paths.get(UPLOAD_DIR +"/"+ fileName);
            Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            e.printStackTrace();
        }
        customer.setProfilePic("UPLOAD_DIR"+"fileName");
		model.addAttribute("customer", customer);
		System.out.println(customer.toString());
		return "formRegisterCustomer";
	}
	
	@PostMapping("/formRegisterCustomer")
	public String registerCustomer (@Valid @ModelAttribute("customer") Customer customer,
            BindingResult userBindingResult, @Valid
            @ModelAttribute("credentials") Credentials credentials,
            BindingResult credentialsBindingResult,
            Model model) {
		
		
		if(!userBindingResult.hasErrors() && !credentialsBindingResult.hasErrors()) {
            userService.saveCustomer(customer);
            credentials.setUser(customer);
            credentialsService.saveCredentials(credentials);
            model.addAttribute("customer", customer);
            return "redirect:/login";
        }
        return "formRegisterCustomer";
		
	}
	

	    @GetMapping("/myPage")
	    public String customerMyPage(Authentication authentication, Model model) {
	        String email = authentication.getName();
	        Optional<Credentials> c = credentialsRepository.findByEmail(email);
	        User u = c.get().getUser();
	        model.addAttribute("user", u );
	        
	        switch(c.get().getRole()) {
            case "Developer":
                return "developerMypage";
            case "Customer":
                return "customerMyPage";
            case "ADMIN":
            	return "adminIndex";
            	default :
            		return "login";
          }
	    }
	
	
	
	

}
