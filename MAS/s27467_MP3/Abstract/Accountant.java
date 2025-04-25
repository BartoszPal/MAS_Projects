package Abstract;

import java.time.LocalDateTime;

public class Accountant extends Employee{

    public Accountant(String email, Double income, LocalDateTime registrationDate) {
        super(email, income, registrationDate);
    }

    @Override
    public Double getIncome() {
        double inflationRate = 0.06;
        int years = LocalDateTime.now().getYear() - getRegistrationDate().getYear();
        double adjustedIncome = getDefaultIncome();
        for (int i = 0; i < years; i++) {
            adjustedIncome *= (1 + inflationRate);
        }
        adjustedIncome = Math.round(adjustedIncome * 100.0) / 100.0;
        return adjustedIncome;
    }
}
