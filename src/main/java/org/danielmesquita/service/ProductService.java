package org.danielmesquita.service;

import java.util.List;
import org.danielmesquita.entities.Product;
import org.danielmesquita.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

@Service
public class ProductService {
  @Autowired private ProductRepository productRepository;

  @Autowired private MongoTemplate mongoTemplate;

  public void insertProduct(Product product) {
    productRepository.save(product);
  }

  public Product findProductById(String id) {
    return productRepository.findById(id).orElse(null);
  }

  public List<Product> findAllProducts() {
    return productRepository.findAll();
  }

  public void updateProduct(Product product) {
    productRepository.save(product);
  }

  public void deleteProduct(String id) {
    productRepository.deleteById(id);
  }
}
