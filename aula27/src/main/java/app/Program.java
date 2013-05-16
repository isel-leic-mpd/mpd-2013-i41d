package app;
import static java.lang.System.in;
import static java.lang.System.out;

import java.sql.SQLException;
import java.util.Scanner;

import model.Product;
import orm.DataMapper;
import orm.JdbcBinder;
import orm.JdbcCmd;
import orm.JdbcCmdTemplate;
import orm.JdbcExecutor;
import orm.northwind.JdbcProductsMapper;
import orm.northwind.ProductConverter;

import com.microsoft.sqlserver.jdbc.SQLServerDataSource;

public class Program {
	public static void main(String[] args) throws SQLException {
		SQLServerDataSource ds = new SQLServerDataSource();
		ds.setUser("myAppUser");
		ds.setPassword("fcp");
		JdbcExecutor exec = new JdbcExecutor(ds);
		DataMapper<Integer, Product> prods = new JdbcProductsMapper(exec);
		
		Iterable<Product> allProds = prods.getAll();
		int total = 0;
		for (Product product : allProds) {
			total++;
		}
		System.out.println(total);
		
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
			out.println(prods.getById(id));
		}

    }
}
