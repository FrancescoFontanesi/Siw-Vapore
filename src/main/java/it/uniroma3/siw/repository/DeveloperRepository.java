package it.uniroma3.siw.repository;

import org.springframework.data.repository.CrudRepository;

import it.uniroma3.siw.model.Developer;

public interface DeveloperRepository extends CrudRepository<Developer, Long> {
	
	
	public Iterable<Developer> findByName(String name);

	public Developer findAllById (Long id);
	
	public Developer findByNameAndSurname(String name,String surname);

}
