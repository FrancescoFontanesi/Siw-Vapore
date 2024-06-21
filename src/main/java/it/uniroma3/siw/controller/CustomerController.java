package it.uniroma3.siw.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import it.uniroma3.siw.service.CustomerService;
import it.uniroma3.siw.validator.GameValidator;

@Controller
public class CustomerController {
	
	
	@Autowired
	private CustomerService customerService;
	
	
	
	@GetMapping("/buyGames")
    public String buyGames(Authentication auth, Model model) {
        boolean success = customerService.buyGames(auth.getName());
        if (success) {
            model.addAttribute("message", "Purchase successful!");
            return "redirect:/myPage";
        } else {
            model.addAttribute("message", "Not enough funds to complete the purchase.");
            return "cart";
        }
    }
	
	
	@PostMapping("/addFounds")
	public String addFounds(@RequestParam("amount") double amount,Authentication auth, Model model) {
		customerService.addFunds(auth.getName(), amount);
		return "redirect:/myPage";
	} 
}
