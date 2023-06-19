package com.project.ecommerce.controller;

import com.project.ecommerce.model.Cart;
import com.project.ecommerce.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/carts")
public class CartController {
    @Autowired
    private CartService cartService;

    @PostMapping("/{userId}/products/{productId}")
    public ResponseEntity<Cart> addToCart(@PathVariable Long userId, @PathVariable Long productId) {
        Cart updatedCart = cartService.addToCart(userId, productId);
        return ResponseEntity.ok(updatedCart);
    }

    @DeleteMapping("/{userId}/products/{productId}")
    public ResponseEntity<Void> removeFromCart(@PathVariable Long userId, @PathVariable Long productId) {
        cartService.removeFromCart(userId, productId);
        return ResponseEntity.noContent().build();
    }


    @PutMapping("/{userId}/products/{productId}")
    public ResponseEntity<Cart> updateCartItemQuantity(@PathVariable Long userId, @PathVariable Long productId, @RequestParam int quantity) {
        Cart updatedCart = cartService.updateCartItemQuantity(userId, productId, quantity);
        return ResponseEntity.ok(updatedCart);
    }

    @GetMapping("/userId")
    public ResponseEntity<Cart> getCartbyUserId(@PathVariable Long userId) {
        Cart cart = cartService.getCartByUserId(userId);
        return ResponseEntity.ok(cart);
    }

}
