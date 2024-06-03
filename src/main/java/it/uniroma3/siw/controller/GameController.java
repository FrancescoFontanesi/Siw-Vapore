package it.uniroma3.siw.controller;




import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import it.uniroma3.siw.model.Game;
import it.uniroma3.siw.repository.GameRepository;
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

    @GetMapping("/randomGames")
    public String showRandomGames(Model model) {
        List<Game> randomGames = gameService.getRandomGames();
        model.addAttribute("randomGames", randomGames);
        return "index";
    }
	
	@GetMapping("/game/{id}")
    public String getGame(@PathVariable Long id, Model model) {
        Game game = gameRepository.findById(id)
               .orElseThrow(() -> new IllegalArgumentException("Invalid game ID:" + id));
        model.addAttribute("game", game);
        return "game"; // Assicurati che 'game' sia il nome della tua vista Thymeleaf
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

