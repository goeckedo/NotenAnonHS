package dao;

import java.util.Collection;

import entity.Exam;
import entity.User_;

public class ExamManager extends GenericManager{
	
	public ExamManager(String persistanceUnit) {
		super(persistanceUnit, Exam.class);
	}

	public Collection<Exam> getExamByExamid(String examID) {
		examID = examID.replaceFirst(".0", "");
//		System.out.println("-----------"+exID);
		return super.getEm().createQuery("SELECT e FROM Exam e "
				+ "WHERE e.pNR = \'" +examID+"\'").getResultList();
	}

	
	public Collection<Exam> getExamByPruefer(int pruefer) {
			System.out.println("SELECT o FROM Exam o WHERE o.user = \""+pruefer+"\"");
			return super.getEm().createQuery("SELECT o FROM Exam o WHERE o.user.userID = \'"+pruefer+"\'").getResultList();
	}
	
	public User_ getUserByID(int id) {
		System.out.println("SELECT o FROM User_ o WHERE o.userID = \'"+id+"\'");
		return (User_) super.getEm().createQuery("SELECT o FROM User_ o WHERE o.userID = \'"+id+"\'").getSingleResult();
	}
	
	public Collection<Exam> getExamsByUserId(int userId){
		return super.getEm().createQuery("select o from Exam o where o.user.userID like "+userId).getResultList();
	}
		
}
