package com.cron_application.news_parser.frontend;

import com.cron_application.news_parser.dto.NewsDto;
import com.cron_application.news_parser.service.NewsServiceImpl;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;

import java.time.LocalDateTime;
import java.util.List;
@SpringBootApplication
@ComponentScan(basePackages = "com.cron_application.news_parser")
public class NewsApp extends Application {
    private NewsServiceImpl newsService;
    private List<NewsDto> newsList;
    private int currentIndex = 0;

    private Label headlineLabel;
    private Label descriptionLabel;
    private Label publicationTimeLabel;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void init() {
        ConfigurableApplicationContext context = new SpringApplicationBuilder(NewsApp.class).run();
        newsService = context.getBean(NewsServiceImpl.class);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("News Viewer");

        VBox vbox = new VBox();
        headlineLabel = new Label();
        descriptionLabel = new Label();
        publicationTimeLabel = new Label();
        Button prevButton = new Button("<");
        Button nextButton = new Button(">");

        ComboBox<String> timePeriodComboBox = new ComboBox<>();

        timePeriodComboBox.getItems().addAll("Morning", "Day", "Evening");
        timePeriodComboBox.setOnAction(e -> loadNewsByTimePeriod(timePeriodComboBox.getValue()));

        prevButton.setOnAction(e -> showPreviousNews());
        nextButton.setOnAction(e -> showNextNews());

        vbox.getChildren().addAll(timePeriodComboBox, headlineLabel, descriptionLabel, publicationTimeLabel, prevButton, nextButton);
        Scene scene = new Scene(vbox, 400, 300);
        primaryStage.setScene(scene);
        primaryStage.show();

        loadNews();
        showNews();
    }

    private void loadNews() {
        newsList = newsService.getAllNews();
    }

    private void loadNewsByTimePeriod(String timePeriod) {
        LocalDateTime start = LocalDateTime.now().withHour(0).withMinute(0).withSecond(0);

        switch (timePeriod) {
            case "Morning":
                start = start.plusHours(6);
                break;
            case "Day":
                start = start.plusHours(12);
                break;
            case "Evening":
                start = start.plusHours(18);
                break;
        }

        newsList = newsService.getNewsByTimePeriod(start, start.plusHours(6));
        currentIndex = 0;
        showNews();
    }

    private void showNews() {
        if (newsList != null && !newsList.isEmpty()) {
            NewsDto news = newsList.get(currentIndex);
            headlineLabel.setText(news.getHeadline());
            descriptionLabel.setText(news.getDescription());
            publicationTimeLabel.setText(news.getPublicationTime().toString());
        }
        else {
            headlineLabel.setText("No news available");
            descriptionLabel.setText("");
            publicationTimeLabel.setText("");
        }
    }

    private void showPreviousNews() {
        if (currentIndex > 0) {
            currentIndex--;
            showNews();
        }
    }

    private void showNextNews() {
        if (currentIndex < newsList.size() - 1) {
            currentIndex++;
            showNews();
        }
    }
}