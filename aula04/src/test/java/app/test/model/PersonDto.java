package app.test.model;

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
	
}
