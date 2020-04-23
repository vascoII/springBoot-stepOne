package rubusdata.location.domain.assembler;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Component;
import rubusdata.location.app.http.controller.action.order.DeleteOrderAction;
import rubusdata.location.app.http.controller.action.order.ReadOrderAction;
import rubusdata.location.app.http.controller.action.order.ListOrderAction;
import rubusdata.location.app.http.controller.action.order.UpdateOrderAction;
import rubusdata.location.domain.entity.Order;
import rubusdata.location.domain.entity.Status;

@Component
public class OrderModelAssembler implements RepresentationModelAssembler<Order, EntityModel<Order>> {

    @Override
    public EntityModel<Order> toModel(Order order) {

        // Unconditional links to single-item resource and aggregate root

        EntityModel<Order> orderModel = new EntityModel<>(order,
                WebMvcLinkBuilder.linkTo(methodOn(ReadOrderAction.class).one(order.getId())).withSelfRel(),
                linkTo(methodOn(ListOrderAction.class).all()).withRel("orders")
        );

        // Conditional links based on state of the order

        if (order.getStatus() == Status.IN_PROGRESS) {
            orderModel.add(
                    linkTo(methodOn(DeleteOrderAction.class)
                            .cancel(order.getId())).withRel("cancel"));
            orderModel.add(
                    linkTo(methodOn(UpdateOrderAction.class)
                            .complete(order.getId())).withRel("complete"));
        }

        return orderModel;
    }
}
