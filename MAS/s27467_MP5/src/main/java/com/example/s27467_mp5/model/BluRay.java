package com.example.s27467_mp5.model;

import jakarta.persistence.Entity;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
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
public class BluRay extends FilmCopy {

    @NotNull(message = "4K information cannot be null")
    private Boolean is4K;

    @NotNull(message = "Layer count cannot be null")
    @Min(value = 1, message = "Layer count must be at least 1")
    @Max(value = 4, message = "Layer count must be at most 4")
    private Integer layerCount;
}
