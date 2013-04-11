package tohtml;

import java.io.PrintStream;

public class HtmlWriter {

	public void writeTo(PrintStream out, Parser p){
		writeElement(out, p.getRoot());
	}
	
	private void writeElement(PrintStream out, HtmlElement elem){
		elem.doBefore(out);
		for(HtmlElement c: elem.getChildren()){
			writeElement(out, c);
		}
		elem.doAfter(out);
	}
}
