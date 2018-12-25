package by.mlionik.cafe.entity;

import by.mlionik.cafe.entity.type.DishType;

import java.math.BigDecimal;

public class Dish extends Entity{
    private int id;
    private String name;
    private BigDecimal cost;
    private String picture;
    private DishType category;
    private boolean isDeleted;

    public Dish(){

    }

    public Dish(int id, String name, BigDecimal cost, String picture, DishType category) {
        this.id = id;
        this.name = name;
        this.cost = cost;
        this.picture = picture;
        this.category = category;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getCost() {
        return cost;
    }

    public void setCost(BigDecimal cost) {
        this.cost = cost;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public DishType getCategory() {
        return category;
    }

    public void setCategory(DishType category) {
        this.category = category;
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(boolean isDeleted) {
        this.isDeleted = isDeleted;
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
