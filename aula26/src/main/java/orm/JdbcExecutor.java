package orm;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.LinkedList;

import javax.sql.DataSource;

public class JdbcExecutor {

	final DataSource ds;
	
	public JdbcExecutor(DataSource ds) {
		this.ds = ds;
	}

	public <T> Iterable<T> executeQuery(JdbcCmd<T> cmd, Object...args) throws SQLException {
		try (Connection con = ds.getConnection(); // Establish the connection.
		     PreparedStatement stmt = con.prepareStatement(cmd.getSql())	
			) {
				cmd.bind(stmt, args);
				ResultSet rs = stmt.executeQuery();
				Collection<T> res = new LinkedList<T>();
				/*
				 * Iterate through the data in the result set and display it.
				 */
				while (rs.next()) {
					T item = cmd.convert(rs);
					res.add(item);
				}
				return res;
			}		

	}

}
