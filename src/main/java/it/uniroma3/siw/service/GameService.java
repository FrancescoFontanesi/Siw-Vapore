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

    public List<Game> getRandomGames(int numberOfGames) {
        List<Game> allGames = gameRepository.findAll();
        Collections.shuffle(allGames);
        
        return allGames.subList(0, Math.min(numberOfGames, allGames.size()));
    }
}
