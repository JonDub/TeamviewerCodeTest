package com.teamviewer.service;

import com.teamviewer.models.OrderItemModel;
import com.teamviewer.repositories.OrderItemsRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;
import java.util.Optional;

@Service
public class OrderItemsService implements CrudService<OrderItemModel> {

    @Autowired
    private OrderItemsRepository orderItemsRepository;

    @Override
    public List<OrderItemModel> getAll() {
        return orderItemsRepository.findAll();
    }

    @Override
    public Optional<OrderItemModel> get(long id) {
        return orderItemsRepository.findById(id);
    }

    @Override
    public OrderItemModel create(OrderItemModel object) {
        validate(object);

        object.setId(null);
        object.setCreatedDate(new java.sql.Date(System.currentTimeMillis()));
        object.setCreatedBy("TODO");
        object.setModifiedDate(null);
        object.setModifiedBy(null);
        return orderItemsRepository.save(object);
    }

    @Override
    public OrderItemModel update(OrderItemModel object) {
        validate(object);
        exists(object.getId());

        object.setModifiedDate(new java.sql.Date(System.currentTimeMillis()));
        object.setModifiedBy("TODO");
        return orderItemsRepository.save(object);
    }

    @Override
    public void delete(long id) {
        OrderItemModel model = exists(id);
        orderItemsRepository.delete(model);
    }

    public List<OrderItemModel> getAllByOrderId(long id) {
        return orderItemsRepository.findByOrderId(id);
    }

    public void deleteByOrderId(long id) {
        orderItemsRepository.deleteByOrderId(id);
    }

    public OrderItemModel exists(long id) {
        return get(id).orElseThrow(() -> new EntityNotFoundException("Order Item does not exist"));
    }

    /**
     * Ensure we are not inserting invalid data and specific business logic
     *
     * @param model
     */
    private void validate(OrderItemModel model) {
        Assert.notNull(model, "Order Item is empty");
    }
}
