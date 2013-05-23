package orm.northwind;

import model.Category;
import orm.JdbcAbstractMapper;
import orm.JdbcBinder;
import orm.JdbcCmd;
import orm.JdbcExecutor;

public class JdbcCategoryMapper extends JdbcAbstractMapper<Integer, Category>{

	public JdbcCategoryMapper(JdbcExecutor exec) {
		super(exec);
	}

	
	@Override
	protected void updateKeyOnValue(Category value, Integer key) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected JdbcCmd<Integer> cmdInsert() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected JdbcCmd<Category> cmdGetAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected JdbcCmd<Category> cmdGetById() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected JdbcCmd<Category> cmdUpdate() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected JdbcCmd<Category> cmdDelete() {
		// TODO Auto-generated method stub
		return null;
	}

}
