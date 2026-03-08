package com.parvez.guidance.app.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "guidance")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Guidance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String emotion;

    @Column(columnDefinition = "TEXT")
    private String quranText;

    private String quranReference;

    @Column(columnDefinition = "TEXT")
    private String hadithText;

    private String hadithReference;
}
