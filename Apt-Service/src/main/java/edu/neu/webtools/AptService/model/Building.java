package edu.neu.webtools.AptService.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="building")
public class Building {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column(nullable = false)
	private String name;
	
	@OneToMany(fetch = FetchType.LAZY)
	private Set<ServiceRequest> serviceRequests = new HashSet<ServiceRequest>();
}