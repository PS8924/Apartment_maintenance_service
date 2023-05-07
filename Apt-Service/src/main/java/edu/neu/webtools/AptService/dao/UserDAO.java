package edu.neu.webtools.AptService.dao;

import java.util.Base64;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import edu.neu.webtools.AptService.model.User;
import javassist.NotFoundException;

@Repository
public class UserDAO extends DAO{
	public UserDAO() {
	}
	
	public User get(int id) throws NotFoundException {

		try {
			// Fetch user object from the database based on id
			begin();
			User user = getSession().get(User.class, id);
			commit();
			close();

			return user;
		} catch (HibernateException e) {

			rollback();
			// throw new AdException("Could not fetch user with id: " + id, e);
			throw new NotFoundException("Exception while fetching user with id: " + id + ", " + e.getMessage());
		}
	}
	
	public User create(User user) throws NotFoundException {
		try {

			// save user object in the database
			begin();
			byte[] bytes = user.getPassword().getBytes();
			((User) user).setPassword(new String(Base64.getEncoder().encode(bytes)));
			getSession().save(user);
			commit();
			close();

			return user;
		} catch (HibernateException e) {
			rollback();
			// throw new AdException("Could not create user " + username, e);
			throw new NotFoundException("Exception while creating user: " + e.getMessage());
		}
	}

	public void delete(User user) throws NotFoundException {
		try {
			// save user object in the database
			begin();
			getSession().delete(user);
			commit();

		} catch (HibernateException e) {
			rollback();
			// throw new AdException("Could not create user " + username, e);
			throw new NotFoundException("Exception while deleting user: " + e.getMessage());
		}
	}
	
	public User update(User user) throws NotFoundException {
		try {
	        // Update the existing author object in the database
	        begin();
	        getSession().update(user);
	        commit();
	        close();
	        return user; // Return the updated Author object	
		} catch (HibernateException e) {
	        rollback();
	        // throw new AdException("Could not update user " + existingUser.getId(), e);
	        throw new NotFoundException("Exception while updating user with id: " + user.getId() + ", "
	                + e.getMessage());
	    }	
	}
	
	public List<User> list() throws NotFoundException {
		try {
			// Fetch all user objects from the database
			begin();
			Query query = getSession().createQuery("from User");
			List<User> userList = query.list();
			commit();
			close();

			return userList;
		} catch (HibernateException e) {
			rollback();
			// throw new AdException("Could not fetch user list", e);
			throw new NotFoundException("Exception while getting user list: " + e.getMessage());
		}
	}
	
	public User authenticate(User user) throws NotFoundException {

		try {
			// Fetch user object from the database based on username and password
			begin();
			Query q = getSession().createQuery("from User where username = :username and password = :password");
			q.setString("username", user.getUsername());
			q.setString("password", user.getPassword());
//			q.setRoleType("role", user.getRole());
			User found = (User) q.uniqueResult();
			close();
			return found;
		} catch (HibernateException e) {
			rollback();
			throw new NotFoundException("Exception while fetching user with id ");
		}
	}
	
	public User authenticate(String username, String password) throws NotFoundException {

		try {
			// Fetch user object from the database based on username and password
			begin();

			// get user from username
			Query q = getSession().createQuery("from User where username = :username");
			q.setString("username", username);
			//q.setString("password", password);
			User found = (User) q.uniqueResult();
			close();

			if (found != null) {
				return found;

			}

			return null;
		} catch (HibernateException e) {
			rollback();
			throw new NotFoundException("Exception while fetching user with id ");
		}
	}
}