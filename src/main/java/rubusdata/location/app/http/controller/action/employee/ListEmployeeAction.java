package rubusdata.location.app.http.controller.action.employee;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import rubusdata.location.domain.assembler.EmployeeModelAssembler;
import rubusdata.location.domain.entity.Employee;
import rubusdata.location.domain.repository.EmployeeRepository;

@RestController
public class ListEmployeeAction {

    private final EmployeeRepository repository;

    private final EmployeeModelAssembler assembler;

    ListEmployeeAction(EmployeeRepository repository, EmployeeModelAssembler assembler) {
        this.repository = repository;
        this.assembler = assembler;
    }

    // Aggregate root
    @GetMapping("/employees")
    public CollectionModel<EntityModel<Employee>> all() {
        List<EntityModel<Employee>> employees = repository.findAll().stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());

        return new CollectionModel<>(employees,
                linkTo(methodOn(ListEmployeeAction.class).all()).withSelfRel());
    }
}
