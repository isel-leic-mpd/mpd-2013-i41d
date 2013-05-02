package tohtml;

import java.io.PrintStream;

public class HtmlSingleElement implements HtmlElement{
	
	private String tag;
	
	public HtmlSingleElement(String tag) {
		this.tag = tag;
	}

	@Override
	public void writeTo(PrintStream out) {
		out.println(String.format("<%s/>", tag));
	}

	@Override
	public Iterable<HtmlElement> getChildren() {
		throw new UnsupportedOperationException();
	}

	@Override
	public void addElement(HtmlElement elem) {
		throw new UnsupportedOperationException();
	}
	
}
