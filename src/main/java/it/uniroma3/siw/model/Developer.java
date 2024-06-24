package it.uniroma3.siw.model;


import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Developer extends User {
	

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	
	
	private String company;
	
	private String logo;
	
	private String site_url;

	private String description;

	
	@OneToMany(mappedBy = "developer", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	public List<Game> developedGames;
	

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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Developer() {
		super();
	}

	@Override
	public String toString() {
		return "Developer [name ="+ super.getName()+", surname =" + super.getSurname() + ",  id=" + id + ", company=" + company + ", logo=" + logo + ", site_url=" + site_url
				+ ", developedGames=" + developedGames + "]";
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public List<Game> getDevelopedGames() {
		return developedGames;
	}

	public void setDevelopedGames(List<Game> developedGames) {
		this.developedGames = developedGames;
	}
	
	
	
}
