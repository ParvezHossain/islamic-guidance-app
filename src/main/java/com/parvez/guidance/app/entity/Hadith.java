package com.parvez.guidance.app.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "hadith")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Hadith {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String book;

    private String chapter;

    @Column(columnDefinition = "TEXT")
    private String text;
}
