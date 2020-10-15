package com.arcuman.borto.controller;

import com.arcuman.borto.Repository.DocumentRepository;
import com.arcuman.borto.forms.DocumentForm;
import com.arcuman.borto.models.Document;
import com.arcuman.borto.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Controller
public class DocumentController {
    @Autowired
    private  DocumentRepository documentRepository;

    @Value("${upload.path}")
    private String uploadPath;

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
            @RequestParam("file") MultipartFile file,
            Model model,
            @ModelAttribute("documentForm") DocumentForm documentForm) throws IOException {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("home");
        String title = documentForm.getTitle();
        String description = documentForm.getDescription();
        String link = documentForm.getLink();


        Document document = new Document(title,description,link,user);
        if(file != null && !file.getOriginalFilename().isEmpty())
        {
            File uploadDir = new File(uploadPath);

            if(!uploadDir.exists()){
                uploadDir.mkdir();
            }

            String uuidFile = UUID.randomUUID().toString();

            String resultFileName = uuidFile + "."+ file.getOriginalFilename();

            file.transferTo(new File(uploadPath + "/" + resultFileName));

            document.setFilename(resultFileName);
        }
        documentRepository.save(document);
        model.addAttribute("documents", documentRepository.findAll());
        return modelAndView;
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
