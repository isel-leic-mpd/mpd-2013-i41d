
public class Product {

	final int id;
	final String name;
	final double price;
	final int stock;
	public Product(int id, String name, double price, int stock) {
	  super();
	  this.id = id;
	  this.name = name;
	  this.price = price;
	  this.stock = stock;
  }
	@Override
  public String toString() {
	  return "Product [id=" + id + ", name=" + name + ", price=" + price
	      + ", stock=" + stock + "]";
  }	
}
