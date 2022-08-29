package com.laszlojanku.spring.workouttracker.model;

/**
 * Represent the RegisterForm.
 */
public class RegisterForm {
	
	private String username;
	private String password;
	private String passwordConfirm;
	
	public RegisterForm(String username, String password, String passwordConfirm) {		
		this.username = username;
		this.password = password;
		this.passwordConfirm = passwordConfirm;
	}
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getPasswordConfirm() {
		return passwordConfirm;
	}
	public void setPasswordConfirm(String passwordConfirm) {
		this.passwordConfirm = passwordConfirm;
	}
	
	@Override
	public String toString() {
		return "RegisterForm [username=" + username + ", password=" + password + ", passwordConfirm=" + passwordConfirm
				+ "]";
	}

}