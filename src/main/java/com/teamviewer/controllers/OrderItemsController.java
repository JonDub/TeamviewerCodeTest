package com.teamviewer.controllers;

import com.teamviewer.service.OrderItemsService;
import com.teamviewer.models.OrderItemModel;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/order-items")
public class OrderItemsController extends AbstractBaseController {

    @Autowired
    private OrderItemsService orderItemsService;

    @GetMapping("")
    @Operation(summary = "GetOrderItems", description = "Gets all order items", tags = "OrderItems")
    public List<OrderItemModel> getOrderItems() {
        return orderItemsService.getAll();
    }

    @GetMapping("/{id}")
    @Operation(summary = "GetOrderItem", description = "Gets an order item by id", tags = "OrderItems")
    public OrderItemModel getOrderItem(@PathVariable long id) {
        return orderItemsService.exists(id);
    }

    @PostMapping("")
    @Operation(summary = "CreateOrderItems", description = "Creates an order item", tags = "OrderItems")
    public OrderItemModel createOrderItem(@RequestBody OrderItemModel orderItemModel) {
        return orderItemsService.create(orderItemModel);
    }

    @PutMapping("/{id}")
    @Operation(summary = "UpdateOrderItem", description = "Updates an order item", tags = "OrderItems")
    public OrderItemModel updateOrderItem(@PathVariable long id, @RequestBody OrderItemModel orderItemModel) {
        orderItemsService.exists(id);
        orderItemModel.setId(id);
        return orderItemsService.update(orderItemModel);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "DeleteOrderItem", description = "Deletes an order item by id", tags = "OrderItems")
    public void deleteOrderItem(@PathVariable long id) {
        orderItemsService.delete(id);
    }
}
