package app;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import tohtml.DateFormatter;
import tohtml.DefaultFormatter;
import tohtml.HtmlTransformer;
import tohtml.IterableFormatter;
import app.test.model.Course;
import app.test.model.StudentDto;

public class Program {

	/**
	 * @param args
	 * @throws ParseException 
	 * @throws IOException 
	 */
	public static void main(String[] args) throws ParseException, IOException {
		/*
		new HtmlWriter().writeTo(System.out, new Parser(new StudentDto("Ze Manel", "32451", 19)));
		new HtmlWriter().writeTo(new PrintStream(new FileOutputStream("dummy.txt")), new Parser(new StudentDto("Ze Manel", "32451", 19)));
		*/
		
		StudentDto std = new StudentDto("Ze Manel", "32451", dtformat.parse("6-12-1987"), Course.MPD, Course.AVE);
		HtmlTransformer transf = new HtmlTransformer(new IterableFormatter(), new DateFormatter(), new DefaultFormatter());
		transf.getRoot(std).writeTo(System.out);
		transf.getRoot(std).writeTo(new PrintStream(new FileOutputStream("dummy.html")));
		Runtime.getRuntime().exec("explorer dummy.html");
	}

	final static SimpleDateFormat dtformat = new SimpleDateFormat("dd-MM-yyyy");
}
