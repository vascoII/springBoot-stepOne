package rubusdata.location.app.http.controller.action.order;

import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.*;
import rubusdata.location.domain.assembler.OrderModelAssembler;
import rubusdata.location.domain.entity.Order;
import rubusdata.location.domain.exception.OrderNotFoundException;
import rubusdata.location.domain.repository.OrderRepository;

@RestController
public class ReadOrderAction {

    private final OrderRepository orderRepository;
    private final OrderModelAssembler assembler;

    ReadOrderAction(OrderRepository orderRepository, OrderModelAssembler assembler) {
        this.orderRepository = orderRepository;
        this.assembler = assembler;
    }

    @GetMapping("/orders/{id}")
    public EntityModel<Order> one(@PathVariable Long id) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new OrderNotFoundException(id));

        return assembler.toModel(order);
    }
}
