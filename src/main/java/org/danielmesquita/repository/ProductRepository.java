package org.danielmesquita.repository;

import static com.mongodb.client.model.Filters.eq;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import java.util.ArrayList;
import java.util.List;
import org.bson.Document;
import org.bson.types.ObjectId;
import org.danielmesquita.dbconfig.DB;
import org.danielmesquita.entities.Product;

public class ProductRepository {
  private final MongoCollection<Document> collection;
  private static final String COLLECTION_NAME = "products";
  public static final String NAME_FIELD = "name";
  public static final String PRICE_FIELD = "price";
  public static final String DESCRIPTION_FIELD = "description";
  public static final String IMAGE_URI_FIELD = "imageUri";
  public static final String ID_FIELD = "_id";

  public ProductRepository() {
    MongoDatabase database = DB.getConnection();
    this.collection = database.getCollection(COLLECTION_NAME);
  }

  public void insertProduct(Product product) {
    Document document =
        new Document(NAME_FIELD, product.getName())
            .append(PRICE_FIELD, product.getPrice())
            .append(DESCRIPTION_FIELD, product.getDescription())
            .append(IMAGE_URI_FIELD, product.getImageUri());
    collection.insertOne(document);
  }

  public Product findProductById(String id) {
    ObjectId objectId = new ObjectId(id);
    Document document = collection.find(eq(ID_FIELD, objectId)).first();
    if (document != null) {
      return new Product(
          document.getObjectId(ID_FIELD).toString(),
          document.getString(NAME_FIELD),
          document.getDouble(PRICE_FIELD),
          document.getString(DESCRIPTION_FIELD),
          document.getString(IMAGE_URI_FIELD));
    }
    return null;
  }

  public List<Product> findAllProducts() {
    List<Product> products = new ArrayList<>();
    for (Document doc : collection.find()) {
      products.add(
          new Product(
              doc.getObjectId(ID_FIELD).toString(),
              doc.getString(NAME_FIELD),
              doc.getDouble(PRICE_FIELD),
              doc.getString(DESCRIPTION_FIELD),
              doc.getString(IMAGE_URI_FIELD)));
    }
    return products;
  }

  public void updateProduct(Product product) {
    Document updatedDoc =
        new Document(NAME_FIELD, product.getName())
            .append(PRICE_FIELD, product.getPrice())
            .append(DESCRIPTION_FIELD, product.getDescription())
            .append(IMAGE_URI_FIELD, product.getImageUri());

    collection.updateOne(eq(ID_FIELD, product.getId()), new Document("$set", updatedDoc));
  }

  public void deleteProduct(String id) {
    collection.deleteOne(eq(ID_FIELD, id));
  }
}
