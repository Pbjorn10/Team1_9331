package com.team1.ecommerce.server.repository;

import com.team1.ecommerce.shared.model.Cart;


public interface CartRepository {

        /**
         * Finds the cart for the given user id.
         *
         * @param userId id of the user
         * @return cart for the user, or an empty cart if none exists
         */
    Cart findByUserId(String userId);

    /**
     * Saves or updates the cart for the given user.
     *
     * @param cart cart to persist
     */
    void save(Cart cart);

    /**
     * Removes all items from the cart of the given user.
     *
     * @param userId id of the user
     */
    void clearCart(String userId);
}

