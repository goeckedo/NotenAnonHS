package test;

import dao.FacultyManager;
import entity.Faculty;

public class FacultyTest {
	public static void main(String[] args) {
		
		FacultyManager manager = new FacultyManager("XYZ");
		Faculty fac = new Faculty("Test","Test");
		manager.save(fac);
		manager.close();
		
		
		
		
	}
}
