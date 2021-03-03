package by.nintendo.petstore.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Order {

    private  long id;
    private long petId;
    private int quantity;
    private LocalDateTime shipDate;
    @Valid
    private OrderStatus status;
    private boolean complete;
}
