package Spring.Address.Book.service;

import Spring.Address.Book.dto.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.*;

@Slf4j
@Service
public class AddressBookService {

    private final Map<Long, User> userMap = new HashMap<>();
    private long nextId = 1;

    public List<User> getAll() {
        log.info("Fetching all users");
        return new ArrayList<>(userMap.values());
    }

    public Optional<User> getById(Long id) {
        log.info("Fetching user with ID: {}", id);
        return Optional.ofNullable(userMap.get(id));
    }

    public User add(User user) {
        user.setId(nextId++);
        userMap.put(user.getId(), user);
        log.info("Added user: {}", user);
        return user;
    }

    public Optional<User> update(Long id, User updatedUser) {
        if (userMap.containsKey(id)) {
            updatedUser.setId(id);
            userMap.put(id, updatedUser);
            log.info("Updated user with ID {}: {}", id, updatedUser);
            return Optional.of(updatedUser);
        }
        log.warn("User with ID {} not found for update", id);
        return Optional.empty();
    }

    public boolean delete(Long id) {
        boolean removed = userMap.remove(id) != null;
        if (removed) {
            log.info("Deleted user with ID {}", id);
        } else {
            log.warn("User with ID {} not found for deletion", id);
        }
        return removed;
    }
}