package dtu.shared;
import java.io.Serializable;

public class ReceptkomponentDTO extends RaavareDTO implements Serializable  {
		
		int receptkomponentId;
		int raavarekomponentId;
		double nettokomponent;
		double tolerancekomponent;
		int madebykomponent;
		
		public ReceptkomponentDTO()
		{
			
		}

		public ReceptkomponentDTO(int receptkomponentId, int raavarekomponentId,double nettokomponent,double tolerancekomponent,int madebykomponent){
			
			this.receptkomponentId = receptkomponentId;
			this.raavarekomponentId = raavarekomponentId;
			this.nettokomponent = nettokomponent;
			this.tolerancekomponent = tolerancekomponent;
			this.madebykomponent = madebykomponent;
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
	}


