package com.example.projectemployeeretro.service;

import com.example.projectemployeeretro.dto.CommentDTO;
import com.example.projectemployeeretro.entity.Comment;
import com.example.projectemployeeretro.entity.Employee;
import com.example.projectemployeeretro.entity.EmployeeProject;
import com.example.projectemployeeretro.entity.Project;
import com.example.projectemployeeretro.exception.EmployeeNotExist;
import com.example.projectemployeeretro.exception.EmployeeNotExistInProject;
import com.example.projectemployeeretro.exception.ProjectNotExist;
import com.example.projectemployeeretro.repository.CommentRepository;
import com.example.projectemployeeretro.repository.EmployeeProjectRepo;
import com.example.projectemployeeretro.repository.EmployeeRepository;
import com.example.projectemployeeretro.repository.ProjectRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class CommentServiceImpl implements CommentService{
    @Autowired
    ProjectRepository projectRepository;
    @Autowired
    EmployeeProjectRepo employeeProjectRepo;
    @Autowired
    EmployeeRepository employeeRepository;
    @Autowired
    ModelMapper mapper;
    @Autowired
    CommentRepository commentRepository;
    @Override
    public ResponseEntity<?> createComment(CommentDTO dto) {
        try {
            checkValidation(dto.getProjectId(), dto.getEvaluatorId(), dto.getEvaluateeId());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
//        Comment comment = mapper.map(dto, Comment.class);
        Comment comment = new Comment();
        comment.setComment(dto.getComment());
        comment.setPoint(dto.getPoint());
        comment.setDateCreate(LocalDate.now());
        comment.setEvaluatee(employeeRepository.findById(dto.getEvaluateeId()).get());
        comment.setEvaluator(employeeRepository.findById(dto.getEvaluatorId()).get());
        comment.setProject(projectRepository.findById(dto.getProjectId()).get());
        return ResponseEntity.status(HttpStatus.CREATED).body(commentRepository.save(comment));
    }

    @Override
    public CommentDTO updateComment(Long id, CommentDTO dto) {
        Comment updateComment = commentRepository.findById(id)
                .map(comment -> {
                    try {
                        checkValidation(dto.getProjectId(), dto.getEvaluatorId(),dto.getEvaluateeId());
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                    comment.setComment(dto.getComment());
                    comment.setPoint(dto.getPoint());
                    comment.setDateCreate(dto.getDateCreate());
                    comment.setProject(projectRepository.findById(dto.getProjectId()).orElse(null));
                    comment.setEvaluator(employeeRepository.findById(dto.getEvaluatorId()).orElse(null));
                    comment.setEvaluatee(employeeRepository.findById(dto.getEvaluateeId()).orElse(null));
                    return commentRepository.save(comment);
                }).orElseGet(() -> {
                    Comment comment = mapper.map(dto, Comment.class);
                    comment.setId(id);
                    return commentRepository.save(comment);
                });
        return mapper.map(commentRepository.save(updateComment), CommentDTO.class);
    }

    @Override
    public ResponseEntity<?> deleteComment(Long id) {
        boolean exist = commentRepository.existsById(id);
        if (!exist){
            throw new RuntimeException("Not exist");
        }
        commentRepository.deleteById(id);
        return ResponseEntity.status(HttpStatus.OK).body("Delete comment successfully");
    }

    @Override
    public List<CommentDTO> getAllComment() {
        List<Comment> list = commentRepository.findAll();
        List<CommentDTO> dtos = list.stream().map(comment -> mapper.map(comment, CommentDTO.class)).collect(Collectors.toList());
        return dtos;
    }

    @Override
    public List<CommentDTO> getCommentByOptions(Long projectId, Long evaluatorId, Long evaluateeId, Float point, LocalDate dateCreate) {
        List<Comment> list = commentRepository.getCommentByOptions(projectId, evaluatorId, evaluateeId, point, dateCreate);
//        List<CommentDTO> dtos = list.stream().map(comment -> mapper.map(comment, CommentDTO.class)).collect(Collectors.toList());
        List<CommentDTO> dtos = new ArrayList<>();
        for (Comment c:list) {
            dtos.add(mapper.map(c, CommentDTO.class));
        }
        return dtos;
    }

    public void checkValidation(Long projectId,Long evaluator_id, Long evaluatee_id) throws Exception {
        if(!projectRepository.existsById(projectId)){
            if (!checkEmployeeProjectValidation(projectId, evaluatee_id)){
                throw new EmployeeNotExistInProject("Employee does not exist in project " + projectId);
            }
            throw new ProjectNotExist("Project does not exist with id " + projectId);
        }
        if (!employeeRepository.existsById(evaluator_id)){
            if (!checkProjectEmployeeValidation(projectId, evaluator_id)){
                throw new EmployeeNotExistInProject("Employee can not evaluate project " + projectId);
            }
            throw new EmployeeNotExist("Employee does not exist with id " + evaluator_id);
        }
        if(evaluator_id == evaluatee_id){
            throw new Exception("Can not evaluate yourself");
        }
    }
    public boolean checkEmployeeProjectValidation(Long projectId,Long employeeId){
        List<EmployeeProject> employeeProject = employeeProjectRepo.findByIdProjectId(projectId);
        boolean exist = false;
        for (EmployeeProject ep:employeeProject) {
            if (ep.getEmployee().getId().equals(employeeId)){
                exist = true;
            }
        }
        return exist;
    }
    public boolean checkProjectEmployeeValidation(Long projectId,Long employeeId){
        List<EmployeeProject> employeeProject = employeeProjectRepo.findByIdProjectId(employeeId);
        boolean exist = false;
        for (EmployeeProject ep:employeeProject) {
            if (ep.getProject().getId().equals(projectId)){
                exist = true;
            }
        }
        return exist;
    }
}
