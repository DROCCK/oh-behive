package drocck.sp.beesandhoney.business.entities.repositories;

import drocck.sp.beesandhoney.business.entities.Person;
import drocck.sp.beesandhoney.business.entities.User;
import drocck.sp.beesandhoney.business.entities.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Kyle on 10/9/2015.
 */
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    List<Employee> findAll();
    Employee findById(Long id);
    Employee findByPerson(Person person);
    Employee findByUser(User user);
    Employee save(Employee employee);
    void delete(Long id);
    void delete(Employee employee);
}
