package tohtml.elements;

import java.io.PrintStream;

import tohtml.HtmlElement;

public class HtmlTitle extends HtmlElement{

	final String text;
	
	public HtmlTitle(String text) {
		this.text = text;
	}

	@Override
	protected void doBefore(PrintStream out) {
		out.print("<title>" + text);
	}

	@Override
	protected void doAfter(PrintStream out) {
		out.println("</title>");
	}
	
	
}
