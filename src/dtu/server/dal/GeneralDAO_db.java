package dtu.server.dal;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import dtu.client.service.KartotekService;
import dtu.shared.DALException;
import dtu.shared.ProduktbatchDTO;
import dtu.shared.RaavareDTO;
import dtu.shared.RaavarebatchDTO;
import dtu.shared.ReceptDTO;
import dtu.shared.ReceptkomponentDTO;
import dtu.shared.UserDTO;

public class GeneralDAO_db extends RemoteServiceServlet implements KartotekService  {

	private static final String URL = "jdbc:mysql://localhost:3306/Raavaredatabase";
	private static final String USERNAME = "root";
	private static final String PASSWORD = "";

	private Connection connection = null; // manages connection

	private PreparedStatement saveRaavareStmt = null;
	private PreparedStatement updateRaavareStmt = null;
	private PreparedStatement getRaavareStmt = null;
	private PreparedStatement getSizeStmt = null;
	private PreparedStatement deleteRaavareStmt = null;
	private PreparedStatement saveReceptStmt = null;
	private PreparedStatement showProduktbatchesStmt = null;
	
	private PreparedStatement saveUserStmt = null;
	
	private PreparedStatement saveReceptkomponent = null;
	
	private PreparedStatement saveProduktbatchStmt= null;
	
	private PreparedStatement saveRaavarebatchStmt = null;
	private PreparedStatement findUserStmt = null;
	
	
	public GeneralDAO_db() throws Exception {
		try 
		{
			connection = DriverManager.getConnection( URL, USERNAME, PASSWORD );

			// create query that add/create a raavare to kartotek
			saveRaavareStmt = 
					connection.prepareStatement( "INSERT INTO raavare " + 
							"( raavare_id, raavare_navn, leverandoer ) " + 
							"VALUES (?, ?, ? )" );

			// create query that updates a raavare
			updateRaavareStmt = connection.prepareStatement( 
					"UPDATE person SET raavare_navn = ?, leverandoer = ?  WHERE raavare_id = ?" );

			// create query that get all raavare in kartotek
			getRaavareStmt = connection.prepareStatement( 
					"SELECT * FROM raavare "); 

			// create query that gets size of kartotek
			getSizeStmt = connection.prepareStatement( 
					"SELECT COUNT(*) FROM raavare ");

			// create query that deletes a person in kartotek
			deleteRaavareStmt = connection.prepareStatement( 
					"DELETE FROM person WHERE raavare_id =  ? ");

			saveUserStmt = connection.prepareStatement(
					"INSERT INTO users(opr_id, opr_navn, ini, cpr, password, gruppe) " +
					"VALUES(?, ?, ?, ?, ?, ?)");

			saveReceptStmt = connection.prepareStatement(
					"INSERT INTO recept(recept_id, recept_navn) " +
					"VALUES(?, ?)");
			
			saveReceptkomponent = connection.prepareStatement(
					"INSERT INTO receptkomponent(recept_id, raavare_id, nom_netto, tolerance, made_by) " +
					"VALUES(?, ?, ?, ?, ?)");
			
			showProduktbatchesStmt = connection.prepareStatement("SELECT * FROM produktbatch");
			
			saveProduktbatchStmt = 
					connection.prepareStatement("INSERT INTO produktbatch " + 
							"( pb_id, status, recept_id, made_by ) " + 
							"VALUES (?, ?, ?, ?)" );
			
			saveRaavarebatchStmt = connection.prepareStatement("INSERT INTO raavarebatch " +
					"(rb_id, raavare_id, maengde) " +
					"VALUES(?, ?, ?)");
			
			findUserStmt = connection.prepareStatement("SELECT * FROM users WHERE opr_id = ? ");
			
			
		} 
		catch ( SQLException sqlException )
		{
			throw new DALException("Kan ikke oprette forbindelse til database");
		}
	}

	@Override
	public void saveRaavare(RaavareDTO r) throws Exception {
		try {
			saveRaavareStmt.setInt(1, r.getRaavareId());
			saveRaavareStmt.setString(2, r.getRaavareNavn());
			saveRaavareStmt.setString(3, r.getLeverandoer());

			saveRaavareStmt.executeUpdate();
		} catch (SQLException e) {
			throw new DALException(" \"saveRaavare\" fejlede");
		} 
	}

	@Override
	public void updateRaavare(RaavareDTO r) throws Exception {
		try {
			updateRaavareStmt.setString(1, r.getRaavareNavn());
			updateRaavareStmt.setString(2, r.getLeverandoer());
			updateRaavareStmt.setInt(3, r.getRaavareId());

			updateRaavareStmt.executeUpdate();
		} catch (SQLException e) {
			throw new DALException(" \"updateRaavare\" fejlede");
		} 
	}

	@Override
	public List<RaavareDTO> getRaavare() throws Exception {
		List< RaavareDTO > results = null;
		ResultSet resultSet = null;

		try 
		{
			resultSet = getRaavareStmt.executeQuery(); 
			results = new ArrayList< RaavareDTO >();

			while ( resultSet.next() )
			{
				results.add( new RaavareDTO(
						resultSet.getInt( "raavare_id" ),
						resultSet.getString( "raavare_navn" ),
						resultSet.getString( "leverandoer" )));
			} 
		} 
		catch ( SQLException sqlException )
		{
			throw new DALException(" \"getRaavare\" fejlede");
		} 
		finally
		{
			try 
			{
				resultSet.close();
			} 
			catch ( SQLException sqlException )
			{
				sqlException.printStackTrace();         
				close();
			} 
		} 
		return results;
	} 

