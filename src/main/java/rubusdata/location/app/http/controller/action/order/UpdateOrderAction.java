package rubusdata.location.app.http.controller.action.order;

import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.mediatype.vnderrors.VndErrors;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rubusdata.location.domain.assembler.OrderModelAssembler;
import rubusdata.location.domain.entity.Order;
import rubusdata.location.domain.entity.Status;
import rubusdata.location.domain.exception.OrderNotFoundException;
import rubusdata.location.domain.repository.OrderRepository;

@RestController
public class UpdateOrderAction {

    private final OrderRepository orderRepository;
    private final OrderModelAssembler assembler;

    UpdateOrderAction(OrderRepository orderRepository, OrderModelAssembler assembler) {

        this.orderRepository = orderRepository;
        this.assembler = assembler;
    }

    @PutMapping("/orders/{id}/complete")
    public ResponseEntity<RepresentationModel> complete(@PathVariable Long id) {

        Order order = orderRepository.findById(id).orElseThrow(() -> new OrderNotFoundException(id));

        if (order.getStatus() == Status.IN_PROGRESS) {
            order.setStatus(Status.COMPLETED);
            return ResponseEntity.ok(assembler.toModel(orderRepository.save(order)));
        }

        return ResponseEntity
                .status(HttpStatus.METHOD_NOT_ALLOWED)
                .body(new VndErrors.VndError("Method not allowed", "You can't complete an order that is in the " + order.getStatus() + " status"));
    }
}
