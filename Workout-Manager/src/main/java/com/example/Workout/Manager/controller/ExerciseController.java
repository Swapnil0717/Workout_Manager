package com.example.Workout.Manager.controller;

import com.example.Workout.Manager.dto.ExerciseRequest;
import com.example.Workout.Manager.dto.ExerciseResponse;
import com.example.Workout.Manager.entity.Exercise;
import com.example.Workout.Manager.service.ExerciseService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin/exercise")
@RequiredArgsConstructor
@PreAuthorize("hasRole('ADMIN')")
public class ExerciseController {

    @Autowired
    private final ExerciseService exerciseService;

    @PostMapping
    public ResponseEntity<?> createExercise (@RequestBody ExerciseRequest request){
        return ResponseEntity.ok(exerciseService.createExercise(request));

    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ExerciseResponse> updateExercise(
            @PathVariable Long id,
            @RequestBody ExerciseRequest request) {

        return ResponseEntity.ok(exerciseService.updateExercise(id, request));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> deleteExercise(@PathVariable Long id) {

        exerciseService.deleteExercise(id);
        return ResponseEntity.ok("Exercise deleted successfully");
    }



}
