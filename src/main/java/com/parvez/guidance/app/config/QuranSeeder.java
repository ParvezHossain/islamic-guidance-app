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
import java.util.List;

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
        ) {
            String arabicLine;
            String englishLine;
            String bengaliLine;
            List<Quran> quranList = new ArrayList<>();

            while ((arabicLine = arabicReader.readLine()) != null &&
                    (englishLine = englishReader.readLine()) != null &&
                    (bengaliLine = bengaliReader.readLine()) != null) {

                arabicLine = arabicLine.trim();
                englishLine = englishLine.trim();
                bengaliLine = bengaliLine.trim();

                // Skip empty lines
                if (arabicLine.isEmpty() || englishLine.isEmpty() || bengaliLine.isEmpty()) {
                    continue;
                }

                // Split lines into 3 parts: surah|ayah|text
                String[] arabicParts = arabicLine.split("\\|", 3);
                String[] englishParts = englishLine.split("\\|", 3);
                String[] bengaliParts = bengaliLine.split("\\|", 3);

                System.out.println("arabicParts" + arabicParts[0]);

                // Skip malformed lines
                if (arabicParts.length < 1 || englishParts.length < 3 || bengaliParts.length < 3) {
                    log.warn("Skipping malformed line: arabic='{}', english='{}', bengali='{}'", arabicLine, englishLine, bengaliLine);
                    continue;
                }

                int surah;
                int ayah;
                try {
                    surah = Integer.parseInt(englishParts[0].trim());
                    ayah = Integer.parseInt(englishParts[1].trim());
                } catch (NumberFormatException e) {
                    log.warn("Skipping line with invalid numbers: '{}'", arabicLine);
                    continue;
                }

                Quran q = Quran.builder()
                        .surahNumber(surah)
                        .ayahNumber(ayah)
                        .arabic(removeSlashes(arabicParts[0]))
                        .english(englishParts[2])
                        .bengali(bengaliParts[2])
                        .build();

                quranList.add(q);

                if (quranList.size() >= 1000) {
                    quranRepository.saveAll(quranList);
                    quranList.clear();
                }
            }
        }
    }

    private String removeSlashes(String str) {
        return str.trim()
                .replaceAll("^\\[?\"*", "")   // remove starting [" or "
                .replaceAll("\"?,?$", "");    // remove trailing ", or "
    }
}