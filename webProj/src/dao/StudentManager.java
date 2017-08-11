package dao;

import java.util.Collection;

import entity.Student;

public class StudentManager extends GenericManager {

	public StudentManager(String persistenceUnit) {
		super(persistenceUnit, Student.class);
	}
	
	public Collection<Student> getUserByMatrikel(String mtknr){
		System.out.println("SELECT s FROM Student s where s.mtknr = \'"+mtknr+"\'");
		return super.getEm().createQuery("SELECT s FROM Student s WHERE s.mtkNr = \'"+mtknr+"\'").getResultList();
//		return super.getEm().createQuery("SELECT s FROM Student s").getResultList();
	}
}
