package com.example.Workout.Manager.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table (name = "exercises")
public class Exercise {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;

    @Column (nullable = false)
    private String name;

    @Column (nullable = false)
    private String description;

    @Column (nullable = false)
    private String muscleGroup;

    @OneToMany(
            mappedBy = "exercise",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<ExerciseLevel> levels;


}
