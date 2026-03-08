package com.parvez.guidance.app.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class QuranResponse {
    private int surah;
    private int ayah;
    private String arabic;
    private String bengali;
    private String english;
}
