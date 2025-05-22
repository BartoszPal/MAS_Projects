import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Studio{
    private String name;
    private String location;
    private List<Actor> employs = new ArrayList<>();

    public Studio(String name, String location) {
        setName(name);
        setLocation(location);
    }

    public List<Actor> getEmploys() {
        return Collections.unmodifiableList(employs);
    }

    private boolean checkIfEmpty(String value) {
        return value == null || value.isBlank();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (checkIfEmpty(name)) {
            throw new IllegalArgumentException("Studio name cannot be empty");
        } else if (name.length() < 2) {
            throw new IllegalArgumentException("Studio name must have at least 2 characters");
        }
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        if (checkIfEmpty(location)) {
            throw new IllegalArgumentException("Location cannot be empty");
        } else if (location.length() < 2) {
            throw new IllegalArgumentException("Location must have at least 2 characters");
        }
        this.location = location;
    }

    public void removeActor(Actor actor) {
        if (actor == null) {
            throw new IllegalArgumentException("Actor cannot be null");
        }

        if(actor.getEmployedBy() != null) {
            throw new IllegalArgumentException("Cannot delete {XOR constraint}.");
        }

        if (employs.contains(actor)) {
            employs.remove(actor);
        } else {
            throw new IllegalArgumentException("Actor is not employed by this studio");
        }
    }

    public void addActor(Actor actor) {
        if (actor == null) {
            throw new IllegalArgumentException("Actor cannot be null");
        }

        if (actor.getRepresentedBy() != null) {
            throw new IllegalArgumentException("Actor is already represented by an Agency (XOR constraint).");
        }

        if (!employs.contains(actor) && actor.getEmployedBy() != null) {
            employs.add(actor);
            actor.setEmployedBy(this);
        } else {
            throw new IllegalArgumentException("Actor is not employed by this studio");
        }
    }
}
