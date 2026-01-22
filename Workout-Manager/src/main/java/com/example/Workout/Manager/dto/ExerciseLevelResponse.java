package com.example.Workout.Manager.dto;

import com.example.Workout.Manager.entity.Level;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExerciseLevelResponse {

    private Level level;
    private int sets;
    private int reps;
    private int duration;
}
