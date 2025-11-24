package com.example.task04;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

/**
 * Обработчик, выводящий сообщения в файл с ротацией
 */
public class RotationFileHandler implements MessageHandler {
    private final String basePath;
    private final ChronoUnit rotationUnit;
    private LocalDateTime currentRotationTime;
    private String currentFilePath;

    public RotationFileHandler(String basePath, ChronoUnit rotationUnit) {
        this.basePath = basePath;
        this.rotationUnit = rotationUnit;
        this.currentRotationTime = getCurrentRotationTime();
        this.currentFilePath = generateFilePath();
    }

    private LocalDateTime getCurrentRotationTime() {
        LocalDateTime now = LocalDateTime.now();
        switch (rotationUnit) {
            case HOURS:
                return now.truncatedTo(ChronoUnit.HOURS);
            case DAYS:
                return now.truncatedTo(ChronoUnit.DAYS);
            case MINUTES:
                return now.truncatedTo(ChronoUnit.MINUTES);
            default:
                return now.truncatedTo(ChronoUnit.HOURS);
        }
    }

    private String generateFilePath() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm");
        return basePath + "_" + currentRotationTime.format(formatter) + ".log";
    }

    private void checkRotation() {
        LocalDateTime now = LocalDateTime.now();
        if (currentRotationTime.until(now, rotationUnit) >= 1) {
            currentRotationTime = getCurrentRotationTime();
            currentFilePath = generateFilePath();
        }
    }

    @Override
    public void handle(String message) {
        checkRotation();

        try (Writer writer = new FileWriter(currentFilePath, true)) {
            writer.write(message + System.lineSeparator());
        } catch (IOException e) {
            System.err.println("Ошибка записи в файл: " + e.getMessage());
        }
    }
}