package com.example.menu.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="dishes")
@AllArgsConstructor
@Data
@NoArgsConstructor


public class Dish {
@Id
@GeneratedValue(strategy=GenerationType.AUTO)
@Column(name="id")
    private  Long ID;
@Column(name = "name")
    private String name;
@Column(name ="calories")
    private int calories;
    @Column(name ="description",columnDefinition ="text")
    private String description;
    @Column(name ="origin")
    private String origin;
    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
    @JoinColumn
    private User user;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY,mappedBy = "dish")
    @ToString.Exclude
    private List<Image> images = new ArrayList<>();
    private Long previewImageid;
    private LocalDateTime dateOfCreation;
    public void init(){
        dateOfCreation = LocalDateTime.now();
    }
    public void addImageToDish(Image image){
        image.setDish(this);
        images.add(image);
    }
}
