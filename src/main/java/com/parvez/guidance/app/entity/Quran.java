package com.parvez.guidance.app.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "quran")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Quran {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int surahNumber;

    private int ayahNumber;

    @Column(columnDefinition = "TEXT")
    private String arabic;

    @Column(columnDefinition = "TEXT")
    private String english;

    @Column(columnDefinition = "TEXT")
    private String bengali;
}