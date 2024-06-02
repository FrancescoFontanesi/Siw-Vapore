package it.uniroma3.siw.service;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import it.uniroma3.siw.model.Credentials;
import it.uniroma3.siw.repository.CredentialsRepository;

import java.util.Collections;
import java.util.Optional;

@Service("userDetailsService")
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private CredentialsRepository credentialsRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println("Attempting to load user by email: " + username);
        Optional<Credentials> credentials = credentialsRepository.findByEmail(username);

        if (!credentials.isPresent()) {
            throw new UsernameNotFoundException("User not found");
        }
        
        System.out.println("User found: " + credentials.get().getEmail());

        return new org.springframework.security.core.userdetails.User(
                credentials.get().getEmail(),
                credentials.get().getPassword(),
                Collections.singletonList(new SimpleGrantedAuthority(credentials.get().getRole())));
    }
}