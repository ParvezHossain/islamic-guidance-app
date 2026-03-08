package com.parvez.guidance.app.controller;

import com.parvez.guidance.app.dto.GuidanceResponse;
import com.parvez.guidance.app.dto.QuranResponse;
import com.parvez.guidance.app.service.GuidanceService;
import com.parvez.guidance.app.service.QuranService;
import io.micrometer.common.util.StringUtils;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.BadRequestException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/guidance")
@RequiredArgsConstructor
public class GuidanceController {

    private  final GuidanceService guidanceService;
    private  final QuranService quranService;

    @GetMapping
    public GuidanceResponse getGuidance(@RequestParam(required = false) String emotion) {

        if (StringUtils.isEmpty(emotion) || emotion.equals("null")) {
            throw new IllegalArgumentException("emotion or emotion is null");
        }
        return guidanceService.getGuidance(emotion.toLowerCase());
    }
}
