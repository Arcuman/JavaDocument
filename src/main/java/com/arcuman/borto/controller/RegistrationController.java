package com.arcuman.borto.controller;

import com.arcuman.borto.Repository.UserRepository;
import com.arcuman.borto.forms.DocumentForm;
import com.arcuman.borto.forms.UserForm;
import com.arcuman.borto.models.Document;
import com.arcuman.borto.models.Role;
import com.arcuman.borto.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Collections;

@Controller
public class RegistrationController {
    @Autowired
    private UserRepository userRepository;


    @GetMapping("/registration")
    public ModelAndView showRegistrationPage(Model model) {
        ModelAndView modelAndView = new ModelAndView("registration");
        UserForm userForm = new UserForm();
        model.addAttribute("userForm", userForm);
        return modelAndView;
    }
    @PostMapping("/registration")
    public ModelAndView AddNewUser(Model model,
             @ModelAttribute("userForm") UserForm userForm) {
        ModelAndView modelAndView = new ModelAndView();

        String userName = userForm.getUsername();
        String password = userForm.getPassword();

        User userFromDB = userRepository.findByUsername(userName);
        if(userFromDB != null){
            model.addAttribute("errorMessage", "ERROR USER IS ALREADY EXIST");
            modelAndView.setViewName("registration");
            return modelAndView;
        }
        modelAndView.setViewName("login");
        User user = new User();
        user.setUsername(userName);
        user.setPassword(password);

        user.setActive(true);
        user.setRoles(Collections.singleton(Role.USER));
        model.addAttribute("errorMessage", "ERROR USER IS ALREADY EXIST");
        userRepository.save(user);
        return modelAndView;
    }
}
