package drocck.sp.beesandhoney.business.services;

import drocck.sp.beesandhoney.business.entities.Employee;
import drocck.sp.beesandhoney.business.entities.repositories.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by Kyle on 10/10/2015.
 */
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private PersonService personService;

    public List<Employee> findAll() {
        return employeeRepository.findAll();
    }

    public Employee findById(Long id) {
        return employeeRepository.findById(id);
    }

    public Employee save(Employee employee) {
        return employeeRepository.save(employee);
    }


    public Employee update(Employee employee) {
        Employee e = employeeRepository.findById(employee.getId());
        e.getPerson().setName(e.getPerson().getName());
        e.getPerson().setContactInfo(employee.getPerson().getContactInfo());
        e.getPerson().getContactInfo().setId(e.getId());
        e.getPerson().getContactInfo().setAddress(employee.getPerson().getContactInfo().getAddress());
        e.getPerson().getContactInfo().getAddress().setId(e.getId());
        return employeeRepository.save(e);


    }

    public void delete(Long id) {
        employeeRepository.delete(id);
    }

    public void delete(Employee employee) {
        employeeRepository.delete(employee);
    }
}
