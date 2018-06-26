package p3;

import java.sql.SQLException;
import java.util.*;
import java.sql.Date;

public interface ReizigerDao {
	public List<Reiziger> findAll() throws SQLException;
	public Reiziger findById(int id) throws SQLException;
	public List<Reiziger> findByGBdatum(Date GBdatum) throws SQLException;
	public Reiziger save(Reiziger reiziger) throws SQLException;
	public Reiziger update(Reiziger reiziger) throws SQLException;
	//public boolean delete(int id) throws SQLException;
	public boolean delete(Reiziger reiziger) throws SQLException;
	//public boolean delete(Reiziger reiziger) throws SQLException;
	public void closeConnection() throws SQLException;
}