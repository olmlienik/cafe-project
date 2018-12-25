package by.mlionik.cafe.entity;

import by.mlionik.cafe.entity.type.PaymentType;

import java.time.LocalDateTime;

public class Order {
    private int id;
    private int idClient;
    private LocalDateTime deliveryTime;
    private PaymentType paymentType;
    private int rate;

}
