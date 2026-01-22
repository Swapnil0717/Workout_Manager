package com.example.Workout.Manager.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table (name = "exercise_level")
public class ExerciseLevel {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private Level level;

    private int sets;
    private int reps;
    private int duration; // seconds

    @ManyToOne
    @JoinColumn(name = "exercise_id")
    private Exercise exercise;


}
