package connector01917;


// erstat konstanterne nedenfor
// constant kalsse opretter connection med databasen
public abstract class Constant
{
	public static final String
		server					= "localhost",  // database-serveren
		database				=  "Raavaredatabase",  //"jdbcdatabase", // navnet paa din database = dit studienummer
		username				= "root", // dit brugernavn = dit studienummer 
		password				= "root"; // dit password som du har valgt til din database
	
	public static final int
		port					= 3306;
}
