package tohtml;

public interface HtmlFormatter<T>{

	public HtmlElement format(String name, T val);
	public Class<T> supportedType();
}
