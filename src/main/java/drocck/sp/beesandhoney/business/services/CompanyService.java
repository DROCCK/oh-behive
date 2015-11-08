package drocck.sp.beesandhoney.business.services;

import drocck.sp.beesandhoney.business.entities.Company;
import drocck.sp.beesandhoney.business.entities.repositories.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by Chai
 * on 10/10/2015.
 */

public class CompanyService {

    @Autowired
    private CompanyRepository companyRepository;

    public List<Company> findAll() {
        return companyRepository.findAll();
    }

    public Company findById(Long id) {
        return companyRepository.findById(id);
    }

    public void delete(Long id){
        companyRepository.delete(id);
    }

    public Company save(final Company company) {
        return companyRepository.save(company);
    }

    public Company update(Company company) {
        Company c = companyRepository.findById(company.getId());
        c.setName(company.getName());
        return companyRepository.save(c);
    }
}