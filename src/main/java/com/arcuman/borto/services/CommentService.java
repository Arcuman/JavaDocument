package com.arcuman.borto.services;

import com.arcuman.borto.dto.CommentDTO;
import com.arcuman.borto.models.Comment;
import com.arcuman.borto.models.Document;

import java.util.List;

public interface CommentService {

  List<CommentDTO> getAllFromDocument(Long documentId);

  void addNewComment(CommentDTO commentDTO);

  void deleteCommentById(Long id);
}
