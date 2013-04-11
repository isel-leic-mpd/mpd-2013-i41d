package tohtml;

import java.io.PrintStream;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;

public abstract class HtmlElement {
	private final Collection<HtmlElement> children = new LinkedList<HtmlElement>();
	
	public Iterable<HtmlElement> getChildren(){
		return Collections.unmodifiableCollection(children);
	}
	
	public void addElement(HtmlElement elem){
		children.add(elem);
	}
	
	protected abstract void doBefore(PrintStream out);
	
	protected abstract void doAfter(PrintStream out);
}
