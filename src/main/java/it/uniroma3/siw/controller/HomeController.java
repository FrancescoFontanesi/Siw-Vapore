package it.uniroma3.siw.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import it.uniroma3.siw.model.Credentials;
import it.uniroma3.siw.model.User;
import it.uniroma3.siw.repository.CredentialsRepository;
import it.uniroma3.siw.repository.GameRepository;

@Controller
public class HomeController {
	
	@Autowired
	public GameRepository gameRepository;
	@Autowired
	private CredentialsRepository credentialsRepository;
	

    @GetMapping(value = "/")
    public String index(Model model) {
    	 model.addAttribute("games", gameRepository.findAll());

         Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
         if (authentication != null && authentication.isAuthenticated()) {
             Object principal = authentication.getPrincipal();
             if (principal instanceof UserDetails) {
                 UserDetails userDetails = (UserDetails) principal;
                 String email = userDetails.getUsername();
                 System.out.println("Authenticated user email: " + email);

                 Credentials credentials = credentialsRepository.findByEmail(email)
                     .orElseThrow(() -> new RuntimeException("User not found"));

                 User user = credentials.getUser();
                 System.out.println(user.toString());
                 model.addAttribute("userPrincipal", user);
             } else {
                 System.out.println("Principal is not an instance of UserDetails");
             }
         } else {
             model.addAttribute("userPrincipal", null);
             System.out.println("No authenticated user");
         }
         return "index";
     }
    
    @GetMapping("/cart")
	public String getCart(Model model) {
		return "cart.html";
	
	}
}
