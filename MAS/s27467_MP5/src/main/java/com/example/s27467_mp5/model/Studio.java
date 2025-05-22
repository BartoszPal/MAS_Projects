package com.example.s27467_mp5.model;

import jakarta.persistence.*;
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
public class Studio {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull(message = "Name cannot be null")
    @NotBlank(message = "Name cannot be blank")
    @Size(min = 2, max = 511)
    private String name;

    @NotNull(message = "Location cannot be null")
    @NotBlank(message = "Location cannot be empty")
    @Size(min = 2, max = 511)
    private String location;

    @Builder.Default
    @OneToMany(mappedBy = "employedBy")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Set<Client> employs = new HashSet<>();
}
