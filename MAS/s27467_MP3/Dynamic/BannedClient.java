package Dynamic;

import java.time.LocalDateTime;

public class BannedClient extends ObjectPlus {
    private Client client;
    private LocalDateTime bannedTime;

    private BannedClient(Client client) {
        setClient(client);
        setBannedTime(LocalDateTime.now());
    }

    public static BannedClient createBannedClient(Client client) {
        if (client == null) {
            throw new IllegalArgumentException("The given client does not exist!");
        }
        if (client.getBannedClient() != null) {
            throw new IllegalStateException("This client is already banned.");
        }
        return new BannedClient(client);
    }

    public LocalDateTime getBannedTime() {
        return bannedTime;
    }

    public void setBannedTime(LocalDateTime registrationDate) {
        if (registrationDate == null) {
            throw new IllegalArgumentException("Registration date cannot be empty");
        }
        if (registrationDate.isAfter(LocalDateTime.now())) {
            throw new IllegalArgumentException("Registration date cannot be in the future");
        }
        this.bannedTime = registrationDate;
    }

    public void destroyBannedClient() {
        if (this.client != null) {
            this.client.changeClientStatus();
            this.client = null;
            ObjectPlus.removeFromExtent(this);
        }
    }

    public Client getClient() {
        return client;
    }

    private void setClient(Client client) {
        if (client == null) {
            throw new IllegalArgumentException("Person cannot be null");
        }
        this.client = client;
    }
}
