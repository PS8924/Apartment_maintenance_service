package edu.neu.webtools.AptService.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/apt-service.htm")
public class HomeController {
	@GetMapping
	public ModelAndView loadHomePage() {
		return new ModelAndView("homepage");
	}
}