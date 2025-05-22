import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Actor extends ObjectPlus {
    private String name;
    private String surname;
    private List<FilmActorRole> roles = new ArrayList<>();
    private Studio employedBy;
    private Agency representedBy;

    public Actor(String name, String surname, Studio studio) {
        setName(name);
        setSurname(surname);
        setEmployedBy(studio);
    }

    public Actor(String name, String surname, Agency agency) {
        setName(name);
        setSurname(surname);
        setRepresentedBy(agency);
    }

    public void destroy() {
        if (!roles.isEmpty()) {
            for (FilmActorRole filmActorRole : new ArrayList<>(roles)) {
                filmActorRole.destroyFilmActorRole();
            }
            this.roles.clear();
        }
        ObjectPlus.removeFromExtent(this);
    }

    public List<FilmActorRole> getRoles() {
        return Collections.unmodifiableList(roles);
    }

    public void addRole(Film film, ActorRole role) {
        if (film == null || role == null) {
            throw new IllegalArgumentException("Film and role are obligatory");
        }
        new FilmActorRole(this, film, role);
    }

    public void addRole(FilmActorRole role) {
        if (role == null) {
            throw new IllegalArgumentException("Role cannot be empty");
        }
//        for (FilmActorRole existingRole : roles) {
//            if (existingRole.equals(role)) {
// Zakładamy, że nie można dodać dwa razy dokładnie takiego samego obiektu (czyli z tymi samymi danymi).
// Dzięki temu użytkownik nie doda przypadkowo tego samego wpisu kilka razy.
// Jeśli ktoś naprawdę chce dodać kilka podobnych wpisów, musi stworzyć osobne obiekty – nawet jeśli mają takie same dane.
//                throw new IllegalArgumentException("This exact role for this film already exists");
//            }
//        }
        this.roles.add(role);
    }

    public void removeRole(FilmActorRole filmActorRole) {
        if (filmActorRole != null && roles.contains(filmActorRole)) {
            roles.remove(filmActorRole);
            filmActorRole.destroyFilmActorRole();
        }
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

    public Studio getEmployedBy() {
        return employedBy;
    }

    public void setEmployedBy(Studio studio) {
        if (studio == null) {
            throw new IllegalArgumentException("Studio cannot be null");
        } else if (this.representedBy != null) {
            throw new IllegalArgumentException("Cannot set Studio - actor is already employed by a Agency (XOR constraint).");
        }
        if (this.employedBy != studio) {
            if(employedBy != null){
                Studio previousStudio = this.employedBy;
                this.employedBy = null;
                previousStudio.removeActor(this);
            }

            this.employedBy = studio;
            employedBy.addActor(this);
        }
    }

    public Agency getRepresentedBy() {
        return representedBy;
    }

    public void setRepresentedBy(Agency agency) {
        if (agency == null) {
            throw new IllegalArgumentException("Agency cannot be null");
        } else if (this.employedBy != null) {
            throw new IllegalArgumentException("Cannot set Agency - actor is already employed by a Studio (XOR constraint).");
        }
        if (this.representedBy != agency) {
            if(representedBy != null){
                Agency previousAgency = this.representedBy;
                this.representedBy = null;
                previousAgency.removeActor(this);
            }

            this.representedBy = agency;
            representedBy.addActor(this);
        }
    }

    public void changeFromStudioToAgency(Agency agency){
        if (agency == null) {
            throw new IllegalArgumentException("Agency cannot be null");
        }
        if (this.representedBy != null) {
            throw new IllegalArgumentException("Actor is already represented by an agency");
        }
        if (this.employedBy == null) {
            throw new IllegalArgumentException("Actor is not employed by any studio");
        }

        Studio previousStudio = this.employedBy;
        this.employedBy = null;
        previousStudio.removeActor(this);

        this.representedBy = agency;
        agency.addActor(this);
    }

    public void changeFromAgencyToStudio(Studio studio) {
        if (studio == null) {
            throw new IllegalArgumentException("Studio cannot be null");
        }
        if (this.employedBy != null) {
            throw new IllegalArgumentException("Actor is already employed by a studio");
        }
        if (this.representedBy == null) {
            throw new IllegalArgumentException("Actor is not represented by any agency");
        }

        Agency previousAgency = this.representedBy;
        this.representedBy = null;
        previousAgency.removeActor(this);

        this.employedBy = studio;
        studio.addActor(this);
    }
}
