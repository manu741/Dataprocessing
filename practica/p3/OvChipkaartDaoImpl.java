package p3;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import oracle.jdbc.proxy.annotation.GetDelegate;

public class OvChipkaartDaoImpl extends OracleBaseDAO implements OvChipkaartDao {
	
	ReizigerDao rDAO = new ReizigerOracleDaoImpl();
	//ProductDao pDao = new ProductDaoImpl();
	
	public OvChipkaartDaoImpl() throws SQLException {
		getConnection();
	};
	
/*alle gegevens uit de tabel halen*/
	public List<OvChipkaart> findAll() throws SQLException{	//interface list<Reiziger> gebruiken, omdat interfaces zo veel mogelijk gebruikt moeten worden in methodes
		ArrayList<OvChipkaart> findAllKaarten = new ArrayList<OvChipkaart>();
		ProductDao pDao = new ProductDaoImpl();
		
		String strQuery = "SELECT * FROM OV_CHIPKAART";
		PreparedStatement pstmt = getConnection().prepareStatement(strQuery);
		ResultSet rs = pstmt.executeQuery();
		OvChipkaart ov1 = null;
		
		while(rs.next()) {
			//System.out.println("in while");
			ov1 = new OvChipkaart(rs.getInt("KAARTNUMMER"), rs.getDate("GELDIGTOT"), rs.getInt("KLASSE"), rs.getDouble("SALDO"), rs.getInt("REIZIGERID"));
			ov1.setEigenaar(rDAO.findById(rs.getInt("REIZIGERID")));
			ov1.setProducten(pDao.findByOv(ov1));
			findAllKaarten.add(ov1);			
		}	
		// Het Preparedstatement sluiten
		pstmt.close();
		rs.close();
		
		return findAllKaarten;
	}
	
/*-------OvChipkaart object toevoegen aan de database tabel OV_Chipkaart------*/
	public OvChipkaart save(OvChipkaart kaart) throws SQLException{
		String strQuery = "insert into ov_chipkaart (KAARTNUMMER, GELDIGTOT, KLASSE, SALDO, REIZIGERID) values (?, ?, ?, ?, ?)";
		PreparedStatement pstmt = getConnection().prepareStatement(strQuery);
		pstmt.setInt(1, kaart.getKaartNr());
		pstmt.setDate(2, kaart.getGeldigTot());
		pstmt.setInt(3, kaart.getKlasse());
		pstmt.setDouble(4, kaart.getSaldo());
		pstmt.setInt(5, kaart.getId());
		pstmt.executeUpdate();
		pstmt.close();
		return kaart;
	}
	
/*---------reiziger object verwijderen uit de arrayList, door de equals methode in klasse Reiziger aan te roepen met remove--------*/
	public boolean delete(OvChipkaart kaart) throws SQLException{
/*---------met reiziger object als parameter--------*/
		Statement stmt = getConnection().createStatement();
		String strQuery = "DELETE FROM OV_CHIPKAART WHERE KAARTNUMMER = " + kaart.getKaartNr();
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
	public OvChipkaart update(OvChipkaart kaart) throws SQLException{
		String strQuery = "UPDATE OV_CHIPKAART SET REIZIGERID=?, GELDIGTOT=?, KLASSE=?, SALDO=? WHERE KAARTNUMMER=?";
		PreparedStatement pstmt = getConnection().prepareStatement(strQuery);
		
		pstmt.setInt(1, kaart.getId());
		pstmt.setDate(2, kaart.getGeldigTot());
		pstmt.setInt(3, kaart.getKlasse());
		pstmt.setDouble(4, kaart.getSaldo());
		pstmt.setInt(5, kaart.getKaartNr());
		
		pstmt.executeUpdate(/*strQuery*/);
		pstmt.close();
		return kaart;
	}
	
/*------een reiziger in de database vinden door op de datum te zoeken------*/
	public List<OvChipkaart> findByGeldigTot(Date geldigTot) throws SQLException{
		ArrayList<OvChipkaart> gtselection = new ArrayList<OvChipkaart>(); 	//nieuwe arrayList aanmaken waarin een object opgeslagen kan worden
		ProductDao pDao = new ProductDaoImpl();
		
		String strQuery = "select * from ov_chipkaart where geldigtot=date '" + geldigTot + "'";
		Statement stmt = getConnection().createStatement();
		ResultSet rs = stmt.executeQuery(strQuery);
		OvChipkaart ov1 = null;
		
		while(rs.next()) {
			ov1 = new OvChipkaart(rs.getInt("KAARTNUMMER"), rs.getDate("GELDIGTOT"), rs.getInt("KLASSE"), rs.getDouble("SALDO"), rs.getInt("REIZIGERID"));
			gtselection.add(ov1);
			ov1.setEigenaar(rDAO.findById(rs.getInt("REIZIGERID")));
			ov1.setProducten(pDao.findByOv(ov1));
			
		}
		stmt.close();
		rs.close();
		
		return gtselection;
	}
	
	public List<OvChipkaart> findByOwner(Reiziger r) throws SQLException{
		List<OvChipkaart> ovlijst = new ArrayList<OvChipkaart>();
		ProductDao pDao = new ProductDaoImpl();
		
		String strQuery = "select * from ov_chipkaart where REIZIGERID = ?";
		PreparedStatement pstmt = getConnection().prepareStatement(strQuery);
		pstmt.setInt(1, r.getId());
		ResultSet rs = pstmt.executeQuery();
		OvChipkaart ov1 = null;
		while(rs.next()){
			ov1 = new OvChipkaart(rs.getInt("KAARTNUMMER"), rs.getDate("GELDIGTOT"), rs.getInt("KLASSE"), rs.getDouble("SALDO"), rs.getInt("REIZIGERID"));
			ov1.setEigenaar(r);
			ov1.setProducten(pDao.findByOv(ov1));
			ovlijst.add(ov1);
		}
		pstmt.close();
		rs.close();
		return ovlijst;
	}
	
	public List<OvChipkaart> findByProduct(Product p) throws SQLException{
		ProductDao pDao = new ProductDaoImpl();
		List<OvChipkaart> ovlijst = new ArrayList<OvChipkaart>();
		String strQuery = "select ov.* \r\n" + 
				"from ov_chipkaart ov, ov_chipkaart_product ovp\r\n" + 
				"where ovp.productnummer = ? and ov.kaartnummer = OVP.KAARTNUMMER";
		PreparedStatement pstmt = getConnection().prepareStatement(strQuery);
		pstmt.setInt(1, p.getProductNr());
		ResultSet rs = pstmt.executeQuery();
		OvChipkaart ov = null;
		
		while(rs.next()){
			ov = new OvChipkaart(rs.getInt("KAARTNUMMER"), rs.getDate("GELDIGTOT"), rs.getInt("KLASSE"), rs.getDouble("SALDO"), rs.getInt("REIZIGERID"));
			ov.setEigenaar(rDAO.findById(rs.getInt("REIZIGERID")));
			ovlijst.add(ov);
		}
		
		pstmt.close();
		rs.close();
		return ovlijst;
	}	
}
