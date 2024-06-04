package it.uniroma3.siw.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

@Entity
public class Credentials {

	public static final String DEVELOPER_ROLE = "Developer";
	public static final String CUSTOMER_ROLE = "Customer";
	public static final String ADMIN_ROLE = "ADMIN";
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
	
	
	@NotEmpty(message ="Email non valida")
	public String email;
	@NotEmpty(message = "La password non puo essere vuota")
	private String password;
	@NotEmpty(message = "Ruolo non valido")
	private String role;

	
	@OneToOne(cascade = CascadeType.ALL)
	private User user;
	
	public Credentials() {
		super();
	}


	public String getEmail() {
		return email;
	}
	
	
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getRole() {
		return role;
	}
	
	public void setRole(String role) {
		this.role = role;
	}


	@Override
	public String toString() {
		return "Credentials [id=" + id + ", email=" + email + ", password=" + password + ", role=" + role + ", user="
				+ user + "]";
	}

}
