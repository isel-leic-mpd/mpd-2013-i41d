package app.test.model;

public class Course {
	
	public static final Course MPD = new Course("MPD", 4.5f);
	public static final Course AVE= new Course("AVE", 4.5f);
	public static final Course PC = new Course("PC", 4.5f);
	
	final String name;
	final float ects;
	
	private Course(String name, float ects) {
		this.name = name;
		this.ects = ects;
	}

	@Override
	public String toString() {
		return "[name=" + name + ", ects=" + ects + "]";
	}

	
	
}
