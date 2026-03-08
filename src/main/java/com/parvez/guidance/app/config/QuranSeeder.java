package com.parvez.guidance.app.config;

import com.parvez.guidance.app.entity.Quran;
import com.parvez.guidance.app.repository.QuranRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Stream;

@Component
@RequiredArgsConstructor
@Slf4j
public class QuranSeeder implements CommandLineRunner {

    private final QuranRepository quranRepository;
    private static final int BATCH_SIZE = 2000;

    @Override
    public void run(String... args) throws Exception {
        if (quranRepository.count() > 0) {
            log.info("Quran table already has data, skipping seeding.");
            return;
        }

        Resource arabicRes = new ClassPathResource("data/quran_arabic.txt");
        Resource englishRes = new ClassPathResource("data/quran_english.txt");
        Resource bengaliRes = new ClassPathResource("data/quran_bengali.txt");

        try (
                BufferedReader arabicReader = new BufferedReader(new InputStreamReader(arabicRes.getInputStream()));
                BufferedReader englishReader = new BufferedReader(new InputStreamReader(englishRes.getInputStream()));
                BufferedReader bengaliReader = new BufferedReader(new InputStreamReader(bengaliRes.getInputStream()));

                Stream<String> arabicLines = arabicReader.lines();
                Stream<String> englishLines = englishReader.lines();
                Stream<String> bengaliLines = bengaliReader.lines();
        ) {

            Iterator<String> arabicIt = arabicLines.iterator();
            Iterator<String> englishIt = englishLines.iterator();
            Iterator<String> bengaliIt = bengaliLines.iterator();

            List<Quran> quranList = new ArrayList<>();

            while (arabicIt.hasNext() && englishIt.hasNext() && bengaliIt.hasNext()) {
                String arabicLine = arabicIt.next().trim();
                String englishLine = englishIt.next().trim();
                String bengaliLine = bengaliIt.next().trim();

                Quran q = parseQuranLine(arabicLine, englishLine, bengaliLine);
                if (q != null) quranList.add(q);

                if (quranList.size() >= BATCH_SIZE) {
                    quranRepository.saveAll(quranList);
                    quranList.clear();
                }
            }
            // Save leftover entries
            if (!quranList.isEmpty()) {
                quranRepository.saveAll(quranList);
            }

            log.info("Finished seeding Quran data.");
        }
    }

    private Quran parseQuranLine(String arabicLine, String englishLine, String bengaliLine) {
        arabicLine = arabicLine.trim();
        englishLine = englishLine.trim();
        bengaliLine = bengaliLine.trim();

        if (arabicLine.isEmpty() || englishLine.isEmpty() || bengaliLine.isEmpty()) return null;

        String[] arabicParts = arabicLine.split("\\|", 3);
        String[] englishParts = englishLine.split("\\|", 3);
        String[] bengaliParts = bengaliLine.split("\\|", 3);

        if (arabicParts.length < 1 || englishParts.length < 3 || bengaliParts.length < 3) {
            log.warn("Skipping malformed line: {}, {}, {}", arabicLine, englishLine, bengaliLine);
            return null;
        }

        int surah, ayah;
        try {
            surah = Integer.parseInt(englishParts[0].trim());
            ayah = Integer.parseInt(englishParts[1].trim());
        } catch (NumberFormatException e) {
            log.warn("Skipping line with invalid numbers: {}", englishParts);
            return null;
        }

        return Quran.builder()
                .surahNumber(surah)
                .ayahNumber(ayah)
                .arabic(cleanText(arabicParts[0]))
                .english(cleanText(englishParts[2]))
                .bengali(cleanText(bengaliParts[2]))
                .build();

    }

    private String cleanText(String str) {
        return str.trim()
                .replaceAll("^\\[?\"*", "")   // remove starting [" or "
                .replaceAll("\"?,?$", "");    // remove trailing ", or "
    }
}