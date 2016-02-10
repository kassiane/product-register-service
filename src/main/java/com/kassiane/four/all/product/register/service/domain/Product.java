package com.kassiane.four.all.product.register.service.domain;

import javax.swing.ImageIcon;

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;

public class Product {
	private final long id;
	private final String name;
	private final float price;
	private final ImageIcon icon;

	public Product(final long id, final String name, final float price,
			final ImageIcon icon) {
		this.id = id;
		this.name = name;
		this.price = price;
		this.icon = icon;
	}

	public long getId() {
		return this.id;
	}

	public String getName() {
		return this.name;
	}

	public float getPrice() {
		return this.price;
	}

	public ImageIcon getIcon() {
		return this.icon;
	}

	@Override
	public int hashCode() {
		return Objects.hashCode(this.id, this.name, this.price, this.icon);
	}

	@Override
	public boolean equals(final Object object) {
		if (object instanceof Product) {
			final Product that = (Product) object;
			return Objects.equal(this.id, that.id)
					&& Objects.equal(this.name, that.name)
					&& Objects.equal(this.price, that.price)
					&& Objects.equal(this.icon, that.icon);
		}
		return false;
	}

	@Override
	public String toString() {
		return MoreObjects.toStringHelper(this).add("id", this.id)
				.add("name", this.name).add("price", this.price)
				.add("icon", this.icon).toString();
	}

}
