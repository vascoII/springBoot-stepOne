package rubusdata.location.app.http.controller.action.order;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.*;
import rubusdata.location.domain.assembler.OrderModelAssembler;
import rubusdata.location.domain.entity.Order;
import rubusdata.location.domain.repository.OrderRepository;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class ListOrderAction {

    private final OrderRepository orderRepository;
    private final OrderModelAssembler assembler;

    ListOrderAction(OrderRepository orderRepository, OrderModelAssembler assembler) {

        this.orderRepository = orderRepository;
        this.assembler = assembler;
    }

    @GetMapping("/orders")
    public CollectionModel<EntityModel<Order>> all() {

        List<EntityModel<Order>> orders = orderRepository.findAll().stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());

        return new CollectionModel<>(orders,
                linkTo(methodOn(rubusdata.location.app.http.controller.action.order.ListOrderAction.class).all()).withSelfRel());
    }
}
