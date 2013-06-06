package app;

import model.Logger;
import model.edu.Course;
import model.edu.School;
import model.edu.Student;

public class Program {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Course c = new Course("LEIC", 
				new Student(12312, "To Ze"),
				new Student(45654, "Matias"),
				new Student(31311, "Jacinta"));
		
		Logger l = new Logger();
		
		l.log(c);
		
		l.log(new School("ISEL", "Instituto Superor de Engenharia de Lisboa"));
		
	}

}
