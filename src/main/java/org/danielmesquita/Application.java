package org.danielmesquita;

import org.danielmesquita.dbconfig.DB;
import org.danielmesquita.entities.Product;
import org.danielmesquita.repository.ProductRepository;

public class Application {
  public static void main(String[] args) {
    ProductRepository repository = new ProductRepository();

    Product product = new Product(null, "Celular", 3000.00, "Iphone 13", "iphone.jpg");
    repository.insertProduct(product);
    System.out.println("Product inserted successfully");

    Product foundProduct = repository.findProductById("66d902926170417768c84cfd");
    System.out.println("Product found: " + foundProduct.getName());

    foundProduct.setPrice(2300.00);
    repository.updateProduct(foundProduct);
    System.out.println("Product price updated to: " + foundProduct.getPrice());

    repository.deleteProduct(foundProduct.getId());
    System.out.println("Product deleted");

    DB.closeConnection();
  }
}
