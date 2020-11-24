package com.arcuman.borto.rest;


import com.arcuman.borto.dto.CommentDTO;
import com.arcuman.borto.dto.DocumentDTO;
import com.arcuman.borto.dto.RegisterDTO;
import com.arcuman.borto.models.Comment;
import com.arcuman.borto.models.Document;
import com.arcuman.borto.services.CommentService;
import com.arcuman.borto.services.DocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/documents/{id}/comments/")
public class CommentRestControllerV1 {
  private final CommentService commentService;
  private final DocumentService documentService;
  public CommentRestControllerV1(CommentService commentService,DocumentService documentService) {
    this.commentService = commentService;
    this.documentService = documentService;
  }

  @PostMapping(value = "/add")
  public ResponseEntity addComment(
      Principal principal,
      @PathVariable(name = "id") Long id,
      @RequestBody CommentDTO commentDTO
  ){
    commentService.addNewComment(commentDTO,id,principal.getName());
    return ResponseEntity.ok("Comment add successfully");
  }
  @DeleteMapping(value = "/delete")
  public ResponseEntity deleteComment(
      Principal principal,
      @Param(value = "commentId") Long commentId
  ){
    commentService.deleteComment(commentId, principal.getName());
    return ResponseEntity.ok("Comment delete successfully");
  }
}
