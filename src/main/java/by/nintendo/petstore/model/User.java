package by.nintendo.petstore.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {

    private long id;
    @Size(min = 3)
    @NotBlank
    private String name;
    @Size(min = 3)
    @NotBlank
    private String firstName;
    @Size(min = 3)
    @NotBlank
    private String lastName;
    @Size(min = 3)
    @NotBlank
    @Email
    private String email;
    @Size(min = 5)
    @NotBlank
    private String password;
    @Size(min = 3)
    @NotBlank
    private String phone;
    private int userStatus;

}
