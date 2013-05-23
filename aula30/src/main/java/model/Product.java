package model;

import orm.ValueHolder;

public class Product {

	int id;
	String productName;
	double unitPrice;
	int unitsInStock;
	Supplier supplier;
	ValueHolder<Category> category;
	
	public Supplier getSupplier() {
		return supplier;
	}

	public void setSupplier(Supplier supplier) {
		this.supplier = supplier;
	}

	public Category getCategory() {
		return category.value();
	}

	public void setCategory(ValueHolder<Category> category) {
		this.category = category;
	}

	public Product(int id, String name, double price, int stock, Supplier supplier, ValueHolder<Category> category) {
		super();
		this.id = id;
		this.productName = name;
		this.unitPrice = price;
		this.unitsInStock = stock;
		this.supplier = supplier;
		this.category = category;
	}
	
	public Product(String name, double price, int stock, Supplier supplier, ValueHolder<Category> category) {
		this.productName = name;
		this.unitPrice = price;
		this.unitsInStock = stock;
		this.supplier = supplier;
		this.category = category;
	}



	public int getId() {
		return id;
	}



	public String getProductName() {
		return productName;
	}



	public double getUnitPrice() {
		return unitPrice;
	}



	public int getUnitsInStock() {
		return unitsInStock;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}



	public void setUnitPrice(double unitPrice) {
		this.unitPrice = unitPrice;
	}



	public void setUnitsInStock(int unitsInStock) {
		this.unitsInStock = unitsInStock;
	}

	@Override
	public String toString() {
		return "Product [id=" + id + ", name=" + productName + ", price=" + unitPrice
				+ ", stock=" + unitsInStock + "]";
	}
	
}
