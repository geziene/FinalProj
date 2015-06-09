package dtu.shared;
import java.io.Serializable;

public class UserDTO implements Serializable {
	
		private static final long serialVersionUID = 1L;
	   
	    int userId;                     
	    String userNavn;                
	    String userIni; 
	    String userCpr;
	    String userPassword;
	    int userGroup;
	    
	    
	    public UserDTO()
	    {
	    	
	    }
		
		public UserDTO(int userId, String userNavn, String userIni, String userCpr, String userPassword, int userGroup)
		{
			this.userId = userId;
			this.userNavn = userNavn;
			this.userIni = userIni;
			this.userCpr = userCpr;
			this.userPassword = userPassword;
			this.userGroup = userGroup;
		}
		
	    public int getuserId() {
	    	return userId;
	    	}
	    public void setuserId(int userId) {
	    	this.userId = userId;
	    	}
	    public String getuserNavn() {
	    	return userNavn; 
	    	}
	    public void setuserNavn(String userNavn) {
	    	this.userNavn = userNavn;
	    	}
	    public String getuserIni() {
	    	return userIni;
	    	}
	    public void setuserIni(String userIni) {
	    	this.userIni = userIni;
	    	}
	    public String getuserCpr(){
	    	return userCpr;
	    	}
	    public void setuserCpr(String userCpr){
	    	this.userCpr = userCpr;
	    	}
	    public String getuserPassword(){
	    	return userPassword;
	    	}
	    public void setuserPassword(String userPassword){
	    	this.userPassword = userPassword;
	    	}
	    public int getuserGroup(){
	    	return userGroup;
	    	}
	    public void setuserGroup(int userGroup){
	    	this.userGroup = userGroup;
	    }
	    
	    public String toString() { 
			return userId + "\t" + userNavn +"\t" + userIni + "\t" + "userCpr" + "\t" + "userPassword" + "\t" + "userGroup"; 
		}
	}



