package com.example.authorization.controller;

import com.example.authorization.models.Course;
import com.example.authorization.models.User;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import springfox.documentation.annotations.ApiIgnore;

@ApiIgnore
@Controller
@RequestMapping("/")
public class TemplateController {

    @GetMapping("/login")
    public String getLoginView() {
        return "login";
    }

    @GetMapping("/signup")
    public String getSignupView(Model model) {
        model.addAttribute("user", new User());
        return "signup";
    }

    @GetMapping("")
    public ModelAndView redirectWithUsingRedirectPrefix(ModelMap model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        model.addAttribute("attribute", "redirectWithRedirectPrefix");
        if (auth != null)
            return new ModelAndView("redirect:/courses", model);
        else
            return new ModelAndView("redirect:/logout", model);
    }
}
