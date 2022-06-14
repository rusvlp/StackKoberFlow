package com.gachisquad.stackkoberflow.controller;


import com.gachisquad.stackkoberflow.entity.Image;
import com.gachisquad.stackkoberflow.entity.User;
import com.gachisquad.stackkoberflow.request.PasswordUpdateRequest;
import com.gachisquad.stackkoberflow.services.CustomUserDetailsService;
import com.gachisquad.stackkoberflow.services.ImageService;
import com.gachisquad.stackkoberflow.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.parameters.P;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;

@Controller
@RequiredArgsConstructor

public class UserController {
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    private final CustomUserDetailsService customUserDetailsService;
    private final ImageService imageService;

    @GetMapping("/user/activate/{code}")
    public String activate(@PathVariable String code, Principal p, Model m){
        System.out.println(code);
        User pr = userService.getUserByPrincipal(p);
        m.addAttribute("user", pr);
        User user = userService.getUserByActivationCode(code);
        if (user == null){
            return "unsuccessActivated";
        }
        user.setActive(true);
        userService.saveUser(user);
        return "successActivated";
    }

    @PostMapping("/user/addAvatar")
    public String addAvatar(@RequestParam(name = "image")MultipartFile file, Principal p){
        User user = userService.getUserByPrincipal(p);
        Image image = imageService.toImage(file);
        user.setAvatar(image);
        userService.saveUser(user);
        return "redirect:/user/"+user.getId().toString();
    }

    @PostMapping("/user/deactivate")
    public String deactivate(Principal p, Model m){
        User user = userService.getUserByPrincipal(p);
        user.setActive(false);
        userService.saveUser(user);
        m.addAttribute("user", user);
        return "sucessDeactivated";
    }


    @PostMapping("/user/{id}/changePassword")
    public String changePassword(@PathVariable Long id, Principal p, @RequestParam(name="newPassword") String newPassword, Model model){
        if (id != userService.getUserByPrincipal(p).getId())
            return "passwordNotUpdated";
        User user = userService.getUserById(id);
        user.setPassword(passwordEncoder.encode(newPassword));
        model.addAttribute("user", user);
        userService.saveUser(user);
        return "passwordUpdated";
    }


    @GetMapping("/user/{id}")
    public String user(@PathVariable Long id, Principal p, Model m){
        m.addAttribute("user", userService.getUserByPrincipal(p));
        m.addAttribute("subject", userService.getUserById(id));
        return "user";
    }
    @GetMapping("/login")
    public String login(){
        return "login";
    }

    @GetMapping("/registration")
    public String registration(){
        return "reg";
    }

    @PostMapping("/registration")
    public String createUser(User user, Model model, Principal p){
        if (!userService.createUser(user)){
            model.addAttribute("errorMessage", "Пользователь с таким Email уже существует");
            return "reg";
        };
        model.addAttribute("user", userService.getUserByPrincipal(p));
        return "checkEmail";
    }

    @GetMapping("/hello")
    public String securityUrl(){
        return "hello";
    }
}
