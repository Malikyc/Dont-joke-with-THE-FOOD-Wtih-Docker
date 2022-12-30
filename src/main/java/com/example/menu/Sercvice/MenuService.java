package com.example.menu.Sercvice;

import com.example.menu.Model.Dish;
import com.example.menu.Model.User;
import com.example.menu.repositories.DishRepository;
import com.example.menu.repositories.ImageRepository;
import com.example.menu.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import lombok.extern.log4j.Log4j;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import com.example.menu.Model.Image;

import javax.swing.plaf.multi.MultiFileChooserUI;
import java.io.IOException;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
@Service
@RequiredArgsConstructor
@Slf4j
public class MenuService {
    private final ImageRepository imageRepository;

    private final DishRepository dishRepository;
    private final UserRepository userRepository;


    public List<Dish> list(String name){
        if(name != null) return dishRepository.findByName(name);
        return dishRepository.findAll();
    }
    public void addDish(Principal principal,Dish dish, MultipartFile file1, MultipartFile file2, MultipartFile file3) throws IOException{
        dish.setUser(getUserByPrincipal(principal));
        Image image1;
        Image image2;
        Image image3;
        imageRepository.deleteAll(dish.getImages());
        if(file1.getSize()!=0){
            image1 = toImageEntity(file1);
            image1.setPreviewImage(true);
            dish.addImageToDish(image1);
        }
        if(file2.getSize()!=0){
            image2 = toImageEntity(file2);
            dish.addImageToDish(image2);
        }
        if(file3.getSize()!=0){
            image3 = toImageEntity(file3);
            dish.addImageToDish(image3);
        }
        log.info("Saving new Dish, Name : {},Origin: {}",dish.getName(),dish.getOrigin());
        Dish dishFromDb = dishRepository.save(dish);
        dishFromDb.setPreviewImageid(dish.getImages().get(0).getId());
        dishRepository.save(dishFromDb);
    }

    public User getUserByPrincipal(Principal principal) {
        if(principal==null) return new User();
       return userRepository.findByEmail(principal.getName());
    }

    public void removeDish(Long id){
       dishRepository.deleteById(id);
    }

    public Dish getDishByID(Long id){
       return dishRepository.findById(id).orElse(null);
    }

    private Image toImageEntity (MultipartFile file) throws IOException {
        Image image = new Image();
        image.setName(file.getName());
        image.setOriginalFileName(file.getOriginalFilename());
        image.setContentType(file.getContentType());
        image.setSize(file.getSize());
        image.setBytes(file.getBytes());
        return image;
    }
}

