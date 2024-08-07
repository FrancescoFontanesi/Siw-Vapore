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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import it.uniroma3.siw.model.Credentials;
import it.uniroma3.siw.model.Customer;
import it.uniroma3.siw.model.Developer;
import it.uniroma3.siw.model.User;
import it.uniroma3.siw.repository.CredentialsRepository;
import it.uniroma3.siw.service.AdminService;
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
		@Autowired
		private AdminService adminService;
		
		
		 // Directory where profile images will be saved
	    private static String UPLOADED_FOLDER_C = "src/main/resources/static/images/profili/";

	    // Directory where profile images will be saved
	    private static String UPLOADED_FOLDER_D = "src/main/resources/static/images/sviluppatori/";

		
		
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
	    public String showSpecificRoleRegister(Model model, @Valid @ModelAttribute("credentials") Credentials credentials, BindingResult result, RedirectAttributes redirectAttributes) {
	       
	    	this.credentialsValidator.validate(credentials, result);
	    	if (result.hasErrors()) {
			    redirectAttributes.addAttribute("error", true);
	            return "redirect:/registration";
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
		public String showRegisterDeveloper(Model model, @ModelAttribute("developer") Developer developer, @ModelAttribute("credentials") Credentials credentials) {
			model.addAttribute("credentials", credentials);
			model.addAttribute("developer", developer);
			System.out.println(developer.toString());
			return "formRegisterDeveloper";
		}
		
		@PostMapping("/formRegisterDeveloper")
		public String registerDeveloper (@Valid @ModelAttribute("developer") Developer developer,
	            BindingResult userBindingResult, @Valid
	            @ModelAttribute("credentials") Credentials credentials,
	            BindingResult credentialsBindingResult,@RequestParam("file") MultipartFile file,
	            Model model) {
			
			 if (!file.isEmpty()) {
	             try {
	                 byte[] bytes = file.getBytes();
	                 Path path = Paths.get(UPLOADED_FOLDER_D + file.getOriginalFilename());
	                 Files.write(path, bytes);
	                 developer.setLogo("/images/sviluppatori/" + file.getOriginalFilename() );
	             } catch (IOException e) {
	                 e.printStackTrace();

	                 model.addAttribute("message", "Failed to upload image");
	                 return "formRegisterCustomer";
	             }
	         }
			
			if(!userBindingResult.hasErrors() && !credentialsBindingResult.hasErrors()) {
	            userService.saveDev(developer);
	            credentials.setUser(developer);
	            credentialsService.saveCredentials(credentials);
	            model.addAttribute("developer", developer);
	            return "redirect:/login";
	        }
			
	        return "formRegisterDeveloper";
			
		}
		
		@GetMapping("/formRegisterCustomer")
		public String showRegisterCustomer(Model model, @ModelAttribute("customer") Customer customer, @ModelAttribute("credentials") Credentials credentials) {
			model.addAttribute("credentials", credentials);
			model.addAttribute("customer", customer);
			System.out.println(customer.toString());
			return "formRegisterCustomer";
		}
		
		@PostMapping("/formRegisterCustomer")
		public String registerCustomer (@Valid @ModelAttribute("customer") Customer customer,
	            BindingResult userBindingResult, @Valid
	            @ModelAttribute("credentials") Credentials credentials,
	            BindingResult credentialsBindingResult,@RequestParam("file") MultipartFile file,
	            Model model) {
			

			 if (!file.isEmpty()) {
	             try {
	                 byte[] bytes = file.getBytes();
	                 Path path = Paths.get(UPLOADED_FOLDER_C + file.getOriginalFilename());
	                 Files.write(path, bytes);
	                 customer.setProfilePic("/images/profili/" + file.getOriginalFilename() );
	             } catch (IOException e) {
	                 e.printStackTrace();

	                 model.addAttribute("message", "Failed to upload image");
	                 return "formRegisterCustomer";
	             }
	         }
			 
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
		    public String myPage(Authentication auth, Model model) {
		 
		    	
		        Optional<Credentials> c = credentialsRepository.findByEmail(auth.getName());
		        User u = c.get().getUser();
		        model.addAttribute("user", u );
		        
		        switch(c.get().getRole()) {
	            case "Developer":
	                return "developerMypage";
	            case "Customer":
	                return "customerMyPage";
	            case "ADMIN":
	            	adminService.loadUsers(model);
	            	return "admin";
	            	default :
	            		return "redirect:/login";
	          }
		    }
		
		
		
		
	
	}
