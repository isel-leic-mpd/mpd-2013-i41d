package app;

import tohtml.HtmlWriter;
import tohtml.Parser;
import app.test.model.StudentDto;

public class Program {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		new HtmlWriter().writeTo(System.out, new Parser(new StudentDto("Ze Manel", "32451", 19)));
	}

}
