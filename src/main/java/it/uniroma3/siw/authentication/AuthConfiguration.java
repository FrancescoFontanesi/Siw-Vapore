package it.uniroma3.siw.authentication;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import it.uniroma3.siw.model.Credentials;
import it.uniroma3.siw.service.CustomUserDetailsService;



@Configuration
@EnableWebSecurity
public class AuthConfiguration {

    @Autowired
    private DataSource dataSource;
    
    @Autowired
    CustomUserDetailsService customUserDetailsSerivce;

    @SuppressWarnings("deprecation")
	  @Bean PasswordEncoder passwordEncoder() {
		  //return new BCryptPasswordEncoder();
		  return (NoOpPasswordEncoder) NoOpPasswordEncoder.getInstance();
	  
	  }
	 
    
 

    
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		/*
		 * auth.jdbcAuthentication() .dataSource(dataSource)
		 * .authoritiesByUsernameQuery("SELECT email, role from credentials WHERE email=?"
		 * )
		 * .usersByUsernameQuery("SELECT email, password, 1 as enabled FROM credentials WHERE username=?"
		 * ) .passwordEncoder(passwordEncoder());
		 */
    	
    	auth.userDetailsService(customUserDetailsSerivce);
    	//.passwordEncoder(passwordEncoder());
    }

    @Bean
    @Order(1)
    SecurityFilterChain configure(HttpSecurity http) throws Exception {
        http.csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(authorizeRequests ->
                        authorizeRequests
                                .requestMatchers("/**", "/index", "/register", "/css/**", "/images/**", "favicon.ico").permitAll()
                                .requestMatchers(HttpMethod.POST, "/register", "/login").permitAll()
                                .requestMatchers("/admin/**").hasAuthority(Credentials.ADMIN_ROLE)
                                .anyRequest().authenticated()
                )
                .formLogin(formLogin ->
                        formLogin
                                .loginPage("/login")
                                .permitAll()
                                .defaultSuccessUrl("/", true)
                                .failureUrl("/login?error")
                )
                .logout(logout ->
                        logout
                                .logoutUrl("/logout")
                                .logoutSuccessUrl("/")
                                .invalidateHttpSession(true)
                                .deleteCookies("JSESSIONID")
                );

        return http.build();
    }
}
