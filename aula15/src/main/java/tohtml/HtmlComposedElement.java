package tohtml;

import java.io.PrintStream;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;

public abstract class HtmlComposedElement implements HtmlElement{
	private final Collection<HtmlElement> children = new LinkedList<HtmlElement>();
	
	public Iterable<HtmlElement> getChildren(){
		return Collections.unmodifiableCollection(children);
	}
	
	public void addElement(HtmlElement elem){
		children.add(elem);
	}
	
	protected abstract void doBefore(PrintStream out);
	
	protected abstract void doAfter(PrintStream out);

	public final void writeTo(PrintStream out) {
		this.doBefore(out);
		for(HtmlElement c: this.getChildren()){
			c.writeTo(out);
		}
		this.doAfter(out);
	}

}
