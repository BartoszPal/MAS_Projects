package com.example.s27467_mp5.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull(message = "Name cannot be null")
    @NotBlank(message = "Name cannot be blank")
    @Size(min = 2, max = 511)
    private String name;

    @Size(min = 2, max = 511)
    @Pattern(regexp = "\\S{2,511}", message = "Second name must be 2 to 511 non-whitespace characters")
    private String secondName;

    @NotNull(message = "Name cannot be null")
    @NotBlank(message = "Surname cannot be blank")
    @Size(min = 2, max = 511)
    private String surname;

    @NotNull(message = "Birth date cannot be null")
    @Past(message = "Birth date must be in the past")
    private LocalDate birth;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "studio_id", nullable = true)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Studio employedBy;

    @Builder.Default
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @OneToMany(mappedBy = "client", cascade = CascadeType.REMOVE)
    private Set<Rating> ratings = new HashSet<>();
}
