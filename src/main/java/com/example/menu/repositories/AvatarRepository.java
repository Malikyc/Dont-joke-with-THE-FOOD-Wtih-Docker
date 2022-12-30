package com.example.menu.repositories;

import com.example.menu.Model.Avatar;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AvatarRepository extends JpaRepository <Avatar,Long> {
}
