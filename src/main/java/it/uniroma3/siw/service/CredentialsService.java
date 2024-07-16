package it.uniroma3.siw.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;

import it.uniroma3.siw.model.Credentials;
import it.uniroma3.siw.model.User;
import it.uniroma3.siw.repository.CredentialsRepository;
import it.uniroma3.siw.repository.UserRepository;
import jakarta.validation.Valid;

@Service
public class CredentialsService  {

    @Autowired
    protected PasswordEncoder passwordEncoder;

    @Autowired
    protected CredentialsRepository credentialsRepository;
    
    @Autowired
    protected UserRepository userRepository;
   
    @Transactional
    public Credentials getCredentials(Long id) {
        Optional<Credentials> result = this.credentialsRepository.findById(id);
        return result.orElse(null);
    }

    @Transactional
    public Credentials getCredentials(String email) {
        Optional<Credentials> result = this.credentialsRepository.findByEmail(email);
        return result.orElse(null);
    }


    @Transactional
    public Credentials saveCredentials(Credentials credentials) {
        credentials.setPassword(this.passwordEncoder.encode(credentials.getPassword()));
        return this.credentialsRepository.save(credentials);
    }
    
    @Transactional
    public String updatePassword(String psw) {
    	return this.passwordEncoder.encode(psw);
    }
    
    @Transactional
    public void deleteCredentials(Long credentialsId) {
        Credentials credentials = credentialsRepository.findById(credentialsId).orElse(null);
        if(credentials!=null) 
        	userRepository.delete(credentials.getUser());
        	credentialsRepository.delete(credentials);
        
    }

    
    
}
   
 
