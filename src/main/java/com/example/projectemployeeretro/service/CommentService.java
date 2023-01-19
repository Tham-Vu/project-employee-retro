package com.example.projectemployeeretro.service;

import com.example.projectemployeeretro.dto.CommentDTO;
import com.example.projectemployeeretro.entity.Comment;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.util.List;

public interface CommentService {
    ResponseEntity<?> createComment(CommentDTO commentDTO);

    CommentDTO updateComment(Long id, CommentDTO dto);

    ResponseEntity<?> deleteComment(Long id);
    List<CommentDTO> getAllComment();
    List<CommentDTO> getCommentByOptions(Long projectId, Long evaluatorId, Long evaluateeId, Float point, LocalDate dateCreate);
}
