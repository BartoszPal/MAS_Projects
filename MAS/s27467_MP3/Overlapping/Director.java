package Overlapping;

public class Director {
    private Person person;

    private Director(Person person) {
        setPerson(person);
    }

    public static Director createDirector(Person person) {
        if (person == null) {
            throw new IllegalArgumentException("The given person does not exist!");
        }
        if (person.getDirector() != null) {
            throw new IllegalStateException("This person is already an director.");
        }
        return new Director(person);
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

    public void performDirectorActions() {
        System.out.println("Directing scenes, guiding actors, making creative decisions.");
    }

    @Override
    public String toString() {
        return "Director{No fields}";
    }
}
