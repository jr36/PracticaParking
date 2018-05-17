package a.b.c;

import java.io.Serializable;
import java.sql.Timestamp;

public class DTOParking implements Serializable{

	private static final long serialVersionUID = 1L;
	private int registro;
	private String matricula;
	private int parkingId;
	private Timestamp timeStamp;
	
	public DTOParking(int regis, String matri, int parkid, Timestamp tm) {
		super();
		this.registro = regis;
		this.matricula = matri;
		this.parkingId = parkid;
		this.timeStamp = tm;
	}
	
	public DTOParking() {
		super();
	}

	public int getRegistro() {
		return registro;
	}

	public void setRegistro(int registro) {
		this.registro = registro;
	}

	public String getMatricula() {
		return matricula;
	}

	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}

	public int getParkingId() {
		return parkingId;
	}

	public void setParkingId(int parkingId) {
		this.parkingId = parkingId;
	}

	public Timestamp getTimeStamp() {
		return timeStamp;
	}

	public void setTimeStamp(Timestamp timeStamp) {
		this.timeStamp = timeStamp;
	}
}
