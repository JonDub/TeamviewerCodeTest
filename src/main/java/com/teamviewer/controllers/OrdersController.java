package com.teamviewer.controllers;

import com.teamviewer.service.OrdersService;
import com.teamviewer.models.OrderModel;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class OrdersController extends AbstractBaseController {

    @Autowired
    private OrdersService ordersService;

    @GetMapping("")
    @Operation(summary = "GetOrders", description = "Gets all orders", tags = "Orders")
    public List<OrderModel> getOrders() {
        return ordersService.getAll();
    }

    @GetMapping("/{id}")
    @Operation(summary = "GetOrder", description = "Gets an order by id", tags = "Orders")
    public OrderModel getOrder(@PathVariable long id) {
        return ordersService.exists(id);
    }

    @PostMapping("")
    @Operation(summary = "CreateOrder", description = "Creates an order", tags = "Orders")
    public OrderModel createOrder(@RequestBody OrderModel orderModel) {
        return ordersService.create(orderModel);
    }

    @PutMapping("/{id}")
    @Operation(summary = "UpdateOrder", description = "Updates an order", tags = "Orders")
    public OrderModel updateOrder(@PathVariable long id, @RequestBody OrderModel orderModel) {
        ordersService.exists(id);
        orderModel.setId(id);
        return ordersService.update(orderModel);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "DeleteOrder", description = "Deletes an order by id", tags = "Orders")
    public void deleteOrder(@PathVariable long id) {
        ordersService.delete(id);
    }
}
