package edu.neu.webtools.AptService.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import edu.neu.webtools.AptService.config.Authorizer;
import edu.neu.webtools.AptService.dao.ApartmentDAO;
import edu.neu.webtools.AptService.model.Apartment;
import edu.neu.webtools.AptService.model.RoleType;
import edu.neu.webtools.AptService.model.Tenant;
import edu.neu.webtools.AptService.model.User;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import javassist.NotFoundException;

@Controller
@RequestMapping("/apartment/create")

public class ApartmentController {
	@Autowired
	private ApartmentDAO apartmentDao;

	@Autowired
	private HttpSession session;
	
	@Autowired
	private Authorizer auth;

	@GetMapping
	public String loadHomePage() {
		if (auth.configure(session).addRole(RoleType.OWNER).authenticate())
			return "redirect:/apt/login";
		return "/apartmentForm";
	}

	@PostMapping("/{new}")
	public ModelAndView addApartment(Apartment apartment, HttpServletRequest req) throws NotFoundException {
		apartmentDao.create(apartment);
		return new ModelAndView("/apartmentForm");
	}
}