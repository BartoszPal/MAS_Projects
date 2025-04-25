package Dynamic;

public class ActiveClient extends ObjectPlus {
    private Client client;

    private ActiveClient(Client client) {
        setClient(client);
    }

    public static ActiveClient createActiveClient(Client client) {
        if (client == null) {
            throw new IllegalArgumentException("The given client does not exist!");
        }
        if (client.getActiveClient() != null) {
            throw new IllegalStateException("This client is already active.");
        }
        return new ActiveClient(client);
    }

    public void destroyActiveClient() {
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
