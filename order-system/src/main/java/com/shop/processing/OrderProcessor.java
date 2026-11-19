package com.shop.processing;

import com.shop.products.Product;
import com.shop.threads.ThreadManager;

import java.util.List;
import java.util.concurrent.Future;
import java.util.function.Function;

public class OrderProcessor<T extends Product> {
    private final ThreadManager threadManager;

    public OrderProcessor(ThreadManager threadManager) {
        this.threadManager = threadManager;
    }

    private void processSingle(T product, Function<T, Double> discountFunc) {
        try {
            if (product.getPrice() <= 0) {
                throw new OrderProcessingException("Invalid price for " + product.getName());
            }
            double newPrice = discountFunc.apply(product);
            
            System.out.printf("[%s] Processed %s, final price: %.2f%n",
                    Thread.currentThread().getName(), product.getName(), newPrice);
            
            printProduct(product);
        } catch (OrderProcessingException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }

    public static void printProduct(Product p) {
        System.out.println("  -> " + p.getName() + " | " + p.getPrice());
    }

    public static double applyDiscount(Product p) {
        return p.getPrice() * 0.9;
    }

    @SuppressWarnings("unchecked")
    public List<? extends Future<?>> processAll(List<? extends T> products, 
                                                java.util.function.Predicate<T> filter, 
                                                Function<T, Double> discountFunc) {
        List<T> typedList = (List<T>) products;
        return threadManager.submitTasks(typedList, filter, 
                p -> processSingle(p, discountFunc));
    }
}