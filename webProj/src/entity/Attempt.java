package entity;

import java.io.Serializable;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: Attempt
 *
 */
@Entity
@Table(name = "attempttable")

public class Attempt implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	private int attemptID;
	private int studentFK;
	private int examFK;
	private String attempt;
	
	public Attempt() {
		
	}
	
	public Attempt(int studentFK, int examFK, String attempt){
		this.studentFK = studentFK;
		this.examFK = examFK;
		this.attempt = attempt;
	}

	public int getAttemptID() {
		return attemptID;
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

	public String getAttempt() {
		return attempt;
	}

	public void setAttempt(String attempt) {
		this.attempt = attempt;
	}

	@Override
	public String toString() {
		return "Attempt [attemptID=" + attemptID + ", studentFK=" + studentFK + ", examFK=" + examFK + ", attempt="
				+ attempt + "]";
	}
	
	

}
