package edu.neu.webtools.AptService.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import edu.neu.webtools.AptService.config.Authorizer;
import edu.neu.webtools.AptService.dao.StaffDAO;
import edu.neu.webtools.AptService.model.RoleType;
import edu.neu.webtools.AptService.model.Staff;
import jakarta.servlet.http.HttpSession;
import javassist.NotFoundException;

@Controller
@RequestMapping("/staff")
public class StaffController {
	
	@Autowired
	private StaffDAO staffDao;
	
	@Autowired
	private HttpSession session;
	
	@Autowired
	private Authorizer auth;
	
	@GetMapping
	public String getApartmentList() throws NotFoundException {
		if (auth.configure(session).addRole(RoleType.OWNER).authenticate())
			return "redirect:/apt/login";
		
		return "/StaffForm";
	}
	
	@PostMapping("/create")
    public ResponseEntity<String> createUser(@RequestBody Staff staff) throws NotFoundException {
		staffDao.create(staff);
		return new ResponseEntity<String>("CREATED", HttpStatus.CREATED);
    }
}