package Spring.Address.Book.service;

import Spring.Address.Book.dto.User;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class AddressBookService {

    private final Map<Long, User> userMap = new HashMap<>();
    private long nextId = 1;

    public List<User> getAll() {
        return new ArrayList<>(userMap.values());
    }

    public Optional<User> getById(Long id) {
        return Optional.ofNullable(userMap.get(id));
    }

    public User add(User user) {
        user.setId(nextId++);
        userMap.put(user.getId(), user);
        return user;
    }

    public Optional<User> update(Long id, User updatedUser) {
        if (userMap.containsKey(id)) {
            updatedUser.setId(id);
            userMap.put(id, updatedUser);
            return Optional.of(updatedUser);
        }
        return Optional.empty();
    }

    public boolean delete(Long id) {
        return userMap.remove(id) != null;
    }
}