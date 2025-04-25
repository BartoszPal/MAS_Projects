import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class Director extends ObjectPlus {
    private String name;
    private String secondName;
    private String surname;
    private LocalDateTime birth;
    private List<Film> directs = new ArrayList<>();

    public Director(String name, String secondName, String surname, LocalDateTime birth) {
        setName(name);
        setSecondName(secondName);
        setSurname(surname);
        setBirth(birth);
    }

    public Director(String name, String surname, LocalDateTime birth) {
        this(name, null, surname, birth);
    }

    public static void showExtent() throws Exception {
        ObjectPlus.showExtent(Director.class);
    }

    public void destroyDirector() {
        if (!directs.isEmpty()) {
            for (Film film : new ArrayList<>(directs)) {
                film.removeDirector(this);
            }
            this.directs.clear();
        }
        ObjectPlus.removeFromExtent(this);
    }

    public List<Film> getDirects() {
        return Collections.unmodifiableList(directs);
    }

    private boolean checkIfEmpty(String value) {
        return value == null || value.isBlank();
    }

    public void addFilm(Film film) {
        if (!directs.contains(film) && film != null) {
            directs.add(film);
            film.addDirector(this);
        }
    }

    public void removeFilm(Film film) {
        if (directs.contains(film) && film != null) {
            directs.remove(film);
            film.removeDirector(this);
        }
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

    @Override
    public String toString() {
        return "Director{" +
                "name='" + name + '\'' +
                ", secondName='" + secondName + '\'' +
                ", surname='" + surname + '\'' +
                ", directs=" + directs +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Director director = (Director) o;
        return Objects.equals(name, director.name) && Objects.equals(secondName, director.secondName) && Objects.equals(surname, director.surname) && Objects.equals(birth, director.birth) && Objects.equals(directs, director.directs);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, secondName, surname, birth, directs);
    }
}
