package com.system.dao;

import com.system.model.ProductGroup;

import java.util.List;

public interface ProductGroupDAO {

    ProductGroup getById(Integer id);

    void addProductGroup(ProductGroup productGroup);

    List<ProductGroup> getAll();

    void delete(ProductGroup productGroup);

    void update(ProductGroup productGroup);
}
