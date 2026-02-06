package com.team1.ecommerce.shared.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Shopping cart for a single user.
 */
public class Cart {

    private String userId;
    private final List<CartItem> items = new ArrayList<>();

    public Cart() {
    }

    public Cart(String userId) {
        this.userId = userId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public List<CartItem> getItems() {
        return items;
    }

    /**
     * Adds an item to the cart. If the product is already present, only its
     * quantity is increased.
     */
    public void addItem(CartItem newItem) {
        for (CartItem item : items) {
            if (item.getProductId().equals(newItem.getProductId())) {
                item.setQuantity(item.getQuantity() + newItem.getQuantity());
                return;
            }
        }
        items.add(newItem);
    }

    /**
     * Removes all items from the cart.
     */
    public void clear() {
        items.clear();
    }
}

