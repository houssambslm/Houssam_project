package ma.emsi.houssam_project.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.util.Collection;

@Data @AllArgsConstructor @NoArgsConstructor @Entity
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(unique=true)
    private String nom;

    private String author;

    @ManyToOne
    private Library Library;

    @ManyToOne
    private Member Member;

}
