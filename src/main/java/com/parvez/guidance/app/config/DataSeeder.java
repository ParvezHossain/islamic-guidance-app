package com.parvez.guidance.app.config;

import com.parvez.guidance.app.entity.Guidance;
import com.parvez.guidance.app.repository.GuidanceRepository;
import com.parvez.guidance.app.repository.HadithRepository;
import com.parvez.guidance.app.repository.QuranRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Random;

@Component
@RequiredArgsConstructor
@Slf4j
public class DataSeeder implements CommandLineRunner {

    private final QuranRepository quranRepository;
    private final HadithRepository hadithRepository;
    private final GuidanceRepository guidanceRepository;
    private final Random random = new Random();

    @Override
    public void run(String... args) throws Exception {
        if (guidanceRepository.count() > 0) {
            log.info("Guidance table already has data, skipping seeding.");
            return;
        }

        List<Guidance> guidanceList = List.of(
                // HAPPY
                Guidance.builder()
                        .emotion("happy")
                        .quranText("Indeed, Allah is with those who fear Him and those who are doers of good.")
                        .quranReference("Quran 16:128")
                        .hadithText("The Prophet (ﷺ) said: 'The best of people are those that bring most benefit to the rest of mankind.'")
                        .hadithReference("Daraqutni, Hasan")
                        .build(),

                Guidance.builder()
                        .emotion("happy")
                        .quranText("So remember Me; I will remember you.")
                        .quranReference("Quran 2:152")
                        .hadithText("The Prophet (ﷺ) said: 'Allah is pleased with the servant who praises Him.'")
                        .hadithReference("Sahih Muslim 2699a")
                        .build(),

                // SAD
                Guidance.builder()
                        .emotion("sad")
                        .quranText("Do not despair of the mercy of Allah.")
                        .quranReference("Quran 39:53")
                        .hadithText("The Prophet (ﷺ) said: 'No fatigue, nor disease, nor sorrow, nor sadness, nor hurt, nor distress befalls a Muslim, even if it were the prick he receives from a thorn, but that Allah expiates some of his sins for that.'")
                        .hadithReference("Sahih Bukhari 5641")
                        .build(),

                Guidance.builder()
                        .emotion("sad")
                        .quranText("Indeed, with hardship comes ease.")
                        .quranReference("Quran 94:6")
                        .hadithText("The Prophet (ﷺ) said: 'The strong believer is better and more beloved to Allah than the weak believer.'")
                        .hadithReference("Sahih Muslim 2664")
                        .build(),

                // ANGRY
                Guidance.builder()
                        .emotion("angry")
                        .quranText("Who restrain anger and pardon people; and Allah loves the doers of good.")
                        .quranReference("Quran 3:134")
                        .hadithText("The Prophet (ﷺ) said: 'The strong person is not the one who throws his adversaries to the ground. The strong person is the one who contains himself when he is angry.'")
                        .hadithReference("Sahih Bukhari 6114")
                        .build(),

                Guidance.builder()
                        .emotion("angry")
                        .quranText("Repel evil with that which is better.")
                        .quranReference("Quran 41:34")
                        .hadithText("The Prophet (ﷺ) said: 'The most perfect of believers in faith is he who is best in conduct.'")
                        .hadithReference("Sunan Tirmidhi 1162")
                        .build(),

                // HOPELESS
                Guidance.builder()
                        .emotion("hopeless")
                        .quranText("And rely upon Allah; and sufficient is Allah as Disposer of affairs.")
                        .quranReference("Quran 33:3")
                        .hadithText("The Prophet (ﷺ) said: 'Whoever relies upon Allah, He will suffice him.'")
                        .hadithReference("Tirmidhi 2517")
                        .build(),

                Guidance.builder()
                        .emotion("hopeless")
                        .quranText("Allah does not burden a soul beyond that it can bear.")
                        .quranReference("Quran 2:286")
                        .hadithText("The Prophet (ﷺ) said: 'Verily, Allah is Gentle and loves gentleness in all things.'")
                        .hadithReference("Sahih Muslim 2593")
                        .build(),

                // MORE ENTRIES (HAPPY)
                Guidance.builder()
                        .emotion("happy")
                        .quranText("And We have certainly made the Qur'an easy to remember. So is there any who will remember?")
                        .quranReference("Quran 54:17")
                        .hadithText("The Prophet (ﷺ) said: 'Make things easy for people and do not make them difficult.'")
                        .hadithReference("Sahih Bukhari 69")
                        .build(),

                Guidance.builder()
                        .emotion("happy")
                        .quranText("And He found you lost and guided [you].")
                        .quranReference("Quran 93:7")
                        .hadithText("The Prophet (ﷺ) said: 'The best charity is that given when one is rich.'")
                        .hadithReference("Sahih Bukhari 1419")
                        .build(),

                // MORE ENTRIES (SAD)
                Guidance.builder()
                        .emotion("sad")
                        .quranText("Indeed, Allah is with the patient.")
                        .quranReference("Quran 2:153")
                        .hadithText("The Prophet (ﷺ) said: 'No one is granted a greater reward than the patient.'")
                        .hadithReference("Sahih Bukhari 5674")
                        .build(),

                Guidance.builder()
                        .emotion("sad")
                        .quranText("And We will surely test you with something of fear and hunger and a loss of wealth and lives and fruits, but give good tidings to the patient.")
                        .quranReference("Quran 2:155")
                        .hadithText("The Prophet (ﷺ) said: 'The patience of a believer is charity.'")
                        .hadithReference("Sahih Bukhari 5644")
                        .build(),

                // MORE ENTRIES (ANGRY)
                Guidance.builder()
                        .emotion("angry")
                        .quranText("And not equal are the good deed and the bad. Repel [evil] by that [deed] which is better.")
                        .quranReference("Quran 41:34")
                        .hadithText("The Prophet (ﷺ) said: 'A person is not a true believer who eats his fill while his neighbor is hungry.'")
                        .hadithReference("Sahih Muslim 1009")
                        .build(),

                Guidance.builder()
                        .emotion("angry")
                        .quranText("Do not consume one another’s wealth unjustly.")
                        .quranReference("Quran 2:188")
                        .hadithText("The Prophet (ﷺ) said: 'He who forgives, Allah will increase his honor.'")
                        .hadithReference("Sunan Tirmidhi 1334")
                        .build(),

                // MORE ENTRIES (HOPELESS)
                Guidance.builder()
                        .emotion("hopeless")
                        .quranText("So do not weaken and do not grieve, and you will be superior if you are [true] believers.")
                        .quranReference("Quran 3:139")
                        .hadithText("The Prophet (ﷺ) said: 'None of you truly believes until he loves for his brother what he loves for himself.'")
                        .hadithReference("Sahih Bukhari 13")
                        .build(),

                Guidance.builder()
                        .emotion("hopeless")
                        .quranText("Call upon Me; I will respond to you.")
                        .quranReference("Quran 40:60")
                        .hadithText("The Prophet (ﷺ) said: 'Dua is the essence of worship.'")
                        .hadithReference("Tirmidhi 3379")
                        .build(),

                // HAPPY
                Guidance.builder()
                        .emotion("happy")
                        .quranText("And Allah invites to the Home of Peace.")
                        .quranReference("Quran 10:25")
                        .hadithText("The Prophet (ﷺ) said: 'Whoever Allah wants good for, He makes him understanding in religion.'")
                        .hadithReference("Sahih Bukhari 71")
                        .build(),

                // SAD
                Guidance.builder()
                        .emotion("sad")
                        .quranText("And We will suffice you against the people.")
                        .quranReference("Quran 93:10")
                        .hadithText("The Prophet (ﷺ) said: 'When a person is afflicted, Allah removes some of his sins.'")
                        .hadithReference("Sahih Muslim 2575")
                        .build()
        );

        guidanceRepository.saveAll(guidanceList);
        log.info("Seeded {} guidance entries.", guidanceList.size());
    }
}