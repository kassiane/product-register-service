package com.kassiane.four.all.product.register.domain;

import javax.swing.ImageIcon;

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;


public class Product {
	private int id;
	private String name;
	private float price;
	private ImageIcon icon;
	
	public Product(int id, String name, float price, ImageIcon icon) {
		this.id = id;
		this.name = name;
		this.price = price;
		this.icon = icon;
	}
	
	public int getId() {
		return id;
	}
	
	public String getName() {
		return name;
	}
	
	public float getPrice() {
		return price;
	}
	
	public ImageIcon getIcon() {
		return icon;
	}

	@Override
	public int hashCode() {
		return Objects.hashCode(id, name, price, icon);
	}

	@Override
	public boolean equals(Object object) {
		if (object instanceof Product) {
			Product that = (Product) object;
			return Objects.equal(this.id, that.id)
					&& Objects.equal(this.name, that.name)
					&& Objects.equal(this.price, that.price)
					&& Objects.equal(this.icon, that.icon);
		}
		return false;
	}

	@Override
	public String toString() {
		return MoreObjects.toStringHelper(this).add("id", id).add("name", name)
				.add("price", price).add("icon", icon).toString();
	}

}
