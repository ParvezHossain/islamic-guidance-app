package com.parvez.guidance.app.repository;

import com.parvez.guidance.app.entity.Quran;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface QuranRepository extends JpaRepository<Quran, Long> {
    List<Quran> findBySurahNumberOrderByAyahNumberAsc(Integer surah);
    Optional<Quran> findBySurahNumberAndAyahNumber(int surahNumber, int ayahNumber);
}