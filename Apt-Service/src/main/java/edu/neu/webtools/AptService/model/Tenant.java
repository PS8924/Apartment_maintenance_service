package edu.neu.webtools.AptService.model;

import java.util.Date;

import javax.persistence.*;

@Entity
@PrimaryKeyJoinColumn
@Table(name="tenant")
public class Tenant extends User {
	
	@Column(nullable = false)
	private Date registeredDate;

	public Date getRegisteredDate() {
		return registeredDate;
	}

	public void setRegisteredDate(Date registeredDate) {
		this.registeredDate = registeredDate;
	}

	public Apartment getApartment() {
		return apartment;
	}

	public void setApartment(Apartment apartment) {
		this.apartment = apartment;
	}

	@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "apt_id", nullable = true)
    private Apartment apartment; // multiple tenants one apartment mapping

	public Tenant(Date registeredDate, Apartment apartment) {
		super();
		this.registeredDate = registeredDate;
		this.apartment = apartment;
	}

	public Tenant() {}
	
	@Override
	public String toString() {
		return "Tenant [registeredDate=" + registeredDate + ", apartment=" + apartment + "]";
	}
	
}