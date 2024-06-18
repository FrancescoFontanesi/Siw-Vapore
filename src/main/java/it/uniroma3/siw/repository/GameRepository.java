package it.uniroma3.siw.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import it.uniroma3.siw.model.Developer;
import it.uniroma3.siw.model.Game;

public interface GameRepository extends CrudRepository<Game, Long> {

	public List<Game> findAllByReleaseDate(int releaseDate);

	public boolean existsByNameAndReleaseDate(String name, int year);
	
	public boolean existsByNameAndDeveloper(String name, Developer developer);
	
	public List<Game> findAllByCategory(String category);

	public Game findByReleaseDate(int year);
	
	//@Query("SELECT g FROM Game g WHERE LOWER (g.name) LIKE LOWER (CONCAT ('%', : name , '%'))")
	public List<Game> searchGamesByNameContainingIgnoreCase(String name);
	
	 
	
	public List<Game> findAll();
	 
	

}
