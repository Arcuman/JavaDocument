package com.arcuman.borto.controller;

import com.arcuman.borto.Repository.DocumentRepository;
import com.arcuman.borto.forms.DocumentForm;
import com.arcuman.borto.models.Document;
import com.arcuman.borto.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Map;

@Controller
public class DocumentController {
    @Autowired
    private  DocumentRepository documentRepository;


    @GetMapping(value = {"/home" , "/"})
    public ModelAndView getAllDocument(Model model) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("home");
        model.addAttribute("documents", documentRepository.findAll());
        return modelAndView;
    }

    @RequestMapping(value = {"/addDocument"}, method = RequestMethod.GET)
    public ModelAndView showAddDocumentPage(Model model) {
        ModelAndView modelAndView = new ModelAndView("addDocument");
        DocumentForm documentForm = new DocumentForm();
        model.addAttribute("documentForm", documentForm);
        return modelAndView;
    }

    @PostMapping(value = "/addDocument")
    public ModelAndView addNewDocument(
            @AuthenticationPrincipal User user,
            Model model,
            @ModelAttribute("documentForm") DocumentForm documentForm) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("home");
        String title = documentForm.getTitle();
        String description = documentForm.getDescription();
        String link = documentForm.getLink();

        Document n = new Document(title,description,link,user);
        documentRepository.save(n);
        model.addAttribute("documents", documentRepository.findAll());
        return modelAndView;
        //model.addAttribute("errorMessage", errorMessage);
        //modelAndView.setViewName("addemployee");
    }

    @PostMapping("/findDocument")
    public ModelAndView findDocument(@RequestParam String description, Model model){
        Iterable<Document> documents;
        if(description != null && !description.isEmpty()){
            documents = documentRepository.findByDescription(description);
        }
        else{
            documents = documentRepository.findAll();
        }
        ModelAndView modelAndView = new ModelAndView("home");
        model.addAttribute("documents",documents);
        return modelAndView;
    }

}
