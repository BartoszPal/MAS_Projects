package Abstract;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class Shopkeeper extends Employee{
     public Shopkeeper(String email, Double income, LocalDateTime registrationDate) {
        super(email, income, registrationDate);
    }

    @Override
    public Double getIncome() {
        long monthsAsClient = ChronoUnit.MONTHS.between(getRegistrationDate(), LocalDateTime.now());
        double bonus = 100 * Math.min(monthsAsClient, 24);
        return getDefaultIncome() + bonus;
    }
}
