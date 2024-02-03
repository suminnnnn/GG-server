package com.example.groundguardians.userAnimalRelation;

import com.example.groundguardians.animal.Animal;
import com.example.groundguardians.user.User;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "user_animal_relation")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserAnimalRelation{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_animal_id")
    private long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "animal_id", nullable = false)
    private Animal animal;

    @Builder
    public UserAnimalRelation(User user, Animal animal){
        this.user = user;
        this.animal = animal;
    }
}
