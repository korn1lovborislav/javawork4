package com.shop.storage;

import com.shop.products.Product;

import java.util.ArrayList;
import java.util.List;

public class OrderRepository<T extends Product> {
    private final List<T> items = new ArrayList<>();

    public void add(T item) {
        items.add(item);
    }

    public List<T> getAll() {
        return items;
    }
}