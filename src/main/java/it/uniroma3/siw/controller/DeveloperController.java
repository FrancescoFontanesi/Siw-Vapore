package it.uniroma3.siw.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

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

import it.uniroma3.siw.model.Game;
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
	private DeveloperService developerService;
	
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
	public String addGame(Model m, @Valid @ModelAttribute("game") Game g, Authentication auth,BindingResult gameBindingResult,@RequestParam("file") MultipartFile file) {
		
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
