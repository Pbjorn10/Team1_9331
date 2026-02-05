package com.team1.ecommerce.server.controller;

import com.team1.ecommerce.server.service.PaymentService;
import com.team1.ecommerce.shared.model.Cart;
import com.team1.ecommerce.shared.model.Transaction;

public class PaymentController {

    private final PaymentService paymentService;

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    /**
     * Executes checkout operation.
     *
     * @param userId user performing payment
     * @param cart   user's cart
     * @return Transaction result or null if failed
     */
    public Transaction checkout(String userId, Cart cart) {

        try {
            return paymentService.processPayment(userId, cart);
        } catch (Exception e) {

            // Log error for debugging/audit
            System.err.println("Payment failed: " + e.getMessage());
            return null;
        }
    }
}