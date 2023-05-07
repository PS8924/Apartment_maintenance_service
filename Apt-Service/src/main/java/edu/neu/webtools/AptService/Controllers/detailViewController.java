package edu.neu.webtools.AptService.Controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import edu.neu.webtools.AptService.config.Authorizer;
import edu.neu.webtools.AptService.dao.ApartmentDAO;
import edu.neu.webtools.AptService.dao.ServiceRequestDAO;
import edu.neu.webtools.AptService.dao.StaffDAO;
import edu.neu.webtools.AptService.dao.TenantDAO;
import edu.neu.webtools.AptService.model.Apartment;
import edu.neu.webtools.AptService.model.RoleType;
import edu.neu.webtools.AptService.model.Tenant;
import edu.neu.webtools.AptService.model.ServiceRequest;
import edu.neu.webtools.AptService.model.Staff;
import jakarta.servlet.http.HttpSession;
import javassist.NotFoundException;

@Controller
@RequestMapping("/detail")
public class detailViewController {
	@Autowired
	private TenantDAO tenantDao;

	@Autowired
	private ApartmentDAO apartmentDao;

	@Autowired
	private StaffDAO staffDao;

	@Autowired
	private ServiceRequestDAO requestDao;

	@Autowired
	private ServiceRequestDAO ServiceDao;
	
	@Autowired
	private HttpSession session;
	
	@Autowired
	private Authorizer auth;

	@GetMapping("/apartment")
	public String getApartmentList() throws NotFoundException {
		
		if (auth.configure(session).addRole(RoleType.OWNER).authenticate())
			return "redirect:/apt/login";
		
		List<Apartment> apt = apartmentDao.list();
		session.setAttribute("all_apartments", apt);

		List<String> header = new ArrayList();
		header.add("Apartment No");
		header.add("Floor");
		header.add("Availability");
		header.add("No Of Tenants");

		session.setAttribute("allheader", header);
		return "/detailView";
	}

	@GetMapping("/tenant")
	public String getTenantList() throws NotFoundException {
		
		if (auth.configure(session).addRole(RoleType.OWNER).authenticate())
			return "redirect:/apt/login";
		
		List<Tenant> tenant = tenantDao.list();
		session.setAttribute("all_tenants", tenant);

		List<String> header = new ArrayList();
		header.add("First Name");
		header.add("Last Name");
		header.add("Email");
		header.add("Username");
		header.add("Apartment Number");
		header.add("Action");

		session.setAttribute("allheader", header);
		return "/showTenants";
	}
	
	@GetMapping("/staff")
	public String getStaffList() throws NotFoundException {
		
		if (auth.configure(session).addRole(RoleType.STAFF).authenticate())
			return "redirect:/apt/login";
		
		List<Staff> staff = staffDao.list();
		session.setAttribute("all_staff", staff);

		List<String> header = new ArrayList();
		header.add("First Name");
		header.add("Last Name");
		header.add("Email");
		header.add("Username");
		header.add("Offering Service");
		header.add("Action");

		session.setAttribute("allheader", header);
		return "/staffShowData";
	}

	@GetMapping("/complaints/{staffId}")
	public String getComplaintListByStaff( @PathVariable("staffId") int staffId)
			throws NotFoundException {
		
		if (auth.configure(session).addRole(RoleType.STAFF).authenticate())
			return "redirect:/apt/login";
		
		// get staff
		Staff found = staffDao.get(staffId);

		List<ServiceRequest> service = requestDao.getListById(found);
		session.setAttribute("staff_complaints", service);

		List<String> header = new ArrayList();
		header.add("Service Name");
		header.add("Service Status");
		header.add("Description");
		header.add("Apartment Number");
		header.add("Action");

		session.setAttribute("allheader", header);
		return "/StaffComplaints";
	}
	
	@GetMapping("/complaints")
	public String getComplaintList()
			throws NotFoundException {

		if (auth.configure(session).addRole(RoleType.OWNER).authenticate())
			return "redirect:/apt/login";
		
		List<ServiceRequest> service = requestDao.list();
		session.setAttribute("all_complaints", service);

		List<String> header = new ArrayList();
		header.add("Service Name");
		header.add("Service Type");
		header.add("Service Status");
		header.add("Description");
		header.add("Apartment Number");

		session.setAttribute("allheader", header);
		return "/showAllComplaints";
	}
	
	@GetMapping("/complaints/search/{action}")
	public String getComplaintSearchList(@PathVariable("action") String action)
			throws NotFoundException {
		if (auth.configure(session).addRole(RoleType.OWNER).authenticate())
			return "redirect:/apt/login";
		
		List<ServiceRequest> service = requestDao.getListBySearch(action);
		session.setAttribute("all_complaints", service);

		List<String> header = new ArrayList();
		header.add("Service Name");
		header.add("Service Type");
		header.add("Service Status");
		header.add("Description");
		header.add("Apartment Number");

		session.setAttribute("allheader", header);
		return "/showAllComplaints";
	}

}