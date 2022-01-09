package net.linksguard.models;

public class AccesModel {
	
	private long id;
	private String email;
	private Integer roleUser;
	private Integer roleAdmin;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Integer getRoleUser() {
		return roleUser;
	}
	public void setRoleUser(Integer roleUser) {
		this.roleUser = roleUser;
	}
	public Integer getRoleAdmin() {
		return roleAdmin;
	}
	public void setRoleAdmin(Integer roleAdmin) {
		this.roleAdmin = roleAdmin;
	}
	
	

}
