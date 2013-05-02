package app.test.model;

import java.util.Arrays;
import java.util.Date;

import tohtml.Format;
import tohtml.HtmlElement;
import tohtml.HtmlFormatter;
import tohtml.elements.HtmlTextElement;

public class StudentDto {
	
	public final String name;
	@Format(formatter="formatId")
	public final String id;
	public final Date birthDate;
	public final Iterable<Course> courses;
	@Format(formatter="formatDepartment")
	public Department dep = new Department();
	
	public StudentDto(String name, String id, Date birthDate, Course...courses) {
		super();
		this.name = name;
		this.id = id;
		this.birthDate = birthDate;
		this.courses = Arrays.asList(courses);
	}

	private static HtmlElement formatId(String name, String val) {
		return new HtmlTextElement("h2", name + ": " + val);
	}

	private static HtmlElement formatDepartment(String name, Department dep){
		return new HtmlTextElement("h3", "Department Ole");
	} 
	
}
