package rubusdata.location.app.http.controller.action.employee;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.hateoas.EntityModel;
import rubusdata.location.domain.assembler.EmployeeModelAssembler;
import rubusdata.location.domain.entity.Employee;
import rubusdata.location.domain.exception.EmployeeNotFoundException;
import rubusdata.location.domain.repository.EmployeeRepository;

@RestController
public class ReadEmployeeAction {

    private final EmployeeRepository repository;

    private final EmployeeModelAssembler assembler;

    ReadEmployeeAction(EmployeeRepository repository, EmployeeModelAssembler assembler) {
        this.repository = repository;
        this.assembler = assembler;
    }

    // Single item
    @GetMapping("/employees/{id}")
    public EntityModel<Employee> one(@PathVariable Long id) {
        Employee employee = repository.findById(id).orElseThrow(() -> new EmployeeNotFoundException(id));

        return assembler.toModel(employee);
    }
}