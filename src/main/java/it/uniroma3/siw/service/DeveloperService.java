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
    

    public void updateDeveloper(Developer oldDev, Developer newDev) {
    	
    	newDev.setId(oldDev.getId());
    	newDev.setdevelopedGames(oldDev.getdevelopedGames());
    	
    	
    	if (newDev.getName() != null && !newDev.getName().equals(oldDev.getName())) {
            oldDev.setName(newDev.getName());
        }

        if (newDev.getSurname() != null && !newDev.getSurname().equals(oldDev.getSurname())) {
            oldDev.setSurname(newDev.getSurname());
        }

		/*
		 * if (newDev.getSite_url() != null &&
		 * !newDev.getSite_url().equals(oldDev.getSite_url())) {
		 * oldDev.setSite_url(newDev.getSite_url()); }
		 * 
		 * if (newDev.getDescription() != null &&
		 * !newDev.getDescription().equals(oldDev.getDescription())) {
		 * oldDev.setDescription(newDev.getDescription()); }
		 * 
		 * if (newDev.getLogo() != null && !newDev.getLogo().equals(oldDev.getLogo())) {
		 * oldDev.setLogo(newDev.getLogo()); }
		 */
        
        Credentials c = credentialsRepository.findByUser(oldDev);
        c.setUser(newDev);
        credentialsRepository.save(c);
    	
    	
      
}

    
    

        
        

}
