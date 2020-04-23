package rubusdata.location.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import rubusdata.location.domain.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

}