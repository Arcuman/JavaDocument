package com.arcuman.borto.services.impl;

import com.arcuman.borto.Repository.CommentRepository;
import com.arcuman.borto.Repository.DocumentRepository;
import com.arcuman.borto.Repository.UserRepository;
import com.arcuman.borto.dto.CommentDTO;
import com.arcuman.borto.dto.DocumentDTO;
import com.arcuman.borto.exception.DocumentNotFoundException;
import com.arcuman.borto.models.Comment;
import com.arcuman.borto.models.Document;
import com.arcuman.borto.services.CommentService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Log4j2
public class CommentServiceImpl implements CommentService {
  private final CommentRepository commentRepository;
  private final UserRepository userRepository;
  private final DocumentRepository documentRepository;

  public CommentServiceImpl(CommentRepository commentRepository, DocumentRepository documentService, UserRepository userRepository) {
    this.commentRepository = commentRepository;
    this.documentRepository = documentService;
    this.userRepository = userRepository;
  }

  @Override
  public List<CommentDTO> getAllFromDocument(Long documentId) {
    return commentRepository.findAllByDocumentId(documentId).stream()
        .map((CommentDTO::fromComment)).collect(Collectors.toList());
  }

  @Override
  public void addNewComment(CommentDTO commentDTO) {
    Comment comment = commentDTO.toComment();
    comment.setUser(userRepository.findByUsername(commentDTO.getUserName()));
    Optional<Document> doc = documentRepository.findById(commentDTO.getDocumentId());
    if (!doc.isPresent()) {
      throw new DocumentNotFoundException("Document with id ${commentDTO.getDocumentId()} not found");
    }
    comment.setDocument(doc.get());
    commentRepository.save(comment);
    log.info("IN addNewComment - comment added successfully");
  }

  @Override
  public void deleteCommentById(Long id) {
    commentRepository.deleteById(id);
    log.info("IN delete - comment with id: ${id} successfully deleted");
  }
}
