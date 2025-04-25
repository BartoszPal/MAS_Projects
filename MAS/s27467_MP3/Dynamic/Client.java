package Dynamic;

import java.time.LocalDateTime;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Client extends ObjectPlus {
    private String email;
    private LocalDateTime registrationDate;
    private ActiveClient activeClient;
    private BannedClient bannedClient;

    public Client(String email) {
        setEmail(email);
        setRegistrationDate(LocalDateTime.now());
        this.activeClient = ActiveClient.createActiveClient(this); //defaultowo jest active
    }

    public void destroyClient() {
        ActiveClient activeClientToDelete = this.activeClient;
        BannedClient bannedClientToDelete = this.bannedClient;
        this.activeClient = null;
        this.bannedClient = null;
        if (activeClientToDelete != null) {
            activeClientToDelete.destroyActiveClient();
        }
        if (bannedClientToDelete != null) {
            bannedClientToDelete.destroyBannedClient();
        }
        ObjectPlus.removeFromExtent(this);
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

    public LocalDateTime getBannedTime() {
        if (this.bannedClient == null) {
            throw new UnsupportedOperationException("Client is not banned, do not have banned time");
        }
        return this.bannedClient.getBannedTime();
    }

    public void setBannedTime(LocalDateTime bannedTime) {
        if (this.bannedClient == null) {
            throw new UnsupportedOperationException("Client is not banned, cannot change banned time");
        }
        this.bannedClient.setBannedTime(bannedTime);
    }

    public String getEmail() {
        return email;
    }

    private void setEmail(String email) {
        if (email == null || email.isBlank()) {
            throw new IllegalArgumentException("Email cannot be empty");
        }
        if (this.bannedClient != null) {
            throw new IllegalStateException("Banned clients cannot update contact information");
        }
        String regex = "^[a-zA-Z0-9_!#$%&amp;'*+/=?`{|}~^-]+(?:\\.[a-zA-Z0-9_!#$%&amp;'*+/=?`{|}~^-]+)*@[a-zA-Z0-9-]+(?:\\.[a-zA-Z0-9-]+)*$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(email);
        if (!matcher.matches()) {
            throw new IllegalArgumentException("Email is invalid");
        }
        this.email = email;
    }

    public boolean isActive() {
        return this.activeClient != null;
    }

    public boolean isBanned() {
        return this.bannedClient != null;
    }

    public ActiveClient getActiveClient() {
        return activeClient;
    }

    public BannedClient getBannedClient() {
        return bannedClient;
    }

    public String getClientStatus() {
        if (this.bannedClient != null) {
            return "Status: banned, Banned time: " + this.bannedClient.getBannedTime();
        } else if (this.activeClient != null) {
            return "Status: active";
        } else {
            return "Status: none, client is deleted";
        }
    }

    public void changeClientStatus() {
        if (this.activeClient != null && this.bannedClient == null) {
            ActiveClient activeClientToDelete = this.activeClient;
            this.activeClient = null;
            activeClientToDelete.destroyActiveClient();
            this.bannedClient = BannedClient.createBannedClient(this);
        } else if (this.bannedClient != null && this.activeClient == null) {
            BannedClient bannedClientToDelete = this.bannedClient;
            this.bannedClient = null;
            bannedClientToDelete.destroyBannedClient();
            this.activeClient = ActiveClient.createActiveClient(this);
        }
    }

    public void onLogin() {
        System.out.println("Welcome, your current status is: " + getClientStatus());
        if (activeClient != null) {
            System.out.println("You can now shop and manage your account.");
        } else if (bannedClient != null) {
            System.out.println("You are banned. Please contact support or submit an unban request.");
        }
    }
}
