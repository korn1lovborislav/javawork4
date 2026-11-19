package com.shop.products;

import lombok.Builder;
import lombok.Getter;

@Getter
public class Clothing extends Product {
    private String size;

    @Builder
    public Clothing(String name, String size, double price) {
        this.name = name;
        this.size = size;
        this.price = price;
    }
}