package com.cron_application.news_parser.mapper;

import com.cron_application.news_parser.dto.NewsDto;
import com.cron_application.news_parser.entity.News;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface NewsMapper {

    NewsDto toDTO(News news);
    News toEntity(NewsDto newsDTO);
}
