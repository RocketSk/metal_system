package com.system.service;

import com.system.model.Product;
import com.system.model.ProductGroup;

import java.util.List;

public interface ProductService {

    Product getById(Integer id);

    Product getByArticle(String article);

    void addProduct(Product product);

    List<Product> getAll();

    List<Product> getAllByGroup(ProductGroup group);

    void delete(Product product);

    void update(Product product);

}
