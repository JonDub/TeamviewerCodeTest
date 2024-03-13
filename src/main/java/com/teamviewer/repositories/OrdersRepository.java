package com.teamviewer.repositories;

import com.teamviewer.models.OrderModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrdersRepository extends JpaRepository<OrderModel, Long> {

}
