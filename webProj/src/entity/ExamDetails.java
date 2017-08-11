package entity;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "examDetails")
public class ExamDetails {

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="ExamDetPK")
	@SequenceGenerator(name="ExamDetPK", sequenceName="examdet_seq", allocationSize=1)
	private int examDetID;
	
	private int examLotto;  		//lotto nummer

	//Zusammengesetzter pk?
	@ManyToOne(cascade=CascadeType.MERGE)
	@JoinColumn(name="studentID", referencedColumnName="studentID")
	private Student stud;				//FK Stud matnr
	@ManyToOne(cascade=CascadeType.MERGE)
	@JoinColumn(name="examID", referencedColumnName="examID")
	private Exam exam;				//FK Prüfung

	private Date changeDate; 		//last change
		
	//Import Daten
	private String vert;		//leer?
	private int bewertung;		// Note ohne komma
	private String pstatus; 	// Bestanden oder nicht bestanden -- Automatisch je nach Note ?
	private int pversuch;		//Prüfungsversuch
	private String labnr;	
	private String pordnr;		//Prüfungsordnung
	private String porgnr;		//Prüfungsordnung
	private String bonus;		//immer 0?
	private String malus;		//immer 0?
	
	public ExamDetails(){
		this(1,null,null,new Date(),"",0,"",0,"","","","","");
	}
	
	public ExamDetails(int examLotto, Student stud, Exam exam, Date changeDate, String vert,
			int bewertung, String pstatus, int pversuch, String labnr, String pordnr, String porgnr, String bonus,
			String malus) {
		this.examLotto = examLotto;
		this.stud = stud;
		this.exam = exam;
		this.changeDate = changeDate;
		this.vert = vert;
		this.bewertung = bewertung;
		this.pstatus = pstatus;
		this.pversuch = pversuch;
		this.labnr = labnr;
		this.pordnr = pordnr;
		this.porgnr = porgnr;
		this.bonus = bonus;
		this.malus = malus;
	}
		
	

	public int getExamDetID() {
		return examDetID;
	}



	public void setExamDetID(int examDetID) {
		this.examDetID = examDetID;
	}



	public int getExamLotto() {
		return examLotto;
	}



	public void setExamLotto(int examLotto) {
		this.examLotto = examLotto;
	}



	public Student getStud() {
		return stud;
	}



	public void setStud(Student stud) {
		this.stud = stud;
	}



	public Exam getExam() {
		return exam;
	}



	public void setExam(Exam exam) {
		this.exam = exam;
	}



	public Date getChangeDate() {
		return changeDate;
	}



	public void setChangeDate(Date changeDate) {
		this.changeDate = changeDate;
	}



	public String getVert() {
		return vert;
	}



	public void setVert(String vert) {
		this.vert = vert;
	}



	public int getBewertung() {
		return bewertung;
	}



	public void setBewertung(int bewertung) {
		this.bewertung = bewertung;
	}



	public String getPstatus() {
		return pstatus;
	}



	public void setPstatus(String pstatus) {
		this.pstatus = pstatus;
	}



	public int getPversuch() {
		return pversuch;
	}



	public void setPversuch(int pversuch) {
		this.pversuch = pversuch;
	}



	public String getLabnr() {
		return labnr;
	}



	public void setLabnr(String labnr) {
		this.labnr = labnr;
	}



	public String getPordnr() {
		return pordnr;
	}



	public void setPordnr(String pordnr) {
		this.pordnr = pordnr;
	}



	public String getPorgnr() {
		return porgnr;
	}



	public void setPorgnr(String porgnr) {
		this.porgnr = porgnr;
	}



	public String getBonus() {
		return bonus;
	}



	public void setBonus(String bonus) {
		this.bonus = bonus;
	}



	public String getMalus() {
		return malus;
	}



	public void setMalus(String malus) {
		this.malus = malus;
	}



	@Override
	public String toString() {
		return "ExamDetails [examDetID=" + examDetID + ", examLotto=" + examLotto + ", stud=" + stud.toString()
				+ ", exam=" + exam.toString() + ", changeDate=" + changeDate + ", vert=" + vert + ", bewertung=" + bewertung
				+ ", pstatus=" + pstatus + ", pversuch=" + pversuch + ", labnr=" + labnr + ", pordnr=" + pordnr
				+ ", porgnr=" + porgnr + ", bonus=" + bonus + ", malus=" + malus + "]";
	}
	
		
}
