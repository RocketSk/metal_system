package com.system.dao;

import com.system.model.Company;

import java.util.List;

public interface CompanyDAO {

    void update(Company company);

    List<Company> getAll();

}
