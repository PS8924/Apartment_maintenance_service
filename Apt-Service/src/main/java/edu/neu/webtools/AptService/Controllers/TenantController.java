package edu.neu.webtools.AptService.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import edu.neu.webtools.AptService.model.Tenant;
import edu.neu.webtools.AptService.model.User;
import edu.neu.webtools.AptService.model.Apartment;
import edu.neu.webtools.AptService.model.RoleType;
import edu.neu.webtools.AptService.model.ServiceRequest;
import edu.neu.webtools.AptService.config.Authorizer;
import edu.neu.webtools.AptService.dao.ApartmentDAO;
import edu.neu.webtools.AptService.dao.TenantDAO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import javassist.NotFoundException;

@Controller
@RequestMapping("/tenant")
public class TenantController {

	@Autowired
	private HttpSession session;

	@Autowired
	private TenantDAO tenantDao;

	@Autowired
	private ApartmentDAO apartmentDao;

	@Autowired
	private Authorizer auth;

	@GetMapping
	public String getApartmentList() throws NotFoundException {

		if (auth.configure(session).addRole(RoleType.OWNER).authenticate()) {
			return "redirect:/apt/login";
		}else {
			session.setAttribute("all_apartments", apartmentDao.list());
			return "/TenantForm";
		}
	}
	
	@GetMapping("/show/{id}")
	public String getTenantList(@PathVariable("id") int id) throws NotFoundException {

		if (auth.configure(session).addRole(RoleType.TENANT).authenticate()) {
			return "redirect:/apt/login";
		}else {
			session.setAttribute("tenant", tenantDao.get(id));
			return "/tenantShowData";
		}
	}

//	@PostMapping("/{aptId}/create")
//	public ModelAndView addApartment(Tenant tenant, HttpServletRequest req) throws NotFoundException {
//		tenantDao.create(tenant);
//		return new ModelAndView("/owner-dashboard");
//	}

	@PostMapping("/{aptId}/create")
	public ResponseEntity<String> createUser(@RequestBody Tenant tenant, @PathVariable("aptId") int aptId)
			throws NotFoundException {
		Apartment apartment = apartmentDao.get(aptId);
		if (apartment.getNumTenants() >= 3) {
			throw new RuntimeException("Apartment is full");
		}
		apartment.setNumTenants(apartment.getNumTenants() + 1);

		if (apartment.getNumTenants() == 3) {
			apartment.setAvailability(false);
		}
		apartmentDao.update(apartment);
		tenantDao.create(tenant);
		return new ResponseEntity<String>("CREATED", HttpStatus.CREATED);
	}

	@PutMapping("/{id}/update")
	public String updateTenant(@PathVariable("id") int id, @RequestBody Tenant tenant)
			throws NotFoundException {

		Tenant found = tenantDao.get(id);
		
		found.setFirstName(tenant.getFirstName());
		found.setLastName(tenant.getLastName());
		found.setEmail(tenant.getEmail());
		
		tenantDao.update(found);
		return "success";
	}
	
	@DeleteMapping("/{tenantId}/{aptId}")
	
	public String deleteAuthor(@PathVariable("tenantId") int tenantId, @PathVariable("aptId") int aptId)
			throws NotFoundException {

		Apartment apartment = apartmentDao.get(aptId);
		apartment.setNumTenants(apartment.getNumTenants() - 1);
		apartment.setAvailability(true);
		apartmentDao.update(apartment);
		tenantDao.deleteTenantById(tenantId);
		return "success";
	}
}