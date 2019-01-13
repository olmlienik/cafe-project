package by.mlionik.cafe.entity;

import by.mlionik.cafe.entity.type.DishType;

/**
 * The type Dish.
 */
public class Dish extends Entity{
    private int id;
    private String name;
    private double cost;
    private String picture;
    private DishType category;
    private boolean isDeleted;

    /**
     * Instantiates a new dish.
     */
    public Dish(){
    }

    /**
     * Instantiates a new dish.
     *
     * @param id the id
     * @param name the name
     * @param cost the cost
     * @param picture the picture
     * @param category the category
     */
    public Dish(int id, String name, double cost, String picture, DishType category) {
        this.id = id;
        this.name = name;
        this.cost = cost;
        this.picture = picture;
        this.category = category;
    }

    /**
     * Instantiates a new dish.
     *
     * @param name the name
     * @param cost the cost
     * @param picture the picture
     * @param category the category
     */
    public Dish(String name, double cost, String picture, DishType category) {
        this.name = name;
        this.cost = cost;
        this.picture = picture;
        this.category = category;
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
     * Gets the name.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name.
     *
     * @param name the new name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the cost.
     *
     * @return the cost
     */
    public double getCost() {
        return cost;
    }

    /**
     * Sets the cost.
     *
     * @param cost the new cost
     */
    public void setCost(double cost) {
        this.cost = cost;
    }

    /**
     * Gets the picture.
     *
     * @return the picture
     */
    public String getPicture() {
        return picture;
    }

    /**
     * Sets the picture.
     *
     * @param picture the new picture
     */
    public void setPicture(String picture) {
        this.picture = picture;
    }

    /**
     * Gets the category.
     *
     * @return the category
     */
    public DishType getCategory() {
        return category;
    }

    /**
     * Sets the category.
     *
     * @param category the new category
     */
    public void setCategory(DishType category) {
        this.category = category;
    }

    /**
     * Checks if is deleted.
     *
     * @return true, if deleted
     */
    public boolean isDeleted() {
        return isDeleted;
    }

    /**
     * Sets the checks if is deleted.
     *
     * @param isDeleted the new checks if is deleted
     */
    public void setIsDeleted(boolean isDeleted) {
        this.isDeleted = isDeleted;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Dish dish = (Dish) o;

        if (id != dish.id) return false;
        if (Double.compare(dish.cost, cost) != 0) return false;
        if (isDeleted != dish.isDeleted) return false;
        if (name != null ? !name.equals(dish.name) : dish.name != null) return false;
        if (picture != null ? !picture.equals(dish.picture) : dish.picture != null) return false;
        return category == dish.category;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        temp = Double.doubleToLongBits(cost);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (picture != null ? picture.hashCode() : 0);
        result = 31 * result + (category != null ? category.hashCode() : 0);
        result = 31 * result + (isDeleted ? 1 : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Dish{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", cost=" + cost +
                ", picture='" + picture + '\'' +
                ", category=" + category +
                ", isDeleted=" + isDeleted +
                '}';
    }
}
