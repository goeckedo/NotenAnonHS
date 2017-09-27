package entity;

import java.io.Serializable;
import javax.persistence.*;

@Entity
@Table(name = "examlabortable")

public class ExamLabor implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	private int examLaborID;
	private int examFK;
	private int laborFK;

	public ExamLabor() {

	}

	public ExamLabor(int examFK, int laborFK) {
		this.examFK = examFK;
		this.laborFK = laborFK;
	}

	public int getExamLaborID() {
		return examLaborID;
	}

	public int getExamFK() {
		return examFK;
	}

	public void setExamFK(int examFK) {
		this.examFK = examFK;
	}

	public int getLaborFK() {
		return laborFK;
	}

	public void setLaborFK(int laborFK) {
		this.laborFK = laborFK;
	}

	@Override
	public String toString() {
		return "ExamLabor [examLaborID=" + examLaborID + ", examFK=" + examFK + ", laborFK=" + laborFK + "]";
	}
}
