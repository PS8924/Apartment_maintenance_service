package edu.neu.webtools.AptService.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import edu.neu.webtools.AptService.dao.UserDAO;
import edu.neu.webtools.AptService.model.RoleType;
import edu.neu.webtools.AptService.model.User;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import javassist.NotFoundException;

@Controller
@RequestMapping("/apt/login")
public class LoginController {

	@Autowired
	private UserDAO userdao;

	@Autowired
	private HttpSession session;
	
	@GetMapping
	public String landingPage() {
		return "login"; //view name
	}

	@PostMapping
	public String login(@RequestParam("username") String username,
			@RequestParam("password") String password) throws NotFoundException {
		
		//session.removeAttribute("authorized");
		//session.invalidate();
		
		// authorize user
		User authorized = userdao.authenticate(username, password);
		
		if(authorized == null) {
			return "redirect:/apt/login";
		}else {
			// store authorized user in session
			session.setAttribute("username", authorized.getFirstName());
			session.setAttribute("authorized", authorized);
			session.setAttribute("role", authorized.getRole());
			RoleType role = authorized.getRole();
			
			if (role.equals(RoleType.OWNER)) {
				return "owner-dashboard";
			} else if (role.equals(RoleType.TENANT)) {
				return "tenant-dashboard";
			} else {
				return "staff-dashboard";
			}
		}
	}
	
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		session.setAttribute("authorized", null);
		session.invalidate();
		return "redirect:/apt/login";
	}
}