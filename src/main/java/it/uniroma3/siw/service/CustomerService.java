package it.uniroma3.siw.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
}
