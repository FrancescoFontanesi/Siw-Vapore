package it.uniroma3.siw.model;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Customer extends User {
	

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
			
	private String profilePic;

	public String birthDay;



	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<Game> cart;
	
	private Double walletFunds;
	
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<Game> boughtGames;

	public Customer() {
		super();
		this.boughtGames = new ArrayList<Game>();
		this.cart = new ArrayList<Game>();
		this.walletFunds = 0.0;
	}

	
	
	
	
	public List<Game> getCart() {
		return cart;
	}



	public void setCart(List<Game> cart) {
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


	@Override
	public String toString() {
		return "Customer [id=" + id + ", profilePic=" + profilePic + ", cart=" + cart
				+ ", walletFunds=" + walletFunds + ", boughtGames=" + boughtGames + "]";
	}

	
	
	public String getBirthDay() {
		return birthDay;
	}





	public void setBirthDay(String birthDay) {
		this.birthDay = birthDay;
	}






	public Long getId() {
		return id;
	}





	public void setId(Long id) {
		this.id = id;
	}






}
