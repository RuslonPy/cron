package com.cron_application.news_parser.frontend;

import javafx.application.Application;
import javafx.stage.Stage;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class JavaFxSpringBootApplication extends Application {
    private ConfigurableApplicationContext context;

    @Override
    public void init() {
        context = new SpringApplicationBuilder(NewsApp.class).run();
    }

    @Override
    public void start(Stage primaryStage) {
        context.getBean(NewsApp.class).start(primaryStage);
    }

    @Override
    public void stop() {
        context.close();
    }

    public static void main(String[] args) {
        launch(args);
    }
}

