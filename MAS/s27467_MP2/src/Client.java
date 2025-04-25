import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class Client extends ObjectPlus {
    private String name;
    private String secondName;
    private String surname;
    private LocalDateTime birth;
    private LocalDateTime registrationDate;
    private List<Rating> rates = new ArrayList<>();

    public Client(String name, String secondName, String surname, LocalDateTime birth) {
        setName(name);
        setSecondName(secondName);
        setSurname(surname);
        setBirth(birth);
        setRegistrationDate(LocalDateTime.now());
    }

    public Client(String name, String surname, LocalDateTime birth) {
        this(name, null, surname, birth);
    }

    public static void showExtent() throws Exception {
        ObjectPlus.showExtent(Client.class);
    }

    public void destroyClient() {
        if (!rates.isEmpty()) {
            for (Rating rating : new ArrayList<>(rates)) {
                rating.destroyRating();
            }
            this.rates.clear();
        }
        ObjectPlus.removeFromExtent(this);
    }

    public List<Rating> getRates() {
        return Collections.unmodifiableList(rates);
    }

    public void removeRating(Rating rating) {
        if (rating != null && rates.contains(rating)) {
            rates.remove(rating);
            rating.destroyRating();
        }
    }

    public void addRating(Rating rating) {
        if (!rates.contains(rating) && rating.getRatedBy() == this) {
            rates.add(rating);
        }
    }

    public void addRating(Film film, Integer score, String comment) {
        if (!rates.isEmpty()) {
            for (Rating rating : rates) {
                if (rating.getRatedFilm() == film && rating.getRatedBy() == this) {
                    throw new IllegalArgumentException("Client already rated this film");
                }
            }
        }
        rates.add(new Rating(this, film, score, comment, LocalDateTime.now()));
    }

    private boolean checkIfEmpty(String value) {
        return value == null || value.isBlank();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (checkIfEmpty(name)) {
            throw new IllegalArgumentException("Name cannot be empty");
        } else if (name.length() < 2) {
            throw new IllegalArgumentException("Name length must be at least 2");
        }
        this.name = name;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        if (secondName == null || secondName.isBlank()) {
            this.secondName = null;
        } else if (secondName.length() < 2) {
            throw new IllegalArgumentException("Second name length must be at least 2");
        } else {
            this.secondName = secondName;
        }
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        if (checkIfEmpty(surname)) {
            throw new IllegalArgumentException("Surname cannot be empty");
        } else if (surname.length() < 2) {
            throw new IllegalArgumentException("Surname length must be at least 2");
        }
        this.surname = surname;
    }

    public LocalDateTime getBirth() {
        return birth;
    }

    public void setBirth(LocalDateTime birth) {
        if (birth == null) {
            throw new IllegalArgumentException("Birth cannot be null");
        } else if (birth.isAfter(LocalDateTime.now())) {
            throw new IllegalArgumentException("Birth date cannot be in the future");
        }
        this.birth = birth;
    }


    public LocalDateTime getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(LocalDateTime registrationDate) {
        if (registrationDate == null) {
            throw new IllegalArgumentException("Registration date cannot be empty");
        } else if (registrationDate.isAfter(LocalDateTime.now())) {
            throw new IllegalArgumentException("Registration date cannot be in the future");
        }
        this.registrationDate = registrationDate;
    }

    @Override
    public String toString() {
        return "Client{" +
                "name='" + name + '\'' +
                ", secondName='" + secondName + '\'' +
                ", surname='" + surname + '\'' +
                ", rates=" + rates +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Client client = (Client) o;
        return Objects.equals(name, client.name) && Objects.equals(secondName, client.secondName) && Objects.equals(surname, client.surname) && Objects.equals(birth, client.birth) && Objects.equals(registrationDate, client.registrationDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, secondName, surname, birth, registrationDate);
    }
}
