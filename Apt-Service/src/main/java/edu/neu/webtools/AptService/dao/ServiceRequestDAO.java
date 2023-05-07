package edu.neu.webtools.AptService.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import edu.neu.webtools.AptService.model.ServiceRequest;
import edu.neu.webtools.AptService.model.Staff;
import edu.neu.webtools.AptService.model.User;
import javassist.NotFoundException;

@Repository
public class ServiceRequestDAO extends DAO {

	public ServiceRequestDAO() {
	}

	public ServiceRequest get(long id) throws NotFoundException {

		try {
			// Fetch user object from the database based on id
			begin();
			ServiceRequest service = getSession().get(ServiceRequest.class, id);
			commit();
			close();

			return service;
		} catch (HibernateException e) {

			rollback();
			// throw new AdException("Could not fetch user with id: " + id, e);
			throw new NotFoundException("Exception while fetching user with id: " + id + ", " + e.getMessage());
		}

	}

	public ServiceRequest create(ServiceRequest service) throws NotFoundException {
		try {

			// save user object in the database
			begin();
			getSession().save(service);
			commit();
			close();

			return service;
		} catch (HibernateException e) {
			rollback();
			// throw new AdException("Could not create user " + username, e);
			throw new NotFoundException("Exception while creating user: " + e.getMessage());
		}
	}

	public void delete(ServiceRequest service) throws NotFoundException {
		try {
			// save user object in the database
			begin();
			getSession().delete(service);
			commit();

		} catch (HibernateException e) {
			rollback();
			// throw new AdException("Could not create user " + username, e);
			throw new NotFoundException("Exception while deleting user: " + e.getMessage());
		}
	}

	public List<ServiceRequest> list() throws NotFoundException {
		try {
			// Fetch all user objects from the database
			begin();
			Query query = getSession().createQuery("from ServiceRequest");
			List<ServiceRequest> serviceList = query.list();
			commit();
			close();

			return serviceList;
		} catch (HibernateException e) {
			rollback();
			// throw new AdException("Could not fetch user list", e);
			throw new NotFoundException("Exception while getting user list: " + e.getMessage());
		}
	}

	public List<ServiceRequest> getListById(Staff staff) throws NotFoundException {
		try {
			// Fetch all user objects from the database
			begin();
			Query query = getSession().createQuery("from ServiceRequest where staff = :staff");
			query.setEntity("staff", staff);
			List<ServiceRequest> serviceList = query.list();
			commit();
			close();

			return serviceList;
		} catch (HibernateException e) {
			rollback();
			// throw new AdException("Could not fetch user list", e);
			throw new NotFoundException("Exception while getting user list: " + e.getMessage());
		}
	}

	public List<ServiceRequest> getListBySearch(String criteria) throws NotFoundException {
		try {
			// Fetch all user objects from the database
			begin();
			Query query = getSession().createQuery("from ServiceRequest where service_status = :criteria");
			query.setParameter("criteria", criteria);
			List<ServiceRequest> serviceList = query.list();
			commit();
			close();

			return serviceList;
		} catch (HibernateException e) {
			rollback();
			// throw new AdException("Could not fetch user list", e);
			throw new NotFoundException("Exception while getting user list: " + e.getMessage());
		}
	}

	public ServiceRequest update(ServiceRequest req) throws NotFoundException {
		try {
			// Update the existing author object in the database
			begin();
			getSession().update(req);
			commit();
			close();
			return req; // Return the updated Author object
		} catch (HibernateException e) {
			rollback();
			// throw new AdException("Could not update user " + existingUser.getId(), e);
			throw new NotFoundException(
					"Exception while updating user with id: " + req.getId() + ", " + e.getMessage());
		}
	}
}
