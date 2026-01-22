package com.example.Workout.Manager.mapper;

import com.example.Workout.Manager.dto.ExerciseRequest;
import com.example.Workout.Manager.dto.ExerciseResponse;
import com.example.Workout.Manager.entity.Exercise;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ExerciseMapper {

    Exercise toEntity(ExerciseRequest request);

    ExerciseResponse toResponse(Exercise exercise);

    List<ExerciseResponse> toResponseList(List<Exercise> exercises);
}
