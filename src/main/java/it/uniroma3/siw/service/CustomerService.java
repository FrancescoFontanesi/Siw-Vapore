package it.uniroma3.siw.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import it.uniroma3.siw.model.Credentials;
import it.uniroma3.siw.model.Customer;
import it.uniroma3.siw.model.Game;
import it.uniroma3.siw.repository.CredentialsRepository;
import it.uniroma3.siw.repository.GameRepository;

@Service
public class CustomerService {

    @Autowired
    private CredentialsRepository credentialsRepository;

    @Autowired
    private GameRepository gameRepository;
    
    private static String UPLOADED_FOLDER_C = "src/main/resources/static/images/profili/";


    
    @Transactional
    public boolean buyGames(String email) {
        Credentials credentials = credentialsRepository.findByEmail(email).orElse(null);
        if (credentials != null && credentials.getUser() instanceof Customer) {
            Customer customer = (Customer) credentials.getUser();
            List<Game> cart = customer.getCart();
            double totalCost = cart.stream().mapToDouble(Game::getPrice).sum();

            if (customer.getWalletFunds() >= totalCost) {
                customer.setWalletFunds(customer.getWalletFunds() - totalCost);
                customer.getBoughtGames().addAll(cart);
                customer.getCart().clear();
                credentialsRepository.save(credentials);
                return true; // Purchase successful
            }
        }
        return false; // Not enough funds or customer not found
    }
    

    @Transactional
    public void addToCartService(String email, Long gameId) {
        Credentials c = credentialsRepository.findByEmail(email).orElse(null);
        if (c != null && c.getUser() instanceof Customer) {
            Customer customer = (Customer) c.getUser();
            Game game = gameRepository.findById(gameId).orElse(null);
            if (game != null) {
                customer.getCart().add(game);
                credentialsRepository.save(c);
            }
        }
    }
    
    @Transactional
    public void addFunds(String email, double amount) {
        Credentials credentials = credentialsRepository.findByEmail(email).orElse(null);
        if (credentials!= null && credentials.getUser() instanceof Customer) {
            Customer customer = (Customer) credentials.getUser();
            customer.setWalletFunds(customer.getWalletFunds() + amount);
            credentialsRepository.save(credentials);
        }
    }

    @Transactional
	public void updateCustomer(Long id, Customer newC,MultipartFile file) {
    	
    	Customer oldC = (Customer)credentialsRepository.findById(id).get().getUser();
    	
    	newC.setId(oldC.getId());
    	newC.setBoughtGames(oldC.getBoughtGames());
    	newC.setCart(oldC.getCart());
    	newC.getWalletFunds();
    	
    	
    	if (newC.getName() != null && !newC.getName().equals(oldC.getName())) {
            oldC.setName(newC.getName());
        }

        if (newC.getSurname() != null && !newC.getSurname().equals(oldC.getSurname())) {
            oldC.setSurname(newC.getSurname());
        }
        
        if (newC.getDate() != null && !newC.getDate().equals(oldC.getDate())) {
  		  oldC.setDate(newC.getDate()); }
  		

        if (!file.isEmpty()) {
            try {
                byte[] bytes = file.getBytes();
                Path path = Paths.get(UPLOADED_FOLDER_C + file.getOriginalFilename());
                Files.write(path, bytes);
                oldC.setProfilePic("/images/profili/" + file.getOriginalFilename() );
            } catch (IOException e) {
                e.printStackTrace();

            }
        }
        
        Credentials c = credentialsRepository.findByUser(oldC);
        c.setUser(oldC);
        credentialsRepository.save(c);
    	
    	
      
}

}
