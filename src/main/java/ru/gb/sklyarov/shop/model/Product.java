package ru.gb.sklyarov.shop.model;

public class Product {
    private Long id;
    private String title;
    private double cost;

    public Product(Long id, String title, double cost) {
        this.id = id;
        this.title = title;
        this.cost = cost;
    }

    public Product(String title, double cost) {
        this.title = title;
        this.cost = cost;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", cost=" + cost +
                '}';
    }
}
