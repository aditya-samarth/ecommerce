package com.project.ecommerce.service;

import com.project.ecommerce.model.Cart;
import com.project.ecommerce.model.Order;
import com.project.ecommerce.model.Product;
import com.project.ecommerce.model.User;
import com.project.ecommerce.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    public Order placeOrder(User user, Cart cart) {
        Order order = new Order(user, cart.getProducts(), cart.getTotalPrice());
        orderRepository.save(order);
        return order;
    }

    public void cancelOrder(Long order) {
        orderRepository.delete(order);
    }

    public Order getOrderById(Long orderId) {
        return orderRepository.findById(orderId)
                .orElseThrow(() -> new NoSuchElementException("Order Not Found"));

    }

    public List<Order> getOrderByUserId(Long userId){
        return orderRepository.findByUserId(userId);
    }

    private BigDecimal calculateTotalPrice(List<Product> products) {
        BigDecimal totalPrice = BigDecimal.ZERO;
        for (Product product: products){
            totalPrice = totalPrice.add(product.getPrice());
        }
        return totalPrice;
    }
}
