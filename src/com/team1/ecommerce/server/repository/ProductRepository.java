package com.team1.ecommerce.server.repository;

import com.team1.ecommerce.shared.model.Product;

import java.util.List;


public interface ProductRepository {

    /**
     * Returns all products.
     *
     * @return list of all products (may be empty, never {@code null})
     */
    List<Product> findAll();

    /**
     * Finds a product by its id.
     *
     * @param id product identifier
     * @return matching {@link Product} or {@code null} if none exists
     */
    Product findById(String id);

    /**
     * Persists a new product.
     *
     * @param product product to save
     */
    void save(Product product);

    /**
     * Updates an existing product.
     *
     * @param product product with updated fields
     */
    void update(Product product);

    /**
     * Deletes a product by id.
     *
     * @param id id of product to delete
     */
    void delete(String id);
}

