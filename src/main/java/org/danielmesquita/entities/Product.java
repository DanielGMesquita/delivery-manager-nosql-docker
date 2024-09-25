package org.danielmesquita.entities;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Document(collection = "Product")
public class Product {
  @Id
  private String id;

  @NotNull(message = "Product name is required")
  private String name;

  @NotNull(message = "Product price is required")
  private Double price;

  @NotNull(message = "Product description is required")
  @Size(min = 10, message = "Product description requires 10 characters at least")
  private String description;

  private String imageUri;

  public Product() {}

  public Product(String id, String name, Double price, String description, String imageUri) {
    this.id = id;
    this.name = name;
    this.price = price;
    this.description = description;
    this.imageUri = imageUri;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Double getPrice() {
    return price;
  }

  public void setPrice(Double price) {
    this.price = price;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public String getImageUri() {
    return imageUri;
  }

  public void setImageUri(String imageUri) {
    this.imageUri = imageUri;
  }

  public boolean validateId(String id) {
    return id != null && !id.isEmpty();
  }
}
