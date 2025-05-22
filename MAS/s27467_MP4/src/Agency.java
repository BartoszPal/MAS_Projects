import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Agency {
    private String name;
    private String registrationNumber;
    private List<Actor> represents = new ArrayList<>();

    public Agency(String name, String registrationNumber) {
        setName(name);
        setRegistrationNumber(registrationNumber);
    }

    public List<Actor> getRepresents() {
        return Collections.unmodifiableList(represents);
    }

    public String getName() {
        return name;
    }

    private boolean checkIfEmpty(String value) {
        return value == null || value.isBlank();
    }

    public void setName(String name) {
        if (checkIfEmpty(name)) {
            throw new IllegalArgumentException("Agency name cannot be empty");
        } else if (name.length() < 2) {
            throw new IllegalArgumentException("Agency name must have at least 2 characters");
        }
        this.name = name;
    }

    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public void setRegistrationNumber(String registrationNumber) {
        if (checkIfEmpty(registrationNumber)) {
            throw new IllegalArgumentException("Registration number cannot be empty");
        } else if (registrationNumber.length() < 2) {
            throw new IllegalArgumentException("Agency registration number must have at least 2 characters");
        }
        this.registrationNumber = registrationNumber;
    }

    public void removeActor(Actor actor) {
        if (actor == null) {
            throw new IllegalArgumentException("Actor cannot be null");
        }

        if(actor.getRepresentedBy() != null) {
            throw new IllegalArgumentException("Cannot delete {XOR constraint}.");
        }

        if (represents.contains(actor)) {
            represents.remove(actor);
        } else {
            throw new IllegalArgumentException("Actor is not represented by this agency");
        }
    }

    public void addActor(Actor actor) {
        if (actor == null) {
            throw new IllegalArgumentException("Actor cannot be null");
        }

        if (actor.getEmployedBy() != null) {
            throw new IllegalArgumentException("Actor is already represented by a Studio (XOR constraint).");
        }

        if (!represents.contains(actor) && actor.getRepresentedBy() != null) {
            represents.add(actor);
            actor.setRepresentedBy(this);
        } else {
            throw new IllegalArgumentException("Actor is not employed by this agency");
        }
    }
}
