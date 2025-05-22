package com.example.s27467_mp5;

import com.example.s27467_mp5.model.BluRay;
import com.example.s27467_mp5.repository.BluRayRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DatabaseTest implements CommandLineRunner {

    private final BluRayRepository bluRayRepository;

    @Override
    public void run(String... args) {
        var iterator = bluRayRepository.findAll().iterator();
        if (iterator.hasNext()) {
            BluRay br = iterator.next();

            bluRayRepository.findByIdWithConnections(br.getId()).ifPresent(b -> {
                System.out.println("BluRay: " + b);
                System.out.println("Film: " + b.getFilm());
            });

            for(BluRay bluRay : bluRayRepository.findAllWithoutConnections()) {
                System.out.println("BluRay: " + bluRay);
            }
        }
    }
}