package test;

import java.util.Arrays;
import java.util.Collection;

import dao.NoSuchRow;
import dao.UserManager;
import entity.Faculty;
import entity.Login;
import entity.User_;

public class UserTest {

	public static void main(String[] args) {
		UserManager manager = new UserManager("webProj");
		User_ user;
		Faculty fac = new Faculty("Test","Test");

		Login log = new Login("admin","admin", fac);
		User_ user1 = new User_("TestUser","123421",fac ,log );
		Collection<User_> userCol;
		
			try {
				//GetByPK
				user = (User_)manager.findByPrimaryKey(1000);
				System.err.println(user.toString());
				//GetAll
				userCol = (Collection<User_>) manager.list();
				System.err.println(Arrays.toString(userCol.toArray()));
				//Insert new User
				manager.save(user1);
				
			} catch (NoSuchRow e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		//GetAll
		userCol = (Collection<User_>) manager.list();
		System.out.println(Arrays.toString(userCol.toArray()));
	}

}
