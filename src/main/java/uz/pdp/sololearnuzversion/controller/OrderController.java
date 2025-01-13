package uz.pdp.sololearnuzversion.controller;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.sololearnuzversion.entity.Order;
import uz.pdp.sololearnuzversion.service.OrderService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/orders")
public class OrderController {
        private final OrderService orderService;

        public OrderController(OrderService orderService) {
            this.orderService = orderService;
        }

        // Create
        @PostMapping
        public ResponseEntity<Order> createOrder(@RequestBody Order order) {
            return ResponseEntity.ok(orderService.createOrder(order));
        }

        // Read all
        @GetMapping
        public ResponseEntity<List<Order>> getAllOrders() {
            return ResponseEntity.ok(orderService.getAllOrders());
        }

        // Read by ID
        @GetMapping("/{id}")
        public ResponseEntity<Order> getOrderById(@PathVariable Long id) {
            return ResponseEntity.ok(orderService.getOrderById(id));
        }

        // Read by User ID
        @GetMapping("/user/{userId}")
        public ResponseEntity<Optional<Order>> getOrdersByUserId(@PathVariable Long userId) {
            return ResponseEntity.ok(orderService.getOrdersByUserId(userId));
        }

        // Update
        @PutMapping("/{id}")
        public ResponseEntity<Order> updateOrder(
                @PathVariable Long id,
                @RequestBody Order updatedOrder) {
            return ResponseEntity.ok(orderService.updateOrder(id, updatedOrder));
        }

        // Delete
        @DeleteMapping("/{id}")
        public ResponseEntity<Void> deleteOrder(@PathVariable Long id) {
            orderService.deleteOrder(id);
            return ResponseEntity.noContent().build();
        }
    }
