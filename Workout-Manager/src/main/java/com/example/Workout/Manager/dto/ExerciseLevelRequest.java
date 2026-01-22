package com.example.Workout.Manager.dto;

import com.example.Workout.Manager.entity.Level;
import lombok.Data;


@Data
public class ExerciseLevelRequest {

    private Level level;
    private int sets;
    private int reps;
    private int duration;
}
