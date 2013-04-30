package tohtml.elements;

import java.io.PrintStream;

import tohtml.HtmlComposedElement;

public class HtmlBody extends HtmlComposedElement{

	@Override
	protected void doBefore(PrintStream out) {
		out.println("<body>");
	}

	@Override
	protected void doAfter(PrintStream out) {
		out.println("</body>");
	}
	
	
}
