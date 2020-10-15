package com.arcuman.borto.controller;

import com.arcuman.borto.Repository.UserRepository;
import com.arcuman.borto.forms.UserForm;
import com.arcuman.borto.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

@Controller
@RequestMapping("/user")
@PreAuthorize("hasAuthority('ADMIN')")
public class UserController {
    @Autowired
    private UserRepository userRepository;

    @GetMapping
    public ModelAndView userList(Model model) {
        ModelAndView modelAndView = new ModelAndView("userList");
        model.addAttribute("users",userRepository.findAll());
        return modelAndView;
    }

    @GetMapping("{user}")
    public ModelAndView userEditForm(@PathVariable User user, Model model){
        ModelAndView modelAndView = new ModelAndView("userEdit");
        model.addAttribute("user",user);
        return modelAndView;
    }

    @PostMapping
    public RedirectView userSave(
            @RequestParam String username,
            @RequestParam("idUser") User user){
        user.setUsername(username);
        userRepository.save(user);
        return new RedirectView("/user");
    }
}
