package p3;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ProductDaoImpl extends OracleBaseDAO implements ProductDao {
	
	OvChipkaartDao ovDao = new OvChipkaartDaoImpl();
	
	public ProductDaoImpl() throws SQLException {
		getConnection();
	}

	@Override
	public List<Product> findAll() {
		List<Product> gevondenProducten = new ArrayList<Product>();
		
		try (Connection con = super.getConnection()) {
			String query = "SELECT * FROM product";
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			
			Product p1 = null;
			
			while (rs.next()) {
				int productNummer = rs.getInt("productnummer");
				String productNaam = rs.getString("productnaam");
				String beschrijving = rs.getString("beschrijving");
				double prijs = rs.getDouble("prijs");
				
				p1 = new Product(productNummer, productNaam, beschrijving, prijs);
				p1.setKaartList(ovDao.findByProduct(p1));
				gevondenProducten.add(p1);
			}
			stmt.close();
			rs.close();
		}
		catch (SQLException sqle) {
			sqle.printStackTrace();
		}
		return gevondenProducten;
	}

	@Override
	public void save(Product p) {
		try(Connection con = super.getConnection()) {
			String query = "INSERT INTO product VALUES(?, ?, ?, ?)";
			PreparedStatement pstmt = con.prepareStatement(query);
			pstmt.setInt(1, p.getProductNr());
			pstmt.setString(2, p.getProductNaam());
			pstmt.setString(3, p.getBeschrijving());
			pstmt.setDouble(4, p.getPrijs());
			pstmt.executeQuery();
			pstmt.close();
		}
		catch(SQLException sqle) {
			sqle.printStackTrace();
		}	
	}

	@Override
	public Product update(Product p) {
		try (Connection con = super.getConnection()) {
			String query = "UPDATE ov_chipkaart SET productnaam = ?, beschrijving = ?, prijs = ? WHERE productnummer = ?";
			PreparedStatement pstmt = con.prepareStatement(query);
			pstmt.setString(1, p.getProductNaam());
			pstmt.setString(2, p.getBeschrijving());
			pstmt.setDouble(3, p.getPrijs());
			pstmt.setInt(4, p.getProductNr());
			pstmt.executeQuery();
			pstmt.close();
		}
		catch (SQLException sqle) {
			sqle.printStackTrace();
		}
		
		return p;
	}

	@Override
	public boolean delete(Product p) {
		try (Connection con = super.getConnection()) {
			String query = "DELETE FROM product WHERE kaartnummer = ?";
			PreparedStatement pstmt = con.prepareStatement(query);
			pstmt.setInt(1, p.getProductNr());
			pstmt.executeQuery();
			pstmt.close();
			return true;
		}
		catch (SQLException sqle) {
			sqle.printStackTrace();
			return false;
		}
	}

	@Override
	public List<Product> findByProductNr(int pNr) {
		List<Product> gevondenProducten = new ArrayList<Product>();
		try (Connection con = super.getConnection()) {
			String query = "SELECT * FROM product WHERE productnummer = ?";
			PreparedStatement pstmt = con.prepareStatement(query);
			pstmt.setInt(1, pNr);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				pNr = rs.getInt("productnummer");
				String productNaam = rs.getString("productnaam");
				String beschrijving = rs.getString("beschrijving");
				double prijs = rs.getDouble("prijs");
				Product newProduct = new Product(pNr, productNaam, beschrijving, prijs);
				gevondenProducten.add(newProduct); 
			}
			pstmt.close();
			rs.close();
		}
		catch(SQLException sqle) {
			sqle.printStackTrace();
		}
		return gevondenProducten;
	}
	
	public List<Product> findByProductNaam(String productNaam) {
		List<Product> gevondenProducten = new ArrayList<Product>();
		try (Connection con = super.getConnection()) {
			String query = "SELECT * FROM product WHERE productnaam = ?";
			PreparedStatement pstmt = con.prepareStatement(query);
			pstmt.setString(1, productNaam);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				int pNr = rs.getInt("productnummer");
				String pNm = rs.getString("productnaam");
				String beschrijving = rs.getString("beschrijving");
				double prijs = rs.getDouble("prijs");
				Product newProduct = new Product(pNr, pNm, beschrijving, prijs);
				gevondenProducten.add(newProduct); 
			}
			pstmt.close();
			rs.close();
		}
		catch(SQLException sqle) {
			sqle.printStackTrace();
		}
		return gevondenProducten;
	}
	
	public List<Product> findByOv(OvChipkaart ov) throws SQLException{
		OvChipkaartDao ovDao = new OvChipkaartDaoImpl();
		List<Product> productlijst = new ArrayList<Product>();
		String strQuery = "select p.* \r\n" + 
							"from product p, ov_chipkaart_product ovp\r\n" + 
							"where p.productnummer = OVP.PRODUCTNUMMER and ovp.kaartnummer = ?";
		PreparedStatement pstmt = getConnection().prepareStatement(strQuery);
		pstmt.setInt(1, ov.getKaartNr());
		ResultSet rs = pstmt.executeQuery();
		Product p = null;
		while(rs.next()){
			p = new Product(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getDouble(4));
			productlijst.add(p);
		}
		
		pstmt.close();
		rs.close();
		return productlijst;
	}	
	
}
