package com.cron_application.news_parser.repository;

import com.cron_application.news_parser.entity.News;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface NewsRepository extends JpaRepository<News, Long> {
    List<News> findByPublicationTimeBetween(LocalDateTime start, LocalDateTime end);
}

