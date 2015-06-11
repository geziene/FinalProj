package dtu.shared;

import java.io.Serializable;

public class ProduktbatchDTO implements Serializable {
	private static final long serialVersionUID = 1L;
    // i omraadet 1-99999999 vaelges af brugerne */
    int produktbatchId;                     
    // min. 2 max. 20 karakterer */
    int  status;                
    // min. 2 max. 20 karakterer */
    int receptId;  
    
    int made_by;
    
    public ProduktbatchDTO()
    {
    	
    }
	
	public ProduktbatchDTO(int produktbatchId, int status, int receptId, int made_by)
	{
		this.produktbatchId = produktbatchId;
		this.status = status;
		this.receptId = receptId;
		this.made_by= made_by;
	}
	
    public int getRaavareId() {
    	return produktbatchId;
    	}
    public void setRaavareId(int produktbatchId) {
    	this.produktbatchId = produktbatchId;
    	}
    public int getstatus() {
    	return status; 
    	}
    public void setstatus(int status) {
    	this.status = status;
    	}
    public int getreceptId() {
    	return receptId;
    	}
    public void setreceptId(int receptId) {
    	this.receptId = receptId;
    	}
    public int getmade_by() {
    	return made_by;
    	}
    public void setmade_by(int made_by) {
    	this.made_by = made_by;
    	}
    
    public String toString() { 
		return produktbatchId + "\t" + status +"\t" + receptId +"\t" + made_by; 
	}
}


