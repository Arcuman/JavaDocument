package com.arcuman.borto.dto;

import com.arcuman.borto.models.Document;
import com.arcuman.borto.models.User;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class DocumentDTO {

  private Long id;

  private String title;

  private String description;

  private String fileName;

  public DocumentDTO() {}

  public DocumentDTO(String title, String description, String fileName) {
    this.title = title;
    this.description = description;
    this.fileName = fileName;
  }

  public Document toDocument(){
    Document document = new Document();
    document.setId(id);
    document.setTitle(title);
    document.setDescription(description);
    document.setFilename(fileName);
    return document;
  }

  public static DocumentDTO fromDocument(Document document) {
    DocumentDTO documentDTO = new DocumentDTO();
    documentDTO.setId(document.getId());
    documentDTO.setTitle(document.getTitle());
    documentDTO.setDescription(document.getDescription());
    documentDTO.setFileName(document.getFilename());
    return documentDTO;
  }
}