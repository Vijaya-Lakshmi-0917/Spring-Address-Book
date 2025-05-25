package Spring.Address.Book.utility;

import java.util.*;
import java.util.concurrent.atomic.AtomicLong;

public class AddressBookUtility {
    private static final List<AddressBook> addressList = new ArrayList<>();
    private static final AtomicLong idCounter = new AtomicLong(0);

    public static List<AddressBook> getAll() {
        return addressList;
    }

    public static Optional<AddressBook> getById(Long id) {
        return addressList.stream().filter(a -> a.getId().equals(id)).findFirst();
    }

    public static AddressBook add(AddressBook address) {
        address.setId(idCounter.incrementAndGet());
        addressList.add(address);
        return address;
    }

    public static Optional<AddressBook> update(Long id, AddressBook updated) {
        return getById(id).map(existing -> {
            existing.setName(updated.getName());
            existing.setEmail(updated.getEmail());
            return existing;
        });
    }

    public static boolean delete(Long id) {
        return addressList.removeIf(a -> a.getId().equals(id));
    }
}