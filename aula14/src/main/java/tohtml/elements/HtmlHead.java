package tohtml.elements;

import java.io.PrintStream;

import tohtml.HtmlElement;

public class HtmlHead extends HtmlElement{

	@Override
	protected void doBefore(PrintStream out) {
		out.println("<head>");
	}

	@Override
	protected void doAfter(PrintStream out) {
		out.println("</head>");
	}
	
	
}
