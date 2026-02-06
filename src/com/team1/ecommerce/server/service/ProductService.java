package com.team1.ecommerce.server.service;

import com.team1.ecommerce.server.repository.ProductRepository;
import com.team1.ecommerce.shared.model.Product;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Business logic for working with products.
 *
 * <p>Business logic for working with products.</p>
 */
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    /**
     * Returns an immutable list of all products.
     */
    public List<Product> getAllProducts() {
        List<Product> products = productRepository.findAll();
        return products == null
                ? Collections.emptyList()
                : Collections.unmodifiableList(new ArrayList<>(products));
    }

    /**
     * Searches products by name and (optionally) category.
     *
     * @param nameQuery   text to look for inside the product name (case‑insensitive)
     * @param categoryOpt optional category filter, may be {@code null} or blank
     * @return matching products
     */
    public List<Product> search(String nameQuery, String categoryOpt) {

        String nameFilter = nameQuery == null ? "" : nameQuery.trim().toLowerCase();
        String categoryFilter = categoryOpt == null ? "" : categoryOpt.trim().toLowerCase();

        List<Product> result = new ArrayList<>();

        for (Product p : productRepository.findAll()) {

            boolean nameMatches = nameFilter.isEmpty()
                    || (p.getName() != null
                    && p.getName().toLowerCase().contains(nameFilter));

            boolean categoryMatches = categoryFilter.isEmpty()
                    || (p.getCategory() != null
                    && p.getCategory().toLowerCase().contains(categoryFilter));

            if (nameMatches && categoryMatches) {
                result.add(p);
            }
        }

        return result;
    }

    /**
     * Decreases stock for a product.
     *
     * @param productId product identifier
     * @param quantity  amount to deduct (must be positive)
     * @return {@code true} if stock was updated, {@code false} if not enough stock
     */
    public synchronized boolean updateStock(String productId, int quantity) {

        if (quantity <= 0) {
            return false;
        }

        Product product = productRepository.findById(productId);
        if (product == null) {
            return false;
        }

        int currentStock = product.getStock();

        if (currentStock < quantity) {
            // Not enough units available
            return false;
        }

        product.setStock(currentStock - quantity);
        productRepository.update(product);

        return true;
    }
}

