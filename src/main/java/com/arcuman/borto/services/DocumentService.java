package com.arcuman.borto.services;

import com.arcuman.borto.dto.DocumentDTO;
import com.arcuman.borto.models.Document;
import com.arcuman.borto.models.User;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface DocumentService {
  List<DocumentDTO> getAll();

  List<DocumentDTO> getAllByDescription(String description);

  void addNewDoucment(DocumentDTO documentDTO,User user);

  void deleteDoucmentById(Long id, String username);

  Resource loadFileAsResource(String fileName);

  String storeFile(MultipartFile file);

  Document findById(Long id);
}
