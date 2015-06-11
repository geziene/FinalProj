package dtu.shared;

import java.io.Serializable;

public class RaavareDTO implements Serializable
{
	private static final long serialVersionUID = 1L;
    // i omraadet 1-99999999 vaelges af brugerne */
    int raavareId;                     
    // min. 2 max. 20 karakterer */
    String raavareNavn;                
    // min. 2 max. 20 karakterer */
    String leverandoer;      
    
    int userId;
    String userNavn;
    String userIni; 
    String userCpr;
    String userPassword;
    int userGroup;
    
    public RaavareDTO()
    {
    	
    }
	
	public RaavareDTO(int raavareId, String raavareNavn, String leverandoer)
	{
		this.raavareId = raavareId;
		this.raavareNavn = raavareNavn;
		this.leverandoer = leverandoer;
	}
	
    public int getRaavareId() {
    	return raavareId;
    	}
    public void setRaavareId(int raavareId) {
    	this.raavareId = raavareId;
    	}
    public String getRaavareNavn() {
    	return raavareNavn; 
    	}
    public void setRaavareNavn(String raavareNavn) {
    	this.raavareNavn = raavareNavn;
    	}
    public String getLeverandoer() {
    	return leverandoer;
    	}
    public void setLeverandoer(String leverandoer) {
    	this.leverandoer = leverandoer;
    	}
    public String raavareToString() { 
		return raavareId + "\t" + raavareNavn +"\t" + leverandoer; 
	}
    
/*    public int getuserId() {
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
    
    public String userToString() { 
		return userId + "\t" + userNavn +"\t" + userIni + "\t" + "userCpr" + "\t" + "userPassword" + "\t" + "userGroup"; 
	}*/
}
