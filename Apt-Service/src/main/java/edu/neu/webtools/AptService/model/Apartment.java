package edu.neu.webtools.AptService.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import javax.persistence.*;

@Entity
@Table(name="apartment")
public class Apartment {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false)
	private Long floor;
	
	@Column(nullable = false, unique = true)
	private Long apt_number;
	
	@Column(nullable = false)
	private boolean availability;
	
	@Column(nullable = false)
    private int numTenants;
	
	//@OneToMany
	//private Set<Integer> tenant = new HashSet<Integer>();
	
	public boolean isAvailability() {
		return availability;
	}
	public void setAvailability(boolean availability) {
		this.availability = availability;
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	public Long getFloor() {
		return floor;
	}
	public void setFloor(Long floor) {
		this.floor = floor;
	}
	
	public Long getApt_number() {
		return apt_number;
	}
	public void setApt_number(Long apt_number) {
		this.apt_number = apt_number;
	}
	
	public Apartment(Long id, Long floor, Long apt_number, boolean availability, int numTenants) {
		super();
		this.id = id;
		this.floor = floor;
		this.apt_number = apt_number;
		this.availability = availability;
		this.numTenants = numTenants;
	}
//	@ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "owner_id", nullable = false)
//    private Owner owner;
	
	public int getNumTenants() {
		return numTenants;
	}
	public void setNumTenants(int numTenants) {
		this.numTenants = numTenants;
	}

	@Override
	public String toString() {
		return "Apartment [id=" + id + ", floor=" + floor + ", apt_number=" + apt_number + ", availability="
				+ availability + ", numTenants=" + numTenants + "]";
	}
	public Apartment() {}
}