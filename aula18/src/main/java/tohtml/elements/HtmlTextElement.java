package tohtml.elements;

import java.io.PrintStream;

import tohtml.HtmlElement;

public class HtmlTextElement implements HtmlElement{
	
	private String tag;
	private String text;
	
	public HtmlTextElement(String tag, String text) {
		this.tag = tag;
		this.text = text;
	}


	@Override
	public void writeTo(PrintStream out) {
		out.println(String.format("<%s>%s</%s>", tag, text, tag));
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
