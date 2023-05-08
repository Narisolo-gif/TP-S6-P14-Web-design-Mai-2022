package com.example.integration.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.Base64;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class Article {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Integer id;
    @Column
    private String titre;
    @Column
    private String resume;
    @Column
    private String contenu;
    @Column
    private String visual;

    public void setVisual(MultipartFile img) throws IOException {
        this.visual = Base64.getEncoder().encodeToString(img.getBytes());
    }
}
