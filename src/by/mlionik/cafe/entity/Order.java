package by.mlionik.cafe.entity;

import by.mlionik.cafe.entity.type.OrderState;
import by.mlionik.cafe.entity.type.PaymentType;

import java.util.ArrayList;
import java.util.List;

public class Order extends Entity{
    private int id;
    private int idUser;
    private String deliveryTime;
    private PaymentType paymentType;
    private double price;
    private OrderState state;
    private List<Dish> dishes = new ArrayList<>();

    public Order() {
    }

    public Order(int idUser, String deliveryTime, PaymentType paymentType, double price, OrderState state) {
        this.idUser = idUser;
        this.deliveryTime = deliveryTime;
        this.paymentType = paymentType;
        this.price = price;
        this.state = state;
    }

    public Order(int idUser, String deliveryTime, PaymentType paymentType, double price, OrderState state, List<Dish> dishes) {
        this.idUser = idUser;
        this.deliveryTime = deliveryTime;
        this.paymentType = paymentType;
        this.price = price;
        this.state = state;
        this.dishes = dishes;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public String getDeliveryTime() {
        return deliveryTime;
    }

    public void setDeliveryTime(String deliveryTime) {
        this.deliveryTime = deliveryTime;
    }

    public PaymentType getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(PaymentType paymentType) {
        this.paymentType = paymentType;
    }

    public OrderState getState() {
        return state;
    }

    public void setState(OrderState state) {
        this.state = state;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public boolean addToBasket(Dish dish){
        return dishes.add(dish);
    }

    public List<Dish> getDishes() {
        return dishes;
    }

    public void setDishes(List<Dish> dishes) {
        this.dishes = dishes;
    }
}
