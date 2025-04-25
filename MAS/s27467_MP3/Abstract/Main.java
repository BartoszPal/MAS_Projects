package Abstract;

import java.time.LocalDateTime;

public class Main {
    public static void main(String[] args) {
        Employee employee1 = new Shopkeeper("ala@wp.pl", 1000.0, LocalDateTime.of(2013, 12,12,12,12));
        Employee employee2 = new Accountant("ala@wp.pl", 1000.0, LocalDateTime.of(2013, 12,12,12,12));
        System.out.println(employee1.getIncome());
        System.out.println(employee2.getIncome());
    }
}
