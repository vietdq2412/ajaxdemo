package com.codegym.demo_restfull.controller;

import com.codegym.demo_restfull.model.AppUser;
import com.codegym.demo_restfull.model.Blog;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@RestController
@SessionAttributes("user")
@RequestMapping("blog")
public class BlogController {

    @GetMapping("/create")
    public String showFormCreate(Model model){
        Blog blog = new Blog();
        model.addAttribute("blog", blog);
        return "blog/create";
    }
    @PostMapping("/create")


    @GetMapping("/list")
    public ModelAndView list(@ModelAttribute("user") AppUser user) {
        user.setUsername(user.getUsername()+"list" +1);
        ModelAndView modelAndView = new ModelAndView("blog/list");
        return modelAndView;
    }
}
