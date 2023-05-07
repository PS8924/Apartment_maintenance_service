package edu.neu.webtools.AptService.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import edu.neu.webtools.AptService.model.RoleType;
import edu.neu.webtools.AptService.model.User;
import jakarta.servlet.http.HttpSession;

@Component
public class Authorizer {

	private List<RoleType> permissibles = new ArrayList<>();
	private User user;

	public boolean authenticate() {
		if (user == null)
			return true;
		if (permissibles.contains(user.getRole()))
			return false;
		return true;
	}

	public List<RoleType> getPermissibles() {
		return permissibles;
	}

	public Authorizer configure(HttpSession session) {
		this.permissibles = new ArrayList<>();
		this.user = (User) session.getAttribute("authorized");
		return this;
	}

	public void setPermissibles(List<RoleType> permissibles) {
		this.permissibles = permissibles;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Authorizer addRole(RoleType role) {
		this.permissibles.add(role);
		return this;
	}

}
