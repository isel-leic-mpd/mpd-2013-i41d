package orm;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

import javax.inject.Inject;
import javax.sql.DataSource;

import model.Product;

public class JdbcExecutorSingleConnection implements JdbcExecutor, AutoCloseable{
	final private DataSource ds;
	final private boolean autocommit;
	private Connection con;
	
	public JdbcExecutorSingleConnection(DataSource ds, boolean autocommit) {
		this.ds = ds;
		this.autocommit = autocommit;
	}
	
	@Inject
	public JdbcExecutorSingleConnection(DataSource ds) {
		this.ds = ds;
		autocommit = false;
	}

	private Connection initConnection() throws SQLException{
		if(con == null){
			con = ds.getConnection(); // Establish the connection.
			con.setAutoCommit(autocommit);
		}
		return con;
	}
	
	public <T> Iterable<T> executeQuery(JdbcCmd<T> cmd, Object...args) throws SQLException{
		final String SQL = cmd.getSql();
		try (PreparedStatement stmt = initConnection().prepareStatement(SQL)) {
			con.setAutoCommit(autocommit);
			cmd.bind(stmt, args);
			ResultSet rs = stmt.executeQuery();
			List<T> res = new LinkedList<T>();
			/*
			 * Iterate through the data in the result set and display it.
			 */
			while (rs.next()) {
				T elem = cmd.convert(rs);
				res.add(elem);
			}
			return res;
		}		
	}
	
	public <T> void executeUpdate(JdbcCmd<T> cmd, Object...args) throws SQLException{
		final String SQL = cmd.getSql();
		try (PreparedStatement stmt = initConnection().prepareStatement(SQL)) {
			con.setAutoCommit(autocommit);
			cmd.bind(stmt, args);
			stmt.executeUpdate();
		}		
	}

	@Override
	public <K> K executeInsert(JdbcCmd<K> cmd, Object... args) throws SQLException {
		final String SQL = cmd.getSql();
		try (PreparedStatement stmt = initConnection().prepareStatement(SQL, Statement.RETURN_GENERATED_KEYS)	
				) {
			con.setAutoCommit(autocommit);
			cmd.bind(stmt, args);
			stmt.executeUpdate();
			ResultSet rs = stmt.getGeneratedKeys();
			rs.next();
			return cmd.convert(rs);
		}		
	}
	
	@Override
	public void close() throws Exception {
		if(con != null){
			if(!autocommit) con.rollback();
			con.close();
			con = null;
		}
	}
}






