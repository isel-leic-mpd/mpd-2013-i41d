package app.test.model;

import java.util.Arrays;
import java.util.Date;

public class StudentDto {
	
	public final String name;
	public final String id;
	public final Date birthDate;
	public final Iterable<Course> courses;
	
	public StudentDto(String name, String id, Date birthDate, Course...courses) {
		super();
		this.name = name;
		this.id = id;
		this.birthDate = birthDate;
		this.courses = Arrays.asList(courses);
	}
	
}
