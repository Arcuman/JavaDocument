package com.arcuman.borto.rest;

import com.arcuman.borto.dto.DocumentDTO;
import com.arcuman.borto.dto.UploadFileResponse;
import com.arcuman.borto.models.Document;
import com.arcuman.borto.models.User;
import com.arcuman.borto.services.CommentService;
import com.arcuman.borto.services.DocumentService;
import com.arcuman.borto.services.UserService;
import lombok.extern.log4j.Log4j2;
import org.springframework.core.io.Resource;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.security.Principal;
import java.util.List;

@Log4j2
@RestController
@RequestMapping(value = "/api/v1/documents/")
public class DocumentRestControllerV1 {

  private final DocumentService documentService;
  private final UserService userService;
  private final CommentService commentService;

  public DocumentRestControllerV1(
      DocumentService documentService, UserService userService, CommentService commentService) {
    this.documentService = documentService;
    this.userService = userService;
    this.commentService = commentService;
  }


  @GetMapping(value = "{id}")
  public ResponseEntity<DocumentDTO> getDocumentById(@PathVariable(name = "id") Long id) {
    Document document = documentService.findById(id);

    if (document == null) {
      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    DocumentDTO result = DocumentDTO.fromDocument(document);
    result.setComments(commentService.getAllFromDocument(id));
    return new ResponseEntity<>(result, HttpStatus.OK);
  }

  @GetMapping(value = "")
  public ResponseEntity<List<DocumentDTO>> getAllDocument() {
    List<DocumentDTO> result = documentService.getAll();
    return new ResponseEntity<>(result, HttpStatus.OK);
  }

  @PostMapping("/add")
  public UploadFileResponse uploadFile(
      Principal principal,
      @RequestParam("file") MultipartFile file,
      @RequestParam("title") String title,
      @RequestParam("description") String description)
      throws IOException {
    String fileName = documentService.storeFile(file);

    String fileDownloadUri =
        ServletUriComponentsBuilder.fromCurrentContextPath()
            .path("/downloadFile/")
            .path(fileName)
            .toUriString();
    User user = userService.findByUsername(principal.getName());
    documentService.addNewDoucment(new DocumentDTO(title, description, fileName), user);
    return new UploadFileResponse(fileName, fileDownloadUri, file.getContentType(), file.getSize());
  }

  @GetMapping("/downloadFile/{fileName:.+}")
  public ResponseEntity<Resource> downloadFile(
      @PathVariable String fileName, HttpServletRequest request) {
    // Load file as Resource
    Resource resource = documentService.loadFileAsResource(fileName);

    // Try to determine file's content type
    String contentType = null;
    try {
      contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
    } catch (IOException ex) {
      log.info("Could not determine file type.");
    }

    // Fallback to the default content type if type could not be determined
    if (contentType == null) {
      contentType = "application/octet-stream";
    }
    return ResponseEntity.ok()
        .contentType(MediaType.parseMediaType(contentType))
        .header(
            HttpHeaders.CONTENT_DISPOSITION,
            "attachment; filename=\"" + resource.getFilename() + "\"")
        .body(resource);
  }


  @PostMapping(value = "/search")
  public ResponseEntity<List<DocumentDTO>> getDocumentsByDescription(
      @RequestParam("search") String description) {
    List<DocumentDTO> result = documentService.getAllByDescription(description);
    return new ResponseEntity<>(result, HttpStatus.OK);
  }

  @DeleteMapping("{id}/delete")
  public ResponseEntity deleteComment(
      Principal principal,
      @PathVariable Long id){
    documentService.deleteDoucmentById(id, principal.getName());
    return ResponseEntity.ok("Document delete successfully");
  }
}
