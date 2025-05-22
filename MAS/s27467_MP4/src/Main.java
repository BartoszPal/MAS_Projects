import java.time.LocalDateTime;

public class Main {
    public static void main(String[] args) {
//        try {
//            FilmCopy copy1 = new FilmCopy(20.0, "DVD-PL-001");
//            FilmCopy copy2 = new FilmCopy(22.0, "DVD-PL-002");  // ❌
//            copy2.setSerialNumber("DVD-PL-001");
//
//        } catch (IllegalArgumentException e) {
//            System.out.println("Błąd (duplikat numeru seryjnego): " + e.getMessage());
//        }
//
//        try {
//            FilmCopy copy3 = new FilmCopy(7.0, "DVD-PL-002");  // ❌
//        } catch (IllegalArgumentException e) {
//            System.out.println("Błąd (cena za niska): " + e.getMessage());
//        }
//
//        try {
//            FilmCopy copy4 = new FilmCopy(10.0, "DVD-PL-003");
//            copy4.setPrice(8.0);  // ❌
//        } catch (IllegalArgumentException e) {
//            System.out.println("Błąd (spadek ceny): " + e.getMessage());
//        }
//
//        try {
//            FilmCopy copy5 = new FilmCopy(10.0, "DVD-PL-004");
//            copy5.setPrice(13.0);  // ❌
//        } catch (IllegalArgumentException e) {
//            System.out.println("Błąd (za duży wzrost ceny): " + e.getMessage());
//        }
//
        try {
            FilmCopy copy6 = new FilmCopy(10.0, "DVD-PL-005");
            copy6.setPrice(12.0);  // OK
            System.out.println("Ustawiono nową cenę dla copy6: " + copy6.getPrice());
        } catch (IllegalArgumentException e) {
            System.out.println("Błąd: " + e.getMessage());
        }

        Director director1 = new Director("John", "Doe", "Smith");
        Director director2 = new Director("Jane", "Alice", "Johnson");

        Film film = new Film("Epic Movie", "alalala sadada sadas dasd as dasd asd asda sdasdasdasd asdas d asd asd as dasd as asdasdasd asd asd asdasdasdasdasdasda", LocalDateTime.now());

        System.out.println("Test: Dodanie reżysera do filmu");
        film.addDirectedBy(director1);
        System.out.println("Directors of the film: " + film.getDirectedBy().size());

        System.out.println("\nTest: Dodanie reżysera, który gra w filmie");
        film.addPlaysBy(director1);
        System.out.println("PlaysBy list size: " + film.getPlayedBy().size());

        System.out.println("\nTest: Próba dodania reżysera, który nie reżyseruje filmu, do PlaysBy");
        try {
            film.addPlaysBy(director2);
        } catch (IllegalArgumentException e) {
            System.out.println("Exception: " + e.getMessage());
        }

        System.out.println("\nTest: Usunięcie reżysera z listy directedBy");
        System.out.println(film.getPlayedBy());
        film.removeDirectedBy(director1);
        System.out.println(film.getPlayedBy());
        System.out.println("Directors of the film after removal: " + film.getDirectedBy().size());

        System.out.println("\nTest: Usunięcie reżysera z listy playsBy");
        film.addDirectedBy(director1);
        film.addPlaysBy(director1);
        film.removePlaysBy(director1);
        System.out.println("PlaysBy list size after removal: " + film.getPlayedBy().size());

        System.out.println("\nTest: Zniszczenie filmu");
        film.destroyFilm();
        System.out.println("Directors of the film after destroy: " + film.getDirectedBy().size());

        System.out.println("\nTest: Zniszczenie reżysera");
        director1.destroyDirector();
        System.out.println("Directors of the film after director is destroyed: " + film.getDirectedBy().size());

        try {
            Studio studio = new Studio("Warner Bros", "Los Angeles");
            // Tworzenie aktorów
            Actor tomHanks = new Actor("Tom", "Hanks", studio);
            Actor tomCruise = new Actor("Tom", "Cruise", studio);

            // Tworzenie filmu
            Film film2 = new Film("Forrest Gump", "asdasd asd asdsaasdsa asd dsa ads asdasd sad sad dsasad sad ads sda dsa sad dsa dsa dsaad ss da s da sdas d asdasdadsdasdasdasd", LocalDateTime.of(1994, 7, 6, 0, 0));

            // Tworzenie ról
            ActorRole role1 = ActorRole.LEAD_ROLE;
            ActorRole role2 = ActorRole.COMEDIC_ROLE;

            // Test {bag} - dodajemy tę samą rolę kilka razy
            System.out.println("Test ograniczenia {bag}:");
            System.out.println("Dodajemy tę samą rolę kilka razy dla tego samego aktora w tym samym filmie");

            // Pierwsze powiązanie
            FilmActorRole role1_1 = new FilmActorRole(tomHanks, film2, role1);
            tomHanks.addRole(role1_1); // todo ???????????????
            tomHanks.addRole(role1_1);

            System.out.println("Liczba ról po pierwszym dodaniu: " + tomHanks.getRoles().size());

            // Drugie powiązanie z tą samą rolą
            FilmActorRole role1_2 = new FilmActorRole(tomHanks, film2, role1);
            System.out.println("Liczba ról po drugim dodaniu: " + tomHanks.getRoles().size());

            // Trzecie powiązanie z inną rolą
            FilmActorRole role2_1 = new FilmActorRole(tomHanks, film2, role2);
            System.out.println("Liczba ról po trzecim dodaniu: " + tomHanks.getRoles().size());

            // Wyświetlenie wszystkich ról
            System.out.println("\nWszystkie role aktora Tom Hanks:");
            for (FilmActorRole role : tomHanks.getRoles()) {
                System.out.println("Film: " + role.getFilm().getTitle() +
                        ", Rola: " + role.getRole().getRoleName() +
                        ", Data utworzenia: " + role.getCreationDate());
            }

        } catch (Exception e) {
            System.err.println("Wystąpił błąd: " + e.getMessage());
            e.printStackTrace();
        }


        try {
            // Tworzenie instancji
            Studio studio1 = new Studio("Warner Bros", "Los Angeles");
            Studio studio2 = new Studio("Universal", "Hollywood");
            Agency agency = new Agency("Talent Agency", "REG123");
            Actor actor = new Actor("John", "Doe", studio1);

            System.out.println("Test 1: Sprawdzanie początkowej relacji z Studio");
            System.out.println("Aktor zatrudniony przez studio: " + (actor.getEmployedBy() != null));
            System.out.println("Aktor reprezentowany przez agencję: " + (actor.getRepresentedBy() != null));
            System.out.println();

            System.out.println("Test 2: Próba ustawienia agencji gdy aktor jest już zatrudniony w studiu");
            try {
                actor.setRepresentedBy(agency);
                System.out.println("BŁĄD: Nie powinno się udać ustawić agencji!");
            } catch (IllegalArgumentException e) {
                System.out.println("OK: Otrzymano oczekiwany wyjątek: " + e.getMessage());
            }
            System.out.println();

            System.out.println("Test 3: Zmiana relacji ze studia na agencję");
            actor.changeFromStudioToAgency(agency);
            System.out.println("Aktor zatrudniony przez studio: " + (actor.getEmployedBy() != null));
            System.out.println("Aktor reprezentowany przez agencję: " + (actor.getRepresentedBy() != null));
            System.out.println();

            System.out.println("Test 4: Próba ustawienia studia gdy aktor jest już reprezentowany przez agencję");
            try {
                actor.setEmployedBy(studio1);
                System.out.println("BŁĄD: Nie powinno się udać ustawić studia!");
            } catch (IllegalArgumentException e) {
                System.out.println("OK: Otrzymano oczekiwany wyjątek: " + e.getMessage());
            }
            System.out.println();

            System.out.println("Test 5: Zmiana relacji z agencji na studio");
            actor.changeFromAgencyToStudio(studio1);
            System.out.println("Aktor zatrudniony przez studio: " + (actor.getEmployedBy() != null));
            System.out.println("Aktor reprezentowany przez agencję: " + (actor.getRepresentedBy() != null));
            System.out.println();

            System.out.println("Test 6: Próba zmiany ze studia na agencję gdy nie ma studia");
            try {
                actor.changeFromStudioToAgency(agency);
                System.out.println("BŁĄD: Nie powinno się udać zmienić na agencję!");
            } catch (IllegalArgumentException e) {
                System.out.println("OK: Otrzymano oczekiwany wyjątek: " + e.getMessage());
            }
            System.out.println();

            System.out.println("Test 7: Próba usunięcia aktora ze studia z poziomu studia");
            try {
                studio1.removeActor(actor);
                System.out.println("BŁĄD: Nie powinno się udać usunąć aktora ze studia!");
            } catch (IllegalArgumentException e) {
                System.out.println("OK: Otrzymano oczekiwany wyjątek: " + e.getMessage());
            }
            System.out.println();

            System.out.println("Test 8: Zmiana studia na inne");
            System.out.println("Aktor zatrudniony przez studio1: " + (actor.getEmployedBy() == studio1));
            System.out.println("Aktor zatrudniony przez studio2: " + (actor.getEmployedBy() == studio2));
            System.out.println("Aktor zatrudniony przez studio: " + (actor.getEmployedBy() != null));
            System.out.println("Aktor reprezentowany przez agencję: " + (actor.getRepresentedBy() != null));
            actor.changeFromAgencyToStudio(studio2);
            System.out.println("=======================================");
            actor.setEmployedBy(studio2);
            System.out.println("Aktor zatrudniony przez studio1: " + (actor.getEmployedBy() == studio1));
            System.out.println("Aktor zatrudniony przez studio2: " + (actor.getEmployedBy() == studio2));
            actor.setEmployedBy(studio1);
            System.out.println("Aktor zatrudniony przez studio1: " + (actor.getEmployedBy() == studio1));
            System.out.println("Aktor zatrudniony przez studio2: " + (actor.getEmployedBy() == studio2));
            System.out.println();
            System.out.println(studio1.getEmploys());
            System.out.println(studio2.getEmploys());
            System.out.println();

            System.out.println("Test 9: Próba usunięcia aktora z agencji z poziomu agencji");
            actor.changeFromStudioToAgency(agency);
            try {
                agency.removeActor(actor);
                System.out.println("BŁĄD: Nie powinno się udać usunąć aktora z agencji!");
            } catch (IllegalArgumentException e) {
                System.out.println("OK: Otrzymano oczekiwany wyjątek: " + e.getMessage());
            }

            System.out.println("================");
            actor.destroy();
            System.out.println("================");

        } catch (Exception e) {
            System.out.println("Wystąpił nieoczekiwany błąd: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
