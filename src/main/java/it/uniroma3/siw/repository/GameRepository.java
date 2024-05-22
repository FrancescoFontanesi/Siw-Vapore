package it.uniroma3.siw.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import it.uniroma3.siw.model.Developer;
import it.uniroma3.siw.model.Game;

public interface GameRepository extends CrudRepository<Game, Long> {

	public List<Game> findByReleaseDate(int releaseDate);

	public boolean existsByNameAndReleaseDate(String name, int year);	
	
	public boolean existsByNameAndDeveloper(String name, Developer dev);

}
