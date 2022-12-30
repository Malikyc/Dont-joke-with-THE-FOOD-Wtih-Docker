package com.example.menu.Sercvice.Controller;

import com.example.menu.Model.Dish;
import com.example.menu.Sercvice.MenuService;
import com.example.menu.repositories.DishRepository;
import com.example.menu.repositories.ImageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.Principal;

@RequiredArgsConstructor
@Controller
public class MenuController  {

    private final MenuService menuService;

    @GetMapping("/")
    public String menu(@RequestParam(name = "name", required = false) String name,Model model,Principal principal ){
        model.addAttribute( "menu", menuService.list(name));
        model.addAttribute("user", menuService.getUserByPrincipal(principal));
        return "menu";

    }
    @PostMapping("/menu/addDish")
    public String addDish(@RequestParam ("file1") MultipartFile file1 , @RequestParam ("file2") MultipartFile file2 ,
                          @RequestParam ("file3")  MultipartFile file3 , Dish dish, Principal principal) throws IOException {
        menuService.addDish(principal,dish,file1,file2,file3);
        return "redirect:/";

    }
    @PostMapping("/menu/delete/{id}")
    public String removeDish(@PathVariable Long id){
        menuService.removeDish(id);
        return "redirect:/";

    }
    @GetMapping ("/menu/{id}")
    public String info(@PathVariable Long id, Model model,Principal principal) {
        Dish dish = menuService.getDishByID(id);
        model.addAttribute("dish", dish);
        model.addAttribute("userByPrincipal",menuService.getUserByPrincipal(principal));
        model.addAttribute("images", dish.getImages());
        return "info";
    }

}
