package app.test.model;

import tohtml.Format;
import tohtml.HtmlElement;
import tohtml.HtmlFormatter;
import tohtml.elements.HtmlTextElement;

public class PersonDto {
	
	public final String name;
	public final String id;
	public final int age;
	
	public PersonDto() {
		this.name = null;
		this.id = null;
		this.age = 0;
	}
	
	public PersonDto(String name, String id, int age) {
		super();
		this.name = name;
		this.id = id;
		this.age = age;
	}
	
	class AgeFormatter implements HtmlFormatter<Integer>{
		@Override
		public HtmlElement format(String name, Integer val) {
			return new HtmlTextElement("h2", name + ": " + val);
		}

		@Override
		public Class<Integer> supportedType() {
			return Integer.class;
		}
		
	}
}
