package Scale;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import dtu.shared.DALException;

public class ASE implements Runnable {

	private static Socket aseSocket;
	private static final String URL = "jdbc:mysql://localhost:3306/Raavaredatabase";
	private static final String USERNAME = "root";
	private static final String PASSWORD = "";
	static Connection connection = null;
	PrintWriter ud;
	BufferedReader ind;
	static int port = 8000, raavareCount;
	static String hostname = "localhost", temp, name;
		
	public void run() {
		try {
			aseSocket = new Socket(hostname, port);
			ud = new PrintWriter(aseSocket.getOutputStream());
			ind = new BufferedReader(new InputStreamReader(aseSocket.getInputStream()));
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			connection = DriverManager.getConnection( URL, USERNAME, PASSWORD );
		} catch (SQLException e) {
			try {
				throw new DALException("Kan ikke oprette forbindelse til database");
			} catch (DALException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			}		
		//while(true) {
		try {
			temp = ind.readLine();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	if(temp.startsWith("1")){
	    PreparedStatement findUserStmt = null;
		try {
			findUserStmt = connection.prepareStatement("SELECT * FROM users WHERE opr_id = ? ");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    try {
			findUserStmt.setInt(1, Integer.parseInt(temp.substring(1)));
		} catch (NumberFormatException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    ResultSet rs;
		try {
			rs = findUserStmt.executeQuery();
			rs.next();
			name = rs.getString("opr_navn");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		ud.println(name);
		ud.flush();
	    }
	
	try {
		temp = ind.readLine();
	} catch (IOException e2) {
		// TODO Auto-generated catch block
		e2.printStackTrace();
	}
	
	if(temp.startsWith("2")) {
		int receptId = 0;
		PreparedStatement findProduktbatchStmt = null;
		PreparedStatement findReceptStmt = null;
		PreparedStatement findReceptkomponentStmt = null;
		PreparedStatement raavareCountStmt = null;
		
		try {
			findProduktbatchStmt = connection.prepareStatement("SELECT recept_id "
					+ "FROM produktbatch WHERE pb_id = ?");

			findReceptStmt = connection.prepareStatement("SELECT recept_navn "
				+ "FROM recept WHERE recept_id = ? ");
		
			findReceptkomponentStmt = connection.prepareStatement(
					"SELECT raavare_navn, raavare_id, nom_netto, tolerance "
					+ "FROM receptkomponent natural join raavare WHERE recept_id = ?");
			
//			raavareCountStmt = connection.prepareStatement("SELECT COUNT(recept_id) "
//					+ "FROM receptkomponent WHERE recept_id = ?");
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			temp = ind.readLine();
			findProduktbatchStmt.setInt(1, Integer.parseInt(temp));
		} catch (NumberFormatException | SQLException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ResultSet rs = null;
		try {
			rs = findProduktbatchStmt.executeQuery();
			rs.next();
			receptId = rs.getInt("recept_id");
			findReceptStmt.setInt(1, receptId);
			rs = findReceptStmt.executeQuery();
			rs.next();
			ud.println("Recept navn: " + rs.getString("recept_navn"));
			ud.println("Raavare navn & ID\tNetto\tTolerance");
			
			findReceptkomponentStmt.setInt(1, receptId);
			rs = findReceptkomponentStmt.executeQuery();
			while(rs.next()){
				ud.println(rs.getString("raavare_navn")+"\t"+ rs.getString("raavare_id")+"\t\t"
			+String.valueOf(rs.getDouble("nom_netto"))+"\t"+String.valueOf(rs.getDouble("tolerance")));
			}
			ud.flush();
			
//			raavareCountStmt.setInt(1, receptId);
//			rs = raavareCountStmt.executeQuery();
//			rs.next();
//			raavareCount = rs.getInt("COUNT(recept_id)");
//			ud.println(raavareCount);
//			ud.flush();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}
	
	try {
		temp = ind.readLine();
	} catch (IOException e2) {
		// TODO Auto-generated catch block
		e2.printStackTrace();
	}
	
	if(temp.startsWith("3")) {
		PreparedStatement statusProduktbatchStmt = null;
		try {
			statusProduktbatchStmt = connection.prepareStatement(
					"UPDATE produktbatch SET status = ? WHERE pb_id = ?");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		int status = 0;
		int id = 0;
		try {
			status = Integer.parseInt(ind.readLine());
			id = Integer.parseInt(ind.readLine());
		} catch (NumberFormatException | IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		try {
			statusProduktbatchStmt.setInt(1, status);
			statusProduktbatchStmt.setInt(2, id);
			statusProduktbatchStmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		}
	
	try {
		temp = ind.readLine();
	} catch (IOException e2) {
		// TODO Auto-generated catch block
		e2.printStackTrace();
	}
	
	if(temp.startsWith("7")) {
		PreparedStatement statusProduktbatchStmt = null;
		try {
			statusProduktbatchStmt = connection.prepareStatement(
					"UPDATE produktbatch SET status = ? WHERE pb_id = ?");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		int status = 0;
		int id = 0;
		try {
			status = Integer.parseInt(ind.readLine());
			id = Integer.parseInt(ind.readLine());
		} catch (NumberFormatException | IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		try {
			statusProduktbatchStmt.setInt(1, status);
			statusProduktbatchStmt.setInt(2, id);
			statusProduktbatchStmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		PreparedStatement getLagerStmt = null;
		PreparedStatement lagerUpdateStmt = null;
		try {
			getLagerStmt = connection.prepareStatement("SELECT maengde FROM raavarebatch WHERE raavare_id = ?");
			lagerUpdateStmt = connection.prepareStatement("UPDATE raavarebatch SET maengde = ? WHERE raavare_id = ?");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for(int i = 0; i < 3; i++ ){
		String raavareId = "";
		int netto = 0;
		try {
			raavareId = ind.readLine();
			netto = ind.read();
			System.out.println("a " + raavareId);
			System.out.println("a " + netto);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			getLagerStmt.setInt(1, Integer.parseInt(raavareId));
			ResultSet rs = getLagerStmt.executeQuery();
			rs.next();
			int lager = rs.getInt("maengde");
			System.out.println(lager);
			System.out.println(netto);
			lagerUpdateStmt.setInt(1, lager - netto);
			lagerUpdateStmt.setString(2, raavareId);
			lagerUpdateStmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}
	}
		//}
	}

}
