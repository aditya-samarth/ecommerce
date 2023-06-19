package com.project.ecommerce.service;


import com.project.ecommerce.model.Cart;
import com.project.ecommerce.model.Product;
import com.project.ecommerce.repository.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.NoSuchElementException;

@Service
public class CartService {
    @Autowired
    private CartRepository cartRepository;

    public void addToCart(Cart cart, Product product) {
        cart.addProduct(product);
        calculateTotalPrice(cart);
        cartRepository.save(cart);

    }

    public void removeFromCart(Cart cart, Product product){
        cart.removeProduct(product);
        calculateTotalPrice(cart);
        cartRepository.save(cart);

    }

    public void updateCartItemQuantity(Cart cart, Product product, int quantity){
        calculateTotalPrice(cart);
        cartRepository.save(cart);

    }

    public Cart getCartByUserId(Long userId) {
        return cartRepository.findByUserId(userId)
                .orElseThrow(() -> new NoSuchElementException("Cart Not Found"));

    }

    public void calculateTotalPrice(Cart cart) {
        BigDecimal totalPrice = BigDecimal.ZERO;
        for (Product product : cart.getProducts()) {
            totalPrice = totalPrice.add(product.getPrice());
        }
        cart.setTotalPrice(totalPrice);
    }
}
