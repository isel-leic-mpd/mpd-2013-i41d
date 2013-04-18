package tohtml;

import java.util.Date;

public abstract class AbstractHtmlFormatter implements HtmlFormatter{

	
	
	@Override
	public final HtmlElement format(String name, Object val) {
		if(val instanceof Iterable){
			return formatIterable(name, val);
		}
		else if(val instanceof Date){
			return formatDate(name, val);
		}else 
			return formatObject(name, val);	

	}

	protected abstract HtmlElement formatObject(String name, Object val);

	protected abstract HtmlElement formatDate(String name, Object val);

	protected abstract HtmlElement formatIterable(String name, Object val) ;

}
