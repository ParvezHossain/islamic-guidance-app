package com.parvez.guidance.app.service;

import com.parvez.guidance.app.dto.QuranResponse;
import com.parvez.guidance.app.entity.Quran;
import com.parvez.guidance.app.repository.QuranRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class QuranService {

    private final QuranRepository quranRepository;

    public List<QuranResponse> getQuranBySurah(int surah) {

        List<Quran> verses = quranRepository.findBySurahNumberOrderByAyahNumberAsc(surah);

        return verses.stream()
                .map(this::mapToDto)
                .toList();

    }

    public QuranResponse getAyah(int surah, int ayah) {
        Quran quran = quranRepository
                .findBySurahNumberAndAyahNumber(surah, ayah)
                .orElseThrow(() -> new RuntimeException("Ayah not found"));

        return mapToDto(quran);
    }

    private QuranResponse mapToDto(Quran quran) {
        return QuranResponse.builder()
                .surah(quran.getSurahNumber())
                .ayah(quran.getAyahNumber())
                .arabic(quran.getArabic())
                .bengali(quran.getBengali())
                .english(quran.getEnglish())
                .build();
    }
}
