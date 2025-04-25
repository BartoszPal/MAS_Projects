package Wielodziedziczenie;

import java.time.LocalDateTime;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Employee extends ObjectPlus implements IEmployee {
    private String email;
    private Double income;
    private LocalDateTime registrationDate;

    public Employee(String email, Double income) {
        setEmail(email);
        setIncome(income);
        setRegistrationDate(LocalDateTime.now());
    }

    public void destroy(){
        ObjectPlus.removeFromExtent(this);
    }

    public LocalDateTime getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(LocalDateTime registrationDate) {
        if (registrationDate == null) {
            throw new IllegalArgumentException("Registration date cannot be empty");
        }
        if (registrationDate.isAfter(LocalDateTime.now())) {
            throw new IllegalArgumentException("Registration date cannot be in the future");
        }
        this.registrationDate = registrationDate;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        if (email == null || email.isBlank()) {
            throw new IllegalArgumentException("Email cannot be empty");
        }
        String regex = "^[a-zA-Z0-9_!#$%&amp;'*+/=?`{|}~^-]+(?:\\.[a-zA-Z0-9_!#$%&amp;'*+/=?`{|}~^-]+)*@[a-zA-Z0-9-]+(?:\\.[a-zA-Z0-9-]+)*$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(email);
        if (!matcher.matches()) {
            throw new IllegalArgumentException("Email is invalid");
        }
        this.email = email;
    }

    @Override
    public Double getIncome() {
        double inflationRate = 0.06;
        int years = LocalDateTime.now().getYear() - getRegistrationDate().getYear();
        double adjustedIncome = income;
        for (int i = 0; i < years; i++) {
            adjustedIncome *= (1 + inflationRate);
        }
        adjustedIncome = Math.round(adjustedIncome * 100.0) / 100.0;
        return adjustedIncome;
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

    public void performEmployeeTasks() {
        System.out.println("Working in shop.");
    }

    @Override
    public String toString() {
        return "Employee{" +
                "email='" + email + '\'' +
                ", income=" + income +
                ", registrationDate=" + registrationDate +
                '}';
    }
}
