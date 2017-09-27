package entity;

import javax.persistence.*;

@Entity
@Table(name = "studenttable")
public class Student {
	
	@Id
	private int mtkNr;
		
	public Student(){

	}
	
	public Student(int mtknr) {
		this.mtkNr = mtknr;
	}
	
	public int getMtknr() {
		return mtkNr;
	}

	public void setMtknr(int mtknr) {
		this.mtkNr = mtknr;
	}

	@Override
	public String toString() {
		return "Student [mtknr=" + mtkNr + "]";
	}
		
}
