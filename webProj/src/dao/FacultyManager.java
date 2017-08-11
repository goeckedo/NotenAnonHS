package dao;

import java.util.Collection;

import entity.Faculty;

public class FacultyManager extends GenericManager{

	public FacultyManager(String persistenceUnit) {
		super(persistenceUnit, Faculty.class);
	}

	public Collection<Faculty> getFacultyByToken(String stg) {
		return super.getEm().createQuery("SELECT f FROM Faculty f "
				+ "WHERE f.token = \'" +stg+ "\'").getResultList();
	}
}
