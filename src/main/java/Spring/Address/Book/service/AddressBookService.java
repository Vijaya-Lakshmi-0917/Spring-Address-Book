package Spring.Address.Book.service;

import Spring.Address.Book.dto.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AddressBookService {

    private final List<User> userList = new ArrayList<>();
    private long nextId = 1;

    public List<User> getAllUsers() {
        return userList;
    }

    public Optional<User> getUserById(Long id) {
        return userList.stream().filter(user -> user.getId().equals(id)).findFirst();
    }

    public User addUser(User user) {
        user.setId(nextId++);
        userList.add(user);
        return user;
    }

    public Optional<User> updateUser(Long id, User updatedUser) {
        Optional<User> existingUser = getUserById(id);
        if (existingUser.isPresent()) {
            updatedUser.setId(id);
            int index = userList.indexOf(existingUser.get());
            userList.set(index, updatedUser);
            return Optional.of(updatedUser);
        }
        return Optional.empty();
    }

    public boolean deleteUser(Long id) {
        return userList.removeIf(user -> user.getId().equals(id));
    }
}