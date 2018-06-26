package p3;

import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

public interface ProductDao {
	public List<Product> findAll();
	public void save(Product p);
	public Product update(Product p);
	public boolean delete(Product p);
	public void closeConnection() throws SQLException;
	public List<Product> findByProductNr(int pNr);
	public List<Product> findByProductNaam(String naam);
	public List<Product> findByOv(OvChipkaart ov) throws SQLException;
}
