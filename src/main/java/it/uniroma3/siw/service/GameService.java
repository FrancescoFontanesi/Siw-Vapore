package it.uniroma3.siw.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

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
	
    private static String UPLOADED_FOLDER = "src/main/resources/static/images/newGame/";



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



		public void updateGame(Long id, @Valid Game newGame, MultipartFile file, List<MultipartFile> additionalFiles) {
			
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
		    
		    
		    if (newGame.getReleaseDate()!= null && !newGame.getReleaseDate().equals(oldGame.getReleaseDate())) {
	            oldGame.setReleaseDate(newGame.getReleaseDate());
	        }
		    
		    if (!file.isEmpty()) {
	            try {
	                byte[] bytes = file.getBytes();
	                Path path = Paths.get(UPLOADED_FOLDER + file.getOriginalFilename());
	                Files.write(path, bytes);
	                oldGame.setCopertina("/images/newGame/" + file.getOriginalFilename() );
	            } catch (IOException e) {
	               
	            }
	        }
			
			if (!additionalFiles.isEmpty()) {
	            for (MultipartFile additionalFile : additionalFiles) {
	                if (!additionalFile.isEmpty()) {
	                    try {
	                        byte[] bytes = additionalFile.getBytes();
	                        Path path = Paths.get(UPLOADED_FOLDER + additionalFile.getOriginalFilename());
	                        Files.copy(additionalFile.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
	                        // Add the path to the game object's list of additional images
	                        oldGame.addImages("/images/newGame/" + additionalFile.getOriginalFilename());
	                    } catch (IOException e) {
	                       
	                    }
	                }
	            }
	        }


		    gameRepository.save(oldGame);
		    
			
		}
    	
    }


