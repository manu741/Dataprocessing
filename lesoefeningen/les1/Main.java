package les1;

//Vergeet deze import niet
import java.sql.*;

public class Main {
	//Zorg ter voorbereiding dat je ojdbc.jar download en toevoegt aan je project.
	
	//Aanmaken van de variabelen die je connectie specificeren. In dit geval een gebruiker "harry" met password "harry"
	//Deze code gebruikt de tabel afdelingen van de casus uit het leerboek 
	private static final String DB_DRIV = "oracle.jdbc.driver.OracleDriver";
	private static final String DB_URL = "jdbc:oracle:thin:@//localhost:1521/xe";
	private static final String DB_USER = "dataprocessing";
	private static final String DB_PASS = "manu741";
	private static Connection conn;
	
	
	// De methode die met JDBC aan de slag gaat moet een SQLException opvangen of gooien
	public static void main(String[] args) throws SQLException{
		//Besluit welke driver je gaat gebruiken voor je verbinding		
		try {
			Class.forName(DB_DRIV).newInstance();
		} 
		catch (InstantiationException | IllegalAccessException | ClassNotFoundException e1) {
			e1.printStackTrace();
		}
		
		// Leg de connectie met de database
		conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
		System.out.println("Connection made");			//daarmee je ziet hoe ver je komt
		
		
		// --------------------------------------------------
		Statement stmt = conn.createStatement();
		String strQuery = "SELECT * FROM cursussen";
		
		System.out.println("geselect");
		ResultSet bs = stmt.executeQuery(strQuery);
		//tweede poging: Iets doen met de resultaten
				String code;
				String omschrijving;
				String type;
				int lengte;
				while (bs.next()) {   
					code = bs.getString("CODE");	
					omschrijving = bs.getString("OMSCHRIJVING");
					type = bs.getString("TYPE"); 
					lengte = bs.getInt("LENGTE");
					System.out.println("Code: " + code + ", " + omschrijving + " type: " + type + " lengte: " + lengte);
				}	
				// De resultset en het statement sluiten
				bs.close();
					
				System.out.println("pehhh");
						
		// **************************************************
		/* De testrecord kun je in SQL Developer invoeren met: 
		 * Insert into AFDELINGEN (ANR, Naam, HOOFD, Locatie) values (50, 'TEST', null, 'UTRECHT');  
		 */
		// Een eerste SQL statement maken
		strQuery = "DELETE FROM afdelingen WHERE anr = 50  ";
		//System.out.println("test");
		// Een eerste SQL statement uitvoeren
		int result = stmt.executeUpdate(strQuery);
		System.out.println("Afdeling 50 verwijderd.\n" + result);	//daarmee je ziet hoe ver je komt
		
		
		// **************************************************
		// Een tweede statement maken dat een resultaat oplevert
		String queryText = "Select * FROM afdelingen";
		
		// Een tweede statement uitvoeren
		ResultSet rs = stmt.executeQuery(queryText);
		
		// Iets doen met de resultaten
		// eerste poging geeft hier uitvoer en stopt dan 
		System.out.println("Eerste ResultSet: " + rs + "\n");
		
		//tweede poging: Iets doen met de resultaten
		int anr;
		String naam;
		String locatie; 
		while (rs.next()) {   
			anr = rs.getInt("ANR");	
			naam = rs.getString("naam");
			locatie = rs.getString("locatie"); 
			System.out.println("Afdeling nr: " + anr + ", " + naam + " in " + locatie);
		}	
		// De resultset en het statement sluiten
		rs.close();
		stmt.close();	
		System.out.println("");


		// **************************************************
		/* De testrecord kun je in SQL Developer invoeren met: 
		 * Insert into AFDELINGEN (ANR, Naam, HOOFD, Locatie) values (60, 'TEST2', null, 'BAARN');  
		 */
		// Een deerde SQL statement maken met PreparedStatement
		strQuery = "DELETE FROM afdelingen WHERE anr = ? AND locatie = ?";
		PreparedStatement pstmt = conn.prepareStatement(strQuery);
		pstmt.setInt(1, 60);
		pstmt.setString(2, "BAARN");
		
		// Een deerde SQL statement uitvoeren
		pstmt.executeUpdate();
		System.out.println("Afdeling 60 verwijderd.\n");	//daarmee je ziet hoe ver je komt
	

		// Het Preparedstatement en de verbinding sluiten
		pstmt.close();
		conn.close();

	}
}