package Spring.Address.Book.controller;

import Spring.Address.Book.utility.AddressBook;
import Spring.Address.Book.utility.AddressBookUtility;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/addressbook")
public class AddressBookController {

    @GetMapping
    public ResponseEntity<List<AddressBook>> getAll() {
        return ResponseEntity.ok(AddressBookUtility.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<AddressBook> getById(@PathVariable Long id) {
        return AddressBookUtility.getById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<AddressBook> create(@RequestBody AddressBook addressBook) {
        return new ResponseEntity<>(AddressBookUtility.add(addressBook), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AddressBook> update(@PathVariable Long id, @RequestBody AddressBook updated) {
        return AddressBookUtility.update(id, updated)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (AddressBookUtility.delete(id)) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}