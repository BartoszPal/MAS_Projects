package Overlapping;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Person extends ObjectPlus{
    private String name;
    private String secondName;
    private String surname;
    private LocalDateTime birth;
    private Employee employee;
    private Director director;
    private Client client;

    public Person(String name, String secondName, String surname, LocalDateTime birth, boolean isClient, boolean isEmployee, boolean isDirector, String clientEmail, String employeeEmail, Double employeeIncome) {
        setName(name);
        setSecondName(secondName);
        setSurname(surname);
        setBirth(birth);
        if (isClient) this.client = Client.createClient(this, clientEmail);
        if (isEmployee) this.employee = Employee.createEmployee(this, employeeEmail, employeeIncome);
        if (isDirector) this.director = Director.createDirector(this);
    }

    public Person(String name, String surname, LocalDateTime birth, boolean isClient, boolean isEmployee, boolean isDirector, String clientEmail, String employeeEmail, Double employeeIncome) {
        this(name, null, surname, birth, isClient, isEmployee, isDirector, clientEmail, employeeEmail, employeeIncome);
    }

//    public String getClientEmail(){
//        if(this.client != null){
//            return this.client.getEmail();
//        }
//        return null;
//    }
//
//    public void setClientEmail(String email){
//        if(this.client != null){
//            this.client.setEmail(email);
//        }
//    }
//
//    public String getEmployeeEmail(){
//        if(this.employee != null){
//            return this.employee.getEmail();
//        }
//        return null;
//    }
//
//    public void setEmployeeEmail(String email){
//        if(this.employee != null){
//            this.employee.setEmail(email);
//        }
//    }
//
//    public Double getEmployeeIncome(){
//        if(this.employee != null){
//            return this.employee.getIncome();
//        }
//        return null;
//    }
//
//    public void setEmployeeIncome(Double income){
//        if(this.employee != null){
//            this.employee.setIncome(income);
//        }
//    }

    public void destroyPerson(){
        ObjectPlus.removeFromExtent(this);
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

    private boolean checkIfEmpty(String value) {
        return value == null || value.isBlank();
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

    public Employee getEmployee() {
        return employee;
    }

    public Director getDirector() {
        return director;
    }

    public Client getClient() {
        return client;
    }

    public String whoAmI() {
        List<String> roles = new ArrayList<>();
        if (this.client != null) {
            roles.add("Client");
        }
        if (this.employee != null) {
            roles.add("Employee");
        }
        if (this.director != null) {
            roles.add("Director");
        }
        if (roles.isEmpty()) {
            return "I am just a person.";
        }
        return "I am a " + String.join(", ", roles) + ".";
    }

    public void performSpecialComboActions() {
        boolean isClient = this.client != null;
        boolean isEmployee = this.employee != null;
        boolean isDirector = this.director != null;

        if (isClient && isEmployee && isDirector) {
            System.out.println("This person is a Client, an Employee, and a Director!");
            client.performClientActions();
            employee.performEmployeeTasks();
            director.performDirectorActions();
        } else if (isClient && isEmployee) {
            System.out.println("This person is a Client and an Employee.");
            employee.performEmployeeTasks();
            client.performClientActions();
        } else if (isEmployee && isDirector) {
            System.out.println("This person is an Employee and a Director.");
            director.performDirectorActions();
            employee.performEmployeeTasks();
        } else if (isClient && isDirector) {
            System.out.println("This person is a Client and a Director.");
            director.performDirectorActions();
            client.performClientActions();
        } else if (isClient) {
            System.out.println("This person is only a Client.");
            client.performClientActions();
        } else if (isEmployee) {
            System.out.println("This person is only an Employee.");
            employee.performEmployeeTasks();
        } else if (isDirector) {
            System.out.println("This person is only a Director.");
            director.performDirectorActions();
        } else {
            System.out.println("I’m just a person who hasn't entered the film world yet.");
        }
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", secondName='" + secondName + '\'' +
                ", surname='" + surname + '\'' +
                ", birth=" + birth +
                ", employee=" + employee +
                ", director=" + director +
                ", client=" + client +
                '}';
    }
}
