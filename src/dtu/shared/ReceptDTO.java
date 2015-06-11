package dtu.shared;

import java.io.Serializable;

public class ReceptDTO extends RaavareDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	int receptId;
	String receptNavn;
		
	public ReceptDTO()
	{
		
	}
	
	
	
	public ReceptDTO(int receptId, String receptNavn)
	{
		this.receptId = receptId;
		this.receptNavn = receptNavn;
		
		
		
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
	  
	  
}
