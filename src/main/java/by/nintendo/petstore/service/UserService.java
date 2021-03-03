package by.nintendo.petstore.service;

import by.nintendo.petstore.exception.NoSuchUserException;
import by.nintendo.petstore.exception.UserAlreadyExistsException;
import by.nintendo.petstore.model.User;
import by.nintendo.petstore.storage.InMemoryUserStorage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.util.List;

@Service
public class UserService {

    @Autowired
    private InMemoryUserStorage inMemoryUserStorage;

    @Autowired
    public UserService() {
    }

    public boolean checkUserByName(String name) {
        return inMemoryUserStorage.getAllUsers().stream().anyMatch(x -> x.getName().equals(name));
    }

    public User getUserByName(String name) {
        for (User user : inMemoryUserStorage.getAllUsers()) {
            if (user.getName().equals(name)) {
                return user;
            }
        }
        throw new NoSuchUserException("User not found");
    }

    public ResponseEntity<Object> deleateUser(String name) {
        boolean chek = checkUserByName(name);
        if (chek) {
            inMemoryUserStorage.getAllUsers().
                    removeIf(x -> x.getName().equals(name));
            return new ResponseEntity<>("User delete.", HttpStatus.OK);
        } else {
            throw new NoSuchUserException("User not found");
        }
    }

    public List<User> allUsers() {
        if (inMemoryUserStorage.getAllUsers().size() > 0) {
            return inMemoryUserStorage.getAllUsers();
        } else {
            throw new NoSuchUserException("No users");
        }
    }

    public boolean checkUserInMemory(User user) {
        boolean result = false;
        for (User us : inMemoryUserStorage.getAllUsers()) {
            if (us.getName().equals(user.getName())) {
                result = true;
                break;
            }
        }
        return result;
    }

    public void createUser(@Valid User user) {
        if (!checkUserInMemory(user)) {
            inMemoryUserStorage.addUser(user);
        } else {
            throw new UserAlreadyExistsException("User already exists.");
        }
    }

    public ResponseEntity<Object> updateUser(String name, User user) {
        boolean cheking = false;

        for (User us : inMemoryUserStorage.getAllUsers()) {
            if (checkUserByName(name)) {
                user.setId(us.getId());
                inMemoryUserStorage.getAllUsers().set(inMemoryUserStorage.getAllUsers().indexOf(us), user);
                cheking = true;
                break;
            }
        }
        if (cheking) {
            return new ResponseEntity<>("User update.", HttpStatus.OK);
        } else {
            throw new NoSuchUserException("User not found");
        }

    }
}



