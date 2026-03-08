package com.parvez.guidance.app.service;

import com.parvez.guidance.app.dto.GuidanceResponse;
import com.parvez.guidance.app.entity.Guidance;
import com.parvez.guidance.app.repository.GuidanceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class GuidanceService {

    private final GuidanceRepository repository;
    private final Random random = new Random();

    public GuidanceResponse getGuidance(String emotion) {
        List<Guidance> list = repository.findByEmotion(emotion);

        if (list.isEmpty()) {
            throw new RuntimeException("GUIDANCE NOT FOUND");
        }

        Guidance guidance = list.get(random.nextInt(list.size()));

        return GuidanceResponse.builder()
                .emotion(guidance.getEmotion())
                .quranText(guidance.getQuranText())
                .quranReference(guidance.getQuranReference())
                .hadithText(guidance.getHadithText())
                .hadithReference(guidance.getHadithReference())
                .build();
    }

}
