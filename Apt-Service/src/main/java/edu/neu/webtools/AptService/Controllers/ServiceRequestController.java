package edu.neu.webtools.AptService.Controllers;

import java.lang.annotation.IncompleteAnnotationException;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import edu.neu.webtools.AptService.dao.UserDAO;
import edu.neu.webtools.AptService.model.Apartment;
import edu.neu.webtools.AptService.model.RoleType;
import edu.neu.webtools.AptService.model.Tenant;
import edu.neu.webtools.AptService.dao.StaffDAO;
import edu.neu.webtools.AptService.dao.TenantDAO;
import edu.neu.webtools.AptService.model.ServiceRequest;
import edu.neu.webtools.AptService.model.Staff;
import jakarta.servlet.http.HttpSession;
import javassist.NotFoundException;
import edu.neu.webtools.AptService.dao.ServiceRequestDAO;
import edu.neu.webtools.AptService.config.Authorizer;
import edu.neu.webtools.AptService.dao.ApartmentDAO;

@Controller
@RequestMapping("/service")
public class ServiceRequestController {
	@Autowired
	private UserDAO userDao;

	@Autowired
	private ServiceRequestDAO serviceRequestDao;

	@Autowired
	private ApartmentDAO apartmentDao;

	@Autowired
	private TenantDAO tenantDao;

	@Autowired
	private StaffDAO staffDao;
	
	@Autowired
	private HttpSession session;
	
	@Autowired
	private Authorizer auth;

	@GetMapping
	public String getApartmentList() throws NotFoundException {
		
		if (auth.configure(session).addRole(RoleType.TENANT).authenticate())
			return "redirect:/apt/login";
		
		session.setAttribute("aptId", ((Tenant) session.getAttribute("authorized")).getApartment().getId());

		List<Staff> staff = staffDao.list();
		List<Map<String, Object>> staffIdsAndTypes = staff.stream().map(s -> {
			Map<String, Object> map = new HashMap<>();
			map.put("id", s.getId());
			map.put("personType", s.getPerson_type());
			return map;
		}).collect(Collectors.toList());

		ObjectMapper objectMapper = new ObjectMapper();
		String staffJson;
		try {
			staffJson = objectMapper.writeValueAsString(staffIdsAndTypes);
			session.setAttribute("all_staff", staffJson);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "/RaiseRequest";
	}

	@PostMapping("/{tenantId}/apt/{aptId}/staff/{staffId}")
	public ResponseEntity<String> createServiceRequest(@RequestBody ServiceRequest service,
			@PathVariable("tenantId") int tenantId, @PathVariable("aptId") int aptId,
			@PathVariable("staffId") int StaffId) throws NotFoundException {
		Apartment found = apartmentDao.get(aptId);
		service.setApartment(found);

		Tenant tenant = tenantDao.get(tenantId);
		service.setTenant(tenant);

		Staff staff = staffDao.get(StaffId);
		service.setStaff(staff);

		serviceRequestDao.create(service);
		return new ResponseEntity<String>("CREATED", HttpStatus.CREATED);
	}

	@PutMapping("/request/{requestId}")
	public ResponseEntity<String> updtaeServiceRequest(@RequestBody ServiceRequest service,
			@PathVariable("requestId") int requestId) throws NotFoundException {

		ServiceRequest found = serviceRequestDao.get(requestId);
		found.setService_status(service.getService_status());
		serviceRequestDao.update(found);

		return new ResponseEntity<String>("CREATED", HttpStatus.CREATED);
	}
}