package com.teamviewer.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name = "orders")
public class OrderModel extends AbstractBaseModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "orders_id_seq")
    @SequenceGenerator(name = "orders_id_seq", sequenceName = "orders_id_seq", allocationSize = 1)
    @Column(name = "id")
    protected Long id;

    @Column(name = "notes")
    private String notes;

    @Column(name = "expected_delivery_date")
    protected Date expectedDeliveryDate;

    @Column(name = "actual_delivery_date")
    protected Date actualDeliveryDate;

    @OneToMany(mappedBy = "orderModel")
    private Set<OrderItemModel> orderItems;
}
