package com.teamviewer.controllers;

import com.teamviewer.service.OrderItemsService;
import com.teamviewer.TestDataUtil;
import com.teamviewer.models.OrderItemModel;
import com.teamviewer.repositories.OrderItemsRepository;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
class OrderItemsControllerTest {

    @Autowired
    private OrderItemsController orderItemsController;

    @Autowired
    @InjectMocks
    private OrderItemsService orderItemsService;

    @Mock
    private OrderItemsRepository orderItemsRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void getOrderItems() {
        List<OrderItemModel> mockOrderItems = TestDataUtil.mockOrderItems(10);
        when(orderItemsRepository.findAll()).thenReturn(mockOrderItems);

        List<OrderItemModel> orderItems = orderItemsController.getOrderItems();
        Assert.notNull(orderItems, "Order Items are empty");
        Assert.notEmpty(orderItems, "Products are empty");
    }


    @Test
    void getOrderItem() {
        // Can't get order item that does not exist
        assertThrows(EntityNotFoundException.class, () -> {
            orderItemsController.getOrderItem(9999L);
        });

        List<OrderItemModel> mockOrderItems = TestDataUtil.mockOrderItems(1);
        OrderItemModel mockOrderItem = mockOrderItems.get(0);
        when(orderItemsRepository.findById(1L)).thenReturn(Optional.ofNullable(mockOrderItem));

        // Get order item
        OrderItemModel orderItem = orderItemsController.getOrderItem(1);
        Assert.notNull(orderItem, "Order Item is empty");
        Assert.isTrue(mockOrderItem.equals(orderItem), "Order Items do not match");
    }

    @Test
    void createOrderItem() {
        List<OrderItemModel> mockOrderItems = TestDataUtil.mockOrderItems(1);
        OrderItemModel mockOrderItem = mockOrderItems.get(0);
        when(orderItemsRepository.save(mockOrderItem)).thenReturn(mockOrderItem);

        orderItemsController.createOrderItem(mockOrderItem);
    }

    @Test
    void updateOrderItem() {
        List<OrderItemModel> mockOrderItems = TestDataUtil.mockOrderItems(1);
        OrderItemModel mockOrderItem = mockOrderItems.get(0);
        when(orderItemsRepository.findById(1L)).thenReturn(Optional.ofNullable(mockOrderItem));
        when(orderItemsRepository.findById(9999L)).thenReturn(Optional.empty());
        when(orderItemsRepository.save(mockOrderItem)).thenReturn(mockOrderItem);

        // Can't update order item that does not exist
        assertThrows(EntityNotFoundException.class, () -> {
            orderItemsController.updateOrderItem(9999L, mockOrderItem);
        });

        // Update ok
        OrderItemModel orderItem = orderItemsController.updateOrderItem(1L, mockOrderItem);
        Assert.notNull(mockOrderItem, "Order Item is empty");
        Assert.isTrue(mockOrderItem.equals(orderItem), "Order Items do not match");
    }

    @Test
    void deleteOrderItem() {
        // Can't delete order item that does not exist
        assertThrows(EntityNotFoundException.class, () -> {
            orderItemsController.deleteOrderItem(200000L);
        });
    }
}