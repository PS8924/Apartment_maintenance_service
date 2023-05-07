package edu.neu.webtools.AptService.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import edu.neu.webtools.AptService.dao.*;
import edu.neu.webtools.AptService.model.User;
import jakarta.servlet.http.HttpServletRequest;
import javassist.NotFoundException;

@Controller
@RequestMapping("/user/add")
public class UserController {
	@Autowired
	private UserDAO userDAO;

	@PostMapping
	public void signUp(@RequestBody User user) throws NotFoundException {

		// authorize user
		User authorized = userDAO.create(user);

	}
}