package by.nintendo.petstore.service;

import by.nintendo.petstore.exception.NoOrdersException;
import by.nintendo.petstore.exception.NoSuchOrderException;
import by.nintendo.petstore.exception.PetNotFoundException;
import by.nintendo.petstore.model.Order;
import by.nintendo.petstore.model.OrderStatus;
import by.nintendo.petstore.model.PetStatus;
import by.nintendo.petstore.storage.InMemoryOrderStorage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
public class OrderService {

    @Autowired
    private InMemoryOrderStorage inMemoryOrderStorage;

    @Autowired
    private PetService petService;

    @Autowired
    public OrderService() {
    }

    public ResponseEntity<Object> createOrder(Order order) {
        if (petService.getPetById(order.getPetId()) != null) {
            if (petService.getAllPetsByStatus().get(PetStatus.AVAILABLE) != null) {
                long count = petService.getAllPetsByStatus().get(PetStatus.AVAILABLE).stream().
                        filter(x -> x.getId() == order.getPetId()).
                        count();
                if (order.getQuantity() <= count) {
                    order.setStatus(OrderStatus.APPROVED);
                    petService.deletePetByOrder(order.getQuantity(), order.getPetId());
                    inMemoryOrderStorage.addOrder(order);
                    return new ResponseEntity<>("Order create", HttpStatus.CREATED);
                } else {
                    order.setStatus(OrderStatus.PLACED);
                    inMemoryOrderStorage.addOrder(order);
                    return new ResponseEntity<>("Order create with status Placed", HttpStatus.CREATED);
                }
            } else {
                return new ResponseEntity<>("Order no create", HttpStatus.ACCEPTED);
            }

        } else {
            throw new PetNotFoundException("Pet not found.");
        }
    }

    public List<Order> getAllOrder() {
        if (inMemoryOrderStorage.getAllOrders() != null) {
            return inMemoryOrderStorage.getAllOrders();
        } else {
            throw new NoOrdersException("No Orders.");
        }
    }

    public Order findOrderById(long id) {

        for (Order order : inMemoryOrderStorage.getAllOrders()) {
            if (order.getId() == id) {
                return order;
            }
        }
        throw new NoSuchOrderException("There is no such order.");
    }

    public void deleateOrder(long id) {
        inMemoryOrderStorage.deleteOrder(findOrderById(id));
    }

    public Map<PetStatus, Long> getStatusList() {
        Map<PetStatus, Long> statusList = new HashMap<>();
        for (PetStatus petStatus : PetStatus.values()) {
            long count = petService.petStorage.getAllPets().stream().filter(x -> x.getStatus().equals(petStatus)).count();
            statusList.put(petStatus, count);
        }
        return statusList;
    }

}
