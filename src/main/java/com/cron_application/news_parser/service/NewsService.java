package com.cron_application.news_parser.service;

import com.cron_application.news_parser.dto.NewsDto;
import com.cron_application.news_parser.entity.News;

import java.time.LocalDateTime;
import java.util.List;

public interface NewsService {
    List<NewsDto> getAllNews();

    void saveNews(boolean isFromApi);

    void deleteNews(Long id);

    void deleteAll();

    List<NewsDto> getNewsByTimePeriod(LocalDateTime start, LocalDateTime end);
}
