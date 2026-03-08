package com.parvez.guidance.app.repository;

import com.parvez.guidance.app.entity.Guidance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GuidanceRepository extends JpaRepository<Guidance, Long> {
    List<Guidance> findByEmotion(String emotion);
}