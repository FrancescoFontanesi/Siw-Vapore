		package it.uniroma3.siw.model;
		
		import jakarta.persistence.Entity;
		import jakarta.persistence.GeneratedValue;
		import jakarta.persistence.GenerationType;
		import jakarta.persistence.Id;
		import jakarta.persistence.Inheritance;
		import jakarta.persistence.InheritanceType;
		import jakarta.persistence.Table;
		import jakarta.validation.constraints.NotBlank;
		
		@Entity
		@Inheritance(strategy = InheritanceType.JOINED)
		@Table(name = "utente")
		public class User {
			
			@Id
		    @GeneratedValue(strategy = GenerationType.AUTO)
		    private Long id;
			
			
		
			@NotBlank
			private String name;
			
			private String surname;
			
			private String date;
			
			
			
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
			
			public String getSurname() {
				return surname;
			}
		
			public void setSurname(String surname) {
				this.surname = surname;
			}
		
			@Override
			public String toString() {
				return "User [id=" + id + ", name=" + name + ", surname=" + surname + "]";
			}
	
			public String getDate() {
				return date;
			}
	
			public void setDate(String date) {
				this.date = date;
			}
	
			
		
			
		}