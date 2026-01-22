package com.example.Workout.Manager.service;

import com.example.Workout.Manager.dto.ExerciseResponse;
import com.example.Workout.Manager.entity.*;
import com.example.Workout.Manager.mapper.ExerciseMapper;
import com.example.Workout.Manager.repository.ExerciseRepository;
import com.example.Workout.Manager.repository.UserExerciseRepository;
import com.example.Workout.Manager.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserExerciseService {

    private final ExerciseRepository exerciseRepository;
    private final UserExerciseRepository userExerciseRepository;
    private final UserRepository userRepository;
    private final ExerciseMapper exerciseMapper;

    // Get exercises by level
    public List<ExerciseResponse> getExercisesByLevel(Level level, String email) {
        User user = userRepository.findByEmail(email).orElseThrow();

        List<Exercise> exercises = exerciseRepository.findByLevel(level);

        return mapExercisesWithStatus(exercises, user);
    }

    // Search and filter exercises
    public List<ExerciseResponse> searchExercises(String search, Level level, String email) {
        User user = userRepository.findByEmail(email).orElseThrow();

        List<Exercise> exercises = exerciseRepository.searchAndFilter(level, search);

        return mapExercisesWithStatus(exercises, user);
    }

    // Mark exercise as COMPLETED or PENDING
    public void markExercise(Long exerciseId, Status status, String email) {
        User user = userRepository.findByEmail(email).orElseThrow();

        Exercise exercise = exerciseRepository.findById(exerciseId).orElseThrow();

        UserExercise userExercise = userExerciseRepository
                .findByUserAndExercise(user, exercise)
                .orElse(new UserExercise());

        userExercise.setUser(user);
        userExercise.setExercise(exercise);
        userExercise.setStatus(status);

        if (status == Status.COMPLETED) {
            userExercise.setCompletedAt(LocalDateTime.now());
        } else {
            userExercise.setCompletedAt(null);
        }

        userExerciseRepository.save(userExercise);
    }

    // Helper: map exercises to DTOs with status & completedAt
    private List<ExerciseResponse> mapExercisesWithStatus(List<Exercise> exercises, User user) {
        return exercises.stream().map(exercise -> {
            ExerciseResponse response = exerciseMapper.toResponse(exercise);

            UserExercise userExercise = userExerciseRepository
                    .findByUserAndExercise(user, exercise)
                    .orElse(null);

            if (userExercise != null) {
                response.setStatus(userExercise.getStatus());
                response.setCompletedAt(userExercise.getCompletedAt());
            } else {
                response.setStatus(Status.PENDING);
                response.setCompletedAt(null);
            }

            return response;
        }).toList();
    }
}
