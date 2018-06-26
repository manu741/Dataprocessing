package p3;

import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

public interface OvChipkaartDao {
	public List<OvChipkaart> findAll() throws SQLException;
	public List<OvChipkaart> findByGeldigTot(Date geldigTot) throws SQLException;
	public OvChipkaart save(OvChipkaart kaart) throws SQLException;
	public OvChipkaart update(OvChipkaart kaart) throws SQLException;
	//public boolean delete(int id) throws SQLException;
	public boolean delete(OvChipkaart kaart) throws SQLException;
	//public boolean delete(Reiziger reiziger) throws SQLException;
	public void closeConnection() throws SQLException;
	public List<OvChipkaart> findByOwner(Reiziger r) throws SQLException;
	public List<OvChipkaart> findByProduct(Product p) throws SQLException;
}
