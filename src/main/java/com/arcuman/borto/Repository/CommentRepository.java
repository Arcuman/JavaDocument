package com.arcuman.borto.Repository;

import com.arcuman.borto.models.Comment;
import com.arcuman.borto.models.Document;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends CrudRepository<Comment, Integer> {
    List<Comment> findByDocument(Document document);
}