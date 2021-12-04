package Werehouse;

import java.io.Serializable;

public class Product implements Serializable {
    private int productID;
    private int sectionID;
    private String country;
    private String name;
    private int price;

    public Product() {
        productID = sectionID = price = -1;
        country = name = "";
    }

    public Product(int productID, String country, String name, int price, int sectionID) {
        this.productID = productID;
        this.sectionID = sectionID;
        this.country = country;
        this.name = name;
        this.price = price;
    }

    @Override
    public String toString(){
        return String.format("Student %s %s , id : %d, age : %d, group id : %d", country, name, productID, price, sectionID);
    }

    public int getSectionID() {
        return sectionID;
    }

    public void setSectionID(int sectionID) {
        this.sectionID = sectionID;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getProductID() {
        return productID;
    }

    public void setProductID(int productID) {
        this.productID = productID;
    }
}
