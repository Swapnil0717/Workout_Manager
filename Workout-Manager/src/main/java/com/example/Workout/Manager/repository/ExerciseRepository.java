package com.example.Workout.Manager.repository;

import com.example.Workout.Manager.entity.Exercise;
import com.example.Workout.Manager.entity.Level;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ExerciseRepository extends JpaRepository<Exercise, Long> {

    // Get exercises by level
    @Query("""
        SELECT DISTINCT e FROM Exercise e
        JOIN e.levels l
        WHERE l.level = :level
    """)
    List<Exercise> findByLevel(@Param("level") Level level);

    // Search & filter exercises
    @Query("""
        SELECT DISTINCT e FROM Exercise e
        JOIN e.levels l
        WHERE (:level IS NULL OR l.level = :level)
          AND (:search IS NULL OR LOWER(e.name) LIKE LOWER(CONCAT('%', :search, '%'))
               OR LOWER(e.muscleGroup) LIKE LOWER(CONCAT('%', :search, '%')))
    """)
    List<Exercise> searchAndFilter(
            @Param("level") Level level,
            @Param("search") String search);
}
