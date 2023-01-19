package com.example.projectemployeeretro.repository;

import com.example.projectemployeeretro.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
    @Query(value = "select * from comment c " +
            "where ((:project_id is null) or (project_id =:project_id)) " +
            "and ((:evaluator_id is null) or (evaluator_id =:evaluator_id))" +
            "and ((:evaluatee_id is null) or (evaluatee_id =:evaluatee_id))" +
            "and ((:point is null) or (point =:point))" +
            "and ((:date_create is null) or (date_create =:date_create))", nativeQuery = true)
    List<Comment> getCommentByOptions(@Param("project_id")Long projectId, @Param("evaluator_id")Long evaluatorId, @Param("evaluatee_id")Long evaluateeId, @Param("point")Float point, @Param("date_create")LocalDate dateCreate);
}
