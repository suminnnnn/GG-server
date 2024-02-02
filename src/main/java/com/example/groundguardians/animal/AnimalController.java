package com.example.groundguardians.animal;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/animal")
@RequiredArgsConstructor
public class AnimalController {

    private final AnimalService animalService;

    @PostMapping("")
    public ResponseEntity<?> postAnimalCard(@RequestBody @Valid AnimalRequestDto animalRequestDto) {

        Animal animal = animalService.postAnimalCard(animalRequestDto);

        if (animal != null) {
            return new ResponseEntity<>(HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{result}")
    public ResponseEntity<?> getAnimalName(@RequestParam("result") String result) {
        AnimalDto animalDto = animalService.getAnimalName(result);

        return ResponseEntity.ok().body(animalDto);
    }
}
