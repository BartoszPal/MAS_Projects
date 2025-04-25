package Wielodziedziczenie;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class StaffMember extends ActiveClient implements IEmployee {
    private Double income;

    public StaffMember(LocalDateTime registrationDate, Double income) {
        super(registrationDate);
        setIncome(income);
    }

    public void destroy() {
        ObjectPlus.removeFromExtent(this);
    }

    @Override
    public Double getIncome() {
        long monthsAsClient = ChronoUnit.MONTHS.between(
                getRegistrationDate(), LocalDateTime.now());
        double bonus = 100 * Math.min(monthsAsClient, 12);
        return this.income + bonus;
    }

    @Override
    public Double getDefaultIncome() {
        return this.income;
    }

    @Override
    public void setIncome(Double income) {
        if (income == null) {
            throw new IllegalArgumentException("Income cannot be null");
        }
        if (income < 0.0) {
            throw new IllegalArgumentException("Income cannot be a negative number");
        }
        this.income = income;
    }

    public void whoAmI(){
        System.out.println("I am active client and employee = staff member");
    }
}
