package edu.neu.webtools.AptService.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@PrimaryKeyJoinColumn
@Table(name="staff")
public class Staff extends User{
	
	@Column(nullable = false)
	private boolean availability;
	
	@Column(nullable = false)
	private String person_type; // ex: plumber

	public boolean isAvailability() {
		return availability;
	}

	public void setAvailability(boolean availability) {
		this.availability = availability;
	}

	public String getPerson_type() {
		return person_type;
	}

	public void setPerson_type(String person_type) {
		this.person_type = person_type;
	}

	public Staff(boolean availability, String person_type) {
		super();
		this.availability = availability;
		this.person_type = person_type;
	}
	
	public Staff() {}
}