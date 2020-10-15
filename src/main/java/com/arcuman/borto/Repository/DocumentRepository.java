package com.arcuman.borto.Repository;

import com.arcuman.borto.models.Document;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DocumentRepository extends CrudRepository<Document, Integer> {
    List<Document> findByDescription(String description);
}
