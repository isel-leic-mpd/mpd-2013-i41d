package app.test.model;

import java.util.Date;
import java.util.LinkedList;

public class StudentDto {
	
	public final String name;
	public final String id;
	public final Date birthDate;
	public final Iterable<Course> courses;
	
	public StudentDto(String name, String id, Date birthDate) {
		super();
		this.name = name;
		this.id = id;
		this.birthDate = birthDate;
		courses = new LinkedList<Course>();
	}
	
}
