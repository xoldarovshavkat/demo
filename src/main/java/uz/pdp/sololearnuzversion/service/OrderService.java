package uz.pdp.sololearnuzversion.service;
import org.springframework.stereotype.Service;
import uz.pdp.sololearnuzversion.entity.Order;
import uz.pdp.sololearnuzversion.repository.OrderRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class OrderService {


    private final OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    // Create
    public Order createOrder(Order order) {
        return orderRepository.save(order);
    }

    // Read all
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    // Read by ID
    public Order getOrderById(Long id) {
        return orderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Order not found!"));
    }

    // Read by User ID
    public Optional<Order> getOrdersByUserId(Long userId) {
        return orderRepository.findById(userId);
    }

    // Update
    public Order updateOrder(Long id, Order updatedOrder) {
        Order order = getOrderById(id);
        order.setStatus(updatedOrder.getStatus());
        order.setTotalPrice(updatedOrder.getTotalPrice());
        order.setUpdatedAt(LocalDateTime.now());
        return orderRepository.save(order);
    }

    // Delete
    public void deleteOrder(Long id) {
        orderRepository.deleteById(id);
    }
}
