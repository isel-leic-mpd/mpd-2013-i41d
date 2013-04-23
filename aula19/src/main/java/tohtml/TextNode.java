package tohtml;

import java.io.PrintStream;

public class TextNode implements HtmlElement{
	
	final String text;

	public TextNode(String text) {
		this.text = text;
	}

	@Override
	public void writeTo(PrintStream out) {
		out.print(text);
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
