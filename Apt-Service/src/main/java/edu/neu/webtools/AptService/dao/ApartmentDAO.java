package edu.neu.webtools.AptService.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import edu.neu.webtools.AptService.model.Apartment;
import edu.neu.webtools.AptService.model.Tenant;
import edu.neu.webtools.AptService.model.User;
import javassist.NotFoundException;

@Repository
public class ApartmentDAO extends DAO{
	
	public ApartmentDAO() {}
	
	public Apartment get(long id) throws NotFoundException {

		try {
			// Fetch user object from the database based on id
			begin();
			Apartment apartment = getSession().get(Apartment.class, id);
			commit();
			close();

			return apartment;
		} catch (HibernateException e) {

			rollback();
			// throw new AdException("Could not fetch user with id: " + id, e);
			throw new NotFoundException("Exception while fetching user with id: " + id + ", " + e.getMessage());
		}

	}
	
	public Apartment create(Apartment apartment) throws NotFoundException {
		try {

			// save user object in the database
			begin();
			getSession().save(apartment);
			commit();
			close();

			return apartment;
		} catch (HibernateException e) {
			rollback();
			// throw new AdException("Could not create user " + username, e);
			throw new NotFoundException("Exception while creating user: " + e.getMessage());
		}
	}
	
	public void delete(Apartment apartment) throws NotFoundException {
		try {
			// save user object in the database
			begin();
			getSession().delete(apartment);
			commit();

		} catch (HibernateException e) {
			rollback();
			// throw new AdException("Could not create user " + username, e);
			throw new NotFoundException("Exception while deleting user: " + e.getMessage());
		}
	}
	
	public Apartment update(Apartment apartment) throws NotFoundException {
		try {
	        // Update the existing author object in the database
	        begin();
	        getSession().update(apartment);
	        commit();
	        close();
	        return apartment; // Return the updated Author object	
		} catch (HibernateException e) {
	        rollback();
	        // throw new AdException("Could not update user " + existingUser.getId(), e);
	        throw new NotFoundException("Exception while updating user with id: " + apartment.getId() + ", "
	                + e.getMessage());
	    }	
	}
	
	public List<Apartment> list() throws NotFoundException {
		try {
			// Fetch all user objects from the database
			begin();
			Query query = getSession().createQuery("from Apartment");
			List<Apartment> apartmentList = query.list();
			commit();
			close();

			return apartmentList;
		} catch (HibernateException e) {
			rollback();
			// throw new AdException("Could not fetch user list", e);
			throw new NotFoundException("Exception while getting user list: " + e.getMessage());
		}
	}
}
