import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Client extends ObjectPlus {
    private String name;
    private String secondName;
    private String surname;
    private LocalDateTime birth;
    private List<FilmRental> filmRentals;

    public Client(String name, String secondName, String surname, LocalDateTime birth, List<FilmRental> filmRentals) {
        setName(name);
        setSecondName(secondName);
        setSurname(surname);
        setBirth(birth);
        setRentals(filmRentals); // todo musi miec conajmniej 1 element
    }

    public Client(String name, String surname, LocalDateTime birth, List<FilmRental> filmRentals) {
        this(name, null, surname, birth, filmRentals);
    }

    public static List<Client> getClientsWithMostRentals() throws ClassNotFoundException {
        List<Client> clients = ObjectPlus.getExtent(Client.class);
        int maxAmount = 0;
        List<Client> returnClients = new ArrayList<>();
        for (Client client : clients) {
            int count = client.filmRentals.size();
            if (count > maxAmount) {
                returnClients.clear();
                maxAmount = count;
                returnClients.add(client);
            } else if (count == maxAmount) {
                returnClients.add(client);
            }
        }
        return returnClients;
    }

    public static void showExtent() throws Exception {
        ObjectPlus.showExtent(Client.class);
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
        boolean isEmpty = checkIfEmpty(secondName);
        if (secondName.length() < 2 && !isEmpty) {
            throw new IllegalArgumentException("Second name length must be at least 2");
        } else if (isEmpty) {
            this.secondName = null;
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

    public List<FilmRental> getRentals() {
        filmRentals.sort((r1, r2) -> r1.getStartDate().compareTo(r2.getStartDate()));
        return filmRentals;
    }

    public void setRentals(List<FilmRental> filmRentals) {
        if (filmRentals == null || filmRentals.isEmpty()) {
            throw new IllegalArgumentException("Film rentals list cannot be empty or null");
        }
        this.filmRentals = filmRentals;
    }

    private boolean checkIfEmpty(String value) {
        return value == null || value.isBlank();
    }

    public void addFilmRental(FilmRental filmRental) {
        if (filmRental == null) {
            throw new IllegalArgumentException("Film rental cannot be empty");
        } else if (filmRentals.contains(filmRental)) {
            throw new IllegalArgumentException("Film rental is already in the list");
        }
        filmRentals.add(filmRental);
    }

    public void deleteFilmRental(FilmRental filmRental) {
        if (!filmRentals.contains(filmRental)) {
            throw new IllegalArgumentException("Film rental not found in the list");
        }
        filmRentals.remove(filmRental);
    }

    @Override
    public String toString() {
        return "Client{" +
                "name='" + name + '\'' +
                ", secondName='" + secondName + '\'' +
                ", surname='" + surname + '\'' +
                ", birth='" + birth.format(DateTimeFormatter.ofPattern("dd-MM-yyyy")) + '\'' +
                ", filmRentals=" + filmRentals +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Client client = (Client) o;
        return Objects.equals(name, client.name) && Objects.equals(secondName, client.secondName) && Objects.equals(surname, client.surname) && Objects.equals(birth, client.birth) && Objects.equals(filmRentals, client.filmRentals);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, secondName, surname, birth, filmRentals);
    }
}
