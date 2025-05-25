package Spring.Address.Book.service;

import Spring.Address.Book.dto.User;
import Spring.Address.Book.exception.AddressBookException;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class AddressBookService {

    private final Map<Long, User> userMap = new HashMap<>();
    private long nextId = 1;

    public List<User> getAll() {
        return new ArrayList<>(userMap.values());
    }

    public User getById(Long id) {
        return Optional.ofNullable(userMap.get(id))
                .orElseThrow(() -> new AddressBookException("User with ID " + id + " not found"));
    }

    public User add(User user) {
        user.setId(nextId++);
        userMap.put(user.getId(), user);
        return user;
    }

    public User update(Long id, User updatedUser) {
        if (!userMap.containsKey(id)) {
            throw new AddressBookException("User with ID " + id + " not found");
        }
        updatedUser.setId(id);
        userMap.put(id, updatedUser);
        return updatedUser;
    }

    public void delete(Long id) {
        if (!userMap.containsKey(id)) {
            throw new AddressBookException("User with ID " + id + " not found");
        }
        userMap.remove(id);
    }
}