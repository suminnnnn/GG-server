package com.example.groundguardians.animal;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AnimalDto {
    private String name;

    private String card;

    private String result;

    private String url;

    public Animal toEntity(AnimalDto animalDto) {
        return Animal.builder()
                .name(this.name)
                .card(this.card)
                .result(this.result)
                .url(this.url)
                .build();
    }


    public static AnimalDto fromEntity(Animal animal) {
        return AnimalDto.builder()
                .name(animal.getName())
                .card(animal.getCard())
                .result(animal.getResult())
                .url(animal.getUrl())
                .build();
    }
}
