package by.nintendo.petstore.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Pet {

    private long id;
    @Valid
    private Category category;
    @NotBlank
    @Size(min = 5)
    private String name;
    @Valid
    private Tag[] tags;
    @NotNull
    @Valid
    private PetStatus status;

}
