package com.example.menu.Model;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@AllArgsConstructor
@Table(name="Avatar")


public class Avatar {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column (name="id")
    private Long id;
    @Column (name="name")
    private String name;
    @Column (name="originalFileName")
    private String originalFileName;
    @Column (name="size")
    private Long size;
    @Column (name="contentType")
    private String contentType;
    @Lob
    private byte[] bytes;

    @OneToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
        private User user;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Avatar avatar = (Avatar) o;
        return id != null && Objects.equals(id, avatar.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
