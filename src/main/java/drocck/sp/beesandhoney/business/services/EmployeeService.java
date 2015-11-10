package drocck.sp.beesandhoney.business.services;

import drocck.sp.beesandhoney.business.entities.Employee;
import drocck.sp.beesandhoney.business.entities.User;
import drocck.sp.beesandhoney.business.entities.repositories.EmployeeRepository;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by Kyle on 10/10/2015.
 */
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    public List<Employee> findAll() {
        return employeeRepository.findAll();
    }

    public Employee findOne(Long id) {
        return employeeRepository.findOne(id);
    }

    public Employee save(Employee employee) {
        return employeeRepository.save(employee);
    }

    public Employee update(Employee employee) {
        return employeeRepository.save(employee);
    }

    public void delete(Long id) { employeeRepository.delete(id); }

    public void delete(Employee employee) {
        employeeRepository.delete(employee);
    }
}
