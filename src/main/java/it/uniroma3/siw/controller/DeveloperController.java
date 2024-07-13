package it.uniroma3.siw.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import it.uniroma3.siw.model.Developer;
import it.uniroma3.siw.model.Game;
import it.uniroma3.siw.repository.DeveloperRepository;
import it.uniroma3.siw.repository.GameRepository;
import it.uniroma3.siw.service.DeveloperService;
import it.uniroma3.siw.validator.GameValidator;
import jakarta.validation.Valid;

@Controller
public class DeveloperController {
	
	@Autowired 
	private GameValidator gameValidator;
	
    private static String UPLOADED_FOLDER = "src/main/resources/static/images/newGame/";

    
    @Autowired
    private GameRepository gameRepository;
	
    @Autowired
	private DeveloperRepository developerRepository;
    
	@Autowired
	private DeveloperService developerService;
	
	@GetMapping("/developer/{id}")
    public String getDeveloper(@PathVariable Long id, Model model) {
        Developer dev = developerRepository.findAllById(id);
        System.out.println(dev.getId());
        model.addAttribute("developer", dev);
        return "dev";
    }
	
	
	@GetMapping("/addGame")
	public String getNewGameForm(Model m) {
		m.addAttribute("game", new Game());
		return "newGame";
	}
	
	@GetMapping("/editGame/{id}")
	public String editGame(@PathVariable("id") Long id, Model m) {
		m.addAttribute("game", gameRepository.findById(id).get());
		m.addAttribute("id", id);
		return "editGame";
	}
	
	@PostMapping("/addGame")
	public String addGame(Model m, @Valid @ModelAttribute("game") Game g, Authentication auth,BindingResult gameBindingResult,
			@RequestParam("file") MultipartFile file,@RequestParam("additionalFiles") List<MultipartFile> additionalFiles) {
		
		if (!file.isEmpty()) {
            try {
                byte[] bytes = file.getBytes();
                Path path = Paths.get(UPLOADED_FOLDER + file.getOriginalFilename());
                Files.write(path, bytes);
                g.setCopertina("/images/newGame/" + file.getOriginalFilename() );
            } catch (IOException e) {
                e.printStackTrace();
                m.addAttribute("message", "Failed to upload image");
                return "newGame";
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
                        g.addImages("/images/newGame/" + additionalFile.getOriginalFilename());
                    } catch (IOException e) {
                        e.printStackTrace();
                        m.addAttribute("message", "Failed to upload additional image");
                        return "newGame";
                    }
                }
            }
        }


		
		
		this.gameValidator.validate(g,gameBindingResult);
		System.out.println(gameBindingResult);
		if(!gameBindingResult.hasErrors()){
			
        System.out.println(g.toString());
		
		developerService.newGame(g, auth.getName());
		return "redirect:/myPage";
		}
		
		return "newGame";
		
		

	}
	
	
	
}
