package org.danielmesquita.repository;

import java.util.List;
import org.danielmesquita.entities.Product;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends MongoRepository<Product, String> {

  List<Product> findByName(String name);

  List<Product> findByPrice(Double price);

  List<Product> findByDescriptionContaining(String description);
}
