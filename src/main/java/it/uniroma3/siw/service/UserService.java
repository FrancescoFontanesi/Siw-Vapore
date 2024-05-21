package it.uniroma3.siw.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.uniroma3.siw.model.User;
import it.uniroma3.siw.repository.UserRepository;

@Service
public class UserService {
	
	
	@Autowired 
	private UserRepository userRepository;
	
	@Transactional
	public User getUser(Long id) {
		Optional<User> u = this.userRepository.findById(id);
		return u.orElse(null);
	}
	
	@Transactional
	public User saveUser(User u) {
        return this.userRepository.save(u);
	}
	
	public Iterable<User> getAllUser(){
		return this.userRepository.findAll();
	}

}
