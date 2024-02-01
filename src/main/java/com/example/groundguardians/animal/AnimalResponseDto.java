package com.example.groundguardians.animal;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class AnimalResponseDto {

    private Long id;

    private String name;

    public static AnimalResponseDto fromEntity(Animal animal) {
        return AnimalResponseDto.builder()
                .id(animal.getId())
                .name(animal.getName())
                .build();
    }
}
