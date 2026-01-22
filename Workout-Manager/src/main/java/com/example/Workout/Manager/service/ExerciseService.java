package com.example.Workout.Manager.service;

import com.example.Workout.Manager.dto.ExerciseRequest;
import com.example.Workout.Manager.dto.ExerciseResponse;
import com.example.Workout.Manager.entity.Exercise;
import com.example.Workout.Manager.entity.ExerciseLevel;
import com.example.Workout.Manager.mapper.ExerciseMapper;
import com.example.Workout.Manager.repository.ExerciseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ExerciseService {

    private final ExerciseRepository exerciseRepository;
    private final ExerciseMapper exerciseMapper;

    public ExerciseResponse createExercise(ExerciseRequest request) {

        Exercise exercise = exerciseMapper.toEntity(request);

        exercise.getLevels()
                .forEach(level -> level.setExercise(exercise));

        Exercise saved = exerciseRepository.save(exercise);

        return exerciseMapper.toResponse(saved);
    }

    public ExerciseResponse updateExercise(Long id, ExerciseRequest request) {

        Exercise existing = exerciseRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Exercise not found"));


        existing.setName(request.getName());
        existing.setDescription(request.getDescription());
        existing.setMuscleGroup(request.getMuscleGroup());

        existing.getLevels().clear();

        request.getLevels().forEach(req -> {
            ExerciseLevel level = new ExerciseLevel();
            level.setLevel(req.getLevel());
            level.setSets(req.getSets());
            level.setReps(req.getReps());
            level.setDuration(req.getDuration());
            level.setExercise(existing);
            existing.getLevels().add(level);
        });

        Exercise saved = exerciseRepository.save(existing);
        return exerciseMapper.toResponse(saved);
    }

    public void deleteExercise(Long id) {

        Exercise exercise = exerciseRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Exercise not found"));

        exerciseRepository.delete(exercise);
    }

}


