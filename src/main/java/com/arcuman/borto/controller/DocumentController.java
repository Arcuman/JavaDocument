package com.arcuman.borto.controller;

import com.arcuman.borto.Repository.DocumentRepository;
import com.arcuman.borto.forms.DocumentForm;
import com.arcuman.borto.models.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class DocumentController {
    @Autowired
    private  DocumentRepository documentRepository;


    @GetMapping(value = {"/all" , "/"})
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
    public ModelAndView addNewDocument(Model model,
          @ModelAttribute("documentForm") DocumentForm documentForm) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("home");
        String title = documentForm.getTitle();
        String description = documentForm.getDescription();
        String link = documentForm.getLink();

        Document n = new Document();
        n.setTitle(title);
        n.setDescription(description);
        n.setLink(link);
        documentRepository.save(n);
        model.addAttribute("documents", documentRepository.findAll());
        return modelAndView;
        //model.addAttribute("errorMessage", errorMessage);
        //modelAndView.setViewName("addemployee");
    }

}
