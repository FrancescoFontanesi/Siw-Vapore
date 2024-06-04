package it.uniroma3.siw.model;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

@Entity
public class Customer extends User {
	

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
			
	private String profilePic;
	
	
	@Min(10)
	public Integer age;

	
	@OneToOne
	private Cart cart;
	
	private Double walletFunds;
	
	@OneToMany
	private List<Game> boughtGames;

	public Customer() {
		super();
		this.boughtGames = new ArrayList<Game>();
	}

	
	
	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
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



	@Override
	public String toString() {
		return "Customer [id=" + id + ", profilePic=" + profilePic + ", age=" + age + ", cart=" + cart
				+ ", walletFunds=" + walletFunds + ", boughtGames=" + boughtGames + "]";
	}

}
