package Overlapping;

import java.time.LocalDateTime;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Client {
    private Person person;
    private String email;
    private LocalDateTime registrationDate;

    private Client(Person person, String email) {
        setPerson(person);
        setEmail(email);
        setRegistrationDate(LocalDateTime.now());
    }

    public static Client createClient(Person person, String email) {
        if (person == null) {
            throw new IllegalArgumentException("The given person does not exist!");
        }
        if (person.getClient() != null) {
            throw new IllegalStateException("This person is already an client.");
        }
        return new Client(person, email);
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

    public void performClientActions() {
        System.out.println("Browsing the film platform, watching movies, rating content.");
    }

    @Override
    public String toString() {
        return "Client{" +
                "email='" + email + '\'' +
                ", registrationDate=" + registrationDate +
                '}';
    }
}
