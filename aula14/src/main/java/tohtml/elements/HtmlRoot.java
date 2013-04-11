package tohtml.elements;

import java.io.PrintStream;

import tohtml.HtmlElement;

public class HtmlRoot extends HtmlElement{

	@Override
	protected void doBefore(PrintStream out) {
		out.println("<html>");
	}

	@Override
	protected void doAfter(PrintStream out) {
		out.println("</html>");
	}
	
	
}
