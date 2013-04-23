package tohtml.elements;

import java.io.PrintStream;

import tohtml.HtmlComposedElement;

public class HtmlRoot extends HtmlComposedElement{

	@Override
	protected void doBefore(PrintStream out) {
		out.println("<html>");
	}

	@Override
	protected void doAfter(PrintStream out) {
		out.println("</html>");
	}
	
	
}
