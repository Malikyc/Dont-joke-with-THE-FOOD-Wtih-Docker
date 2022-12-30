package com.example.menu.Sercvice;

import com.example.menu.Model.Avatar;
import com.example.menu.Model.Image;
import com.example.menu.Model.enums.Role;
import com.example.menu.configurations.SecurityConfig;
import com.example.menu.repositories.ImageRepository;
import com.example.menu.repositories.UserRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.example.menu.Model.User;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.Principal;
import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@AllArgsConstructor
@Slf4j

public class UserService {

    private UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final MenuService menuService;

    public boolean createUser(User user, MultipartFile file1) throws IOException {
        String email = user.getEmail();
        if (userRepository.findByEmail(email) != null) {
            return false;
        } else {
            user.setActive(true);
            if(file1.getSize()!=0){
                Avatar image1 = toAvatarEntity(file1);
                image1.setUser(user);
                user.setAvatar(image1);
                user.setHasAvatar(true);
            }
            if (userRepository.findAll().size()<1){
            user.getRoles().add(Role.ROLE_ADMIN);}
            else user.getRoles().add(Role.ROLE_USER);
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            log.info("Saving new user by email:{}", email);
            userRepository.save(user);
            return true;
        }

    }

    public void banUser(Long id) {
        User user = userRepository.findById(id).orElse(null);
        if (user != null) {
            if(user.isActive()){
                user.setActive(false);
                log.info("Banning user with id:{},by email:{}",user.getId(),user.getEmail());
            }
            else {
                user.setActive(true);
            log.info("Unbanning user with id:{},by email:{}",user.getId(),user.getEmail());
        }
        }
        userRepository.save(user);
    }
    public List <User> users(){
        return userRepository.findAll();
    }

   public void changeUserRole (User user, Map <String,String> roles){
       Set <String> stringroles = Arrays.stream(Role.values()).
               map(Role::name)
               .collect(Collectors.toSet());
       user.getRoles().clear();
       for(String key:roles.keySet()){
           if(stringroles.contains(key)){
               user.getRoles().add(Role.valueOf(key));
           }
           userRepository.save(user);
       }
   }
    private Avatar toAvatarEntity (MultipartFile file) throws IOException {
        Avatar avatar = new Avatar();
        avatar.setName(file.getName());
        avatar.setOriginalFileName(file.getOriginalFilename());
        avatar.setContentType(file.getContentType());
        avatar.setSize(file.getSize());
        avatar.setBytes(file.getBytes());
        return avatar;
    }

}
