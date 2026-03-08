package com.parvez.guidance.app.controller;

import com.parvez.guidance.app.dto.QuranResponse;
import com.parvez.guidance.app.service.QuranService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/api/quran")
@RequiredArgsConstructor
public class QuranController {

    private final QuranService quranService;

    @GetMapping("/surah/{surah}")
    public List<QuranResponse> getSurah(@PathVariable int surah) {
        return quranService.getQuranBySurah(surah);
    }

    @GetMapping("/surah/{surah}/ayah/{ayah}")
    public QuranResponse getAyah(
            @PathVariable int surah,
            @PathVariable int ayah
    ) {
        return quranService.getAyah(surah, ayah);
    }
}