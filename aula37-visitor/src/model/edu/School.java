package model.edu;

import model.Entity;
import model.Logger;

public class School implements Entity<String>{

	private String id;
	private String name;
	
	
	public School(String id, String name) {
		this.id = id;
		this.name = name;
	}

	@Override
	public String getId() {
		return id;
	}

	@Override
	public void setId(String val) {
		id = val;
	}

	@Override
	public String toString() {
		return "School [id=" + id + ", name=" + name + "]";
	}

	@Override
	public void accept(Logger logger) {
		logger.visit(this);
	}

}
