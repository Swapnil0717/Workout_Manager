package com.example.Workout.Manager.dto;

import com.example.Workout.Manager.entity.Status;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExerciseResponse {

    private Long id;
    private String name;
    private String description;
    private String muscleGroup;
    private List<ExerciseLevelResponse> levels;
    private Status status;    // COMPLETED / PENDING
    private LocalDateTime completedAt;
}
