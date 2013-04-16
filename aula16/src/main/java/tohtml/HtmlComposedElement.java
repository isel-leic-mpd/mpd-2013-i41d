package tohtml;

import java.io.PrintStream;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;

/**
 * @author  mcarvalho
 */
public abstract class HtmlComposedElement implements HtmlElement{
 	/**
	 * @uml.associationEnd aggregation="shared" inverse="tohtml.HtmlElement" multiplicity="(0 -1)" 
	 */
	private final Collection<HtmlElement> children = new LinkedList<HtmlElement>();
	
	/**
	 * @return
	 * @uml.property  name="children"
	 */
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
