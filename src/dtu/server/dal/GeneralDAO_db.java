package dtu.server.dal;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.google.gwt.user.client.Window;
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
	
	private PreparedStatement saveUserStmt = null;
	private PreparedStatement findUserStmt = null;
	private PreparedStatement updateUserStmt = null;
	
	private PreparedStatement saveReceptkomponentStmt = null;
	private PreparedStatement findReceptkomponentStmt = null;
	
	private PreparedStatement saveProduktbatchStmt = null;
	private PreparedStatement findProduktbatchStmt = null;
	private PreparedStatement showProduktbatcheStmt = null;
	private PreparedStatement statusProduktbatchStmt = null;
	
	private PreparedStatement saveRaavarebatchStmt = null;
	
	private PreparedStatement getUsersStmt = null;
	
	private PreparedStatement getProduktbatchesStmt = null;
	
	private PreparedStatement updateProduktbatchesStmt = null;
	
	private PreparedStatement showReceptStmt = null;
	

	
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
					"UPDATE raavare SET raavare_navn = ?, leverandoer = ?  WHERE raavare_id = ?" );

			// create query that get all raavare in kartotek
			getRaavareStmt = connection.prepareStatement( 
					"SELECT * FROM raavare "); 

			// create query that gets size of kartotek
			getSizeStmt = connection.prepareStatement( 
					"SELECT COUNT(*) FROM raavare ");

			// create query that deletes a person in kartotek
			deleteRaavareStmt = connection.prepareStatement( 
					"DELETE FROM raavare WHERE raavare_id =  ? ");

			saveUserStmt = connection.prepareStatement(
					"INSERT INTO users(opr_id, opr_navn, ini, cpr, password, gruppe) " +
					"VALUES(?, ?, ?, ?, ?, ?)");

			saveReceptStmt = connection.prepareStatement(
					"INSERT INTO recept(recept_id, recept_navn, made_by) " +
					"VALUES(?, ?, ?)");
			
			saveReceptkomponentStmt = connection.prepareStatement(
					"INSERT INTO receptkomponent(recept_id, raavare_id, made_by, pb_id, nom_netto, tolerance) " +
					"VALUES(?, ?, ?, ?, ?, ?)");
			
			showProduktbatcheStmt = connection.prepareStatement("SELECT * FROM produktbatch");
			
			saveProduktbatchStmt = 
					connection.prepareStatement("INSERT INTO produktbatch " + 
							"( pb_id, status, recept_id, made_by ) " + 
							"VALUES (?, ?, ?, ?)" );
			
			saveRaavarebatchStmt = connection.prepareStatement("INSERT INTO raavarebatch " +
					"(rb_id, raavare_id, maengde) " +
					"VALUES(?, ?, ?)");
			
			findUserStmt = connection.prepareStatement("SELECT * FROM users WHERE opr_id = ? ");
			
			findProduktbatchStmt = connection.prepareStatement("SELECT recept_id FROM produktbatch WHERE pb_id = ?");
			
			findReceptkomponentStmt = connection.prepareStatement("SELECT raavare_id, nom_netto, tolerance "
					+ "FROM receptkomponent WHERE recept_id = ?");
			
			statusProduktbatchStmt = connection.prepareStatement("UPDATE produktbatch SET status = ? WHERE pb_id = ?");
			
			updateUserStmt = connection.prepareStatement("UPDATE users SET opr_navn = ?, ini = ?, cpr = ?, "
					+ "password = ?, gruppe = ?  WHERE opr_id = ?" );
			
			getUsersStmt = connection.prepareStatement("SELECT * FROM users"); 

			getProduktbatchesStmt = connection.prepareStatement("SELECT * FROM produktbatch "); 
			
			updateProduktbatchesStmt = connection.prepareStatement("UPDATE produktbatch SET status = ?, recept_id = ?, made_by = ?  WHERE pb_id = ?" );

			showReceptStmt = connection.prepareStatement("SELECT * FROM recept");
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
			throw new DALException(" \"updateRaavare \" fejlede" + e.getMessage());
		} 
	}

	@Override
	public ArrayList<RaavareDTO> getRaavare() throws Exception {
		ArrayList< RaavareDTO > results;
		ResultSet resultSet;

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
			saveReceptStmt.setInt(3, newRecept.getmade_by());
			
			saveReceptStmt.executeUpdate();
		} catch (SQLException e) {
		throw new DALException("\"save Recept\" fejlede");
		
	}
}
	@Override
	public void saveReceptkomponent(ReceptkomponentDTO newReceptkomponent) throws Exception {
		try {
			saveReceptkomponentStmt.setInt(1, newReceptkomponent.getreceptkomponentId());
			saveReceptkomponentStmt.setInt(2, newReceptkomponent.getraavarekomponentId());
			saveReceptkomponentStmt.setInt(3, newReceptkomponent.getmadebykomponent());
			saveReceptkomponentStmt.setInt(4, newReceptkomponent.getpbidkomponentId());
			saveReceptkomponentStmt.setDouble(5, newReceptkomponent.getnettokomponent());
			saveReceptkomponentStmt.setDouble(6, newReceptkomponent.gettolerancekomponent());			
			saveReceptkomponentStmt.executeUpdate();
		} catch (SQLException e) {
		throw new DALException("\"save Receptkomponent\" fejlede" + e.getMessage());
		
	}
}
	@Override
	public ArrayList<String> showProduktbatch() throws Exception{
		
		try {
			ResultSet rs = showProduktbatcheStmt.executeQuery();
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
	public ArrayList<ReceptDTO> showRecept() throws Exception{
		ArrayList< ReceptDTO > results = null;
		ResultSet resultSet = null;

		try 
		{
			resultSet = showReceptStmt.executeQuery(); 
			results = new ArrayList< ReceptDTO >();
			


			while ( resultSet.next() )
			{

				results.add( new ReceptDTO(
						resultSet.getInt( "recept_id" ),
						resultSet.getString("recept_navn"),
						resultSet.getInt("made_by")));
			}
			return results;
			
		} catch (SQLException e) {
		
			throw new DALException("Kunne ikke hente recept" + e.getMessage());
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
		String name;
		try {
		findUserStmt.setInt(1, id);
		ResultSet rs = findUserStmt.executeQuery();
		while(rs.next()){
		int gruppe = rs.getInt("gruppe");
		
		if(gruppe != 5){
			name = rs.getString("opr_navn");
			return name;
			}
		}
		return null;
		
		} catch(SQLException e) {
			throw new DALException("Kunne ikke hente User");
		}
	}
	
	public int logUser(int id, String psw) throws Exception{
		try {
			findUserStmt.setInt(1, id);

			ResultSet rs1 = findUserStmt.executeQuery();

			while(rs1.next())
			{
				if(rs1.getInt("opr_id") == id)
				{
					if(rs1.getString("password").equals(psw))
					{
						return rs1.getInt("gruppe");
					} 
				}
			}
			return 0;	
	} catch (SQLException e) {
			
				throw new DALException("Bruger findes ikke");
			}
	}
	
	public void updateUser(UserDTO r) throws Exception {
		try {
					
					updateUserStmt.setString(1, r.getuserNavn());
					updateUserStmt.setString(2, r.getuserIni());
					updateUserStmt.setString(3, r.getuserCpr());
					if(r.getuserGroup() == 5)
					{
					updateUserStmt.setString(4, "");
					}
					else 
						updateUserStmt.setString(4, r.getuserPassword());
					if (r.getuserPassword() == "")
					{
						updateUserStmt.setInt(5, 5);
					}else
					updateUserStmt.setInt(5, r.getuserGroup());
					updateUserStmt.setInt(6, r.getuserId());
			
					updateUserStmt.executeUpdate();


		} catch (SQLException e) {
			throw new DALException(" \"updateUser\" fejlede" + e.getMessage());
		} 
	}

	@Override
	public ArrayList<String> findReceptkomponet(int id) throws Exception {
		ArrayList<String> rkList = new ArrayList<String>();
		int receptId = 0;
		
		findProduktbatchStmt.setInt(1, id);
		ResultSet rs = findProduktbatchStmt.executeQuery();
		rs.next();
		receptId = rs.getInt("recept_id");
		
		findReceptkomponentStmt.setInt(1, receptId);
		rs = findReceptkomponentStmt.executeQuery();
		while(rs.next()){
			rkList.add(String.valueOf(rs.getInt("raavare_id")));
			rkList.add(String.valueOf(rs.getDouble("nom_netto")));
			rkList.add(String.valueOf(rs.getDouble("tolerance")));
		}
		
		return rkList;
	}
	
	@Override
	public void statusProduktbatch(int id, int status) throws Exception {
		statusProduktbatchStmt.setInt(1, id);
		statusProduktbatchStmt.setInt(2, status);
		statusProduktbatchStmt.executeUpdate();
	}
	
	@Override
	public ArrayList<UserDTO> getUsers() throws Exception {
		ArrayList< UserDTO > results = null;
		ResultSet resultSet = null;

		try 
		{
			resultSet = getUsersStmt.executeQuery(); 
			results = new ArrayList< UserDTO >();

			while ( resultSet.next() )
			{
				results.add( new UserDTO(
						resultSet.getInt( "opr_id" ),
						resultSet.getString( "opr_navn" ),
						resultSet.getString( "ini" ),
						resultSet.getString( "cpr" ),
						resultSet.getString( "password" ),
						resultSet.getInt( "gruppe" )));
			} 
		} 
		catch ( SQLException sqlException )
		{
			throw new DALException(" \"getUsers\" fejlede");
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
	public ArrayList<ProduktbatchDTO> getProduktbatches() throws Exception {
		
		ArrayList< ProduktbatchDTO > results = null;
		ResultSet resultSet = null;

		try 
		{
			resultSet = getProduktbatchesStmt.executeQuery(); 
			results = new ArrayList< ProduktbatchDTO >();

			while ( resultSet.next() )
			{
				results.add( new ProduktbatchDTO(
						resultSet.getInt( "pb_id" ),
						resultSet.getInt("status"),
						resultSet.getInt( "recept_id"),
						resultSet.getInt("made_by")));
			} 
		} 
		catch ( SQLException sqlException )
		{
			sqlException.printStackTrace();
			throw new DALException(" \"getProduktbatches\" fejlede" + sqlException.getMessage());
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
	public void updateProduktbatches(ProduktbatchDTO p) throws Exception {
		try {
					
					updateProduktbatchesStmt.setInt(1, p.getstatus());
					updateProduktbatchesStmt.setInt(3, p.getreceptId());
					updateProduktbatchesStmt.setInt(2, p.getmade_by());
					updateProduktbatchesStmt.setInt(4, p.getproduktbatchId());
			
					updateProduktbatchesStmt.executeUpdate();


		} catch (SQLException e) {
			throw new DALException(" \"updateProduktbatces\" fejlede" + e.getMessage());
		} 
	}


	// close the database connection
	public void close() {
		try {
			connection.close();
		}
		catch (SQLException sqlException) {
			sqlException.printStackTrace();
		} 
	}

}