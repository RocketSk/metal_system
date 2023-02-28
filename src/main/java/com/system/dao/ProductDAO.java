package com.system.dao;

import com.system.model.Product;
import com.system.model.ProductGroup;

import java.util.List;

public interface ProductDAO {

    Product getById(Integer id);

    void addProduct(Product product);

    List<Product> getAll();

    List<Product> getAllByGroup(ProductGroup group);

    void delete(Product product);

    Product getByArticle(String article);

    void update(Product product);

}
