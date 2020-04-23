package rubusdata.location.app.http.controller.action.employee;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import rubusdata.location.domain.assembler.EmployeeModelAssembler;
import rubusdata.location.domain.repository.EmployeeRepository;

@RestController
class DeleteEmployeeAction {

    private final EmployeeRepository repository;

    private final EmployeeModelAssembler assembler;

    DeleteEmployeeAction(EmployeeRepository repository, EmployeeModelAssembler assembler) {
        this.repository = repository;
        this.assembler = assembler;
    }

    @DeleteMapping("/employees/{id}")
    ResponseEntity<?> deleteEmployee(@PathVariable Long id) {
        repository.deleteById(id);

        return ResponseEntity.noContent().build();
    }
}
