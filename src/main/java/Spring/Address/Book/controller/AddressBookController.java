package Spring.Address.Book.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AddressBookController {

    @GetMapping("/addressbook")
    public String getWelcomeMessage() {
        return "Welcome to the Address Book App!";
    }
}
