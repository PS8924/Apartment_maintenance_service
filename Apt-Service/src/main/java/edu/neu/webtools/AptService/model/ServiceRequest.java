package edu.neu.webtools.AptService.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="service_request")
public class ServiceRequest{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false)
	private String service_name;
	
	@Column(nullable = false)
	private Date createdDate;
	
	@Column(nullable = false)
	private String service_status;
	
	@Column(nullable = false)
	@Enumerated(EnumType.STRING)
	private ComplaintType service_type;
	
	@Column(nullable = false)
	private String service_description;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getService_name() {
		return service_name;
	}
	public void setService_name(String service_name) {
		this.service_name = service_name;
	}
	
	
	public String getService_status() {
		return service_status;
	}
	public void setService_status(String service_status) {
		this.service_status = service_status;
	}
	
	public Tenant getTenant() {
		return tenant;
	}
	public void setTenant(Tenant tenant) {
		this.tenant = tenant;
	}
	
	public String getService_description() {
		return service_description;
	}
	public void setService_description(String service_description) {
		this.service_description = service_description;
	}
	
	public Date getCreatedDate() {
		return createdDate;
	}
	
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
	
	@Override
	public String toString() {
		return "ServiceRequest [id=" + id + ", service_name=" + service_name + ", createdDate=" + createdDate
				+ ", service_status=" + service_status + ", service_type=" + service_type + ", service_description="
				+ service_description + "]";
	}
	
	//	public Tenant getTenant() {
	//	return tenant;
	//}
	//public void setTenant(Tenant tenant) {
	//	this.tenant = tenant;
	//}
	
	//@OneToMany(fetch = FetchType.LAZY)
	//@JoinColumn(name = "tenant_id", nullable = false)
	//private Tenant tenant;
	//
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "apt_id", nullable = false)
	private Apartment apartment;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "tenant_id", nullable = false)
	private Tenant tenant;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "staff_id", nullable = false)
	private Staff staff;

	public ServiceRequest(Long id, String service_name, Date createdDate, String service_status,
			ComplaintType service_type, String service_description, Apartment apartment, Tenant tenant, Staff staff) {
		super();
		this.id = id;
		this.service_name = service_name;
		this.createdDate = createdDate;
		this.service_status = service_status;
		this.service_type = service_type;
		this.service_description = service_description;
		this.apartment = apartment;
		this.tenant = tenant;
		this.staff = staff;
	}
	
	public ServiceRequest() {}
	
	public Staff getStaff() {
		return staff;
	}
	public void setStaff(Staff staff) {
		this.staff = staff;
	}
	public ComplaintType getService_type() {
		return service_type;
	}
	public void setService_type(ComplaintType service_type) {
		this.service_type = service_type;
	}
	
	public Apartment getApartment() {
		return apartment;
	}
	public void setApartment(Apartment apartment) {
		this.apartment = apartment;
	}
	
//	public Staff getStaff() {
//		return staff;
//	}
//	public void setStaff(Staff staff) {
//		this.staff = staff;
//	}
	
	//public Apartment getApartment() {
	//	return apartment;
	//}
	//public void setApartment(Apartment apartment) {
	//	this.apartment = apartment;
	//}
}