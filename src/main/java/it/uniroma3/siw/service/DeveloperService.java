package it.uniroma3.siw.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

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
	

    // Directory where profile images will be saved
    private static String UPLOADED_FOLDER_D = "src/main/resources/static/images/sviluppatori/";
	
    @Transactional
	public void newGame(Game g,String email) {
		
		Credentials c = credentialsRepository.findByEmail(email).orElse(null);
        if (c != null && c.getUser() instanceof Developer) {
            Developer d = (Developer) c.getUser();

		
			g.setDeveloper(d);
			d.getDevelopedGames().add(g);
			c.setUser(d);
			
			gameRepository.save(g);
			credentialsRepository.save(c);
		
	     }
	}
    
 
    public void updateDeveloper(Long id, Developer newDev,MultipartFile file) { 
    	
    	Developer oldDev = (Developer)credentialsRepository.findById(id).get().getUser();
    	
    	newDev.setId(oldDev.getId());
    	newDev.setDevelopedGames(oldDev.getDevelopedGames());
    	
    	
    	System.out.println(file.isEmpty());
    	
    	if (newDev.getName() != null && !newDev.getName().equals(oldDev.getName())) {
            oldDev.setName(newDev.getName());
        }

        if (newDev.getSurname() != null && !newDev.getSurname().equals(oldDev.getSurname())) {
            oldDev.setSurname(newDev.getSurname());
        }

		
		if (newDev.getSite_url() != null && !newDev.getSite_url().equals(oldDev.getSite_url())) {
		  oldDev.setSite_url(newDev.getSite_url()); }
		
		
		 if (newDev.getDescription() != null &&
		 !newDev.getDescription().equals(oldDev.getDescription())) {
		 oldDev.setDescription(newDev.getDescription()); }
		 
		 if (!file.isEmpty()) {
             try {
                 byte[] bytes = file.getBytes();
                 Path path = Paths.get(UPLOADED_FOLDER_D + file.getOriginalFilename());
                 Files.write(path, bytes);
                 oldDev.setLogo("/images/sviluppatori/" + file.getOriginalFilename() );
             } catch (IOException e) {
                 e.printStackTrace();

             }
         }
		 
        
        Credentials c = credentialsRepository.findByUser(oldDev);
        c.setUser(oldDev);
        credentialsRepository.save(c);
    	
    	
      
}

    
    

        
        

}
