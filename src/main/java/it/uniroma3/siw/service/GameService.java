package it.uniroma3.siw.service;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.uniroma3.siw.model.Game;
import it.uniroma3.siw.repository.GameRepository;

@Service
public class GameService {
	
	 @Autowired
	    private GameRepository gameRepository;

	    public List<Game> getRandomGames() {
	        List<Game> allGames = gameRepository.findAll(); // Assume che findAll() restituisca tutti i giochi
	        Collections.shuffle(allGames); // Mischia l'elenco dei giochi
	        return allGames.subList(0, Math.min(4, allGames.size())); // Restituisci i primi 4 giochi, o meno se ci sono meno di 4 giochi disponibili
	    }
}
