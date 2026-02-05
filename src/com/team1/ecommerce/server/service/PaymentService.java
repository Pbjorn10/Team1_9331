package com.team1.ecommerce.server.service;

import com.team1.ecommerce.shared.model.Cart;
import com.team1.ecommerce.shared.model.CartItem;
import com.team1.ecommerce.shared.model.Transaction;
import com.team1.ecommerce.server.repository.CartRepository;
import com.team1.ecommerce.server.repository.TransactionRepository;

import java.util.Date;

public class PaymentService {

    private final ProductService productService;
    private final TransactionRepository transactionRepository;
    private final CartRepository cartRepository;

    /**
     * Constructs PaymentService with required dependencies.
     */
    public PaymentService(ProductService productService,
                          TransactionRepository transactionRepository,
                          CartRepository cartRepository) {

        this.productService = productService;
        this.transactionRepository = transactionRepository;
        this.cartRepository = cartRepository;
    }

    /**
     * Processes a checkout request.
     *
     * Steps:
     * 1. Validate cart
     * 2. Update stock for each item
     * 3. Create transaction record
     * 4. Clear user's cart
     *
     * @param userId the ID of the user performing checkout
     * @param cart   the user's current cart
     * @return Transaction object representing completed purchase
     * @throws Exception if cart is empty or stock update fails
     */
    public Transaction processPayment(String userId, Cart cart) throws Exception {

        // Validate cart before processing
        if (cart == null || cart.getItems().isEmpty()) {
            throw new Exception("Cart is empty.");
        }

        double totalAmount = 0;

        // Loop through cart items and update stock
        for (CartItem item : cart.getItems()) {

            // Attempt to deduct stock from product inventory
            boolean success = productService.updateStock(
                    item.getProductId(),
                    item.getQuantity()
            );

            // Abort entire payment if any item is unavailable
            if (!success) {
                throw new Exception(
                        "Out of stock for product: " + item.getProductId()
                );
            }

            // Accumulate total purchase amount
            totalAmount += item.getPrice() * item.getQuantity();
        }

        // Create transaction record after successful stock updates
        Transaction transaction = new Transaction();
        transaction.setUserId(userId);
        transaction.setDate(new Date());
        transaction.setAmount(totalAmount);

        // Persist transaction
        transactionRepository.save(transaction);

        // Clear cart after successful checkout
        cartRepository.clearCart(userId);

        return transaction;
    }
}