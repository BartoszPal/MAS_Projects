import java.io.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws Exception {
//        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("data.ser"))) {
//            ObjectPlus.readExtents(ois);
//        } catch (IOException | ClassNotFoundException e) {
//            e.printStackTrace();
//        }

        FilmRental rental1 = new FilmRental();
        FilmRental rental2 = new FilmRental();
        List<FilmRental> rentals = new ArrayList<>();
        rentals.add(rental1);
        rentals.add(rental2);

        FilmRental rental3 = new FilmRental();
        List<FilmRental> rentals2 = new ArrayList<>();
        rentals2.add(rental1);
        rentals2.add(rental2);
        rentals2.add(rental3);

        Client client1 = new Client("Adam", "Jan","Kowalski", LocalDateTime.of(1990, 1, 15, 0, 0), rentals2);
        Client client2 = new Client("Barbara", "Nowak", LocalDateTime.of(1985, 3, 22, 0, 0), rentals);
        Client client3 = new Client("Cezary", "Wiśniewski", LocalDateTime.of(1992, 7, 8, 0, 0), rentals);
        Client client4 = new Client("Danuta", "Lewandowska", LocalDateTime.of(1988, 11, 30, 0, 0), rentals);

        Client.showExtent();
        FilmRental.showExtent();
        System.out.println(Client.getClientsWithMostRentals());


//        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("data.ser"))) {
//            ObjectPlus.writeExtents(oos);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }
}
