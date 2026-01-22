package com.example.Workout.Manager.controller;

import com.example.Workout.Manager.dto.ExerciseResponse;
import com.example.Workout.Manager.entity.Level;
import com.example.Workout.Manager.entity.Status;
import com.example.Workout.Manager.service.UserExerciseService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserExerciseController {

    private final UserExerciseService service;

    // Get exercises by level
    @GetMapping("/exercises")
    public List<ExerciseResponse> getExercises(
            @RequestParam Level level,
            Authentication auth) {

        return service.getExercisesByLevel(level, auth.getName());
    }

    // Search & filter exercises
    @GetMapping("/exercises/search")
    public List<ExerciseResponse> searchExercises(
            @RequestParam(required = false) String search,
            @RequestParam(required = false) Level level,
            Authentication auth) {

        return service.searchExercises(search, level, auth.getName());
    }

    // Mark exercise as COMPLETED / PENDING
    @PostMapping("/exercises/{id}/status")
    public String markExercise(
            @PathVariable Long id,
            @RequestParam Status status,
            Authentication auth) {

        service.markExercise(id, status, auth.getName());
        return "Status updated";
    }
}
