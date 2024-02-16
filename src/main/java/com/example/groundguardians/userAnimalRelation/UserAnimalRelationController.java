package com.example.groundguardians.userAnimalRelation;

import com.example.groundguardians.animal.AnimalService;
import com.example.groundguardians.jwt.JwtRequired;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@Tag(name = "마이페이지 API")
@RestController
@RequestMapping("/mypage")
@RequiredArgsConstructor
@JwtRequired
public class UserAnimalRelationController {

    private final UserAnimalRelationService userAnimalRelationService;

    @PostMapping("/{animal_id}")
    public ResponseEntity<?> addCardToMyPage(@PathVariable("animal_id")long animalId, Principal principal){
        long userId = Long.parseLong(principal.getName());

        return ResponseEntity.ok().body(userAnimalRelationService.addAnimalCardToUser(userId, animalId));
    }

    @GetMapping("")
    public ResponseEntity<?> getAllAnimalCard(Principal principal){
        long userId = Long.parseLong(principal.getName());

        return ResponseEntity.ok().body(userAnimalRelationService.getMyPageInfo(userId));
    }

}
