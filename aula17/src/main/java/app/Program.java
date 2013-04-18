package app;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import tohtml.DefaultHtmlFormatter;
import tohtml.HtmlComposedElement;
import tohtml.HtmlElement;
import tohtml.HtmlFormatter;
import tohtml.HtmlTransformer;
import tohtml.elements.HtmlDiv;
import tohtml.elements.HtmlTextElement;
import tohtml.elements.HtmlUl;
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
		
		StudentDto std = new StudentDto("Ze Manel", "32451", dtformat.parse("6-12-1987"), Course.MPD, Course.AVE, Course.PC);
		new HtmlTransformer(std, new DefaultHtmlFormatter()).getRoot().writeTo(System.out);
		new HtmlTransformer(std, new MyFormatter()).getRoot().writeTo(new PrintStream(new FileOutputStream("dummy.html")));
		Runtime.getRuntime().exec("explorer dummy.html");
	}

	final static SimpleDateFormat dtformat = new SimpleDateFormat("dd-MM-yyyy");
	
	static class MyFormatter extends DefaultHtmlFormatter{
		@Override
		protected HtmlElement formatDate(String name, Object val) {
			return new HtmlTextElement("h2", name + ": " + val);	
		}
	}
}
