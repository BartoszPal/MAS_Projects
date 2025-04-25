package Overlapping;

import java.time.LocalDateTime;

public class Main {
    public static void main(String[] args) throws Exception {
        Person person = new Person("Ala", "Nowak", LocalDateTime.now(), true, false, true, "alamakota@wp.pl", null, null);
        person.whoAmI();
        System.out.println(person.getClient().getEmail());
        person.getClient().setRegistrationDate(LocalDateTime.now());
        System.out.println(person.whoAmI());
        ObjectPlus.showExtent(Person.class);
        person.performSpecialComboActions();
    }
}
