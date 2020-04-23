package rubusdata.location.app.http.controller.action.employee;

import java.net.URISyntaxException;

import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.hateoas.EntityModel;
import rubusdata.location.domain.assembler.EmployeeModelAssembler;
import rubusdata.location.domain.entity.Employee;
import rubusdata.location.domain.repository.EmployeeRepository;

@RestController
class CreateEmployeeAction {

    private final EmployeeRepository repository;

    private final EmployeeModelAssembler assembler;

    CreateEmployeeAction(EmployeeRepository repository, EmployeeModelAssembler assembler) {
        this.repository = repository;
        this.assembler = assembler;
    }

    @PostMapping("/employees")
    ResponseEntity<?> newEmployee(@RequestBody Employee newEmployee) throws URISyntaxException {
        EntityModel<Employee> entityModel = assembler.toModel(repository.save(newEmployee));

        return ResponseEntity
                .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
                .body(entityModel);
    }
}