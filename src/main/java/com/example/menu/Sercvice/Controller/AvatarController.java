package com.example.menu.Sercvice.Controller;

import com.example.menu.Model.Avatar;
import com.example.menu.repositories.AvatarRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.io.ByteArrayInputStream;

@RequiredArgsConstructor
@RestController
@AllArgsConstructor
public class AvatarController {
    @Autowired
    private AvatarRepository avatarRepository;

    @GetMapping("/avatar/{id}")
    ResponseEntity<?> getAvatarById (@PathVariable Long id){
        Avatar avatar  = avatarRepository.findById(id).orElse(null);
        return ResponseEntity.ok()
                .header("fileName",avatar.getOriginalFileName())
                .contentType(MediaType.valueOf(avatar.getContentType()))
                .contentLength(avatar.getSize())
                .body(new InputStreamResource(new ByteArrayInputStream(avatar.getBytes())));
    }

}
