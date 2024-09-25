package org.danielmesquita.controller;

import java.util.List;
import org.danielmesquita.entities.Product;
import org.danielmesquita.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/products")
public class ProductController {

  @Autowired private ProductService productService;

  // Endpoint para criar um novo produto
  @PostMapping
  public ResponseEntity<Product> createProduct(@RequestBody Product product) {
    productService.insertProduct(product);
    return new ResponseEntity<>(product, HttpStatus.CREATED);
  }

  // Endpoint para buscar todos os produtos
  @GetMapping
  public ResponseEntity<List<Product>> getAllProducts() {
    List<Product> products = productService.findAllProducts();
    return new ResponseEntity<>(products, HttpStatus.OK);
  }

  // Endpoint para buscar um produto pelo ID
  @GetMapping("/{id}")
  public ResponseEntity<Product> getProductById(@PathVariable String id) {
    Product product = productService.findProductById(id);
    return product != null
        ? new ResponseEntity<>(product, HttpStatus.OK)
        : new ResponseEntity<>(HttpStatus.NOT_FOUND);
  }

  // Endpoint para atualizar um produto
  @PutMapping("/{id}")
  public ResponseEntity<Product> updateProduct(
      @PathVariable String id, @RequestBody Product product) {
    product.setId(id); // Certifique-se de que o ID do produto seja o correto
    productService.updateProduct(product);
    return new ResponseEntity<>(product, HttpStatus.OK);
  }

  // Endpoint para deletar um produto
  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteProduct(@PathVariable String id) {
    productService.deleteProduct(id);
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }
}
