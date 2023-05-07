package edu.neu.webtools.AptService.model;

import java.util.Date;

import javax.persistence.*;

@Entity
@Table(name="complaints")
public class Complaints {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false)
	private String complaint_name;
	
	@Column(nullable = false)
	private Date createdDate;
	
	public Date getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
	
	@Column(nullable = false)
	private String complaint_status;
	
	@Column(nullable = false)
	private String complaint_type;
	
	@Column(nullable = false)
	private String complaint_description;
	
	public String getComplaint_type() {
		return complaint_type;
	}
	public void setComplaint_type(String complaint_type) {
		this.complaint_type = complaint_type;
	}
	
	public String getComplaint_description() {
		return complaint_description;
	}
	public void setComplaint_description(String complaint_description) {
		this.complaint_description = complaint_description;
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getComplaint_name() {
		return complaint_name;
	}
	public void setComplaint_name(String complaint_name) {
		this.complaint_name = complaint_name;
	}
	
	public String getComplaint_status() {
		return complaint_status;
	}
	public void setComplaint_status(String complaint_status) {
		this.complaint_status = complaint_status;
	}
	
	
//	public Tenant getTenant() {
//		return tenant;
//	}
//	public void setTenant(Tenant tenant) {
//		this.tenant = tenant;
//	}
	
//	@OneToMany(fetch = FetchType.LAZY)
//    @JoinColumn(name = "tenant_id", nullable = false)
//    private Tenant tenant;
//	
//	@OneToMany(fetch = FetchType.LAZY)
//    @JoinColumn(name = "apt_id", nullable = false)
//	private Apartment apartment;

//	public Apartment getApartment() {
//		return apartment;
//	}
//	public void setApartment(Apartment apartment) {
//		this.apartment = apartment;
//	}
	
}