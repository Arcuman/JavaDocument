package com.arcuman.borto.controller;

import com.arcuman.borto.Repository.CommentRepository;
import com.arcuman.borto.Repository.UserRepository;
import com.arcuman.borto.forms.DocumentForm;
import com.arcuman.borto.models.Comment;
import com.arcuman.borto.models.Document;
import com.arcuman.borto.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Controller
@RequestMapping("/comment")
public class CommentController {
    @Autowired
    private CommentRepository commentRepository;

    @GetMapping("{document}")
    public ModelAndView commentList(@PathVariable Document document, Model model) {
        ModelAndView modelAndView = new ModelAndView("commentList");
        model.addAttribute("comments",commentRepository.findByDocument(document));
        model.addAttribute("document",document);
        return modelAndView;
    }

    @PostMapping(value = "/addComment")
    public RedirectView addNewComment(
            @AuthenticationPrincipal User user,
            Model model,
            @RequestParam String message,
            @RequestParam("idDocument") Document document){
        Comment comment = new Comment(message,user,document);
        commentRepository.save(comment);
        return new RedirectView("/comment");
    }

}
