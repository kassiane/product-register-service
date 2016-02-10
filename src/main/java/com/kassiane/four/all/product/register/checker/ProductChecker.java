package com.kassiane.four.all.product.register.checker;

import com.kassiane.four.all.product.register.service.domain.Product;

public class ProductChecker {

	public static final String INFORM_PRICE = "Informe o preço do produto.";
	public static final String INVALID_NAME = "O nome do produto informado é inválido.";
	private final Product product;

	public ProductChecker(final Product product) {
		this.product = product;
	}

	private boolean checkName() {
		if (this.product.getName().equals("") || this.product.getName() == null)
			throw new IllegalArgumentException(INVALID_NAME);
		return true;
	}

	private boolean checkPrice() {
		if (String.valueOf(this.product.getPrice()).replace('.', '0')
				.split("0").length == 0)
			throw new IllegalArgumentException(INFORM_PRICE);
		return true;
	}

	public void checkProduct() {
		this.checkName();
		this.checkPrice();
	}
}
