package it.uniroma3.siw.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.uniroma3.siw.model.Customer;
import it.uniroma3.siw.model.Developer;
import it.uniroma3.siw.model.User;
import it.uniroma3.siw.repository.CustomerRepository;
import it.uniroma3.siw.repository.DeveloperRepository;
import it.uniroma3.siw.repository.UserRepository;

@Service
public class UserService {
	
	
	@Autowired 
	private UserRepository userRepository;
	@Autowired 
	private CustomerRepository customerRepository;
	@Autowired 
	private DeveloperRepository developerRepository;
	
    
	public Iterable<User> getAllSuperUser(){
		return this.userRepository.findAll();
	}
	
	@Transactional
	public Developer saveDev(Developer d) {
        return this.developerRepository.save(d);
	}
	
	@Transactional
	public Customer saveCustomer(Customer c) {
        return this.customerRepository.save(c);
	}
	
	
}
