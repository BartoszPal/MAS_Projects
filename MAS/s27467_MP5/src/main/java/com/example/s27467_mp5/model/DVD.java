package com.example.s27467_mp5.model;

import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@SuperBuilder
public class DVD extends FilmCopy {

    @NotNull(message = "Region code cannot be null")
    @NotBlank(message = "Region code cannot be blank")
    private String regionCode;

    @NotNull(message = "Bonus content cannot be null")
    private Boolean hasBonusContent;
}