package it.uniroma3.siw.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import it.uniroma3.siw.model.Game;
import it.uniroma3.siw.repository.GameRepository;

@Component
public class GameValidator implements Validator {
	@Autowired
	private GameRepository GameRepository;

	@Override
	public void validate(Object o, Errors errors) {
		Game Game = (Game)o;
		if (Game.getName()!=null && Game.getDeveloper()!=null 
				&& GameRepository.existsByNameAndDeveloper(Game.getName(), Game.getDeveloper())) {
			errors.reject("Game.duplicate");
		}
	}

	@Override
	public boolean supports(Class<?> aClass) {
		return Game.class.equals(aClass);
	}
}