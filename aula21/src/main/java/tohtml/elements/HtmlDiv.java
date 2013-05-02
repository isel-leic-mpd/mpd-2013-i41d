package tohtml.elements;

import java.io.PrintStream;

import tohtml.HtmlComposedElement;

public class HtmlDiv extends HtmlComposedElement{

	@Override
	protected void doBefore(PrintStream out) {
		out.println("<div>");
	}

	@Override
	protected void doAfter(PrintStream out) {
		out.println("</div>");
	}
	
	
}
