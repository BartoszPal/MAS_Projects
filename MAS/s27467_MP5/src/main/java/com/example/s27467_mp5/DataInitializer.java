package com.example.s27467_mp5;

import com.example.s27467_mp5.model.*;
import com.example.s27467_mp5.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Component
@RequiredArgsConstructor
public class DataInitializer {

    private final StudioRepository studioRepository;
    private final ClientRepository clientRepository;
    private final FilmRepository filmRepository;
    private final FilmCopyRepository filmCopyRepository;
    private final DVDRepository dvdRepository;
    private final BluRayRepository bluRayRepository;
    private final RatingRepository ratingRepository;

    @EventListener
    public void atStart(ContextRefreshedEvent event) {
        clientRepository.deleteAll();
        studioRepository.deleteAll();
        filmRepository.deleteAll();
        dvdRepository.deleteAll();
        bluRayRepository.deleteAll();
        ratingRepository.deleteAll();

        Studio s1 = Studio.builder().name("Warner Bros").location("Los Angeles").build();
        Studio s2 = Studio.builder().name("Ghibli").location("Tokyo").build();
        Studio s3 = Studio.builder().name("Legendary Pictures").location("Burbank").build();
        studioRepository.saveAll(List.of(s1, s2, s3));

        Client c1 = Client.builder().name("John").secondName(null).surname("Smith").birth(LocalDate.of(1990, 5, 20)).employedBy(s1).build();
        Client c2 = Client.builder().name("Anna").secondName(null).surname("Kowalska").birth(LocalDate.of(1995, 3, 15)).employedBy(s2).build();
        Client c3 = Client.builder().name("Tom").secondName("Jason").surname("Brown").birth(LocalDate.of(1985, 8, 10)).employedBy(null).build();
        clientRepository.saveAll(List.of(c1, c2, c3));

        Film f1 = Film.builder().title("The Matrix").releaseYear(1999).language("English").genre(Set.of(Genre.ACTION, Genre.SCIENCE_FICTION)).build();
        Film f2 = Film.builder().title("Spirited Away").releaseYear(2001).language("Japanese").genre(Set.of(Genre.FAMILY, Genre.ANIMATION)).build();
        Film f3 = Film.builder().title("Inception").releaseYear(2010).language("English").genre(Set.of(Genre.THRILLER, Genre.SCIENCE_FICTION)).build();
        filmRepository.saveAll(List.of(f1, f2, f3));

        DVD dvd1 = DVD.builder().status(MyStatus.AVAILABLE).price(10.0).film(f1).regionCode("3166-1").hasBonusContent(true).build();
        DVD dvd2 = DVD.builder().status(MyStatus.RENTED).price(8.0).film(f2).regionCode("3166-1").hasBonusContent(false).build();
        DVD dvd3 = DVD.builder().status(MyStatus.DAMAGED).price(6.0).film(f3).regionCode("3166-1").hasBonusContent(false).build();
        dvdRepository.saveAll(List.of(dvd1, dvd2, dvd3));

        BluRay br1 = BluRay.builder().status(MyStatus.AVAILABLE).price(15.0).film(f1).is4K(true).layerCount(2).build();
        BluRay br2 = BluRay.builder().status(MyStatus.AVAILABLE).price(20.0).film(f2).is4K(false).layerCount(1).build();
        BluRay br3 = BluRay.builder().status(MyStatus.AVAILABLE).price(12.5).film(f3).is4K(true).layerCount(3).build();
        bluRayRepository.saveAll(List.of(br1, br2, br3));

        Rating r1 = Rating.builder().score(9).comment("Great movie").dateRated(LocalDate.now()).client(c1).film(f1).build();
        Rating r2 = Rating.builder().score(10).comment(null).dateRated(LocalDate.now().minusDays(1)).client(c2).film(f2).build();
        Rating r3 = Rating.builder().score(7).comment(null).dateRated(LocalDate.now().minusDays(2)).client(c3).film(f3).build();
        ratingRepository.saveAll(List.of(r1, r2, r3));

        System.out.println(bluRayRepository.findByIdWithConnections(br1.getId()));
        System.out.println(dvdRepository.findByIdWithConnections(dvd1.getId()));
        System.out.println(filmCopyRepository.findByIdWithConnections(dvd1.getId()));
        System.out.println(filmRepository.findByIdWithConnections(f1.getId()));
        System.out.println(clientRepository.findByIdWithConnections(c1.getId()));
        System.out.println(studioRepository.findByIdWithConnections(s1.getId()));

        System.out.println("Database initialized.");
    }
}