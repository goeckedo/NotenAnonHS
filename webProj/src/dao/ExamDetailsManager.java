package dao;

import java.util.Collection;

import entity.Exam;
import entity.ExamDetails;

public class ExamDetailsManager extends GenericManager {

	public ExamDetailsManager(String persistanceUnit) {
		super(persistanceUnit, ExamDetails.class);
	}

	public void create() {
	}
	
	@SuppressWarnings("unchecked")
	public Collection<ExamDetails> getExamDetails(Exam exam){
		System.out.println("in ExamDetailsManager: getExamDetails");
		System.out.println("select ed from ExamDetails ed WHERE ed.exam.examID =" +exam.getExamID());
		return super.getEm().createQuery("select ed from ExamDetails ed WHERE ed.exam.pNR =\'" +exam.getpNR()+ "\'").getResultList();
	}
	
	public ExamDetails findByLottoID(int lottoID){
		return (ExamDetails) super.getEm().createQuery("select ed from ExamDetails ed WHERE ed.examLotto =\'" +lottoID+ "\'").getSingleResult();
	}
	
}
