package com.example.demo.controller.controller;

import com.example.demo.service.dto.UserDto;
import com.example.demo.service.exception.UserIncorrectPasswordException;
import com.example.demo.service.exception.UserNotFoundException;
import com.example.demo.service.service.UserService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.view.RedirectView;

@Controller
@RequestMapping
public class LoginController {

    @Autowired private UserService userService;

    @RequestMapping("/")
    public RedirectView defaultHome(HttpServletResponse response)
            throws UserNotFoundException, UserIncorrectPasswordException {
        UserDto user = userService.login("user", "pass");
        Cookie cookie = new Cookie("userId", user.getId().toString());
        cookie.setPath("/V1/");
        response.addCookie(cookie);
        return new RedirectView("/V1/notes/list");
    }
}
