package app.test.model;

import app.Validator;

public class Person {
	public final String id;
	private String name;
	private int age;
	
	public Person(){
		this.id = null;
	}
	
	public Person(String id, String name, int age) {
		this.id = id;
		this.name = name;
		this.age = age;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Validator(AgeValidator.class)
	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}
}
