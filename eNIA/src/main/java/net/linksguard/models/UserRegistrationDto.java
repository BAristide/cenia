package net.linksguard.models;

public class UserRegistrationDto {
	private String lastName;
	private String firstName;
	private String matricule;
	private String email;
	private String password;
	
	public UserRegistrationDto(){
		
	}

	public UserRegistrationDto(String lastName, String firstName, String matricule, String email, String password) {
		super();
		this.lastName = lastName;
		this.firstName = firstName;
		this.matricule = matricule;
		this.email = email;
		this.password = password;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getMatricule() {
		return matricule;
	}

	public void setMatricule(String matricule) {
		this.matricule = matricule;
	}

	public String getEmail() {
		return email;
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
	
	
}
