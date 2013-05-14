package app;
import static java.lang.System.in;
import static java.lang.System.out;

import java.sql.SQLException;
import java.util.Scanner;

import javax.sql.DataSource;

import model.Product;

import orm.JdbcCmd;
import orm.JdbcExecutor;
import orm.northwind.CmdGetProductById;

import com.microsoft.sqlserver.jdbc.SQLServerDataSource;

public class Program {
	public static void main(String[] args) throws SQLException {
		SQLServerDataSource ds = new SQLServerDataSource();
		ds.setUser("myAppUser");
		ds.setPassword("fcp");
		JdbcExecutor exec = new JdbcExecutor(ds);
		JdbcCmd<Product> c = new CmdGetProductById();
		
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
			out.println(exec.executeQuery(c, id));
		}

    }
}
