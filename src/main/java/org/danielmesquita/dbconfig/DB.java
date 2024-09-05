package org.danielmesquita.dbconfig;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class DB {
  private static MongoClient mongoClient;

  public static MongoDatabase getConnection() {
    Properties props = loadProperties();
    if (mongoClient == null) {
      String uri = props.getProperty("mongodb.uri");
      mongoClient = MongoClients.create(uri);
    }

    String databaseName = props.getProperty("mongodb.database");
    return mongoClient.getDatabase(databaseName);
  }

  public static void closeConnection() {
    if (mongoClient != null) {
      mongoClient.close();
    }
  }

  private static Properties loadProperties() {
    try (FileInputStream fs = new FileInputStream("src/main/resources/db.properties")) {
      Properties props = new Properties();
      props.load(fs);
      return props;
    } catch (IOException e) {
      throw new DbException(e.getMessage());
    }
  }
}
