package entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "usertable")
public class User implements Serializable{
	
	
	private static final long serialVersionUID = 1L;
	@Id
	private int userID;
	private String userName;
	private String password;
	private String credentials;
	
	public User() {

	}

	public User(int userID, String userName, String password, String credentials) {
		super();
		this.userID = userID;
		this.userName = userName;
		this.password = password;
		this.credentials = credentials;
	}

	public int getUserID() {
		return userID;
	}

	public void setUserID(int userID) {
		this.userID = userID;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getCredentials() {
		return credentials;
	}

	public void setCredentials(String credentials) {
		this.credentials = credentials;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "User [userID=" + userID + ", userName=" + userName + ", password=" + password + ", credentials="
				+ credentials + "]";
	}
	
	
}

