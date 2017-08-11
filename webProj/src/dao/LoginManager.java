package dao;

import java.util.Collection;

import entity.Login;

public class LoginManager extends GenericManager{

	public LoginManager(String persistenceUnit) {
		super(persistenceUnit, Login.class);
	}
	
	@SuppressWarnings("unchecked")
	public Collection<Login> getUserByName(String user){
		System.out.println("SELECT l FROM Login l where username = \'"+user+"\'");
		return super.getEm().createQuery("SELECT o FROM Login o "
										+ "WHERE o.lusername = \'"+user+"\'").getResultList();
	}
	//UPDATE tabelle SET spalte = "ZU-ERSETZENDER-WERT";
		public void changePw(String user, String password){
			
			//try catch !!!
			
			super.beginTransaction();
			//String pwHash = super.hash1(password);
			
			super.getEm().createQuery("Update Login o set o.lpassword = \'"+password+"\'"
											+ " WHERE o.lusername = \'"+user+"\'").executeUpdate();
			super.commitTransaction();
		}
		public void setInactiv(String user){
			super.beginTransaction();
			
			super.getEm().createQuery("Update Login o set o.activ = 0"
											+ " WHERE o.lusername = \'"+user+"\'").executeUpdate();
			super.commitTransaction();
		}
		
		public void setActiv(String user){
			super.beginTransaction();
			
			super.getEm().createQuery("Update Login o set o.lpassword = 1"
											+ " WHERE o.lusername = \'"+user+"\'").executeUpdate();
			super.commitTransaction();
		}
		
		
	
	}
