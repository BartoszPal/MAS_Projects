package Wieloaspektowe;

import Enums.Condition;
import Enums.MyStatus;

public class Main {
    public static void main(String[] args) {
        DVD dvd = new DVD(MyStatus.AVAILABLE, Condition.NEW, 15.0, "2 years warranty", 2, true);
        BluRay bluRay = new BluRay(MyStatus.AVAILABLE, Condition.USED, 12.0, 3, true, 2);

        bluRay.incrementTimesRented();
        System.out.println(bluRay.getTimesRented());
        dvd.activateTrialPeriod();
        System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++");

        System.out.println("Testing DVD");
        dvd.rentFilm();
        System.out.println("===================");

        System.out.println("Testing BluRay");
        bluRay.rentFilm();
        System.out.println("===================");

        System.out.println("Destroying all copies...");
        bluRay.destroy();
        dvd.destroy();
    }
}
