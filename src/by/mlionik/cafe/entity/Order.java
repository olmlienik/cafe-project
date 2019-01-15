package by.mlionik.cafe.entity;

import java.util.ArrayList;
import java.util.List;

/**
 * The type Order.
 */
public class Order extends Entity {
    private int id;
    private int idUser;
    private String deliveryTime;
    private PaymentType paymentType;
    private double price;
    private OrderState state;
    private List<Dish> dishes = new ArrayList<>();

    /**
     * Instantiates a new order.
     */
    public Order() {
    }

    /**
     * Instantiates a new order.
     *
     * @param idUser       the id user
     * @param deliveryTime the delivery time
     * @param paymentType  the payment type
     * @param price        the price
     * @param state        the state
     */
    public Order(int idUser, String deliveryTime, PaymentType paymentType, double price, OrderState state) {
        this.idUser = idUser;
        this.deliveryTime = deliveryTime;
        this.paymentType = paymentType;
        this.price = price;
        this.state = state;
    }

    /**
     * Instantiates a new order.
     *
     * @param idUser       the id user
     * @param deliveryTime the delivery time
     * @param paymentType  the payment type
     * @param price        the price
     * @param state        the state
     * @param dishes       the dishes
     */
    public Order(int idUser, String deliveryTime, PaymentType paymentType, double price, OrderState state, List<Dish> dishes) {
        this.idUser = idUser;
        this.deliveryTime = deliveryTime;
        this.paymentType = paymentType;
        this.price = price;
        this.state = state;
        this.dishes = dishes;
    }

    /**
     * Gets the id.
     *
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * Sets the id.
     *
     * @param id the new id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Gets the id user.
     *
     * @return the id user
     */
    public int getIdUser() {
        return idUser;
    }

    /**
     * Sets the id user.
     *
     * @param idUser the new id user
     */
    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    /**
     * Gets the delivery time.
     *
     * @return the delivery time
     */
    public String getDeliveryTime() {
        return deliveryTime;
    }

    /**
     * Sets the delivery time.
     *
     * @param deliveryTime the new delivery time
     */
    public void setDeliveryTime(String deliveryTime) {
        this.deliveryTime = deliveryTime;
    }

    /**
     * Gets the payment type.
     *
     * @return the payment type
     */
    public PaymentType getPaymentType() {
        return paymentType;
    }

    /**
     * Sets the payment type.
     *
     * @param paymentType the new payment type
     */
    public void setPaymentType(PaymentType paymentType) {
        this.paymentType = paymentType;
    }

    /**
     * Gets the state.
     *
     * @return the state
     */
    public OrderState getState() {
        return state;
    }

    /**
     * Sets the state.
     *
     * @param state the new state
     */
    public void setState(OrderState state) {
        this.state = state;
    }

    /**
     * Gets the price.
     *
     * @return the price
     */
    public double getPrice() {
        return price;
    }

    /**
     * Sets the price.
     *
     * @param price the new price
     */
    public void setPrice(double price) {
        this.price = price;
    }

    /**
     * Adds the to basket.
     *
     * @param dish the dish
     * @return true, if successful
     */
    public boolean addToBasket(Dish dish) {
        return dishes.add(dish);
    }

    /**
     * Gets the dishes.
     *
     * @return the dishes
     */
    public List<Dish> getDishes() {
        return dishes;
    }

    /**
     * Sets the dishes.
     *
     * @param dishes the new dishes
     */
    public void setDishes(List<Dish> dishes) {
        this.dishes = dishes;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Order order = (Order) o;

        if (id != order.id) return false;
        if (idUser != order.idUser) return false;
        if (Double.compare(order.price, price) != 0) return false;
        if (deliveryTime != null ? !deliveryTime.equals(order.deliveryTime) : order.deliveryTime != null) return false;
        if (paymentType != order.paymentType) return false;
        if (state != order.state) return false;
        return dishes != null ? dishes.equals(order.dishes) : order.dishes == null;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = id;
        result = 31 * result + idUser;
        result = 31 * result + (deliveryTime != null ? deliveryTime.hashCode() : 0);
        result = 31 * result + (paymentType != null ? paymentType.hashCode() : 0);
        temp = Double.doubleToLongBits(price);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (state != null ? state.hashCode() : 0);
        result = 31 * result + (dishes != null ? dishes.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", idUser=" + idUser +
                ", deliveryTime='" + deliveryTime + '\'' +
                ", paymentType=" + paymentType +
                ", price=" + price +
                ", state=" + state +
                ", dishes=" + dishes +
                '}';
    }
}
