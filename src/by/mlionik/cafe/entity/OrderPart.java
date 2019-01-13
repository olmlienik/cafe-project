package by.mlionik.cafe.entity;

/**
 * The type Order part.
 */
public class OrderPart extends Entity {
    private int id;
    private int orderId;
    private int dishId;

    /**
     * Instantiates a new order part.
     */
    public OrderPart() {
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
     * Gets the order id.
     *
     * @return the order id
     */
    public int getOrderId() {
        return orderId;
    }

    /**
     * Sets the order id.
     *
     * @param orderId the new order id
     */
    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    /**
     * Gets the dish id.
     *
     * @return the dish id
     */
    public int getDishId() {
        return dishId;
    }

    /**
     * Sets the dish id.
     *
     * @param dishId the new dish id
     */
    public void setDishId(int dishId) {
        this.dishId = dishId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        OrderPart orderPart = (OrderPart) o;

        if (id != orderPart.id) return false;
        if (orderId != orderPart.orderId) return false;
        return dishId == orderPart.dishId;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + orderId;
        result = 31 * result + dishId;
        return result;
    }

    @Override
    public String toString() {
        return "OrderPart{" +
                "id=" + id +
                ", orderId=" + orderId +
                ", dishId=" + dishId +
                '}';
    }
}
