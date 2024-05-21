package it.uniroma3.siw.model;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.NotBlank;

@Entity
public class Customer extends User {
	

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	public Long id;
	
	
	@NotBlank
	private String name;	
	
	private String profilePic;
	@NotBlank
	
	private String surname;
	
	@OneToOne
	private Cart cart;
	
	private Double walletFunds;
	
	@OneToMany
	private List<Game> boughtGames;

	public Customer() {
		super();
		this.boughtGames = new ArrayList<Game>();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}


	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public Cart getCart() {
		return cart;
	}

	public void setCart(Cart cart) {
		this.cart = cart;
	}

	public String getProfilePic() {
		return profilePic;
	}

	public void setProfilePic(String profilePic) {
		this.profilePic = profilePic;
	}

	public Double getWalletFunds() {
		return walletFunds;
	}

	public void setWalletFunds(Double walletFunds) {
		this.walletFunds = walletFunds;
	}

	public List<Game> getBoughtGames() {
		return boughtGames;
	}

	public void setBoughtGames(List<Game> boughtGames) {
		this.boughtGames = boughtGames;
	}
	//da implementare in services
	public Boolean buyGames() {
		Double total = this.cart.getTotal();
		
		if(total < this.walletFunds) {
			boughtGames.addAll(this.cart.getGames());
			this.walletFunds -= total;
			return true;
		}
		return false;
		
	}

}
