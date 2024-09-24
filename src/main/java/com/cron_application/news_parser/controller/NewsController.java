package com.cron_application.news_parser.controller;

import com.cron_application.news_parser.dto.NewsDto;
import com.cron_application.news_parser.service.NewsServiceImpl;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/news")
public class NewsController {

    private final NewsServiceImpl newsService;

    public NewsController(NewsServiceImpl newsService) {
        this.newsService = newsService;
    }

//    @GetMapping
//    public List<NewsDto> getAllNews() {
//        return newsService.getAllNews();
//    }
//
//    @GetMapping("/time-period")
//    public List<NewsDto> getNewsByTimePeriod(@RequestParam LocalDateTime start, @RequestParam LocalDateTime end) {
//        return newsService.getNewsByTimePeriod(start, end);
//    }

    @GetMapping
    public void saveNews() {
        newsService.saveNews(true);
    }

    @DeleteMapping("/{id}")
    public void deleteNews(@PathVariable Long id) {
        newsService.deleteNews(id);
    }
}
