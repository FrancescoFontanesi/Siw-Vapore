package it.uniroma3.siw.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.uniroma3.siw.model.Game;
import it.uniroma3.siw.model.Review;
import it.uniroma3.siw.repository.GameRepository;
import it.uniroma3.siw.repository.ReviewRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

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
    	
    	@Transactional
    	public void deleteGame(Long id) {
    		
    		Game game = gameRepository.findById(id)
    				.orElseThrow(() -> new IllegalArgumentException("Invalid game ID:" + id));
    		gameRepository.delete(game);
    		
    	}



		public void updateGame(Long id, @Valid Game newGame) {
			
		    Game oldGame = gameRepository.findById(id).get();
		    
		    newGame.setDeveloper(oldGame.getDeveloper());
		    newGame.setReviews(new ArrayList<Review>());
		    
		    if (newGame.getName() != null && !newGame.getName().equals(oldGame.getName())) {
	            oldGame.setName(newGame.getName());
	        }
		    
		    if (newGame.getCategory()!= null && !newGame.getCategory().equals(oldGame.getCategory())) {
	            oldGame.setCategory(newGame.getCategory());
	        }
		    
		    if (newGame.getPrice() != null && !newGame.getPrice().equals(oldGame.getPrice())) {
	            oldGame.setPrice(newGame.getPrice());
	        }
		    
		    if (newGame.getCopertina()!= null && !newGame.getCopertina().equals(oldGame.getCopertina())) {
	            oldGame.setCopertina(newGame.getCopertina());
	        }
		    
		    if (newGame.getReleaseDate()!= null && !newGame.getReleaseDate().equals(oldGame.getReleaseDate())) {
	            oldGame.setReleaseDate(newGame.getReleaseDate());
	        }

		    gameRepository.save(newGame);
		    
			
		}
    	
    }


