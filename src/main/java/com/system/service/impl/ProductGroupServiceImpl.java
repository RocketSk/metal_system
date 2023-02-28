package com.system.service.impl;

import com.system.dao.ProductGroupDAO;
import com.system.dao.impl.ProductGroupDAOImpl;
import com.system.model.ProductGroup;
import com.system.service.ProductGroupService;

import java.util.List;

public class ProductGroupServiceImpl implements ProductGroupService {

    private final ProductGroupDAO productGroupDAO = new ProductGroupDAOImpl();

    @Override
    public ProductGroup getById(Integer id) {
        return productGroupDAO.getById(id);
    }

    @Override
    public void addProductGroup(ProductGroup productGroup) {
        productGroupDAO.addProductGroup(productGroup);
    }

    @Override
    public List<ProductGroup> getAll() {
        return productGroupDAO.getAll();
    }

    @Override
    public void delete(ProductGroup productGroup) {
        productGroupDAO.delete(productGroup);
    }

    @Override
    public void update(ProductGroup productGroup) {
        productGroupDAO.update(productGroup);
    }
}
