package com.log.book.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="roles")
public class RoleEntity implements Serializable {

	private static final long serialVersionUID = 1993877023911450148L;
	
	@Id
	@GeneratedValue
	private long id;
	
	@Column(nullable=false, length=20)
	private String name;
	
	@Column(nullable=false, length=50)
	private String value;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	
	

}
