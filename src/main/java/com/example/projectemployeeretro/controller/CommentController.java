package com.example.projectemployeeretro.controller;

import com.example.projectemployeeretro.dto.CommentDTO;
import com.example.projectemployeeretro.entity.Comment;
import com.example.projectemployeeretro.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/comments")
public class CommentController {
    @Autowired
    private CommentService service;
    @PostMapping("/insert")
    public ResponseEntity<?> insertComments(@RequestBody CommentDTO dto){
        return ResponseEntity.status(HttpStatus.CREATED).body(service.createComment(dto));
    }
    @GetMapping
    public ResponseEntity<?> getCommentByOptions(@RequestParam(required = false) Long projectId,@RequestParam(required = false) Long evaluatorId,@RequestParam(required = false) Long evaluateeId, @RequestParam(required = false) Float point, @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date){
        if (projectId == null && evaluatorId == null && evaluateeId == null && point == null && date == null){
            return ResponseEntity.status(HttpStatus.OK).body(service.getAllComment());
        }
        return ResponseEntity.status(HttpStatus.OK).body(service.getCommentByOptions(projectId, evaluatorId, evaluateeId, point, date));
    }
}
