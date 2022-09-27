package com.codegym.demo_restfull.controller;

import com.codegym.demo_restfull.model.AppUser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@SessionAttributes("user")
public class HomeController {
    @ModelAttribute("user")
    AppUser setUpCounter() {
        return new AppUser("viet", "1");
    }

    @GetMapping("/")
    public String index(@ModelAttribute("user") AppUser user) {
        user.setUsername(user.getUsername() + 1);
        return "index";
    }

    @RequestMapping("/login")
    public String Index(@CookieValue(value = "setUser", defaultValue = "") String setUser, Model model) {
        Cookie cookie = new Cookie("setUser", setUser);
        cookie.setMaxAge(60*60);
        model.addAttribute("cookieValue", cookie);
        return "/WEB-INF/user/login.html";
    }

    @PostMapping("/dologin")
    public String doLogin(@ModelAttribute("user") AppUser user, Model model, @CookieValue(value = "setUser",
            defaultValue = "") String setUser, HttpServletResponse response, HttpServletRequest request) {
        //implement business logic
        if (user.getUsername().equals("viet") && user.getPassword().equals("1")) {
            if (user.getUsername() != null)
                setUser = user.getUsername();

            // create cookie and set it in response
            Cookie cookie = new Cookie("setUser", setUser);
            cookie.setMaxAge(60);
            response.addCookie(cookie);

            //get all cookies
            Cookie[] cookies = request.getCookies();
            //iterate each cookie
            for (Cookie ck : cookies) {
                //display only the cookie with the name 'setUser'
                if (ck.getName().equals("setUser")) {
                    model.addAttribute("cookieValue", ck);
                    break;
                } else {
                    ck.setValue("");
                    model.addAttribute("cookieValue", ck);
                    break;
                }
            }
            model.addAttribute("message", "Login success. Welcome ");
        } else {
            user.setUsername("");
            Cookie cookie = new Cookie("setUser", setUser);
            model.addAttribute("cookieValue", cookie);
            model.addAttribute("message", "Login failed. Try again.");
        }
        return "user/login";
    }
}
