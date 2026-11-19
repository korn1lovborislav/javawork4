package com.shop.products;

import lombok.Builder;
import lombok.Getter;

@Getter
public class Electronics extends Product {
    private String model;

    @Builder
    public Electronics(String name, String model, double price) {
        this.name = name;
        this.model = model;
        this.price = price;
    }
}