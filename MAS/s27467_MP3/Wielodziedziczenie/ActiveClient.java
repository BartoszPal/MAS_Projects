package Wielodziedziczenie;

import java.time.LocalDateTime;

public class ActiveClient extends ObjectPlus{
    private LocalDateTime registrationDate;

    public ActiveClient(LocalDateTime registrationDate) {
        setRegistrationDate(registrationDate);
    }

    public LocalDateTime getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(LocalDateTime registrationDate) {
        if(registrationDate == null){
            throw new IllegalArgumentException("Registration date cannot be empty");
        }if(registrationDate.isAfter(LocalDateTime.now())){
            throw new IllegalArgumentException("Registration date cannot be in the future");
        }
        this.registrationDate = registrationDate;
    }

    public void whoAmI(){
        System.out.println("I am active client");
    }
}
