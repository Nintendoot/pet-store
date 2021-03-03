package by.nintendo.petstore.resource;

import by.nintendo.petstore.model.Order;
import by.nintendo.petstore.model.PetStatus;
import by.nintendo.petstore.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(path = "/store")
public class StoreResourse {

    @Autowired
    OrderService orderService;

    @PostMapping(path = "/order")
    public ResponseEntity<Object> createOrderPet(@Valid @RequestBody Order order) {
        return orderService.createOrder(order);
    }

    @GetMapping(path = "/all")
    public ResponseEntity<List<Order>> allOrders() {
        List<Order> orders = orderService.getAllOrder();
        return new ResponseEntity<>(orders, HttpStatus.OK);
    }

    @GetMapping(path = "/order/{id}")
    public ResponseEntity<Order> getOrderById(@PathVariable("id") long id) {
        Order order = orderService.findOrderById(id);
        return new ResponseEntity<>(order, HttpStatus.OK);
    }

    @DeleteMapping(path = "/order/{id}")
    public ResponseEntity<Object> deleateOrderById(@PathVariable("id") long id) {
        orderService.deleateOrder(id);
        return new ResponseEntity<>("Order deleate.", HttpStatus.OK);
    }

    @GetMapping(path = "/inventory")
    public ResponseEntity<Map<PetStatus, Long>> getStatusList() {
        Map<PetStatus, Long> petStatusLongMap = orderService.getStatusList();
        return new ResponseEntity<>(petStatusLongMap, HttpStatus.OK);
    }


}
