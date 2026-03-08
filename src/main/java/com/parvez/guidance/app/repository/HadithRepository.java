package com.parvez.guidance.app.repository;

import com.parvez.guidance.app.entity.Hadith;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HadithRepository extends JpaRepository<Hadith, Long> { }