package p3;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;

public class Main3 {

	public static void main(String[] args) throws ParseException, SQLException{
		ProductDao pDAO = new ProductDaoImpl();
		
		
		List<Product> test = pDAO.findAll();
		for(int i=0; i < test.size(); i++) {
			System.out.println(test.get(i));
		}
		
		
		
	}

}
