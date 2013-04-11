package tohtml;

import java.io.PrintStream;

public interface HtmlElement {

	void writeTo(PrintStream out);
	
}
