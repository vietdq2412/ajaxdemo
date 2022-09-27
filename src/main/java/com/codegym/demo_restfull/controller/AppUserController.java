package com.codegym.demo_restfull.controller;

import com.codegym.demo_restfull.model.AppUser;
import com.codegym.demo_restfull.service.user.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@RestController
@CrossOrigin
@RequestMapping("/user")
public class AppUserController {
    @Autowired
    IUserService userService;

    @GetMapping("/register")
    public String ShowRegisterForm(Model model) {
        model.addAttribute("message", "hello");
        return "user/register";
    }

    @PostMapping("/register")
    public String Register(@RequestBody AppUser user) {
        userService.save(user);
        return "redirect:/user/login";
    }

    @GetMapping("/login")
    public ModelAndView login() {
        ModelAndView modelAndView = new ModelAndView("user/login");
        return modelAndView;
    }

    @GetMapping("/list")
    public ResponseEntity<Iterable<AppUser>> listUser(){
        return new ResponseEntity<>(userService.findAll(), HttpStatus.OK);
    }
}
