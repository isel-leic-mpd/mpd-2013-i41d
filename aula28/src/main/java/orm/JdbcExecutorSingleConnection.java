package orm;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.LinkedList;

import javax.sql.DataSource;

public class JdbcExecutorSingleConnection implements JdbcExecutor{
	
	final DataSource ds;
	Connection con;
	final boolean toCommit;

	public JdbcExecutorSingleConnection(DataSource ds, boolean toCommit) {
		this.ds = ds;
		this.toCommit = toCommit;
	}
	
	private Connection initConnection() throws SQLException{
		if(con == null){
			con = ds.getConnection();
			con.setAutoCommit(false);
		}
		return con;
	}

	public <T> Iterable<T> executeQuery(JdbcCmd<T> cmd, Object...args) throws SQLException {
		Connection con = initConnection(); // Establish the connection.
		try (PreparedStatement stmt = con.prepareStatement(cmd.getSql())	
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

	public <T> void executeUpdate(JdbcCmd<T> cmd, Object...args) throws SQLException {
		Connection con = initConnection(); // Establish the connection.
		try (PreparedStatement stmt = con.prepareStatement(cmd.getSql())	
				) {
			cmd.bind(stmt, args);
			stmt.executeUpdate();
		}		
	}

	@Override
	public void close() throws Exception {
		if(con != null){
			if(toCommit)
				con.commit();
			else
				con.rollback();
			con.close();
		}
	}
}
