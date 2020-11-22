package com.arcuman.borto.rest;


import com.arcuman.borto.dto.CommentDTO;
import com.arcuman.borto.dto.DocumentDTO;
import com.arcuman.borto.dto.RegisterDTO;
import com.arcuman.borto.models.Comment;
import com.arcuman.borto.models.Document;
import com.arcuman.borto.services.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/comment/")
public class CommentRestControllerV1 {
  private final CommentService commentService;

  public CommentRestControllerV1(CommentService commentService) {
    this.commentService = commentService;
  }

  @GetMapping(value = "{id}")
  public ResponseEntity<List<CommentDTO>> getCommentsById(@PathVariable(name = "id") Long id){
    List<CommentDTO> comments = commentService.getAllFromDocument(id);
    return new ResponseEntity<>(comments, HttpStatus.OK);
  }
  @PostMapping(value = "/addComment")
  public ResponseEntity addComment(
      Principal principal,
      @RequestBody CommentDTO commentDTO
  ){
    commentDTO.setUserName(principal.getName());
    commentService.addNewComment(commentDTO);
    return ResponseEntity.ok("Comment add successfully");
  }
}
