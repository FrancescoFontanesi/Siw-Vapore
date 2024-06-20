package it.uniroma3.siw.controller;




import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import it.uniroma3.siw.model.Credentials;
import it.uniroma3.siw.model.Customer;
import it.uniroma3.siw.model.Game;
import it.uniroma3.siw.repository.CredentialsRepository;
import it.uniroma3.siw.repository.GameRepository;
import it.uniroma3.siw.service.CustomerService;
import it.uniroma3.siw.service.GameService;
import it.uniroma3.siw.validator.GameValidator;

@Controller
public class GameController {
	
	@Autowired 
	private GameRepository gameRepository;

	@Autowired 
	private GameValidator gameValidator;
	@Autowired
	private GameService gameService;
	

	@Autowired 
	private CredentialsRepository credentialsRepository;
	
	@Autowired
	private CustomerService customerService;
	
	
	

	
	
	@GetMapping("/game/{id}")
    public String getGame(@PathVariable Long id, Model model) {
        Game game = gameRepository.findById(id)
               .orElseThrow(() -> new IllegalArgumentException("Invalid game ID:" + id));
        model.addAttribute("game", game);
        return "game";
    }
	

	@PostMapping("/game/{id}")
	public String addReview(@PathVariable Long id, @RequestParam("text") String text, @RequestParam("rating") Integer rating) {
		gameService.addReviewToGame(id, text, rating);
		return "redirect:/game/" + id;
	}
	
	@GetMapping("/game/{id}/addToCart")
	public String addToCart(@PathVariable Long id, Model model,Authentication auth) {
		
		customerService.addToCartService(auth.getName(), id);
		
		return "redirect:/cart";
		
		
	}
	
	
	@GetMapping("/foundGames")
    public String foundGames(@RequestParam("name") String name, Model model) {
        List<Game> games = gameRepository.searchGamesByNameContainingIgnoreCase(name);
        model.addAttribute("games", games);
        return "foundGames.html";
    }
	
	
	
    /*
	@GetMapping(value="/admin/formNewGame")
	public String formNewGame(Model model) {
		model.addAttribute("game", new Game());
		return "admin/formNewGame.html";
	}

	@GetMapping(value="/admin/formUpdateGame/{id}")
	public String formUpdateGame(@PathVariable("id") Long id, Model model) {
		model.addAttribute("game", gameRepository.findById(id).get());
		return "admin/formUpdateGame.html";
	}

	@GetMapping(value="/admin/indexgame")
	public String indexgame() {
		return "admin/indexGame.html";
	}
	
	@GetMapping(value="/admin/manageGames")
	public String manageGames(Model model) {
		model.addAttribute("games", this.gameRepository.findAll());
		return "admin/managegames.html";
	}
	
    /*
	@PostMapping("/admin/game")
	public String newgame(@Valid @ModelAttribute("game") Game game, BindingResult bindingResult, Model model) {
		
		this.gameValidator.validate(game, bindingResult);
		if (!bindingResult.hasErrors()) {
			this.gameRepository.save(game); 
			model.addAttribute("game", game);
			return "game.html";
		} else {
			return "admin/formNewgame.html"; 
		}
	}

	

	
	@GetMapping("/formSearchGames")
	public String formSearchGames() {
		return "formSearchGames.html";
	}

	@PostMapping("/searchGames")
	public String searchGames(Model model, @RequestParam int year) {
		model.addAttribute("games", this.gameRepository.findByReleaseDate(year));
		return "foundGames.html";
	}
	
	*/
}

