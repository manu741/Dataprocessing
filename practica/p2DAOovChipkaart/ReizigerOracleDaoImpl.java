package p2DAOovChipkaart;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ReizigerOracleDaoImpl extends OracleBaseDAO implements ReizigerDao {

	ArrayList<Reiziger> reizigers = new ArrayList<Reiziger>();
	
	
	public ReizigerOracleDaoImpl() throws SQLException {
		getConnection();
	};
	
/*alle gegevens in de tabel ophalen*/
	public List<Reiziger> findAll() throws SQLException{	//interface list<Reiziger> gebruiken, omdat interfaces zo veel mogelijk gebruikt moeten worden in methodes
		OvChipkaartDao ovDAO = new OvChipkaartDaoImpl();
		List<Reiziger> findAllReizigers = new ArrayList<Reiziger>();
		
		String strQuery = "SELECT * FROM reiziger";
		PreparedStatement pstmt = getConnection().prepareStatement(strQuery);
		ResultSet rs = pstmt.executeQuery();
		Reiziger r1 = null;
		
		while(rs.next()) {
			//System.out.println("in while");
			r1 = new Reiziger(rs.getInt("REIZIGERID"), rs.getString("VOORLETTERS"), rs.getString("TUSSENVOEGSEL"), rs.getString("ACHTERNAAM"), rs.getDate("GEBORTEDATUM"));
			r1.setOvList(ovDAO.findByOwner(r1));
			findAllReizigers.add(r1);			
		}	
		
		// Het Preparedstatement sluiten
		pstmt.close();
		rs.close();
		
		return findAllReizigers;
	}
	
/*-------reiziger object toevoegen aan de database tabel REIZIGER------*/
	public Reiziger save(Reiziger reiziger) throws SQLException{
		String strQuery = "insert into reiziger (REIZIGERID, VOORLETTERS, TUSSENVOEGSEL, ACHTERNAAM, GEBORTEDATUM) values (?, ?, ?, ?, ?)";
		PreparedStatement pstmt = getConnection().prepareStatement(strQuery);
		pstmt.setInt(1, reiziger.getId());
		pstmt.setString(2, reiziger.getVoorletter());
		pstmt.setString(3, reiziger.getTussenvoegsel());
		pstmt.setString(4, reiziger.getNaam());
		pstmt.setDate(5, reiziger.getGBdatum());
		pstmt.executeUpdate();
		return reiziger;
	}
	
/*---------reiziger object verwijderen uit de arrayList, door de equals methode in klasse Reiziger aan te roepen met remove--------*/
	public boolean delete(Reiziger reiziger) throws SQLException{
/*---------met reiziger object als parameter--------*/
		Statement stmt = getConnection().createStatement();
		String strQuery = "DELETE FROM REIZIGER WHERE REIZIGERID = " + reiziger.getId();
		boolean deleted = stmt.execute(strQuery);
		stmt.close();
		return deleted;
		
/*---------met int id als parameter--------*/
//		Statement stmt = getConnection().createStatement();
//		String strQuery = "DELETE FROM REIZIGER WHERE REIZIGERID = " + id;
//		boolean deleted = stmt.execute(strQuery);
//		stmt.close();
//		return deleted;
	}
	
/*------Een rij updaten waar de reizigerID hetzelfde is als de een die is ingevoerd------*/
	public Reiziger update(Reiziger reiziger) throws SQLException{
		String strQuery = "UPDATE REIZIGER SET VOORLETTERS=?, TUSSENVOEGSEL=?, ACHTERNAAM=?, GEBORTEDATUM=? WHERE REIZIGERID=?";
		PreparedStatement pstmt = getConnection().prepareStatement(strQuery);
		
		pstmt.setString(1, reiziger.getVoorletter());
		pstmt.setString(2, reiziger.getTussenvoegsel());
		pstmt.setString(3, reiziger.getNaam());
		pstmt.setDate(4, reiziger.getGBdatum());
		pstmt.setInt(5, reiziger.getId());
		
		pstmt.executeUpdate(/*strQuery*/);
		return reiziger;
	}
	
/*------een reiziger in de database vinden door op de datum te zoeken------*/
	public List<Reiziger> findByGBdatum(Date GBdatum) throws SQLException{
		ArrayList<Reiziger> gbselection = new ArrayList<Reiziger>(); 	//nieuwe arrayList aanmaken waarin een object opgeslagen kan worden
		String strQuery = "select * from reiziger where gebortedatum=date '" + GBdatum + "'";
		Statement stmt = getConnection().createStatement();
		ResultSet rs = stmt.executeQuery(strQuery);
		Reiziger r1 = null;
		
		while(rs.next()) {
			r1 = new Reiziger(rs.getInt("REIZIGERID"), rs.getString("VOORLETTERS"), rs.getString("TUSSENVOEGSEL"), rs.getString("ACHTERNAAM"), rs.getDate("GEBORTEDATUM"));
			gbselection.add(r1);
		}
		
		return gbselection;
	}
	
	public Reiziger findById(int id) throws SQLException{
		String strQuery = "select * from reiziger where reizigerid=" + id;
		Statement stmt = getConnection().createStatement();
		ResultSet rs = stmt.executeQuery(strQuery);
		Reiziger r1 = null;
		rs.next();
		r1 = new Reiziger(rs.getInt("REIZIGERID"), rs.getString("VOORLETTERS"), rs.getString("TUSSENVOEGSEL"), rs.getString("ACHTERNAAM"), rs.getDate("GEBORTEDATUM"));
	
		return r1;
	}
	
}
