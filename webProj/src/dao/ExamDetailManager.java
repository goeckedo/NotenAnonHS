package dao;

import java.util.Collection;

import entity.Exam;
import entity.ExamDetails;
import entity.Student;

public class ExamDetailManager extends GenericManager {

	public ExamDetailManager(String persistanceUnit) {
		super(persistanceUnit, ExamDetails.class);
	}
	
	public Collection<ExamDetails> getEDByPidName(String id, String name) {
		return super.getEm().createQuery("SELECT ed FROM ExamDetails ed WHERE ed.exam.sortname = \'" + name + "\' and ed.exam.pNR = \'" + id +"\' ").getResultList();
	}
	
	public Collection<Exam> getDetails(Student stud, Exam exam) {
		return super.getEm().createQuery("SELECT ed FROM ExamDetails ed "
				+ "WHERE ed.stud.studentID = \'" +stud.getStudID()+ "\' and ed.exam.examID = \'" +exam.getExamID()+ "\'").getResultList();
	}
}
