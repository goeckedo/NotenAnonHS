package dao;


import java.util.Collection;

import entity.ExamDetails;
import entity.User_;

public class UserManager extends GenericManager{

	public UserManager(String persistanceUnit) {
		super(persistanceUnit, User_.class);
	}

	public Collection<User_> getUserByPerso(String persNo) {
		return super.getEm().createQuery("SELECT u FROM User_ u "
				+ "WHERE u.personalnummer = \'" +persNo+ "\'").getResultList();
	}
	
	public boolean persoNoExists(int perNo){
		boolean exists;
	
		if(super.getEm().createQuery("SELECT u FROM User_ u "
									+ "WHERE u.personalnummer = \'" +perNo+ "\'").getResultList().size()>0) {
			exists = true;
		} else {
			exists = false;
		}
				
		return exists;
	}
	
	public boolean usernameExists(String username){
		boolean exists;

		if(super.getEm().createQuery("SELECT u.lusername FROM Login u WHERE u.lusername = \'" + username + "\'").getResultList().size()>0) {
			exists = true;
		} else {
			exists = false;
		}
				
		return exists;
	}
	
	@SuppressWarnings("unchecked")
	public Collection<User_> sucheUser(String pid) {
		return super.getEm().createQuery("SELECT u FROM User_ u  WHERE UPPER(u.name) LIKE UPPER(\'" + pid + "%\')").getResultList();
	}
	
	public Collection<User_> getUserByName(String name) {
		return super.getEm().createQuery("SELECT u FROM User_ u  WHERE u.login.lusername = \'" + name + "\'").getResultList();
	}
	public Collection<ExamDetails> sucheExam(String pid) {
		return super.getEm().createQuery("SELECT a FROM ExamDetails a WHERE a.examLotto LIKE \'" + pid + "%\'").getResultList();
	}
	
}
