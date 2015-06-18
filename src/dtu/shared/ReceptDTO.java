package dtu.shared;

import java.io.Serializable;

public class ReceptDTO implements Serializable {
	
	int receptId;
	String receptNavn;
	int made_by;
		
	public ReceptDTO()
	{
		
	}

	public ReceptDTO(int receptId, String receptNavn, int made_by)
	{
		this.receptId = receptId;
		this.receptNavn = receptNavn;
		this.made_by = made_by;
	}
	
	  public int getreceptId() {
	    	return receptId;
	    	}
	  public void setreceptId(int receptId) {
	    	this.receptId = receptId;
	    	}
	  public String getreceptNavn() {
	    	return receptNavn; 
	    	}
	  public void setreceptNavn(String receptNavn) {
	    	this.receptNavn = receptNavn;
	  }	
	  public int getmade_by() {
	    	return made_by;
	    	}
	  public void setmade_by(int receptId) {
	    	this.made_by = made_by;
	    	}
	  
	  
}
