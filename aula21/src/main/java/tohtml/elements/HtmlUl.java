package tohtml.elements;

import java.io.PrintStream;

import tohtml.HtmlComposedElement;

public class HtmlUl extends HtmlComposedElement{

	@Override
	protected void doBefore(PrintStream out) {
		out.println("<ul>");
	}

	@Override
	protected void doAfter(PrintStream out) {
		out.println("</ul>");
	}
	
	
}
