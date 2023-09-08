package spring;

import parsing.Product;

import java.util.List;

public class ProductsAnswer {
    int amount;
    List<Product> products;

    public ProductsAnswer(int amount, List<Product> products) {
        this.amount = amount;
        this.products = products;
    }

    public int getAmount() {
        return amount;
    }

    public List<Product> getProducts() {
        return products;
    }
}
