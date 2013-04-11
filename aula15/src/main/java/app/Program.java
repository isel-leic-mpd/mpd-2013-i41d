package app;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;

import tohtml.HtmlTransformer;
import app.test.model.StudentDto;

public class Program {

	/**
	 * @param args
	 * @throws FileNotFoundException 
	 */
	public static void main(String[] args) throws FileNotFoundException {
		/*
		new HtmlWriter().writeTo(System.out, new Parser(new StudentDto("Ze Manel", "32451", 19)));
		new HtmlWriter().writeTo(new PrintStream(new FileOutputStream("dummy.txt")), new Parser(new StudentDto("Ze Manel", "32451", 19)));
		*/
		
		new HtmlTransformer(new StudentDto("Ze Manel", "32451", 19)).getRoot().writeTo(System.out);
		new HtmlTransformer(new StudentDto("Ze Manel", "32451", 19)).getRoot().writeTo(new PrintStream(new FileOutputStream("dummy.html")));
	}

}
