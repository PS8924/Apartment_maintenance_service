package edu.neu.webtools.AptService.dao;

import org.hibernate.HibernateException;
import org.springframework.stereotype.Repository;

import edu.neu.webtools.AptService.model.Building;
import javassist.NotFoundException;

@Repository
public class BuildingDAO extends DAO{
	public BuildingDAO() {}
	
	public Building get(long id) throws NotFoundException {

		try {
			// Fetch user object from the database based on id
			begin();
			Building building = getSession().get(Building.class, id);
			commit();
			close();

			return building;
		} catch (HibernateException e) {

			rollback();
			// throw new AdException("Could not fetch user with id: " + id, e);
			throw new NotFoundException("Exception while fetching user with id: " + id + ", " + e.getMessage());
		}

	}
	
	public Building create(Building building) throws NotFoundException {
		try {

			// save user object in the database
			begin();
			getSession().save(building);
			commit();
			close();

			return building;
		} catch (HibernateException e) {
			rollback();
			// throw new AdException("Could not create user " + username, e);
			throw new NotFoundException("Exception while creating building: " + e.getMessage());
		}
	}
}