package rubusdata.location.domain.assembler;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;
import rubusdata.location.domain.entity.Employee;
import rubusdata.location.app.http.controller.action.employee.ReadEmployeeAction;
import rubusdata.location.app.http.controller.action.employee.ListEmployeeAction;

@Component
public
class EmployeeModelAssembler implements RepresentationModelAssembler<Employee, EntityModel<Employee>> {

    @Override
    public EntityModel<Employee> toModel(Employee employee) {

        return new EntityModel<>(employee,
                linkTo(methodOn(ReadEmployeeAction.class).one(employee.getId())).withSelfRel(),
                linkTo(methodOn(ListEmployeeAction.class).all()).withRel("employees"));
    }
}
