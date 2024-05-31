package it.uniroma3.siw.repository;

import org.springframework.data.repository.CrudRepository;

import it.uniroma3.siw.model.Customer;

public interface CustomerRepository extends CrudRepository<Customer, Long> {
	
	public Customer findByAge(Integer age);

}
