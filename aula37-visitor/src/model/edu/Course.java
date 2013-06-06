package model.edu;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import model.Entity;
import model.Logger;

public class Course implements Entity<String>, Iterable<Student>{

	private String id;
	private Set<Student> students;

	public Course(String id, Student...sts) {
		this.id = id;
		this.students = new HashSet<>();
		for (Student s : sts) {
			this.students.add(s);
		}
	}

	@Override
	public String getId() {
		return id;
	}

	@Override
	public void setId(String val) {
		val = id;
	}

	@Override
	public Iterator<Student> iterator() {
		return students.iterator();
	}
	
	@Override
	public void accept(Logger logger) {
		logger.visit(this);
	}
	
	
}
