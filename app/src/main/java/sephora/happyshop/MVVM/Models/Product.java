package sephora.happyshop.mvvm.Models;

/**
 * Created by fadel on 23/8/17.
 */

public class Product {

    private Integer id;
    private String name;
    private String category;
    private Double price;
    private String img_url;
    private String description;
    private Boolean underSale;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getImgUrl() {
        return img_url;
    }

    public void setImgUrl(String imgUrl) {
        this.img_url = imgUrl;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getUnderSale() {
        return underSale;
    }

    public void setUnderSale(Boolean underSale) {
        this.underSale = underSale;
    }

}
