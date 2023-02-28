package com.system.service.impl;

import com.system.dao.CompanyDAO;
import com.system.dao.impl.CompanyDAOImpl;
import com.system.model.Company;
import com.system.service.CompanyService;

import java.util.List;

public class CompanyServiceImpl implements CompanyService {

    private final CompanyDAO companyDAO = new CompanyDAOImpl();

    @Override
    public void update(Company company) {
        companyDAO.update(company);
    }

    @Override
    public List<Company> getAll() {
        return companyDAO.getAll();
    }
}
