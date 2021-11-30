package Werehouse;

import java.util.ArrayList;
import lombok.Getter;

public class Werehouse {

  @Getter
  private ArrayList<Section> sections;
  @Getter
  private ArrayList<Products> products;

  public Werehouse() {
    sections = new ArrayList<>();
    products = new ArrayList<>();
  }

  public void addSection(Section section) throws Exception {
    if (!sections.contains(section)) {
      sections.add(section);
    } else {
      throw new Exception("Section already exists");
    }
  }

  public void addSection(String name, int type) throws Exception {
    Section section = new Section(name, type);
    if (!sections.contains(section)) sections.add(section);
    else {
      throw new Exception("Section already exists");
    }
  }

  public Section getSection(String name) {
    for (Section section : sections) {
      if (section.getName().equals(name)) return section;
    }
    return null;
  }

  public Section getSection(int index) throws Exception {
    if (index < 0 || index >= sections.size()) {
      throw new Exception("Index out of bounds");
    } else {
      return sections.get(index);
    }
  }

  public void addProduct(Products product) throws Exception {
    if (!this.products.contains(product)) {
      this.products.add(product);
    } else {
      throw new Exception("Product already exists");
    }
  }

  public void addProduct(String section, String name, int price) throws Exception {
    Section sect = getSection(section);
    Products product = new Product(sect, name, price);
    if (!products.contains(product)) {
      products.add(product);
    } else {
      throw new Exception("Product already exists");
    }
  }

  public void addProduct(Section section, String name, int price) throws Exception {
    Products product = new Product(section, name, price);
    if (!products.contains(product)) {
      products.add(product);
    } else {
      throw new Exception("Product already exists");
    }
  }

  public Product getProduct(String name) {
    for (Product product : products) {
      if (product.getName().equals(name)) {
        return product;
      }
    }
    return null;
  }

  public Product getProduct(int index) throws Exception {
    if (index < 0 || index >= products.size()) {
      throw new Exception("Index out of bounds");
    } else {
      return products.get(index);
    }
  }

  public ArrayList<Products> getProducts(String sectionName) {
    ArrayList<Product> productsList = new ArrayList<>();
    for (Product product : products) {
      if (product.getSection().getName().equals(sectionName)) {
        productsList.add(product);
      }
    }
    return productsList;
  }

  public void deleteSection(String name) {
    products.removeIf((Products pr) -> {
        if (pr.getSection().getName().equals(name)) {
          return true;
        } else {
          return false;
        }
      });

    sections.removeIf((Section section) -> {
        if (section.getName().equals(name)) {
          return true;
        } else {
          return false;
        }
      });
  }

  public void deleteProduct(String name) {
    Product pr = getProduct(name);
    if (pr != null) {
      products.remove(pr);
    }
  }

}
