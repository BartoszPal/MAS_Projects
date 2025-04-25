import Enums.Genre;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Main {
    public static void main(String[] args) throws Exception {
//        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("data.ser"))) {
//            ObjectPlus.readExtents(ois);
//        } catch (IOException | ClassNotFoundException e) {
//            e.printStackTrace();
//        }

        Client client1 = new Client("Jan", "Kowalski", LocalDateTime.of(1990, 5, 15, 0, 0));
        Client client2 = new Client("Anna", "Nowak", LocalDateTime.of(1985, 8, 10, 0, 0));

        ObjectPlus.showExtent(Client.class);

        Film film1 = new Film(
                "Matrix",
                LocalDateTime.of(1999, 3, 31, 0, 0),
                136,
                "en",
                16,
                List.of(Genre.ACTION, Genre.SCIENCE_FICTION),
                3,
                9.99
        );

        Film film2 = new Film(
                "Incepcja",
                148,
                "en",
                13,
                List.of(Genre.ACTION, Genre.THRILLER),
                2,
                12.50
        );

        Director director1 = new Director("Lana", "Wachowski", LocalDateTime.of(1965, 6, 21, 0, 0));
        Director director2 = new Director("Christopher", "Nolan", LocalDateTime.of(1970, 7, 30, 0, 0));
        film1.addDirector(director1);
        film2.addDirector(director2);

        System.out.println("Filmy wyreżyserowane przez " + director1.getName() + ":");
        for (Film film : director1.getDirects()) {
            System.out.println(film);
        }

        ObjectPlus.showExtent(Film.class);
        ObjectPlus.showExtent(Director.class);

        //film1.destroyFilm();

        ObjectPlus.showExtent(Film.class);
//
//        ObjectPlus.showExtent(Rating.class);
        client1.addRating(film1, 9, "Świetny film, klasyk!");
//        client1.addFilmRating(film2, 10, "Mistrzostwo świata!");
        ObjectPlus.showExtent(Rating.class);

        client1.removeRating(client1.getRates().get(0));
        ObjectPlus.showExtent(Rating.class);


//         client1.addFilmRating(film1, 7, "Zmieniam zdanie.");

        System.out.println("Oceny filmu " + film1.getTitle() + ":");
        for (Rating rating : film1.getRates()) { // nie ma zadnych bo usunelismy ocene filmu z klienta
            System.out.println(rating);
        }

        System.out.println("Oceny wystawione przez " + client2.getName() + ":");
        for (Rating rating : client2.getRates()) {
            System.out.println(rating);
        }
        System.out.println("==============");
        ObjectPlus.showExtent(Client.class);
        ObjectPlus.showExtent(Film.class);
        ObjectPlus.showExtent(Director.class);


        System.out.println(film1);
//        film1.removeFilmCopy(film1.getFilmCopies().iterator().next());
        System.out.println(film1);
//        film1.destroyFilm();
        film1.setFilmStorage(new FilmStorage());
//        film1.removeFilmFromFilmStorage();
        System.out.println("=============");
        film1.removeFilmCopy(film1.getFilmCopies().iterator().next());
        film1.removeFilmCopy(film1.getFilmCopies().iterator().next());
        film1.removeFilmCopy(film1.getFilmCopies().iterator().next());
        film1.createNewFilmCopy();
        film1.removeFilmCopy(film1.getFilmCopies().iterator().next());
//        List<FilmCopy> copiesToRemove = new ArrayList<>(film1.getFilmCopies());
//        for (FilmCopy filmCopy : copiesToRemove) {
//            filmCopy.destroyFilmCopy();
//        }
        System.out.println(film1.getFilmStorage().findFilmByTitle("Matrix"));
        ObjectPlus.showExtent(FilmStorage.class);


//        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("data.ser"))) {
//            ObjectPlus.writeExtents(oos);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }
}