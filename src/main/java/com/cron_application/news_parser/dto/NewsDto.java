package com.cron_application.news_parser.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class NewsDto {
    private Long id;
    private String headline;
    private String description;
    private LocalDateTime publicationTime;
}

