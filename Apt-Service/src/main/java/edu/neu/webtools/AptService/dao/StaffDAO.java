package edu.neu.webtools.AptService.dao;

import java.util.Base64;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import edu.neu.webtools.AptService.model.Staff;
import edu.neu.webtools.AptService.model.Tenant;
import edu.neu.webtools.AptService.model.User;
import javassist.NotFoundException;

@Repository
public class StaffDAO extends DAO{
	public StaffDAO() {}
	
	public Staff get(int id) throws NotFoundException {

		try {
			// Fetch user object from the database based on id
			begin();
			Staff staff = getSession().get(Staff.class, id);
			commit();
			close();

			return staff;
		} catch (HibernateException e) {

			rollback();
			// throw new AdException("Could not fetch user with id: " + id, e);
			throw new NotFoundException("Exception while fetching user with id: " + id + ", " + e.getMessage());
		}

	}
	
	public Staff create(Staff staff) throws NotFoundException {
		try {

			// save user object in the database
			begin();
			byte[] bytes = staff.getPassword().getBytes();
			((Staff) staff).setPassword(new String(Base64.getEncoder().encode(bytes)));
			
			getSession().save(staff);
			commit();
			close();

			return staff;
		} catch (HibernateException e) {
			rollback();
			// throw new AdException("Could not create user " + username, e);
			throw new NotFoundException("Exception while creating user: " + e.getMessage());
		}
	}
	
	public void delete(Staff staff) throws NotFoundException {
		try {
			// save user object in the database
			begin();
			getSession().delete(staff);
			commit();

		} catch (HibernateException e) {
			rollback();
			// throw new AdException("Could not create user " + username, e);
			throw new NotFoundException("Exception while deleting user: " + e.getMessage());
		}
	}
	
	public User update(Staff staff) throws NotFoundException {
		try {
	        // Update the existing author object in the database
	        begin();
	        getSession().update(staff);
	        commit();
	        close();
	        return staff; // Return the updated Author object	
		} catch (HibernateException e) {
	        rollback();
	        // throw new AdException("Could not update user " + existingUser.getId(), e);
	        throw new NotFoundException("Exception while updating user with id: " + staff.getId() + ", "
	                + e.getMessage());
	    }	
	}
	
	public List<Staff> list() throws NotFoundException {
		try {
			// Fetch all user objects from the database
			begin();
			Query query = getSession().createQuery("from Staff");
			List<Staff> staffList = query.list();
			commit();
			close();

			return staffList;
		} catch (HibernateException e) {
			rollback();
			// throw new AdException("Could not fetch user list", e);
			throw new NotFoundException("Exception while getting user list: " + e.getMessage());
		}
	}

}
