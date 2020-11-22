package com.arcuman.borto.Repository;

import com.arcuman.borto.models.Comment;
import com.arcuman.borto.models.Document;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
  List<Comment> findAllByDocumentId(Long id);
}
