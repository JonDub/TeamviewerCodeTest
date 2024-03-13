package com.teamviewer.repositories;

import com.teamviewer.models.OrderItemModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderItemsRepository extends JpaRepository<OrderItemModel, Long> {

    List<OrderItemModel> findByOrderId(long orderId);

    void deleteByOrderId(long orderId);
}
