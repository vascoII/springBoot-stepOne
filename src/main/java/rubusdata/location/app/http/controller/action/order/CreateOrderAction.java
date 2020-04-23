package rubusdata.location.app.http.controller.action.order;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rubusdata.location.domain.assembler.OrderModelAssembler;
import rubusdata.location.domain.entity.Order;
import rubusdata.location.domain.entity.Status;
import rubusdata.location.domain.repository.OrderRepository;

@RestController
public class CreateOrderAction {

    private final OrderRepository orderRepository;
    private final OrderModelAssembler assembler;

    CreateOrderAction(OrderRepository orderRepository, OrderModelAssembler assembler) {
        this.orderRepository = orderRepository;
        this.assembler = assembler;
    }

    @PostMapping("/orders")
    ResponseEntity<EntityModel<Order>> newOrder(@RequestBody Order order) {

        order.setStatus(Status.IN_PROGRESS);
        Order newOrder = orderRepository.save(order);

        return ResponseEntity
                .created(linkTo(methodOn(rubusdata.location.app.http.controller.action.order.ReadOrderAction.class).one(newOrder.getId())).toUri())
                .body(assembler.toModel(newOrder));
    }
}
