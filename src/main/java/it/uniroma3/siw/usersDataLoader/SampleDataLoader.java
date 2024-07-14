package it.uniroma3.siw.usersDataLoader;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import it.uniroma3.siw.model.Credentials;
import it.uniroma3.siw.model.Customer;
import it.uniroma3.siw.model.Developer;
import it.uniroma3.siw.model.User;
import it.uniroma3.siw.repository.CredentialsRepository;

@Component
public class SampleDataLoader implements CommandLineRunner {

  

    @Autowired
    private CredentialsRepository credentialsRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        // Creating a regular user
        User user = new User();
        user.setName("Ajeje");
        user.setSurname("Brazorf");
        user.setDate("30/92/1965");
        user.setDescription("Admin");

      

        // Creating a developer
        Developer developer = new Developer();
        developer.setName("Jane");
        developer.setSurname("Doe");
        developer.setDate("02-02-1985");
        developer.setDescription("Game Developer");
        developer.setCompany("Awesome Games");
        developer.setLogo("logo.png");
        developer.setSite_url("http://awesomegames.com");

        

        // Creating a customer
        Customer customer = new Customer();
        customer.setName("Alice");
        customer.setSurname("Smith");
        customer.setDate("03-03-1992");
        customer.setDescription("Gamer");
        customer.setProfilePic("profilepic.png");


        // Creating credentials for user
        Credentials userCredentials = new Credentials();
        userCredentials.setEmail("admin@example.com");
        userCredentials.setPassword(passwordEncoder.encode("pass"));
        userCredentials.setRole(Credentials.ADMIN_ROLE);
        userCredentials.setUser(user);

        credentialsRepository.save(userCredentials);

        // Creating credentials for developer
        Credentials developerCredentials = new Credentials();
        developerCredentials.setEmail("jane.doe@example.com");
        developerCredentials.setPassword(passwordEncoder.encode("pass"));
        developerCredentials.setRole(Credentials.DEVELOPER_ROLE);
        developerCredentials.setUser(developer);

        credentialsRepository.save(developerCredentials);

        // Creating credentials for customer
        Credentials customerCredentials = new Credentials();
        customerCredentials.setEmail("alice.smith@example.com");
        customerCredentials.setPassword(passwordEncoder.encode("pass"));
        customerCredentials.setRole(Credentials.CUSTOMER_ROLE);
        customerCredentials.setUser(customer);

        credentialsRepository.save(customerCredentials);

        System.out.println("Sample data loaded.");
        
        
    }
}
