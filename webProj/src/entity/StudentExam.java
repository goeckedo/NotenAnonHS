package entity;

import java.io.Serializable;
import javax.persistence.*;

@Entity
@Table(name = "studentexamtable")

public class StudentExam implements Serializable {

	
	private static final long serialVersionUID = 1L;
	@Id
	private int studentExamID;
	private int studentExamGrade;
	private int anonNR;
	private int studentFK;
	private int examFK;
	
	public StudentExam() {
		
	}
	
	public StudentExam(int studentExamGrade,int anonNR,int studentFK,int examFK){
		this.studentExamGrade = studentExamGrade;
		this.anonNR = anonNR;
		this.studentFK = studentFK;
		this.examFK = examFK;
	}

	public int getStudentExamID() {
		return studentExamID;
	}

	public int getStudentExamGrade() {
		return studentExamGrade;
	}

	public void setStudentExamGrade(int studentExamGrade) {
		this.studentExamGrade = studentExamGrade;
	}

	public int getAnonNR() {
		return anonNR;
	}

	public void setAnonNR(int anonNR) {
		this.anonNR = anonNR;
	}

	public int getStudentFK() {
		return studentFK;
	}

	public void setStudentFK(int studentFK) {
		this.studentFK = studentFK;
	}

	public int getExamFK() {
		return examFK;
	}

	public void setExamFK(int examFK) {
		this.examFK = examFK;
	}

	@Override
	public String toString() {
		return "ExamLabor [studentExamID=" + studentExamID + ", studentExamGrade=" + studentExamGrade + ", anonNR="
				+ anonNR + ", studentFK=" + studentFK + ", examFK=" + examFK + "]";
	}
	
	
   
}
