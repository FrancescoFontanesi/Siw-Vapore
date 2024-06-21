package it.uniroma3.siw.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.uniroma3.siw.model.Credentials;
import it.uniroma3.siw.model.Developer;
import it.uniroma3.siw.model.Game;
import it.uniroma3.siw.repository.CredentialsRepository;
import it.uniroma3.siw.repository.GameRepository;

@Service
public class DeveloperService {
	
	@Autowired
	private GameRepository gameRepository;
	@Autowired
	private CredentialsRepository credentialsRepository;
	
    @Transactional
	public void newGame(Game g,String email) {
		
		Credentials c = credentialsRepository.findByEmail(email).orElse(null);
        if (c != null && c.getUser() instanceof Developer) {
            Developer d = (Developer) c.getUser();

		
			g.setDeveloper(d);
			d.getdevelopedGames().add(g);
			c.setUser(d);
			
			gameRepository.save(g);
			credentialsRepository.save(c);
		
	     }
	}
        

}
