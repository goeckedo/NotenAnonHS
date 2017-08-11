package dao;

import java.util.Collection;
import java.util.Date;

import entity.Exam;
import entity.ExamDetails;
import entity.Faculty;
import entity.Student;
import entity.User_;

public class ImportManager {

	//TODO Lotto nr, User übergeben
	private String abschl,stg,pnr,pversion,vert,sortname,
					mtknr,bewertung,pversuch,labnr,
					semester,pordnr,porgnr,bonus,malus,personalnummer,pstatus;
	private String pdatum;
	private String pers = "webProj";
	private StudentManager sm = new StudentManager(pers);
	private FacultyManager fm = new FacultyManager(pers);
	private UserManager um = new UserManager(pers);
	private ExamManager em = new ExamManager(pers);
	private ExamDetailManager edm = new ExamDetailManager(pers);

	public ImportManager(String abschl, String stg, String pnr, String pversion, String vert, String sortname,
			String mtknr, String bewertung, String pstatus, String pdatum, String pversuch,  String labnr, String semester,
			String pordnr, String porgnr, String bonus, String malus) {
		this.personalnummer = "1234";
		this.abschl = abschl;
		this.stg = stg;
		this.pnr = pnr.replaceFirst(".0", "");
		this.pversion = pversion.replaceFirst(".0", "");
		this.vert = vert;
		this.sortname = sortname;
		this.mtknr = mtknr.replaceFirst(".0", "");
		this.bewertung = bewertung.replaceFirst(".0", "");
		this.pdatum = pdatum.replaceFirst(".0", "");
		this.pversuch = pversuch;
		this.labnr = labnr;
		this.semester = semester;
		this.pordnr = pordnr;
		this.porgnr = porgnr;
		this.bonus = bonus;
		this.malus = malus;
		this.pstatus = pstatus;
	}

	@Override
	public String toString() {
		return "ImportManager [abschl=" + abschl + ", stg=" + stg + ", pnr=" + pnr + ", pversion=" + pversion
				+ ", vert=" + vert + ", sortname=" + sortname + ", mtknr=" + mtknr + ", bewertung=" + bewertung
				+ ", pversuch=" + pversuch + ", labnr=" + labnr + ", semester=" + semester + ", pordnr=" + pordnr
				+ ", porgnr=" + porgnr + ", bonus=" + bonus + ", malus=" + malus + ", personalnummer=" + personalnummer
				+ ", pstatus=" + pstatus + ", pdatum=" + pdatum + ", pers=" + pers + ", sm=" + sm + ", fm=" + fm
				+ ", um=" + um + ", em=" + em + ", edm=" + edm +"]";
	}



	public boolean insertCheckStatic(){
		//UserCheck
		Boolean bool = false;
		User_ pruefer = um.getUserByPerso("1234").iterator().next();
		if(!pruefer.getPersonalnummer().isEmpty()){
			System.out.println("User Existiert");
			//Exam Check
			if(!em.getExamByExamid(pnr).isEmpty()){
				System.err.println("ERROR - Prüfung Existiert bereits");
				bool = true;
			}else{
				System.out.println("Neue Prüfung Erstellen");
				Exam exam = new Exam(new Date(),pnr,pversion,sortname,pruefer.getFaculty(),pruefer);
				em.save(exam);
				bool = true;
			}
		}else{
			System.err.println("ERROR - User unbekannt");
			bool = false;
		}
		
		return bool;
	}
	
	public void insertCheckVariable(){		
		//Student
		Student stud;
		Faculty fac;
		if (!sm.getUserByMatrikel(mtknr).isEmpty()){
				System.err.println("ERROR - Student bereits vorhanden");
				stud = sm.getUserByMatrikel(mtknr).iterator().next();
			}else{
				System.out.println("Student erstellen");
				fac = fm.getFacultyByToken(stg).iterator().next();
				stud = new Student(semester,mtknr,abschl,fac);
				sm.save(stud);
			}	
		Exam exam = em.getExamByExamid(pnr).iterator().next();
		if(!edm.getDetails(stud, exam).isEmpty()){
			System.err.println("Student ist bereits in Prüfung");
		}else{
			System.out.println("Student wird in Prüfung geschupsst");
			ExamDetails ed = new ExamDetails(1, stud, exam, new Date(), vert, new Integer(bewertung.replaceFirst(".0", "")), pstatus, new Integer(pversuch), labnr, pordnr, porgnr, bonus, malus);
			edm.save(ed);
		}
		}
	}
