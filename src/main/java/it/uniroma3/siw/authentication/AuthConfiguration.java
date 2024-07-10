package it.uniroma3.siw.authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.session.HttpSessionEventPublisher;

import it.uniroma3.siw.service.CustomUserDetailsService;



@Configuration
@EnableWebSecurity
public class AuthConfiguration  {

   
    
    @Autowired
    CustomUserDetailsService customUserDetailsSerivce;


	  @Bean PasswordEncoder passwordEncoder() {
		 return new BCryptPasswordEncoder();
	  }
    


    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception { 
    	        auth.userDetailsService(customUserDetailsSerivce).passwordEncoder(passwordEncoder()); 
    	}
    
    
    

    @Bean
    @Order(1)
     SecurityFilterChain configure(HttpSecurity http) throws Exception {
        http.csrf(csrf -> csrf.disable())
            .authorizeHttpRequests(authorizeRequests ->
                authorizeRequests
                    .requestMatchers("/**", "/index", "/register", "/css/**", "/images/**", "favicon.ico").permitAll()
                    .requestMatchers(HttpMethod.POST, "/register", "/login").permitAll()
                    .requestMatchers("/myPage/**","/editGame","/addGame","/cart").authenticated()
                    .anyRequest().authenticated())
            .formLogin(formLogin ->
                formLogin
                    .loginPage("/login")
                    .permitAll()
                    .defaultSuccessUrl("/", true)
                    .failureUrl("/login?error"))
            .logout(logout ->
                logout
                    .logoutUrl("/logout")
                    .logoutSuccessUrl("/")
                    .invalidateHttpSession(true)
                    .deleteCookies("JSESSIONID"));
         

        return http.build();
    }

    @Bean
    HttpSessionEventPublisher httpSessionEventPublisher() {
        return new HttpSessionEventPublisher();
    }



}
