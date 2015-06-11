package dtu.shared;

import java.io.Serializable;

public class RaavarebatchDTO implements Serializable {
    int rb_id, raavareId;                     
    double maengde;                

    public RaavarebatchDTO()
    {
    	
    }
	
	public RaavarebatchDTO(int rb_id, int raavareId, double maengde)
	{
		this.rb_id = rb_id;
		this.raavareId = raavareId;
		this.maengde = maengde;
	}
	
	public int getRaavarebatchId() {
		return rb_id;
	}
	public void setRaavarebatchId(int rb_id) {
		this.rb_id = rb_id;
	}
    public int getRaavareId() {
    	return raavareId;
    	}
    public void setRaavareId(int raavareId) {
    	this.raavareId = raavareId;
    	}
   public double getMaengde() {
	   return maengde;
   }
   public void setMaengde(double maengde) {
	   this.maengde = maengde;
   }
    public String raavareToString() { 
		return rb_id + "\t" + raavareId + "\t" + maengde +"\t"; 
    }
}
