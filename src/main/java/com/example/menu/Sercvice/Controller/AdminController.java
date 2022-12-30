package com.example.menu.Sercvice.Controller;

import com.example.menu.Model.User;
import com.example.menu.Model.enums.Role;
import com.example.menu.Sercvice.MenuService;
import com.example.menu.Sercvice.UserService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;
import java.util.Map;

@PreAuthorize("hasAuthority('ROLE_ADMIN')")
@Controller
@AllArgsConstructor
public class AdminController {
    private final UserService userService;
    private final MenuService menuService;
    @GetMapping("/admin")
    public String adminPage (Principal principal, Model model) {
        model.addAttribute("user", menuService.getUserByPrincipal(principal));
        model.addAttribute("users",userService.users());
        return "admin";
    }
@PostMapping("/admin/ban/user/{id}")
    public String userBan(@PathVariable("id") Long id){
            userService.banUser(id);
            return "redirect:/admin";

    }
    @GetMapping("/admin/edit/user/{user}")
    public String userEdit(@PathVariable("user") User user,Model model,Principal principal){
        model.addAttribute("user",user);
        model.addAttribute("userByPrincipal",menuService.getUserByPrincipal(principal));
        model.addAttribute("roles", Role.getRoles());
        return "user-roles";
    }
    @PostMapping("/admin/edit/user/")
    public String changeUserRole (@RequestParam("userId") User user,  @RequestParam Map<String,String> form){
        userService.changeUserRole(user, form);
        return "redirect:/admin";
    }
}
