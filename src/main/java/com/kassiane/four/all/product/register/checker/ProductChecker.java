package com.kassiane.four.all.product.register.checker;

import com.kassiane.four.all.product.register.service.domain.Product;

public class ProductChecker {

    public static final String INFORM_PRICE = "Informe o preço do produto.";
    public static final String INVALID_NAME = "O nome do produto informado é inválido.";
    public static final String TOO_BIG_NAME = "O nome do produto deve conter no máximo 255 caracteres.";

    private final Product product;

    public ProductChecker(final Product product) {
        this.product = product;
    }

    private boolean checkName() {
        if (this.product.getName() == null || this.product.getName().equals(""))
            throw new IllegalArgumentException(INVALID_NAME);
        if (this.product.getName().length() > 255)
            throw new IllegalArgumentException(TOO_BIG_NAME);
        return true;
    }

    private boolean checkPrice() {
        if (this.product.getPrice() == 0)
            throw new IllegalArgumentException(INFORM_PRICE);
        return true;
    }

    public void checkProduct() {
        this.checkName();
        this.checkPrice();
    }
}
