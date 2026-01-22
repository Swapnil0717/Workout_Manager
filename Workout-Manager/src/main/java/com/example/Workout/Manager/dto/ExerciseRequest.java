package com.example.Workout.Manager.dto;

import lombok.Data;
import java.util.List;

@Data
public class ExerciseRequest {

    private String name;
    private String description;
    private String muscleGroup;
    private List<ExerciseLevelRequest> levels;
}
