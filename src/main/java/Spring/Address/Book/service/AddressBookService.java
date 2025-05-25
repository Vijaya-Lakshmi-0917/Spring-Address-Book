package Spring.Address.Book.service;

import Spring.Address.Book.dto.User;
import Spring.Address.Book.entity.AddressBookEntity;
import Spring.Address.Book.repository.AddressBookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AddressBookService {

    @Autowired
    private AddressBookRepository repository;

    private AddressBookEntity fromDTO(User dto) {
        return new AddressBookEntity(null, dto.getName(), dto.getEmail());
    }

    private User toDTO(AddressBookEntity model) {
        return new User(model.getName(), model.getEmail());
    }

    public List<User> getAll() {
        return repository.findAll()
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    public Optional<User> getById(Long id) {
        return repository.findById(id).map(this::toDTO);
    }

    public User add(User dto) {
        AddressBookEntity saved = repository.save(fromDTO(dto));
        return toDTO(saved);
    }

    public Optional<User> update(Long id, User dto) {
        return repository.findById(id).map(existing -> {
            existing.setName(dto.getName());
            existing.setEmail(dto.getEmail());
            AddressBookEntity updated = repository.save(existing);
            return toDTO(updated);
        });
    }

    public boolean delete(Long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return true;
        }
        return false;
    }
}