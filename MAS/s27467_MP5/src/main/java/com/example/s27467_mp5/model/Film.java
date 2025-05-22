package com.example.s27467_mp5.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class Film {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank(message = "Title cannot be blank")
    @Size(min = 2, max = 511)
    private String title;

    @NotNull(message = "Release year cannot be null")
    @Min(1900)
    private Integer releaseYear;

    @NotBlank(message = "Language cannot be blank")
    @Size(min = 2, max = 511)
    private String language;

    @Builder.Default
    @ElementCollection(targetClass = Genre.class)
    @CollectionTable(name = "film_genres", joinColumns = @JoinColumn(name = "film_id"))
    @Enumerated(EnumType.STRING)
    @Column(name = "genre")
    @Size(min = 1, message = "Genre list cannot be empty")
    @NotNull(message = "Genre list cannot be null")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Set<Genre> genre = new HashSet<>();

    @Builder.Default
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @OneToMany(mappedBy = "film", cascade = CascadeType.REMOVE)
    private Set<Rating> ratings = new HashSet<>();

    @Builder.Default
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @OneToMany(mappedBy = "film", cascade = CascadeType.REMOVE)
    private Set<FilmCopy> filmCopies = new HashSet<>();
}
