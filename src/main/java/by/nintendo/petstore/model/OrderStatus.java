package by.nintendo.petstore.model;

import javax.validation.constraints.NotNull;

public enum OrderStatus {
    @NotNull
    PLACED,APPROVED,DELIVERED
}
