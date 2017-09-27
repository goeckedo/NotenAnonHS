package entity;

import java.io.Serializable;
import javax.persistence.*;

@Entity
@Table(name = "labortable")

public class Labor implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	private int laborID;
	private String laborBuilding;
	private String laborRoom;

	public Labor() {

	}

	public Labor(String laborBuilding, String laborRoom) {
		this.laborBuilding = laborBuilding;
		this.laborRoom = laborRoom;
	}

	public int getLaborID() {
		return laborID;
	}

	public String getLaborBuilding() {
		return laborBuilding;
	}

	public void setLaborBuilding(String laborBuilding) {
		this.laborBuilding = laborBuilding;
	}

	public String getLaborRoom() {
		return laborRoom;
	}

	public void setLaborRoom(String laborRoom) {
		this.laborRoom = laborRoom;
	}

	@Override
	public String toString() {
		return "Labor [laborID=" + laborID + ", laborBuilding=" + laborBuilding + ", laborRoom=" + laborRoom + "]";
	}

}
