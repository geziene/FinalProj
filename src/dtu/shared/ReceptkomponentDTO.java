package dtu.shared;
import java.io.Serializable;

public class ReceptkomponentDTO extends RaavareDTO implements Serializable  {
		
		/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
		int receptkomponentId;
		int raavarekomponentId;
		double nettokomponent;
		double tolerancekomponent;
		int madebykomponent;
		int pbidkomponentId;
		
		public ReceptkomponentDTO()
		{
			
		}

		public ReceptkomponentDTO(int receptkomponentId, int raavarekomponentId, int madebykomponent, int pbidkomponentId, double nettokomponent,double tolerancekomponent){
			
			this.receptkomponentId = receptkomponentId;
			this.raavarekomponentId = raavarekomponentId;
			this.nettokomponent = nettokomponent;
			this.tolerancekomponent = tolerancekomponent;
			this.madebykomponent = madebykomponent;
			this.pbidkomponentId = pbidkomponentId;
		}
		  
		  public int getreceptkomponentId(){
			  return receptkomponentId;
		  }
		  
		  public void setreceptkomponentId(int receptkomponentId){
			  this.receptkomponentId = receptkomponentId;
		  }
		  
		  public int getraavarekomponentId(){
			  return receptkomponentId;
		  }
		  
		  public void setraavarekomponentId(int raavarekomponentId){
			  this.raavarekomponentId = raavarekomponentId;
		  }
		
		  public double getnettokomponent(){
			  return nettokomponent;
		  }
		  public void setnettokomponent(double nettokomponent){
			  this.nettokomponent = nettokomponent;
		  }
		  
		  public double gettolerancekomponent(){
			  return tolerancekomponent;
		  }
		  public void settolerancekomponent(double tolerancekomponent){
			  this.tolerancekomponent = tolerancekomponent;
		  }
		  public int getmadebykomponent(){
			  return madebykomponent;
		  }
		  
		  public void setmadebykomponent(int madebykomponent){
			  this.madebykomponent = madebykomponent;
		  }

		public int getpbidkomponentId() {
			return pbidkomponentId;
		}
		 public void setpbidkomponentId(int getpbidkomponentId){
			  this.pbidkomponentId = getpbidkomponentId;
		  }
	}


