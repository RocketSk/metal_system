package com.system.service;

import com.system.model.Company;

import java.util.List;

public interface CompanyService {

    void update(Company company);

    List<Company> getAll();

}
