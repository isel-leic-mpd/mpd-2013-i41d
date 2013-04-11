package tohtml;

import tohtml.elements.HtmlHead;
import tohtml.elements.HtmlRoot;
import tohtml.elements.HtmlTitle;

public class Parser {
	
	final HtmlRoot root;
	
	public Parser(Object src){
		root = new HtmlRoot();
		/*
		 * Head
		 */
		HtmlHead head = new HtmlHead();
		head.addElement(new HtmlTitle(src.getClass().getSimpleName()));
		root.addElement(head);
		/*
		 * Body
		 */
	}
	
	public HtmlElement getRoot(){
		return root;
	}
}
