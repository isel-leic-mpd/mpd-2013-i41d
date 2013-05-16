package orm;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public interface JdbcCmd<T> {

	String getSql();

	void bind(PreparedStatement stmt, Object[] args) throws SQLException;

	T convert(ResultSet rs)throws SQLException;

}
