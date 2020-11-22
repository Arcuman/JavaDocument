package com.arcuman.borto.Repository;

import com.arcuman.borto.models.Document;
import com.arcuman.borto.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DocumentRepository extends JpaRepository<Document, Long> {
  List<Document> findByDescriptionContaining(String description);
}
