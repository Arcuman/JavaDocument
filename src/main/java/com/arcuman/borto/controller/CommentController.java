//package com.arcuman.borto.controller;
//
//import com.arcuman.borto.models.Comment;
//import com.arcuman.borto.models.Document;
//import com.arcuman.borto.models.User;
//import com.arcuman.borto.services.CommentService;
//import org.springframework.security.core.annotation.AuthenticationPrincipal;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.*;
//import org.springframework.web.servlet.ModelAndView;
//import org.springframework.web.servlet.view.RedirectView;
//
//@Controller
//@RequestMapping("/comment")
//public class CommentController {
//
//  final CommentService commentService;
//
//  public CommentController(CommentService commentService) {
//    this.commentService = commentService;
//  }
//
//  @GetMapping("{document}")
//  public ModelAndView commentList(@PathVariable Document document, Model model) {
//    ModelAndView modelAndView = new ModelAndView("commentList");
//    model.addAttribute("comments", commentService.getAllFromDocument(document));
//    model.addAttribute("document", document);
//    return modelAndView;
//  }
//
//  @PostMapping(value = "/addComment")
//  public RedirectView addNewComment(
//      @AuthenticationPrincipal User user,
//      Model model,
//      @RequestParam String message,
//      @RequestParam("idDocument") Document document) {
//    Comment comment = new Comment(message, user, document);
//    commentService.addNewComment(comment);
//    return new RedirectView("/comment" + '\\' + document.getId());
//  }
//}
