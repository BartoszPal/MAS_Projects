package Overlapping;

import java.time.LocalDateTime;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Employee {
    private Person person;
    private String email;
    private Double income;
    private LocalDateTime registrationDate;

    private Employee(Person person, String email, Double income) {
        setPerson(person);
        setEmail(email);
        setIncome(income);
        setRegistrationDate(LocalDateTime.now());
    }

    public static Employee createEmployee(Person person, String email, Double income) {
        if (person == null) {
            throw new IllegalArgumentException("The given person does not exist!");
        }
        if (person.getEmployee() != null) {
            throw new IllegalStateException("This person is already an employee.");
        }
        return new Employee(person, email, income);
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

    public Person getPerson() {
        return person;
    }

    private void setPerson(Person person) {
        if (person == null) {
            throw new IllegalArgumentException("Person cannot be null");
        }
        this.person = person;
    }

    public Double getIncome() {
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