	@Override
	public int getSize() throws Exception {
		//return size of raavare table
		try {
			ResultSet rs = null;
			rs = getSizeStmt.executeQuery();
			rs.next();
			return rs.getInt(1);
		} catch (SQLException e) {
			throw new DALException(" \"getSize\" fejlede");
		} 
	}

	@Override
	public void deleteRaavare(int id) throws Exception {
		try {
			deleteRaavareStmt.setInt(1, id);

			deleteRaavareStmt.executeUpdate();
		} catch (SQLException e) {
			throw new DALException(" \"deleteRaavare\" fejlede");
		} 
	}

	@Override
	public void saveUser(UserDTO u) throws Exception {
		try {
			saveUserStmt.setInt(1, u.getuserId());
			saveUserStmt.setString(2, u.getuserNavn());
			saveUserStmt.setString(3, u.getuserIni());
			saveUserStmt.setString(4, u.getuserCpr());
			saveUserStmt.setString(5, u.getuserPassword());
			saveUserStmt.setInt(6, u.getuserGroup());

			saveUserStmt.executeUpdate();
		} catch (SQLException e) {
		throw new DALException("\"saveUser\" fejlede");
		}
	}
		
	@Override
	public UserDTO findUser(int id) throws Exception{
		try {
			findUserStmt.setInt(1, id);
			ResultSet rs = findUserStmt.executeQuery();
			while(rs.next())
			{
				if(rs.getInt("opr_id") == id)
				{
					UserDTO dto = new UserDTO(
							rs.getInt( "opr_id" ),
							rs.getString( "opr_navn" ),
							rs.getString( "ini" ),
							rs.getString( "cpr" ),
							rs.getString( "password" ),
							rs.getInt( "gruppe" ));
					return dto;
				}
			}
			return null;	


	} catch (SQLException e) {
			
				throw new DALException("Kunne ikke finde ID");
			}
	}

	@Override
	public void saveRecept(ReceptDTO newRecept) throws Exception {
		try {
			saveReceptStmt.setInt(1, newRecept.getreceptId());
			saveReceptStmt.setString(2, newRecept.getreceptNavn());
			
			saveReceptStmt.executeUpdate();
		} catch (SQLException e) {
		throw new DALException("\"save Recept\" fejlede");
		
	}
}
	@Override
	public void saveReceptkomponent(ReceptkomponentDTO newReceptkomponent) throws Exception {
		try {
			saveReceptkomponent.setInt(1, newReceptkomponent.getreceptkomponentId());
			saveReceptkomponent.setInt(2, newReceptkomponent.getraavarekomponentId());
			saveReceptkomponent.setDouble(3, newReceptkomponent.getnettokomponent());
			saveReceptkomponent.setDouble(4, newReceptkomponent.gettolerancekomponent());
			saveReceptkomponent.setInt(5, newReceptkomponent.getmadebykomponent());
			
			saveReceptkomponent.executeUpdate();
		} catch (SQLException e) {
		throw new DALException("\"save Receptkomponent\" fejlede");
		
	}
}
	@Override
	public ArrayList<String> showProduktbatch() throws Exception{
		
		try {
			ResultSet rs = showProduktbatchesStmt.executeQuery();
			ArrayList<String> pbList = new ArrayList<String>();
			while(rs.next()){
				pbList.add(String.valueOf(rs.getInt("pb_id")));
				pbList.add(String.valueOf(rs.getInt("status")));
				pbList.add(String.valueOf(rs.getInt("recept_id")));
				pbList.add(String.valueOf(rs.getInt("made_by")));
			}
			return pbList;
			
		} catch (SQLException e) {
		
			throw new DALException("Kunne ikke hente Produktbatch");
		}
	}
	
	@Override
	public void saveProduktbatch(ProduktbatchDTO pb) throws Exception {
		try {
			saveProduktbatchStmt.setInt(1, pb.getproduktbatchId());
			saveProduktbatchStmt.setInt(2, pb.getstatus());
			saveProduktbatchStmt.setInt(3, pb.getreceptId());
			saveProduktbatchStmt.setInt(4, pb.getmade_by());
			saveProduktbatchStmt.executeUpdate();
		} catch (SQLException e) {
			throw new DALException(" \"save Produktbatch\" fejlede");
		}
	}
	
	@Override
	public void saveRaavarebatch(RaavarebatchDTO rb) throws Exception {
		try {
			saveRaavarebatchStmt.setInt(1, rb.getRaavarebatchId());
			saveRaavarebatchStmt.setInt(2, rb.getRaavareId());
			saveRaavarebatchStmt.setDouble(3, rb.getMaengde());
			saveRaavarebatchStmt.executeUpdate();
		} catch (SQLException e) {
			throw new DALException("\" save Raavarebatch\" fejlede");
		}
		
	}
	
	@Override
	public String userName(int id) throws Exception {
		String name = "hej";
		try {
		findUserStmt.setInt(1, id);
		ResultSet rs = findUserStmt.executeQuery();
		int gruppe = rs.getInt("gruppe");
		
//		if(gruppe == 4){
//			name = rs.getString("opr_id");
//		}
		return name;
		
		} catch(SQLException e) {
			throw new DALException("Kunne ikke hente User");
		}
	}
	
	// close the database connection
	public void close() {
		try {
			connection.close();
		} // end try
		catch (SQLException sqlException) {
			sqlException.printStackTrace();
		} 
	}
}