package app.test.model;

import java.util.Arrays;
import java.util.Date;

import tohtml.Format;
import tohtml.HtmlElement;
import tohtml.HtmlFormatter;
import tohtml.elements.HtmlTextElement;

public class StudentDto {
	
	public final String name;
	@Format(IdFormatter.class)
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

	public static class IdFormatter implements HtmlFormatter<String>{
		@Override
		public HtmlElement format(String name, String val) {
			return new HtmlTextElement("h2", name + ": " + val);
		}

		@Override
		public Class<String> supportedType() {
			return String.class;
		}
		
	}

}
