package com.cron_application.news_parser.service;

import com.cron_application.news_parser.dto.NewsDto;
import com.cron_application.news_parser.entity.News;
import com.cron_application.news_parser.mapper.NewsMapper;
import com.cron_application.news_parser.repository.NewsRepository;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class NewsServiceImpl implements NewsService{

    private final NewsRepository newsRepository;
    private final NewsMapper newsMapper;

    public NewsServiceImpl(NewsRepository newsRepository, NewsMapper newsMapper) {
        this.newsRepository = newsRepository;
        this.newsMapper = newsMapper;
    }

    @Override
    public List<NewsDto> getAllNews() {
        return newsRepository.findAll().stream()
                .map(newsMapper::toDTO)
                .toList();
    }

    @Override
    public void saveNews(boolean isFromApi) {
        String url = "https://kun.uz";

        try {
            Document doc = Jsoup.connect(url).get();

            Elements articles = doc.select(".main-news__right-item");
            for (Element article : articles) {
                String subject = article.select(".gray-text").first().child(0).text();
                String time = subject.substring(subject.indexOf('|') + 2, subject.length());
                LocalTime publicationTime = LocalTime.parse(time, DateTimeFormatter.ofPattern("HH:mm"));
                LocalTime currentTime = LocalTime.now();
                LocalTime timeMinus20Minutes = publicationTime.minusMinutes(20);
                if(!isFromApi && !currentTime.isBefore(timeMinus20Minutes)){
                    break;
                }
                String title = article.select(".main-news__right-text").text();
                String articleUrl = article.select("a").attr("href");
                Document articleDoc = Jsoup.connect(url + articleUrl).get();
                Elements childElements = articleDoc.select(".news-inner__content-page").first().children();
                StringBuilder description = new StringBuilder();
                for (Element child : childElements) {
                    description.append(child.text()).append("\n");
                }

                News news = new News();
                news.setDescription(description.substring(0, Math.min(description.length(), 4999)));
                news.setHeadline(title);
                news.setPublicationTime(LocalDateTime.of(LocalDate.now(), publicationTime));
                newsRepository.save(news);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteNews(Long id) {
        newsRepository.deleteById(id);
    }

    @Override
    public void deleteAll() {
        newsRepository.deleteAll();
    }

    // Morning - 06:00 - 12:00
    // Day - 12:00 - 18:00
    // Evening - 18:00 - 00:00
    @Override
    public List<NewsDto> getNewsByTimePeriod(LocalDateTime start, LocalDateTime end) {
        return newsRepository.findByPublicationTimeBetween(start, end).stream()
                .map(newsMapper::toDTO)
                .toList();
    }
}

