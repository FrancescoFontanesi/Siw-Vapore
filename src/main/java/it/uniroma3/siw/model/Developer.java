package it.uniroma3.siw.model;


import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotNull;

@Entity
public class Developer {
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	public Long id;
	
	@NotNull
	public String name;
	
	public String logo;
	
	public String site_url;
	
	@OneToMany(mappedBy ="developer")
	public List<Game> developedGames;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLogo() {
		return logo;
	}

	public void setLogo(String logo) {
		this.logo = logo;
	}

	public String getSite_url() {
		return site_url;
	}

	public void setSite_url(String site_url) {
		this.site_url = site_url;
	}

	public List<Game> getdevelopedGames() {
		return developedGames;
	}

	public void setdevelopedGames(List<Game> developedGames) {
		this.developedGames = developedGames;
	}

	public Developer() {
		super();
	}
	
	
	
}
