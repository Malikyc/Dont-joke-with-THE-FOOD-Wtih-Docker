package com.example.menu.repositories;

import com.example.menu.Model.Image;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImageRepository extends JpaRepository <Image,Long> {
}
