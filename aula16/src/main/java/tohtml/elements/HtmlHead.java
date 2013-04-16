package tohtml.elements;

import java.io.PrintStream;

import tohtml.HtmlComposedElement;

public class HtmlHead extends HtmlComposedElement{

	@Override
	protected void doBefore(PrintStream out) {
		out.println("<head>");
	}

	@Override
	protected void doAfter(PrintStream out) {
		out.println("</head>");
	}
	
	
}
