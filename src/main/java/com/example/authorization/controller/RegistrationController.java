package com.example.authorization.controller;

import com.example.authorization.models.RoleEditor;
import com.example.authorization.models.User;
import com.example.authorization.security.UserRole;
import com.example.authorization.service.registrationService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import springfox.documentation.annotations.ApiIgnore;

@AllArgsConstructor
@RestController
@ApiIgnore
@RequestMapping(path = "/signup")
public class RegistrationController {
    private final registrationService registrationService;

    @PostMapping
    public ModelAndView postUser(@ModelAttribute("user") User user) {
        if (!registrationService.createUser(user))
            return new ModelAndView("redirect:/signup?failure");

        return new ModelAndView("redirect:/signup?success");
    }


    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(UserRole.class, new RoleEditor());
    }

//    @GetMapping("/changetoAdmin")
//    public ModelAndView makeAdmin(@RequestParam("Change to ADMIN") int roleID,
//                                  ModelMap model) {
//        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//        String username = ((UserDetails) principal).getUsername();
//        registrationService.changeUserRole(username, roleID);
//        return new ModelAndView("redirect:/courses", model);
//    }
//
//    @GetMapping("/changetoStudent")
//    public ModelAndView makeStudent(@RequestParam("Change to STUDENT") int roleID,
//                                    ModelMap model) {
//
//        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//        String username = ((UserDetails) principal).getUsername();
//        registrationService.changeUserRole(username, roleID);
//        return new ModelAndView("redirect:/courses", model);
//    }
}
