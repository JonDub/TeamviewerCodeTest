package com.teamviewer;

import com.teamviewer.models.OrderItemModel;
import com.teamviewer.models.OrderModel;
import com.teamviewer.models.ProductModel;
import net.datafaker.Faker;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class TestDataUtil {

    public static List<ProductModel> mockProducts(int size) {
        Faker faker = new Faker();
        List<ProductModel> mockProducts = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            ProductModel product = new ProductModel();
            product.setId((long) i + 1);
            product.setName(faker.appliance().equipment());
            product.setDescription(faker.lorem().sentence());
            product.setManufacturer(faker.company().name());
            product.setCost((float) faker.number().randomDouble(2, 0, 100));
            mockProducts.add(product);
        }
        return mockProducts;
    }

    public static List<OrderModel> mockOrders(int size) {
        Faker faker = new Faker();
        List<OrderModel> mockOrders = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            OrderModel order = new OrderModel();
            order.setId((long) i + 1);
            order.setNotes(faker.lorem().sentence());
            order.setExpectedDeliveryDate(new java.sql.Date(faker.date().past(21, TimeUnit.DAYS).getTime()));
            order.setOrderItems(new HashSet<>());
            boolean completed = faker.random().nextBoolean();
            if (completed)
                order.setActualDeliveryDate(new java.sql.Date(faker.date().future(1, TimeUnit.DAYS).getTime()));
            mockOrders.add(order);
        }
        return mockOrders;
    }

    public static List<OrderItemModel> mockOrderItems(int size) {
        Faker faker = new Faker();
        List<OrderItemModel> mockOrderItems = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            OrderItemModel orderItem = new OrderItemModel();
            orderItem.setId((long) i + 1);
            orderItem.setOrderId(faker.random().nextLong(1, 100));
            orderItem.setProductId(faker.random().nextLong(1, 100));
            orderItem.setQuantity(faker.random().nextLong(1, 1000));
            mockOrderItems.add(orderItem);
        }
        return mockOrderItems;
    }
}
