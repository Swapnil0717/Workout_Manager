package com.example.Workout.Manager.repository;

import com.example.Workout.Manager.entity.Exercise;
import com.example.Workout.Manager.entity.User;
import com.example.Workout.Manager.entity.UserExercise;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserExerciseRepository extends JpaRepository<UserExercise, Long> {

    Optional<UserExercise> findByUserAndExercise(User user, Exercise exercise);

    List<UserExercise> findByUser(User user);
}
