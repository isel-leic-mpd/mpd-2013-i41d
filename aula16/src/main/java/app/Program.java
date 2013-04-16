package app;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import tohtml.DefaultHtmlFormatter;
import tohtml.HtmlElement;
import tohtml.HtmlFormatter;
import tohtml.HtmlTransformer;
import tohtml.elements.HtmlTextElement;
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
		
		new HtmlTransformer(new StudentDto("Ze Manel", "32451", dtformat.parse("6-12-1987")), new DefaultHtmlFormatter()).getRoot().writeTo(System.out);
		new HtmlTransformer(new StudentDto("Ze Manel", "32451", dtformat.parse("6-12-1987")), new MyFormatter()).getRoot().writeTo(new PrintStream(new FileOutputStream("dummy.html")));
		Runtime.getRuntime().exec("explorer dummy.html");
	}

	final static SimpleDateFormat dtformat = new SimpleDateFormat("dd-MM-yyyy");
	
	static class MyFormatter implements HtmlFormatter{
		@Override
		public HtmlElement format(String name, Object val) {
			if(val instanceof Iterable){
				// listar por bullets
				return new HtmlTextElement("p", name);
			}
			if(val instanceof Date){
				return new HtmlTextElement("p", name + ": " + dtformat.format(val));
			}else 
				return new HtmlTextElement("h2", name + ": " + val);	
		}

	}
}
