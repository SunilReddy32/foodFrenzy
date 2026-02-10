package com.foodFrenzy.controller;

import com.foodFrenzy.dto.OrderRequest;
import com.foodFrenzy.dto.OrderStatusRequest;
import com.foodFrenzy.entity.Order;
import com.foodFrenzy.service.OrderService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class OrderController {
     private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    // USER: place order
    @PostMapping
    @PreAuthorize("hasRole('USER')")
    public Order placeOrder(@RequestBody OrderRequest request,
                            Principal principal) {
        return orderService.placeOrder(principal.getName(), request);
    }

    // USER: view own orders
    @GetMapping("/my")
    @PreAuthorize("hasRole('USER')")
    public List<Order> getMyOrders(Principal principal) {
        return orderService.getMyOrders(principal.getName());
    }
      // ADMIN: view all orders
    @GetMapping("/all")
    @PreAuthorize("hasRole('ADMIN')")
    public List<Order> getAllOrders() {
        return orderService.getAllOrders();
    }

    // ADMIN: update order status
    @PutMapping("/{id}/status")
    @PreAuthorize("hasRole('ADMIN')")
    public Order updateOrderStatus(@PathVariable Long id,
                                   @RequestBody OrderStatusRequest request) {
        return orderService.updateOrderStatus(id, request.getStatus());
    }
}
