package com.teamviewer.service;

import com.teamviewer.models.OrderModel;
import com.teamviewer.repositories.OrdersRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;
import java.util.Optional;

@Service
public class OrdersService implements CrudService<OrderModel> {

    @Autowired
    private OrdersRepository ordersRepository;
    @Autowired
    private OrderItemsService orderItemsService;

    @Override
    public List<OrderModel> getAll() {
        return ordersRepository.findAll();
    }

    @Override
    public Optional<OrderModel> get(long id) {
        return ordersRepository.findById(id);
    }

    @Override
    public OrderModel create(OrderModel object) {
        validate(object);

        object.setId(null);
        object.setCreatedDate(new java.sql.Date(System.currentTimeMillis()));
        object.setCreatedBy("TODO");
        object.setModifiedDate(null);
        object.setModifiedBy(null);
        return ordersRepository.save(object);
    }

    @Override
    @Transactional
    public OrderModel update(OrderModel object) {
        validate(object);
        exists(object.getId());

        // remove old references
        orderItemsService.deleteByOrderId(object.getId());
        // add new references
        if (object.getOrderItems() != null) {
            object.getOrderItems().forEach(item -> {
                item.setOrderId(object.getId());
                orderItemsService.create(item);
            });
        }

        object.setModifiedDate(new java.sql.Date(System.currentTimeMillis()));
        object.setModifiedBy("TODO");
        return ordersRepository.save(object);
    }

    @Override
    public void delete(long id) {
        OrderModel model = exists(id);
        ordersRepository.delete(model);
    }

    public OrderModel exists(long id) {
        return get(id).orElseThrow(() -> new EntityNotFoundException("Order does not exist"));
    }

    /**
     * Ensure we are not inserting invalid data and specific business logic
     *
     * @param model
     */
    private void validate(OrderModel model) {
        Assert.notNull(model, "Order is empty");
    }
}
