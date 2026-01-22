package com.example.Workout.Manager.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
public class UserExercise {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private User user;

    @ManyToOne
    private Exercise exercise;

    @Enumerated(EnumType.STRING)
    private Status status;

    private LocalDateTime completedAt;
}
