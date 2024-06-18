package it.uniroma3.siw.service;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.uniroma3.siw.model.Game;
import it.uniroma3.siw.model.Review;
import it.uniroma3.siw.repository.GameRepository;
import it.uniroma3.siw.repository.ReviewRepository;
import jakarta.transaction.Transactional;

@Service
public class GameService {
	
	@Autowired
    private GameRepository gameRepository;
	@Autowired
	private ReviewRepository reviewRepository;


    public List<Game> getRandomGames(int numberOfGames) {
        List<Game> allGames = gameRepository.findAll();
        Collections.shuffle(allGames);
        
        return allGames.subList(0, Math.min(numberOfGames, allGames.size()));
    }
    
  
    	
    	@Transactional
    	public void addReviewToGame(Long gameId, String text, Integer rating) {
    		Game game = gameRepository.findById(gameId)
    				.orElseThrow(() -> new IllegalArgumentException("Invalid game ID:" + gameId));
    		Review review = new Review(text, rating, game);
    		reviewRepository.save(review);
    		game.getReviews().add(review);
    		gameRepository.save(game);
    	}
    }


