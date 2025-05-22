package com.example.s27467_mp5.repository;

import com.example.s27467_mp5.model.BluRay;
import com.example.s27467_mp5.model.DVD;
import com.example.s27467_mp5.model.MyStatus;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.validation.ConstraintViolationException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class FilmCopyRepositoryTest {

    @Autowired
    private DVDRepository dvdRepository;

    @Autowired
    private BluRayRepository bluRayRepository;

    @PersistenceContext
    private EntityManager entityManager;

    private DVD dvd;
    private BluRay bluRay;

    @BeforeEach
    void initData() {
        dvd = DVD.builder()
                .status(MyStatus.AVAILABLE)
                .price(10.0)
                .regionCode("EU")
                .hasBonusContent(true)
                .build();

        bluRay = BluRay.builder()
                .status(MyStatus.AVAILABLE)
                .price(15.0)
                .is4K(true)
                .layerCount(2)
                .build();
    }

    @Test
    void testRequiredDependencies() {
        assertNotNull(dvdRepository);
        assertNotNull(bluRayRepository);
    }

    @Test
    void testSaveDVD() {
        dvdRepository.save(dvd);
        entityManager.flush();
        assertTrue(dvd.getId() != null && dvd.getId() > 0);
        long count = dvdRepository.count();
        assertEquals(1, count);
    }

    @Test
    void testSaveInvalidDVD_NullStatus() {
        dvd.setStatus(null);
        assertThrows(ConstraintViolationException.class, () -> {
            dvdRepository.save(dvd);
            entityManager.flush();
        });
    }

    @Test
    void testSaveInvalidDVD_PriceTooLow() {
        dvd.setPrice(4.9);
        assertThrows(ConstraintViolationException.class, () -> {
            dvdRepository.save(dvd);
            entityManager.flush();
        });
    }

    @Test
    void testSaveInvalidDVD_PriceTooHigh() {
        dvd.setPrice(31.0);
        assertThrows(ConstraintViolationException.class, () -> {
            dvdRepository.save(dvd);
            entityManager.flush();
        });
    }

    @Test
    void testSaveInvalidDVD_NullPrice() {
        dvd.setPrice(null);
        assertThrows(ConstraintViolationException.class, () -> {
            dvdRepository.save(dvd);
            entityManager.flush();
        });
    }

    @Test
    void testSaveBluRay() {
        bluRayRepository.save(bluRay);
        entityManager.flush();
        assertNotNull(bluRay.getId());
        assertTrue(bluRay.getId() > 0);
        assertEquals(1, bluRayRepository.count());
    }

    @Test
    void testSaveInvalidBluRay_NullIs4K() {
        bluRay.setIs4K(null);
        assertThrows(ConstraintViolationException.class, () -> {
            bluRayRepository.save(bluRay);
            entityManager.flush();
        });
    }

    @Test
    void testSaveInvalidBluRay_NullLayerCount() {
        bluRay.setLayerCount(null);
        assertThrows(ConstraintViolationException.class, () -> {
            bluRayRepository.save(bluRay);
            entityManager.flush();
        });
    }

    @Test
    void testSaveInvalidBluRay_LayerCountTooLow() {
        bluRay.setLayerCount(0);
        assertThrows(ConstraintViolationException.class, () -> {
            bluRayRepository.save(bluRay);
            entityManager.flush();
        });
    }

    @Test
    void testSaveInvalidBluRay_LayerCountTooHigh() {
        bluRay.setLayerCount(5);
        assertThrows(ConstraintViolationException.class, () -> {
            bluRayRepository.save(bluRay);
            entityManager.flush();
        });
    }

    @Test
    void testSaveInvalidBluRay_NullStatus() {
        bluRay.setStatus(null);
        assertThrows(ConstraintViolationException.class, () -> {
            bluRayRepository.save(bluRay);
            entityManager.flush();
        });
    }

    @Test
    void testSaveInvalidBluRay_PriceTooLow() {
        bluRay.setPrice(4.0);
        assertThrows(ConstraintViolationException.class, () -> {
            bluRayRepository.save(bluRay);
            entityManager.flush();
        });
    }

    @Test
    void testSaveInvalidBluRay_PriceTooHigh() {
        bluRay.setPrice(31.0);
        assertThrows(ConstraintViolationException.class, () -> {
            bluRayRepository.save(bluRay);
            entityManager.flush();
        });
    }
}
