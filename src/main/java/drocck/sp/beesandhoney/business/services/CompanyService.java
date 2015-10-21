package drocck.sp.beesandhoney.business.services;

import drocck.sp.beesandhoney.business.entities.Company;
import drocck.sp.beesandhoney.business.entities.Person;
import drocck.sp.beesandhoney.business.entities.repositories.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;

/**
 * Created by Chai on 10/10/2015.
 */

public class CompanyService {
    @Autowired
    private CompanyRepository companyRepository;
    @Autowired

    public CompanyService() {
        super();
    }

    public List<Company> findAll() {
        return this.companyRepository.findAll();
    }

    public Company findById(Long id) {
        Company company = companyRepository.findById(id);
        return company;
    }

    public void delete(Long id){
        companyRepository.delete(id);
    }

    public Company save(final Company company) {
        return this.companyRepository.save(company);
    }

    public Company update(Company company) {
        Company c = companyRepository.findById(company.getId());
        c.setName(company.getName());
        return companyRepository.save(c);
    }
}