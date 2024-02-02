package com.example.groundguardians.animal;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AnimalDto {

    private String name;

    private byte[] card;

    private String result;

    private String url;

    public Animal toEntity(AnimalDto animalDto) {
        return Animal.builder()
                .name(this.name)
                .card(card)
                .result(this.result)
                .url(this.url)
                .build();
    }

    public static AnimalDto fromEntity(Animal animal) {
        return AnimalDto.builder()
                .name(animal.getName())
                .card(animal.getCard())
                .build();
    }


    public static AnimalDto fromRequest(AnimalRequestDto animalRequestDto) {
        return AnimalDto.builder()
                .name(animalRequestDto.getName())
                .result(animalRequestDto.getResult())
                .url(animalRequestDto.getUrl())
                .build();
    }
}
