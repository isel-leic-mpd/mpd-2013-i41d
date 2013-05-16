package model;

public class Product {

	int id;
	String productName;
	double unitPrice;
	int unitsInStock;

	public Product(int id, String name, double price, int stock) {
		super();
		this.id = id;
		this.productName = name;
		this.unitPrice = price;
		this.unitsInStock = stock;
	}

	@Override
	public String toString() {
		return "Product [id=" + id + ", name=" + productName + ", price=" + unitPrice
				+ ", stock=" + unitsInStock + "]";
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public double getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(double unitPrice) {
		this.unitPrice = unitPrice;
	}

	public int getUnitsInStock() {
		return unitsInStock;
	}

	public void setUnitsInStock(int unitsInStock) {
		this.unitsInStock = unitsInStock;
	}
	
	
}
