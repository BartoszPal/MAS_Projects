package Dynamic;

public class Main {
    public static void main(String[] args) throws Exception {
        Client client = new Client("ala@wp.pl");
        System.out.println(client.getClientStatus());
        client.changeClientStatus();
        System.out.println(client.getClientStatus());
        ObjectPlus.showExtent(BannedClient.class);
        ObjectPlus.showExtent(ActiveClient.class);
        client.getBannedClient().destroyBannedClient();
        System.out.println(client.getClientStatus());
        client.destroyClient();
        client.changeClientStatus();
        System.out.println(client.getClientStatus());
    }
}