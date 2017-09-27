package test;

import java.util.Arrays;
import java.util.Collection;
import java.util.Date;

import dao.ExamDetailManager;
import dao.ExamManager;
import dao.StudentManager;
import dao.UserManager;
import entity.Exam;
import entity.ExamDetails;
import entity.Faculty;
import entity.Login;
import entity.Student;
import entity.User_;


public class importTest {
	public static void main(String[] args) {
		UserManager managerU = new UserManager("webProj");
		ExamManager managerE = new ExamManager("webProj");
		StudentManager managerS = new StudentManager("webProj");
		ExamDetailManager managerED = new ExamDetailManager("webProj");

		Faculty fac = new Faculty("Test","Test");

		Login log = new Login("admin","admin", fac);
			try {
				User_ user = (User_)managerU.findByPrimaryKey(1000);

				//UserCheck
				if(!managerU.getUserByPerso("1234").isEmpty()){
					System.out.println("User Existiert");
					Exam exam = managerE.getExamByExamid("1000").iterator().next();
					if(exam.getExamID() == 1000){
						System.err.println("ERROR - Prüfung Existiert bereits");
					}else{
						System.out.println("Neue Prüfung Erstellen");
						exam = new Exam(new Date(),"1000","1","TestExam",fac,user);
						managerE.save(exam);
					}
					Student stud = managerS.getUserByMatrikel("1234").iterator().next();

						System.err.println("ERROR - Student bereits vorhanden");

						System.out.println("Student erstellen");
						managerS.save(stud);

					if(!managerED.getDetails(stud, exam).isEmpty()){
						System.err.println("Student ist bereits in Prüfung");
					}else{
						System.out.println("Student wird in Prüfung geschupsst");
						ExamDetails ed = new ExamDetails(1, stud, exam, new Date(), "", 0, "BE", 1, "1234", "123", "1234", "0.0", "0.0");
						managerED.save(ed);
					}
				}else{
					System.err.println("ERROR - User unbekannt");
				}
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}


}
