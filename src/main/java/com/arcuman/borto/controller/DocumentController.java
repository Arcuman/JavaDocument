//package com.arcuman.borto.controller;
//
//import com.arcuman.borto.dto.DocumentDTO;
//import com.arcuman.borto.models.User;
//import com.arcuman.borto.services.DocumentService;
//import org.springframework.security.access.prepost.PreAuthorize;
//import org.springframework.security.core.annotation.AuthenticationPrincipal;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.*;
//import org.springframework.web.multipart.MultipartFile;
//import org.springframework.web.servlet.ModelAndView;
//
//import java.io.IOException;
//
//@Controller
//public class DocumentController {
//  private final DocumentService documentService;
//
//  public DocumentController(DocumentService documentService) {
//    this.documentService = documentService;
//  }
//
//  @GetMapping(value = {"/home", "/"})
//  public ModelAndView getAllDocument(Model model) {
//    ModelAndView modelAndView = new ModelAndView();
//    modelAndView.setViewName("home");
//    model.addAttribute("documents", documentService.getAll());
//    return modelAndView;
//  }
//
//  @PreAuthorize("hasAuthority('ADMIN')")
//  @RequestMapping(
//      value = {"/addDocument"},
//      method = RequestMethod.GET)
//  public ModelAndView showAddDocumentPage(Model model) {
//    ModelAndView modelAndView = new ModelAndView("addDocument");
//    DocumentDTO documentDTO = new DocumentDTO();
//    model.addAttribute("documentForm", documentDTO);
//    return modelAndView;
//  }
//
//  @PreAuthorize("hasAuthority('ADMIN')")
//  @PostMapping(value = "/addDocument")
//  public ModelAndView addNewDocument(
//      @AuthenticationPrincipal User user,
//      @RequestParam("file") MultipartFile file,
//      Model model,
//      @ModelAttribute("documentForm") DocumentDTO documentDTO)
//      throws IOException {
//    ModelAndView modelAndView = new ModelAndView();
//    modelAndView.setViewName("home");
//    documentService.addNewDoucment(documentDTO, user, file);
//    model.addAttribute("documents", documentService.getAll());
//    return modelAndView;
//  }
//
//  @PostMapping("/findDocument")
//  public ModelAndView findDocument(@RequestParam String description, Model model) {
//    ModelAndView modelAndView = new ModelAndView("home");
//    model.addAttribute("documents", documentService.getAllByDescription(description));
//    return modelAndView;
//  }
//}
