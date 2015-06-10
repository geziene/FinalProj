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
import dtu.shared.RaavareDTO;

public class RaavareDAO_db extends RemoteServiceServlet implements KartotekService  {
	

	private static final String URL = "jdbc:mysql://localhost:3306/Raavaredatabase";
	private static final String USERNAME = "root";
	private static final String PASSWORD = "";

	private Connection connection = null; // manages connection

	private PreparedStatement saveRaavareStmt = null;
	private PreparedStatement updateRaavareStmt = null;
	private PreparedStatement getRaavareStmt = null;
	private PreparedStatement getSizeStmt = null;
	private PreparedStatement deleteRaavareStmt = null;

	public RaavareDAO_db() throws Exception {
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


		} 
		catch ( SQLException sqlException )
		{
			throw new DALException("Kan ikke oprette forbindelse til database");
		}
	}

	@Override
	public void saveRaavare(RaavareDTO r) throws Exception {
		// simulate server error
		// throw new RuntimeException(" \"saveRaavare\" fejlede");

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
