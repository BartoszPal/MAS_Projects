package Abstract;

import java.time.LocalDateTime;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class Employee extends ObjectPlus{
    private String email;
    private Double income;
    private LocalDateTime registrationDate;

    public Employee(String email, Double income, LocalDateTime registrationDate) {
        setEmail(email);
        setIncome(income);
        setRegistrationDate(registrationDate);
    }

    public void destroyEmployee(){
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

    public abstract Double getIncome();

    public Double getDefaultIncome() {
        return this.income;
    }

    public void setIncome(Double income) {
        if (income == null) {
            throw new IllegalArgumentException("Income cannot be null");
        }
        if (income < 0.0) {
            throw new IllegalArgumentException("Income cannot be a negative number");
        }
        this.income = income;
    }
}
