import static java.lang.System.in;
import static java.lang.System.out;

import java.sql.SQLException;
import java.util.Scanner;

import javax.sql.DataSource;

import com.microsoft.sqlserver.jdbc.SQLServerDataSource;

public class App {
	public static void main(String[] args) throws SQLException {
		ProductDataAccessor data = new ProductDataAccessor();
		DataSource ds = new SQLServerDataSource();
		
		/*
		Iterable<Product> prods = data.getProducts(ds);
		for (Product product : prods) {
	    System.out.println(product);
	    */
		
		//
		// run command shell
		//
		Scanner cin = new Scanner(in);
		out.println("****** Commmand shell application *****");
		while(true){
			out.print("> Insert the employee id: ");
			out.flush();
			String inLine = cin.nextLine();
			int id = Integer.parseInt(inLine);
			out.println(data.getProductById(ds, id));
		}

    }
}
