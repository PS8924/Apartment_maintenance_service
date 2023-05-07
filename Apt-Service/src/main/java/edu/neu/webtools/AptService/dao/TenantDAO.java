package edu.neu.webtools.AptService.dao;

import java.util.Base64;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import edu.neu.webtools.AptService.model.Tenant;
import edu.neu.webtools.AptService.model.User;
import edu.neu.webtools.AptService.model.Apartment;
import edu.neu.webtools.AptService.dao.ApartmentDAO;
import javassist.NotFoundException;

@Repository
public class TenantDAO extends DAO {

	@Autowired
    private ApartmentDAO apartmentDao;	
	
public TenantDAO() {}
	
	public Tenant get(int id) throws NotFoundException {

		try {
			// Fetch user object from the database based on id
			begin();
			Tenant tenant = getSession().get(Tenant.class, id);
			commit();
			close();

			return tenant;
		} catch (HibernateException e) {

			rollback();
			// throw new AdException("Could not fetch user with id: " + id, e);
			throw new NotFoundException("Exception while fetching user with id: " + id + ", " + e.getMessage());
		}

	}
	
	public Tenant create(Tenant tenant) throws NotFoundException {
		try {
			// save user object in the database
			begin();
			byte[] bytes = tenant.getPassword().getBytes();
			((Tenant) tenant).setPassword(new String(Base64.getEncoder().encode(bytes)));
			getSession().save(tenant);
			commit();
			close();

			return tenant;
		} catch (HibernateException e) {
			rollback();
			// throw new AdException("Could not create user " + username, e);
			throw new NotFoundException("Exception while creating user: " + e.getMessage());
		}
	}
	
	public void delete(Tenant tenant) throws NotFoundException {
		try {
			// save user object in the database
			begin();
			getSession().delete(tenant);
			commit();

		} catch (HibernateException e) {
			rollback();
			// throw new AdException("Could not create user " + username, e);
			throw new NotFoundException("Exception while deleting user: " + e.getMessage());
		}
	}
	
	public void deleteTenantById(int tenantId) throws NotFoundException {
		try {
			Tenant tenant = getSession().load(Tenant.class, tenantId);
			if(tenant != null) {
				begin();
				getSession().delete(tenant);
				commit();
			}
		}catch (HibernateException e) {
	        rollback();
	        // throw new AdException("Could not update user " + existingUser.getId(), e);
	        throw new NotFoundException("Exception while updating user with id: " + tenantId + ", "
	                + e.getMessage());
	    }	
	}
	
	public User update(Tenant tenant) throws NotFoundException {
		try {
	        // Update the existing author object in the database
	        begin();
	        getSession().update(tenant);
	        commit();
	        close();
	        return tenant; // Return the updated Author object	
		} catch (HibernateException e) {
	        rollback();
	        // throw new AdException("Could not update user " + existingUser.getId(), e);
	        throw new NotFoundException("Exception while updating user with id: " + tenant.getId() + ", "
	                + e.getMessage());
	    }	
	}
	
	public List<Tenant> list() throws NotFoundException {
		try {
			// Fetch all user objects from the database
			begin();
			Query query = getSession().createQuery("from Tenant");
			List<Tenant> tenantList = query.list();
			commit();
			close();

			return tenantList;
		} catch (HibernateException e) {
			rollback();
			// throw new AdException("Could not fetch user list", e);
			throw new NotFoundException("Exception while getting user list: " + e.getMessage());
		}
	}
}