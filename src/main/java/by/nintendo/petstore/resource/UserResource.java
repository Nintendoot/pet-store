package by.nintendo.petstore.resource;

import by.nintendo.petstore.model.User;
import by.nintendo.petstore.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(path = "/user")
public class UserResource {

    @Autowired
    private UserService userService;


    @GetMapping(path = "/{name}")
    public ResponseEntity<?> getUserByName(@PathVariable("name") String name) {
        User user = userService.getUserByName(name);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }


    @PutMapping(path = "/{name}")
    public ResponseEntity<Object> updateUser(@PathVariable("name") String name, @Valid @RequestBody User user) {
        return userService.updateUser(name, user);
    }


    @GetMapping(path = "/all")
    public ResponseEntity<List<User>> getAll() {
        List<User> users = userService.allUsers();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }


    @PostMapping
    public ResponseEntity<?> createUser(@Valid @RequestBody User user) {
        userService.createUser(user);
        return new ResponseEntity<>("User create", HttpStatus.CREATED);
    }


    @DeleteMapping(path = "/{name}")
    public ResponseEntity<Object> deleateUserByName(@PathVariable("name") String name) {
        return userService.deleateUser(name);
    }


    @PostMapping(path = "/createWithArray")
    public ResponseEntity<?> createWithArray(@RequestBody User[] users) {
        if (users.length != 0) {
            for (@Valid User i : users) {
                userService.createUser(i);
            }
            return new ResponseEntity<>("Users add", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("No users", HttpStatus.BAD_REQUEST);
        }
    }
}



