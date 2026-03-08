package com.parvez.guidance.app.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class GuidanceResponse {

    private String emotion;

    private String quranText;
    private String quranReference;

    private String hadithText;
    private String hadithReference;

}