package com.example.menu.Sercvice.Controller;

import com.example.menu.Sercvice.MenuService;
import com.example.menu.Sercvice.UserService;
import com.example.menu.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import com.example.menu.Model.User;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.Principal;

@RequiredArgsConstructor
@Controller
public class UserController {
    private final UserService userService;
    private final MenuService menuService;


    @GetMapping("/login")
    public String login(Principal principal, Model model) {
        model.addAttribute("user", menuService.getUserByPrincipal(principal));
        return "login";
    }

    @GetMapping("/registration")
    public String registration(Principal principal, Model model) {
        model.addAttribute("user", menuService.getUserByPrincipal(principal));
        return "registration";
    }

    @PostMapping("/registration")
    public String addUser(User user, Model model, @RequestParam ("file1") MultipartFile file1) throws IOException {
        if (!userService.createUser(user,file1)) {
            model.addAttribute("errorMess", "Пользовател с такой почтой уже существует");
            return "registration";
        }
        return "redirect:/login";
    }
    @GetMapping("/user/{user}")
    public String userInfo ( @PathVariable("user") User user, Model model, Principal principal){
        model.addAttribute("user",user);
        model.addAttribute("userByPrincipal",menuService.getUserByPrincipal(principal));
        model.addAttribute("dishes",user.getDishes());
       return "user-info";

    }
    @GetMapping("/profile")
    public String myProfile (Principal principal, Model model){
        User user =menuService.getUserByPrincipal(principal);
        model.addAttribute("user",user);
        return "profile";

    }
    @GetMapping("/my/dishes")
    public String myDishes (  Model model, Principal principal){
        model.addAttribute("userByPrincipal",menuService.getUserByPrincipal(principal));
        model.addAttribute("dishes",menuService.getUserByPrincipal(principal).getDishes());
        return "my-dishes";

    }


}
