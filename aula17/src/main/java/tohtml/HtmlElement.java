package tohtml;

import java.io.PrintStream;

public interface HtmlElement {

	void writeTo(PrintStream out);
	
	public Iterable<HtmlElement> getChildren();
	
	public void addElement(HtmlElement elem);
	
}
