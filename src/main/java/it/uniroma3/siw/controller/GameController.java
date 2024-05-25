package it.uniroma3.siw.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import it.uniroma3.siw.model.Game;
import it.uniroma3.siw.repository.GameRepository;
import it.uniroma3.siw.validator.GameValidator;
import jakarta.validation.Valid;

@Controller
public class GameController {
	
	@Autowired 
	private GameRepository gameRepository;

	@Autowired 
	private GameValidator gameValidator;
	
	@GetMapping("/game/{id}")
	public String getGame(@PathVariable("id") Long id, Model model) {
		model.addAttribute("game", this.gameRepository.findById(id));
		return "game.html";
	}
	
	

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


	
	@GetMapping(value="/admin/manageGames")
	public String manageGames(Model model) {
		model.addAttribute("games", this.gameRepository.findAll());
		return "admin/managegames.html";
	}
	
 
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
	
	
}

