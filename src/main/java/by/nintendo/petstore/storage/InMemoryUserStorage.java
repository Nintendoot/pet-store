package by.nintendo.petstore.storage;

import by.nintendo.petstore.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class InMemoryUserStorage {
    private final List<User> userList = new ArrayList<>();
    private  long userId = 1;

    @Autowired
    public InMemoryUserStorage() {
    }

    public List<User> getAllUsers() {
        return userList;
    }

    public void addUser(User user) {
        user.setId(userId);
        userList.add(user);
        userId++;
    }

}
