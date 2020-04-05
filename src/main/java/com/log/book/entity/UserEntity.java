package com.log.book.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;

@Entity
@Table(name="users")
public class UserEntity implements Serializable {

	private static final long serialVersionUID = 5588850077483519676L;
	
	@Id
	@GeneratedValue
	private long id;
	
	@Column(nullable = false, length=50)
	private String firstName;
	
	@Column(nullable = false, length=50)
	private String lastName;
	
	@Column(nullable=false, length=100)
	private String email;
	
	@Column(nullable=false, length=300)
	private String encryptedPassword;
	
	private int active;
	
	private String roles;
	
	private String permissions;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getEncryptedPassword() {
		return encryptedPassword;
	}

	public void setEncryptedPassword(String encryptedPassword) {
		this.encryptedPassword = encryptedPassword;
	}

	public int getActive() {
		return active;
	}

	public void setActive(int active) {
		this.active = active;
	}

	public String getRoles() {
		return roles;
	}

	public void setRoles(String roles) {
		this.roles = roles;
	}

	public String getPermissions() {
		return permissions;
	}

	public void setPermissions(String permissions) {
		this.permissions = permissions;
	}
	
	public List<String> getRoleList() {
		 if(roles.length() > 0) return Arrays.asList(this.roles.split(","));
		 return new ArrayList<>();
	}
	
	public List<String> getPermissionList() {
		if(permissions.length() > 0) return Arrays.asList(permissions.split(","));
		return new ArrayList<>();
	}
	
}
