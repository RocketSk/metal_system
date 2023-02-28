package com.system.service.impl;

import com.system.dao.ProductDAO;
import com.system.dao.impl.ProductDAOImpl;
import com.system.model.Product;
import com.system.model.ProductGroup;
import com.system.service.ProductService;

import java.util.List;

public class ProductServiceImpl implements ProductService {

    private final ProductDAO productDAO = new ProductDAOImpl();

    @Override
    public Product getById(Integer id) {
        return productDAO.getById(id);
    }

    @Override
    public Product getByArticle(String article) {
        return productDAO.getByArticle(article);
    }

    @Override
    public void addProduct(Product product) {
        productDAO.addProduct(product);
    }

    @Override
    public List<Product> getAll() {
        return productDAO.getAll();
    }

    @Override
    public List<Product> getAllByGroup(ProductGroup group) {
        return productDAO.getAllByGroup(group);
    }

    @Override
    public void delete(Product product) {
        productDAO.delete(product);
    }

    @Override
    public void update(Product product) {
        productDAO.update(product);
    }
}
