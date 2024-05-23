package it.uniroma3.siw.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import it.uniroma3.siw.model.Game;

public interface GameRepository extends CrudRepository<Game, Long> {

	public List<Game> findAllByReleaseDate(int releaseDate);

	public boolean existsByNameAndReleaseDate(String name, int year);
	
	public List<Game> findAllByCategory(String category);

}
