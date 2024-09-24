package com.cron_application.news_parser.scheduler;

import com.cron_application.news_parser.dto.NewsDto;
import com.cron_application.news_parser.service.NewsServiceImpl;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class NewsScheduler {

    private final NewsServiceImpl newsService;

    public NewsScheduler(NewsServiceImpl newsService) {
        this.newsService = newsService;
    }

    @Scheduled(cron = "0 */20 * * * *")
    public void fetchNews() {
        newsService.saveNews(false);
    }

    @Scheduled(cron = "0 0 * * * *")
    public void deleteNews() {
        newsService.deleteAll();
    }
}

